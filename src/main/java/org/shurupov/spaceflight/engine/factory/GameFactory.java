package org.shurupov.spaceflight.engine.factory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import lombok.RequiredArgsConstructor;
import org.shurupov.spaceflight.engine.command.Command;
import org.shurupov.spaceflight.engine.eventloop.EventLoop;
import org.shurupov.spaceflight.engine.eventloop.command.CommandHandler;
import org.shurupov.spaceflight.engine.eventloop.command.EventLoopCommandHandler;
import org.shurupov.spaceflight.engine.exception.HandlerSelector;
import org.shurupov.spaceflight.engine.graphic.Game;
import org.shurupov.spaceflight.engine.graphic.render.DisplayManager;
import org.shurupov.spaceflight.engine.graphic.render.Loader;
import org.shurupov.spaceflight.engine.graphic.render.Renderer;
import org.shurupov.spaceflight.engine.graphic.shaders.StaticShader;
import org.shurupov.spaceflight.game.GameEntitiesFactory;

@RequiredArgsConstructor
public class GameFactory {

  private final IterationFactory iterationFactory;
  private final GameEntitiesFactory gameEntitiesFactory;

  public Game game(String title, int windowWidth, int windowHeight) {
    DisplayManager displayManager = displayManager(title, windowWidth, windowHeight);
    displayManager.createDisplay();
    Loader loader = loader();
    StaticShader shader = staticShader();
    Renderer renderer = renderer(shader, displayManager);
    gameEntitiesFactory.setLoader(loader);
    CommandHandler iterationCommand = iterationFactory.iterationCommand(displayManager, gameEntitiesFactory.entities(), renderer, shader);
    HandlerSelector handlerSelector = new HandlerSelector();
    BlockingQueue<Command> queue = commandQueue(iterationCommand);
    EventLoop eventLoop = new EventLoop(queue, handlerSelector, displayManager::shouldNotDisplayClose);
    eventLoop.setHandler(new EventLoopCommandHandler(eventLoop));
    return new Game(displayManager, loader, staticShader(), eventLoop);
  }

  public BlockingQueue<Command> commandQueue(CommandHandler iterationCommand) {
    BlockingQueue<Command> queue = new LinkedBlockingQueue<>();
    queue.add(iterationCommand);
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
