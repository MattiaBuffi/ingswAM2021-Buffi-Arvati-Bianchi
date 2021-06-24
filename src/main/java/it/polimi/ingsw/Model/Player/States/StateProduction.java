package it.polimi.ingsw.Model.Player.States;

import it.polimi.ingsw.Model.CardStorage.Selection.ProductionSelector;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.PlayerState;
import it.polimi.ingsw.Model.Player.ProductionHandler;
import it.polimi.ingsw.Model.TurnHandler;


public class StateProduction extends PlayerState {


    private static StateProduction instance;

    private StateProduction(Name stateName){
        super(stateName);
    }


    public boolean production(ProductionHandler productionHandler, ProductionSelector selector){
        return productionHandler.produce(selector);
    }



    @Override
    protected boolean production(Player context, ProductionSelector selector) {

        return production(context.getProductionHandler(), selector);

    }



    public void endTurn(ProductionHandler handler, PlayerState.Context context, TurnHandler turnHandler){
        handler.empty();
        context.setState(StateWait.get());
        turnHandler.endTurn();
    }


    @Override
    protected boolean endTurn(Player context){
        endTurn(context.getProductionHandler(),context, context.getTurnHandler());
        return true;
    }









    public static void setState(Player context){
        context.setState(get());

    }


    public static StateProduction get(){
        if(instance == null){
            instance = new StateProduction(Name.PRODUCTION);
        }
        return instance;
    }


}