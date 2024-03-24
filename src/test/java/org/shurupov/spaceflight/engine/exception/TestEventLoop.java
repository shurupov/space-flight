package org.shurupov.spaceflight.engine.exception;

import java.util.Queue;
import lombok.RequiredArgsConstructor;
import org.shurupov.spaceflight.engine.command.Command;

@RequiredArgsConstructor
public class TestEventLoop {

  private final Queue<Command> commandQueue;
  private final HandlerSelector handlerSelector;

  void update() {
    while (!commandQueue.isEmpty()) {
      Command command = commandQueue.poll();
      try {
        command.execute();
      } catch (CommandException e) {
        handlerSelector.getHandler(e, command).execute();
      }
    }
  }
}
