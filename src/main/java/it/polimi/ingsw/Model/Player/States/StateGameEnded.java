package it.polimi.ingsw.Model.Player.States;

import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.PlayerState;


/**
 * State in which the game is ended
 */
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


    public static void setState(Player context){
        context.setState(get());

    }

    public static StateGameEnded get(){
        if(instance == null){
            instance = new StateGameEnded(Name.END_GAME);
        }
        return instance;
    }




}
