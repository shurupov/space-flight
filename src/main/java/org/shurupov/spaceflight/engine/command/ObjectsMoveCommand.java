package org.shurupov.spaceflight.engine.command;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import org.shurupov.spaceflight.engine.abstraction.Movable;
import org.shurupov.spaceflight.engine.exception.CommandException;

@RequiredArgsConstructor
public class ObjectsMoveCommand implements Command {

  private final Consumer<Movable> recreator;
  private final List<Movable> movables;
  private final float dx;
  private final float dy;

  @Override
  public void execute() throws CommandException {
    for (Movable movable : movables) {
      movable.move(dx, dy);
      recreator.accept(movable);
    }
  }
}
