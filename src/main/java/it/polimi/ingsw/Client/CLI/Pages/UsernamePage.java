package it.polimi.ingsw.Client.CLI.Pages;

import it.polimi.ingsw.Client.CLI.CLI_Controller;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ClientMessages.Login;
import it.polimi.ingsw.Message.Model.ErrorUpdate;
import it.polimi.ingsw.Message.Model.UsernameSelected;
import it.polimi.ingsw.Message.ModelEventHandler;
import java.util.Scanner;

public class UsernamePage extends ModelEventHandler.Default {

    private ViewBackEnd backEnd;
    public static String name;
    private static final int FirstCellPosition = 2671;


    public void UsernamePageView(ViewBackEnd backEnd) {
        this.backEnd = backEnd;
        this.backEnd.setEventHandler(this);
        CLI_Controller.cls();

        CLI_Controller.read(
            (input)->{
                char[] charArray = CLI_Controller.readSchematics(8);
                System.out.println(charArray);

                System.out.println("Insert Username (max 12 characters, only alpha-numeric): ");
                String newName = input.nextLine();
                this.backEnd.notify(new Login(newName));
                System.arraycopy(newName.toCharArray(), 0, charArray, FirstCellPosition, newName.toCharArray().length);
            }
        );

    }

    public static String getName() {
        return name;
    }

    @Override
    public void handle(UsernameSelected event) {
        name = event.getUsername();
        this.backEnd.setUsername(event.getUsername());
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