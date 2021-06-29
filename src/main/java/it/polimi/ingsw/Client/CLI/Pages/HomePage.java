package it.polimi.ingsw.Client.CLI.Pages;

import it.polimi.ingsw.Client.CLI.CLI_Controller;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.LeaderCard;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ClientMessages.ActivateLeaderCard;
import it.polimi.ingsw.Message.ClientMessages.DiscardLeaderCard;
import it.polimi.ingsw.Message.ClientMessages.EndTurn;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.Model.*;
import it.polimi.ingsw.Message.ModelEventHandler;
import java.util.List;
import java.util.Scanner;

public class HomePage extends ModelEventHandler.Default {

    private static int lastPosition = 0;
    private static final int TurnPosition = 2228;
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


    public void print(){
        CLI_Controller.cls();
        //Printing Name of Current Player
        String currentName;
        if (this.backEnd.getModel().current.getUsername().equals(this.backEnd.getMyUsername())) {
            currentName = "It's your Turn     ";
        } else {
            currentName = "It's " + this.backEnd.getModel().current.getUsername() + "'s Turn     ";
        }
        System.arraycopy(currentName.toCharArray(), 0, homePage, TurnPosition, currentName.toCharArray().length);

        CLI_Controller.UpdateShelf(this.backEnd, homePage);
        CLI_Controller.UpdateChest(this.backEnd, homePage);


        int position = this.backEnd.getModel().vaticanRoute.getPlayerFaithPoint(this.backEnd.getMyUsername());
        if ( position != lastPosition && lastPosition!= 0){
            System.arraycopy(Integer.toString(lastPosition).toCharArray(), 0, homePage, FaithCellPosition[lastPosition], Integer.toString(lastPosition).toCharArray().length);
            if(position>=10){
                homePage[FaithCellPosition[position]]= 'X';
                homePage[FaithCellPosition[position]+1]= ' ';
            }else
                homePage[FaithCellPosition[position]]= 'X';
            lastPosition = position;
        }else{
            homePage[FaithCellPosition[position]]= 'X';
        }

        for (int i = 0; i < CLI_Controller.popeFavourActive.length; i++){
            if(CLI_Controller.popeFavourActive[i] == 1){
                homePage[HomePopeFavourPosition[i]] = 'O';
                homePage[HomePopeFavourPosition[i]+1] = 'K';
            }
        }

        List<LeaderCard> leaderCard = this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getLeaderCard();
        for (int i = 0; i < leaderCard.size(); i++) {
            CLI_Controller.LeaderCardInfoExtractor(homePage, leaderCard, i, HomeLeaderType, HomeLeaderPV, HomeLeaderCost, HomeLeaderEffect);
        }

        if(CLI_Controller.leaderActive[1]>0){
            CLI_Controller.showLeaderShelf(homePage);
        }

        System.out.println(homePage);

        System.out.println("Insert Command (Produce, CardMarket, RssMarket, View, Activate, Discard, EndTurn, Quit): ");



    }

    public void activate(String line){

        if (line.equals("1")){
            ActivateLeaderCard messageActivate = new ActivateLeaderCard(this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getLeaderCard().get(0).getId());
            this.backEnd.notify(messageActivate);

        }else if (line.equals("2")){
            ActivateLeaderCard messageActivate = new ActivateLeaderCard(this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getLeaderCard().get(1).getId());
            this.backEnd.notify(messageActivate);

        }
    }

    public void discard(String line){

        if (line.equals("1")){
            DiscardLeaderCard messageDiscard = new DiscardLeaderCard(this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getLeaderCard().get(0).getId());
            this.backEnd.notify(messageDiscard);
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 20; j++) {
                    homePage[LeaderCardHomePosDiscard[0]+j+i*133]= ' ';
                }
            }
        }else if (line.equals("2")){
            DiscardLeaderCard messageDiscard = new DiscardLeaderCard(this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getLeaderCard().get(1).getId());
            this.backEnd.notify(messageDiscard);
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 20; j++) {
                    homePage[LeaderCardHomePosDiscard[1]+j+i*133]= ' ';
                }
            }
        }
    }



    public void HomePageView (ViewBackEnd backEnd){
        this.backEnd = backEnd;
        this.backEnd.setEventHandler(this);

        print();

        CLI_Controller.setReadHandler(
                (line)->{
                        line = line.toUpperCase();
                        if(line.equals("ACTIVATE")){
                            System.out.println("Which Leader card do you want to Activate (1/2): ");
                            CLI_Controller.setReadHandler(this::activate);
                        }else if (line.equals("DISCARD")){
                            System.out.println("Which Leader card do you want to Discard (1/2): ");
                            CLI_Controller.setReadHandler(this::discard);
                        }else {
                            switch (line) {
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
                                case "ENDTURN":
                                    EndTurn message = new EndTurn();
                                    this.backEnd.notify(message);
                                    break;
                                default:
                                    System.out.println("Wrong Command");
                                    print();
                                    break;
                            }
                        }
                }
        );



    }







    @Override
    public void invalidMessage() {
        CLI_Controller.homePage.HomePageView(this.backEnd);
    }

    @Override
    public void handle(ModelUpdate event){
        this.backEnd.getModel().updateModel(event);

        for (Message<ModelEventHandler> e: event.getMessages()){
            e.accept(this);
        }

    }

    @Override
    public void handle(ActivePlayer event){
        CLI_Controller.homePage.HomePageView(this.backEnd);
    }

    @Override
    public void handle(ErrorUpdate event) {
        CLI_Controller.showError(event);
        CLI_Controller.homePage.HomePageView(this.backEnd);
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
        System.out.println(this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getLeaderCard().size());
        if (event.getLeaderCard().getId().equals(this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getLeaderCard().get(0).getId())){
            System.arraycopy(active.toCharArray(), 0, homePage, LeaderCardHomePosActive[0], active.length());
            CLI_Controller.leaderPowerSelector(event.getLeaderCard().getId());
        }else if (event.getLeaderCard().getId().equals(this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getLeaderCard().get(1).getId())){
            System.arraycopy(active.toCharArray(), 0, homePage, LeaderCardHomePosActive[1], active.length());
            CLI_Controller.leaderPowerSelector(event.getLeaderCard().getId());
        }

        CLI_Controller.homePage.HomePageView(this.backEnd);
    }

    @Override
    public void handle(VaticanReport event) {
        CLI_Controller.activatePopeFavor(event.getIndex());
    }

    @Override
    public void handle(VaticanRoutePosition event) {

        if(event.getUsername().equals(this.backEnd.getMyUsername())){
            HomePageView(this.backEnd);
        }
    }

    @Override
    public void handle(ActionTokenPlayed event) {
        CLI_Controller.cls();
        System.out.println("New Action Token played by Lorenzo");
        System.out.println(event.getMessage());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CLI_Controller.homePage.HomePageView(this.backEnd);
    }




}
