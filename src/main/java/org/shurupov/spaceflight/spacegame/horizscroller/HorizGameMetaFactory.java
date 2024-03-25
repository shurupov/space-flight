package org.shurupov.spaceflight.spacegame.horizscroller;

import static org.shurupov.spaceflight.spacegame.common.interpreter.InstructionProcessorImpl.MOVE_X_ACTION_NAME;
import static org.shurupov.spaceflight.spacegame.common.interpreter.InstructionProcessorImpl.MOVE_Y_ACTION_NAME;

import java.util.LinkedList;
import java.util.List;
import org.shurupov.spaceflight.engine.graphic.render.DisplayManager;
import org.shurupov.spaceflight.engine.graphic.render.Loader;
import org.shurupov.spaceflight.engine.graphic.render.Renderer;
import org.shurupov.spaceflight.engine.graphic.shaders.StaticShader;
import org.shurupov.spaceflight.spacegame.common.AbstractMacroCommandFactory;
import org.shurupov.spaceflight.spacegame.common.GameEntitiesFactory;
import org.shurupov.spaceflight.spacegame.common.AbstractGameMetaFactory;
import org.shurupov.spaceflight.spacegame.common.GameState;

public class HorizGameMetaFactory extends AbstractGameMetaFactory {

  public HorizGameMetaFactory() {
    super(List.of(MOVE_X_ACTION_NAME, MOVE_Y_ACTION_NAME));
  }

  @Override
  protected GameEntitiesFactory gameEntitiesFactory(Loader loader) {
    return new HorizGameEntitiesFactory(loader);
  }

  @Override
  protected AbstractMacroCommandFactory macroCommandFactory(Renderer renderer, StaticShader shader,
      DisplayManager displayManager, GameState gameState) {
    return new HorizMacroCommandFactory(renderer, shader, displayManager, gameState, new LinkedList<>());
  }
}
