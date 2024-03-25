package org.shurupov.spaceflight.spacegame.verticalscroller;

import java.io.IOException;
import org.shurupov.spaceflight.engine.graphic.entity.Entity;
import org.shurupov.spaceflight.engine.graphic.render.Loader;
import org.shurupov.spaceflight.spacegame.common.GameEntitiesFactory;

public class VerticalGameEntitiesFactory extends GameEntitiesFactory {

  public VerticalGameEntitiesFactory(Loader loader) {
    super(loader);
  }

  public Entity spaceship() throws IOException {
    return spaceship(90, 0, -0.2f);
  }
}
