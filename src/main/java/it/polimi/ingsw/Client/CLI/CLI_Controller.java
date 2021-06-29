package it.polimi.ingsw.Client.CLI;

import it.polimi.ingsw.Client.CLI.Pages.*;
import it.polimi.ingsw.Client.ClientApp;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.LeaderCard;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.Shelf;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ClientMessages.*;
import it.polimi.ingsw.Message.Model.ErrorUpdate;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.ResourceList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;


public class CLI_Controller {

    private static ViewBackEnd backEnd;
    private final ClientApp app;


    public static StartPage start;
    public static LoadingPage loading;
    public static UsernamePage username;
    public static SelectNumberPlayerPage selectNumber;
    public static WaitPage waitPage;
    public static SelectionPage selectionPage;
    public static HomePage homePage;
    public static ProductionPage productionPage;
    public static CardMarketPage cardMarketPage;
    public static RssMarketPage rssMarketPage;
    public static ViewPage viewPage;
    public static QuitPage quitPage;


    private static final int[] RssPosition = {415,944,952,1471,1479,1488, 3594, 3601, 3608, 3615};
    private static final int[] LeaderShelfPosition = {1864,1879};


    public static int[] leaderActive = {0,0,0,0};
    public static int[] popeFavourActive = {0,0,0};

    private static final String rssPath = "src/main/resources/CLI/";
    private static final String[] currentFile =
            {"StartMenuView.txt","LoadingView.txt","NoActionView.txt",
                    "ProductionView.txt","CardMarketView.txt","ResourceMarketView.txt",
                    "JoinGame.txt", "Exit.txt", "NewGame.txt", "WaitingForOtherPlayer.txt",
                    "BigFaithTrack.txt", "LeaderSelectionView.txt", "cardScheme.txt", "leaderShelfScheme.txt", "leaderProductionScheme.txt"};

    public CLI_Controller(){

        app = new ClientApp(this::CLIView);
        backEnd = ViewBackEnd.getCLIBackend(app);
        app.setBackEnd(backEnd);

        char[] home = readSchematics(2);
        char[] production = readSchematics(3);
        char[] cardMarket = readSchematics(4);
        char[] rssMarket = readSchematics(5);

        start = new StartPage();
        loading = new LoadingPage();
        username = new UsernamePage();
        selectNumber = new SelectNumberPlayerPage();
        waitPage = new WaitPage();
        selectionPage = new SelectionPage();
        homePage = new HomePage(home);
        productionPage = new ProductionPage(production);
        cardMarketPage = new CardMarketPage(cardMarket);
        rssMarketPage = new RssMarketPage(rssMarket);
        viewPage = new ViewPage();
        quitPage = new QuitPage();
    }



    public static void main(String[] args) {
        CLI_Controller controller = new CLI_Controller();
        controller.CLIView();

        new Thread(()->readLine()).run();


    }





    public void CLIView() {
        start.StartPageView(backEnd);
    }


    public static void defaultHandler(String line){
        System.out.println(line);
    }


    private static Scanner scanner = new Scanner(System.in);
    private static boolean running = true;
    private static Consumer<String> lineHandler = CLI_Controller::defaultHandler;
    private static Consumer<String> newHandler = CLI_Controller::defaultHandler;

    private static void readLine(){

        while (running){

            String line = scanner.nextLine();

            synchronized (newHandler){
                lineHandler = newHandler;
            }

            lineHandler.accept(line);

        }

    }


    public static void setReadHandler(Consumer<String> readHandler){
        synchronized (newHandler){
            newHandler = readHandler;
        }

    }





    private static Executor executor= Executors.newCachedThreadPool();

    public static void read(Consumer<Scanner> lineHandler){
        executor.execute(()->{
            lineHandler.accept(new Scanner(System.in));
        });
    }



    public static void UpdateShelf(ViewBackEnd backEnd, char[] scheme) {
            List<Shelf> playerShelf = backEnd.getModel().getPlayer(backEnd.getModel().myUsername).getShelves();
            for (int i = 0; i < playerShelf.size(); i++) {
                int size = playerShelf.get(i).size;
                if(playerShelf.get(i).color!= null){
                switch (playerShelf.get(i).color) {
                    case YELLOW:
                        for (int j = 0; j < size; j++) {
                            if(playerShelf.get(i).maxSize == 3)
                                scheme[RssPosition[i + j+1]] = 'Y';
                            else
                                scheme[RssPosition[i + j]] = 'Y';
                        }
                        break;
                    case BLUE:
                        for (int j = 0; j < size; j++) {
                            if(playerShelf.get(i).maxSize == 3)
                                scheme[RssPosition[i + j+1]] = 'B';
                            else
                                scheme[RssPosition[i + j]] = 'B';
                        }
                        break;
                    case PURPLE:
                        for (int j = 0; j < size; j++) {
                            if(playerShelf.get(i).maxSize == 3)
                                scheme[RssPosition[i + j+1]] = 'P';
                            else
                                scheme[RssPosition[i + j]] = 'P';
                        }
                        break;
                    case GREY:
                        for (int j = 0; j < size; j++) {
                            if(playerShelf.get(i).maxSize == 3)
                                scheme[RssPosition[i + j+1]] = 'G';
                            else
                                scheme[RssPosition[i + j]] = 'G';
                        }
                        break;
                    default:
                        for (int j = 0; j < size; j++) {
                            scheme[RssPosition[i + j]] = ' ';
                        }
                        break;
                }
            }
            }
    }


    public static void LeaderCardInfoExtractor(char[] home, List<LeaderCard> leaderCard, int i, int[] homeLeaderType, int[] homeLeaderPV, int[] homeLeaderCost, int[] homeLeaderEffect) {
        String leaderType = String.valueOf(leaderCard.get(i).getType());
        System.arraycopy(leaderType.toCharArray(), 0, home, homeLeaderType[i], leaderType.toCharArray().length);

        String leaderPV = leaderCard.get(i).getVictoryPoint() + "VP";
        System.arraycopy(leaderPV.toCharArray(), 0, home, homeLeaderPV[i], leaderPV.toCharArray().length);

        List<Marble.Color> leaderCostMarbles = leaderCard.get(i).getResourceRequirement();
        if (leaderCostMarbles != null) {
            String leaderCost = getColorString(leaderCostMarbles);
            System.arraycopy(leaderCost.toCharArray(), 0, home, homeLeaderCost[i], leaderCost.toCharArray().length);
        }else {
            List<String> leaderCostDevCard = leaderCard.get(i).getDevelopmentCardRequirement();
            StringBuilder costString = new StringBuilder();
            for (String s : leaderCostDevCard) {
                costString.append(s).append(" ");
            }
            System.arraycopy(costString.toString().toCharArray(), 0, home, homeLeaderCost[i], costString.toString().toCharArray().length);
        }

        Marble.Color colorEffect = leaderCard.get(i).getColor();
        String colorEffected = getColorString(colorEffect);
        String effect;
        switch (leaderType) {
            case "DISCOUNT":
                 effect = "-1" + colorEffected;
                break;
            case "MARBLE_CONVERSION":
                 effect = "White = " + colorEffected;
                break;
            case "EXTRA_SHELF":
                 effect = "2 " + colorEffected + " Slot";
                break;
            case "EXTRA_PRODUCTION":
                 effect = "1" + colorEffected + " -> 1? + 1R";
                break;
            default:
                effect = "";
        }
        System.arraycopy(effect.toCharArray(), 0, home, homeLeaderEffect[i], effect.toCharArray().length);
    }


    public static void Production(String productionString, ViewBackEnd backEnd) {
        if(productionString.equals("0")){
            System.out.println("Please insert data for basic production (in1-in2-out): ");
            Scanner input = new Scanner(System.in);
            String basicProd = input.nextLine();
            String[] basicProdArray = basicProd.split("-");
            Marble.Color[] prodColor = new Marble.Color[3];
            for (int i = 0; i < basicProdArray.length; i++) {
                switch (basicProdArray[i]) {
                    case "Y":
                        prodColor[i] = Marble.Color.YELLOW;
                        break;
                    case "G":
                        prodColor[i] = Marble.Color.GREY;
                        break;
                    case "P":
                        prodColor[i] = Marble.Color.PURPLE;
                        break;
                    case "B":
                        prodColor[i] = Marble.Color.BLUE;
                        break;
                }
            }

            BasicProduction message = new BasicProduction(prodColor[0], prodColor[1],prodColor[2]);
            backEnd.notify(message);
        }else{
            CardProduction message = new CardProduction(Integer.parseInt(productionString)-1);
            backEnd.notify(message);
        }
    }


    public static void cls()
    {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (InterruptedException | IOException ignored) {}
    }


    public static char[] readSchematics(int x) {
        File file = new File(rssPath + currentFile[x]);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert scanner != null;
        StringBuilder theString = new StringBuilder(scanner.nextLine());
        while (scanner.hasNextLine()) {
            theString.append("\n").append(scanner.nextLine());
        }
        return theString.toString().toCharArray();
    }

    public static String getColorStringAvailableRss(Marble marble){
            switch (marble.getColor()){
                case YELLOW:
                    return "Y";
                case BLUE:
                    return "B";
                case PURPLE:
                    return "P";
                case GREY:
                    return "G";
                default:
                    return " ";
            }
    }


    public static String getColorStringFromMarble(List<Marble> marbleList){
        int[] colorMarble = {0,0,0,0,0};
        for (Marble marble: marbleList) {
            Marble.Color color = marble.getColor();
            colorExtractor(colorMarble, color);
        }
        return stringBuilder(colorMarble);
    }

    public static String getColorString(Marble.Color color){
        String returnedColor;
        switch (color){
            case YELLOW:
                returnedColor = "Y";
                break;
            case BLUE:
                returnedColor = "B";
                break;
            case GREY:
                returnedColor = "G";
                break;
            case PURPLE:
                returnedColor = "P";
                break;
            case RED:
                returnedColor = "R";
                break;
            default:
                returnedColor = "";
                break;
        }
        return returnedColor;
    }


    public static String getColorString(List<Marble.Color> colorList){
        int[] colorMarble = {0,0,0,0,0};
        for (Marble.Color marble: colorList) {
            colorExtractor(colorMarble, marble);
        }
        return stringBuilder(colorMarble);
    }


    public static void colorExtractor(int[] colorMarble, Marble.Color color) {
        switch (color) {
            case YELLOW:
                colorMarble[0]++;
                break;
            case BLUE:
                colorMarble[1]++;
                break;
            case GREY:
                colorMarble[2]++;
                break;
            case PURPLE:
                colorMarble[3]++;
                break;
            case RED:
                colorMarble[4]++;
                break;
        }
    }


    public static String stringBuilder(int[] colorMarble) {
        StringBuilder colorString = new StringBuilder();
        String col;
        for (int k = 0; k < 5; k++) {
            if(colorMarble[k]>0){
                if(k == 0)
                    col = "Y";
                else if (k == 1)
                    col = "B";
                else if (k == 2)
                    col = "G";
                else if (k == 3)
                    col = "P";
                else
                    col = "R";
                colorString.append(colorMarble[k]).append(col).append(" ");
            }
        }
        return colorString.toString();
    }



    public static void UpdateChest(ViewBackEnd backEnd, char[] page ){
            ResourceList playerChest = backEnd.getModel().getPlayer(backEnd.getModel().myUsername).getChest();
            if(playerChest != null) {
                List<Marble> playerChestMarble = playerChest.getAllMarble();
                String[] playerChestRss = CLI_Controller.getColorStringFromMarble(playerChestMarble).split(" ");
                for (int i = 0; i < playerChestRss.length; i++) {
                    System.arraycopy(playerChestRss[i].toCharArray(), 0, page, RssPosition[i + 6], playerChestRss[i].toCharArray().length);
                }
            }
    }

    public static void showError(ErrorUpdate event){
        cls();
        System.out.println(event.getErrorMessage());
        System.out.println("Here is a free time travel, enjoy it");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void leaderPowerSelector(String s){
        int id = Integer.parseInt(s.split("_")[1]);
        if(id < 5) {
            leaderActive[0] += 1;
        } else if(id < 9){
            leaderActive[1] += 1;
        } else if(id <13){
            leaderActive[2] += 1;
        } else {
            leaderActive[3] += 1;
        }
    }

    public static void showLeaderShelf(char[] page){
        char[] shelf;
        shelf = readSchematics(13);
        for (int i = 0; i < leaderActive[1]; i++){
            for (int j = 0; j < 15; j++){
                for (int k = 0; k < 4; k++){
                    page[LeaderShelfPosition[i]+j+133*k] = shelf[j+15*k];
                }
            }
        }
    }

    public static void activatePopeFavor(int index) {
        popeFavourActive[index] = 1;
    }
 }
