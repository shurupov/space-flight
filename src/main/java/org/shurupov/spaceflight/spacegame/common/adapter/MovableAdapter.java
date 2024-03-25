package org.shurupov.spaceflight.spacegame.common.adapter;

import lombok.RequiredArgsConstructor;
import org.shurupov.spaceflight.engine.abstraction.Movable;
import org.shurupov.spaceflight.engine.graphic.entity.Entity;

@RequiredArgsConstructor
public class MovableAdapter implements Movable {

  private final Entity entity;

  @Override
  public void move(float dx, float dy) {
    entity.increasePosition(dx, dy, 0f);
  }

  @Override
  public void setX(float x) {
    entity.getPosition().x = x;
  }

  @Override
  public void setY(float y) {
    entity.getPosition().y = y;
  }

  @Override
  public float getX() {
    return entity.getPosition().x;
  }

  @Override
  public float getY() {
    return entity.getPosition().y;
  }
}
