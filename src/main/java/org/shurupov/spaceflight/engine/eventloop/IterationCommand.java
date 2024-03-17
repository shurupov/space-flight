package org.shurupov.spaceflight.engine.eventloop;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.lwjgl.glfw.GLFW;
import org.shurupov.spaceflight.engine.graphic.entity.Entity;
import org.shurupov.spaceflight.engine.graphic.io.Keyboard;
import org.shurupov.spaceflight.engine.graphic.io.Mouse;
import org.shurupov.spaceflight.engine.graphic.render.DisplayManager;
import org.shurupov.spaceflight.engine.graphic.render.Renderer;
import org.shurupov.spaceflight.engine.graphic.shaders.StaticShader;

@RequiredArgsConstructor
public class IterationCommand implements Command {

  private final List<Entity> entities;
  private final Renderer renderer;
  private final StaticShader shader;

  @Override
  public void execute() {
//TODO Перетащить в отдельную команду/команды ловли событий с устройств ввода
    /*if(Keyboard.isKeyDown(GLFW.GLFW_KEY_A) || Keyboard.isKeyDown(GLFW.GLFW_KEY_LEFT)) {
      entity.increasePosition(-0.01f, 0, 0);
    } else if(Keyboard.isKeyDown(GLFW.GLFW_KEY_D) || Keyboard.isKeyDown(GLFW.GLFW_KEY_RIGHT)) {
      entity.increasePosition(0.01f, 0, 0);
    } else if(Keyboard.isKeyDown(GLFW.GLFW_KEY_W) || Keyboard.isKeyDown(GLFW.GLFW_KEY_UP)) {
      entity.increasePosition(0f, 0.01f, 0);
    } else if(Keyboard.isKeyDown(GLFW.GLFW_KEY_S) || Keyboard.isKeyDown(GLFW.GLFW_KEY_DOWN)) {
      entity.increasePosition(0f, -0.01f, 0);
    }
    if (Mouse.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT))
      entity.increaseRotation(0, 0, 1);
    else if (Mouse.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_RIGHT))
      entity.increaseRotation(0, 0, -1);*/

    renderer.prepare(); // подготовка окна для рисования кадра

    shader.start(); // запускаем шейдер статических моделей
    for (Entity entity : entities) {
      renderer.render(entity, shader); // рисуем объект
    }
    shader.stop(); // останавливаем шейдер статических моделей

    DisplayManager.updateDisplay();
  }
}
