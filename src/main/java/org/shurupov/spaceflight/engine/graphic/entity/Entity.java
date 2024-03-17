package org.shurupov.spaceflight.engine.graphic.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.joml.Vector3f;

@AllArgsConstructor
@Getter
public class Entity {

    /** текстурированная модель */
    private final TexturedModel model;
    /** позиция модели в 3д пространстве */
    private final Vector3f position;
    /** углы поворота модели в 3д пространстве */
    private float rotationX, rotationY, rotationZ;
    /** масштаб модели в 3д пространстве */
    private final float scale;
    
    /**
     * Смещение модели относительно данной позиции
     * @param dx смещение по Х
     * @param dy смещение по Y
     * @param dz смещение по Z
     */
    public void increasePosition(float dx, float dy, float dz) {
        this.position.x += dx;
        this.position.y += dy;
        this.position.z += dz;
    }

    /**
     * Поворот модели относительно данной позиции
     * @param dx поворота по оси Х
     * @param dy поворота по оси Y
     * @param dz поворота по оси Z
     */
    public void increaseRotation(float dx, float dy, float dz) {
        this.rotationX += dx;
        this.rotationY += dy;
        this.rotationZ += dz;
    }
}

