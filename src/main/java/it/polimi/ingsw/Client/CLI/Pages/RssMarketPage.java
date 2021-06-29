package it.polimi.ingsw.Client.CLI.Pages;

import it.polimi.ingsw.Client.CLI.CLI_Controller;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RssMarketPage extends ModelEventHandler.Default {

    private static final int[] RssMarket = {578,593,608,623,1509,1524,1539,1554,2440,2455,2470,2485,3439};
    private static final int[] leaderWhiteBall = {3824, 3957};


    ViewBackEnd backEnd;
    char[] rssMarket;
    List<Marble> discarded = new ArrayList<>();

    boolean firstRssAvailable = true;

    public RssMarketPage(char[] rssMarket) {
        this.rssMarket = rssMarket;
    }


    private void buy(String line){
        TakeResources messageBuyRss = new TakeResources(Integer.parseInt(line)-1);
        this.backEnd.notify(messageBuyRss);
    }


    private String origin;
    private String dest;

    public void store1(String line){
        origin =  line;
        System.out.println("Where do you want to move this rss? ");
        System.out.println("(value between 1 and 3 [4/5 if you have a storage Leader Active] [1 = first shelf on the top]) : ");
        CLI_Controller.setReadHandler(this::store2);
    }

    public void store2(String line){
        dest =  line;
        MoveResources moveMessage = new MoveResources(Integer.parseInt(origin)-1, Integer.parseInt(dest)-1);
        this.backEnd.notify(moveMessage);
    }


    public void RssMarketPageView(ViewBackEnd backEnd){

        this.backEnd = backEnd;
        this.backEnd.setEventHandler(this);
        origin = null;
        dest = null;

        CLI_Controller.cls();
        updatePage();
        System.out.println("Insert Command (Buy,MoveRss,EndTurn,Exit): ");

        CLI_Controller.setReadHandler(
                (line)->{
                    line = line.toUpperCase();
                    switch (line){
                        case "BUY":
                            System.out.println("Which rss row/column do you want to buy? (value between 1 and 7 [1 = first column on the left, 5 = first row from the top]) : ");
                            CLI_Controller.setReadHandler(this::buy);
                            break;
                        case "MOVERSS":
                            CLI_Controller.setReadHandler(this::store1);
                            break;
                        case "EXIT":
                            System.out.println("redirecting to Home..");
                            CLI_Controller.homePage.HomePageView(backEnd);
                            break;
                        case "ENDTURN":
                            EndTurn message = new EndTurn();
                            this.backEnd.notify(message);
                            break;
                        default:
                            System.out.println("Wrong Command, please insert a real command");
                            CLI_Controller.rssMarketPage.RssMarketPageView(this.backEnd);
                            break;
                    }
                }
        );


    }




    public void rssHandler(){

        String rssAvailable = CLI_Controller.getColorStringAvailableRss(this.backEnd.getModel().resourceMarketBuffer.get(0));
        System.out.println("You take a " + rssAvailable + " rss, where do you want to put it? 1 to 3 [4/5 if you have a leader] - 0 if you want to discard this rss");

        CLI_Controller.setReadHandler(
                (line)->{
                    if(this.backEnd.getModel().resourceMarketBuffer.size()>0) {
                        if (!discarded.contains(this.backEnd.getModel().resourceMarketBuffer.get(0))) {

                            if (line.isEmpty()) {
                                System.out.println("You take a " + rssAvailable + " rss, where do you want to put it? 1 to 3 [4/5 if you have a leader] - 0 if you want to discard this rss");
                                return;
                            }
                            if (Integer.parseInt(line) < 0 || Integer.parseInt(line) > 5) {
                                System.out.println("You take a " + rssAvailable + " rss, where do you want to put it? 1 to 3 [4/5 if you have a leader] - 0 if you want to discard this rss");
                                return;
                            }
                            if (line.equals("0")) {
                                discarded.add(this.backEnd.getModel().resourceMarketBuffer.get(0));
                                rssHandler();
                            } else {
                                DepositResource deposit = new DepositResource(this.backEnd.getModel().resourceMarketBuffer.get(0).getColor(), Integer.parseInt(line) - 1);
                                this.backEnd.notify(deposit);
                            }
                            /*
                            CLI_Controller.setReadHandler(
                                    (checkedLine)->{
                                        if (checkedLine.isEmpty()) {
                                            System.out.println("You take a " + rssAvailable + " rss, where do you want to put it? 1 to 3 [4/5 if you have a leader] - 0 if you want to discard this rss");
                                            return;
                                        }
                                        if (Integer.parseInt(checkedLine) < 0 || Integer.parseInt(checkedLine) > 5) {
                                            System.out.println("You take a " + rssAvailable + " rss, where do you want to put it? 1 to 3 [4/5 if you have a leader] - 0 if you want to discard this rss");
                                            return;
                                        }
                                        if (checkedLine.equals("0")) {
                                            discarded.add(this.backEnd.getModel().resourceMarketBuffer.get(0));
                                            rssHandler();
                                        } else {
                                            DepositResource deposit = new DepositResource(this.backEnd.getModel().resourceMarketBuffer.get(0).getColor(), Integer.parseInt(checkedLine) - 1);
                                            this.backEnd.notify(deposit);
                                        }
                                    }
                            );*/
                        }
                        /*
                        else if (discarded.size() != this.backEnd.getModel().resourceMarketBuffer.size()) {


                            String rssAvailable = CLI_Controller.getColorStringAvailableRss(this.backEnd.getModel().resourceMarketBuffer.get(discarded.size()));
                            System.out.println("You take a " + rssAvailable + " rss, where do you want to put it? 1 to 3 [4/5 if you have a leader] - 0 if you want to discard this rss");


                            CLI_Controller.setReadHandler(
                                    (checkedLine)->{

                                        if (checkedLine.isEmpty()) {
                                            System.out.println("You take a " + rssAvailable + " rss, where do you want to put it? 1 to 3 [4/5 if you have a leader] - 0 if you want to discard this rss");
                                            return;
                                        }
                                        if (Integer.parseInt(checkedLine) < 0 || Integer.parseInt(checkedLine) > 5) {
                                            System.out.println("You take a " + rssAvailable + " rss, where do you want to put it? 1 to 3 [4/5 if you have a leader] - 0 if you want to discard this rss");
                                            return;
                                        }
                                        if (checkedLine.equals("0")) {
                                            discarded.add(this.backEnd.getModel().resourceMarketBuffer.get(discarded.size()));
                                            rssHandler();
                                        } else {
                                            DepositResource deposit = new DepositResource(this.backEnd.getModel().resourceMarketBuffer.get(discarded.size()).getColor(), Integer.parseInt(checkedLine) - 1);
                                            this.backEnd.notify(deposit);
                                        }

                                    }
                            );


                        }*/
                    }else{
                        CLI_Controller.rssMarketPage.RssMarketPageView(this.backEnd);
                    }



                }
        );


        /*
        if(this.backEnd.getModel().resourceMarketBuffer.size()>0) {
            if (!discarded.contains(this.backEnd.getModel().resourceMarketBuffer.get(0))) {
                String rssAvailable = CLI_Controller.getColorStringAvailableRss(this.backEnd.getModel().resourceMarketBuffer.get(0));
                System.out.println("You take a " + rssAvailable + " rss, where do you want to put it? 1 to 3 [4/5 if you have a leader] - 0 if you want to discard this rss");

                String selectedShelf = CLI_Controller.scanner.nextLine();

                while (Integer.parseInt(selectedShelf) < 0 || Integer.parseInt(selectedShelf) > 5) {
                    System.out.println("You take a " + rssAvailable + " rss, where do you want to put it? 1 to 3 [4/5 if you have a leader] - 0 if you want to discard this rss");
                    selectedShelf =CLI_Controller.scanner.nextLine();
                }
                if (selectedShelf.equals("0")) {
                    discarded.add(this.backEnd.getModel().resourceMarketBuffer.get(0));
                    rssHandler();
                } else {
                    DepositResource deposit = new DepositResource(this.backEnd.getModel().resourceMarketBuffer.get(0).getColor(), Integer.parseInt(selectedShelf) - 1);
                    this.backEnd.notify(deposit);
                }
            } else if (discarded.size() != this.backEnd.getModel().resourceMarketBuffer.size()) {
                String rssAvailable = CLI_Controller.getColorStringAvailableRss(this.backEnd.getModel().resourceMarketBuffer.get(discarded.size()));
                System.out.println("You take a " + rssAvailable + " rss, where do you want to put it? 1 to 3 [4/5 if you have a leader] - 0 if you want to discard this rss");
                String selectedShelf = CLI_Controller.scanner.nextLine();
                while (selectedShelf.equals("")) {
                    System.out.println("You take a " + rssAvailable + " rss, where do you want to put it? 1 to 3 [4/5 if you have a leader] - 0 if you want to discard this rss");
                    selectedShelf = CLI_Controller.scanner.nextLine();
                }
                while (Integer.parseInt(selectedShelf) < 0 || Integer.parseInt(selectedShelf) > 5) {
                    System.out.println("You take a " + rssAvailable + " rss, where do you want to put it? 1 to 3 [4/5 if you have a leader] - 0 if you want to discard this rss");
                    selectedShelf =CLI_Controller.scanner.nextLine();
                }
                if (selectedShelf.equals("0")) {
                    discarded.add(this.backEnd.getModel().resourceMarketBuffer.get(discarded.size()));
                    rssHandler();
                } else {
                    DepositResource deposit = new DepositResource(this.backEnd.getModel().resourceMarketBuffer.get(discarded.size()).getColor(), Integer.parseInt(selectedShelf) - 1);
                    this.backEnd.notify(deposit);
                }
            }
        }else{
            CLI_Controller.rssMarketPage.RssMarketPageView(this.backEnd);
        }


         */
    }












    @Override
    public void invalidMessage() {}

    @Override
    public void handle(ChestUpdate event) {
        backEnd.getModel().updateModel(event);
        CLI_Controller.UpdateChest(backEnd, rssMarket);
    }

    @Override
    public void handle(ShelfUpdate event) {
        backEnd.getModel().updateModel(event);
        CLI_Controller.UpdateShelf(backEnd, rssMarket);
    }

    @Override
    public void handle(ResourceMarketUpdate event) {
        this.backEnd.getModel().updateModel(event);
    }

    @Override
    public void handle(ResourceMarketExtra event) {
        this.backEnd.getModel().updateModel(event);
    }

    @Override
    public void handle(ErrorUpdate event) {
        CLI_Controller.showError(event);
        RssMarketPageView(this.backEnd);
    }

    @Override
    public void handle(ModelUpdate event){
        this.backEnd.getModel().updateModel(event);
        for (Message<ModelEventHandler> message: event.getMessages()) {
            if (message instanceof ActivePlayer) {
                this.discarded.clear();
                CLI_Controller.homePage.HomePageView(this.backEnd);
            }
        }

        if(this.discarded.size() == this.backEnd.getModel().resourceMarketBuffer.size()){
            CLI_Controller.rssMarketPage.RssMarketPageView(this.backEnd);
        }else{
            rssHandler();
        }

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

        CLI_Controller.UpdateShelf(this.backEnd, rssMarket);
        CLI_Controller.UpdateChest(this.backEnd, rssMarket);

        if(CLI_Controller.leaderActive[1]>0){
            CLI_Controller.showLeaderShelf(rssMarket);
        }

        if(CLI_Controller.leaderActive[2]>0){
            List<LeaderCard> card = this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getLeaderCard();
            int i = 0;
            for (LeaderCard leaderCard : card) {
                if(leaderCard.getType() == ActivationStrategy.Type.MARBLE_CONVERSION){
                    Marble.Color colorEffect = leaderCard.getColor();
                    String colorEffected = "W -> " + CLI_Controller.getColorString(colorEffect);
                    System.arraycopy(colorEffected.toCharArray(), 0, rssMarket, leaderWhiteBall[i], colorEffected.toCharArray().length);
                    i++;
                }

            }
        }
        System.out.println(rssMarket);
    }
    



}
