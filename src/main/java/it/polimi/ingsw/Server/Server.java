package it.polimi.ingsw.Server;

public interface Server {


    void login(Client client, String username);

    void joinGame(Client client);

    void createGame(Client client, int size);

    void removeClient(Client client);

}
