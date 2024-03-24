package org.shurupov.spaceflight.game.command;

import lombok.RequiredArgsConstructor;
import org.lwjgl.glfw.GLFW;
import org.shurupov.spaceflight.engine.command.Command;
import org.shurupov.spaceflight.engine.exception.CommandException;
import org.shurupov.spaceflight.engine.graphic.entity.Entity;
import org.shurupov.spaceflight.engine.graphic.io.Keyboard;
import org.shurupov.spaceflight.engine.graphic.io.Mouse;

@RequiredArgsConstructor
public class ControlMacroCommand implements Command {

  private final Entity spaceship;

  @Override
  public void execute() throws CommandException {
    if(Keyboard.isKeyDown(GLFW.GLFW_KEY_A) || Keyboard.isKeyDown(GLFW.GLFW_KEY_LEFT)) {
      spaceship.increasePosition(-0.01f, 0, 0);
    } else if(Keyboard.isKeyDown(GLFW.GLFW_KEY_D) || Keyboard.isKeyDown(GLFW.GLFW_KEY_RIGHT)) {
      spaceship.increasePosition(0.01f, 0, 0);
    } else if(Keyboard.isKeyDown(GLFW.GLFW_KEY_W) || Keyboard.isKeyDown(GLFW.GLFW_KEY_UP)) {
      spaceship.increasePosition(0f, 0.01f, 0);
    } else if(Keyboard.isKeyDown(GLFW.GLFW_KEY_S) || Keyboard.isKeyDown(GLFW.GLFW_KEY_DOWN)) {
      spaceship.increasePosition(0f, -0.01f, 0);
    }
    if (Mouse.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT))
      spaceship.increaseRotation(0, 0, 1);
    else if (Mouse.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_RIGHT))
      spaceship.increaseRotation(0, 0, -1);
  }
}
