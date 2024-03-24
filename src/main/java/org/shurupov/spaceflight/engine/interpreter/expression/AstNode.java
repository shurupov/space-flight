package org.shurupov.spaceflight.engine.interpreter.expression;

public interface AstNode<O>  {
  O interpret();
}
