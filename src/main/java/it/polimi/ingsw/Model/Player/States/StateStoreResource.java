package it.polimi.ingsw.Model.Player.States;

import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.PlayerState;
import it.polimi.ingsw.Model.Player.ResourceMarket.ResourceMarketHandler;

public class StateStoreResource extends PlayerState {

    private static StateStoreResource instance;

    private StateStoreResource(Name stateName){
        super(stateName);
    }


    @Override
    protected boolean moveResources(Player context, int originId, int destinationId) {
        return context.getResourceStorage().move(originId, destinationId);
    }

    @Override
    protected boolean storeResource(Player context, Marble.Color color, int storageId) {
        ResourceMarketHandler handler = context.getResourceMarketBuffer();

        if(!handler.getColors().contains(color)){   //check that the buffer contains the specified color
            return false;
        }

        if(!context.getResourceStorage().store(color, storageId)){
            return false;
        }

        handler.take(color);  //guaranteed success since already checked the color presence

        return true;

    }

    @Override
    protected boolean endTurn(Player context){
        context.getResourceMarketBuffer().empty();
        context.setState(StateWait.get());
        context.getTurnHandler().endTurn();
        return true;
    }


    public static StateStoreResource get(){
        if(instance == null){
            instance = new StateStoreResource(Name.STORE_RESOURCE);
        }
        return instance;
    }

}