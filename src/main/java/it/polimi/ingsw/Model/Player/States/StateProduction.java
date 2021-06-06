package it.polimi.ingsw.Model.Player.States;

import it.polimi.ingsw.Model.CardStorage.Selection.ProductionSelector;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.PlayerState;
import it.polimi.ingsw.Model.Player.ProductionHandler;



public class StateProduction extends PlayerState {


    private static StateProduction instance;

    private StateProduction(Name stateName){
        super(stateName);
    }


    @Override
    protected boolean production(Player context, ProductionSelector selector) {

        ProductionHandler handler = context.getProductionHandler();
        return handler.produce(selector);

    }



    @Override
    protected boolean endTurn(Player context){

        context.getProductionHandler().empty();
        context.setState(StateWait.get());
        context.getTurnHandler().endTurn();
        return true;

    }










    public static StateProduction get(){
        if(instance == null){
            instance = new StateProduction(Name.PRODUCTION);
        }
        return instance;
    }


}