package it.polimi.ingsw.Model.CardMarket;



import it.polimi.ingsw.Message.Model.MarketCardUpdate;
import it.polimi.ingsw.Model.EventBroadcaster;
import it.polimi.ingsw.Model.ProductionCard.DevelopmentCard;
import it.polimi.ingsw.Parser.ProductionCard.CardParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardMarket implements CardRemover{


    private static final int COLOR_SIZE = DevelopmentCard.Color.values().length;
    private static final int MAX_LEVEL = 3;

    private static final int GREEN_INDEX = 0;
    private static final int BLUE_INDEX = 1;
    private static final int YELLOW_INDEX = 2;
    private static final int PURPLE_INDEX = 3;


    private EventBroadcaster broadcaster;
    private List<List<List<PurchasableCard>>> cardMatrix;




    public CardMarket(EventBroadcaster broadcaster){

        this.broadcaster = broadcaster;
        this.cardMatrix = new ArrayList<>();

        initMatrix(cardMatrix, COLOR_SIZE, MAX_LEVEL);

        List<PurchasableCard> cards = CardParser.getMarketCards(this);
        Collections.shuffle(cards);
        
        for (PurchasableCard pc: cards){
            store(pc, getColorIndex(pc.getColor()), pc.getLevel()-1);
        }

        for (int j = 0; j < COLOR_SIZE; j++) {

            for (int i = 0; i < MAX_LEVEL; i++) {
                notifyNewCard(cardMatrix.get(j).get(i).get(0));
            }

        }

    }

    public void initMatrix(List<List<List<PurchasableCard>>> matrix, int columns, int rows){

        for (int j = 0; j < columns; j++) {

            matrix.add(new ArrayList<>());

            for (int i = 0; i < rows; i++) {
                matrix.get(j).add(new ArrayList<>());
            }

        }
    }

    private int getColorIndex(DevelopmentCard.Color color){
        switch (color){
            case GREEN:
                return GREEN_INDEX;
            case YELLOW:
                return YELLOW_INDEX;
            case PURPLE:
                return PURPLE_INDEX;
            case BLUE:
                return BLUE_INDEX;
            default:
                throw new IllegalArgumentException();
        }
    }

    private void store(PurchasableCard card, int color, int level){
        cardMatrix.get(color).get(level).add(card);
    }




    public List<PurchasableCard> getLevelList(List<List<List<PurchasableCard>>> cardMatrix, int column, int row){
        return cardMatrix.get(column).get(row);
    }




    public PurchasableCard getCard(int x_color, int y_level){
        return cardMatrix.get(x_color).get(y_level).get(0);
    }

    public PurchasableCard getCard(DevelopmentCard.Color color, int level){
        return cardMatrix.get(getColorIndex(color)).get(level-1).get(0);
    }

    public PurchasableCard getCard(String id){

        for (int j = 0; j < COLOR_SIZE; j++) {

            for (int i = 0; i < MAX_LEVEL; i++) {
                if( id == cardMatrix.get(j).get(i).get(0).getId()){
                    return cardMatrix.get(j).get(i).get(0);
                }
            }
        }

        return null;

    }


    @Override
    public boolean removeCard(DevelopmentCard.Color color, int level) {
        //List<PurchasableCard> cards = cardMatrix.get(getColorIndex(color)).get(level-1);
        List<PurchasableCard> cards = getLevelList(cardMatrix, getColorIndex(color),level-1);

        if(cards.size() == 0){
            return false;
        }

        notifyNewCard(cards.remove(0));

        return true;
    }

    @Override
    public boolean removeCard(DevelopmentCard.Color color) {
        for (int i = 1; i < 4; i++) {
            if(removeCard(color,i)){
                return true;
            }
        }
        return false;
    }


    private void notifyNewCard(PurchasableCard purchasableCard){
        DevelopmentCard card = purchasableCard.getCard();
        broadcaster.notifyAllPlayers(new MarketCardUpdate(getColorIndex(card.getColor()),
                card.getLevel()-1,
                card.getId(),
                card.getVictoryPoint(),
                purchasableCard.getCost(),
                card.getRequire(),
                card.getProduce()
        ));
    }




}
