package org.shurupov.spaceflight.engine.graphic;

import lombok.RequiredArgsConstructor;
import org.shurupov.spaceflight.engine.command.Command;
import org.shurupov.spaceflight.engine.eventloop.EventLoop;
import org.shurupov.spaceflight.engine.graphic.render.DisplayManager;
import org.shurupov.spaceflight.engine.graphic.render.Loader;
import org.shurupov.spaceflight.engine.graphic.shaders.StaticShader;

@RequiredArgsConstructor
public class Game {

  private final DisplayManager displayManager;
  private final Loader loader; // загрузчик моделей
  private final StaticShader shader; // шейдер статических моделей
  private final EventLoop eventLoop;

  public void loop() {
    eventLoop.run();
  }

  public void cleanUp() {
    shader.cleanUp(); // очищаем шейдер статических моделей
    loader.cleanUp(); // очищаем память от загруженной модели
    displayManager.closeDisplay();
  }
}
