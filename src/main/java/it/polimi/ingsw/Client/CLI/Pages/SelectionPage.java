package it.polimi.ingsw.Client.CLI.Pages;

import it.polimi.ingsw.Client.CLI.CLI_Controller;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.LeaderCard;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ClientMessages.DiscardLeaderCard;
import it.polimi.ingsw.Message.Model.ErrorUpdate;
import it.polimi.ingsw.Message.ModelEventHandler;
import java.util.List;
import java.util.Scanner;

public class SelectionPage extends ModelEventHandler.Default{

    private static final int[] LeaderSelectionCostPos = {1616,1641,1666,1691};
    private static final int[] LeaderSelectionTypePos = {1882,1907,1932,1957};
    private static final int[] LeaderSelectionEffectPos = {2148,2173,2198,2223};
    private static final int[] LeaderSelectionPVPos = {2560,2585,2610,2635};

    ViewBackEnd backEnd;

    public void SelectionPageView(ViewBackEnd backEnd) {
        this.backEnd = backEnd;
        this.backEnd.setEventHandler(this);
        CLI_Controller.cls();
        Scanner input = new Scanner(System.in);
        char[] charArray = CLI_Controller.readSchematics(11);

        List<LeaderCard> leaderCardSelection = this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getLeaderCard();
        for (int i = 0; i < 4; i++) {
            CLI_Controller.LeaderCardInfoExtractor(charArray, leaderCardSelection, i, LeaderSelectionTypePos, LeaderSelectionPVPos, LeaderSelectionCostPos, LeaderSelectionEffectPos);
        }
        System.out.println(charArray);
        System.out.println("Which Leader Cards do you want to discard (pos-pos)[pos starts from zero]: ");
        String discardedCard = input.nextLine();
        String[] cardArray = discardedCard.split("-");
        for (String s : cardArray) {
            DiscardLeaderCard messageDiscard = new DiscardLeaderCard(backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getLeaderCard().get(Integer.parseInt(s)).getId());
            this.backEnd.notify(messageDiscard);
        }
        CLI_Controller.homePage.SelectInitialRss(this.backEnd);
    }

    @Override
    public void invalidMessage() {

    }

    @Override
    public void handle(ErrorUpdate event) {
        CLI_Controller.showError(event);
        SelectionPageView(this.backEnd);
    }

}
