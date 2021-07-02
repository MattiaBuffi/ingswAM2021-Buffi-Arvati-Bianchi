package it.polimi.ingsw.Client.CLI.Pages;

import it.polimi.ingsw.Client.CLI.Cli;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.Model.*;
import it.polimi.ingsw.Message.ModelEventHandler;

public class WaitPage extends ModelEventHandler.Default {

    ViewBackEnd backEnd;
    char[] waitPage;

    public void WaitPageView(ViewBackEnd backEnd)  {
        this.backEnd = backEnd;
        this.backEnd.setEventHandler(this);
        Cli.cls();
        waitPage = Cli.readSchematics(9);
        System.out.println(waitPage);
    }

    @Override
    public void invalidMessage() {
    }

    @Override
    public void handle(GameSizeRequest event) {
        Cli.selectNumber.SelectNumberPlayerPageView(this.backEnd);
    }

    @Override
    public void handle(WaitingPlayersUpdate event){
        String playerWaiting =  "player currently joined: " + event.getLobbyCurrentSize();
        Cli.cls();
        System.arraycopy(playerWaiting.toCharArray(),0, waitPage, 3910, playerWaiting.toCharArray().length);
        System.out.println(waitPage);
    }

    @Override
    public void handle(ErrorUpdate event) {
        Cli.showError(event);
        WaitPageView(this.backEnd);
    }

    @Override
    public void handle(ModelUpdate event) {



    }

    @Override
    public void handle(AvailableLeaderCard event) {
        Cli.selectionPage.SelectionPageView(this.backEnd);
    }


}
