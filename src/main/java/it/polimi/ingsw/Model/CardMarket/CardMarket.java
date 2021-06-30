package it.polimi.ingsw.Model.CardMarket;



import it.polimi.ingsw.Message.Model.MarketCardUpdate;
import it.polimi.ingsw.Model.EventBroadcaster;
import it.polimi.ingsw.Model.ProductionCard.DevelopmentCard;
import it.polimi.ingsw.Parser.ProductionCard.CardParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * rappresenta il mercato delle carte
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
     *usato per inizializzare il mercato delle carte
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
     *ritorna l'indice della colonna del mercato delle carte corrispondente ad un certo colore
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
     *inserisce una carta nella matrice del mercato nella posizione specificata
     */
    private void store(PurchasableCard card, int color, int level){
        cardMatrix.get(color).get(level).add(card);
    }



    /**
     *  ritorna la pila di carte conservata nella posizione specificata
     */
    public List<PurchasableCard> getLevelList(List<List<List<PurchasableCard>>> cardMatrix, int column, int row){
        return cardMatrix.get(column).get(row);
    }



    /**
     *ritorna la prima carta conservata nella pila nella posizione specificata
     */
    public PurchasableCard getCard(int x_color, int y_level){
        return cardMatrix.get(x_color).get(y_level).get(0);
    }


    /**
     *ritorna la prima carta conservata nella pila nella posizione specificata
     */
    public PurchasableCard getCard(DevelopmentCard.Color color, int level){
        return cardMatrix.get(getColorIndex(color)).get(level-1).get(0);
    }

    /**
     *ritorna la prima carta con l'id specificato se questa è in ad una pila di carte, ritona null altrimenti
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
     *notifica a tutti i giocatori che una carta in una certa posizione è cambiata
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
