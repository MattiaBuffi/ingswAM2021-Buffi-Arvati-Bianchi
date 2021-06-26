package it.polimi.ingsw.Client.CLI.Pages;

import it.polimi.ingsw.Client.CLI.CLI_Controller;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ModelEventHandler;

import java.io.FileNotFoundException;

public class QuitPage extends ModelEventHandler.Default {

    ViewBackEnd backEnd;

    public void QuitPageView(ViewBackEnd backEnd) {
        this.backEnd = backEnd;
        this.backEnd.setEventHandler(this);
        CLI_Controller.cls();
        char[] charArray = new char[0];
        try {
            charArray = CLI_Controller.readSchematics(7);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(charArray);
    }

    @Override
    public void invalidMessage() {

    }
}
