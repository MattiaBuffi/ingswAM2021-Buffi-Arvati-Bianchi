package it.polimi.ingsw.Network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class SocketCreator implements Runnable{



    private final int port;
    private final SocketHandler socketHandler;

    private boolean running;

    private ServerSocket serverSocket;


    public SocketCreator(int port, SocketHandler socketHandler){
        this.socketHandler = socketHandler;
        this.port = port;
    }


    private void addConnection(Socket socket){
        socketHandler.handleSocket(socket);
    }


    public boolean isRunning() {
        return running;
    }

    public void stop() {

        this.running = false;
        shutdown();

    }


    @Override
    public void run() {

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.running = true;

        while (running) {
            try {
                addConnection(serverSocket.accept());
            } catch (SocketException e){
                if (running){
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    private void shutdown(){
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
