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
    private char[] charArray;

    private String ip;
    private String port;

    public void askIp(String line){
        this.ip = line;
        System.arraycopy(ip.toCharArray(), 0, charArray, FirstCellPosition, ip.toCharArray().length);
        System.out.println(charArray);
        System.out.println("Insert Port Number: ");
        CLI_Controller.setReadHandler(this::askPort);
    }

    public void askPort(String line){

        this.port = line;
        System.arraycopy(port.toCharArray(), 0, charArray, SecondCellPosition, port.toCharArray().length);
        System.out.println(charArray);

        if(this.backEnd.connectToServer(this.ip, Integer.parseInt(this.port))){
            CLI_Controller.username.UsernamePageView(this.backEnd);
        } else {
            CLI_Controller.start.StartPageView(this.backEnd);
        }

    }


    public void StartPageView(ViewBackEnd backEnd){
        this.backEnd = backEnd;
        this.backEnd.setEventHandler(this);
        this.charArray = CLI_Controller.readSchematics(0);
        this.ip = "0.0.0.0";
        this.port = "0000";

        CLI_Controller.cls();

        System.out.println(charArray);
        System.out.println("Insert Server IP: ");
        CLI_Controller.setReadHandler(this::askIp);


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
