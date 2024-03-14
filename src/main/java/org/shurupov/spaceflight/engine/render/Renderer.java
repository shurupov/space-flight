package org.shurupov.spaceflight.engine.render;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.shurupov.spaceflight.engine.entity.Entity;
import org.shurupov.spaceflight.engine.entity.RawModel;
import org.shurupov.spaceflight.engine.entity.TexturedModel;
import org.shurupov.spaceflight.engine.shaders.StaticShader;
import org.shurupov.spaceflight.engine.tools.Maths;

/**
 * Визуализация данных
 */
public class Renderer {

    /**
     * Вызввается каждый кадр данный метод.
     */
    public void prepare() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT); // Очистка экрана и рисование цветом в цветовом буфере
        GL11.glClearColor(0.1f, 0.2f, 0.3f, 1); // Загрузка выбранного цвета в цветовой буфер
    }
    
    /**
     * Визуализация объекта
     * @param entity объект в пространстве
     * @param shader какой статический шейдер применить к модели
     */
    public void render(Entity entity, StaticShader shader) {
        TexturedModel model = entity.getModel(); // текстурированная модель
        RawModel rawModel = model.getRawModel(); // данные модели
        
        // Связываем VAO модели, указываем что будем работать с этими данными
        GL30.glBindVertexArray(rawModel.getVaoId());
        // Активируем нулевой списки атрибутов
        GL20.glEnableVertexAttribArray(0); // координаты вершин
        GL20.glEnableVertexAttribArray(1); // текстурные координаты
        
        // создадим матрицу преобразования  и передадим преобразования 
        Matrix4f transformationMatrix = Maths.getTransformationMatrix(
                entity.getPosition(), 
                entity.getRotationX(), 
                entity.getRotationY(), 
                entity.getRotationZ(), 
                entity.getScale());
        // передача преобразований в шейдер
        shader.loadTransformationMatrix(transformationMatrix);
        
        // Активируем текстурный блок перед привязкой текстуры
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        // Привяжем её, чтобы функции, использующие текстуры, знали какую текстуру использовать
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getId());
        
        // Рисуем примитивы. Аргументы:
        // - тип примитива (в данном случаем треугольники)
        // - количество вершин в модели
        // - указываем тип индексов данных в памяти
        // - смещение
        GL11.glDrawElements(GL11.GL_TRIANGLES, rawModel.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
        
        // т.к. закончили использовать нулевой список атрибутов, то отключаем
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        // отвязываем VAO модели
        GL30.glBindVertexArray(0);
    }
}
