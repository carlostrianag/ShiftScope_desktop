package shiftscope.utils;

import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.util.ArrayList;

/**
 *
 * @author Carlos
 */
public class StoryBoard {
    
    public static DigitalFont font;
    public static FontRenderContext frc;
    private static ArrayList<Drawable> scenes;
    private static int currentScene = -1;
    public static int WIDTH;
    public static int HEIGHT;
    
    public static void paintScene(Graphics g) {
        if (currentScene != -1) {
            scenes.get(currentScene).paint(g);
        }
    }
    
    public static void addScene(Drawable s) {
        scenes.add(s);
        currentScene = 0;
    }
    
    public static void init() {
        scenes = new ArrayList();
        font = new DigitalFont();
    }

    public static int getWidth() {
        return WIDTH;
    }

    public static void setWidth(int width) {
        StoryBoard.WIDTH = width;
    }

    public static int getHeight() {
        return HEIGHT;
    }

    public static void setHeight(int height) {
        StoryBoard.HEIGHT = height;
    }
    
    public static void mouseMoved(int x, int y) {
        scenes.get(currentScene).mouseMoved(x, y);
    }
    
    public static void mousePressed(int x, int y) {
        scenes.get(currentScene).mousePressed(x, y);
    }
}
