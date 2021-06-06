
package it.polimi.ingsw.Model.ActionTokens;


import it.polimi.ingsw.Model.CardMarket.CardRemover;
import it.polimi.ingsw.Model.GameTerminator;
import it.polimi.ingsw.Model.ProductionCard.DevelopmentCard;


public class DiscardActionToken implements ActionToken{

    private DevelopmentCard.Color color;
    private CardRemover remover;
    private GameTerminator terminator;

    public DiscardActionToken(CardRemover remover, DevelopmentCard.Color color, GameTerminator terminator) {
        this.color = color;
        this.remover = remover;
        this.terminator = terminator;
    }

    @Override
    public void activate() {
        if(!remover.removeCard(color)){
            terminator.endGame();
            return;
        }
        if(!remover.removeCard(color)){
            terminator.endGame();
            return;
        }
    }

}
