package it.polimi.ingsw.Client.CLI.Pages;
import it.polimi.ingsw.Client.CLI.CLI_Controller;
import it.polimi.ingsw.Client.ClientApp;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ClientMessages.DepositResource;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.Model.ActivePlayer;
import it.polimi.ingsw.Message.Model.ErrorUpdate;
import it.polimi.ingsw.Message.Model.ModelUpdate;
import it.polimi.ingsw.Message.Model.ResourceSetup;
import it.polimi.ingsw.Message.ModelEventHandler;
import it.polimi.ingsw.Model.Marble.Marble;

import java.util.Locale;
import java.util.Scanner;

public class LoadingPage extends ModelEventHandler.Default{

    ViewBackEnd backEnd;
    char[] loading;

    public void LoadingPageView(ViewBackEnd backEnd){

        this.backEnd = backEnd;
        this.backEnd.setEventHandler(this);
        CLI_Controller.cls();
        this.loading = CLI_Controller.readSchematics(1);
        System.out.println(this.loading);
    }

    @Override
    public void invalidMessage() {
    }



    @Override
    public void handle(ResourceSetup event){
            CLI_Controller.cls();

            System.out.println("You can get " + event.getAvailableResources() +
                    " initial Resources for free, please insert the color of the resource that you want to take " +
                    "[(P/G/B/Y) if more than 1 rss please insert the two colors divided by a -]");

            CLI_Controller.setReadHandler(
                    (line)->{
                        line.toUpperCase();
                        if (line.length() > 1) {
                            String[] rss = line.split("-");

                            CLI_Controller.setReadHandler(
                                (position)->{
                                    for (String s : rss) {
                                        System.out.println("Where do you want to put your " + s + " rss? 1 to 3 to identify the shelf");
                                        Marble.Color color = colorSelector(s.toUpperCase());
                                        DepositResource deposit = new DepositResource(color, Integer.parseInt(position) - 1);
                                        this.backEnd.notify(deposit);
                                    }
                                }
                            );


                        } else {
                            System.out.println("Where do you want to put your " + line + " rss? 1 to 3 to identify the shelf");

                            CLI_Controller.setReadHandler(
                                    (position)->{
                                        Marble.Color color = colorSelector(line.toUpperCase());
                                        DepositResource deposit = new DepositResource(color, Integer.parseInt(position) - 1);
                                        this.backEnd.notify(deposit);
                                        System.out.println(this.loading);
                                    }
                            );

                        }
                    }
            );


    }






    @Override
    public void handle(ActivePlayer event){

    }

    public static Marble.Color colorSelector(String s){
        switch (s){
            case "P":
                return Marble.Color.PURPLE;
            case "G":
                return Marble.Color.GREY;
            case "B":
                return Marble.Color.BLUE;
            case "Y":
                return Marble.Color.YELLOW;
            default:
                return null;
        }
    }

    @Override
    public void handle(ErrorUpdate event){
        CLI_Controller.showError(event);
        LoadingPageView(this.backEnd);
    }

    @Override
    public void handle(ModelUpdate event){

        for (Message<ModelEventHandler> e: event.getMessages()){
            if(e instanceof ActivePlayer){
                CLI_Controller.homePage.HomePageView(this.backEnd);
            }
        }
    }
}
