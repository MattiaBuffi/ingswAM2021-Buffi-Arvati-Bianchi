package it.polimi.ingsw.Client.CLI.Pages;

import it.polimi.ingsw.Client.CLI.CLI_Controller;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ClientMessages.GameSize;
import it.polimi.ingsw.Message.ModelEventHandler;
import java.util.Scanner;

public class SelectNumberPlayerPage extends ModelEventHandler.Default {

    ViewBackEnd backEnd;
    private static final int FirstCellPosition = 2671;

    public void SelectNumberPlayerPageView(ViewBackEnd backEnd) {
        this.backEnd = backEnd;
        this.backEnd.setEventHandler(this);
        CLI_Controller.cls();
        char[] charArray = CLI_Controller.readSchematics(6);
        System.out.println(charArray);
        System.out.println("Insert Number of Player (1-4): ");



        CLI_Controller.setReadHandler(
                (line)->{

                    if (Integer.parseInt(line) > 4 || Integer.parseInt(line) < 1) {
                        System.out.println("Insert a Valid Number please: ");
                        return;
                    }
                    char[] numArray = line.toCharArray();
                    System.arraycopy(numArray, 0, charArray, FirstCellPosition, numArray.length);
                    System.out.println(charArray);
                    GameSize messageGameSize = new GameSize(Integer.parseInt(line));
                    this.backEnd.notify(messageGameSize);

                    CLI_Controller.waitPage.WaitPageView(this.backEnd);
                }
        );
    }


    @Override
    public void invalidMessage() {

    }

}
