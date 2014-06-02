package shiftscope.utils;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author Carlos
 */
public class Drawable {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean isRelative;
    protected float scale;
    protected Drawable container;
    protected ArrayList<Drawable> viewArray;

    
    public Drawable() {
        viewArray = new ArrayList();
    }
    
    public Drawable(Drawable container) {
        viewArray = new ArrayList();
        this.container = container;
    }
   

    public void paint(Graphics g) {
    }

    public void addDrawable(Drawable d) {
        viewArray.add(d);
    }

    public void mouseMoved(int x, int y) {

    }
    
    public void mouseOver(int x, int y) {
        
    }
    
    public boolean mousePressed(int x, int y) {
        return false;
    }
    
    

    public static void adjustComponentsToRow(ArrayList<Button> components, int startX, int startY, int space) {
        if (!components.isEmpty()) {
            components.get(0).setX(startX);
            components.get(0).setY(startY);
            for (int i = 1; i < components.size(); i++) {
                components.get(i).setX(components.get(i - 1).getX() + components.get(i - 1).getWidth()/2 + space);
                components.get(i).setY(startY);
            }
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public ArrayList<Drawable> getViewArray() {
        return viewArray;
    }

    public void setViewArray(ArrayList<Drawable> viewArray) {
        this.viewArray = viewArray;
    }
    
}
