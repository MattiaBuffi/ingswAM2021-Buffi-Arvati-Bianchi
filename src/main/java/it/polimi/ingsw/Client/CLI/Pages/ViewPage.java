package it.polimi.ingsw.Client.CLI.Pages;

import it.polimi.ingsw.Client.CLI.CLI_Controller;
import it.polimi.ingsw.Client.ModelData.Player;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ClientMessages.EndTurn;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.Model.*;
import it.polimi.ingsw.Message.ModelEventHandler;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.ResourceList;
import java.util.List;
import java.util.Scanner;

public class ViewPage extends ModelEventHandler.Default {

    private static final int[] BigFaithCellPosition ={1739,1745,1751,1086,421,427,433,439,445,451,1116,1781,1787,1793,1799,1805,1811,1146,481,487,493,499,505,511,517,
            1742,1748,1754,1089,424,430,436,442,448,454,1119,1784,1790,1796,1802,1808,1814,1149,484,490,496,502,508,514,520,
            2005,2011,2017,1352,687,693,699,705,711,717,1382,2047,2053,2059,2065,2071,2077,1412,747,753,759,765,771,777,783,
            2008,2014,2020,1355,690,696,702,708,714,717,1385,2050,2056,2062,2068,2074,2080,1415,750,756,762,768,774,780,786};
    private static final int[] BigFaithPlayerNamePos = {2802,2822,2842,2862};
    private static final int[] BigFaithPlayerRssPos = {3070,3090,3110,3130};
    private static final int[] BigFaithPlayerProdPos = {3203,3223,3243,3263};
    private static final int[] BigFaithPlayerPVPos = {3336,3356,3376,3396};
    private static final int[] BigFaithPopeFavourPosition = {1235,1266,1302};

    ViewBackEnd backEnd;
    int totalDevCard;


    public void print(){
        CLI_Controller.cls();

        char[] bigView = CLI_Controller.readSchematics(10);

        List<Player> users = this.backEnd.getModel().players;
        for (int i = 0; i < users.size(); i++) {
            Player user = users.get(i);
            System.arraycopy((((i+1) + " " + user.getUsername()).toCharArray()), 0, bigView, BigFaithPlayerNamePos[i], (((i+1)+" "+ user.getUsername()).toCharArray()).length);

            totalDevCard = user.getProductions().get(0).size()+user.getProductions().get(1).size()+user.getProductions().get(2).size();
            if(totalDevCard>0)
                System.arraycopy(Integer.toString(totalDevCard).toCharArray(), 0, bigView, BigFaithPlayerProdPos[i], Integer.toString(totalDevCard).toCharArray().length);
            else
                System.arraycopy("0".toCharArray(), 0, bigView, BigFaithPlayerProdPos[i], "0".toCharArray().length);

            ResourceList playerChest = this.backEnd.getModel().getPlayer(user.getUsername()).getChest();
            List<Marble> playerChestMarble = playerChest.getAllMarble();
            char[] playerChestRss;
            if(playerChestMarble.size()>0)
                playerChestRss = CLI_Controller.getColorStringFromMarble(playerChestMarble).toCharArray();
            else
                playerChestRss = "0B 0Y 0P 0G".toCharArray();
            System.arraycopy(playerChestRss, 0, bigView, BigFaithPlayerRssPos[i], playerChestRss.length);


            System.arraycopy((user.getVictoryPoints() + " VP").toCharArray(), 0, bigView, BigFaithPlayerPVPos[i], (user.getVictoryPoints() + " VP").toCharArray().length);
            bigView[BigFaithCellPosition[this.backEnd.getModel().vaticanRoute.getPlayerFaithPoint(user.getUsername()) + i * 25]] = ((i+1) + " ").toCharArray()[0];
        }

        if(users.size()==1){
            bigView[BigFaithCellPosition[this.backEnd.getModel().vaticanRoute.getPlayerFaithPoint("cpu") + 25]] = 'L';
        }


        for (int j = 0; j < CLI_Controller.vaticanReport.length; j++){
            if(CLI_Controller.vaticanReport[j] == 1 ){
                if(CLI_Controller.vaticanReportActive[j]) {
                    bigView[BigFaithPopeFavourPosition[j]] = 'O';
                    bigView[BigFaithPopeFavourPosition[j]+1] = 'K';
                }else{
                    bigView[BigFaithPopeFavourPosition[j]] = 'X';
                }
            }
        }

        System.out.println(bigView);
        System.out.println("Insert Command (Exit,EndTurn): ");
    }


    public void ViewPageView(ViewBackEnd backEnd){
        this.backEnd = backEnd;
        this.backEnd.setEventHandler(this);

        print();

        CLI_Controller.setReadHandler(
                (line)->{
                    line = line.toUpperCase();
                    if (line.equals("EXIT")) {
                        System.out.println("redirecting to Home..");
                    }else if (line.equals("ENDTURN")){
                        EndTurn message = new EndTurn();
                        this.backEnd.notify(message);
                        CLI_Controller.homePage.HomePageView(backEnd);
                    } else {
                        System.out.println("Wrong Command, but you are very lucky, i'm redirecting you to Home anyway..");
                    }
                    CLI_Controller.homePage.HomePageView(backEnd);
                }
        );


    }

    @Override
    public void invalidMessage() {

    }

    @Override
    public void handle(ErrorUpdate event) {
        CLI_Controller.showError(event);
        ViewPageView(this.backEnd);
    }

    @Override
    public void handle(ModelUpdate event){
        for (Message<ModelEventHandler> e: event.getMessages()){
            e.accept(this);
        }
        /*
        CLI_Controller.showUpdateMessage(event.getMessage());
        ViewPageView(this.backEnd);*/
    }

    @Override
    public void handle(ActivePlayer event){
        CLI_Controller.homePage.HomePageView(this.backEnd);
    }

    @Override
    public void handle(ActionTokenPlayed event) {
        CLI_Controller.showSingleMessage(event, this.backEnd);
    }

    @Override
    public void handle(VaticanReport event) {
        CLI_Controller.activatePopeFavor(event.getIndex());
    }
}
