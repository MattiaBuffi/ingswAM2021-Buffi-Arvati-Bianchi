package it.polimi.ingsw.Model.CardMarket;

import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.ResourceList;
import it.polimi.ingsw.Model.ProductionCard.DevelopmentCard;
import it.polimi.ingsw.Model.ResourceStorage.ResourceStorage;

import java.util.Collections;
import java.util.List;

public class NullCard extends  PurchasableCard{

    private static NullCard instance;

    private static class NullDevelopmentCard extends DevelopmentCard{


        private NullDevelopmentCard(String id, ResourceList require, ResourceList produce, int level, Color color, int victoryPoint) {
            super(id, require, produce, level, color, victoryPoint);
        }

        @Override
        public List<Marble> make(ResourceStorage storage) {
            return Collections.EMPTY_LIST;
        }
    }


    private NullCard(CardRemover remover, ResourceList cost, DevelopmentCard card) {
        super(remover, cost, card);
    }


    @Override
    public void buy() {
        return;
    }

    public static NullCard get(){
        if(instance == null){
            instance = new NullCard(null, new ResourceList(),new NullDevelopmentCard("null", new ResourceList(), new ResourceList(), 0, null, 0));
        }
        return instance;
    }


}