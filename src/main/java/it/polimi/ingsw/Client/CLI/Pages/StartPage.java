package it.polimi.ingsw.Client.CLI.Pages;

import it.polimi.ingsw.Client.CLI.CLI_Controller;
import it.polimi.ingsw.Client.ViewBackEnd;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class StartPage {

    private static final int FirstCellPosition = 2671;
    private static final int SecondCellPosition = 2704;
    private ViewBackEnd backEnd;


    public void StartPageView() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        char[] charArray = CLI_Controller.readSchematics(0);
        System.out.println(charArray);
        System.out.println("Insert Server IP: ");
        String server = input.nextLine();
        System.arraycopy(server.toCharArray(), 0, charArray, FirstCellPosition, server.toCharArray().length);
        System.out.println(charArray);
        System.out.println("Insert Port Number: ");
        String port = input.nextLine();
        System.arraycopy(port.toCharArray(), 0, charArray, SecondCellPosition, port.toCharArray().length);
        System.out.println(charArray);
        backEnd.connectToServer(server, Integer.parseInt(port));

        CLI_Controller.scene = "LOADING";
    }

    public StartPage(ViewBackEnd backEnd) {
        this.backEnd = backEnd;
    }
}
