package org.shurupov.spaceflight.engine.eventloop.command;

import java.util.concurrent.BlockingQueue;
import lombok.extern.slf4j.Slf4j;
import org.shurupov.spaceflight.engine.command.Command;
import org.shurupov.spaceflight.engine.eventloop.EventLoop;
import org.shurupov.spaceflight.engine.exception.CommandException;
import org.shurupov.spaceflight.engine.exception.HandlerSelector;

@Slf4j
public class EventLoopCommandHandler implements CommandHandler {

  protected final BlockingQueue<Command> commandQueue;
  private final HandlerSelector handlerSelector;

  public EventLoopCommandHandler(EventLoop eventLoop) {
    commandQueue = eventLoop.getQueue();
    handlerSelector = eventLoop.getHandlerSelector();
  }

  @Override
  public void execute() {
    Command command;
    try {
      command = commandQueue.take();
      execute(command);
      commandQueue.put(command);
    } catch (InterruptedException e) {
      log.warn(e.getMessage(), e);
    }
  }

  protected void execute(Command command) {
    try {
      command.execute();
    } catch (CommandException e) {
      handlerSelector.getHandler(e, command).execute();
    }
  }
}
