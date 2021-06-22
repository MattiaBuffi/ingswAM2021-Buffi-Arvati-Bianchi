package it.polimi.ingsw.Client.CLI.Pages;

import it.polimi.ingsw.Client.CLI.CLI_Controller;
import it.polimi.ingsw.Client.ModelData.Player;
import it.polimi.ingsw.Client.ViewBackEnd;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class ViewPage {

    private static final int[] BigFaithCellPosition ={1739,1745,1751,1086,421,427,433,439,445,451,1116,1781,1787,1793,1799,1805,1811,1146,481,487,493,499,505,511,517,
            1742,1748,1754,1089,424,430,436,442,448,454,1119,1784,1790,1796,1802,1808,1814,1149,484,490,496,502,508,514,520,
            2005,2011,2017,1352,687,693,699,705,711,717,1382,2047,2053,2059,2065,2071,2077,1412,747,753,759,765,771,777,783,
            2008,2014,2020,1355,690,696,702,708,714,717,1385,2050,2056,2062,2068,2074,2080,1415,750,756,762,768,774,780,786};
    private static final int[] BigFaithPlayerNamePos = {2800,2820,2840,2860};
    private static final int[] BigFaithPlayerRssPos = {3068,3088,3108,3128};
    private static final int[] BigFaithPlayerProdPos = {3201,3221,3241,3261};
    private static final int[] BigFaithPlayerPVPos = {3334,3354,3374,3394};

    ViewBackEnd backEnd;

    public ViewPage(ViewBackEnd backEnd) {
        this.backEnd = backEnd;
    }

    public void ViewPageView() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        char[] bigView = CLI_Controller.readSchematics(10);
        int i = 0;
        List<Player> users = backEnd.getModel().players;
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
        String command = input.nextLine().toUpperCase();
        if (command.equals("EXIT")) {
            System.out.println("redirecting to Home..");
        } else {
            System.out.println("Wrong Command, but you are very lucky, i'm redirecting you to Home anyway..");
        }
        CLI_Controller.scene="HOME";
    }
}
