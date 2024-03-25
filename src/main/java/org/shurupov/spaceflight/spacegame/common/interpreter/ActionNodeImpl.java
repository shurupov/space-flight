package org.shurupov.spaceflight.spacegame.common.interpreter;

import static org.shurupov.spaceflight.spacegame.common.adapter.AdapterFactory.movable;
import static org.shurupov.spaceflight.spacegame.common.interpreter.InstructionProcessorImpl.MOVE_X_CATION_NAME;
import static org.shurupov.spaceflight.spacegame.common.interpreter.InstructionProcessorImpl.MOVE_Y_CATION_NAME;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.shurupov.spaceflight.engine.abstraction.Movable;
import org.shurupov.spaceflight.engine.interpreter.control.ControlAction;
import org.shurupov.spaceflight.engine.interpreter.exception.InstructionParseException;
import org.shurupov.spaceflight.engine.interpreter.expression.ActionNode;
import org.shurupov.spaceflight.spacegame.common.GameState;
import org.shurupov.spaceflight.spacegame.common.interpreter.action.MoveXAction;
import org.shurupov.spaceflight.spacegame.common.interpreter.action.MoveYAction;

@Slf4j
@RequiredArgsConstructor
public class ActionNodeImpl implements ActionNode {
  private final GameState gameState;
  private final String action;
  private final String parameter;

  @Override
  public String getActionParameter() {
    return parameter;
  }

  @Override
  public ControlAction interpret() {
    Movable movable = movable(gameState.getSpaceship());
    log.debug("Interpreting action");
    return switch (action) {
      case MOVE_X_CATION_NAME -> new MoveXAction(movable);
      case MOVE_Y_CATION_NAME -> new MoveYAction(movable);
      default -> throw new InstructionParseException();
    };
  }
}
