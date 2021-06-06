package it.polimi.ingsw.Model.CardMarket;

import it.polimi.ingsw.Model.ProductionCard.DevelopmentCard;

public interface CardRemover {

    boolean removeCard(DevelopmentCard.Color color, int level);

    boolean removeCard(DevelopmentCard.Color color);


}
