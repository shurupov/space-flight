package org.shurupov.spaceflight.engine.exception.handler;

import java.util.Queue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.shurupov.spaceflight.engine.command.Command;
import org.shurupov.spaceflight.engine.command.RetryCommand;

@Slf4j
@RequiredArgsConstructor
public class CreateRetryCommandHandler implements CommandExceptionHandler {

  private final Queue<Command> commandQueue;
  private final Command command;

  @Override
  public void execute() {
    commandQueue.add(new RetryCommand(command));
  }
}
