package org.shurupov.spaceflight;

import org.shurupov.spaceflight.engine.factory.GameFactory;
import org.shurupov.spaceflight.engine.graphic.Game;
import org.shurupov.spaceflight.spacegame.verticalscroller.VerticalGameMetaFactory;

public class SpaceFlightApp {

  public static void main(String[] args) {
    GameFactory gameFactory = new GameFactory(new VerticalGameMetaFactory());
    Game game = gameFactory.game();
    game.launch();
  }
}
