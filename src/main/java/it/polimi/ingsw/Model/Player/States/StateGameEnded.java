package it.polimi.ingsw.Model.Player.States;

import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.PlayerState;

public class StateGameEnded extends PlayerState {

    private static StateGameEnded instance;

    private StateGameEnded(Name name) {
        super(name);
    }

    @Override
    protected int getVictoryPoints(Player context) {

        int points = context.getResourceStorage().getResources().getSize()/5;

        return super.getVictoryPoints(context) + points;

    }

    public static StateGameEnded get(){
        if(instance == null){
            instance = new StateGameEnded(Name.END_GAME);
        }
        return instance;
    }



}