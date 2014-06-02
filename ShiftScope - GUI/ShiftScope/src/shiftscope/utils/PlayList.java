package shiftscope.utils;

import java.awt.Graphics;
import shiftscope.model.Song;

/**
 *
 * @author carlos
 */
public class PlayList extends Drawable{
    
    public PlayList(int x, int y, Drawable container) {
        super(container);
        isRelative = true;
        scale = 1;
        this.x = x;
        this.y = y;
    }
    
    public PlayList() {
        isRelative = true;
        width = 50;
        height = 100;
        scale = 1;
    }
    
    @Override
    public void paint(Graphics g) {
        int initialPositionY = 0;
        
        if (container != null) {
            initialPositionY = container.getY();
            width = container.getWidth();
            height = 0;
        }
        
        for (Drawable t : viewArray) {
            t.setX(container.getX());
            t.setY(initialPositionY);
            initialPositionY += t.getHeight();
            height = initialPositionY;
            t.paint(g);
        }
        
    }
    
    public void addTrack(Song t) {
        Track track = new Track(t, this);
        viewArray.add(track);
    }

    @Override
    public void mouseOver(int x, int y) {
        if (!isRelative) {
            if (x >= this.x && x <= this.x + (int) ((width * scale)) && y >= this.y  && y <= this.y + (int) ((height * scale))) {
                System.out.println("adentro del playlist");
            }
        }
        for (Drawable drawable : viewArray) {
            drawable.mouseOver(x, y);
        }
    }  

    @Override
    public boolean mousePressed(int x, int y) {
        return false;
    }
    
    
}
