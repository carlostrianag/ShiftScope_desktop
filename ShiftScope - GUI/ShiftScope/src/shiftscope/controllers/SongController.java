package shiftscope.controllers;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import shiftscope.dao.SongDAO;
import shiftscope.main.Main;
import shiftscope.model.Song;

/**
 *
 * @author Carlos
 */
public class SongController {
    
    private static void create(Song song){
        SongDAO.addSong(song);
    }
    
    public static ArrayList<Song> searchSongs() {
        SongDAO.init();
        File root = new File(Main.ROOT);
        FileFilter fileFilter = new FileFilter(){

            @Override
            public boolean accept(File pathname) {
                if(pathname.getName().endsWith(".mp3")) {
                    return true;
                }
                return false;
            }
        
        };
        
        for (File f : root.listFiles(fileFilter)) {
            Song newSong = new Song();
            newSong.setName(f.getName());
            newSong.setArtist("CTDEE");
            newSong.setComposer("MegaNorte");
            newSong.setLocalPath(f.getAbsolutePath());
            create(newSong);
        }
        return SongDAO.getSongs();
        
    } 
    
    public static String getSongPathById(int id) {
        return SongDAO.getSongPathById(id);
    }
    
    public static Song getSongById(int id) {
        return SongDAO.getSongById(id);
    }
}
