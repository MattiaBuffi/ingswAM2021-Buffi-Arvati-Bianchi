package it.polimi.ingsw.Client.CLI.Pages;

import it.polimi.ingsw.Client.CLI.CLI_Controller;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ClientMessages.Login;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.Model.ErrorUpdate;
import it.polimi.ingsw.Message.Model.ModelUpdate;
import it.polimi.ingsw.Message.Model.UsernameSelected;
import it.polimi.ingsw.Message.ModelEventHandler;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class UsernamePage extends ModelEventHandler.Default  {

    private ViewBackEnd backEnd;
    private static final int FirstCellPosition = 2671;


    public void UsernamePageView(ViewBackEnd backEnd){
        this.backEnd = backEnd;
        this.backEnd.setEventHandler(this);
        CLI_Controller.cls();
        Scanner input = new Scanner(System.in);
        char[] charArray = new char[0];
        try {
            charArray = CLI_Controller.readSchematics(8);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(charArray);

        System.out.println("Insert Username (max 12 characters, only alpha-numeric): ");
        String name = input.nextLine();

        System.arraycopy(name.toCharArray(), 0, charArray, FirstCellPosition, name.toCharArray().length);

        this.backEnd.notify(new Login(name));
    }

    @Override
    public void handle(UsernameSelected event){
        CLI_Controller.waitPage.WaitPageView(this.backEnd);
    }

    @Override
    public void invalidMessage() {
        System.out.println("invalid Message");
    }

    @Override
    public void handle(ErrorUpdate event) {
        CLI_Controller.showError(event);
        UsernamePageView(this.backEnd);
    }

}
