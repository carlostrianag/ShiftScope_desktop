/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftscope.main;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import shiftscope.controllers.MusicPlayer;
import shiftscope.controllers.ConnectionController;
import shiftscope.utils.StoryBoard;
import shiftscope.views.MainView;

/**
 *
 * @author Carlos
 */
public class Main {

    public static String ROOT = "/home/carlos/Desktop/MERENGUE";
    public static MainView s;
    public static void main(String[] args) throws SocketException {
        try {
            StoryBoard.init();
            System.out.println(InetAddress.getLocalHost());
            System.out.println(Inet4Address.getLocalHost().getHostAddress());
            s = new MainView();
            s.setVisible(true);
            MusicPlayer.initPlayer();
            ConnectionController.receive();
            ConnectionController.multicastToGroup();
            
        } catch (UnknownHostException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
