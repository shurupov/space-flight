package org.shurupov.spaceflight.game.interpreter;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.shurupov.spaceflight.engine.interpreter.InstructionProcessor;
import org.shurupov.spaceflight.engine.interpreter.expression.ActionNode;
import org.shurupov.spaceflight.engine.interpreter.expression.CheckObjectNode;
import org.shurupov.spaceflight.engine.interpreter.expression.PermissionNode;
import org.shurupov.spaceflight.game.GameState;

@RequiredArgsConstructor
public class InstructionProcessorImpl implements InstructionProcessor {

  public static final String CONTROL_OBJECT_NAME = "spaceship";
  public static final String MOVE_X_CATION_NAME = "moveX";
  public static final String MOVE_Y_CATION_NAME = "moveY";
  public static final List<String> AVAILABLE_ACTIONS = List.of(MOVE_X_CATION_NAME, MOVE_Y_CATION_NAME);

  private final GameState gameState;

  @Override
  public PermissionNode permission(String userType, String userId, String objectId,
      String actionName) {
    return () -> CONTROL_OBJECT_NAME.equals(objectId) && AVAILABLE_ACTIONS.contains(actionName);
  }

  @Override
  public CheckObjectNode checkObject(String objectId, String objectType) {
    return () -> CONTROL_OBJECT_NAME.equals(objectId);
  }

  @Override
  public ActionNode action(String objectId, String actionName, String actionParameter) {
    return new ActionNodeImpl(gameState, actionName, actionParameter);
  }
}
