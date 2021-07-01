package it.polimi.ingsw.Client.CLI.Pages;

import it.polimi.ingsw.Client.CLI.Cli;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.DevelopmentCardData;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.LeaderCard;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ClientMessages.*;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.Model.*;
import it.polimi.ingsw.Message.ModelEventHandler;
import it.polimi.ingsw.Model.LeaderCard.ActivationStrategy.ActivationStrategy;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.ProductionCard.DevelopmentCard;

import java.util.List;

public class ProductionPage extends ModelEventHandler.Default{

    private static final int[] singleCardPosition = {64, 106, 182, 171};
    private static final int[] ProductionPosition = {2451,2475,2499,1786,1810,1834,1121,1145,1169};
    private static final int[] leaderDevelopmentPosition = {707,1505};
    private static final int singleLeaderDevPosition = 36;

    ViewBackEnd backEnd;
    char[] production;

    public ProductionPage(char[] production) {
        this.production = production;
    }

    private void print(){
        Cli.cls();

        char[] customCard;
        customCard = Cli.readSchematics(12);
        int row;
        int column = 0;

        List<List<DevelopmentCardData>> playerProductions = this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getProductions();
        DevelopmentCard.Color color;
        List<Marble> requireList;
        String requireString;
        List<Marble> produceList;
        String produceString;
        String vp;


        for (List<DevelopmentCardData> productionColumn : playerProductions) {
            row = 0;
            for (DevelopmentCardData devCard : productionColumn) {

                color = devCard.color;
                System.arraycopy((Cli.getDevColor(color)).toCharArray(), 0, customCard, singleCardPosition[3], (Cli.getDevColor(color)).toCharArray().length);

                requireList = devCard.require.getAllMarble();
                requireString = Cli.getColorStringFromMarble(requireList);
                System.arraycopy(("Req: " + requireString).toCharArray(), 0, customCard, singleCardPosition[0], ("Req: " + requireString).toCharArray().length);

                produceList = devCard.produce.getAllMarble();
                produceString = Cli.getColorStringFromMarble(produceList);
                System.arraycopy(("Prod: " + produceString).toCharArray(), 0, customCard, singleCardPosition[1], ("Prod: " + produceString).toCharArray().length);

                vp = Integer.toString(devCard.victoryPoints);
                System.arraycopy(vp.toCharArray(), 0, customCard, singleCardPosition[2], vp.toCharArray().length);


                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 20; j++) {
                        production[ProductionPosition[column+row*3]+j+i*133] = customCard[i*21+j];
                    }
                }
                row++;
            }
            column++;
        }

        if(Cli.leaderActive[1]>0){
            Cli.showLeaderShelf(production);
        }

        Cli.UpdateShelf(this.backEnd, production);
        Cli.UpdateChest(this.backEnd, production);

        if(Cli.leaderActive[3]>0){
            List<LeaderCard> card = this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getLeaderCard();
            int i = 0;
            for (LeaderCard leaderCard : card) {
                if(leaderCard.getType() == ActivationStrategy.Type.EXTRA_PRODUCTION && leaderCard.isActive()){
                    char[] scheme = Cli.readSchematics(14);
                    Marble.Color colorEffect = leaderCard.getColor();
                    String colorEffected = Cli.getColorString(colorEffect);
                    scheme[singleLeaderDevPosition] = colorEffected.charAt(0);
                    for (int j = 0; j < 7; j++){
                        for (int k = 0; k < 10; k++){
                            production[leaderDevelopmentPosition[i]+k+133*j] = scheme[11*j+k];
                        }
                    }
                    i++;
                }
            }
        }

        System.out.println(production);
        System.out.println("Insert Command (Produce,Exit, EndTurn): ");
    }


    private void produce(String line){
            Production(line, backEnd);
    }

    private void basic(String line){
        String[] basicProdArray = line.split("-");
        Marble.Color[] prodColor = new Marble.Color[3];
        for (int i = 0; i < basicProdArray.length; i++) {
            prodColor[i] = Cli.fromStringToColor(basicProdArray[i]);
        }
        BasicProduction message = new BasicProduction(prodColor[0], prodColor[1], prodColor[2]);
        backEnd.notify(message);
    }

    private void leaderProd(String line){
        System.err.println(backEnd.getModel().getPlayer(backEnd.getMyUsername()).getLeaderCard().get(0).getId());
        System.err.println(Cli.fromStringToColor(line));
        if(backEnd.getModel().getPlayer(backEnd.getMyUsername()).getLeaderCard().get(0).isActive()) {
            LeaderCardProduction message = new LeaderCardProduction(backEnd.getModel().getPlayer(backEnd.getMyUsername()).getLeaderCard().get(0).getId(), Cli.fromStringToColor(line));
            backEnd.notify(message);
        }else{
            Cli.showUpdateMessage("Your leader is not active");
            ProductionPageView(this.backEnd);
        }
    }

    private void leaderProd2(String line){
        if(backEnd.getModel().getPlayer(backEnd.getMyUsername()).getLeaderCard().get(1).isActive()) {
            LeaderCardProduction message = new LeaderCardProduction(backEnd.getModel().getPlayer(backEnd.getMyUsername()).getLeaderCard().get(1).getId(), Cli.fromStringToColor(line));
            backEnd.notify(message);
        }else{
            Cli.showUpdateMessage("Your leader is not active");
            ProductionPageView(this.backEnd);
        }
    }

    public void Production(String productionString, ViewBackEnd backEnd) {
        switch (productionString) {
            case "0":
                System.out.println("Please insert data for basic production (in1-in2-out): ");
                Cli.setReadHandler(this::basic);
                break;
            case "4":
                if(this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getLeaderCard().get(0).isActive() &&
                        String.valueOf(this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getLeaderCard().get(0).getType()).equals("EXTRA_PRODUCTION")){
                    System.out.println("Which rss do you want[p/g/y/b]: ");
                    Cli.setReadHandler(this::leaderProd);
                }
                break;
            case "5":
                if(this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getLeaderCard().get(1).isActive() &&
                        String.valueOf(this.backEnd.getModel().getPlayer(this.backEnd.getMyUsername()).getLeaderCard().get(1).getType()).equals("EXTRA_PRODUCTION")){
                    System.out.println("Which rss do you want[p/g/y/b]: ");
                    Cli.setReadHandler(this::leaderProd2);
                }
                break;
            default:
                try{
                    CardProduction message = new CardProduction(Integer.parseInt(productionString) - 1);
                    backEnd.notify(message);
                }catch (NumberFormatException e){
                    Cli.showUpdateMessage("Wrong Input");
                    ProductionPageView(this.backEnd);
                    return;
                }
                break;
        }
    }


    public void ProductionPageView(ViewBackEnd backEnd) {
        this.backEnd = backEnd;
        this.backEnd.setEventHandler(this);

        print();

        Cli.setReadHandler(
                (line)->{
                    line = line.toUpperCase();
                    switch (line){
                        case "PRODUCE":
                            System.out.println("Which production do you want to do? (0 = basic, 1 to 3 = production card, 4-5 = leader production [ insert one by one]: ");
                            System.out.println("Pay attention to Leader Card Production, if your leader is the card on the left in the homepage insert 4, if it is the one on the right insert 5");
                            Cli.setReadHandler(this::produce);
                            break;
                        case "EXIT":
                            System.out.println("redirecting to Home..");
                            Cli.homePage.HomePageView(this.backEnd);
                            break;
                        case "ENDTURN":
                            EndTurn message = new EndTurn();
                            this.backEnd.notify(message);
                            Cli.homePage.HomePageView(backEnd);
                        default:
                            System.out.println("Wrong Command, please insert a real command");
                            break;
                    }
                }
        );


    }

    @Override
    public void invalidMessage() {

    }

    @Override
    public void handle(ChestUpdate event) {

        Cli.UpdateChest(backEnd, production);
        Cli.productionPage.ProductionPageView(backEnd);
    }

    @Override
    public void handle(ShelfUpdate event) {

        Cli.UpdateShelf(backEnd, production);
        Cli.productionPage.ProductionPageView(backEnd);
    }

    @Override
    public void handle(ErrorUpdate event) {
        Cli.showError(event);
        ProductionPageView(this.backEnd);
    }

    @Override
    public void handle(ModelUpdate event){
        for (Message<ModelEventHandler> e: event.getMessages()){
            e.accept(this);
        }
        /*
        CLI_Controller.showUpdateMessage(event.getMessage());
        ProductionPageView(this.backEnd);*/
    }

    @Override
    public void handle(ActivePlayer event){
        Cli.cls();
        System.out.println("your turn is over, redirecting to home");
        Cli.homePage.HomePageView(this.backEnd);
    }

    @Override
    public void handle(ActionTokenPlayed event) {
        Cli.showSingleMessage(event, this.backEnd);
    }

    @Override
    public void handle(VaticanReport event) {
        Cli.activatePopeFavor(event.getIndex());
    }

}
