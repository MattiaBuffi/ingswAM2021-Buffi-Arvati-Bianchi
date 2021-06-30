package it.polimi.ingsw.Model.CardMarket;

import it.polimi.ingsw.Model.ProductionCard.DevelopmentCard;

public interface CardRemover {


    /**
     * Remove the first card from the list of cards in the market with the specified color and level
     * @param color Color of the card to remove
     * @param level Level of the card to remove
     */
    boolean removeCard(DevelopmentCard.Color color, int level);


    /**
     * Remove a card from the market of the specified color, starting from the lower level
     * @param color Color of the card to remove
     */

    boolean removeCard(DevelopmentCard.Color color);


}
