package it.polimi.ingsw.Client.CLI.Pages;

import it.polimi.ingsw.Client.CLI.CLI_Controller;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ModelEventHandler;

public class EndGamePage extends ModelEventHandler.Default {

    ViewBackEnd backEnd;
    int namePosition=2444;

    public void EndGameView(ViewBackEnd backEnd) {
        this.backEnd = backEnd;
        this.backEnd.setEventHandler(this);
        CLI_Controller.cls();
        char[] charArray = CLI_Controller.readSchematics(15);
        String winnerName = "";
        System.arraycopy(winnerName.toCharArray(),0, charArray, namePosition, winnerName.toCharArray().length);
        System.out.println(charArray);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CLI_Controller.quitPage.QuitPageView(this.backEnd);
    }

    @Override
    public void invalidMessage() {

    }
}