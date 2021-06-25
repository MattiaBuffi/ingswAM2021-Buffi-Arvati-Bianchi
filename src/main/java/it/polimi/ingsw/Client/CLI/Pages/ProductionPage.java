package it.polimi.ingsw.Client.CLI.Pages;

import it.polimi.ingsw.Client.CLI.CLI_Controller;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.DevelopmentCardData;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.Shelf;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.Model.ChestUpdate;
import it.polimi.ingsw.Message.Model.ErrorUpdate;
import it.polimi.ingsw.Message.Model.ProductionBufferUpdate;
import it.polimi.ingsw.Message.Model.ShelfUpdate;
import it.polimi.ingsw.Message.ModelEventHandler;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.ResourceList;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class ProductionPage extends ModelEventHandler.Default{

    private static final int[] singleCardPosition = {21, 59, 97, 166};
    private static final int[] ProductionPosition = {2451,2475,2499,1786,1810,1834,1121,1145,1169};
    private static final int[] RssPosition = {415,944,952,1471,1479,1488, 3594, 3601, 3608, 3615};

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
        char[] customCard = new char[0];
        try {
            customCard = CLI_Controller.readSchematics(12);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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

        List<Shelf> playerShelf = this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getShelves();
        CLI_Controller.ShelfExtractor(production, playerShelf);

        ResourceList playerChest = this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getChest();
        List<Marble> playerChestMarble = playerChest.getAll();

        String[] playerChestRss = CLI_Controller.getColorStringFromMarble(playerChestMarble).split(" ");
        for (int i = 0; i < 4; i++) {
            System.arraycopy(playerChestRss[i].toCharArray(), 0, production, RssPosition[i+6], playerChestRss[i].toCharArray().length);
        }

        System.out.println(production);
        System.out.println("Insert Command (Produce,Exit): ");
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
    }

    @Override
    public void handle(ShelfUpdate event) {
        backEnd.getModel().updateModel(event);
    }

    @Override
    public void handle(ErrorUpdate event) {
        CLI_Controller.cls();
        System.out.println(event.getErrorMessage());
        System.out.println("Here is a free time travel, enjoy it");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ProductionPageView(this.backEnd);
    }
}
