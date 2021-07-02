package it.polimi.ingsw.Model.LeaderCard.RequirementsStrategy;

import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.ProductionCard.DevelopmentCard;

import java.util.ArrayList;
import java.util.List;

/**
 *  Check if a Player has accomplished CardRequirement to activate a Leader Card
 */
public class RequireCards implements RequirementStrategy {

    private List<DevelopmentCard.Info> requiredCards;

    public RequireCards(List<DevelopmentCard.Info> requiredCards) {
        this.requiredCards = requiredCards;
    }

    private List<DevelopmentCard.Info> extractPlayerCardInfo(Player player){
        List<DevelopmentCard> playerCards = player.getCardStorage().getCards();
        List<DevelopmentCard.Info> playerCardInfo = new ArrayList<>();

        for(DevelopmentCard card: playerCards){
            playerCardInfo.add(card.getInfo());
        }

        return playerCardInfo;
    }

    public List<DevelopmentCard.Info> getRequiredCards() {
        return requiredCards;
    }

    /**
     * Check if the player can activate a Leader Card
     * @param player Player who wants to activate a Leader Card
     * @return True if the player can Activate a Leader Card
     */
    @Override
    public boolean canActivate(Player player) {

        List<DevelopmentCard.Info> playerCardInfo = extractPlayerCardInfo(player);

        for(DevelopmentCard.Info info: requiredCards){
            boolean found = false;
            for (DevelopmentCard.Info playerInfo: playerCardInfo){
                if(info.color != playerInfo.color){
                    continue;
                }
                if(info.level > playerInfo.level){
                    continue;
                }
                playerCardInfo.remove(playerInfo);
                found = true;
                break;
            }
            if(!found){
                return false;
            }
        }
        return true;

    }

    /**
     * @return Requirement Type of a Leader Card
     */
    @Override
    public Type getType() {
        return Type.CARD;
    }
}
