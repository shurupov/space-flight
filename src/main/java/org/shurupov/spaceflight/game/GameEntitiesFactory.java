package org.shurupov.spaceflight.game;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import lombok.extern.slf4j.Slf4j;
import org.joml.Vector3f;
import org.shurupov.spaceflight.engine.graphic.entity.Entity;
import org.shurupov.spaceflight.engine.graphic.entity.ModelTexture;
import org.shurupov.spaceflight.engine.graphic.entity.RawModel;
import org.shurupov.spaceflight.engine.graphic.entity.TexturedModel;
import org.shurupov.spaceflight.engine.graphic.render.Loader;

@Slf4j
public class GameEntitiesFactory {

  public List<Entity> entities(Loader loader) {
    List<Entity> entities = new ArrayList<>();

    addEntity(entities, () -> spaceship(loader));

    return entities;
  }

  private void addEntity(List<Entity> entities, EntityCreator creator) {
    try {
      entities.add(creator.create());
    } catch (Throwable e) {
      log.error("Failed to load");
    }
  }

  public Entity spaceship(Loader loader) throws IOException {
    return entity(loader, "assets/images/spaceRockets_003.png", -90);
  }

  public Entity entity(Loader loader, String texturePath, float rotation) throws IOException {
    log.info("Loading model using texture {}", texturePath);

    BufferedImage bufferedImage = ImageIO.read(new FileInputStream(texturePath));
    int modelWidth = bufferedImage.getWidth();
    int modelHeight = bufferedImage.getHeight();

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
    ModelTexture texture = new ModelTexture(loader.loadTexture(texturePath));
    // Создание текстурной модели
    TexturedModel staticModel = new TexturedModel(model, texture);

    return new Entity(
        staticModel,
        new Vector3f(-0.02f, 0.3f, 0),
        0, 0, rotation,
        0.8f
    );
  }

  public interface EntityCreator {

    Entity create() throws Throwable;
  }

}
