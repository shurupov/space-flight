package org.shurupov.spaceflight.engine.graphic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.joml.Vector3f;

@AllArgsConstructor
@Data
public class Entity {

    /** текстурированная модель */
    private TexturedModel model;
    /** позиция модели в 3д пространстве */
    private Vector3f position;
    /** углы поворота модели в 3д пространстве */
    private float rotationX, rotationY, rotationZ;
    /** масштаб модели в 3д пространстве */
    private float scale;
    
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

    public TexturedModel getModel() {
        return model;
    }

    public void setModel(TexturedModel model) {
        this.model = model;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public float getRotationX() {
        return rotationX;
    }

    public void setRotationX(float rotationX) {
        this.rotationX = rotationX;
    }

    public float getRotationY() {
        return rotationY;
    }

    public void setRotationY(float rotationY) {
        this.rotationY = rotationY;
    }

    public float getRotationZ() {
        return rotationZ;
    }

    public void setRotationZ(float rotationZ) {
        this.rotationZ = rotationZ;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }
}

