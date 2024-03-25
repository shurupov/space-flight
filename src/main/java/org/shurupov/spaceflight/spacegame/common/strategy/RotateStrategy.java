package org.shurupov.spaceflight.spacegame.common.strategy;

import org.shurupov.spaceflight.engine.abstraction.Movable;

public class RotateStrategy {

  public void rotate(Movable centered, Movable subject, float angle) {
    double legX1 = (centered.getX() - subject.getX());
    double legY1 = (centered.getY() - subject.getY());
    double hypo1 = Math.sqrt( legX1*legX1 + legY1*legY1 );
    double angle1 = Math.acos(legX1 / hypo1);
    double angle2 = angle1 + angle;
    float x2 = centered.getX() - (float) Math.cos(angle2);
    float y2 = centered.getY() - (float) Math.sin(angle2);
    subject.setX(x2);
    subject.setY(y2);
  }
}
