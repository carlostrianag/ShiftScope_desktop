package shiftscope.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import shiftscope.model.Song;

/**
 *
 * @author Carlos
 */
public class Track extends Drawable {

    private Song song;

    public Track(Song s, Drawable container) {
        super(container);
        song = s;
        height = 45;
    }

    @Override
    public void paint(Graphics g) {
        width = container.getWidth();

        
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, y, width, height);
        g.setColor(Color.BLUE);
        g.drawRect(x, y, width, height);

        StoryBoard.font.setBold(true);
        StoryBoard.font.setFontSize(17);
        g.setColor(new Color(255, 255, 255));
        g.setFont(StoryBoard.font.getFont());
        Rectangle2D bounds = g.getFontMetrics(StoryBoard.font.getFont()).
                getStringBounds(song.getName(), g);
        g.setColor(Color.lightGray);
        g.drawString(song.getName(), x + 20, (int) ((y + (height/2)) - bounds.getHeight()/2));

    }

    /**
     * @return the song
     */
    public Song getSong() {
        return song;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param song the song to set
     */
    public void setSong(Song song) {
        this.song = song;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

}
