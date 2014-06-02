package shiftscope.dao;

import java.util.ArrayList;
import shiftscope.model.Song;

/**
 *
 * @author Carlos
 */
public class SongDAO {
    
    private static ArrayList<Song> songs;
    
    public static void addSong(Song song) {
        song.setIdSong(songs.size());
        songs.add(song);
    }
    
    public static String getSongPathById(int id) {
        for (Song song : songs) {
            if (id == song.getIdSong()) {
                return song.getLocalPath();
            }
        }
        return null;
    }
    
    public static Song getSongById(int id) {
        for (Song song : songs) {
            if (id == song.getIdSong()) {
                return song;
            }
        }
        return null;
    }
    
    public static void init(){
        songs = new ArrayList();
    }

    public static ArrayList<Song> getSongs() {
        return songs;
    }
    
}
