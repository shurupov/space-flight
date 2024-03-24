package org.shurupov.spaceflight.engine.eventloop.state;

import lombok.RequiredArgsConstructor;
import org.shurupov.spaceflight.engine.eventloop.EventLoop;

@RequiredArgsConstructor
public class Run implements EventLoopState {

  private final EventLoop eventLoop;

  @Override
  public void start() {

  }

  @Override
  public void stop() {
    eventLoop.setState(new Stop());
  }

  @Override
  public boolean isRun() {
    return true;
  }
}
