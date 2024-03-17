package org.shurupov.spaceflight.game;

import static org.shurupov.spaceflight.engine.graphic.render.DisplayManager.CANVAS_PIXEL_CHUNK;
import static org.shurupov.spaceflight.game.GameEntitiesFactory.CoordinateType.X;
import static org.shurupov.spaceflight.game.GameEntitiesFactory.CoordinateType.Y;
import static org.shurupov.spaceflight.game.GameEntitiesFactory.Position.LEFT;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.joml.Vector3f;
import org.shurupov.spaceflight.engine.graphic.entity.Entity;
import org.shurupov.spaceflight.engine.graphic.entity.ModelTexture;
import org.shurupov.spaceflight.engine.graphic.entity.RawModel;
import org.shurupov.spaceflight.engine.graphic.entity.TexturedModel;
import org.shurupov.spaceflight.engine.graphic.render.DisplayManager;
import org.shurupov.spaceflight.engine.graphic.render.Loader;
import org.shurupov.spaceflight.engine.graphic.render.Loader;

@Slf4j
public class GameEntitiesFactory {

  @Setter
  private DisplayManager displayManager;
  @Setter
  private Loader loader;

  public List<Entity> entities() {
    List<Entity> entities = new ArrayList<>();

    addEntity(entities, () -> spaceship());
    for (int i = 0; i < 10; i++) {
      int finalI = i;
      addEntity(entities, () -> planet(finalI));
    }

    return entities;
  }

  private void addEntity(List<Entity> entities, EntityCreator creator) {
    try {
      entities.add(creator.create());
    } catch (Throwable e) {
      log.error("Failed to load texture");
    }
  }

  public Entity spaceship() throws IOException {
    return entity( "assets/images/spaceRockets_003.png", 0, 0, 50);
  }

  public Entity planet(int number) throws IOException {
    return entity("assets/images/planets/planet" + String.format("%02d", number) + ".png", 0, 50, 50);
  }

  private float calcCoordinate(Position position, CoordinateType coordType, int modelWidth, int modelHeight, int windowWidth, int windowHeight) {

    int maxDimension = Math.max(modelWidth, modelHeight);
    float result = switch (coordType) {
      case X -> (float) modelWidth;
      case Y -> (float) modelHeight;
      default -> throw new RuntimeException();
    };

    result = switch (position) {
      case TOP, LEFT -> maxDimension - result;
      case BOTTOM, RIGHT -> maxDimension + result;
      default -> throw new RuntimeException();
    };

    result /= 2;

    result /= maxDimension;

    result -= 0.5f;

    return result;
  }

  public enum Position {
    LEFT, RIGHT, TOP, BOTTOM
  }

  public enum CoordinateType {
    X, Y
  }

  public Entity entity(String texturePath, float rotation, float x, float y) throws IOException {
    log.info("Loading model using texture {}", texturePath);

    BufferedImage bufferedImage = ImageIO.read(new FileInputStream(texturePath));
    int modelWidth = bufferedImage.getWidth();
    int modelHeight = bufferedImage.getHeight();

    float[] modelVerticesInModelUnit = {
        calcCoordinate(LEFT, X, modelWidth, modelHeight, displayManager.getWindowWidth(), displayManager.getWindowHeight()),
        calcCoordinate(Position.TOP, Y, modelWidth, modelHeight, displayManager.getWindowWidth(), displayManager.getWindowHeight()),
        0,
        calcCoordinate(LEFT, X, modelWidth, modelHeight, displayManager.getWindowWidth(), displayManager.getWindowHeight()),
        calcCoordinate(Position.BOTTOM, Y, modelWidth, modelHeight, displayManager.getWindowWidth(), displayManager.getWindowHeight()),
        0,
        calcCoordinate(Position.RIGHT, X, modelWidth, modelHeight, displayManager.getWindowWidth(), displayManager.getWindowHeight()),
        calcCoordinate(Position.BOTTOM, Y, modelWidth, modelHeight, displayManager.getWindowWidth(), displayManager.getWindowHeight()),
        0,
        calcCoordinate(Position.RIGHT, X, modelWidth, modelHeight, displayManager.getWindowWidth(), displayManager.getWindowHeight()),
        calcCoordinate(Position.TOP, Y, modelWidth, modelHeight, displayManager.getWindowWidth(), displayManager.getWindowHeight()),
        0,
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

    float absoluteX = (x * CANVAS_PIXEL_CHUNK) / displayManager.getWindowWidth() - 0.5f;
    float absoluteY = (-y * CANVAS_PIXEL_CHUNK) / displayManager.getWindowHeight() + 0.5f;

    return new Entity(
        staticModel,
        new Vector3f(absoluteX, absoluteY, 0),
        0, 0, rotation,
        0.3f
    );
  }

  public interface EntityCreator {

    Entity create() throws Throwable;
  }

}
