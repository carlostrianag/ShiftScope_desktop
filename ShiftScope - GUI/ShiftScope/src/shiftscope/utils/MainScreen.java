package shiftscope.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import shiftscope.controllers.MusicPlayer;
import shiftscope.views.MainView;

/**
 *
 * @author Carlos
 */
public class MainScreen extends Drawable {

    
    private String currentSong;
    //private ArrayList<Song> songTracks;
    private PlayList playList;
    private ArrayList<Button> buttons;
    private ImageIcon background;
    private ImageIcon nowPlayingLabel;
    private float scale;

    public MainScreen() {
        super();
        //background = new ImageIcon("imgs\\deep-green.jpg");
        background = new ImageIcon("imgs/madera.jpg");
        nowPlayingLabel = new ImageIcon("imgs/buttons/control_22.png");
        
        
        Scrollable playListScroll = new Scrollable(100, 180, this);
        playList = new PlayList(0,0,playListScroll);
        MusicPlayer.addPlayList(playList);
        playListScroll.addDrawable(playList);
        addDrawable(playListScroll);
        
        buttons = new ArrayList();
        buttons.add(new Button(0, "play", true, 0.5f));
        buttons.add(new Button(1, "pause", true, 0.5f));
        buttons.add(new Button(2, "stop", true, 0.5f));
        Drawable.adjustComponentsToRow(buttons, 80, -70, 5);
        buttons.add(new Button(3, "browse", 50, 50, false, 1));
        this.scale = 1f;
    }

    @Override
    public void paint(Graphics g) {
        width = StoryBoard.WIDTH;
        height = StoryBoard.HEIGHT;
        g.drawImage(background.getImage(), 0, 0,StoryBoard.WIDTH, StoryBoard.HEIGHT, null);
        g.drawImage(nowPlayingLabel.getImage(), StoryBoard.WIDTH/2 - (int) (nowPlayingLabel.getIconWidth()*scale/2), 70, (int) (nowPlayingLabel.getIconWidth()*scale),(int) ( nowPlayingLabel.getIconHeight()*scale), null);
        for (Drawable d : viewArray) {
            d.paint(g);
        }
        for (Button b : buttons) {
            b.paint(g);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        for (Button b : buttons) {
            b.mouseOver(x, y);
        }
        for (Drawable drawable : viewArray) {
            drawable.mouseOver(x, y);
        }
    }

    @Override
    public boolean mousePressed(int x, int y) {
        for (Button b : buttons) {
            if (b.mousePressed(x, y)) {
                executeAction(b);
                return true;
            }
        }
        return false;
    }
    
    public void executeAction(Button b) {
        switch(b.getId()) {
            case 0:
                break;
            case 3:
                MainView.findMusic();
                break;
        }
    }
    
    
}
