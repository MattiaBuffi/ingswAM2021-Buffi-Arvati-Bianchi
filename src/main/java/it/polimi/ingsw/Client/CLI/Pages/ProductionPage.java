package it.polimi.ingsw.Client.CLI.Pages;

import it.polimi.ingsw.Client.CLI.CLI_Controller;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.DevelopmentCardData;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.Shelf;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.ResourceList;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class ProductionPage {

    private static final int[] singleCardPosition = {21, 59, 97, 166};
    private static final int[] ProductionPosition = {2451,2475,2499,1786,1810,1834,1121,1145,1169};
    private static final int[] RssPosition = {415,944,952,1471,1479,1488, 3594, 3601, 3608, 3615};

    ViewBackEnd backEnd;
    char[] production;

    public ProductionPage(ViewBackEnd backEnd, char[] production) {
        this.backEnd = backEnd;
        this.production = production;
    }

    public void ProductionPageView() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        char[] customCard = CLI_Controller.readSchematics(12);
        int row = 0;
        int column = 0;
        List<List<DevelopmentCardData>> playerProductions = backEnd.getModel().current.getProductions();
        for (List<DevelopmentCardData> productionColumn : playerProductions) {
            for (DevelopmentCardData devCard : productionColumn) {
                List<Marble> costList = devCard.price.getAll();
                String costString = CLI_Controller.getColorStringFromMarble(costList);
                char[] costArray = costString.toCharArray();
                System.arraycopy(costArray, 0, customCard, singleCardPosition[0], costArray.length);

                List<Marble> requireList = devCard.require.getAll();
                String requireString = CLI_Controller.getColorStringFromMarble(requireList);
                char[] requireArray = requireString.toCharArray();
                System.arraycopy(requireArray, 0, customCard, singleCardPosition[1], requireArray.length);

                List<Marble> produceList = devCard.produce.getAll();
                String produceString = CLI_Controller.getColorStringFromMarble(produceList);
                char[] produceArray = produceString.toCharArray();
                System.arraycopy(produceArray, 0, customCard, singleCardPosition[2], produceArray.length);

                String vp = Integer.toString(devCard.victoryPoints);
                char[] vpArray = vp.toCharArray();
                System.arraycopy(vpArray, 0, customCard, singleCardPosition[3], vpArray.length);

                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 20; j++) {
                        production[ProductionPosition[column+row*3]+j+i*133] = customCard[i*20+j];
                    }
                }
                column++;
            }
            row++;
        }

        List<Shelf> playerShelf = backEnd.getModel().current.getShelves();
        CLI_Controller.ShelfExtractor(production, playerShelf);

        ResourceList playerChest = backEnd.getModel().current.getChest();
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
                        CLI_Controller.Production(x, backEnd);
                    }
                }else{
                    CLI_Controller.Production(productionString, backEnd);
                }
                CLI_Controller.scene = "HOME";
                break;
            case "EXIT":
                System.out.println("redirecting to Home..");
                CLI_Controller.scene="HOME";
                break;
            default:
                System.out.println("Wrong Command, please insert a real command");
                break;
        }
    }

}
