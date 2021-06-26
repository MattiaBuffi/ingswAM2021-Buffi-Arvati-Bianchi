package it.polimi.ingsw.Client.CLI.Pages;

import it.polimi.ingsw.Client.CLI.CLI_Controller;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ClientMessages.GameSize;
import it.polimi.ingsw.Message.Model.ErrorUpdate;
import it.polimi.ingsw.Message.ModelEventHandler;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class SelectNumberPlayerPage extends ModelEventHandler.Default {

    ViewBackEnd backEnd;
    private static final int FirstCellPosition = 2671;

    public void SelectNumberPlayerPageView(ViewBackEnd backEnd) {
        this.backEnd = backEnd;
        this.backEnd.setEventHandler(this);
        CLI_Controller.cls();
        Scanner input = new Scanner(System.in);
        char[] charArray = new char[0];

        try {
            charArray = CLI_Controller.readSchematics(6);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(charArray);
        System.out.println("Insert Number of Player (1-4): ");
        String playerNumber = input.nextLine();
        while (Integer.parseInt(playerNumber) > 4 || Integer.parseInt(playerNumber) < 1) {
            System.out.println("Insert a Valid Number please: ");
            playerNumber = input.nextLine();
        }
        char[] numArray = playerNumber.toCharArray();
        System.arraycopy(numArray, 0, charArray, FirstCellPosition, numArray.length);
        System.out.println(charArray);
        GameSize messageGameSize = new GameSize(Integer.parseInt(playerNumber));
        this.backEnd.notify(messageGameSize);

        CLI_Controller.waitPage.WaitPageView(this.backEnd);
    }


    @Override
    public void invalidMessage() {

    }

}
