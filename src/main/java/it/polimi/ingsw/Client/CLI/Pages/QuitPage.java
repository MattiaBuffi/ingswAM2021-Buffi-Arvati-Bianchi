package it.polimi.ingsw.Client.CLI.Pages;

import it.polimi.ingsw.Client.CLI.Cli;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ModelEventHandler;

public class QuitPage extends ModelEventHandler.Default {

    ViewBackEnd backEnd;

    public void QuitPageView(ViewBackEnd backEnd) {
        this.backEnd = backEnd;
        this.backEnd.setEventHandler(this);
        Cli.cls();
        char[] charArray = Cli.readSchematics(7);
        System.out.println(charArray);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    @Override
    public void invalidMessage() {

    }
}
