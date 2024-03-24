package org.shurupov.spaceflight.engine.command;

import lombok.RequiredArgsConstructor;
import org.shurupov.spaceflight.engine.exception.CommandException;

@RequiredArgsConstructor
public class SecondRetryCommand implements Command {

  private final Command command;

  @Override
  public void execute() throws CommandException {
    command.execute();
  }
}
