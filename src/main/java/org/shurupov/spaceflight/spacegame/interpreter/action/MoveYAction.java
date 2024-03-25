package org.shurupov.spaceflight.spacegame.interpreter.action;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.shurupov.spaceflight.engine.abstraction.Movable;
import org.shurupov.spaceflight.engine.interpreter.control.ControlAction;

@Slf4j
@RequiredArgsConstructor
public class MoveYAction implements ControlAction {

  private final Movable movable;

  @Override
  public void apply(String dy) {
    log.debug("Moving by Y {}", dy);
    movable.move(0, Float.parseFloat(dy));
    log.debug("Moved by Y {}", dy);
  }
}
