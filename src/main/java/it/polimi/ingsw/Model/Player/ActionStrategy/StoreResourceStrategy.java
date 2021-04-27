package it.polimi.ingsw.Model.Player.ActionStrategy;

import it.polimi.ingsw.Model.Marble.MarbleColor;
import it.polimi.ingsw.Model.Player.Player;

public interface StoreResourceStrategy {
    boolean execute(Player player, MarbleColor color, String storageId);
}
