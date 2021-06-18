package it.polimi.ingsw.Server;

import it.polimi.ingsw.Network.ConnectionHandler;
import it.polimi.ingsw.Server.Client.ClientHandler;

import java.util.ArrayList;
import java.util.List;

public class Server {

    private List<ClientHandler> connectedClients;
    private final LoginHandler loginHandler;
    private final Lobby lobby;


    public Server() {
        lobby = new Lobby();
        loginHandler = new LoginHandler();
    }



    public void addClient(ConnectionHandler.Builder connectionBuilder){
        synchronized (connectedClients){

        }
    }

    public List<ClientHandler> getConnectedClients() {
        synchronized (connectedClients){
            return new ArrayList<>(connectedClients);
        }
    }


    public void login(ClientHandler client, String username){
        synchronized (loginHandler){
            if(!loginHandler.validUsername(username)){
                return;
            }
            if(loginHandler.usernameIsTaken(username)){
                return;
            }
            loginHandler.addUsername(username);
            client.setUsername(username);
            joinGame(client);
        }
    }

    public void joinGame(ClientHandler client){
        synchronized (lobby){
            lobby.join(client);
        }
    }

    public void createGame(ClientHandler client, int size){
        synchronized (lobby){
            lobby.setGameSize(client, size);
        }
    }


    public void disconnect(ClientHandler client){

    }



}
