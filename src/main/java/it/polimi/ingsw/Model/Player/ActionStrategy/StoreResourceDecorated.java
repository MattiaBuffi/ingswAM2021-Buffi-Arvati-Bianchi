package it.polimi.ingsw.Model.Player.ActionStrategy;

import it.polimi.ingsw.Model.Marble.MarbleColor;
import it.polimi.ingsw.Model.Player.Player;

public class StoreResourceDecorated implements StoreResourceStrategy {

    private StoreResourceStrategy strategy;
    private MarbleColor color;

    public StoreResourceDecorated(StoreResourceStrategy strategy, MarbleColor color) {
        this.strategy = strategy;
        this.color = color;
    }

    @Override
    public boolean execute(Player player, MarbleColor color, String storageId) {
        if(strategy.execute(player, color, storageId)){
            return true;
        }
        if(this.color != color){
            return false;
        }
        if(!player.marketBuffer.remove(MarbleColor.WHITE) ){
            return false;
        }
        return player.resourceStorage.store(color, storageId);
    }

}

