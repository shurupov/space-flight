package org.shurupov.spaceflight.engine.factory;

import lombok.RequiredArgsConstructor;
import org.shurupov.spaceflight.engine.eventloop.Command;
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
    Command iterationCommand = iterationFactory.iterationCommand(displayManager, gameEntitiesFactory.entities(), renderer, shader);
    return new Game(displayManager, loader, staticShader(), iterationCommand);
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
