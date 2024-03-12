package org.shurupov.spaceflight.engine;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.shurupov.spaceflight.engine.entity.Entity;
import org.shurupov.spaceflight.engine.entity.ModelTexture;
import org.shurupov.spaceflight.engine.entity.RawModel;
import org.shurupov.spaceflight.engine.entity.TexturedModel;
import org.shurupov.spaceflight.engine.io.Keyboard;
import org.shurupov.spaceflight.engine.io.Mouse;
import org.shurupov.spaceflight.engine.render.DisplayManager;
import org.shurupov.spaceflight.engine.render.Loader;
import org.shurupov.spaceflight.engine.render.Renderer;
import org.shurupov.spaceflight.engine.shaders.StaticShader;

public class GameLoop {

  public void loop() {
    DisplayManager.createDisplay();

    Loader loader = new Loader(); // загрузчик моделей
    Renderer renderer = new Renderer(); // визуализатор моделей
    StaticShader shader = new StaticShader(); // шейдер статических моделей

    float[] vertices = {
        -0.5f,  0.5f, 0f, // V0
        -0.5f, -0.5f, 0f, // V1
        0.5f, -0.5f, 0f, // V2
        0.5f,  0.5f, 0f, // V3
    };

    int[] indices = {
        0, 1, 3, // Верхний левый треугольник
        3, 1, 2, // Нижний правый треугольник
    };

    float[] textureCoords = {
        0.0f, 0.0f, // V0
        0.0f, 1.0f, // V1
        1.0f, 1.0f, // V2
        1.0f, 0.0f, // V3
    };

    // загружаем массив вершин, текстурных координат и индексов в память GPU
    RawModel model = loader.loadToVao(vertices, textureCoords, indices);
    // загрузим текстуру используя загрузчик
    ModelTexture texture = new ModelTexture(loader.loadTexture("src/main/resources/images/image.png"));
    // Создание текстурной модели
    TexturedModel staticModel = new TexturedModel(model, texture);

    Entity entity = new Entity(staticModel,
        new Vector3f(0f, 0, 0),
        0, 0, 30,
        1.0f);

    // запускаем цикл пока пользователь не закроет окно
    while (DisplayManager.shouldDisplayClose()) {
      if(Keyboard.isKeyDown(GLFW.GLFW_KEY_A) || Keyboard.isKeyDown(GLFW.GLFW_KEY_LEFT))
        entity.increasePosition(-0.1f, 0, 0);
      else if(Keyboard.isKeyDown(GLFW.GLFW_KEY_D))
        entity.increasePosition(0.1f, 0, 0);
      if (Mouse.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT))
        entity.increaseRotation(0, 0, 1);
      else if (Mouse.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_RIGHT))
        entity.increaseRotation(0, 0, -1);

      renderer.prepare(); // подготовка окна для рисования кадра

      shader.start(); // запускаем шейдер статических моделей
      renderer.render(entity, shader); // рисуем объект
      shader.stop(); // останавливаем шейдер статических моделей

      DisplayManager.updateDisplay();
    }

    shader.cleanUp(); // очищаем шейдер статических моделей
    loader.cleanUp(); // очищаем память от загруженной модели
    DisplayManager.closeDisplay();
  }
}
