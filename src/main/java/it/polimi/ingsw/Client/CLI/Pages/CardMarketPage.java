package it.polimi.ingsw.Client.CLI.Pages;

import it.polimi.ingsw.Client.CLI.Cli;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.DevelopmentCardData;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.LeaderCard;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ClientMessages.BuyDevelopmentCard;
import it.polimi.ingsw.Message.ClientMessages.EndTurn;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.Model.*;
import it.polimi.ingsw.Message.ModelEventHandler;
import it.polimi.ingsw.Model.LeaderCard.ActivationStrategy.ActivationStrategy;
import it.polimi.ingsw.Model.Marble.Marble;

import java.io.FileNotFoundException;
import java.util.List;

public class CardMarketPage extends ModelEventHandler.Default {

    private static final int[] CardMarketCostPosition = {307, 330, 353, 376, 1637, 1660, 1683, 1706, 2967, 2990, 3013, 3036};
    private static final int[] CardMarketInputPosition = {573, 596, 619, 642, 1903, 1926, 1949, 1972, 3233, 3256, 3279, 3302};
    private static final int[] CardMarketOutputPosition = {839, 862, 885, 908, 2169, 2192, 2215, 2238, 3499, 3522, 3545, 3568};
    private static final int[] CardMarketVPPosition = {1251, 1274, 1297, 1320, 2581, 2604, 2627, 2650, 3911, 3934, 3957, 3980};
    private static final int[] leaderDiscount = {135, 147};

    ViewBackEnd backEnd;
    char[] cardMarket;
    int spaces;
    DevelopmentCardData[][] cardMatrix = new DevelopmentCardData[3][4];

    public CardMarketPage(char[] cardMarket) {
        this.cardMarket = cardMarket;
    }

    public void update(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                updateCard(i, j);
            }
        }

        if (Cli.leaderActive[1] > 0) {
            Cli.showLeaderShelf(cardMarket);
        }

        if (Cli.leaderActive[0] > 0) {
            List<LeaderCard> card = this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getLeaderCard();
            int i = 0;
            for (LeaderCard leaderCard : card) {
                if (leaderCard.getType() == ActivationStrategy.Type.DISCOUNT) {
                    Marble.Color colorEffect = leaderCard.getColor();
                    String colorEffected = "Discount -1" + Cli.getColorString(colorEffect);
                    System.arraycopy(colorEffected.toCharArray(), 0, cardMarket, leaderDiscount[i], colorEffected.toCharArray().length);
                    i++;
                }
            }
        }

        Cli.UpdateShelf(this.backEnd, cardMarket);
        Cli.UpdateChest(this.backEnd, cardMarket);
    }


    private void buy(String line) {
        if (line.length() > 1) {
            String[] buyCardArray = line.split("-");
            try {
                BuyDevelopmentCard messageBuyDev = new BuyDevelopmentCard(Integer.parseInt(buyCardArray[0]) - 1, Integer.parseInt(buyCardArray[1]) - 1, Integer.parseInt(buyCardArray[2]) - 1);
                this.backEnd.notify(messageBuyDev);
            }catch (NumberFormatException e) {
                Cli.showUpdateMessage("Wrong Input");
                CardMarketPageView(this.backEnd);
                return;
            }

        }
        update();
    }


    public void CardMarketPageView(ViewBackEnd backEnd) {
        this.backEnd = backEnd;
        this.backEnd.setEventHandler(this);
        Cli.cls();
        update();
        System.out.println(cardMarket);

        System.out.println("Insert Command (Buy,EndTurn,Exit): ");

        Cli.setReadHandler(
                (line) -> {
                    line = line.toUpperCase();
                    switch (line) {
                        case "BUY":
                            System.out.println("Which card do you want to buy? (x-y-z where x and y are the coordinates of the card and z is the column where do you want to put your new card) : ");
                            System.out.println("Coordinates start from 1, for example if you want to take the green lv 1 card and put it in the first column use 1-1-1");
                            Cli.setReadHandler(this::buy);
                            break;

                        case "EXIT":
                            System.out.println("redirecting to Home..");
                            Cli.homePage.HomePageView(this.backEnd);
                            break;

                        case "ENDTURN":
                            EndTurn message = new EndTurn();
                            this.backEnd.notify(message);
                            Cli.homePage.HomePageView(backEnd);

                        default:
                            System.out.println("Wrong Command, please insert a real command");
                            Cli.cardMarketPage.CardMarketPageView(this.backEnd);
                    }
                }
        );
    }

    public void updateCard(int i, int j) {
        cardMatrix[i][j] = this.backEnd.getModel().cardMarket.getCard(j, i);

        if(!cardMatrix[i][j].id.equals("null")) {
            List<Marble> costList = cardMatrix[i][j].price.getAllMarble();
            String costString = Cli.getColorStringFromMarble(costList);
            spaces = 11 - costString.length();
            for (int k = 0; k < spaces; k++) {
                costString = costString.concat(" ");
            }
            System.arraycopy(("COST: " + costString).toCharArray(), 0, cardMarket, CardMarketCostPosition[i * 4 + j], ("COST: " + costString).toCharArray().length);

            List<Marble> requireList = cardMatrix[i][j].require.getAllMarble();
            String requireString = Cli.getColorStringFromMarble(requireList);
            spaces = 13 - requireString.length();
            for (int k = 0; k < spaces; k++) {
                requireString = requireString.concat(" ");
            }
            System.arraycopy(("IN: " + requireString).toCharArray(), 0, cardMarket, CardMarketInputPosition[i * 4 + j], ("IN: " + requireString).toCharArray().length);

            List<Marble> produceList = cardMatrix[i][j].produce.getAllMarble();
            String produceString = Cli.getColorStringFromMarble(produceList);
            spaces = 12 - produceString.length();
            for (int k = 0; k < spaces; k++) {
                produceString = produceString.concat(" ");
            }
            System.arraycopy(("OUT: " + produceString).toCharArray(), 0, cardMarket, CardMarketOutputPosition[i * 4 + j], ("OUT: " + produceString).toCharArray().length);

            String vp = Integer.toString(cardMatrix[i][j].victoryPoints);
            System.arraycopy((vp + "VP").toCharArray(), 0, cardMarket, CardMarketVPPosition[i * 4 + j], (vp + "VP").toCharArray().length);

        }else{
            System.arraycopy(("COST:            ").toCharArray(), 0, cardMarket, CardMarketCostPosition[i * 4 + j], ("COST:            ").toCharArray().length);

            System.arraycopy(("IN:              ").toCharArray(), 0, cardMarket, CardMarketInputPosition[i * 4 + j], ("IN:              ").toCharArray().length);

            System.arraycopy(("OUT:              ").toCharArray(), 0, cardMarket, CardMarketOutputPosition[i * 4 + j], ("OUT:             ").toCharArray().length);

            System.arraycopy(("    ").toCharArray(), 0, cardMarket, CardMarketVPPosition[i * 4 + j], ("    ").toCharArray().length);
        }
    }

    @Override
    public void invalidMessage() {

    }

    @Override
    public void handle(ChestUpdate event) {
        Cli.UpdateChest(backEnd, cardMarket);
    }

    @Override
    public void handle(ShelfUpdate event) {
        Cli.UpdateShelf(backEnd, cardMarket);
    }

    @Override
    public void handle(MarketCardUpdate event) {
        Cli.homePage.HomePageView(this.backEnd);
    }

    @Override
    public void handle(ErrorUpdate event) {
        Cli.showError(event);
        CardMarketPageView(this.backEnd);
    }

    @Override
    public void handle(ModelUpdate event) {
        for (Message<ModelEventHandler> e : event.getMessages()) {
            e.accept(this);
        }
    /*
        CLI_Controller.showUpdateMessage(event.getMessage());
        update();*/
    }

    @Override
    public void handle(ActivePlayer event) {
        Cli.cls();
        System.out.println("your turn is over, redirecting to home");
        Cli.homePage.HomePageView(this.backEnd);
    }

    @Override
    public void handle(ActionTokenPlayed event) {
        Cli.showSingleMessage(event, this.backEnd);
    }

    @Override
    public void handle(VaticanReport event) {
        Cli.activatePopeFavor(event.getIndex());
    }

    @Override
    public void handle(EndGame event){
        Cli.endPage.EndGameView(this.backEnd, event);
    }
}
