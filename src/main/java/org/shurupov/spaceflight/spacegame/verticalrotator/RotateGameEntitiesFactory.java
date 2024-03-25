package org.shurupov.spaceflight.spacegame.verticalrotator;

import java.io.IOException;
import org.shurupov.spaceflight.engine.graphic.entity.Entity;
import org.shurupov.spaceflight.engine.graphic.render.Loader;
import org.shurupov.spaceflight.spacegame.common.GameEntitiesFactory;

public class RotateGameEntitiesFactory extends GameEntitiesFactory {

  public RotateGameEntitiesFactory(Loader loader) {
    super(loader);
  }

  public Entity spaceship() throws IOException {
    return spaceship(90, 0, -0.2f);
  }
}
