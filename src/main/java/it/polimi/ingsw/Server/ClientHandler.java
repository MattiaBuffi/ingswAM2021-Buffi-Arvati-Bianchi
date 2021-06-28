package it.polimi.ingsw.Server;

import it.polimi.ingsw.Controller.GameController;
import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.ClientMessages.*;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.ModelEventHandler;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Network.ConnectionHandler;

import java.io.IOException;


public class ClientHandler extends Client implements ClientEventHandler, ConnectionHandler.ShutdownHandler {



    private Server server;

    private ConnectionHandler<Message<ClientEventHandler>, Message<ModelEventHandler>> connectionHandler;


    private GameController gameController;


    

    public ClientHandler(Server server, ConnectionHandler.Builder<Message<ClientEventHandler>, Message<ModelEventHandler>> builder) throws IOException {
        this.server = server;
        this.connectionHandler = builder.build(5000, this::handleMessage, this);

    }

    public void handleMessage(Message<ClientEventHandler> message){
        message.accept(this);
    }


    @Override
    public void setGame(Game game) {
        super.setGame(game);
        gameController = new GameController(game, username);
    }

    @Override
    public void handle(ActivateLeaderCard event) {
        this.active = true;

        if (game == null){
            return;
        }

        synchronized (game){
            gameController.handle(event);
        }

    }

    @Override
    public void handle(DiscardLeaderCard event) {
        this.active = true;

        if (game == null){
            return;
        }

        synchronized (game){
            gameController.handle(event);
        }

    }

    @Override
    public void handle(BuyDevelopmentCard event) {
        this.active = true;

        if (game == null){
            return;
        }

        synchronized (game){
            gameController.handle(event);
        }
    }

    @Override
    public void handle(TakeResources event) {
        this.active = true;

        if (game == null){
            return;
        }

        synchronized (game){
            gameController.handle(event);
        }

    }

    @Override
    public void handle(EndTurn event) {
        this.active = true;

        if (game == null){
            return;
        }

        synchronized (game){
            gameController.handle(event);
        }

    }

    @Override
    public void handle(BasicProduction event) {
        this.active = true;

        if (game == null){
            return;
        }

        synchronized (game){
            gameController.handle(event);
        }

    }

    @Override
    public void handle(CardProduction event) {
        this.active = true;

        if (game == null){
            return;
        }

        synchronized (game){
            gameController.handle(event);
        }

    }

    @Override
    public void handle(LeaderCardProduction event) {
        this.active = true;

        if (game == null){
            return;
        }

        synchronized (game){
            gameController.handle(event);
        }

    }



    @Override
    public void handle(Login event) {
        this.active = true;
        server.login(this, event.getUsername());
    }

    @Override
    public void handle(GameSize event) {
        this.active = true;
        server.createGame(this, event.getSize());
    }

    @Override
    public void handle(Ping event) {

    }

    @Override
    public void handle(MoveResources event) {

    }

    @Override
    public void handle(DepositResource event) {

    }

    @Override
    public void update(Message<ModelEventHandler> event) {
        this.send(event);
    }





    @Override
    public void disconnect() {
        connectionHandler.stop();
        if(game != null){
            //disconnect from game
        }
    }

    @Override
    public void send(Message event) {
        connectionHandler.send(event);
    }

    @Override
    public void close(ConnectionHandler connection) {
        System.out.println("closing connection");
        this.active = false;
        connection.stop();
        server.removeClient(this);
    }

}
