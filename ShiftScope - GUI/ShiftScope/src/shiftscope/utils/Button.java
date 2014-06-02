package shiftscope.utils;

import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 *
 * @author Carlos
 */
public class Button extends Drawable{
    
    private final int id;
    private final String name;
    private final ImageIcon normal;
    private final ImageIcon over;
    private ImageIcon pressed;
    private int state;
    private final float scale;

    public Button(int id, String n, int x, int y, boolean relative, float scale) {
        this.id = id;
        this.name = n;
        this.x = x;
        this.y = y;
        this.state = 0;
        this.isRelative = relative;
        normal = new ImageIcon("imgs/buttons/" + getName() + "-normal.png");
        over = new ImageIcon("imgs/buttons/" + getName() + "-over.png");
        width = normal.getIconWidth();
        height = normal.getIconHeight();
        this.scale = scale;
    }
    
    public Button(int id, String n, boolean relative, float scale) {
        this.id = id;
        this.name = n;
        this.state = 0;
        this.isRelative = relative;
        normal = new ImageIcon("imgs/buttons/" + getName() + "-normal.png");
        over = new ImageIcon("imgs/buttons/" + getName() + "-over.png");
        width = normal.getIconWidth();
        height = normal.getIconHeight();
        this.scale = scale;
    }

    
    @Override
    public void paint(Graphics g) {
        if (!isRelative) {
            switch (state) {
                case 0:
                    g.drawImage(normal.getImage(), (int) (x - (width * scale) / 2), (int) (y - (height * scale) / 2), (int) (width * scale), (int) (height * scale), null);
                    break;
                case 1:
                    g.drawImage(over.getImage(), (int) (x - (width * scale) / 2), (int) (y - (height * scale) / 2), (int) (width * scale), (int) (height * scale), null);
                    break;
            }
        } else {
            switch (state) {
                case 0:
                    g.drawImage(normal.getImage(), (int) (x - (width * scale) / 2), (int) (StoryBoard.HEIGHT + y - (height * scale) / 2), (int) (width * scale), (int) (height * scale), null);
                    break;
                case 1:
                    g.drawImage(over.getImage(), (int) (x - (width * scale) / 2), (int) (StoryBoard.HEIGHT + y - (height * scale) / 2), (int) (width * scale), (int) (height * scale), null);
                    break;
            }
        }

    }

    @Override
    public void mouseMoved(int x, int y) {
        

    }

    @Override
    public void mouseOver(int x, int y) {
        if (!isRelative) {
            if (x >= this.x - (int) ((width * scale) / 2) && x <= this.x + (int) ((width * scale) / 2) && y >= this.y - (int) ((height * scale) / 2) && y <= this.y + (int) ((height * scale) / 2)) {
                state = 1;
            } else {
                state = 0;
            }
        } else {
            if (x >= this.x - (int) ((width * scale) / 2) && x <= this.x + (int) ((width * scale) / 2) && y >= StoryBoard.HEIGHT + this.y - (int) ((height * scale) / 2) && y <= StoryBoard.HEIGHT + this.y + (int) ((height * scale) / 2)) {
                state = 1;
            } else {
                state = 0;
            }
        }
    }

    @Override
    public boolean mousePressed(int x, int y) {
        if (!isRelative) {
            if (x >= this.x - (int) ((width * scale) / 2) && x <= this.x + (int) ((width * scale) / 2) && y >= this.y - (int) ((height * scale) / 2) && y <= this.y + (int) ((height * scale) / 2)) {
                return true;
            }
        } else {
            if (x >= this.x - (int) ((width * scale) / 2) && x <= this.x + (int) ((width * scale) / 2) && y >= StoryBoard.HEIGHT + this.y - (int) ((height * scale) / 2) && y <= StoryBoard.HEIGHT + this.y + (int) ((height * scale) / 2)) {
                return true;
            }
        }
        return false;
    }
    
    

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
}
