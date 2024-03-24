package org.shurupov.spaceflight.engine.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.shurupov.spaceflight.engine.exception.CommandException;

@Slf4j
@RequiredArgsConstructor
public class ExceptionLogCommand implements Command {

  private final CommandException exception;

  @Override
  public void execute() throws CommandException {
    log.error("Exception handled", exception);
  }
}
