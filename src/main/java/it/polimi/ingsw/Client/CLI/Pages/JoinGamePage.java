package it.polimi.ingsw.Client.CLI.Pages;

import it.polimi.ingsw.Client.CLI.CLI_Controller;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ClientMessages.Login;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class JoinGamePage {

    ViewBackEnd backEnd;
    private static final int FirstCellPosition = 2671;

    public JoinGamePage(ViewBackEnd backEnd) {
        this.backEnd = backEnd;
    }

    public void JoinGamePageView() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        char[] charArray = CLI_Controller.readSchematics(6);
        System.out.println(charArray);
        System.out.println("Insert Username: ");
        String name = input.nextLine();
        System.arraycopy(name.toCharArray(), 0, charArray, FirstCellPosition, name.toCharArray().length);
        System.out.println(charArray);
        Login message = new Login(name);
        backEnd.notify(message);
        CLI_Controller.scene = "WAIT";
    }




}
