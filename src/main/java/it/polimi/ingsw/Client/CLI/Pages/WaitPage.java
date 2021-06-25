package it.polimi.ingsw.Client.CLI.Pages;

import it.polimi.ingsw.Client.CLI.CLI_Controller;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.Model.ErrorUpdate;
import it.polimi.ingsw.Message.Model.GameSizeRequest;
import it.polimi.ingsw.Message.Model.WaitingPlayersUpdate;
import it.polimi.ingsw.Message.ModelEventHandler;

import java.io.FileNotFoundException;

public class WaitPage extends ModelEventHandler.Default {

    ViewBackEnd backEnd;
    char[] waitPage;

    public void WaitPageView(ViewBackEnd backEnd)  {
        this.backEnd = backEnd;
        this.backEnd.setEventHandler(this);
        CLI_Controller.cls();
        try {
            waitPage = CLI_Controller.readSchematics(9);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
        System.arraycopy(playerWaiting.toCharArray(),0, waitPage, 3910, playerWaiting.toCharArray().length);
        System.out.println(waitPage);
        //CLI_Controller.homePage.HomePageView(this.backEnd);
    }

    @Override
    public void handle(ErrorUpdate event) {
        CLI_Controller.cls();
        System.out.println(event.getErrorMessage());
        System.out.println("Here is a free time travel, enjoy it");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WaitPageView(this.backEnd);
    }


}
