package org.shurupov.spaceflight.engine.eventloop.command;

import java.util.List;
import java.util.Random;
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
public class RunMacroCommand implements CommandHandler {

  private static final Random RANDOM = new Random();

  private final DisplayManager displayManager;
  private final List<Entity> entities;
  private final Renderer renderer;
  private final StaticShader shader;

  @Override
  public void execute() {
//TODO Перетащить в отдельную команду/команды ловли событий с устройств ввода
    if(Keyboard.isKeyDown(GLFW.GLFW_KEY_A) || Keyboard.isKeyDown(GLFW.GLFW_KEY_LEFT)) {
      entities.get(entities.size()-1).increasePosition(-0.01f, 0, 0);
//      log.debug("x: {}, y: {}", entities.get(entities.size()-1).getPosition().x, entities.get(entities.size()-1).getPosition().y);
    } else if(Keyboard.isKeyDown(GLFW.GLFW_KEY_D) || Keyboard.isKeyDown(GLFW.GLFW_KEY_RIGHT)) {
      entities.get(entities.size()-1).increasePosition(0.01f, 0, 0);
//      log.debug("x: {}, y: {}", entities.get(entities.size()-1).getPosition().x, entities.get(entities.size()-1).getPosition().y);
    } else if(Keyboard.isKeyDown(GLFW.GLFW_KEY_W) || Keyboard.isKeyDown(GLFW.GLFW_KEY_UP)) {
      entities.get(entities.size()-1).increasePosition(0f, 0.01f, 0);
//      log.debug("x: {}, y: {}", entities.get(entities.size()-1).getPosition().x, entities.get(entities.size()-1).getPosition().y);
    } else if(Keyboard.isKeyDown(GLFW.GLFW_KEY_S) || Keyboard.isKeyDown(GLFW.GLFW_KEY_DOWN)) {
      entities.get(entities.size()-1).increasePosition(0f, -0.01f, 0);
//      log.debug("x: {}, y: {}", entities.get(entities.size()-1).getPosition().x, entities.get(entities.size()-1).getPosition().y);
    }
    if (Mouse.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT))
      entities.get(0).increaseRotation(0, 0, 1);
    else if (Mouse.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_RIGHT))
      entities.get(0).increaseRotation(0, 0, -1);

    for (int i = 0; i < entities.size()-1; i++) {
      Entity entity = entities.get(i);
      entity.increasePosition(-0.005f, 0f, 0f);
      if (entity.getPosition().x < -0.7) {
        entity.getPosition().x = 0.5f + RANDOM.nextFloat() * 0.1f;
        entity.getPosition().y = -0.5f + RANDOM.nextFloat();
      }
    }

    renderer.prepare(); // подготовка окна для рисования кадра

    shader.start(); // запускаем шейдер статических моделей
    for (Entity entity : entities) {
      renderer.render(entity, shader); // рисуем объект
    }
    shader.stop(); // останавливаем шейдер статических моделей

    displayManager.updateDisplay();
  }
}
