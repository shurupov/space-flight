package org.shurupov.spaceflight.spacegame.common.strategy;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.shurupov.spaceflight.engine.abstraction.Movable;

class RotateStrategyTest {

  private final RotateStrategy rotateStrategy = new RotateStrategy();

  @Test
  void rotate() {

    float x = 1;
    float y = 1;

    float distance = 1;
    float angleBefore = 0;
    float dAngle = 0.5f;
    float angleAfter = angleBefore + dAngle;

    Movable centered = new MovableImpl(x, y);
    Movable rotated = new MovableImpl(x + distance * (float) Math.cos(angleBefore), y + distance * (float) Math.sin(angleBefore));
    rotateStrategy.rotate(centered, rotated, dAngle);

    assertThat(rotated.getX()).isEqualTo(x + distance * (float) Math.cos(angleAfter));
    assertThat(rotated.getY()).isEqualTo(y + distance * (float) Math.sin(angleAfter));

  }

  @AllArgsConstructor
  @Data
  public static class MovableImpl implements Movable {

    private float x;
    private float y;

    @Override
    public void move(float dx, float dy) {
      x += dx;
      y += dy;
    }
  }
}