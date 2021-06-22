package it.polimi.ingsw.Client.CLI.Pages;

import it.polimi.ingsw.Client.CLI.CLI_Controller;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.DevelopmentCardData;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.Shelf;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ClientMessages.BuyDevelopmentCard;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.ResourceList;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class CardMarketPage {

    private static final int[] CardMarketCostPosition = {307,330,353,376,1637,1660,1683,1706,2967,2990,3013,3036};
    private static final int[] CardMarketInputPosition = {573,596,619,642,1903,1926,1949,1972,3233,3256,2379,2402};
    private static final int[] CardMarketOutputPosition = {839,862,885,908,2169,2192,2215,2238,3499,3522,3545,3568};
    private static final int[] CardMarketVPPosition = {1251,1274,1297,1320,2581,2604,2627,2650,3911,3934,3957,3980};
    private static final int[] RssPosition = {415,944,952,1471,1479,1488, 3594, 3601, 3608, 3615};

    ViewBackEnd backEnd;
    char[] cardMarket;
    DevelopmentCardData[][] cardMatrix = new DevelopmentCardData[3][4];

    public CardMarketPage(ViewBackEnd backEnd, char[] cardMarket) {
        this.backEnd = backEnd;
        this.cardMarket = cardMarket;
    }

    public void CardMarketPageView() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        for(int i=0;i<3;i++){
            for(int j=0;j<4;j++){
                cardMatrix[i][j] = backEnd.getModel().cardMarket.getCard(i,j);

                List<Marble> costList = cardMatrix[i][j].price.getAll();
                String costString = CLI_Controller.getColorStringFromMarble(costList);
                char[] costArray = costString.toCharArray();
                System.arraycopy(costArray, 0, cardMarket, CardMarketCostPosition[i*4+j], costArray.length);

                List<Marble> requireList = cardMatrix[i][j].require.getAll();
                String requireString = CLI_Controller.getColorStringFromMarble(requireList);
                char[] requireArray = requireString.toCharArray();
                System.arraycopy(requireArray, 0, cardMarket, CardMarketInputPosition[i*4+j], requireArray.length);

                List<Marble> produceList = cardMatrix[i][j].produce.getAll();
                String produceString = CLI_Controller.getColorStringFromMarble(produceList);
                char[] produceArray = produceString.toCharArray();
                System.arraycopy(produceArray, 0, cardMarket, CardMarketOutputPosition[i*4+j], produceArray.length);

                String vp = Integer.toString(cardMatrix[i][j].victoryPoints);
                char[] vpArray = vp.toCharArray();
                System.arraycopy(vpArray, 0, cardMarket, CardMarketVPPosition[i*4+j], vpArray.length);
            }
        }
        List<Shelf> playerShelf = backEnd.getModel().current.getShelves();
        CLI_Controller.ShelfExtractor(cardMarket, playerShelf);

        ResourceList playerChest = backEnd.getModel().current.getChest();
        List<Marble> playerChestMarble = playerChest.getAll();
        String[] playerChestRss = CLI_Controller.getColorStringFromMarble(playerChestMarble).split(" ");
        for (int i = 0; i < playerChestRss.length; i++) {
            System.arraycopy(playerChestRss[i].toCharArray(), 0, cardMarket, RssPosition[i+6], playerChestRss[i].toCharArray().length);
        }

        System.out.println(cardMarket);
        System.out.println("Insert Command (Buy,Exit): ");
        String command = input.nextLine().toUpperCase();
        switch (command){
            case "BUY":
                System.out.println("Which card do you want to buy? (x-y-z where x and y are the coordinates of the card and z is the column where do you want to put your new card) : ");
                String buyCard = input.nextLine();
                String[] buyCardArray = buyCard.split("-");
                BuyDevelopmentCard messageBuyDev = new BuyDevelopmentCard(Integer.parseInt(buyCardArray[0]), Integer.parseInt(buyCardArray[1]), Integer.parseInt(buyCardArray[2]));
                backEnd.notify(messageBuyDev);
                CLI_Controller.scene = "HOME";
                break;
            case "EXIT":
                System.out.println("redirecting to Home..");
                CLI_Controller.scene = "HOME";
                break;
            default:
                System.out.println("Wrong Command, please insert a real command");
                CLI_Controller.scene = "HOME";
        }
    }
}
