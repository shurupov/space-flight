package org.shurupov.spaceflight.spacegame.common;

import org.shurupov.spaceflight.engine.factory.GameConfiguration;

public class GameConfigurationImpl implements GameConfiguration {
  @Override
  public String windowTitle() {
    return "Space Flight";
  }

  @Override
  public int windowWidth() {
    return 1200;
  }

  @Override
  public int windowHeight() {
    return 800;
  }
}
