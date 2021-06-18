package it.polimi.ingsw.Server;

import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player.User;
import it.polimi.ingsw.Server.Client.ClientHandler;

import java.util.ArrayList;
import java.util.List;

public class Lobby {

    private List<ClientHandler> clientWaitingQueue;
    private int gameSize;

    public Lobby(){
        this.clientWaitingQueue = new ArrayList<>();
        this.gameSize = 0;
    }

    public synchronized void join(ClientHandler client){
        clientWaitingQueue.add(client);

        if(clientWaitingQueue.size() == 1){
            //client.send()
            return;
        }

        if(clientWaitingQueue.size() < gameSize){
            //wait
            return;
        }

        startGame(gameSize, clientWaitingQueue);
    }

    public synchronized void setGameSize(ClientHandler client, int size){

        if (gameSize != 0){
            //size alreadySet
            return;
        }

        if(clientWaitingQueue.get(0) != client){
            return;
        }

        if(clientWaitingQueue.size() < size){
            return;
        }

        startGame(gameSize, clientWaitingQueue);

    }



    private void startGame(int size, List<ClientHandler> clients){
        List<User> players = new ArrayList<>();
        this.gameSize = 0;

        for (int i = 0; i <size ; i++) {
            User user = new User(clients.get(i).getUsername());
            user.addObserver(clients.get(i));
            players.add(user);
        }

        Game game = new Game(players);

        for (int i = 0; i <size ; i++) {
            clients.get(i).setGame(game);
        }

        clients.removeAll(clients.subList(0, size));

        if(clients.size() == 0){
            return;
        }

        //clients.get(0).notify(); setSize

    }




}
