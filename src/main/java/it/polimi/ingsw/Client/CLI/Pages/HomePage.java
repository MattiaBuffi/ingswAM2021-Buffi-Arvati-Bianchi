package it.polimi.ingsw.Client.CLI.Pages;

import it.polimi.ingsw.Client.CLI.CLI_Controller;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.LeaderCard;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ClientMessages.ActivateLeaderCard;
import it.polimi.ingsw.Message.ClientMessages.DiscardLeaderCard;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.Model.*;
import it.polimi.ingsw.Message.ModelEventHandler;
import java.util.List;
import java.util.Scanner;

public class HomePage extends ModelEventHandler.Default {

    private static int lastPosition = 0;
    private static final int TurnPosition = 2235;
    private static final int[] HomeLeaderCost = {2304, 2330};
    private static final int[] HomeLeaderType = {2570, 2596};
    private static final int[] HomeLeaderEffect = {2836, 2862};
    private static final int[] HomeLeaderPV = {3247, 3273};
    private static final int[] HomePopeFavourPosition = {726,751,780};
    private static final int[] FaithCellPosition = {1102, 1107,1112,713,314, 319, 324, 329, 334,339,738,1137,
                                                    1142,1147,1152,1157,1162,763,364,369,374,379,384,389,394,399};
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


        CLI_Controller.UpdateShelf(this.backEnd, homePage);
        CLI_Controller.UpdateChest(this.backEnd, homePage);



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

        for (int i = 0; i < CLI_Controller.popeFavourActive.length; i++){
            if(CLI_Controller.popeFavourActive[i] == 1){
                homePage[HomePopeFavourPosition[i]] = 'X';
            }
        }

        List<LeaderCard> leaderCard = this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getLeaderCard();
        for (int i = 0; i < 2; i++) {
            CLI_Controller.LeaderCardInfoExtractor(homePage, leaderCard, i, HomeLeaderType, HomeLeaderPV, HomeLeaderCost, HomeLeaderEffect);
        }

        if(CLI_Controller.leaderActive[1]>0){
            CLI_Controller.showLeaderShelf(homePage);
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

            }else if (activateLeader.equals("2")){
                ActivateLeaderCard messageActivate = new ActivateLeaderCard(this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getLeaderCard().get(1).getId());
                this.backEnd.notify(messageActivate);

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
                default:
                    System.out.println("Wrong Command");
                    CLI_Controller.homePage.HomePageView(backEnd);
            }

        }

    }



    @Override
    public void invalidMessage() {

    }

    @Override
    public void handle(ErrorUpdate event) {
        CLI_Controller.showError(event);
        HomePageView(this.backEnd);
    }

    @Override
    public void handle(ChestUpdate event) {
        backEnd.getModel().updateModel(event);
        CLI_Controller.UpdateChest(backEnd, homePage);
    }

    @Override
    public void handle(ShelfUpdate event) {
        backEnd.getModel().updateModel(event);
        CLI_Controller.UpdateShelf(backEnd, homePage);
    }

    @Override
    public void handle(LeaderCardActivation event) {
        if (event.getId().equals(this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getLeaderCard().get(0).getId())){
            System.arraycopy(active.toCharArray(), 0, homePage, LeaderCardHomePosActive[0], active.length());
        }else{
            System.arraycopy(active.toCharArray(), 0, homePage, LeaderCardHomePosActive[1], active.length());
        }

        CLI_Controller.leaderPowerSelector(event.getId());
    }

    @Override
    public void handle(VaticanReport event) {
        //backEnd.something

        CLI_Controller.activatePopeFavor(event.getIndex());
    }

    @Override
    public void handle(VaticanRoutePosition event) {
        //backEnd.something

        if(event.getUsername().equals(this.backEnd.getMyUsername())){
            HomePageView(this.backEnd);
        }
    }

}
