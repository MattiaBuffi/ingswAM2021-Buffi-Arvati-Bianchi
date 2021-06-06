package it.polimi.ingsw.Model.Player.States;


import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.PlayerState;

public class StateNewGame extends PlayerState {


    private static StateNewGame instance;

    private StateNewGame(Name stateName){
        super(stateName);
    }


    @Override
    protected boolean discardLeader(Player context, String leaderId) {

        context.getLeaderCards().remove(leaderId);

        if(context.getLeaderCards().size() == 2){
            context.setState(StateWait.get());
        }

        return true;

    }




    public static StateNewGame get(){
        if(instance == null){
            instance = new StateNewGame(Name.NEW_GAME);
        }
        return instance;
    }
}