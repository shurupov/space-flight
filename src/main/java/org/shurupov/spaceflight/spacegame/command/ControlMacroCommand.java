package org.shurupov.spaceflight.spacegame.command;

import java.util.Queue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lwjgl.glfw.GLFW;
import org.shurupov.spaceflight.engine.command.Command;
import org.shurupov.spaceflight.engine.exception.CommandException;
import org.shurupov.spaceflight.engine.graphic.io.Keyboard;
import org.shurupov.spaceflight.engine.interpreter.InstructionParser;
import org.shurupov.spaceflight.engine.interpreter.expression.Instruction;

@Slf4j
@RequiredArgsConstructor
public class ControlMacroCommand implements Command {
  private final InstructionParser instructionParser;
  private final Queue<Command> controlQueue;

  @Override
  public void execute() throws CommandException {
    String dslInstruction = null;
    if(Keyboard.isKeyDown(GLFW.GLFW_KEY_A) || Keyboard.isKeyDown(GLFW.GLFW_KEY_LEFT)) {
      dslInstruction = "user default moveX -0.01 object spaceship";
    } else if(Keyboard.isKeyDown(GLFW.GLFW_KEY_D) || Keyboard.isKeyDown(GLFW.GLFW_KEY_RIGHT)) {
      dslInstruction = "user default moveX 0.01 object spaceship";
    } else if(Keyboard.isKeyDown(GLFW.GLFW_KEY_W) || Keyboard.isKeyDown(GLFW.GLFW_KEY_UP)) {
      dslInstruction = "user default moveY 0.01 object spaceship";
    } else if(Keyboard.isKeyDown(GLFW.GLFW_KEY_S) || Keyboard.isKeyDown(GLFW.GLFW_KEY_DOWN)) {
      dslInstruction = "user default moveY -0.01 object spaceship";
    }
    /*if (Mouse.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT))
      spaceship.increaseRotation(0, 0, 1);
    else if (Mouse.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_RIGHT))
      spaceship.increaseRotation(0, 0, -1);*/

    if (dslInstruction != null) {
      log.debug("instruction received: {}", dslInstruction);
      Instruction instruction = instructionParser.parse(dslInstruction);
      log.debug("instruction parsed");
      Command controlCommand = instruction.interpret();
      log.debug("instruction interpret");
      controlQueue.add(controlCommand);
    }
  }
}
