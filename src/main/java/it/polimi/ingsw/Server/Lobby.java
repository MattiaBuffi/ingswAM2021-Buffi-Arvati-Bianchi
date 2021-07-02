package it.polimi.ingsw.Server;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.Model.ErrorUpdate;
import it.polimi.ingsw.Message.Model.GameSizeRequest;
import it.polimi.ingsw.Message.Model.WaitingPlayersUpdate;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Identifies the Lobby
 */
public class Lobby {

    private List<Client> clientWaitingQueue;
    private int gameSize;

    public Lobby(){
        this.clientWaitingQueue = new ArrayList<>();
        this.gameSize = Integer.MAX_VALUE;
    }


    /**
     * Remove a client from the Waiting Queue
     * @param client client to remove
     */
    public void removeClient(Client client){
        clientWaitingQueue.remove(client);
        sendToAllClients(clientWaitingQueue, new WaitingPlayersUpdate(clientWaitingQueue.size()));

        if(clientWaitingQueue.size() == 0){
            gameSize = Integer.MAX_VALUE;
            return;
        }

        if(clientWaitingQueue.size() == 1){
            gameSize = Integer.MAX_VALUE;
        }

        if(gameSize == Integer.MAX_VALUE){
            clientWaitingQueue.get(0).send(new GameSizeRequest(clientWaitingQueue.size()));
        }

    }

    /**
     * Add the client to the Waiting Queue
     * @param client client that wants to join
     */
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
            gameSize = Integer.MAX_VALUE;
        }


    }


    /**
     * Set the Size of the lobby
     * @param client client that set the game size
     * @param size size of the Lobby
     */
    public void setGameSize(Client client, int size){

        if (gameSize != Integer.MAX_VALUE){
            client.send(new ErrorUpdate("game size already set"));
            return;
        }

        if(clientWaitingQueue.get(0) != client){
            client.send(new ErrorUpdate("action not allowed"));
            return;
        }

        gameSize = size;

        if(startGame(gameSize, clientWaitingQueue)){
            gameSize = Integer.MAX_VALUE;
        }


    }


    /**
     * Send a message to all clients
     * @param clients List of all clients in the lobby
     * @param message Message to send
     */
    public void sendToAllClients(List<Client> clients, Message message){
        for (Client c: clients){
            c.send(message);
        }
    }

    /**
     * Check if the game can start
     * @param size size of the lobby
     * @param clients List of all clients in the lobby
     * @return true when enough clients are connected to the same lobby
     */
    public boolean canStartGame(int size, List<Client> clients){
        if(clients.size() < size){
            sendToAllClients(clients, new WaitingPlayersUpdate(clients.size()));
            return false;
        }
        return true;
    }

    /**
     * Start the Game
     * @param size size of the lobby
     * @param clients List of all clients in the lobby
     * @return true after starting the game
     */
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
