package it.polimi.ingsw.Client.CLI;

import it.polimi.ingsw.Client.CLI.Pages.*;
import it.polimi.ingsw.Client.ClientApp;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.LeaderCard;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.Shelf;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.Model.ActionTokenPlayed;
import it.polimi.ingsw.Message.Model.ErrorUpdate;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.ResourceList;
import it.polimi.ingsw.Model.ProductionCard.DevelopmentCard;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Consumer;


public class Cli {

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
    public static EndGamePage endPage;
    public static int cardSpaces;
    public static String spacer = "                                                   ";
    private static final int[] ShelfRssPosition = {415,944,952,1471,1479,1488, 2001, 2009, 2018, 2026};
    private static final int[] ChestRssPosition = {3594, 3601, 3608, 3615};
    private static final int[] LeaderShelfPosition = {1864,1879};


    public static int[] leaderActive = {0,0,0,0};
    public static int[] vaticanReport = {0,0,0};
    public static boolean[] vaticanReportActive = {false,false,false};


    private static final String rssPath = "/CLI/";
    private static final String[] currentFile =
            {"StartMenuView.txt","LoadingView.txt","NoActionView.txt",
                    "ProductionView.txt","CardMarketView.txt","ResourceMarketView.txt",
                    "JoinGame.txt", "Exit.txt", "NewGame.txt", "WaitingForOtherPlayer.txt",
                    "BigFaithTrack.txt", "LeaderSelectionView.txt", "cardScheme.txt", "leaderShelfScheme.txt",
                    "leaderProductionScheme.txt", "EndGame.txt", "EndGameLose.txt"};

    private static char[] shelfColors = new char[2];

    public Cli(){

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
        endPage = new EndGamePage();
    }



    public static void main(String[] args) {
        Cli controller = new Cli();
        controller.CLIView();

        new Thread(Cli::readLine).start();

    }

    public void askMode(String line){
        Cli.cls();
        switch (line.toUpperCase()) {
            case "ONLINE" -> Cli.start.StartPageView(backEnd);
            case "LOCAL" -> {
                Cli.selectionPage.setup(backEnd);
                backEnd.localGame();
            }
            default -> {
                System.out.println("Error, closing application");
                System.exit(0);
            }
        }
    }




    public void CLIView() {
        System.out.println("Do you want to play a Local Game or an Online Game?");
        System.out.println("Insert: Local / Online");
        Cli.setReadHandler(this::askMode);
    }


    public static void defaultHandler(String line){
        System.out.println(line);
    }


    private static final Scanner scanner = new Scanner(System.in);
    private static boolean running = true;
    private static Consumer<String> lineHandler = Cli::defaultHandler;
    private static Consumer<String> newHandler = Cli::defaultHandler;

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
        executor.execute(()-> lineHandler.accept(new Scanner(System.in)));
    }



    public static void UpdateShelf(ViewBackEnd backEnd, char[] scheme) {
            List<Shelf> playerShelf = backEnd.getModel().getPlayer(backEnd.getMyUsername()).getShelves();
            for (int i = 0; i < playerShelf.size(); i++) {
                int size = playerShelf.get(i).size;
                if(playerShelf.get(i).color!= null){
                    switch (playerShelf.get(i).color) {
                        case YELLOW:
                            for (int j = 0; j < size; j++) {
                                if (i == 2) {
                                    scheme[ShelfRssPosition[i + j + 1]] = 'Y';
                                }else if (i == 3) {
                                    scheme[ShelfRssPosition[i + j + 3]] = 'Y';
                                }else if (i == 4) {
                                    scheme[ShelfRssPosition[i + j + 5]] = 'Y';
                                }else {
                                    scheme[ShelfRssPosition[i + j]] = 'Y';
                                }
                            }
                            break;
                        case BLUE:
                            for (int j = 0; j < size; j++) {
                                if (i == 2) {
                                    scheme[ShelfRssPosition[i + j + 1]] = 'B';
                                }else if (i == 3) {
                                    scheme[ShelfRssPosition[i + j + 3]] = 'B';
                                }else if (i == 4) {
                                    scheme[ShelfRssPosition[i + j + 5]] = 'B';
                                }else {
                                    scheme[ShelfRssPosition[i + j]] = 'B';
                                }
                            }
                            break;
                        case PURPLE:
                            for (int j = 0; j < size; j++) {
                                if (i == 2) {
                                    scheme[ShelfRssPosition[i + j + 1]] = 'P';
                                }else if (i == 3) {
                                    scheme[ShelfRssPosition[i + j + 3]] = 'P';
                                }else if (i == 4) {
                                    scheme[ShelfRssPosition[i + j + 5]] = 'P';
                                }else {
                                    scheme[ShelfRssPosition[i + j]] = 'P';
                                }
                            }
                            break;
                        case GREY:
                            for (int j = 0; j < size; j++) {
                                if (i == 2) {
                                    scheme[ShelfRssPosition[i + j + 1]] = 'G';
                                } else if (i == 3){
                                    scheme[ShelfRssPosition[i + j + 3]] = 'G';
                                }else if (i == 4){
                                    scheme[ShelfRssPosition[i + j + 5]] = 'G';
                                }else {
                                    scheme[ShelfRssPosition[i + j]] = 'G';
                                }
                            }
                            break;
                        default:
                            for (int j = 0; j < size; j++) {
                                if(i==2) {
                                    scheme[ShelfRssPosition[i + j + 1]] = ' ';
                                }else if(i==3) {
                                    scheme[ShelfRssPosition[i + j + 3]] = ' ';
                                }else if(i==4) {
                                    scheme[ShelfRssPosition[i + j + 5]] = ' ';
                                }else {
                                    scheme[ShelfRssPosition[i + j]] = ' ';
                                }
                            }
                            break;
                    }
                }else if (size==0){
                    for (int j = 0; j<playerShelf.get(i).maxSize; j++) {
                        if(i==2)
                            scheme[ShelfRssPosition[i + j+1]] = ' ';
                        else if(i==3)
                            scheme[ShelfRssPosition[i + j+3]] = ' ';
                        else if(i==4)
                            scheme[ShelfRssPosition[i + j+5]] = ' ';
                        else
                            scheme[ShelfRssPosition[i + j]] = ' ';
                    }
                }
            }
    }


    public static void LeaderCardInfoExtractor(char[] home, List<LeaderCard> leaderCard, int i, int[] homeLeaderType, int[] homeLeaderPV, int[] homeLeaderCost, int[] homeLeaderEffect) {
        String leaderType = String.valueOf(leaderCard.get(i).getType());
        cardSpaces = 17-leaderType.length();
        String leaderTypeCleaner = leaderType;
        for (int spa = 0; spa < cardSpaces; spa++) {
            leaderTypeCleaner = leaderTypeCleaner.concat(" ");
        }
        System.arraycopy(leaderTypeCleaner.toCharArray(), 0, home, homeLeaderType[i], leaderTypeCleaner.toCharArray().length);

        String leaderPV = leaderCard.get(i).getVictoryPoint() + "VP";
        System.arraycopy(leaderPV.toCharArray(), 0, home, homeLeaderPV[i], leaderPV.toCharArray().length);

        List<Marble.Color> leaderCostMarbles = leaderCard.get(i).getResourceRequirement();
        if (leaderCostMarbles != null) {
            String leaderCost = getColorString(leaderCostMarbles);
            cardSpaces = 17-leaderCost.length();
            for (int spa = 0; spa < cardSpaces; spa++) {
                leaderCost = leaderCost.concat(" ");
            }
            System.arraycopy(leaderCost.toCharArray(), 0, home, homeLeaderCost[i], leaderCost.toCharArray().length);
        }else {
            List<String> leaderCostDevCard = leaderCard.get(i).getDevelopmentCardRequirement();
            StringBuilder costStringBuild = new StringBuilder();
            for (String s : leaderCostDevCard) {
                costStringBuild.append(s).append(" ");
            }
            String costString = costStringBuild.toString();
            cardSpaces = 17-costString.length();
            for (int spa = 0; spa < cardSpaces; spa++) {
                costString = costString.concat(" ");
            }
            System.arraycopy(costString.toCharArray(), 0, home, homeLeaderCost[i], costString.toCharArray().length);
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
        cardSpaces = 16-effect.length();
        for (int spa = 0; spa < cardSpaces; spa++) {
            effect = effect.concat(" ");
        }
        System.arraycopy(effect.toCharArray(), 0, home, homeLeaderEffect[i], effect.toCharArray().length);
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

    public static String getDevColor(DevelopmentCard.Color color){
        switch (color) {
            case BLUE:
                return "BLUE";
            case YELLOW:
                return "YELLOW";
            case PURPLE:
                return "PURPLE";
            case GREEN:
                return "GREEN";
            default:
                return "";
        }
    }


    public static char[] readSchematics(int x) {
       // File file = new File(rssPath + currentFile[x]);


        BufferedReader file
                = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Cli.class.getResourceAsStream(rssPath + currentFile[x])), StandardCharsets.UTF_8));
        Scanner readRes = new Scanner(file);

        StringBuilder theString = new StringBuilder(readRes.nextLine());
        while (readRes.hasNextLine()) {
            theString.append("\n").append(readRes.nextLine());
        }
        return theString.toString().toCharArray();
    }

    public static String getColorStringAvailableRss(Marble marble){
        switch (marble.getColor()) {
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

    public static Marble.Color fromStringToColor(String s){
            char c = s.toUpperCase().charAt(0);
            switch (c) {
                case 'Y':
                    return Marble.Color.YELLOW;
                case 'B':
                    return Marble.Color.BLUE;
                case 'P':
                    return Marble.Color.PURPLE;
                case 'G':
                    return Marble.Color.GREY;
                default:
                    return null;
            }
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

    public static void showSingleMessage(ActionTokenPlayed event, ViewBackEnd backend){
        Cli.cls();
        for (int i = 0; i < 5; i++) {
            System.out.println();
        }

        System.out.println(spacer + "New Action Token played by Lorenzo");
        System.out.println(spacer + event.getMessage());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Cli.homePage.HomePageView(backend);
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
                String[] playerChestRss = Cli.getColorStringFromMarble(playerChestMarble).split(" ");
                for (int i = 0; i < playerChestRss.length; i++) {
                    System.arraycopy((playerChestRss[i]+ " ").toCharArray(), 0, page, ChestRssPosition [i], (playerChestRss[i]+ " ").toCharArray().length);
                }
            }
    }

    public static void showError(ErrorUpdate event){
        cls();
        for (int i = 0; i < 5; i++) {
            System.out.println();
        }
        System.out.println(spacer + event.getErrorMessage());
        System.out.println(spacer + "Here is a free time travel, enjoy it");
        try {
            Thread.sleep(2000);
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
        List<LeaderCard> leaders = backEnd.getModel().getPlayer(backEnd.getMyUsername()).getLeaderCard();
        int pos = 0;
        for (LeaderCard leader: leaders) {
            if(leader.isActive() && String.valueOf(leader.getType()).equals("EXTRA_SHELF")){
                shelfColors[pos]= Cli.getColorString(leader.getColor()).charAt(0);
                pos++;
            }
        }


        for (int i = 0; i < leaderActive[1]; i++){
            for (int j = 0; j < 4; j++){
                for (int k = 0; k < 15; k++){
                    page[LeaderShelfPosition[i]+k+133*j] = shelf[k+16*j];
                }
            }
            page[LeaderShelfPosition[i]+ 7] = shelfColors[i];
        }
    }

    public static void activatePopeFavor(int index) {
        vaticanReport[index-1] = 1;
        for (Integer i: backEnd.getModel().vaticanRoute.getVaticanReports(backEnd.getMyUsername())) {
            if (i == (index - 1)) {
                vaticanReportActive[index-1] = true;
                break;
            }
        }

    }

    public static void showUpdateMessage(String message){
        cls();
        for (int i = 0; i < 5; i++) {
            System.out.println();
        }
        System.out.println(spacer + message);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
 }
