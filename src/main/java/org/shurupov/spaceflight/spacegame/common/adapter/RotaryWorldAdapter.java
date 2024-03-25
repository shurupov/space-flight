package org.shurupov.spaceflight.spacegame.common.adapter;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.shurupov.spaceflight.engine.abstraction.Movable;
import org.shurupov.spaceflight.engine.abstraction.Rotary;
import org.shurupov.spaceflight.spacegame.common.strategy.RotateStrategy;

@Slf4j
@RequiredArgsConstructor
public class RotaryWorldAdapter implements Rotary {

  private final RotateStrategy rotateStrategy = new RotateStrategy();

  private final Movable centered;
  private final List<Movable> subjects;

  @Override
  public void rotate(float angle) {
    rotateWorld(angle);
  }

  private void rotateWorld(float angle) {
    for (Movable subject : subjects) {
      rotateStrategy.rotate(centered, subject, angle);
    }
  }
}
