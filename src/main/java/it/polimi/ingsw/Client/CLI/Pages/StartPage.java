package it.polimi.ingsw.Client.CLI.Pages;

import it.polimi.ingsw.Client.CLI.CLI_Controller;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.Model.ErrorUpdate;
import it.polimi.ingsw.Message.ModelEventHandler;
import java.util.Scanner;

public class StartPage extends ModelEventHandler.Default{

    private static final int FirstCellPosition = 2671;
    private static final int SecondCellPosition = 2704;
    private ViewBackEnd backEnd;


    public void StartPageView(ViewBackEnd backEnd){
        this.backEnd = backEnd;
        this.backEnd.setEventHandler(this);
        CLI_Controller.cls();
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

        if(this.backEnd.connectToServer(server, Integer.parseInt(port))){
            try {
                CLI_Controller.loading.LoadingPageView(backEnd);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            CLI_Controller.start.StartPageView(backEnd);
        }
    }

    @Override
    public void invalidMessage() {

    }

    @Override
    public void handle(ErrorUpdate event) {
        CLI_Controller.showError(event);
        StartPageView(this.backEnd);
    }

}
