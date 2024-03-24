package org.shurupov.spaceflight.engine.interpreter.expression;

import org.shurupov.spaceflight.engine.command.Command;

public interface Instruction extends AstNode<Command> {
  Command interpret();
}
