package org.shurupov.spaceflight.game;

import lombok.RequiredArgsConstructor;
import org.shurupov.spaceflight.engine.factory.GameConfiguration;
import org.shurupov.spaceflight.engine.factory.MacroCommandFactory;
import org.shurupov.spaceflight.engine.factory.GameMetaFactory;
import org.shurupov.spaceflight.engine.graphic.render.DisplayManager;
import org.shurupov.spaceflight.engine.graphic.render.Loader;
import org.shurupov.spaceflight.engine.graphic.render.Renderer;
import org.shurupov.spaceflight.engine.graphic.shaders.StaticShader;

@RequiredArgsConstructor
public class GameMetaFactoryImpl implements GameMetaFactory {
  @Override
  public MacroCommandFactory create(Renderer renderer, StaticShader shader,
      DisplayManager displayManager, Loader loader) {
    GameEntitiesFactory gameEntitiesFactory = new GameEntitiesFactory(loader);
    GameState gameState = new GameState(gameEntitiesFactory);
    return new MacroCommandFactoryImpl(renderer, shader, displayManager, gameState);
  }

  @Override
  public GameConfiguration configuration() {
    return new GameConfigurationImpl();
  }
}
