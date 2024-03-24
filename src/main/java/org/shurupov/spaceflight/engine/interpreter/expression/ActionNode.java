package org.shurupov.spaceflight.engine.interpreter.expression;


import org.shurupov.spaceflight.engine.interpreter.control.ControlAction;

public interface ActionNode extends AstNode<ControlAction> {
  String getActionParameter();
}
