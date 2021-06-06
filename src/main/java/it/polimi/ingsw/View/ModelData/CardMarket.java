package it.polimi.ingsw.View.ModelData;

import it.polimi.ingsw.View.ModelData.ReducedDataModel.DevelopmentCardData;

import java.util.ArrayList;
import java.util.List;

public class CardMarket {

    private List<List<DevelopmentCardData>> cards;

    public CardMarket(){
        cards = new ArrayList<>();
    }

    public DevelopmentCardData getCard(int x, int y){
        return cards.get(x).get(y);
    }

    public void setCard(int x, int y, DevelopmentCardData card){
        cards.get(x).add(y, card);
    }

}
