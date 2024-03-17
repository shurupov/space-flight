package org.shurupov.spaceflight.engine.factory;

import java.util.List;
import org.shurupov.spaceflight.engine.eventloop.Command;
import org.shurupov.spaceflight.engine.eventloop.IterationCommand;
import org.shurupov.spaceflight.engine.graphic.entity.Entity;
import org.shurupov.spaceflight.engine.graphic.render.Renderer;
import org.shurupov.spaceflight.engine.graphic.shaders.StaticShader;

public class IterationFactory {

  public Command iterationCommand(List<Entity> entities, Renderer renderer, StaticShader staticShader) {
    return new IterationCommand(entities, renderer, staticShader);
  }

}
