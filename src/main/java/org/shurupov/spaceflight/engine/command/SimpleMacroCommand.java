package org.shurupov.spaceflight.engine.command;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.shurupov.spaceflight.engine.exception.CommandException;

@RequiredArgsConstructor
public class SimpleMacroCommand implements Command {

  private final List<Command> commands;

  @Override
  public void execute() throws CommandException {
    for (Command command : commands) {
      command.execute();
    }
  }
}
