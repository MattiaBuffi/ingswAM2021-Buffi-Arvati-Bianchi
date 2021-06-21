package it.polimi.ingsw.Server;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.ModelEventHandler;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Utils.Observer;

public abstract class Client implements Observer<Message<ModelEventHandler>> {

    protected boolean active;

    protected boolean SearchingGame;

    protected String username;

    protected Game game;


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }



    public boolean isSearchingGame() {
        return SearchingGame;
    }

    public void setSearchingGame(boolean searchingGame) {
        SearchingGame = searchingGame;
    }



    public Game getGame() {
        return game;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public void setGame(Game game) {
        this.game = game;
    }



    public abstract void disconnect();

    public abstract void send(Message message);



}
