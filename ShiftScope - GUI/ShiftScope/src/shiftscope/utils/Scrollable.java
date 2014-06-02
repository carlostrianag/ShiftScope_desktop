package shiftscope.utils;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Carlos
 */
public class Scrollable extends Drawable{

    public Scrollable(int x, int y, int width, int height) {
        super();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        isRelative = false;
        scale = 1;
    }

    public Scrollable(int x, int y, Drawable container) {
        super(container);
        this.x = x;
        this.y = y;
        isRelative = false;
        scale = 1;
    }
    
    @Override
    public void paint(Graphics g) {
        //30 es el margen
        if (container != null) {
            width = (container.getWidth() - 100) - x;
            height = (container.getHeight() - 120) - y;
        }
        g.setColor(Color.WHITE);
        g.drawRect(x, y, width, height);
        for (Drawable d : viewArray) {
            d.paint(g);
        }

    }

    @Override
    public void addDrawable(Drawable d) {
        super.addDrawable(d);
        d.setX(x + d.getX());
        d.setY(y + d.getY());
    }

    @Override
    public void mouseOver(int x, int y) {
        if (!isRelative) {
            if (x >= this.x && x <= this.x + (int) ((width * scale)) && y >= this.y  && y <= this.y + (int) ((height * scale))) {
            }
        }
        for (Drawable drawable : viewArray) {
            drawable.mouseOver(x, y);
        }
    }
}
