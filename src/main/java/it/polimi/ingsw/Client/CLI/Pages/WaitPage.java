package it.polimi.ingsw.Client.CLI.Pages;

import it.polimi.ingsw.Client.CLI.CLI_Controller;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.Model.*;
import it.polimi.ingsw.Message.ModelEventHandler;

public class WaitPage extends ModelEventHandler.Default {

    ViewBackEnd backEnd;
    char[] waitPage;

    public void WaitPageView(ViewBackEnd backEnd)  {
        this.backEnd = backEnd;
        this.backEnd.setEventHandler(this);
        CLI_Controller.cls();
        waitPage = CLI_Controller.readSchematics(9);
        System.out.println(waitPage);
    }

    @Override
    public void invalidMessage() {
        System.out.println("invalid action");
    }

    @Override
    public void handle(GameSizeRequest event) {
        CLI_Controller.selectNumber.SelectNumberPlayerPageView(this.backEnd);
    }

    @Override
    public void handle(WaitingPlayersUpdate event){
        String playerWaiting =  "player currently joined: " + event.getLobbyCurrentSize();
        CLI_Controller.cls();
        System.arraycopy(playerWaiting.toCharArray(),0, waitPage, 3910, playerWaiting.toCharArray().length);
        System.out.println(waitPage);
    }

    @Override
    public void handle(ErrorUpdate event) {
        CLI_Controller.showError(event);
        WaitPageView(this.backEnd);
    }

    @Override
    public void handle(ModelUpdate event) {



    }

    @Override
    public void handle(AvailableLeaderCard event) {
        CLI_Controller.selectionPage.SelectionPageView(this.backEnd);
    }


}
