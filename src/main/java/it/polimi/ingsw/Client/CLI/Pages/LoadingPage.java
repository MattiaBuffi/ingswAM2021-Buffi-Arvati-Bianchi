package it.polimi.ingsw.Client.CLI.Pages;
import it.polimi.ingsw.Client.CLI.CLI_Controller;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ClientMessages.DepositResource;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.Model.ActivePlayer;
import it.polimi.ingsw.Message.Model.ErrorUpdate;
import it.polimi.ingsw.Message.Model.ModelUpdate;
import it.polimi.ingsw.Message.Model.ResourceSetup;
import it.polimi.ingsw.Message.ModelEventHandler;
import it.polimi.ingsw.Model.Marble.Marble;

public class LoadingPage extends ModelEventHandler.Default{

    ViewBackEnd backEnd;
    char[] loading;
    Marble.Color multipleColor;

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

    public void multipleInitial1(String line){
        multipleColor = CLI_Controller.fromStringToColor(line);
        System.out.println("Where do you want to put your " + line + " rss? 1 to 3 to identify the shelf");
        CLI_Controller.setReadHandler(this::multipleInitial2);
    }

    public void multipleInitial2(String position){
        DepositResource deposit = new DepositResource(multipleColor, Integer.parseInt(position) - 1);
        this.backEnd.notify(deposit);

    }
/*
    public void multipleInitial3(String line){
        multipleColor = CLI_Controller.fromStringToColor(line);
        System.out.println("Where do you want to put your " + line + " rss? 1 to 3 to identify the shelf");
        CLI_Controller.setReadHandler(this::multipleInitial4);

    }

    public void multipleInitial4(String position){
        DepositResource deposit = new DepositResource(multipleColor, Integer.parseInt(position) - 1);
        this.backEnd.notify(deposit);
        CLI_Controller.cls();
        System.out.println(this.loading);
    }
*/
    @Override
    public void handle(ResourceSetup event){
            CLI_Controller.cls();

            System.out.println("You can get " + event.getAvailableResources() +
                    " initial Resources for free, please insert the color of the resource that you want to take " +
                    "use one of this letter please  P/G/B/Y ");
        if(event.getAvailableResources()>1){
            CLI_Controller.setReadHandler(this::multipleInitial1);
            return;
        }


            CLI_Controller.setReadHandler(
                    (line)->{

                                System.out.println("Where do you want to put your " + line + " rss? 1 to 3 to identify the shelf");

                                CLI_Controller.setReadHandler(
                                        (position)->{
                                            try {
                                                Marble.Color color = CLI_Controller.fromStringToColor(line);
                                                DepositResource deposit = new DepositResource(color, Integer.parseInt(position) - 1);
                                                this.backEnd.notify(deposit);
                                                System.out.println(this.loading);
                                            }catch (NumberFormatException ex) {
                                                CLI_Controller.showUpdateMessage("Wrong Input");
                                            }

                                }
                            );
                    }
            );
    }

    @Override
    public void handle(ErrorUpdate event){
        CLI_Controller.showError(event);
        LoadingPageView(this.backEnd);
    }

    @Override
    public void handle(ModelUpdate event){
        for (Message<ModelEventHandler> e: event.getMessages()){
            e.accept(this);
        }
    }

    @Override
    public void handle(ActivePlayer event){
        CLI_Controller.homePage.HomePageView(this.backEnd);
        backEnd.update(event);
    }
}
