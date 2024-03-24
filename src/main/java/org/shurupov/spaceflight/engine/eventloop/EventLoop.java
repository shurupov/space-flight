package org.shurupov.spaceflight.engine.eventloop;

import java.util.concurrent.BlockingQueue;
import java.util.function.Supplier;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.shurupov.spaceflight.engine.command.Command;
import org.shurupov.spaceflight.engine.eventloop.command.CommandHandler;
import org.shurupov.spaceflight.engine.eventloop.state.EventLoopState;
import org.shurupov.spaceflight.engine.eventloop.state.Run;
import org.shurupov.spaceflight.engine.exception.HandlerSelector;

@RequiredArgsConstructor
public class EventLoop {

  @Setter
  private CommandHandler handler;
  @Getter
  private final BlockingQueue<Command> queue;
  @Getter
  private final HandlerSelector handlerSelector;
  private final Supplier<Boolean> systemRun;

  @Setter
  @Getter
  private EventLoopState state = new Run(this);

  public void run() {
    while (state.isRun() && systemRun.get()) {
      handler.execute();
    }
  }
}
