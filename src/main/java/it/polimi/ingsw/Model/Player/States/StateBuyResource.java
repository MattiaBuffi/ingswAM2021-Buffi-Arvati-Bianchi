package it.polimi.ingsw.Model.Player.States;

import it.polimi.ingsw.Model.Marble.*;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.PlayerState;
import it.polimi.ingsw.Model.Player.ResourceMarket.ResourceMarketHandler;

import java.util.List;

public class StateBuyResource extends PlayerState {

    private static StateBuyResource instance;

    private StateBuyResource(Name stateName){
        super(stateName);
    }



    public void buyResources(ResourceMarketHandler marketHandler, List<Marble> resources){
        for (Marble m: resources){
            m.accept(marketHandler);
        }
    }

    public void changeState(PlayerState.Context context){
        context.setState(StateStoreResource.get());
    }


    @Override
    protected boolean buyResources(Player context, List<Marble> resources) {

        buyResources(context.getResourceMarketBuffer(), resources);

        changeState(context);

        return true;

    }


    public static StateBuyResource get(){
        if(instance == null){
            instance = new StateBuyResource(Name.BUY_RESOURCE);
        }
        return instance;
    }

}