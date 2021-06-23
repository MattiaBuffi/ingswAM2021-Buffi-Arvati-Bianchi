package it.polimi.ingsw.Client.CLI.Pages;

import it.polimi.ingsw.Client.CLI.CLI_Controller;
import it.polimi.ingsw.Client.ViewBackEnd;

import java.io.FileNotFoundException;

public class WaitPage {

    ViewBackEnd backEnd;

    public WaitPage(ViewBackEnd backEnd) {
        this.backEnd = backEnd;
    }

    public void WaitPageView() throws FileNotFoundException {
        char[] charArray = CLI_Controller.readSchematics(9);
        System.out.println(charArray);
        CLI_Controller.scene = "SELECTION";
    }
}
