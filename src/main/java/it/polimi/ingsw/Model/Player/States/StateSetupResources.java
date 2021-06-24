package it.polimi.ingsw.Model.Player.States;

import it.polimi.ingsw.Message.Model.ResourceSetup;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.PlayerState;

public class StateSetupResources extends PlayerState {

    private static StateSetupResources instance;

    public StateSetupResources(Name name) {
        super(name);
    }


    @Override
    protected boolean storeResource(Player context, Marble.Color color, int storageId) {
        if(!context.getResourceStorage().store(color, storageId)){
            return false;
        }

        if(!completeSetup(context)){
            return false;
        }

        return true;

    }


    public static int availableResources(int playerPosition){
        switch (playerPosition){
            case 1:
                return 0;
            case 2:
                return 1;
            case 3:
                return 1;
            case 4:
                return 2;
            default:
                return 0;
        }
    }

    public static boolean completeSetup(Player context){

        if ( context.getResourceStorage().getResources().getSize() ==  availableResources(context.getPosition()) ){
            context.setReady(true);
            StateWait.setState(context);
            context.getGameHandler().startGame();
            return true;
        }

        return false;
    }

    public static void setState(Player context){
        if (!completeSetup(context)){
            context.setState(get());
            context.notifyUser(new ResourceSetup());
        }
    }


    public static StateSetupResources get(){
        if(instance == null){
            instance = new StateSetupResources(Name.SETUP_RESOURCES);
        }
        return instance;
    }





}
