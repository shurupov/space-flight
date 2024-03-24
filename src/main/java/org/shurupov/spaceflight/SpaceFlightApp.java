package org.shurupov.spaceflight;

import org.shurupov.spaceflight.engine.factory.GameFactory;
import org.shurupov.spaceflight.engine.graphic.Game;
import org.shurupov.spaceflight.game.GameMetaFactoryImpl;

public class SpaceFlightApp {

  public static void main(String[] args) {
    GameFactory gameFactory = new GameFactory(new GameMetaFactoryImpl());
    Game game = gameFactory.game();
    game.launch();
  }
}
