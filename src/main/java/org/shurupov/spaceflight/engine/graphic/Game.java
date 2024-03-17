package org.shurupov.spaceflight.engine.graphic;

import lombok.RequiredArgsConstructor;
import org.shurupov.spaceflight.engine.eventloop.Command;
import org.shurupov.spaceflight.engine.graphic.render.DisplayManager;
import org.shurupov.spaceflight.engine.graphic.render.Loader;
import org.shurupov.spaceflight.engine.graphic.shaders.StaticShader;

@RequiredArgsConstructor
public class Game {

  private final Loader loader; // загрузчик моделей
  private final StaticShader shader; // шейдер статических моделей
  private final Command iterationCommand;

  public void loop() {

    // запускаем цикл пока пользователь не закроет окно
    while (DisplayManager.shouldDisplayClose()) {
      iterationCommand.execute();
    }
  }

  public void cleanUp() {
    shader.cleanUp(); // очищаем шейдер статических моделей
    loader.cleanUp(); // очищаем память от загруженной модели
    DisplayManager.closeDisplay();
  }
}
