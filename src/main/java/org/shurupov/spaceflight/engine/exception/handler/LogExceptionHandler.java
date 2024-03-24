package org.shurupov.spaceflight.engine.exception.handler;

import java.util.Queue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.shurupov.spaceflight.engine.command.Command;
import org.shurupov.spaceflight.engine.command.ExceptionLogCommand;
import org.shurupov.spaceflight.engine.exception.CommandException;

@Slf4j
@RequiredArgsConstructor
public class LogExceptionHandler implements CommandExceptionHandler {

  private final Queue<Command> commandQueue;
  private final CommandException exception;

  @Override
  public void execute() throws CommandException {
    commandQueue.add(new ExceptionLogCommand(exception));
  }
}
