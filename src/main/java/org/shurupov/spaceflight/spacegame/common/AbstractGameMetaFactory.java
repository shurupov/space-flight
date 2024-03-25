package org.shurupov.spaceflight.spacegame.common;

import static org.shurupov.spaceflight.spacegame.common.interpreter.InstructionProcessorImpl.MOVE_X_ACTION_NAME;
import static org.shurupov.spaceflight.spacegame.common.interpreter.InstructionProcessorImpl.MOVE_Y_ACTION_NAME;

import java.util.List;
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
  private final List<String> availableActions;

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
    GameState gameState = new GameState(gameEntitiesFactory, availableActions);
    return macroCommandFactory(renderer, shader, displayManager, gameState);
  }

  @Override
  public GameConfiguration configuration() {
    return new GameConfigurationImpl();
  }
}
