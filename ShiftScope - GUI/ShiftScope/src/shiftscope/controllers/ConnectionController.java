package shiftscope.controllers;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import shiftscope.model.Song;

/**
 *
 * @author Carlos
 */
public class ConnectionController {

    private static ServerSocket serverSocket;
    private static Socket socket;
    private static final int PORT = 8000;
    private static DataOutputStream outputStream;
    private static BufferedReader inputStream;
    private static String receivedMessage;
    private static boolean connectionIsOn;
    private static String response;
    private static String connectionStatus;
    private static Gson JSONParser;

    public static Thread multicastThread;
    public static Thread receiverThread;

    public static void receive() {
        receiverThread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    serverSocket = new ServerSocket(PORT);
                    System.out.println("Esperando conexion");
                    socket = serverSocket.accept();
                    System.out.println("Aceptada");
                    multicastThread.stop();
                    System.out.println("Muerto papi");
                    connectionIsOn = true;
                    while (connectionIsOn) {
                        try {
                            inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            outputStream = new DataOutputStream(socket.getOutputStream());
                            receivedMessage = inputStream.readLine();
                            if (executeRequest(receivedMessage)) {
                                outputStream.writeUTF(getResponse());
                            }
                            System.out.println(receivedMessage);
                        } catch (IOException ex) {
                            System.err.println("Connection error...");
                        }
                    }

                } catch (IOException ex) {
                    Logger.getLogger(ConnectionController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        receiverThread.start();
    }

    public static void multicastToGroup() {
        multicastThread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    MulticastSocket socket;
                    InetAddress group;
                    group = InetAddress.getByName("225.4.5.6");
                    socket = new MulticastSocket();
                    while (true) {
                        try {
                            byte[] buffer;
                            buffer = "REQUEST_CONNECTION".getBytes();
                            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, 3456);
                            socket.send(packet);
                            System.out.println("Enviado...");
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(ConnectionController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ConnectionController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        multicastThread.start();
    }
    
    private static void closeConnection() {
        try {
            inputStream.close();
            serverSocket.close();
            serverSocket = null;
            receive();
            multicastToGroup();
        } catch (IOException ex) {
            Logger.getLogger(ConnectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static boolean executeRequest(String request) {
        response = "";
        if (request != null && !request.equals("null")) {

            if (request.equals("PLAY")) {
                MusicPlayer.play();
                response = "CURRENT_SONG:" + MusicPlayer.getCurrentSong().getName();
                return true;
            } else if (request.equals("NEXT")) {
                MusicPlayer.next();
                response = "CURRENT_SONG:" + MusicPlayer.getCurrentSong().getName();
                return true;
            } else if (request.equals("BACK")) {
                MusicPlayer.back();
                response = "CURRENT_SONG:" + MusicPlayer.getCurrentSong().getName();
                return true;
            } else if (request.equals("MUTE")) {
                MusicPlayer.mute();
                response = "CURRENT_SONG:" + MusicPlayer.getCurrentSong().getName();
                return true;
            } else if (request.equals("TRACKLIST")) {
                JSONParser = new Gson();
                response = JSONParser.toJson(SongController.searchSongs()).toString();
                System.out.println(getResponse());
                return true;
            } else if (request.equals("PAUSE")) {
                MusicPlayer.pause();
                response = "OK";
                return true;
            } else if (request.contains("PLAY")) {
                Song r = SongController.getSongById(Integer.parseInt(request.substring(request.lastIndexOf(":") + 1, request.length())));
                MusicPlayer.play(r);
                response = "CURRENT_SONG:" + MusicPlayer.getCurrentSong().getName();
                return true;
            } else {
                response = "OK";
                return true;
            }

        } else {
            closeConnection();
            connectionIsOn = false;
        }
        return false;
    }

    /**
     * @return the response
     */
    public static String getResponse() {
        return response;
    }

    /**
     * @return the connectionStatus
     */
    public static String getConnectionStatus() {
        return connectionStatus;
    }
}
