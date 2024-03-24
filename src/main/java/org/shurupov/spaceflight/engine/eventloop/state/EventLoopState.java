package org.shurupov.spaceflight.engine.eventloop.state;

public interface EventLoopState {
  void start();
  void stop();

  boolean isRun();
}
