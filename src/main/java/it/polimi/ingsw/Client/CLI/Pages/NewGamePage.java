package it.polimi.ingsw.Client.CLI.Pages;

import it.polimi.ingsw.Client.CLI.CLI_Controller;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ClientMessages.GameSize;
import it.polimi.ingsw.Message.ClientMessages.Login;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class NewGamePage {

    ViewBackEnd backEnd;
    private static final int FirstCellPosition = 2671;
    private static final int SecondCellPosition = 2704;

    public NewGamePage(ViewBackEnd backEnd) {
        this.backEnd = backEnd;
    }

    public void NewGamePageView() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        char[] charArray = CLI_Controller.readSchematics(8);
        System.out.println(charArray);

        System.out.println("Insert Username: ");
        String name = input.nextLine();
        char[] nameArray = name.toCharArray();
        System.arraycopy(nameArray, 0, charArray, FirstCellPosition, nameArray.length);
        System.out.println(charArray);
        Login messageUsername = new Login(name);
        backEnd.notify(messageUsername);
        System.out.println("Insert Number of Player (1-4): ");
        String playerNumber = input.nextLine();
        while (Integer.parseInt(playerNumber) > 4 || Integer.parseInt(playerNumber) < 1) {
            System.out.println("Insert a Valid Number please: ");
            playerNumber = input.nextLine();
        }
        char[] numArray = playerNumber.toCharArray();
        System.arraycopy(numArray, 0, charArray, SecondCellPosition, numArray.length);
        System.out.println(charArray);
        GameSize messageGameSize = new GameSize(Integer.parseInt(playerNumber));
        backEnd.notify(messageGameSize);

        CLI_Controller.scene = "WAIT";

    }
}
