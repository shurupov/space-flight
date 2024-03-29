package org.shurupov.spaceflight.spacegame.common;

import static org.shurupov.spaceflight.spacegame.common.adapter.AdapterFactory.movables;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.shurupov.spaceflight.engine.abstraction.Movable;
import org.shurupov.spaceflight.engine.graphic.entity.Entity;
import org.shurupov.spaceflight.engine.interpreter.InstructionParser;
import org.shurupov.spaceflight.spacegame.common.interpreter.InstructionProcessorImpl;

@Slf4j
public class GameState {

  @Getter
  private final InstructionParser instructionParser;
  @Getter
  private Entity spaceship;
  @Getter
  private final List<Entity> meteors;
  @Getter
  private final List<List<Entity>> stars;
  @Getter
  private final List<Movable> worldMovables;

  public GameState(GameEntitiesFactory gameEntitiesFactory, List<String> actions) {

    instructionParser = new InstructionParser(new InstructionProcessorImpl(this, actions));

    try {
      spaceship = gameEntitiesFactory.spaceship();
    } catch (IOException e) {
      log.error("Spaceship loading failed");
    }
    meteors = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      try {
        meteors.add(gameEntitiesFactory.meteor());
      } catch (IOException e) {
        log.error("Meteor loading failed");
      }
    }

    stars = new ArrayList<>();
    for (int si = 0; si < 3; si++) {
      List<Entity> starLayer = new ArrayList<>();
      for (int i = 0; i < 100; i++) {
        try {
          starLayer.add(gameEntitiesFactory.star(si));
        } catch (IOException e) {
          log.error("Star loading failed");
        }
      }
      stars.add(starLayer);
    }

    worldMovables = new ArrayList<>();
    worldMovables.addAll(movables(meteors));
    worldMovables.addAll(movables(stars.get(0)));
    worldMovables.addAll(movables(stars.get(1)));
    worldMovables.addAll(movables(stars.get(2)));
  }
}
