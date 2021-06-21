package it.polimi.ingsw.Client.CLI.Pages;

import it.polimi.ingsw.Client.CLI.CLI_Controller;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.LeaderCard;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ClientMessages.DiscardLeaderCard;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class SelectionPage {

    private static final int[] LeaderSelectionCostPos = {1616,1641,1666,1691};
    private static final int[] LeaderSelectionTypePos = {1882,1907,1932,1957};
    private static final int[] LeaderSelectionEffectPos = {2148,2173,2198,2223};
    private static final int[] LeaderSelectionPVPos = {2560,2585,2610,2635};

    ViewBackEnd backEnd;

    public SelectionPage(ViewBackEnd backEnd) {
        this.backEnd = backEnd;
    }

    public void SelectionPageView() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        char[] charArray = CLI_Controller.readSchematics(11);
        List<LeaderCard> leaderCardSelection = backEnd.getModel().current.getLeaderCard();
        for (int i = 0; i < 4; i++) {
            CLI_Controller.LeaderCardInfoExtractor(charArray, leaderCardSelection, i, LeaderSelectionTypePos, LeaderSelectionPVPos, LeaderSelectionCostPos);
        }
        System.out.println(charArray);
        System.out.println("Which Leader Cards do you want to discard (pos-pos)[pos starts from zero]: ");
        String discardedCard = input.nextLine();
        String[] cardArray = discardedCard.split("-");
        for (String s : cardArray) {
            DiscardLeaderCard messageDiscard = new DiscardLeaderCard(backEnd.getModel().current.getLeaderCard().get(Integer.parseInt(s)).getId());
            backEnd.notify(messageDiscard);
        }
        CLI_Controller.scene = "HOME";
    }
}
