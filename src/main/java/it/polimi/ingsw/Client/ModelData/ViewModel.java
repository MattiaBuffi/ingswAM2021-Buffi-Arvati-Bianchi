package it.polimi.ingsw.Client.ModelData;


import java.util.List;

public class ViewModel {

    private List<Player> players;
    private Player current;

    public List<Player> getPlayers() {
        return players;
    }

    public Player getCurrent() {
        return current;
    }

    public void setCurrent(Player current) {
        this.current = current;
    }


}
