package org.shurupov.spaceflight.engine.graphic.render;

import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MAJOR;
import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MINOR;
import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_CORE_PROFILE;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_FORWARD_COMPAT;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_PROFILE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetCursorPosCallback;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetMouseButtonCallback;
import static org.lwjgl.glfw.GLFW.glfwSetScrollCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.GL_TRUE;

import lombok.Getter;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.shurupov.spaceflight.engine.graphic.io.Keyboard;
import org.shurupov.spaceflight.engine.graphic.io.Mouse;

/**
 * Управление дисплеем
 */
public class DisplayManager {

    public static final int CANVAS_PIXEL_CHUNK = 10;

    /** заголовок окна*/
    private final String title;
    /** ширина окна */
    @Getter
    private final int windowWidth;
    /** высота окна */
    @Getter
    private final int windowHeight;

    /** ссылка на окно */
    public long window;
    
    private Keyboard keyboard;
    private Mouse mouse;

    public DisplayManager(String title, int windowWidth, int windowHeight) {
        this.title = title;
        this.windowWidth = windowWidth / CANVAS_PIXEL_CHUNK * CANVAS_PIXEL_CHUNK;
        this.windowHeight = windowHeight / CANVAS_PIXEL_CHUNK * CANVAS_PIXEL_CHUNK;
    }

    /**
     * Создание окна, объявляем до начала самой игры
     */
    public void createDisplay() {
        // Инициализация GLFW. Большинство функций GLFW не будут работать перед этим
        if (!glfwInit())
            throw new RuntimeException("Не могу инициализировать GLFW");

        // Настройка GLFW
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // окно будет скрыто после создания
        glfwWindowHint(GLFW_RESIZABLE, GL_FALSE); // окно будет изменяемого размера
        // Задается минимальная требуемая версия OpenGL. 
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4); // Мажорная
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2); // Минорная
        // Установка профайла для которого создается контекст
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);

        // создание окна
        window = glfwCreateWindow(windowWidth, windowHeight, title, 0, 0);
        if (window == 0) 
            throw new RuntimeException("Ошибка создания GLFW окна");

        // Получаем разрешение основного монитора/экрана 
        GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        assert vidMode != null;
        // устанавливаем окно в центр экрана
        glfwSetWindowPos(window, (vidMode.width() - windowWidth) / 2, (vidMode.height() - windowHeight) / 2);

        keyboard = new Keyboard();
        mouse = new Mouse();
        
        // регистрируем обратный вызов ввода с клавиатуры и мыши
        glfwSetKeyCallback(window, keyboard);
        glfwSetCursorPosCallback(window, mouse.getMouseMoveCallback());
        glfwSetMouseButtonCallback(window, mouse.getMouseButtonsCallback());
        glfwSetScrollCallback(window, mouse.getMouseScrollCallback());
        
        // Делаем контекст OpenGL текущим
        glfwMakeContextCurrent(window);
        // устанавливаем значение 1 чтобы ограничивать до 60 FPS
        glfwSwapInterval(1);
        // показываем окно
        glfwShowWindow(window);
        
        // Эта строка критически важна для взаимодействия LWJGL с контекстом GLGW OpenGL 
        // или любым контекстом, который управляется извне. LWJGL обнаруживает 
        // текущий контекст в текущем потоке, создает экземпляр GLCapabilities 
        // и делает привязки OpenGL доступными для использования.
        GL.createCapabilities();  
    }
    
    /**
     * Для обновления окна каждый кадр
     */
    public void updateDisplay() {
        glfwSwapBuffers(window); // поменяем цветовые буферы
        // Проверяет были ли вызваны какие либо события (вроде ввода с клавиатуры или перемещение мыши)
        glfwPollEvents(); 
    }
    
    /**
     * Закрытие окна по завершении игры.
     * Освободить обратные вызовы окна и уничтожить окно
     */
    public void closeDisplay() {
        mouse.destroy();
        keyboard.close();
        glfwWindowShouldClose(window); // Освобождаем обратные вызовы окна
        glfwDestroyWindow(window); // Уничтожаем окно
        glfwTerminate(); // Завершаем GLFW. Очистка выделенных нам ресурсов
    }
    
    /**
     * Проверяет в начале каждой итерации цикла, получил ли GLFW инструкцию к закрытию, 
     * @return если закрыто окно, то функция вернет false
     */
    public boolean shouldDisplayClose() {
        return !glfwWindowShouldClose(window);
    }
}
