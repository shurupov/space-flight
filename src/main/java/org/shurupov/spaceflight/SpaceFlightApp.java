package org.shurupov.spaceflight;

import org.shurupov.spaceflight.engine.factory.GameFactory;
import org.shurupov.spaceflight.engine.factory.IterationFactory;
import org.shurupov.spaceflight.engine.graphic.Game;
import org.shurupov.spaceflight.game.GameEntitiesFactory;

public class SpaceFlightApp {

  public static void main(String[] args) {
    GameFactory gameFactory = new GameFactory(new IterationFactory(), new GameEntitiesFactory());
    Game game = gameFactory.game("Space Flight", 800, 800);
    game.loop();
    game.cleanUp();
  }
}
