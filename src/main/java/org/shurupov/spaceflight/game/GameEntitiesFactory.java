package org.shurupov.spaceflight.game;

import java.util.List;
import org.joml.Vector3f;
import org.shurupov.spaceflight.engine.graphic.entity.Entity;
import org.shurupov.spaceflight.engine.graphic.entity.ModelTexture;
import org.shurupov.spaceflight.engine.graphic.entity.RawModel;
import org.shurupov.spaceflight.engine.graphic.entity.TexturedModel;
import org.shurupov.spaceflight.engine.graphic.render.Loader;

public class GameEntitiesFactory {

  public List<Entity> entities(Loader loader) {
    return List.of(spaceship(loader));
  }

  public Entity spaceship(Loader loader) {
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

    return entity;
  }

}
