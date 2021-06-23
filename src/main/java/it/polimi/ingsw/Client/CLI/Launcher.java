package it.polimi.ingsw.Client.CLI;

import it.polimi.ingsw.Client.App;
import it.polimi.ingsw.Client.ViewBackEnd;

import java.io.IOException;

public class Launcher {
    public static void main(ViewBackEnd backEnd) throws IOException {
        CLI_Controller cliController = new CLI_Controller(backEnd);
        cliController.CLIView();
    }
}
