package org.shurupov.spaceflight.spacegame.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.shurupov.spaceflight.engine.command.Command;
import org.shurupov.spaceflight.engine.command.ListMacroCommand;
import org.shurupov.spaceflight.engine.command.QueueMacroCommand;
import org.shurupov.spaceflight.engine.factory.MacroCommandFactory;
import org.shurupov.spaceflight.engine.graphic.entity.Entity;
import org.shurupov.spaceflight.engine.graphic.render.DisplayManager;
import org.shurupov.spaceflight.engine.graphic.render.Renderer;
import org.shurupov.spaceflight.engine.graphic.shaders.StaticShader;
import org.shurupov.spaceflight.spacegame.common.command.ControlMacroCommand;
import org.shurupov.spaceflight.spacegame.common.command.FrameUpdateMacroCommand;
import org.shurupov.spaceflight.engine.command.ObjectsMoveCommand;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractMacroCommandFactory implements MacroCommandFactory {

  protected static final Random RANDOM = new Random();
  private final Renderer renderer;
  private final StaticShader shader;
  private final DisplayManager displayManager;
  protected final GameState gameState;
  protected final Queue<Command> controlQueue;

  @Override
  public Command inputProcessingMacroCommand() {
    return new ControlMacroCommand(gameState.getInstructionParser(), controlQueue);
  }

  @Override
  public Command gameFrameProcessingMacroCommand() {
    return new ListMacroCommand(
        List.of(
            new QueueMacroCommand(controlQueue),
            objectsMoveCommand(gameState.getMeteors(), -0.003f),
            objectsMoveCommand(gameState.getStars().get(0), -0.001f),
            objectsMoveCommand(gameState.getStars().get(1), -0.0006f),
            objectsMoveCommand(gameState.getStars().get(2), -0.0003f)
        )
    );
  }
  protected abstract ObjectsMoveCommand objectsMoveCommand(List<Entity> entities, float dcoord);

  @Override
  public Command displayUpdateProcessingMacroCommand() {
    List<Entity> entities = new ArrayList<>();
    entities.addAll(gameState.getMeteors());
    entities.addAll(gameState.getStars().get(0));
    entities.addAll(gameState.getStars().get(1));
    entities.addAll(gameState.getStars().get(2));
    entities.add(gameState.getSpaceship());
    return new FrameUpdateMacroCommand(renderer, shader, displayManager, entities);
  }
}
