package it.polimi.ingsw.Client.CLI.Pages;

import it.polimi.ingsw.Client.CLI.Cli;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.LeaderCard;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ClientMessages.DepositResource;
import it.polimi.ingsw.Message.ClientMessages.EndTurn;
import it.polimi.ingsw.Message.ClientMessages.MoveResources;
import it.polimi.ingsw.Message.ClientMessages.TakeResources;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.Model.*;
import it.polimi.ingsw.Message.ModelEventHandler;
import it.polimi.ingsw.Model.LeaderCard.ActivationStrategy.ActivationStrategy;
import it.polimi.ingsw.Model.Marble.*;

import java.util.List;

public class RssMarketPage extends ModelEventHandler.Default {

    private static final int[] RssMarket = {578,593,608,623,1509,1524,1539,1554,2440,2455,2470,2485,3439};
    private static final int[] leaderWhiteBall = {3824, 3957};


    ViewBackEnd backEnd;
    char[] rssMarket;
    //List<Marble> discarded = new ArrayList<>();

    boolean firstRssAvailable = true;

    private int selectedMarble;


    public RssMarketPage(char[] rssMarket) {
        this.rssMarket = rssMarket;
    }


    private void buy(String line){
        try{
            TakeResources messageBuyRss = new TakeResources(Integer.parseInt(line)-1);
            selectedMarble = 0;
            this.backEnd.notify(messageBuyRss);
        }catch (NumberFormatException e){
            Cli.showUpdateMessage("Wrong Input");
            RssMarketPageView(this.backEnd);
        }
    }


    private String origin;
    private String dest;
    private String selectedColor;
    private String whitePosition;

    public void store1(String line){
        origin =  line;
        System.out.println("Where do you want to move this rss? ");
        System.out.println("(value between 1 and 3 [4/5 if you have a storage Leader Active] [1 = first shelf on the top]) : ");
        Cli.setReadHandler(this::store2);
    }

    public void store2(String line){
        dest =  line;
        try {
            MoveResources moveMessage = new MoveResources(Integer.parseInt(origin) - 1, Integer.parseInt(dest) - 1);
            this.backEnd.notify(moveMessage);
        }catch (NumberFormatException e){
            Cli.showUpdateMessage("Wrong Input");
            RssMarketPageView(this.backEnd);
        }
    }

    public void selectColorWhiteMarble(String line){
        selectedColor = line;
        System.out.println("Where do you want to store this rss? ");
        System.out.println("(value between 1 and 3 [4/5 if you have a storage Leader Active] [1 = first shelf on the top]) : ");
        Cli.setReadHandler(this::storeWhiteMarble);
    }

    public void storeWhiteMarble(String line){
        whitePosition = line;
        if (whitePosition.equals("0")) {
            selectedMarble += 1;
            if (selectedMarble == this.backEnd.getModel().resourceMarketBuffer.size()) {
                backEnd.notify(new EndTurn());
                backEnd.setEventHandler(Cli.homePage);
            }
        }else {
            try {
                DepositResource deposit = new DepositResource(Cli.fromStringToColor(selectedColor), Integer.parseInt(whitePosition) - 1);
                this.backEnd.notify(deposit);
            }catch (NumberFormatException e){
                Cli.showUpdateMessage("Wrong Input");
                RssMarketPageView(this.backEnd);
                return;
            }
            if (selectedMarble+1 == this.backEnd.getModel().resourceMarketBuffer.size()) {
                backEnd.notify(new EndTurn());
                backEnd.setEventHandler(Cli.homePage);
            }
        }
    }

    public void RssMarketPageView(ViewBackEnd backEnd){

        this.backEnd = backEnd;
        this.backEnd.setEventHandler(this);
        origin = null;
        dest = null;

        Cli.cls();
        updatePage();
        System.out.println("Insert Command (Buy,MoveRss,EndTurn,Exit): ");

        Cli.setReadHandler(
                (line)->{
                    line = line.toUpperCase();
                    switch (line){
                        case "BUY":
                            System.out.println("Which rss row/column do you want to buy? (value between 1 and 7 [1 = first column on the left, 5 = first row from the top]) : ");
                            Cli.setReadHandler(this::buy);
                            break;
                        case "MOVERSS":
                            Cli.setReadHandler(this::store1);
                            break;
                        case "EXIT":
                            System.out.println("redirecting to Home..");
                            Cli.homePage.HomePageView(backEnd);
                            break;
                        case "ENDTURN":
                            EndTurn message = new EndTurn();
                            this.backEnd.notify(message);
                            break;
                        default:
                            System.out.println("Wrong Command, please insert a real command");
                            Cli.rssMarketPage.RssMarketPageView(this.backEnd);
                            break;
                    }
                }
        );
    }

    private String getMarble(int position){
        return Cli.getColorStringAvailableRss(this.backEnd.getModel().resourceMarketBuffer.get(position));
    }

    public void rssHandler(){

        if(this.backEnd.getModel().resourceMarketBuffer.get(selectedMarble).getColor() == Marble.Color.WHITE){
            SelectableMarble marble = (SelectableMarble)this.backEnd.getModel().resourceMarketBuffer.get(selectedMarble);
            if(marble.getSelectableColors().size() == 1) {
                System.out.println("You take a W Marble, which color do you want to take? " + Cli.getColorString(marble.getSelectableColors().get(0)));
            }else {
                System.out.println("You take a W Marble, which color do you want to take? "
                        + Cli.getColorString(marble.getSelectableColors().get(0)) + " or "
                        + Cli.getColorString(marble.getSelectableColors().get(1)));
            }
            Cli.setReadHandler(this::selectColorWhiteMarble);
            return;
        } else {
                System.out.println("You take a " + getMarble(selectedMarble) + " rss, where do you want to put it? 1 to 3 [4/5 if you have a leader] - 0 if you want to discard this rss. ");
        }

        Cli.setReadHandler(
                (line)->{
                    if(this.backEnd.getModel().resourceMarketBuffer.size()>0) {
                        if (line.isEmpty()) {
                            System.out.println("You take a " + getMarble(selectedMarble) + " rss, where do you want to put it? 1 to 3 [4/5 if you have a leader] - 0 if you want to discard this rss");
                            return;
                        }
                            try {
                                if (Integer.parseInt(line) < 0 || Integer.parseInt(line) > 5) {
                                    System.out.println("You take a " + getMarble(selectedMarble) + " rss, where do you want to put it? 1 to 3 [4/5 if you have a leader] - 0 if you want to discard this rss");
                                    return;
                                }
                            }catch (NumberFormatException e){
                                Cli.showUpdateMessage("Wrong Input");
                                RssMarketPageView(this.backEnd);
                                return;
                            }

                            if (line.equals("0")) {
                                selectedMarble += 1;

                                if (selectedMarble == this.backEnd.getModel().resourceMarketBuffer.size()) {
                                    backEnd.notify(new EndTurn());
                                    backEnd.setEventHandler(Cli.homePage);
                                    return;
                                }
                                System.out.println("You take a " + getMarble(selectedMarble) + " rss, where do you want to put it? 1 to 3 [4/5 if you have a leader] - 0 if you want to discard this rss. ");
                            } else {
                                try {
                                    DepositResource deposit = new DepositResource(this.backEnd.getModel().resourceMarketBuffer.get(0).getColor(), Integer.parseInt(line) - 1);
                                    this.backEnd.notify(deposit);
                                }catch (NumberFormatException e){
                                    Cli.showUpdateMessage("Wrong Input");
                                    RssMarketPageView(this.backEnd);
                                    return;
                                }
                                if (selectedMarble + 1 == this.backEnd.getModel().resourceMarketBuffer.size()) {
                                    backEnd.notify(new EndTurn());
                                    backEnd.setEventHandler(Cli.homePage);
                                }
                            }
                    }else{
                        Cli.rssMarketPage.RssMarketPageView(this.backEnd);
                    }

                }
        );

    }

    @Override
    public void invalidMessage() {}

    @Override
    public void handle(ChestUpdate event) {

        Cli.UpdateChest(backEnd, rssMarket);
    }

    @Override
    public void handle(ShelfUpdate event) {

        Cli.UpdateShelf(backEnd, rssMarket);
    }

    @Override
    public void handle(ErrorUpdate event) {
        Cli.showError(event);
        RssMarketPageView(this.backEnd);
    }

    @Override
    public void handle(ActivePlayer event) {
        Cli.homePage.HomePageView(this.backEnd);
    }

    @Override
    public void handle(ModelUpdate event){
        for (Message<ModelEventHandler> message: event.getMessages()) {
            message.accept(this);
        }
        if(this.backEnd.getModel().resourceMarketBuffer.size() > 0 && this.backEnd.getModel().current.getUsername().equals(this.backEnd.getMyUsername())){
            rssHandler();
        }
        /*
        CLI_Controller.showUpdateMessage(event.getMessage());
        RssMarketPageView(this.backEnd);*/
    }

    @Override
    public void handle(ActionTokenPlayed event) {
        Cli.showSingleMessage(event, this.backEnd);
    }

    public void updatePage(){
        for (int i = 0; i < 4; i++){
            List<Marble> rssColumn = this.backEnd.getModel().resourceMarket.get(i);
            for (int j=0; j<rssColumn.size(); j++) {
                Marble.Color color = rssColumn.get(j).getColor();
                switch (color) {
                    case YELLOW:
                        rssMarket[RssMarket[j*4+i]] = 'Y';
                        break;
                    case BLUE:
                        rssMarket[RssMarket[j*4+i]] = 'B';
                        break;
                    case GREY:
                        rssMarket[RssMarket[j*4+i]] = 'G';
                        break;
                    case PURPLE:
                        rssMarket[RssMarket[j*4+i]] = 'P';
                        break;
                    case RED:
                        rssMarket[RssMarket[j*4+i]] = 'R';
                        break;
                    case WHITE:
                        rssMarket[RssMarket[j*4+i]] = 'W';
                        break;
                }
            }
        }
        Marble bonusMarble = this.backEnd.getModel().resourceMarket.getBonusMarble();
        Marble.Color bonusColor = bonusMarble.getColor();
        switch (bonusColor) {
            case YELLOW:
                rssMarket[RssMarket[12]] = 'Y';
                break;
            case BLUE:
                rssMarket[RssMarket[12]] = 'B';
                break;
            case GREY:
                rssMarket[RssMarket[12]] = 'G';
                break;
            case PURPLE:
                rssMarket[RssMarket[12]] = 'P';
                break;
            case RED:
                rssMarket[RssMarket[12]] = 'R';
                break;
            case WHITE:
                rssMarket[RssMarket[12]] = 'W';
                break;
        }



        if(Cli.leaderActive[1]>0){
            Cli.showLeaderShelf(rssMarket);
        }

        Cli.UpdateShelf(this.backEnd, rssMarket);
        Cli.UpdateChest(this.backEnd, rssMarket);

        if(Cli.leaderActive[2]>0){
            List<LeaderCard> card = this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getLeaderCard();
            int i = 0;
            for (LeaderCard leaderCard : card) {
                if(leaderCard.getType() == ActivationStrategy.Type.MARBLE_CONVERSION && leaderCard.isActive()){
                    Marble.Color colorEffect = leaderCard.getColor();
                    String colorEffected = "W -> " + Cli.getColorString(colorEffect);
                    System.arraycopy(colorEffected.toCharArray(), 0, rssMarket, leaderWhiteBall[i], colorEffected.toCharArray().length);
                    i++;
                }
            }
        }
        System.out.println(rssMarket);
    }

    @Override
    public void handle(VaticanReport event) {
        Cli.activatePopeFavor(event.getIndex());
    }

}
