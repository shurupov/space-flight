package org.shurupov.spaceflight.engine.abstraction;

public interface Movable {
  void move(float dx, float dy);
  void setX(float x);
  void setY(float y);

  float getX();
  float getY();
}
