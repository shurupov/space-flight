package org.shurupov.spaceflight.engine.eventloop;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lwjgl.glfw.GLFW;
import org.shurupov.spaceflight.engine.graphic.entity.Entity;
import org.shurupov.spaceflight.engine.graphic.io.Keyboard;
import org.shurupov.spaceflight.engine.graphic.io.Mouse;
import org.shurupov.spaceflight.engine.graphic.render.DisplayManager;
import org.shurupov.spaceflight.engine.graphic.render.Renderer;
import org.shurupov.spaceflight.engine.graphic.shaders.StaticShader;

@Slf4j
@RequiredArgsConstructor
public class IterationMacroCommand implements Command {

  private final DisplayManager displayManager;
  private final List<Entity> entities;
  private final Renderer renderer;
  private final StaticShader shader;

  @Override
  public void execute() {
//TODO Перетащить в отдельную команду/команды ловли событий с устройств ввода
    if(Keyboard.isKeyDown(GLFW.GLFW_KEY_A) || Keyboard.isKeyDown(GLFW.GLFW_KEY_LEFT)) {
      entities.get(0).increasePosition(-0.01f, 0, 0);
      log.debug("x: {}, y: {}", entities.get(0).getPosition().x, entities.get(0).getPosition().y);
    } else if(Keyboard.isKeyDown(GLFW.GLFW_KEY_D) || Keyboard.isKeyDown(GLFW.GLFW_KEY_RIGHT)) {
      entities.get(0).increasePosition(0.01f, 0, 0);
      log.debug("x: {}, y: {}", entities.get(0).getPosition().x, entities.get(0).getPosition().y);
    } else if(Keyboard.isKeyDown(GLFW.GLFW_KEY_W) || Keyboard.isKeyDown(GLFW.GLFW_KEY_UP)) {
      entities.get(0).increasePosition(0f, 0.01f, 0);
      log.debug("x: {}, y: {}", entities.get(0).getPosition().x, entities.get(0).getPosition().y);
    } else if(Keyboard.isKeyDown(GLFW.GLFW_KEY_S) || Keyboard.isKeyDown(GLFW.GLFW_KEY_DOWN)) {
      entities.get(0).increasePosition(0f, -0.01f, 0);
      log.debug("x: {}, y: {}", entities.get(0).getPosition().x, entities.get(0).getPosition().y);
    }
    if (Mouse.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT))
      entities.get(0).increaseRotation(0, 0, 1);
    else if (Mouse.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_RIGHT))
      entities.get(0).increaseRotation(0, 0, -1);

    renderer.prepare(); // подготовка окна для рисования кадра

    shader.start(); // запускаем шейдер статических моделей
    for (Entity entity : entities) {
      renderer.render(entity, shader); // рисуем объект
    }
    shader.stop(); // останавливаем шейдер статических моделей

    displayManager.updateDisplay();
  }
}
