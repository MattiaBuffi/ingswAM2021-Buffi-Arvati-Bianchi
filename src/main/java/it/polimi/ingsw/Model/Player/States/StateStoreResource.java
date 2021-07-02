package it.polimi.ingsw.Model.Player.States;

import it.polimi.ingsw.Model.GameHandler;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.PlayerState;
import it.polimi.ingsw.Model.Player.ResourceMarket.ResourceMarketHandler;
import it.polimi.ingsw.Model.ResourceStorage.PlayerStorage;
import it.polimi.ingsw.Model.ResourceStorage.ResourceStorage;
//import it.polimi.ingsw.Model.TurnHandler;

/**
 * State in which the player can store or discard the resources taken
 */
public class StateStoreResource extends PlayerState {

    private static StateStoreResource instance;

    private StateStoreResource(Name stateName){
        super(stateName);
    }


    public boolean move(PlayerStorage storage, int originId, int destinationId){
        return storage.move(originId, destinationId);
    }

    @Override
    protected boolean moveResources(Player context, int originId, int destinationId) {
        return move(context.getResourceStorage(), originId, destinationId);
    }



    public boolean storeResource(ResourceMarketHandler marketHandler, PlayerStorage playerStorage, Marble.Color color, int storageId ){

        if(!marketHandler.getColors().contains(color)){   //check that the buffer contains the specified color
            return false;
        }

        if(!playerStorage.store(color, storageId)){
            return false;
        }

        marketHandler.take(color);  //guaranteed success since already checked the color presence

        return true;
    }

    @Override
    protected boolean storeResource(Player context, Marble.Color color, int storageId) {
        return storeResource(context.getResourceMarketBuffer(), context.getResourceStorage(), color, storageId);
    }



    public void endTurn(ResourceMarketHandler marketHandler, PlayerState.Context context, GameHandler turnHandler){
        marketHandler.empty();
        context.setState(StateWait.get());
        turnHandler.endTurn();
    }

    @Override
    protected boolean endTurn(Player context){
        endTurn(context.getResourceMarketBuffer(), context, context.getGameHandler());
        return true;
    }



    public static void setState(Player context){
        context.setState(get());

    }

    public static StateStoreResource get(){
        if(instance == null){
            instance = new StateStoreResource(Name.STORE_RESOURCE);
        }
        return instance;
    }

}