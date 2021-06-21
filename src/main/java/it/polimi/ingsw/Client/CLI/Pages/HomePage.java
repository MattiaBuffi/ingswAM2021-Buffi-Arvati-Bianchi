package it.polimi.ingsw.Client.CLI.Pages;

import it.polimi.ingsw.Client.CLI.CLI_Controller;
import it.polimi.ingsw.Client.ModelData.Player;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.LeaderCard;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.Shelf;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ClientMessages.ActivateLeaderCard;
import it.polimi.ingsw.Message.ClientMessages.DiscardLeaderCard;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.ResourceList;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class HomePage {

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

    public HomePage(ViewBackEnd backEnd, char[] homePage) {
        this.backEnd = backEnd;
        this.homePage = homePage;
    }

    public void HomePageView() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);

        //Printing Name of Current Player
        String customName = backEnd.getModel().current.getUsername() + "'s Turn";
        System.arraycopy(customName.toCharArray(), 0, homePage, TurnPosition, customName.toCharArray().length);

        //Printing Rank
        for (int i = 0; i < backEnd.getModel().players.size(); i++) {
            Player user = backEnd.getModel().players.get(i);
            String posName = "" + (i + 1);
            char[] posNameArray = posName.toCharArray();
            homePage[(19 + i) * 133 + 103] = posNameArray[0];
            homePage[(19 + i) * 133 + 104] = '.';
            System.arraycopy(user.getUsername().toCharArray(), 0, homePage, HomeRankPosition[i], user.getUsername().toCharArray().length);
        }

        //Printing Shelves
        List<Shelf> playerShelf = backEnd.getModel().current.getShelves();
        CLI_Controller.ShelfExtractor(homePage, playerShelf);

        //Printing Chest
        ResourceList playerChest = backEnd.getModel().current.getChest();
        List<Marble> playerChestMarble = playerChest.getAll();
        String[] playerChestRss = CLI_Controller.getColorStringFromMarble(playerChestMarble).split(" ");
        for (int i = 0; i < playerChestRss.length; i++) {
            System.arraycopy(playerChestRss[i].toCharArray(), 0, homePage, RssPosition[i+6], playerChestRss[i].toCharArray().length);
        }


        int position = backEnd.getModel().current.getFaithPoints();
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

        List<LeaderCard> leaderCard = backEnd.getModel().current.getLeaderCard();
        for (int i = 0; i < 2; i++) {
            CLI_Controller.LeaderCardInfoExtractor(homePage, leaderCard, i, HomeLeaderType, HomeLeaderPV, HomeLeaderCost);
        }

        System.out.println(homePage);

        System.out.println("Insert Command (Produce, CardMarket, RssMarket, View, Activate, Discard, Quit): ");
        String command = input.nextLine().toUpperCase();
        if(command.equals("ACTIVATE")){

            System.out.println("Which Leader card do you want to Activate (1/2): ");
            String activateLeader = input.nextLine();

            if (activateLeader.equals("1")){
                ActivateLeaderCard messageActivate = new ActivateLeaderCard(backEnd.getModel().current.getLeaderCard().get(0).getId());
                backEnd.notify(messageActivate);
                //Clausola attivazione possibile
                System.arraycopy(active.toCharArray(), 0, homePage, LeaderCardHomePosActive[0], active.length());
            }else if (activateLeader.equals("2")){
                ActivateLeaderCard messageActivate = new ActivateLeaderCard(backEnd.getModel().current.getLeaderCard().get(1).getId());
                backEnd.notify(messageActivate);
                //Clausola attivazione possibile
                System.arraycopy(active.toCharArray(), 0, homePage, LeaderCardHomePosActive[1], active.length());
            }
        }else if (command.equals("DISCARD")){

            System.out.println("Which Leader card do you want to Discard (1/2): ");
            String discardLeader = input.nextLine();
            if (discardLeader.equals("1")){
                DiscardLeaderCard messageDiscard = new DiscardLeaderCard(backEnd.getModel().current.getLeaderCard().get(0).getId());
                backEnd.notify(messageDiscard);
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 20; j++) {
                        homePage[LeaderCardHomePosDiscard[0]+j+i*133]= ' ';
                    }
                }
            }else if (discardLeader.equals("2")){
                DiscardLeaderCard messageDiscard = new DiscardLeaderCard(backEnd.getModel().current.getLeaderCard().get(1).getId());
                backEnd.notify(messageDiscard);
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 20; j++) {
                        homePage[LeaderCardHomePosDiscard[1]+j+i*133]= ' ';
                    }
                }
            }
        }else {
            CLI_Controller.scene = command;
        }

    }
}
