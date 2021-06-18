package it.polimi.ingsw.Server.Client;

import it.polimi.ingsw.Controller.GameController;
import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.ClientMessage.ActivateLeaderCard;
import it.polimi.ingsw.Message.ClientMessage.GameSize;
import it.polimi.ingsw.Message.ClientMessage.Login;
import it.polimi.ingsw.Message.ClientMessage.ThrowCard;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Network.ConnectionHandler;
import it.polimi.ingsw.Server.Server;
import it.polimi.ingsw.Utils.Observer;

public class ClientHandler implements ClientEventHandler, Observer<Message> {

    private boolean active;

    private Server server;

    private ConnectionHandler<Message, Message> connectionHandler;
    private String username;

    private Game game;

    private GameController gameController;

    

    public ClientHandler(Server server, ConnectionHandler.Builder<Message, Message> builder, GameController controller){
        this.server = server;
        this.connectionHandler = builder.build(this::handleMessage);
        this.gameController = controller;
    }

    public void handleMessage(Message message){
        message.accept(this);
    }


    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public void handle(ActivateLeaderCard event) {
        active = true;

        if(game == null){

        }

        gameController.handle(event);

    }

    @Override
    public void handle(ThrowCard event) {
        active = true;

        if(game == null){

        }

        gameController.handle(event);
    }


    @Override
    public void handle(Login event) {
        active = true;
        server.login(this, event.getUsername());
    }

    @Override
    public void handle(GameSize event) {
        active = true;
        server.createGame(this, event.getSize());
    }





    @Override
    public void update(Message event) {
        connectionHandler.send(event);
    }
    
}
