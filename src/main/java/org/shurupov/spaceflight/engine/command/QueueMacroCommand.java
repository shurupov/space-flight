package org.shurupov.spaceflight.engine.command;

import java.util.Queue;
import lombok.RequiredArgsConstructor;
import org.shurupov.spaceflight.engine.exception.CommandException;

@RequiredArgsConstructor
public class QueueMacroCommand implements Command {

  private final Queue<Command> commandQueue;

  @Override
  public void execute() throws CommandException {
    while (!commandQueue.isEmpty()) {
      commandQueue.poll().execute();
    }
  }
}
