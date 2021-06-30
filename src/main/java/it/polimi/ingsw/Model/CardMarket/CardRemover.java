package it.polimi.ingsw.Model.CardMarket;

import it.polimi.ingsw.Model.ProductionCard.DevelopmentCard;

public interface CardRemover {


    /**
     *  rimuove una carta dalla pila di uno specifico colore e livello
     */
    boolean removeCard(DevelopmentCard.Color color, int level);


    /**
     *  rimuove una carta dalla pila di uno specifico colore, inizia dal livello piu basso
     */
    boolean removeCard(DevelopmentCard.Color color);


}
