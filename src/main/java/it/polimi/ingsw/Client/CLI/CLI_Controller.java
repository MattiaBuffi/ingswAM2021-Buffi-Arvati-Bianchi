package it.polimi.ingsw.Client.CLI;

import it.polimi.ingsw.Client.CLI.Pages.*;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.LeaderCard;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.Shelf;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.ClientMessages.*;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Utils.Observable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


public class CLI_Controller extends Observable<Message<ClientEventHandler>> {
    private static final int[] RssPosition = {415,944,952,1471,1479,1488, 3594, 3601, 3608, 3615};

    private final ViewBackEnd backEnd;

    public static String scene;
    private static final String rssPath = "src/main/resources/CLI/";
    private static final String[] currentFile =
            {"StartMenuView.txt","LoadingView.txt","NoActionView.txt",
                    "ProductionView.txt","CardMarketView.txt","ResourceMarketView.txt",
                    "JoinGame.txt", "Exit.txt", "NewGame.txt", "WaitingForOtherPlayer.txt",
                    "BigFaithTrack.txt", "LeaderSelectionView.txt", "carcScheme.txt"};

    public CLI_Controller(ViewBackEnd backEnd) {
        this.backEnd = backEnd;
    }

    public void CLIView() throws IOException {
        char[] home = readSchematics(2);
        char[] production = readSchematics(3);
        char[] cardMarket = readSchematics(4);
        char[] rssMarket = readSchematics(5);

        scene = "START";
        while(!scene.equals("exit")) {
            switch (scene) {
                case "START":
                    //cls();
                    StartPage start = new StartPage(backEnd);
                    start.StartPageView();
                    break;

                case "LOADING":
                   // cls();
                    LoadingPage loading = new LoadingPage(backEnd);
                    loading.LoadingPageView();
                    break;

                case "NEWGAME":
                    // cls();
                    NewGamePage newGame = new NewGamePage(backEnd);
                    newGame.NewGamePageView();
                    break;

                case "JOINGAME":
                    // cls();
                    JoinGamePage joinGame = new JoinGamePage(backEnd);
                    joinGame.JoinGamePageView();
                    break;

                case "WAIT":
                    WaitPage waitPage = new WaitPage(backEnd);
                    waitPage.WaitPageView();
                    break;


                case "SELECTION":
                    //cls();
                    SelectionPage selectionPage = new SelectionPage(backEnd);
                    selectionPage.SelectionPageView();
                    break;

                case "HOME":
                    // cls();
                    HomePage homePage = new HomePage(backEnd, home);
                    homePage.HomePageView();
                    break;

                case "PRODUCE":
                  //  cls();
                    ProductionPage productionPage = new ProductionPage(backEnd, production);
                    productionPage.ProductionPageView();
                    break;

                case "CARDMARKET":
                    //cls();
                    CardMarketPage cardMarketPage = new CardMarketPage(backEnd, cardMarket);
                    cardMarketPage.CardMarketPageView();
                    break;

                case "RSSMARKET":
                    //cls();
                    RssMarketPage rssMarketPage = new RssMarketPage(backEnd, rssMarket);
                    rssMarketPage.RssMarketPageView();
                    break;


                case "VIEW":
                    //cls();
                    ViewPage viewPage = new ViewPage(backEnd);
                    viewPage.ViewPageView();
                    break;

                case "QUIT":
                    //cls();
                    QuitPage quitPage = new QuitPage(backEnd);
                    quitPage.QuitPageView();
                    break;

                default:
                   // cls();
                    System.out.println("Wrong Command, please insert a real command, redirecting to Home..");
                    scene = "HOME";
                    break;
            }
        }
    }

    public static void ShelfExtractor(char[] scheme, List<Shelf> playerShelf) {
        for (int i = 0; i < playerShelf.size(); i++) {
            int size = playerShelf.get(i).size;
            switch (playerShelf.get(i).color.toString()) {
                case "YELLOW":
                    for (int j = 0; j < size; j++) {
                        scheme[RssPosition[i+j]]= 'Y';
                    }
                    break;
                case "BLUE":
                    for (int j = 0; j < size; j++) {
                        scheme[RssPosition[i+j]]= 'B';
                    }
                    break;
                case "PURPLE":
                    for (int j = 0; j < size; j++) {
                        scheme[RssPosition[i+j]]= 'P';
                    }
                    break;
                case "GREY":
                    for (int j = 0; j < size; j++) {
                        scheme[RssPosition[i+j]]= 'G';
                    }
                    break;
            }
        }
    }


    public static void LeaderCardInfoExtractor(char[] home, List<LeaderCard> leaderCard, int i, int[] homeLeaderType, int[] homeLeaderPV, int[] homeLeaderCost) {
        String leaderTypeString = leaderCard.get(i).getType();
        char[] leaderTypeArray = leaderTypeString.toCharArray();
        System.arraycopy(leaderTypeArray, 0, home, homeLeaderType[i], leaderTypeArray.length);

        String leaderPVString = leaderCard.get(i).getVictoryPoint() + "PV";
        char[] leaderPvArray = leaderPVString.toCharArray();
        System.arraycopy(leaderPvArray, 0, home, homeLeaderPV[i], leaderPvArray.length);

        List<Marble.Color> leaderCostMarbles = leaderCard.get(i).getResourceRequirement();
        if (leaderCostMarbles != null) {
            String leaderCostString = getColorString(leaderCostMarbles);
            char[] leaderCostArray = leaderCostString.toCharArray();
            System.arraycopy(leaderCostArray, 0, home, homeLeaderCost[i], leaderCostArray.length);
        }else {
            List<String> leaderCostDevCard = leaderCard.get(i).getDevelopmentCardRequirement();
            StringBuilder costString = new StringBuilder();
            String col = "";
            for (String s : leaderCostDevCard) {
                costString.append(s).append(col).append(" ");
            }
            System.arraycopy(costString.toString().toCharArray(), 0, home, homeLeaderCost[i], costString.toString().toCharArray().length);
        }
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
        try
        {
            new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        }catch(Exception E)
        {
            System.out.println(E);
        }
    }


    public static char[] readSchematics(int x) throws FileNotFoundException {
        File file = new File(rssPath + currentFile[x]);
        Scanner scanner = new Scanner(file);
        StringBuilder theString = new StringBuilder(scanner.nextLine());
        while (scanner.hasNextLine()) {
            theString.append("\n").append(scanner.nextLine());
        }
        return theString.toString().toCharArray();
    }


    public static String getColorStringFromMarble(List<Marble> colorList){
        int[] colorMarble = {0,0,0,0};
        for (Marble marble: colorList) {
            String color = marble.getColor().toString();
            colorExtractor(colorMarble, color);
        }
        return stringBuilder(colorMarble);
    }


    public static String getColorString(List<Marble.Color> colorList){
        int[] colorMarble = {0,0,0,0};
        for (Marble.Color marble: colorList) {
            String color = marble.toString();
            colorExtractor(colorMarble, color);
        }
        return stringBuilder(colorMarble);
    }


    public static void colorExtractor(int[] colorMarble, String color) {
        switch (color) {
            case "YELLOW":
                colorMarble[0]++;
                break;
            case "BLUE":
                colorMarble[1]++;
                break;
            case "GREY":
                colorMarble[2]++;
                break;
            case "PURPLE":
                colorMarble[3]++;
                break;
        }
    }


    public static String stringBuilder(int[] colorMarble) {
        StringBuilder colorString = new StringBuilder();
        String col;
        for (int k = 0; k < 4; k++) {
            if(colorMarble[k]>0){
                if(k == 0)
                    col = "Y";
                else if (k == 1)
                    col = "B";
                else if (k == 2)
                    col = "G";
                else
                    col = "P";
                colorString.append(colorMarble[k]).append(col).append(" ");
            }
        }
        return colorString.toString();
    }
}
