package org.shurupov.spaceflight.game.adapter;

import java.util.List;
import org.shurupov.spaceflight.engine.abstraction.Movable;
import org.shurupov.spaceflight.engine.graphic.entity.Entity;

public class AdapterFactory {

  public static Movable movable(Entity entity) {
    return new MovableAdapter(entity);
  }

  public static List<Movable> movables(List<Entity> entities) {
    return entities.stream()
        .map(AdapterFactory::movable)
        .toList();
  }
}
