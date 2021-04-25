package it.polimi.ingsw.Model.LeaderCard;

import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.ResourceStorage.ShelfLeader;

public class LeaderCardStorage extends LeaderCard {

    private ShelfLeader shelf;

    public LeaderCardStorage(ShelfLeader shelf){
        this.shelf = shelf;
    }

    @Override
    public void activate(Player player) {

    }



}
