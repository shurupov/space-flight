package org.shurupov.spaceflight.engine.graphic;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.shurupov.spaceflight.engine.graphic.entity.Entity;
import org.shurupov.spaceflight.engine.graphic.entity.ModelTexture;
import org.shurupov.spaceflight.engine.graphic.entity.RawModel;
import org.shurupov.spaceflight.engine.graphic.entity.TexturedModel;
import org.shurupov.spaceflight.engine.graphic.io.Keyboard;
import org.shurupov.spaceflight.engine.graphic.io.Mouse;
import org.shurupov.spaceflight.engine.graphic.render.DisplayManager;
import org.shurupov.spaceflight.engine.graphic.render.Loader;
import org.shurupov.spaceflight.engine.graphic.render.Renderer;
import org.shurupov.spaceflight.engine.graphic.shaders.StaticShader;

public class GameLoop {



  public void loop() {
    DisplayManager.createDisplay();

    Loader loader = new Loader(); // загрузчик моделей
    Renderer renderer = new Renderer(); // визуализатор моделей
    StaticShader shader = new StaticShader(); // шейдер статических моделей

    int modelWidth = 235;
    int modelHeight = 630;
    int maxDimension = Math.max(modelWidth, modelHeight);
    int dimensionDifference = Math.abs(modelHeight-modelWidth);
    int dimensionSum = modelHeight+modelWidth;
    float[] modelVerticesInModelUnit = {
        0 + (float) dimensionDifference / 2 / maxDimension - 0.5f, 0.5f, 0f,
        0 + (float) dimensionDifference / 2 / maxDimension - 0.5f, -0.5f, 0f,
        0 + (float) dimensionSum / 2 / maxDimension - 0.5f, -0.5f, 0f,
        0 + (float) dimensionSum / 2 / maxDimension - 0.5f, 0.5f, 0f,
    };

    float[] vertices = new float[modelVerticesInModelUnit.length];
    for (int i = 0; i < vertices.length; i += 3) {
      vertices[i] = modelVerticesInModelUnit[i];
      vertices[i+1] = modelVerticesInModelUnit[i+1];
      vertices[i+2] = 0f;
    }

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
    ModelTexture texture = new ModelTexture(loader.loadTexture("src/main/resources/images/spaceRockets_003.png"));
    // Создание текстурной модели
    TexturedModel staticModel = new TexturedModel(model, texture);

    Entity entity = new Entity(
        staticModel,
        new Vector3f(-0.02f, 0.3f, 0),
        0, 0, -90,
        0.8f
    );

    // запускаем цикл пока пользователь не закроет окно
    while (DisplayManager.shouldDisplayClose()) {

      //TODO Перетащить в отдельную команду/команды ловли событий с устройств ввода
      if(Keyboard.isKeyDown(GLFW.GLFW_KEY_A) || Keyboard.isKeyDown(GLFW.GLFW_KEY_LEFT)) {
        entity.increasePosition(-0.01f, 0, 0);
      } else if(Keyboard.isKeyDown(GLFW.GLFW_KEY_D) || Keyboard.isKeyDown(GLFW.GLFW_KEY_RIGHT)) {
        entity.increasePosition(0.01f, 0, 0);
      } else if(Keyboard.isKeyDown(GLFW.GLFW_KEY_W) || Keyboard.isKeyDown(GLFW.GLFW_KEY_UP)) {
        entity.increasePosition(0f, 0.01f, 0);
      } else if(Keyboard.isKeyDown(GLFW.GLFW_KEY_S) || Keyboard.isKeyDown(GLFW.GLFW_KEY_DOWN)) {
        entity.increasePosition(0f, -0.01f, 0);
      }
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
