package org.shurupov.spaceflight.spacegame.common.command;

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
public class ControlMacroCommand extends AbstractControlMacroCommand {

  public ControlMacroCommand(InstructionParser instructionParser, Queue<Command> controlQueue) {
    super(instructionParser, controlQueue);
  }

  @Override
  protected String getDslInstruction() {
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
    return dslInstruction;
  }
}
