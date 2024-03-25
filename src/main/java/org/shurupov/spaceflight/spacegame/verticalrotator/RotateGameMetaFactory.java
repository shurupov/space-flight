package org.shurupov.spaceflight.spacegame.verticalrotator;

import static org.shurupov.spaceflight.spacegame.common.interpreter.InstructionProcessorImpl.MOVE_X_ACTION_NAME;
import static org.shurupov.spaceflight.spacegame.common.interpreter.InstructionProcessorImpl.MOVE_Y_ACTION_NAME;
import static org.shurupov.spaceflight.spacegame.common.interpreter.InstructionProcessorImpl.ROTATE_ACTION_NAME;

import java.util.LinkedList;
import java.util.List;
import org.shurupov.spaceflight.engine.graphic.render.DisplayManager;
import org.shurupov.spaceflight.engine.graphic.render.Loader;
import org.shurupov.spaceflight.engine.graphic.render.Renderer;
import org.shurupov.spaceflight.engine.graphic.shaders.StaticShader;
import org.shurupov.spaceflight.spacegame.common.AbstractGameMetaFactory;
import org.shurupov.spaceflight.spacegame.common.AbstractMacroCommandFactory;
import org.shurupov.spaceflight.spacegame.common.GameEntitiesFactory;
import org.shurupov.spaceflight.spacegame.common.GameState;

public class RotateGameMetaFactory extends AbstractGameMetaFactory {

  public RotateGameMetaFactory() {
    super(List.of(ROTATE_ACTION_NAME, MOVE_Y_ACTION_NAME));
  }

  @Override
  protected GameEntitiesFactory gameEntitiesFactory(Loader loader) {
    return new RotateGameEntitiesFactory(loader);
  }

  @Override
  protected AbstractMacroCommandFactory macroCommandFactory(Renderer renderer, StaticShader shader,
      DisplayManager displayManager, GameState gameState) {
    return new RotateMacroCommandFactory(renderer, shader, displayManager, gameState, new LinkedList<>());
  }
}
