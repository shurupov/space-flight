package org.shurupov.spaceflight.spacegame.common.interpreter.action;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.shurupov.spaceflight.engine.abstraction.Movable;
import org.shurupov.spaceflight.engine.interpreter.control.ControlAction;
import org.shurupov.spaceflight.spacegame.common.strategy.RotateStrategy;

@Slf4j
@RequiredArgsConstructor
public class RotateAction implements ControlAction {

  private final RotateStrategy rotateStrategy = new RotateStrategy();

  private final Movable centered;
  private final List<Movable> subjects;

  @Override
  public void apply(String da) {
    rotateWorld(Float.parseFloat(da));
  }

  private void rotateWorld(float angle) {
    for (Movable subject : subjects) {
      rotateStrategy.rotate(centered, subject, angle);
    }
  }
}
