package it.polimi.ingsw.Client;

import it.polimi.ingsw.Network.ConnectionHandler;

import java.io.IOException;
import java.net.Socket;

public class ClientNetworkHandler extends ConnectionHandler {



    public static ClientNetworkHandler ConnectionSetup(int port, String ip) throws IOException {
        Socket socket = new Socket(ip,port);
        return new ClientNetworkHandler(socket);
    }

    public ClientNetworkHandler(Socket socket) {
        super(socket);
    }

    @Override
    protected void handleReadMessage(Object event) {

    }





}
