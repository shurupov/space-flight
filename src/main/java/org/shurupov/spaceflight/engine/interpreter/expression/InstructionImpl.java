package org.shurupov.spaceflight.engine.interpreter.expression;

import lombok.Builder;
import lombok.Value;
import org.shurupov.spaceflight.engine.command.Command;
import org.shurupov.spaceflight.engine.exception.CommandException;
import org.shurupov.spaceflight.engine.interpreter.control.ControlAction;
import org.shurupov.spaceflight.engine.interpreter.exception.ControlPermissionCommandException;
import org.shurupov.spaceflight.engine.interpreter.exception.ObjectNotFoundCommandException;

@Builder
@Value
public class InstructionImpl implements Instruction {

  PermissionNode permissionNode;
  CheckObjectNode checkObjectNode;
  ActionNode actionNode;

  @Override
  public Command interpret() {

    if (!permissionNode.interpret()) {
      throw new ControlPermissionCommandException();
    }

    if (!checkObjectNode.interpret()) {
      throw new ObjectNotFoundCommandException();
    }

    return new InstructionCommand(actionNode.interpret(), actionNode.getActionParameter());
  }

  @Value
  public static class InstructionCommand implements Command {

    ControlAction controlAction;
    String actionParameter;

    @Override
    public void execute() throws CommandException {
      controlAction.apply(actionParameter);
    }
  }
}
