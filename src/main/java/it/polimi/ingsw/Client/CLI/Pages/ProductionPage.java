package it.polimi.ingsw.Client.CLI.Pages;

import it.polimi.ingsw.Client.CLI.CLI_Controller;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.DevelopmentCardData;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.LeaderCard;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ClientMessages.EndTurn;
import it.polimi.ingsw.Message.Model.*;
import it.polimi.ingsw.Message.ModelEventHandler;
import it.polimi.ingsw.Model.Marble.Marble;

import java.util.List;
import java.util.Scanner;

public class ProductionPage extends ModelEventHandler.Default{

    private static final int[] singleCardPosition = {21, 59, 97, 166};
    private static final int[] ProductionPosition = {2451,2475,2499,1786,1810,1834,1121,1145,1169};
    private static final int[] leaderDevelopmentPosition = {842,1640};
    private static final int singleLeaderDevPosition = 13;

    ViewBackEnd backEnd;
    char[] production;

    public ProductionPage(char[] production) {
        this.production = production;
    }

    public void ProductionPageView(ViewBackEnd backEnd) {
        this.backEnd = backEnd;
        this.backEnd.setEventHandler(this);
        CLI_Controller.cls();
        Scanner input = new Scanner(System.in);
        char[] customCard;
        customCard = CLI_Controller.readSchematics(12);
        int row = 0;
        int column = 0;

        List<List<DevelopmentCardData>> playerProductions = this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getProductions();
        for (List<DevelopmentCardData> productionColumn : playerProductions) {
            for (DevelopmentCardData devCard : productionColumn) {
                List<Marble> costList = devCard.price.getAll();
                String costString = CLI_Controller.getColorStringFromMarble(costList);
                System.arraycopy(costString.toCharArray(), 0, customCard, singleCardPosition[0], costString.toCharArray().length);

                List<Marble> requireList = devCard.require.getAll();
                String requireString = CLI_Controller.getColorStringFromMarble(requireList);
                System.arraycopy(requireString.toCharArray(), 0, customCard, singleCardPosition[1], requireString.toCharArray().length);

                List<Marble> produceList = devCard.produce.getAll();
                String produceString = CLI_Controller.getColorStringFromMarble(produceList);
                System.arraycopy(produceString.toCharArray(), 0, customCard, singleCardPosition[2], produceString.toCharArray().length);

                String vp = Integer.toString(devCard.victoryPoints);
                System.arraycopy(vp.toCharArray(), 0, customCard, singleCardPosition[3], vp.toCharArray().length);

                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 20; j++) {
                        production[ProductionPosition[column+row*3]+j+i*133] = customCard[i*20+j];
                    }
                }
                column++;
            }
            row++;
        }

        CLI_Controller.UpdateShelf(this.backEnd, production);
        CLI_Controller.UpdateChest(this.backEnd, production);

        if(CLI_Controller.leaderActive[1]>0){
            CLI_Controller.showLeaderShelf(production);
        }

        if(CLI_Controller.leaderActive[3]>0){
            List<LeaderCard> card = this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getLeaderCard();
            int i = 0;
            for (LeaderCard leaderCard : card) {
                if(leaderCard.getType().equals("DEVELOPMENT")){
                    char[] scheme = CLI_Controller.readSchematics(14);
                    Marble.Color colorEffect = leaderCard.getColorEffected();
                    String colorEffected = CLI_Controller.getColorString(colorEffect);
                    scheme[singleLeaderDevPosition] = colorEffected.charAt(0);
                    for (int j = 0; j < 6; j++){
                        for (int k = 0; k < 5; k++){
                            production[leaderDevelopmentPosition[i]+j+133*k] = scheme[j+6*k];
                        }
                    }
                    i++;
                }
            }
        }

        System.out.println(production);
        System.out.println("Insert Command (Produce,Exit, EndTurn): ");
        String command = input.nextLine().toUpperCase();
        switch (command){
            case "PRODUCE":
                System.out.println("Which production do you want to do? (0 = basic, 1 to 3 = production card, 4-5 = leader production, insert the numbers divided by '-') : ");
                String productionString = input.nextLine();
                if(productionString.length()>1) {
                    String[] productionArray = productionString.split("-");
                    for(String x: productionArray){
                        CLI_Controller.Production(x, this.backEnd);
                    }
                }else{
                    CLI_Controller.Production(productionString, backEnd);
                }
                CLI_Controller.homePage.HomePageView(this.backEnd);
                break;
            case "EXIT":
                System.out.println("redirecting to Home..");
                CLI_Controller.homePage.HomePageView(this.backEnd);
                break;
            case "ENDTURN":
                EndTurn message = new EndTurn();
                this.backEnd.notify(message);
                CLI_Controller.homePage.HomePageView(backEnd);
            default:
                System.out.println("Wrong Command, please insert a real command");
                break;
        }
    }

    @Override
    public void invalidMessage() {

    }

    @Override
    public void handle(ProductionBufferUpdate event) {
        backEnd.getModel().updateModel(event);
    }


    @Override
    public void handle(ChestUpdate event) {
        backEnd.getModel().updateModel(event);
        CLI_Controller.UpdateChest(backEnd, production);
        CLI_Controller.productionPage.ProductionPageView(backEnd);
    }

    @Override
    public void handle(ShelfUpdate event) {
        backEnd.getModel().updateModel(event);
        CLI_Controller.UpdateShelf(backEnd, production);
        CLI_Controller.productionPage.ProductionPageView(backEnd);
    }

    @Override
    public void handle(ErrorUpdate event) {
        CLI_Controller.showError(event);
        ProductionPageView(this.backEnd);
    }

}
