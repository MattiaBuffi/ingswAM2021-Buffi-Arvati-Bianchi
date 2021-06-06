package it.polimi.ingsw.Model.CardStorage;

import it.polimi.ingsw.Message.Model.DevelopmentCardBuyUpdate;
import it.polimi.ingsw.Model.EventBroadcaster;
import it.polimi.ingsw.Model.ProductionCard.DevelopmentCard;
import it.polimi.ingsw.View.ModelData.ReducedDataModel.DevelopmentCardData;

import java.util.ArrayList;
import java.util.List;

public class CardSlot {

    private EventBroadcaster broadcaster;
    private final int position;

    private DevelopmentCard activeCard;
    private List<DevelopmentCard> inactiveCard;


    public CardSlot(int position, EventBroadcaster broadcaster) {
        this.position = position;
        this.inactiveCard = new ArrayList<>();
        this.broadcaster = broadcaster;
    }


    public int getPosition() {
        return position;
    }


    public DevelopmentCard getActiveCard(){
        return activeCard;
    }

    public List<DevelopmentCard> getCards(){
        List<DevelopmentCard> list = new ArrayList<>();
        if(activeCard != null){
            list.add(activeCard);
        }
        list.addAll(inactiveCard);
        return list;
    }


    public void setCard(DevelopmentCard card){
        inactiveCard.add(activeCard);
        activeCard = card;
        broadcaster.notifyAllPlayers(new DevelopmentCardBuyUpdate(position, card.getId(), card.getVictoryPoint(), card.getRequire(), card.getProduce()));
    }

    public int getLevel(){
        if(activeCard == null){
            return 0;
        } else {
            return activeCard.getLevel();
        }
    }

}
