package it.polimi.ingsw.Client.CLI.Pages;

import it.polimi.ingsw.Client.CLI.CLI_Controller;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.LeaderCard;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ClientMessages.DiscardLeaderCard;
import it.polimi.ingsw.Message.Model.AvailableLeaderCard;
import it.polimi.ingsw.Message.Model.ErrorUpdate;
import it.polimi.ingsw.Message.ModelEventHandler;
import java.util.List;
import java.util.Scanner;

public class SelectionPage extends ModelEventHandler.Default{

    private static final int[] LeaderSelectionCostPos = {1616,1641,1667,1692};
    private static final int[] LeaderSelectionTypePos = {1882,1907,1933,1958};
    private static final int[] LeaderSelectionEffectPos = {2148,2173,2199,2224};
    private static final int[] LeaderSelectionPVPos = {2560,2585,2611,2636};

    ViewBackEnd backEnd;
    char[] selection;

    public void SelectionPageView(ViewBackEnd backEnd) {
        this.backEnd = backEnd;
        this.backEnd.setEventHandler(this);
        CLI_Controller.cls();
        Scanner input = new Scanner(System.in);
        this.selection = CLI_Controller.readSchematics(11);
        List<LeaderCard> leaderCardSelection = this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getLeaderCard();
        for (int i = 0; i < leaderCardSelection.size(); i++) {
            CLI_Controller.LeaderCardInfoExtractor(this.selection, leaderCardSelection, i, LeaderSelectionTypePos, LeaderSelectionPVPos, LeaderSelectionCostPos, LeaderSelectionEffectPos);
        }
        if(leaderCardSelection.size()==3){
            for (int i = 0; i <10 ; i++) {
                for (int j = 0; j < 20; j++) {
                    this.selection[1557+j+i*133] = ' ';
                }
            }
        }
        System.out.println(this.selection);
        System.out.println("Which Leader Cards do you want to discard 1 to "+ leaderCardSelection.size() + " : ");
        String discardedCard = input.nextLine();
        DiscardLeaderCard messageDiscard = new DiscardLeaderCard(backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getLeaderCard().get(Integer.parseInt(discardedCard)-1).getId());
        this.backEnd.notify(messageDiscard);
    }

    @Override
    public void invalidMessage() {

    }

    @Override
    public void handle(ErrorUpdate event) {
        CLI_Controller.showError(event);
        SelectionPageView(this.backEnd);
    }

    @Override
    public void handle(AvailableLeaderCard event){
        this.backEnd.getModel().updateModel(event);
        if(event.getLeaderCard().size() == 2){
            //CLI_Controller.homePage.SelectInitialRss(this.backEnd);
            CLI_Controller.loading.LoadingPageView(this.backEnd);
        }else{
            SelectionPageView(this.backEnd);
        }
    }


}
