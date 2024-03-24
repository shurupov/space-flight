package org.shurupov.spaceflight.engine.factory;

import java.util.List;
import org.shurupov.spaceflight.engine.eventloop.command.CommandHandler;
import org.shurupov.spaceflight.engine.eventloop.command.RunMacroCommand;
import org.shurupov.spaceflight.engine.graphic.entity.Entity;
import org.shurupov.spaceflight.engine.graphic.render.DisplayManager;
import org.shurupov.spaceflight.engine.graphic.render.Renderer;
import org.shurupov.spaceflight.engine.graphic.shaders.StaticShader;

public class IterationFactory {

  public CommandHandler iterationCommand(DisplayManager displayManager, List<Entity> entities, Renderer renderer, StaticShader staticShader) {
    return new RunMacroCommand(displayManager, entities, renderer, staticShader);
  }

}
