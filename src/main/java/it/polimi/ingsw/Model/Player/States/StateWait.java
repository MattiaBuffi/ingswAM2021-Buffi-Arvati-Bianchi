package it.polimi.ingsw.Model.Player.States;

import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.PlayerState;

public class StateWait extends PlayerState {

    private static StateWait instance;

    private StateWait(Name stateName){
        super(stateName);
    }

    @Override
    protected boolean setActive(Player context) {
        if(!context.isReady()){
            return false;
        }

        context.setState(StateNewTurn.get());

        return true;
    }



    public static void setState(Player context){
        context.setState(get());

    }

    public static StateWait get(){
        if(instance == null){
            instance = new StateWait(Name.WAIT);
        }
        return instance;
    }

}