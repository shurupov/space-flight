package org.shurupov.spaceflight.engine.io;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWScrollCallback;

/**
 * Класс для работы с мышью
 */
public class Mouse {

    private static final boolean[] buttons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
    private static double mouseX, mouseY;
    private static double mouseScrollX, mouseScrollY;
    private final GLFWCursorPosCallback mouseMove;
    private final GLFWMouseButtonCallback mouseButtons;
    private final GLFWScrollCallback mouseScroll;

    public Mouse() {

        mouseMove = new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double xpos, double ypos) {
                mouseX = xpos;
                mouseY = ypos;
            }
        };

        mouseButtons = new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                buttons[button] = (action != GLFW.GLFW_RELEASE);
            }
        };

        mouseScroll = new GLFWScrollCallback() {
            @Override
            public void invoke(long window, double offsetX, double offsetY) {
                mouseScrollX += offsetX;
                mouseScrollY += offsetY;
            }
        };
    }

    public GLFWCursorPosCallback getMouseMoveCallback() {
        return mouseMove;
    }

    public GLFWMouseButtonCallback getMouseButtonsCallback() {
        return mouseButtons;
    }

    public GLFWScrollCallback getMouseScrollCallback() {
        return mouseScroll;
    }

    public void destroy() {
        mouseMove.free();
        mouseButtons.free();
        mouseScroll.free();
    }

    public static boolean isButtonDown(int button) {
        return buttons[button];
    }

    public static double getMouseX() {
        return mouseX;
    }

    public static double getMouseY() {
        return mouseY;
    }

    public static double getMouseScrollX() {
        return mouseScrollX;
    }

    public static double getMouseScrollY() {
        return mouseScrollY;
    }
}

