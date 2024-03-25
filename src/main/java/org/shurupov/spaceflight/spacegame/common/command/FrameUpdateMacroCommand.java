package org.shurupov.spaceflight.spacegame.common.command;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.shurupov.spaceflight.engine.command.Command;
import org.shurupov.spaceflight.engine.exception.CommandException;
import org.shurupov.spaceflight.engine.graphic.entity.Entity;
import org.shurupov.spaceflight.engine.graphic.render.DisplayManager;
import org.shurupov.spaceflight.engine.graphic.render.Renderer;
import org.shurupov.spaceflight.engine.graphic.shaders.StaticShader;

@RequiredArgsConstructor
public class FrameUpdateMacroCommand implements Command {

  private final Renderer renderer;
  private final StaticShader shader;
  private final DisplayManager displayManager;
  private final List<Entity> entities;

  @Override
  public void execute() throws CommandException {
    renderer.prepare(); // подготовка окна для рисования кадра

    shader.start(); // запускаем шейдер статических моделей
    for (Entity entity : entities) {
      renderer.render(entity, shader); // рисуем объект
    }
    shader.stop(); // останавливаем шейдер статических моделей

    displayManager.updateDisplay();
  }
}
