package it.polimi.ingsw.Client.CLI.Pages;

import it.polimi.ingsw.Client.CLI.CLI_Controller;
import it.polimi.ingsw.Client.ViewBackEnd;

import java.io.FileNotFoundException;

public class QuitPage {

    ViewBackEnd backEnd;

    public QuitPage(ViewBackEnd backEnd) {
        this.backEnd = backEnd;
    }

    public void QuitPageView() throws FileNotFoundException {
        char[] charArray = CLI_Controller.readSchematics(7);
        System.out.println(charArray);
        CLI_Controller.scene = "exit";
    }
}
