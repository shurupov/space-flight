package org.shurupov.spaceflight.engine.factory;

import org.shurupov.spaceflight.engine.command.Command;

public interface MacroCommandFactory {

  Command inputProcessingMacroCommand();
  Command gameFrameProcessingMacroCommand();
  Command displayUpdateProcessingMacroCommand();
}
