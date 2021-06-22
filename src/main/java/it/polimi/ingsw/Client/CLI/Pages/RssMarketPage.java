package it.polimi.ingsw.Client.CLI.Pages;

import it.polimi.ingsw.Client.CLI.CLI_Controller;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.Shelf;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ClientMessages.TakeResources;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.ResourceList;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class RssMarketPage {

    private static final int[] RssMarket = {578,593,608,623,1509,1524,1539,1554,2440,2455,2470,2485,3439};
    private static final int[] RssPosition = {415,944,952,1471,1479,1488, 3594, 3601, 3608, 3615};

    ViewBackEnd backEnd;
    char[] rssMarket;

    public RssMarketPage(ViewBackEnd backEnd, char[] rssMarket) {
        this.backEnd = backEnd;
        this.rssMarket = rssMarket;
    }

    public void RssMarketPageView() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < 3; i++){
            List<Marble> rssRow = backEnd.getModel().resourceMarket.get(i);
            for (int j=0; j<rssRow.size(); j++) {
                String color = rssRow.get(j).getColor().toString();
                switch (color) {
                    case "YELLOW":
                        rssMarket[RssMarket[j+i*4]] = 'Y';
                        break;
                    case "BLUE":
                        rssMarket[RssMarket[j+i*4]] = 'B';
                        break;
                    case "GREY":
                        rssMarket[RssMarket[j+i*4]] = 'G';
                        break;
                    case "PURPLE":
                        rssMarket[RssMarket[j+i*4]] = 'P';
                        break;
                }
            }
        }
        Marble bonusMarble = backEnd.getModel().resourceMarket.getBonusMarble();
        String bonusColor = bonusMarble.getColor().toString();
        switch (bonusColor) {
            case "YELLOW":
                rssMarket[RssMarket[12]] = 'Y';
                break;
            case "BLUE":
                rssMarket[RssMarket[12]] = 'B';
                break;
            case "GREY":
                rssMarket[RssMarket[12]] = 'G';
                break;
            case "PURPLE":
                rssMarket[RssMarket[12]] = 'P';
                break;
        }

        List<Shelf> playerShelf = backEnd.getModel().current.getShelves();
        CLI_Controller.ShelfExtractor(rssMarket, playerShelf);

        ResourceList playerChest = backEnd.getModel().current.getChest();
        List<Marble> playerChestMarble = playerChest.getAll();
        String[] playerChestRss = CLI_Controller.getColorStringFromMarble(playerChestMarble).split(" ");
        for (int i = 0; i < 4; i++) {
            System.arraycopy(playerChestRss[i].toCharArray(), 0, rssMarket, RssPosition[i+6], playerChestRss[i].toCharArray().length);
        }

        System.out.println(rssMarket);
        System.out.println("Insert Command (Buy,Exit): ");
        String command = input.nextLine().toUpperCase();
        switch (command){
            case "BUY":
                System.out.println("Which card do you want to buy? (value between 1 and 7 [1 = first column on the left, 7 = first row from the top]) : ");
                String buyRss = input.nextLine();
                TakeResources messageBuyRss = new TakeResources(Integer.parseInt(buyRss));
                backEnd.notify(messageBuyRss);
                CLI_Controller.scene="HOME";
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
