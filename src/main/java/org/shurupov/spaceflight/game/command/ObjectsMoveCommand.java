package org.shurupov.spaceflight.game.command;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.shurupov.spaceflight.engine.command.Command;
import org.shurupov.spaceflight.engine.exception.CommandException;
import org.shurupov.spaceflight.engine.graphic.entity.Entity;

@RequiredArgsConstructor
public class ObjectsMoveCommand implements Command {

  private static final Random RANDOM = new Random();

  public ObjectsMoveCommand(List<Entity> entities, float dx) {
    this(entities, dx, 0);
  }

  private final List<Entity> entities;
  private final float dx;
  private final float dy;

  @Override
  public void execute() throws CommandException {
    for (int i = 0; i < entities.size()-1; i++) {
      Entity entity = entities.get(i);
      entity.increasePosition(dx, dy, 0f);
      if (entity.getPosition().x < -0.7) {
        entity.getPosition().x = 0.5f + RANDOM.nextFloat() * 0.1f;
        entity.getPosition().y = -0.5f + RANDOM.nextFloat();
      }
    }
  }
}
