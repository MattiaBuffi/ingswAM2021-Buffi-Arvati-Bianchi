package it.polimi.ingsw.Client.CLI.Pages;

import it.polimi.ingsw.Client.CLI.CLI_Controller;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.DevelopmentCardData;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.Shelf;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ClientMessages.BuyDevelopmentCard;
import it.polimi.ingsw.Message.Model.*;
import it.polimi.ingsw.Message.ModelEventHandler;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.ResourceList;

import java.util.List;
import java.util.Scanner;

public class CardMarketPage extends ModelEventHandler.Default {

    private static final int[] CardMarketCostPosition = {307,330,353,376,1637,1660,1683,1706,2967,2990,3013,3036};
    private static final int[] CardMarketInputPosition = {573,596,619,642,1903,1926,1949,1972,3233,3256,2379,2402};
    private static final int[] CardMarketOutputPosition = {839,862,885,908,2169,2192,2215,2238,3499,3522,3545,3568};
    private static final int[] CardMarketVPPosition = {1251,1274,1297,1320,2581,2604,2627,2650,3911,3934,3957,3980};
    private static final int[] RssPosition = {415,944,952,1471,1479,1488, 3594, 3601, 3608, 3615};

    ViewBackEnd backEnd;
    char[] cardMarket;
    DevelopmentCardData[][] cardMatrix = new DevelopmentCardData[3][4];

    public CardMarketPage(char[] cardMarket) {
        this.cardMarket = cardMarket;
    }

    public void CardMarketPageView(ViewBackEnd backEnd){
        this.backEnd = backEnd;
        this.backEnd.setEventHandler(this);
        CLI_Controller.cls();
        Scanner input = new Scanner(System.in);
        for(int i=0;i<3;i++){
            for(int j=0;j<4;j++){
                updateCard(i,j);
            }
        }

        List<Shelf> playerShelf = this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getShelves();
        CLI_Controller.ShelfExtractor(cardMarket, playerShelf);

        ResourceList playerChest = this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getChest();
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
                this.backEnd.notify(messageBuyDev);
                CLI_Controller.homePage.HomePageView(this.backEnd);
                break;
            case "EXIT":
                System.out.println("redirecting to Home..");
                CLI_Controller.homePage.HomePageView(this.backEnd);
                break;
            default:
                System.out.println("Wrong Command, please insert a real command");
                CLI_Controller.cardMarketPage.CardMarketPageView(this.backEnd);
        }
    }

    @Override
    public void invalidMessage() {

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
    public void handle(MarketCardUpdate event) {
        backEnd.getModel().updateModel(event);

        updateCard(event.getX(), event.getY());
    }

    public void updateCard(int i, int j){
        cardMatrix[i][j] = this.backEnd.getModel().cardMarket.getCard(i,j);

        List<Marble> costList = cardMatrix[i][j].price.getAll();
        String costString = CLI_Controller.getColorStringFromMarble(costList);
        System.arraycopy(costString.toCharArray(), 0, cardMarket, CardMarketCostPosition[i*4+j], costString.toCharArray().length);

        List<Marble> requireList = cardMatrix[i][j].require.getAll();
        String requireString = CLI_Controller.getColorStringFromMarble(requireList);
        System.arraycopy(requireString.toCharArray(), 0, cardMarket, CardMarketInputPosition[i*4+j], requireString.toCharArray().length);

        List<Marble> produceList = cardMatrix[i][j].produce.getAll();
        String produceString = CLI_Controller.getColorStringFromMarble(produceList);
        System.arraycopy(produceString.toCharArray(), 0, cardMarket, CardMarketOutputPosition[i*4+j], produceString.toCharArray().length);

        String vp = Integer.toString(cardMatrix[i][j].victoryPoints);
        System.arraycopy(vp.toCharArray(), 0, cardMarket, CardMarketVPPosition[i*4+j], vp.toCharArray().length);
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
        CardMarketPageView(this.backEnd);
    }
}
