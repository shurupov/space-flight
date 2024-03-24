package org.shurupov.spaceflight.game;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.shurupov.spaceflight.engine.command.Command;
import org.shurupov.spaceflight.engine.factory.GameFactory;
import org.shurupov.spaceflight.engine.factory.MacroCommandFactory;
import org.shurupov.spaceflight.engine.graphic.entity.Entity;
import org.shurupov.spaceflight.engine.graphic.render.DisplayManager;
import org.shurupov.spaceflight.engine.graphic.render.Renderer;
import org.shurupov.spaceflight.engine.graphic.shaders.StaticShader;
import org.shurupov.spaceflight.game.command.ControlMacroCommand;
import org.shurupov.spaceflight.game.command.FrameUpdateMacroCommand;
import org.shurupov.spaceflight.game.command.GameProcessingMacroCommand;

@Slf4j
@RequiredArgsConstructor
public class MacroCommandFactoryImpl implements MacroCommandFactory {

  private final Renderer renderer;
  private final StaticShader shader;
  private final DisplayManager displayManager;
  private final GameState gameState;

  @Override
  public Command inputProcessingMacroCommand() {
    return new ControlMacroCommand(gameState.getSpaceship());
  }

  @Override
  public Command gameFrameProcessingMacroCommand() {
    return new GameProcessingMacroCommand(gameState.getMeteors());
  }

  @Override
  public Command displayUpdateProcessingMacroCommand() {
    List<Entity> entities = new ArrayList<>(gameState.getMeteors());
    entities.add(gameState.getSpaceship());
    return new FrameUpdateMacroCommand(renderer, shader, displayManager, entities);
  }
}
