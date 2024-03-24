package org.shurupov.spaceflight.engine.factory;

import org.shurupov.spaceflight.engine.graphic.render.DisplayManager;
import org.shurupov.spaceflight.engine.graphic.render.Loader;
import org.shurupov.spaceflight.engine.graphic.render.Renderer;
import org.shurupov.spaceflight.engine.graphic.shaders.StaticShader;

public interface GameMetaFactory {

  MacroCommandFactory create(Renderer renderer, StaticShader shader, DisplayManager displayManager, Loader loader);

  GameConfiguration configuration();

}
