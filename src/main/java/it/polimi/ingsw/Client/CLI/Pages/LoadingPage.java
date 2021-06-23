package it.polimi.ingsw.Client.CLI.Pages;

import it.polimi.ingsw.Client.CLI.CLI_Controller;
import it.polimi.ingsw.Client.ViewBackEnd;

import java.io.FileNotFoundException;

public class LoadingPage {

    ViewBackEnd backEnd;

    public LoadingPage(ViewBackEnd backEnd) {
        this.backEnd = backEnd;
    }

    public void LoadingPageView() throws FileNotFoundException {
        char[] charArray = CLI_Controller.readSchematics(1);
        System.out.println(charArray);
        //clausola primo giocatore
        CLI_Controller.scene = "NEWGAME";
        //CLI_Controller.scene = "JOINGAME"

    }
}
