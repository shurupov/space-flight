package org.shurupov.spaceflight.engine.exception.handler.generator;

import java.util.function.BiFunction;
import lombok.extern.slf4j.Slf4j;
import org.shurupov.spaceflight.engine.command.Command;
import org.shurupov.spaceflight.engine.exception.CommandException;

@Slf4j
public class DefaultHandlerGenerator implements BiFunction<CommandException, Command, Command> {

  @Override
  public Command apply(CommandException exception, Command command) {
    return () -> log.info("Command {} threw exception {}", command, exception);
  }
}
