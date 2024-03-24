package org.shurupov.spaceflight.engine.factory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import lombok.RequiredArgsConstructor;
import org.shurupov.spaceflight.engine.command.Command;
import org.shurupov.spaceflight.engine.eventloop.EventLoop;
import org.shurupov.spaceflight.engine.eventloop.command.EventLoopCommandHandler;
import org.shurupov.spaceflight.engine.exception.HandlerSelector;
import org.shurupov.spaceflight.engine.graphic.Game;
import org.shurupov.spaceflight.engine.graphic.render.DisplayManager;
import org.shurupov.spaceflight.engine.graphic.render.Loader;
import org.shurupov.spaceflight.engine.graphic.render.Renderer;
import org.shurupov.spaceflight.engine.graphic.shaders.StaticShader;

@RequiredArgsConstructor
public class GameFactory {
  private final GameMetaFactory gameMetaFactory;

  public Game game() {

    GameConfiguration configuration = gameMetaFactory.configuration();

    DisplayManager displayManager = displayManager(
        configuration.windowTitle(),
        configuration.windowWidth(),
        configuration.windowHeight()
    );
    displayManager.createDisplay();
    Loader loader = loader();
    StaticShader shader = staticShader();
    Renderer renderer = renderer(shader, displayManager);

    MacroCommandFactory macroCommandFactory = gameMetaFactory.create(renderer, shader, displayManager, loader);

    HandlerSelector handlerSelector = new HandlerSelector();
    BlockingQueue<Command> queue = commandQueue(macroCommandFactory);
    EventLoop eventLoop = new EventLoop(queue, handlerSelector, displayManager::shouldNotDisplayClose);
    eventLoop.setHandler(new EventLoopCommandHandler(eventLoop));
    return new Game(displayManager, loader, shader, eventLoop);
  }

  public BlockingQueue<Command> commandQueue(MacroCommandFactory macroCommandFactory) {
    BlockingQueue<Command> queue = new LinkedBlockingQueue<>();
    queue.add(macroCommandFactory.inputProcessingMacroCommand());
    queue.add(macroCommandFactory.gameFrameProcessingMacroCommand());
    queue.add(macroCommandFactory.displayUpdateProcessingMacroCommand());
    return queue;
  }

  public DisplayManager displayManager(String title, int windowWidth, int windowHeight) {
    return new DisplayManager(title, windowWidth, windowHeight);
  }

  public Loader loader() {
    return new Loader();
  }

  public Renderer renderer(StaticShader shader, DisplayManager displayManager) {
    return new Renderer(shader, displayManager);
  }

  public StaticShader staticShader() {
    return new StaticShader();
  }
}
