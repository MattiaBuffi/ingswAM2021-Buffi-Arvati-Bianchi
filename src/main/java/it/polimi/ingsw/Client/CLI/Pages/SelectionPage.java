package it.polimi.ingsw.Client.CLI.Pages;

import it.polimi.ingsw.Client.CLI.Cli;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.LeaderCard;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ClientMessages.DiscardLeaderCard;
import it.polimi.ingsw.Message.Model.ActivePlayer;
import it.polimi.ingsw.Message.Model.AvailableLeaderCard;
import it.polimi.ingsw.Message.Model.ErrorUpdate;
import it.polimi.ingsw.Message.ModelEventHandler;
import java.util.List;

public class SelectionPage extends ModelEventHandler.Default{

    private static final int[] LeaderSelectionCostPos = {1616,1641,1667,1692};
    private static final int[] LeaderSelectionTypePos = {1882,1907,1933,1958};
    private static final int[] LeaderSelectionEffectPos = {2148,2173,2199,2224};
    private static final int[] LeaderSelectionPVPos = {2560,2585,2611,2636};

    ViewBackEnd backEnd;
    char[] selection;


    private void print(List<LeaderCard> leaderCardSelection){

        Cli.cls();

        this.selection = Cli.readSchematics(11);

        for (int i = 0; i < leaderCardSelection.size(); i++) {
            Cli.LeaderCardInfoExtractor(this.selection, leaderCardSelection, i, LeaderSelectionTypePos, LeaderSelectionPVPos, LeaderSelectionCostPos, LeaderSelectionEffectPos);
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

    }


    public void setup(ViewBackEnd backEnd){
        this.backEnd = backEnd;
        this.backEnd.setEventHandler(this);
    }


    public void SelectionPageView(ViewBackEnd backEnd) {


        this.backEnd = backEnd;
        this.backEnd.setEventHandler(this);


        List<LeaderCard> leaderCardSelection = this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getLeaderCard();

        print(leaderCardSelection);


        Cli.setReadHandler(
                (line)->{
                    try {
                        if (line.isEmpty() && (Integer.parseInt(line) < 1 || Integer.parseInt(line) > leaderCardSelection.size())) {
                            System.out.println("Which Leader Cards do you want to discard 1 to " + leaderCardSelection.size() + " : ");
                            return;
                        }
                        DiscardLeaderCard messageDiscard = new DiscardLeaderCard(backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getLeaderCard().get(Integer.parseInt(line) - 1).getId());
                        this.backEnd.notify(messageDiscard);
                    }catch (NumberFormatException e){
                        Cli.showUpdateMessage("Wrong Input");
                        SelectionPageView(this.backEnd);
                    }
                }

        );


    }

    @Override
    public void invalidMessage() {

    }

    @Override
    public void handle(ErrorUpdate event) {
        Cli.showError(event);
        SelectionPageView(this.backEnd);
    }

    @Override
    public void handle(AvailableLeaderCard event){

        if(event.getLeaderCard().size() == 2){
            Cli.loading.LoadingPageView(this.backEnd);
        }else{
            SelectionPageView(this.backEnd);
        }
    }


}
