package it.polimi.ingsw.Model.Player.States;

import it.polimi.ingsw.Model.Marble.*;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.PlayerState;

import java.util.List;

public class StateBuyResource extends PlayerState {

    private static StateBuyResource instance;

    private StateBuyResource(Name stateName){
        super(stateName);
    }


    @Override
    protected boolean buyResources(Player context, List<Marble> resources) {

        for (Marble m: resources){
            m.accept(context.getResourceMarketBuffer());
        }

        context.setState(StateStoreResource.get());
        return true;

    }


    public static StateBuyResource get(){
        if(instance == null){
            instance = new StateBuyResource(Name.BUY_RESOURCE);
        }
        return instance;
    }

}