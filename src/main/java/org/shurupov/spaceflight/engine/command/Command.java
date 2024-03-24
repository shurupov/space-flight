package org.shurupov.spaceflight.engine.command;

import org.shurupov.spaceflight.engine.exception.CommandException;

public interface Command {

  void execute() throws CommandException;

}
