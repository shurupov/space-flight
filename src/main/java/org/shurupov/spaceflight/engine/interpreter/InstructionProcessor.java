package org.shurupov.spaceflight.engine.interpreter;

import org.shurupov.spaceflight.engine.interpreter.expression.ActionNode;
import org.shurupov.spaceflight.engine.interpreter.expression.CheckObjectNode;
import org.shurupov.spaceflight.engine.interpreter.expression.PermissionNode;

public interface InstructionProcessor {

  PermissionNode permission(String userType, String userId, String objectId, String actionName);

  CheckObjectNode checkObject(String objectId, String objectType);

  ActionNode action(String objectId, String actionName, String actionParameter);

}
