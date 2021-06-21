package it.polimi.ingsw.Server;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.Model.ErrorUpdate;
import it.polimi.ingsw.Message.Model.GameSizeRequest;
import it.polimi.ingsw.Message.Model.WaitingPlayersUpdate;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player.User;

import java.util.ArrayList;
import java.util.List;

public class Lobby {

    private List<Client> clientWaitingQueue;
    private int gameSize;

    public Lobby(){
        this.clientWaitingQueue = new ArrayList<>();
        this.gameSize = 0;
    }


    public void join(Client client){

        if (client.isSearchingGame()){
            client.send(new ErrorUpdate("already connected"));
            return;
        }

        clientWaitingQueue.add(client);
        client.setSearchingGame(true);

        if(clientWaitingQueue.size() == 1){
            client.send(new GameSizeRequest(clientWaitingQueue.size()));
            return;
        }

        if(startGame(gameSize, clientWaitingQueue)){
            gameSize = 0;
        }


    }


    public void setGameSize(Client client, int size){

        if (gameSize != 0){
            client.send(new ErrorUpdate("game size already set"));
            return;
        }

        if(clientWaitingQueue.get(0) != client){
            client.send(new ErrorUpdate("action not allowed"));
            return;
        }


        if(startGame(gameSize, clientWaitingQueue)){
            gameSize = 0;
        }


    }



    public void sendToAllClients(List<Client> clients, Message message){
        for (Client c: clients){
            c.send(message);
        }
    }


    public boolean canStartGame(int size, List<Client> clients){
        if(clients.size() < size){
            sendToAllClients(clients, new WaitingPlayersUpdate(clients.size()));
            return false;
        }
        return true;
    }


    public boolean startGame(int size, List<Client> clients){

        if (!canStartGame(size, clients)){
            return false;
        }

        List<User> players = new ArrayList<>();

        for (int i = 0; i <size ; i++) {
            User user = new User(clients.get(i).getUsername());
            user.addObserver(clients.get(i));
            players.add(user);
        }

        Game game = new Game(players);

        for (int i = 0; i < size ; i++) {
            Client client = clients.get(i);
            client.setGame(game);
            client.setSearchingGame(false);
        }

        clients.removeAll(clients.subList(0, size));

        if(clients.size() != 0){
            clients.get(0).send(new GameSizeRequest(clients.size()));
        }

        return true;

    }




}
