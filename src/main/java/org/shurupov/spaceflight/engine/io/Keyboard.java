package org.shurupov.spaceflight.engine.io;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

/**
 * Класс обработки нажатия клавиш
 */
public class Keyboard extends GLFWKeyCallback {
    private static final boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST];

    @Override
    public void invoke(long window, int key, int scancode, int action, int mods) {
        keys[key] = action != GLFW.GLFW_RELEASE;
    }

    public static boolean isKeyDown(int keycode) {
        return keys[keycode];
    }
}

