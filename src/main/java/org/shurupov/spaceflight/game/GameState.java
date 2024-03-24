package org.shurupov.spaceflight.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.shurupov.spaceflight.engine.graphic.entity.Entity;

@Slf4j
public class GameState {


  @Getter
  private Entity spaceship;
  @Getter
  private final List<Entity> meteors;


  public GameState(GameEntitiesFactory gameEntitiesFactory) {

    try {
      spaceship = gameEntitiesFactory.spaceship();
    } catch (IOException e) {
      log.error("Spaceship loading failed");
    }
    meteors = new ArrayList<>();
    for (int i = 0; i < 15; i++) {
      try {
        meteors.add(gameEntitiesFactory.meteor());
      } catch (IOException e) {
        log.error("Meteor loading failed");
      }
    }
  }
}
