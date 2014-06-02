package shiftscope.controllers;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import maryb.player.Player;
import maryb.player.PlayerState;
import shiftscope.model.Song;
import shiftscope.utils.PlayList;

/**
 *
 * @author Carlos
 */
public class MusicPlayer {

    private static Player player;
    private static Song currentSong;
    private static ArrayList<Song> queue;
    private static PlayList playList;
    private static int currentTrackOnQueue;

    public static void play() {
        if (PlayerState.PAUSED == player.getState()) {
            try {
                player.playSync();
            } catch (InterruptedException ex) {
                Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void play(Song song) {
        if (PlayerState.PLAYING == player.getState()) {
            queue.add(song);
            System.out.println("Cancion encolada...");
        } else {
            queue.add(song);
            if (!player.isEndOfMediaReached()) {
                try {
                    player.stopSync();
                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            currentSong = song;
            player.setSourceLocation(song.getLocalPath());
            player.play();
            
        }
        playList.addTrack(song);

    }
    
    public static void next() {
        Song nextSong;
        if (currentTrackOnQueue <= getQueue().size() - 1) {
            nextSong = getQueue().get(currentTrackOnQueue);
            currentTrackOnQueue++;
            if (!player.isEndOfMediaReached()) {
                try {
                    player.stopSync();
                } catch (InterruptedException ex) {
                    Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            currentSong = nextSong;
            player.setSourceLocation(nextSong.getLocalPath());
            player.play();
        } else {
            System.out.println("Se acabo la lista");
        }
    }
    
    public static void back() {
        Song previousSong;
        if (currentTrackOnQueue >= 0) {
            previousSong = getQueue().get(currentTrackOnQueue);
            currentTrackOnQueue--;
            if (!player.isEndOfMediaReached()) {
                try {
                    player.stopSync();
                } catch (InterruptedException ex) {
                    Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            currentSong = previousSong;
            player.setSourceLocation(previousSong.getLocalPath());
            player.play();
        } else {
            currentTrackOnQueue = 0;
            System.out.println("Principio de la list");
        }
    }

    public static void pause() {
        if (PlayerState.PLAYING == player.getState()) {
            try {
                player.pauseSync();
            } catch (InterruptedException ex) {
                Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void mute(){
        float volume = player.getCurrentVolume();
        if (volume != 0) {
            try {
                player.pauseSync();
                player.setCurrentVolume(0);
                player.playSync();
            } catch (InterruptedException ex) {
                Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            try {
                player.pauseSync();
                player.setCurrentVolume(0.5f);
                player.playSync();
            } catch (InterruptedException ex) {
                Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void initPlayer() {
        player = new Player();
        queue = new ArrayList();
        currentTrackOnQueue = 0;
    }
    
    
    
    public static void addPlayList(PlayList p) {
        playList = p;
    }

    /**
     * @return the queue
     */
    public static ArrayList<Song> getQueue() {
        return queue;
    }

    public static Song getCurrentSong() {
        return currentSong;
    }
    
}
