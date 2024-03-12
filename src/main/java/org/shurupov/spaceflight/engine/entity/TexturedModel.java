package org.shurupov.spaceflight.engine.entity;

import lombok.Value;

/**
 * Класс Текстурированной модели.
 * Этот класс объединит данные модели и текстуры.
 */
@Value
public class TexturedModel {

    /** загруженная модель */
    RawModel rawModel;
    
    /** загруженная текстура */
    ModelTexture texture;
}

