package org.shurupov.spaceflight.game.interpreter.action;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.shurupov.spaceflight.engine.abstraction.Movable;
import org.shurupov.spaceflight.engine.interpreter.control.ControlAction;

@Slf4j
@RequiredArgsConstructor
public class MoveXAction implements ControlAction {

  private final Movable movable;

  @Override
  public void apply(String dx) {
    log.debug("Moving by X {}", dx);
    movable.move(Float.parseFloat(dx), 0);
    log.debug("Moved by X {}", dx);
  }
}
