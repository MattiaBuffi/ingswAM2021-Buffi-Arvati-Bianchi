package it.polimi.ingsw.Client.ModelData;

import it.polimi.ingsw.Client.ModelData.ReducedDataModel.DevelopmentCardData;

import java.util.ArrayList;
import java.util.List;

public class CardMarket {

    private boolean changed;

    private List<List<DevelopmentCardData>> cards;

    public CardMarket(){
        cards = new ArrayList<>();
        cards.add(new ArrayList<>());
        cards.add(new ArrayList<>());
        cards.add(new ArrayList<>());
        cards.add(new ArrayList<>());
    }


    public DevelopmentCardData getCard(int x, int y){
        return cards.get(x).get(y);
    }


    public void setCard(int x, int y, DevelopmentCardData card){
        cards.get(x).add(y, card);
    }






}
