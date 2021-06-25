package it.polimi.ingsw.Client.CLI.Pages;

import it.polimi.ingsw.Client.CLI.CLI_Controller;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.LeaderCard;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.Shelf;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ClientMessages.ActivateLeaderCard;
import it.polimi.ingsw.Message.ClientMessages.DiscardLeaderCard;
import it.polimi.ingsw.Message.Model.ChestUpdate;
import it.polimi.ingsw.Message.Model.ErrorUpdate;
import it.polimi.ingsw.Message.Model.LeaderCardActivation;
import it.polimi.ingsw.Message.Model.ShelfUpdate;
import it.polimi.ingsw.Message.ModelEventHandler;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.ResourceList;

import java.util.List;
import java.util.Scanner;

public class HomePage extends ModelEventHandler.Default {

    private static int lastPosition = 0;
    private static final int TurnPosition = 2235;
    private static final int[] HomeLeaderCost = {2304, 2330};
    private static final int[] HomeLeaderType = {2570, 2596};
    private static final int[] HomeLeaderEffect = {2836, 2862};
    private static final int[] HomeLeaderPV = {3247, 3273};
    private static final int[] HomeRankPosition = {2633,2766,2899,3032};
    private static final int[] FaithCellPosition = {1102, 1107,1112,713,314, 319, 324, 329, 334,339,738,1137,
                                                    1142,1147,1152,1157,1162,763,364,369,374,379,384,389,394,399};
    private static final int[] RssPosition = {415,944,952,1471,1479,1488, 3594, 3601, 3608, 3615};
    private static final int[] LeaderCardHomePosActive = {3502,3528};
    private static final int[] LeaderCardHomePosDiscard = {2169,2195};
    private static final String active = "Active";

    ViewBackEnd backEnd;
    char[] homePage;

    public HomePage(char[] homePage) {
        this.homePage = homePage;
    }

    public void SelectInitialRss(ViewBackEnd backEnd){
        this.backEnd = backEnd;
        this.backEnd.setEventHandler(this);
        CLI_Controller.cls();
        Scanner input = new Scanner(System.in);
        int x = 0;
        int y = 0;
        System.out.println("You are Player Number" + x + ", you can get: " + y +
                "initial Resources for free, please insert the color of the resource that you want to take " +
                "[(P/G/B/Y) if more than 1 rss please insert the two colors dividded by a -]" );
        String freeRssTaken = input.next();


        HomePageView(backEnd);
    }



    public void HomePageView(ViewBackEnd backEnd){
        this.backEnd = backEnd;
        this.backEnd.setEventHandler(this);

        Scanner input = new Scanner(System.in);
        CLI_Controller.cls();
        //Printing Name of Current Player
        String customName = this.backEnd.getModel().current.getUsername() + "'s Turn";
        System.arraycopy(customName.toCharArray(), 0, homePage, TurnPosition, customName.toCharArray().length);

        //Printing Shelves
        List<Shelf> playerShelf = this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getShelves();
        CLI_Controller.ShelfExtractor(homePage, playerShelf);

        //Printing Chest
        ResourceList playerChest = this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getChest();
        List<Marble> playerChestMarble = playerChest.getAll();
        String[] playerChestRss = CLI_Controller.getColorStringFromMarble(playerChestMarble).split(" ");
        for (int i = 0; i < playerChestRss.length; i++) {
            System.arraycopy(playerChestRss[i].toCharArray(), 0, homePage, RssPosition[i+6], playerChestRss[i].toCharArray().length);
        }


        int position = this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getFaithPoints();
        if ( position != lastPosition){
            String lastPosString = Integer.toString(lastPosition);
            char[] lastPositionArray = lastPosString.toCharArray();
            System.arraycopy(lastPositionArray, 0, homePage, FaithCellPosition[lastPosition], lastPositionArray.length);
            if(position>=10){
                homePage[FaithCellPosition[position]]= '+';
                homePage[FaithCellPosition[position]+1]= ' ';
            }else
                homePage[FaithCellPosition[position]]= '+';
            lastPosition = position;
        }

        List<LeaderCard> leaderCard = this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getLeaderCard();
        for (int i = 0; i < 2; i++) {
            CLI_Controller.LeaderCardInfoExtractor(homePage, leaderCard, i, HomeLeaderType, HomeLeaderPV, HomeLeaderCost, HomeLeaderEffect);
        }

        System.out.println(homePage);

        System.out.println("Insert Command (Produce, CardMarket, RssMarket, View, Activate, Discard, Quit): ");
        String command = input.nextLine().toUpperCase();
        if(command.equals("ACTIVATE")){

            System.out.println("Which Leader card do you want to Activate (1/2): ");
            String activateLeader = input.nextLine();

            if (activateLeader.equals("1")){
                ActivateLeaderCard messageActivate = new ActivateLeaderCard(this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getLeaderCard().get(0).getId());
                this.backEnd.notify(messageActivate);
                //Clausola attivazione possibile
                System.arraycopy(active.toCharArray(), 0, homePage, LeaderCardHomePosActive[0], active.length());
            }else if (activateLeader.equals("2")){
                ActivateLeaderCard messageActivate = new ActivateLeaderCard(this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getLeaderCard().get(1).getId());
                this.backEnd.notify(messageActivate);
                //Clausola attivazione possibile
                System.arraycopy(active.toCharArray(), 0, homePage, LeaderCardHomePosActive[1], active.length());
            }
        }else if (command.equals("DISCARD")){

            System.out.println("Which Leader card do you want to Discard (1/2): ");
            String discardLeader = input.nextLine();
            if (discardLeader.equals("1")){
                DiscardLeaderCard messageDiscard = new DiscardLeaderCard(this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getLeaderCard().get(0).getId());
                this.backEnd.notify(messageDiscard);
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 20; j++) {
                        homePage[LeaderCardHomePosDiscard[0]+j+i*133]= ' ';
                    }
                }
            }else if (discardLeader.equals("2")){
                DiscardLeaderCard messageDiscard = new DiscardLeaderCard(this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getLeaderCard().get(1).getId());
                this.backEnd.notify(messageDiscard);
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 20; j++) {
                        homePage[LeaderCardHomePosDiscard[1]+j+i*133]= ' ';
                    }
                }
            }
        }else {
            switch (command) {
                case "PRODUCE":
                    CLI_Controller.productionPage.ProductionPageView(backEnd);
                    break;
                case "CARDMARKET":
                    CLI_Controller.cardMarketPage.CardMarketPageView(backEnd);
                    break;
                case "RSSMARKET":
                    CLI_Controller.rssMarketPage.RssMarketPageView(backEnd);
                    break;
                case "VIEW":
                    CLI_Controller.viewPage.ViewPageView(backEnd);
                    break;
                case "QUIT":
                    CLI_Controller.quitPage.QuitPageView(backEnd);
                    break;
            }

        }

    }

    @Override
    public void invalidMessage() {

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
        HomePageView(this.backEnd);
    }

    @Override
    public void handle(ChestUpdate event) {
        backEnd.getModel().updateModel(event);
    }

    @Override
    public void handle(ShelfUpdate event) {
        backEnd.getModel().updateModel(event);
    }

}
