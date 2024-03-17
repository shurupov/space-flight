package org.shurupov.spaceflight.engine.factory;

import lombok.RequiredArgsConstructor;
import org.shurupov.spaceflight.engine.eventloop.Command;
import org.shurupov.spaceflight.engine.graphic.Game;
import org.shurupov.spaceflight.engine.graphic.entity.Entity;
import org.shurupov.spaceflight.engine.graphic.render.DisplayManager;
import org.shurupov.spaceflight.engine.graphic.render.Loader;
import org.shurupov.spaceflight.engine.graphic.render.Renderer;
import org.shurupov.spaceflight.engine.graphic.shaders.StaticShader;
import org.shurupov.spaceflight.game.GameEntitiesFactory;

@RequiredArgsConstructor
public class GameFactory {

  private final IterationFactory iterationFactory;
  private final GameEntitiesFactory gameEntitiesFactory;

  public Game game() {
    DisplayManager.createDisplay();
    Loader loader = loader();
    Renderer renderer = renderer();
    StaticShader shader = staticShader();
    Command iterationCommand = iterationFactory.iterationCommand(gameEntitiesFactory.entities(loader), renderer, shader);
    return new Game(loader(), staticShader(), iterationCommand);
  }

  public Loader loader() {
    return new Loader();
  }

  public Renderer renderer() {
    return new Renderer();
  }

  public StaticShader staticShader() {
    return new StaticShader();
  }
}
