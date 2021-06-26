package it.polimi.ingsw.Client.CLI.Pages;

import it.polimi.ingsw.Client.CLI.CLI_Controller;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ModelEventHandler;

import java.io.FileNotFoundException;

public class LoadingPage extends ModelEventHandler.Default{

    ViewBackEnd backEnd;

    public void LoadingPageView(ViewBackEnd backEnd) throws InterruptedException {

        this.backEnd = backEnd;
        this.backEnd.setEventHandler(this);
        CLI_Controller.cls();
        char[] charArray = new char[0];
        try {
            charArray = CLI_Controller.readSchematics(1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(charArray);
        Thread.sleep(100);
        CLI_Controller.username.UsernamePageView(backEnd);
    }


    @Override
    public void invalidMessage() {

    }
}
