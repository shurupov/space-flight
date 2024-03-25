package org.shurupov.spaceflight.spacegame.common;

import lombok.RequiredArgsConstructor;
import org.shurupov.spaceflight.engine.factory.GameConfiguration;
import org.shurupov.spaceflight.engine.factory.MacroCommandFactory;
import org.shurupov.spaceflight.engine.factory.GameMetaFactory;
import org.shurupov.spaceflight.engine.graphic.render.DisplayManager;
import org.shurupov.spaceflight.engine.graphic.render.Loader;
import org.shurupov.spaceflight.engine.graphic.render.Renderer;
import org.shurupov.spaceflight.engine.graphic.shaders.StaticShader;

@RequiredArgsConstructor
public abstract class AbstractGameMetaFactory implements GameMetaFactory {

  protected abstract GameEntitiesFactory gameEntitiesFactory(Loader loader);

  protected abstract AbstractMacroCommandFactory macroCommandFactory(
      Renderer renderer,
      StaticShader shader,
      DisplayManager displayManager,
      GameState gameState
  );

  @Override
  public MacroCommandFactory create(Renderer renderer, StaticShader shader,
      DisplayManager displayManager, Loader loader) {
    GameEntitiesFactory gameEntitiesFactory = gameEntitiesFactory(loader);
    GameState gameState = new GameState(gameEntitiesFactory);
    return macroCommandFactory(renderer, shader, displayManager, gameState);
  }

  @Override
  public GameConfiguration configuration() {
    return new GameConfigurationImpl();
  }
}
