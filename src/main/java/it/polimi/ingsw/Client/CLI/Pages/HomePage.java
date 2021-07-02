package it.polimi.ingsw.Client.CLI.Pages;

import it.polimi.ingsw.Client.CLI.Cli;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.LeaderCard;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ClientMessages.ActivateLeaderCard;
import it.polimi.ingsw.Message.ClientMessages.DiscardLeaderCard;
import it.polimi.ingsw.Message.ClientMessages.EndTurn;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.Model.*;
import it.polimi.ingsw.Message.ModelEventHandler;
import java.util.List;

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
    int discardedLeader=0;

    public HomePage(char[] homePage) {
        this.homePage = homePage;
    }


    public void print(){
        Cli.cls();
        //Printing Name of Current Player
        String currentName;
        int spaces;
        if (this.backEnd.getModel().current.getUsername().equals(this.backEnd.getMyUsername())) {
            currentName = "It's your Turn";
            spaces = 31-currentName.length();
            for (int i = 0; i < spaces; i++) {
                currentName = currentName.concat(" ");
            }
        } else {
            currentName = "It's " + this.backEnd.getModel().current.getUsername() + "'s Turn";
            spaces = 31-currentName.length();
            for (int i = 0; i < spaces; i++) {
                currentName = currentName.concat(" ");
            }
        }
        System.arraycopy(currentName.toCharArray(), 0, homePage, TurnPosition, currentName.toCharArray().length);

        int position = this.backEnd.getModel().vaticanRoute.getPlayerFaithPoint(this.backEnd.getMyUsername());
        if ( position != lastPosition){
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

        for (int j = 0; j < Cli.vaticanReport.length; j++){
            if(Cli.vaticanReport[j] == 1 ){
                if(Cli.vaticanReportActive[j]) {
                    switch (j) {
                        case 0 : homePage[HomePopeFavourPosition[j]] = '2';break;
                        case 1 : homePage[HomePopeFavourPosition[j]] = '3';break;
                        case 2 : homePage[HomePopeFavourPosition[j]] = '4';break;
                        default: homePage[HomePopeFavourPosition[j]] = 'X';break;
                    }
                }
            }
        }

        List<LeaderCard> leaderCard = this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getLeaderCard();
        for (int i = 0; i < leaderCard.size(); i++) {
            Cli.LeaderCardInfoExtractor(homePage, leaderCard, i, HomeLeaderType, HomeLeaderPV, HomeLeaderCost, HomeLeaderEffect);
            if(leaderCard.get(i).isActive()){
                System.arraycopy(active.toCharArray(), 0, homePage, LeaderCardHomePosActive[i], active.length());
            }
        }

        if(Cli.leaderActive[1]>0){
            Cli.showLeaderShelf(homePage);
        }

        Cli.UpdateShelf(this.backEnd, homePage);
        Cli.UpdateChest(this.backEnd, homePage);

        System.out.println(homePage);
        System.out.println("Insert Command (Production, CardMarket, RssMarket, View, Activate, Discard, EndTurn, Quit): ");

    }

    public void activate(String line){

        if (line.equals("1") && this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getLeaderCard().size()>0){
            if(!this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getLeaderCard().get(0).isActive()) {
                ActivateLeaderCard messageActivate = new ActivateLeaderCard(this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getLeaderCard().get(0).getId());
                this.backEnd.notify(messageActivate);
            }else{
                Cli.showUpdateMessage("Leader Card Error");
                Cli.homePage.HomePageView(this.backEnd);
            }
        }else if (line.equals("2") && this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getLeaderCard().size()>1){
            if(!this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getLeaderCard().get(1).isActive()) {
                ActivateLeaderCard messageActivate = new ActivateLeaderCard(this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getLeaderCard().get(1).getId());
                this.backEnd.notify(messageActivate);
            }else{
                Cli.showUpdateMessage("Leader Card Error");
                Cli.homePage.HomePageView(this.backEnd);
            }
        }else {
            Cli.showUpdateMessage("Leader Card Error");
            Cli.homePage.HomePageView(this.backEnd);
        }
    }

    public void discard(String line){

        if (line.equals("1") && this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getLeaderCard().size()>0 && !this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getLeaderCard().get(0).isActive() ){
            DiscardLeaderCard messageDiscard = new DiscardLeaderCard(this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getLeaderCard().get(0).getId());
            this.backEnd.notify(messageDiscard);

        }else if (line.equals("2")&& this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getLeaderCard().size()>1 && !this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getLeaderCard().get(1).isActive()){
            DiscardLeaderCard messageDiscard = new DiscardLeaderCard(this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getLeaderCard().get(1).getId());
            this.backEnd.notify(messageDiscard);
        }else {
            Cli.cls();
            Cli.showUpdateMessage("Leader Card Error");
            Cli.homePage.HomePageView(this.backEnd);
        }
    }



    public void HomePageView (ViewBackEnd backEnd){
        this.backEnd = backEnd;
        this.backEnd.setEventHandler(this);

        print();

        Cli.setReadHandler(
                (line)->{
                        line = line.toUpperCase();
                        if(line.equals("ACTIVATE")){
                            System.out.println("Which Leader card do you want to Activate (1/2): ");
                            Cli.setReadHandler(this::activate);
                        }else if (line.equals("DISCARD")){
                            System.out.println("Which Leader card do you want to Discard (1/2): ");
                            Cli.setReadHandler(this::discard);
                        }else {
                            switch (line) {
                                case "PRODUCTION":
                                    Cli.productionPage.ProductionPageView(backEnd);
                                    break;
                                case "CARDMARKET":
                                    Cli.cardMarketPage.CardMarketPageView(backEnd);
                                    break;
                                case "RSSMARKET":
                                    Cli.rssMarketPage.RssMarketPageView(backEnd);
                                    break;
                                case "VIEW":
                                    Cli.viewPage.ViewPageView(backEnd);
                                    break;
                                case "QUIT":
                                    Cli.quitPage.QuitPageView(backEnd);
                                    break;
                                case "ENDTURN":
                                    EndTurn message = new EndTurn();
                                    this.backEnd.notify(message);
                                    break;
                                default:
                                    Cli.showUpdateMessage("Wrong Command");
                                    print();
                                    break;
                            }
                        }
                }
        );



    }







    @Override
    public void invalidMessage() {
        Cli.homePage.HomePageView(this.backEnd);
    }

    @Override
    public void handle(ModelUpdate event){
        for (Message<ModelEventHandler> e: event.getMessages()){
            e.accept(this);
        }
        /*
        CLI_Controller.showUpdateMessage(event.getMessage());
        HomePageView(this.backEnd);*/
    }

    @Override
    public void handle(ActivePlayer event){
        Cli.homePage.HomePageView(this.backEnd);
    }

    @Override
    public void handle(ErrorUpdate event) {
        Cli.showError(event);
        Cli.homePage.HomePageView(this.backEnd);
    }


    @Override
    public void handle(ChestUpdate event) {
        Cli.UpdateChest(backEnd, homePage);
    }

    @Override
    public void handle(ShelfUpdate event) {
        Cli.UpdateShelf(backEnd, homePage);
    }

    @Override
    public void handle(LeaderCardActivation event) {

        if (event.getLeaderCard().getId().equals(this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getLeaderCard().get(0).getId())){
            System.arraycopy(active.toCharArray(), 0, homePage, LeaderCardHomePosActive[0], active.length());
            Cli.leaderPowerSelector(event.getLeaderCard().getId());
        }else if (event.getLeaderCard().getId().equals(this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getLeaderCard().get(1).getId())){
            System.arraycopy(active.toCharArray(), 0, homePage, LeaderCardHomePosActive[1], active.length());
            Cli.leaderPowerSelector(event.getLeaderCard().getId());
        }

        Cli.homePage.HomePageView(this.backEnd);
    }

    @Override
    public void handle(VaticanReport event) {
        Cli.activatePopeFavor(event.getIndex());
    }

    @Override
    public void handle(VaticanRoutePosition event) {
        if(event.getUsername().equals(this.backEnd.getMyUsername())){
            HomePageView(this.backEnd);
        }
    }

    @Override
    public void handle(ActionTokenPlayed event) {
        Cli.showSingleMessage(event, this.backEnd);
    }

    @Override
    public void handle(AvailableLeaderCard event){
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 20; j++) {
                homePage[LeaderCardHomePosDiscard[1-discardedLeader]+j+i*133]= ' ';
            }
        }
        discardedLeader++;
        if(this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getLeaderCard().get(0).isActive()){
            System.arraycopy(active.toCharArray(), 0, homePage, LeaderCardHomePosActive[1-discardedLeader], active.length());
        }
        Cli.homePage.HomePageView(this.backEnd);
    }

    @Override
    public void handle(EndGame event){
        Cli.endPage.EndGameView(this.backEnd, event);
    }


}
