package org.shurupov.spaceflight.spacegame.verticalscroller;

import static org.shurupov.spaceflight.spacegame.common.adapter.AdapterFactory.movables;

import java.util.List;
import java.util.Queue;
import org.shurupov.spaceflight.engine.command.Command;
import org.shurupov.spaceflight.engine.command.ObjectsMoveCommand;
import org.shurupov.spaceflight.engine.graphic.entity.Entity;
import org.shurupov.spaceflight.engine.graphic.render.DisplayManager;
import org.shurupov.spaceflight.engine.graphic.render.Renderer;
import org.shurupov.spaceflight.engine.graphic.shaders.StaticShader;
import org.shurupov.spaceflight.spacegame.common.AbstractMacroCommandFactory;
import org.shurupov.spaceflight.spacegame.common.GameState;

public class VerticalMacroCommandFactory extends AbstractMacroCommandFactory {

  public VerticalMacroCommandFactory(
      Renderer renderer,
      StaticShader shader,
      DisplayManager displayManager,
      GameState gameState,
      Queue<Command> controlQueue) {
    super(renderer, shader, displayManager, gameState, controlQueue);
  }

  @Override
  public ObjectsMoveCommand objectsMoveCommand(List<Entity> entities, float dcoord) {
    return new ObjectsMoveCommand(
        movable -> {
          if (movable.getY() < -0.7) {
            movable.setY(0.5f + RANDOM.nextFloat() * 0.1f);
            movable.setX(-0.5f + RANDOM.nextFloat());
          }
        },
        movables(entities),
        0,
        dcoord
    );
  }
}
