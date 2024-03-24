package org.shurupov.spaceflight.engine.command;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.shurupov.spaceflight.engine.abstraction.Movable;
import org.shurupov.spaceflight.engine.exception.CommandException;

@RequiredArgsConstructor
public class ObjectsMoveCommand implements Command {

  private static final Random RANDOM = new Random();

  private final List<Movable> movables;
  private final float dx;
  private final float dy;

  @Override
  public void execute() throws CommandException {
    for (Movable movable : movables) {
      movable.move(dx, dy);
      if (movable.getX() < -0.7) {
        movable.setX(0.5f + RANDOM.nextFloat() * 0.1f);
        movable.setY(-0.5f + RANDOM.nextFloat());
      }
    }
  }
}
