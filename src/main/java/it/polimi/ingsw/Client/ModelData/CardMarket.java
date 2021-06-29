package it.polimi.ingsw.Client.ModelData;

import it.polimi.ingsw.Client.ModelData.ReducedDataModel.DevelopmentCardData;

import java.util.ArrayList;
import java.util.List;

public class CardMarket {

    private boolean changed;

    private DevelopmentCardData[][] cards;

    public CardMarket(){
        cards = new DevelopmentCardData[4][3];
    }


    public DevelopmentCardData getCard(int x, int y){
        return cards[x][y];
    }


    public void setCard(int x, int y, DevelopmentCardData card){
        cards[x][y] = card;
    }






}
