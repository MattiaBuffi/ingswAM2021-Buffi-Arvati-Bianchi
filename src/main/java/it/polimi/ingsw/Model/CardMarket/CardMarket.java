package it.polimi.ingsw.Model.CardMarket;



import it.polimi.ingsw.Message.Model.MarketCardUpdate;
import it.polimi.ingsw.Model.EventBroadcaster;
import it.polimi.ingsw.Model.ProductionCard.DevelopmentCard;
import it.polimi.ingsw.Parser.ProductionCard.CardParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Represent the card market
 */
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


    /**
     * Initialize the card market
     */
    public void initMatrix(List<List<List<PurchasableCard>>> matrix, int columns, int rows){

        for (int j = 0; j < columns; j++) {

            matrix.add(new ArrayList<>());

            for (int i = 0; i < rows; i++) {
                matrix.get(j).add(new ArrayList<>());
            }

        }
    }

    /**
     * Return the column index of the specified color
     * @param color Color of the desired column
     */
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

    /**
     * Insert the card passed as parameter in the cell specified by color and leve parameters
     * @param card Card to insert
     * @param color Color of the column where to insert the card
     * @param level Level of the card to identify the cell where to insert
     */
    private void store(PurchasableCard card, int color, int level){
        cardMatrix.get(color).get(level).add(card);
    }



    /**
     * Return the list of cards in the cell of the market specified by the input parameters
     * @param column Coordinate X of the market grid
     * @param row Coordinate Y of the marked grid
     * @return List of the cards in the specified cells of the market
     */
    public List<PurchasableCard> getLevelList(List<List<List<PurchasableCard>>> cardMatrix, int column, int row){
        return cardMatrix.get(column).get(row);
    }

    /**
     * Return first card in the list of cards in the cell of the market specified by the input parameters
     * @param x_color Coordinate X of the market grid
     * @param y_level Coordinate Y of the market grid
     * @return Card in the specified position
     */
    public PurchasableCard getCard(int x_color, int y_level){
        return cardMatrix.get(x_color).get(y_level).get(0);
    }

    /**
     * Return first card in the list of cards in the cell of the market specified by the input parameters
     * @param color Color of the market column
     * @param level Level of the card to return
     * @return Card in the specified position
     */
    public PurchasableCard getCard(DevelopmentCard.Color color, int level){
        return cardMatrix.get(getColorIndex(color)).get(level-1).get(0);
    }

    /**
     * Return the card from the market with the id specified as parameter
     * @param id Id of the card to return
     * @return Card with specified id if exists, otherwise, if the card doesn't exist return null
     */
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

    /**
     * Remove the first card from the list of cards in the market with the specified color and level
     * @param color Color of the card to remove
     * @param level  Level of the card to remove
     * @return True if was possible to remove the card or False if the list of cards of the specified position is empty
     */
    @Override
    public boolean removeCard(DevelopmentCard.Color color, int level) {
        //List<PurchasableCard> cards = cardMatrix.get(getColorIndex(color)).get(level-1);
        List<PurchasableCard> cards = getLevelList(cardMatrix, getColorIndex(color),level-1);

        if(cards.size() == 0){
            return false;
        }

        cards.remove(0);
        if(cards.size() == 0){
            return true;
        }
        notifyNewCard(cards.get(0));

        return true;
    }


    /**
     * Remove a card from the market of the specified color
     * @param color Color of the card to remove
     * @return True if was possible to remove the card
     */
    @Override
    public boolean removeCard(DevelopmentCard.Color color) {
        for (int i = 1; i < 4; i++) {
            if(removeCard(color,i)){
                return true;
            }
        }
        return false;
    }


    /**
     * Notify all the players that a card in a certain position of the market is changed
     */
    private void notifyNewCard(PurchasableCard purchasableCard){
        DevelopmentCard card = purchasableCard.getCard();
        broadcaster.notifyAllPlayers(new MarketCardUpdate(
                getColorIndex(card.getColor()),
                card.getLevel()-1,
                card.getId(),
                card.getVictoryPoint(),
                purchasableCard.getCost(),
                card.getRequire(),
                card.getProduce(),
                card.getColor()
        ));
    }




}
