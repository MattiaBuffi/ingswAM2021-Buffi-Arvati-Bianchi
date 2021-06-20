package it.polimi.ingsw.Client.CLI;

import it.polimi.ingsw.Client.ModelData.Player;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.DevelopmentCardData;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.LeaderCard;
import it.polimi.ingsw.Client.ModelData.ViewModel;
import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.ClientMessages.*;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.ResourceList;
import it.polimi.ingsw.Utils.Observable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


public class CLI_Controller extends Observable<Message<ClientEventHandler>>  {
    private static final int[] FaithCellPosition = {1102, 1107,1112,713,314, 319, 324, 329, 334,339,738,1137, 1142,1147,1152,1157,1162,763,364,369,374,379,384,389,394,399};
    private static final int[] RssPosition = {415,944,952,1471,1479,1488, 3594, 3601, 3608, 3615};
    private static final int[] HomeLeaderCost = {2304, 2330};
    private static final int[] HomeLeaderType = {2570, 2596};
    private static final int[] HomeLeaderEffect = {2836, 2862};
    private static final int[] HomeLeaderPV = {3247, 3273};
    private static final int[] HomeRankPosition = {2633,2766,2899,3032};
    private static final int[] CardMarketCostPosition = {307,330,353,376,1637,1660,1683,1706,2967,2990,3013,3036};
    private static final int[] CardMarketInputPosition = {573,596,619,642,1903,1926,1949,1972,3233,3256,2379,2402};
    private static final int[] CardMarketOutputPosition = {839,862,885,908,2169,2192,2215,2238,3499,3522,3545,3568};
    private static final int[] CardMarketVPPosition = {1251,1274,1297,1320,2581,2604,2627,2650,3911,3934,3957,3980};
    private static final int[] LeaderSelectionCostPos = {1616,1641,1666,1691};
    private static final int[] LeaderSelectionTypePos = {1882,1907,1932,1957};
    private static final int[] LeaderSelectionEffectPos = {2148,2173,2198,2223};
    private static final int[] LeaderSelectionPVPos = {2560,2585,2610,2635};
    private static final int[] ProductionPosition = {2451,2475,2499,1786,1810,1834,1121,1145,1169};
    private static final int[] RssMarket = {578,593,608,623,1509,1524,1539,1554,2440,2455,2470,2485,3439};
    private static final int[] BigFaithCellPosition ={1739,1745,1751,1086,421,427,433,439,445,451,1116,1781,1787,1793,1799,1805,1811,1146,481,487,493,499,505,511,517,
            1742,1748,1754,1089,424,430,436,442,448,454,1119,1784,1790,1796,1802,1808,1814,1149,484,490,496,502,508,514,520,
            2005,2011,2017,1352,687,693,699,705,711,717,1382,2047,2053,2059,2065,2071,2077,1412,747,753,759,765,771,777,783,
            2008,2014,2020,1355,690,696,702,708,714,717,1385,2050,2056,2062,2068,2074,2080,1415,750,756,762,768,774,780,786};
    private static final int[] BigFaithPlayerNamePos = {2800,2820,2840,2860};
    private static final int[] BigFaithPlayerRssPos = {3068,3088,3108,3128};
    private static final int[] BigFaithPlayerProdPos = {3201,3221,3241,3261};
    private static final int[] BigFaithPlayerPVPos = {3334,3354,3374,3394};
    private static final int[] LeaderCardHomePosActive = {3502,3528};
    private static final int[] LeaderCardHomePosDiscard = {2169,2195};
    private static final int TurnPosition = 2235;
    private static final int FirstCellPosition = 2671;
    private static final int SecondCellPosition = 2704;
    private static final int[] singleCardPosition = {21, 59, 97, 166};

    int lastPosition = 0;

    Scanner input = new Scanner(System.in);
    private static final String rssPath = "src/main/resources/CLI/";
    private static final String[] currentFile =
            {"StartMenuView.txt","LoadingView.txt","NoActionView.txt",
                    "ProductionView.txt","CardMarketView.txt","ResourceMarketView.txt",
                    "JoinGame.txt", "Exit.txt", "NewGame.txt", "WaitingForOtherPlayer.txt",
                    "BigFaithTrack.txt", "LeaderSelectionView.txt", "carcScheme.txt"};

    public void CLIView() throws IOException, InterruptedException {
        ViewModel reducedModel = new ViewModel();
        char[] home = readSchematics(2);
        char[] production = readSchematics(3);
        char[] cardMarket = readSchematics(4);
        char[] rssMarket = readSchematics(5);
        DevelopmentCardData[][] cardMatrix = new DevelopmentCardData[3][4];

        String value = "start";
        while(!value.equals("exit")) {
            switch (value) {
                case "start":
                    //cls();
                    char[] charArray = readSchematics(0);
                    System.out.println(charArray);
                    System.out.println("Insert Server IP: ");
                    String server = input.nextLine();
                    char[] serverArray = server.toCharArray();
                    System.arraycopy(serverArray, 0, charArray, FirstCellPosition, serverArray.length);
                    System.out.println(charArray);
                    System.out.println("Insert Port Number: ");
                    String port = input.nextLine();

                    char[] portArray = port.toCharArray();
                    System.arraycopy(portArray, 0, charArray, SecondCellPosition, portArray.length);
                    System.out.println(charArray);
                    value = "loading";
                    break;

                case "loading":
                   // cls();
                    charArray = readSchematics(1);
                    System.out.println(charArray);
                    Thread.sleep(2000);
                    //clausola primo giocatore
                    if(true)
                    {
                        value = "NEWGAME";
                    }else{
                        value = "JOINGAME";
                    }
                    break;

                case "NEWGAME":
                    // cls();
                    charArray = readSchematics(8);
                    System.out.println(charArray);

                    System.out.println("Insert Username: ");
                    String name = input.nextLine();
                    char[] nameArray = name.toCharArray();
                    System.arraycopy(nameArray, 0, charArray, 2671, nameArray.length);
                    System.out.println(charArray);

                    System.out.println("Insert Number of Player (1-4): ");
                    String playerNumber = input.nextLine();
                    while (Integer.parseInt(playerNumber) > 4 || Integer.parseInt(playerNumber) < 1) {
                        System.out.println("Insert a Valid Number please: ");
                        playerNumber = input.nextLine();
                    }
                    char[] numArray = playerNumber.toCharArray();
                    System.arraycopy(numArray, 0, charArray, SecondCellPosition, numArray.length);
                    System.out.println(charArray);
                    value = "WAIT";
                    break;

                case "JOINGAME":
                    // cls();
                    charArray = readSchematics(6);
                    System.out.println(charArray);

                    System.out.println("Insert Username: ");
                    name = input.nextLine();
                    nameArray = name.toCharArray();
                    System.arraycopy(nameArray, 0, charArray, FirstCellPosition, nameArray.length);
                    System.out.println(charArray);
                    value = "WAIT";
                    break;

                case "WAIT":
                    charArray = readSchematics(9);
                    System.out.println(charArray);
                    Thread.sleep(2000);
                    value = "SELECTION";
                    break;


                case "SELECTION":
                    //cls();
                    //DA FIXARE
                    charArray = readSchematics(11);
                    List<LeaderCard> leaderCardSelection = reducedModel.current.getLeaderCard();
                    for (int i = 0; i < 4; i++) {
                        LeaderCardInfoExtractor(charArray, leaderCardSelection, i, LeaderSelectionTypePos, LeaderSelectionPVPos, LeaderSelectionCostPos);
                    }
                    System.out.println(charArray);
                    System.out.println("Which Leader Cards do you want to discard (pos-pos)[pos starts from zero]: ");
                    String discardedCard = input.nextLine();
                    String[] cardArray = discardedCard.split("-");
                    for (String s : cardArray) {
                        DiscardLeaderCard message = new DiscardLeaderCard(reducedModel.current.getLeaderCard().get(Integer.parseInt(s)).getId());
                        notify(message);
                    }
                    value = "HOME";
                    break;


                case "HOME":
                    // cls();

                    String customName = reducedModel.current.getUsername() + "'s Turn";
                    nameArray = customName.toCharArray();
                    System.arraycopy(nameArray, 0, home, TurnPosition, nameArray.length);

                    for (int i = 0; i < reducedModel.players.size(); i++) {
                        Player user = reducedModel.players.get(i);
                        nameArray = user.getUsername().toCharArray();
                        String posName = "" + (i + 1);
                        char[] posNameArray = posName.toCharArray();
                        home[(19 + i) * 133 + 103] = posNameArray[0];
                        home[(19 + i) * 133 + 104] = '.';
                        System.arraycopy(nameArray, 0, home, HomeRankPosition[i], nameArray.length);
                    }


                    //Mancano le shelf
                    ResourceList playerChest = reducedModel.current.getChest();
                    List<Marble> playerChestMarble = playerChest.getAll();
                    String[] playerChestRss = getColorStringFromMarble(playerChestMarble).split(" ");
                    for (int i = 0; i < playerChestRss.length; i++) {
                        System.arraycopy(playerChestRss[i].toCharArray(), 0, home, RssPosition[i+6], playerChestRss[i].toCharArray().length);
                    }


                    int position = reducedModel.current.getFaithPoints();
                    if ( position != lastPosition){
                        String lastPosString = Integer.toString(lastPosition);
                        char[] lastPositionArray = lastPosString.toCharArray();
                        System.arraycopy(lastPositionArray, 0, home, FaithCellPosition[lastPosition], lastPositionArray.length);
                        if(position>=10){
                            home[FaithCellPosition[position]]= '+';
                            home[FaithCellPosition[position]+1]= ' ';
                        }else
                            home[FaithCellPosition[position]]= '+';
                        lastPosition = position;
                    }

                    List<LeaderCard> leaderCard = reducedModel.current.getLeaderCard();
                    for (int i = 0; i < 2; i++) {
                        LeaderCardInfoExtractor(home, leaderCard, i, HomeLeaderType, HomeLeaderPV, HomeLeaderCost);
                    }


                    System.out.println(home);

                    System.out.println("Insert Command (Produce, CardMarket, RssMarket, View, Activate, Discard, Quit): ");
                    String command = input.nextLine();
                    command = command.toUpperCase();
                    if(command.equals("ACTIVATE")){
                        String active = "Active";
                        char[] activeArray = active.toCharArray();
                        System.out.println("Which Leader card do you want to Activate (1/2): ");
                        String activateLeader = input.nextLine();

                        if (activateLeader.equals("1")){
                            ActivateLeaderCard message = new ActivateLeaderCard(reducedModel.current.getLeaderCard().get(0).getId());
                            notify(message);
                            System.arraycopy(activeArray, 0, home, LeaderCardHomePosActive[0], active.length());
                        }else if (activateLeader.equals("2")){
                            ActivateLeaderCard message = new ActivateLeaderCard(reducedModel.current.getLeaderCard().get(1).getId());
                            notify(message);
                            System.arraycopy(activeArray, 0, home, LeaderCardHomePosActive[1], active.length());
                        }
                    }else if (command.equals("DISCARD")){

                        System.out.println("Which Leader card do you want to Discard (1/2): ");
                        String discardLeader = input.nextLine();
                        if (discardLeader.equals("1")){
                            DiscardLeaderCard message = new DiscardLeaderCard(reducedModel.current.getLeaderCard().get(0).getId());
                            notify(message);
                            for (int i = 0; i < 10; i++) {
                                for (int j = 0; j < 20; j++) {
                                    home[LeaderCardHomePosDiscard[0]+j+i*133]= ' ';
                                }
                            }
                        }else if (discardLeader.equals("2")){
                            DiscardLeaderCard message = new DiscardLeaderCard(reducedModel.current.getLeaderCard().get(1).getId());
                            notify(message);
                            for (int i = 0; i < 10; i++) {
                                for (int j = 0; j < 20; j++) {
                                    home[LeaderCardHomePosDiscard[1]+j+i*133]= ' ';
                                }
                            }
                        }
                    }else {
                        value = command;
                    }
                    System.out.println(home);
                    break;
                case "PRODUCE":
                  //  cls();
                    char[] customCard = readSchematics(12);
                    int row = 0;
                    int column = 0;
                    List<List<DevelopmentCardData>> playerProductions = reducedModel.current.getProductions();
                    for (List<DevelopmentCardData> productionColumn : playerProductions) {
                        for (DevelopmentCardData devCard : productionColumn) {
                            List<Marble> costList = devCard.price.getAll();
                            String costString = getColorStringFromMarble(costList);
                            char[] costArray = costString.toCharArray();
                            System.arraycopy(costArray, 0, customCard, singleCardPosition[0], costArray.length);

                            List<Marble> requireList = devCard.require.getAll();
                            String requireString = getColorStringFromMarble(requireList);
                            char[] requireArray = requireString.toCharArray();
                            System.arraycopy(requireArray, 0, customCard, singleCardPosition[1], requireArray.length);

                            List<Marble> produceList = devCard.produce.getAll();
                            String produceString = getColorStringFromMarble(produceList);
                            char[] produceArray = produceString.toCharArray();
                            System.arraycopy(produceArray, 0, customCard, singleCardPosition[2], produceArray.length);

                            String vp = Integer.toString(devCard.victoryPoints);
                            char[] vpArray = vp.toCharArray();
                            System.arraycopy(vpArray, 0, customCard, singleCardPosition[3], vpArray.length);

                            for (int i = 0; i < 10; i++) {
                                for (int j = 0; j < 20; j++) {
                                    production[ProductionPosition[column+row*3]+j+i*133] = customCard[i*20+j];
                                }
                            }
                            column++;
                        }
                        row++;
                    }

                    playerChest = reducedModel.current.getChest();
                    playerChestMarble = playerChest.getAll();
                    playerChestRss = getColorStringFromMarble(playerChestMarble).split(" ");
                    for (int i = 0; i < playerChestRss.length; i++) {
                        System.arraycopy(playerChestRss[i].toCharArray(), 0, production, RssPosition[i+6], playerChestRss[i].toCharArray().length);
                    }

                    System.out.println(production);
                    System.out.println("Insert Command (Produce,Exit): ");
                    command = input.nextLine();
                    command = command.toUpperCase();
                    switch (command){
                        case "PRODUCE":
                            System.out.println("Which production do you want to do? (0 = basic, 1 to 3 = production card, 4 = leader production, insert the numbers divided by '-') : ");
                            String productionString = input.nextLine();
                            if(productionString.length()>1) {
                                String[] productionArray = productionString.split("-");
                                for(String x: productionArray){
                                    Production(x);
                                }
                            }else{
                                Production(productionString);
                            }
                            value = "HOME";
                            break;
                        case "EXIT":
                            System.out.println("redirecting to Home..");
                            value="HOME";
                            break;
                        default:
                            System.out.println("Wrong Command, please insert a real command");
                            break;
                    }

                    break;

                case "CARDMARKET":
                    //cls();
                    for(int i=0;i<3;i++){
                        for(int j=0;j<4;j++){
                            cardMatrix[i][j] = reducedModel.cardMarket.getCard(i,j);

                            List<Marble> costList = cardMatrix[i][j].price.getAll();
                            String costString = getColorStringFromMarble(costList);
                            char[] costArray = costString.toCharArray();
                            System.arraycopy(costArray, 0, rssMarket, CardMarketCostPosition[i*4+j], costArray.length);

                            List<Marble> requireList = cardMatrix[i][j].require.getAll();
                            String requireString = getColorStringFromMarble(requireList);
                            char[] requireArray = requireString.toCharArray();
                            System.arraycopy(requireArray, 0, rssMarket, CardMarketInputPosition[i*4+j], requireArray.length);

                            List<Marble> produceList = cardMatrix[i][j].produce.getAll();
                            String produceString = getColorStringFromMarble(produceList);
                            char[] produceArray = produceString.toCharArray();
                            System.arraycopy(produceArray, 0, rssMarket, CardMarketOutputPosition[i*4+j], produceArray.length);

                            String vp = Integer.toString(cardMatrix[i][j].victoryPoints);
                            char[] vpArray = vp.toCharArray();
                            System.arraycopy(vpArray, 0, rssMarket, CardMarketVPPosition[i*4+j], vpArray.length);
                        }
                    }

                    playerChest = reducedModel.current.getChest();
                    playerChestMarble = playerChest.getAll();
                    playerChestRss = getColorStringFromMarble(playerChestMarble).split(" ");
                    for (int i = 0; i < playerChestRss.length; i++) {
                        System.arraycopy(playerChestRss[i].toCharArray(), 0, cardMarket, RssPosition[i+6], playerChestRss[i].toCharArray().length);
                    }

                    System.out.println(cardMarket);
                    System.out.println("Insert Command (Buy,Exit): ");
                    command = input.nextLine();
                    command = command.toUpperCase();
                    switch (command){
                        case "BUY":
                            System.out.println("Which card do you want to buy? (x-y-z where x and y are the coordinates of the card and z is the column where do you want to put your new card) : ");
                            String buyCard = input.nextLine();
                            String[] buyCardArray = buyCard.split("-");
                            BuyDevelopmentCard message = new BuyDevelopmentCard(Integer.parseInt(buyCardArray[0]), Integer.parseInt(buyCardArray[1]), Integer.parseInt(buyCardArray[2]));
                            notify(message);
                            break;
                        case "EXIT":
                            System.out.println("redirecting to Home..");
                            value="HOME";
                            break;
                        default:
                            System.out.println("Wrong Command, please insert a real command");
                            break;
                    }
                    break;

                case "RSSMARKET":
                    //cls();
                    for (int i = 0; i < 3; i++){
                        List<Marble> rssRow = reducedModel.resourceMarket.get(i);
                        for (int j=0; j<rssRow.size(); j++) {
                            String color = rssRow.get(j).getColor().toString();
                            switch (color) {
                                case "YELLOW":
                                    rssMarket[RssMarket[j+i*4]] = 'Y';
                                    break;
                                case "BLUE":
                                    rssMarket[RssMarket[j+i*4]] = 'B';
                                    break;
                                case "GREY":
                                    rssMarket[RssMarket[j+i*4]] = 'G';
                                    break;
                                case "PURPLE":
                                    rssMarket[RssMarket[j+i*4]] = 'P';
                                    break;
                            }
                        }
                    }
                    Marble bonusMarble = reducedModel.resourceMarket.getBonusMarble();
                    String bonusColor = bonusMarble.getColor().toString();
                    switch (bonusColor) {
                        case "YELLOW":
                            rssMarket[RssMarket[12]] = 'Y';
                            break;
                        case "BLUE":
                            rssMarket[RssMarket[12]] = 'B';
                            break;
                        case "GREY":
                            rssMarket[RssMarket[12]] = 'G';
                            break;
                        case "PURPLE":
                            rssMarket[RssMarket[12]] = 'P';
                            break;
                    }

                    playerChest = reducedModel.current.getChest();
                    playerChestMarble = playerChest.getAll();
                    playerChestRss = getColorStringFromMarble(playerChestMarble).split(" ");
                    for (int i = 0; i < playerChestRss.length; i++) {
                        System.arraycopy(playerChestRss[i].toCharArray(), 0, rssMarket, RssPosition[i+6], playerChestRss[i].toCharArray().length);
                    }

                    System.out.println(rssMarket);
                    System.out.println("Insert Command (Buy,Exit): ");
                    command = input.nextLine();
                    command = command.toUpperCase();
                    switch (command){
                        case "BUY":
                            System.out.println("Which card do you want to buy? (value between 1 and 7 [1 = first column on the left, 7 = first row from the top]) : ");
                            String buyRss = input.nextLine();
                            TakeResources message = new TakeResources(Integer.parseInt(buyRss));
                            notify(message);
                            break;
                        case "EXIT":
                            System.out.println("redirecting to Home..");
                            value="HOME";
                            break;
                        default:
                            System.out.println("Wrong Command, please insert a real command");
                            break;
                    }
                    break;


                case "VIEW":
                    char[] bigView = readSchematics(10);
                    int i = 0;
                    List<Player> users = reducedModel.players;
                    for (Player user: users) {
                        System.arraycopy(((char)(i+1)+ " " + user.getUsername()).toCharArray(), 0, bigView, BigFaithPlayerNamePos[i], user.getUsername().toCharArray().length);
                        System.arraycopy(Integer.toString(user.getProductions().size()).toCharArray(), 0, bigView, BigFaithPlayerProdPos[i], Integer.toString(user.getProductions().size()).toCharArray().length);
                        System.arraycopy(Integer.toString(user.getChest().getSize()).toCharArray(), 0, bigView, BigFaithPlayerRssPos[i], Integer.toString(user.getChest().getSize()).toCharArray().length);
                        System.arraycopy(Integer.toString(user.getVictoryPoints()).toCharArray(), 0, bigView, BigFaithPlayerPVPos[i], Integer.toString(user.getVictoryPoints()).toCharArray().length);
                        bigView[BigFaithCellPosition[user.getFaithPoints() + i*25]] = (char)(i+1);
                        i++;
                    }
                    System.out.println(bigView);
                    System.out.println("Insert Command (Exit): ");
                    command = input.nextLine();
                    command = command.toUpperCase();
                    if ("EXIT".equals(command)) {
                        System.out.println("redirecting to Home..");
                    } else {
                        System.out.println("Wrong Command, but you are very lucky, i'm redirecting you to Home anyway..");
                    }
                    value = "HOME";
                    break;

                case "QUIT":
                    charArray = readSchematics(7);
                    System.out.println(charArray);
                    Thread.sleep(1500);
                    value = "exit";
                    break;

                default:
                   // cls();
                    System.out.println("Wrong Command, please insert a real command, redirecting to Home..");
                    value = "HOME";
                    break;
            }
        }
    }

    private static void LeaderCardInfoExtractor(char[] home, List<LeaderCard> leaderCard, int i, int[] homeLeaderType, int[] homeLeaderPV, int[] homeLeaderCost) {
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

    private void Production(String productionString) {
        if(productionString.equals("0")){
            System.out.println("Please insert data for basic production (in1-in2-out): ");
            String basicProd = input.nextLine();
            String[] basicProdArray = basicProd.split("-");
            //BasicProduction message = new BasicProduction(basicProdArray[0], basicProdArray[1],basicProdArray[2]);
            //notify(message);
        }else{
            CardProduction message = new CardProduction(Integer.parseInt(productionString));
            notify(message);
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

    private static char[] readSchematics(int x) throws FileNotFoundException {
        File file = new File(rssPath + currentFile[x]);
        Scanner scanner = new Scanner(file);
        StringBuilder theString = new StringBuilder(scanner.nextLine());
        while (scanner.hasNextLine()) {
            theString.append("\n").append(scanner.nextLine());
        }
        return theString.toString().toCharArray();
    }

    private static String getColorStringFromMarble(List<Marble> colorList){
        int[] colorMarble = {0,0,0,0};
        for (Marble marble: colorList) {
            String color = marble.getColor().toString();
            colorExtractor(colorMarble, color);
        }
        return stringBuilder(colorMarble);
    }

    private static String getColorString(List<Marble.Color> colorList){
        int[] colorMarble = {0,0,0,0};
        for (Marble.Color marble: colorList) {
            String color = marble.toString();
            colorExtractor(colorMarble, color);
        }
        return stringBuilder(colorMarble);
    }

    private static void colorExtractor(int[] colorMarble, String color) {
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

    private static String stringBuilder(int[] colorMarble) {
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

    public static void main(String[] args) throws IOException, InterruptedException {
        CLI_Controller controller = new CLI_Controller();
        controller.CLIView();
    }
}
