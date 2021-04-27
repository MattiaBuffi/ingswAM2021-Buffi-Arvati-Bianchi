package it.polimi.ingsw.Model.LeaderCard;

import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.ResourceStorage.Shelf.ShelfLeader;
import it.polimi.ingsw.Model.ResourceStorage.Shelf.Shelves;
import it.polimi.ingsw.Model.ResourceStorage.Shelf.ShelvesExtra;

public class ActivateStorage implements ActivationStrategy {

    private ShelfLeader shelf;

    public ActivateStorage(ShelfLeader shelf){
        this.shelf = shelf;
    }

    @Override
    public void execute(Player player) {
        player.resourceStorage.setShelves(new ShelvesExtra(player.resourceStorage.getShelves(), shelf));
    }



}
