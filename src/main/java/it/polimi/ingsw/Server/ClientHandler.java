package it.polimi.ingsw.Server;

import it.polimi.ingsw.Controller.GameController;
import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.ClientMessages.*;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.ModelEventHandler;
import it.polimi.ingsw.Network.ConnectionHandler;




public class ClientHandler extends Client implements ClientEventHandler {



    private Server server;

    private ConnectionHandler<Message<ClientEventHandler>, Message<ModelEventHandler>> connectionHandler;


    private GameController gameController;

    

    public ClientHandler(Server server, ConnectionHandler.Builder<Message<ClientEventHandler>, Message<ModelEventHandler>> builder, GameController controller){
        this.server = server;
        this.connectionHandler = builder.build(this::handleMessage);
        this.gameController = controller;
    }

    public void handleMessage(Message<ClientEventHandler> message){
        message.accept(this);
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
        System.out.println("Server client handler is sending a message");
        connectionHandler.send(event);
    }


}
