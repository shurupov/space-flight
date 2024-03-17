package org.shurupov.spaceflight.engine.graphic.entity;

import lombok.Value;

/**
 * Представляет 3д модели хранящиеся в памяти
 */
@Value
public class RawModel {
    
    /** Идентификатор VAO (Объект вершинного массива)*/
    int vaoId;
    /** Сколько вершин хранит модель */
    int vertexCount;
}
