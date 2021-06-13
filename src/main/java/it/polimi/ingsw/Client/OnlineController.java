package it.polimi.ingsw.Client;

import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.ModelEventHandler;
import it.polimi.ingsw.Network.ConnectionHandler;
import it.polimi.ingsw.Utils.Observable;
import it.polimi.ingsw.Utils.Observer;

import java.net.Socket;

public class OnlineController extends Controller {

    private ConnectionHandler<Message<ModelEventHandler>, Message<ClientEventHandler>> connectionHandler;

    public OnlineController(Socket socket){
        this.connectionHandler = new ConnectionHandler<>(socket, this::notify);
    }

    @Override
    public void disconnect() {
        this.connectionHandler.stop();
    }

    @Override
    public void update(Message<ClientEventHandler> event) {
        connectionHandler.send(event);
    }


}
