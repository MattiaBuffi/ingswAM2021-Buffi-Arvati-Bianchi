package it.polimi.ingsw.Client.CLI.Pages;

import it.polimi.ingsw.Client.CLI.Cli;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ClientMessages.GameSize;
import it.polimi.ingsw.Message.ModelEventHandler;

public class SelectNumberPlayerPage extends ModelEventHandler.Default {

    ViewBackEnd backEnd;
    private static final int FirstCellPosition = 2671;

    public void SelectNumberPlayerPageView(ViewBackEnd backEnd) {
        this.backEnd = backEnd;
        this.backEnd.setEventHandler(this);
        Cli.cls();
        char[] charArray = Cli.readSchematics(6);
        System.out.println(charArray);
        System.out.println("Insert Number of Player (1-4): ");



        Cli.setReadHandler(
                (line)->{
                    try{
                        if (Integer.parseInt(line) > 4 || Integer.parseInt(line) < 1) {
                            System.out.println("Insert a Valid Number please: ");
                            return;
                        }
                    }catch (NumberFormatException e){
                        Cli.showUpdateMessage("Wrong Input");
                        SelectNumberPlayerPageView(this.backEnd);
                        return;
                    }
                    char[] numArray = line.toCharArray();
                    System.arraycopy(numArray, 0, charArray, FirstCellPosition, numArray.length);
                    System.out.println(charArray);
                    try{
                        GameSize messageGameSize = new GameSize(Integer.parseInt(line));
                        this.backEnd.notify(messageGameSize);
                        Cli.waitPage.WaitPageView(this.backEnd);
                    }catch (NumberFormatException e){
                        Cli.showUpdateMessage("Wrong Input");
                        SelectNumberPlayerPageView(this.backEnd);
                    }
                }
        );
    }


    @Override
    public void invalidMessage() {

    }

}
