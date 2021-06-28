package it.polimi.ingsw.Model.Player.States;


import it.polimi.ingsw.Message.Model.AvailableLeaderCard;
import it.polimi.ingsw.Model.LeaderCard.LeaderCard;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.PlayerState;

public class StateSetupCard extends PlayerState {


    private static StateSetupCard instance;

    private StateSetupCard(Name stateName){
        super(stateName);
    }


    @Override
    protected boolean discardLeader(Player context, String leaderId) {

        for (LeaderCard lc: context.getLeaderCards()){
            if(lc.getId().equals(leaderId)){
                context.getLeaderCards().remove(lc);
                context.notifyUser(new AvailableLeaderCard(context.getLeaderCards()));
                break;
            }
        }

        if(context.getLeaderCards().size() == 2){
            context.setState(StateWait.get());
        }

        return true;

    }


    public static void setState(Player context){
        context.setState(get());
    }


    public static StateSetupCard get(){
        if(instance == null){
            instance = new StateSetupCard(Name.SETUP_CARD);
        }
        return instance;
    }
}