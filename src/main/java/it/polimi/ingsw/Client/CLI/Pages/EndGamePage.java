package it.polimi.ingsw.Client.CLI.Pages;

import it.polimi.ingsw.Client.CLI.Cli;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ModelEventHandler;
import it.polimi.ingsw.Message.Model.EndGame;

public class EndGamePage extends ModelEventHandler.Default {

    ViewBackEnd backEnd;
    int namePosition=2444;

    public void EndGameView(ViewBackEnd backEnd, EndGame event) {
        this.backEnd = backEnd;
        this.backEnd.setEventHandler(this);
        Cli.cls();
        char[] charArray;
        if(event.getUsername().equals("cpu") || !event.getUsername().equals(this.backEnd.getMyUsername())){
            charArray = Cli.readSchematics(16);
        }else{
            charArray = Cli.readSchematics(15);
        }
        System.out.println(charArray);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Cli.quitPage.QuitPageView(this.backEnd);
    }

    @Override
    public void invalidMessage() {

    }
}