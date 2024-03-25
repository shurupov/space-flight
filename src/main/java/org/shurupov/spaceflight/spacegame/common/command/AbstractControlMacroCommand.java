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
@RequiredArgsConstructor
public abstract class AbstractControlMacroCommand implements Command {
  private final InstructionParser instructionParser;
  private final Queue<Command> controlQueue;

  @Override
  public void execute() throws CommandException {
    String dslInstruction = getDslInstruction();

    if (dslInstruction != null) {
      log.debug("instruction received: {}", dslInstruction);
      Instruction instruction = instructionParser.parse(dslInstruction);
      log.debug("instruction parsed");
      Command controlCommand = instruction.interpret();
      log.debug("instruction interpret");
      controlQueue.add(controlCommand);
    }
  }

  protected abstract String getDslInstruction();
}
