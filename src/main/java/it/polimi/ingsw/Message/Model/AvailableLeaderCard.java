package it.polimi.ingsw.Message.Model;

import it.polimi.ingsw.Client.ModelData.ReducedDataModel.LeaderCard;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.ModelEventHandler;
import it.polimi.ingsw.Model.LeaderCard.RequirementsStrategy.RequireCards;
import it.polimi.ingsw.Model.LeaderCard.RequirementsStrategy.RequireResources;
import it.polimi.ingsw.Model.Marble.ResourceList;
import it.polimi.ingsw.Model.ProductionCard.DevelopmentCard;

import java.util.ArrayList;
import java.util.List;

public class AvailableLeaderCard implements Message<ModelEventHandler> {

    private List<LeaderCard> viewLeaderCard;

    public AvailableLeaderCard(List<it.polimi.ingsw.Model.LeaderCard.LeaderCard> cards){
        viewLeaderCard = new ArrayList<>();
        for(it.polimi.ingsw.Model.LeaderCard.LeaderCard c: cards){
            viewLeaderCard.add(extractLeaderCard(c));
        }
    }

    private LeaderCard extractLeaderCard(it.polimi.ingsw.Model.LeaderCard.LeaderCard card){
        LeaderCard leaderCard = new LeaderCard();
        leaderCard.setId(card.getId());
        leaderCard.setActive(card.isActive());
        leaderCard.setColor(card.getActivationStrategy().getColor());
        leaderCard.setVictoryPoint(card.getVictoryPoints());
        switch (card.getRequirementsStrategy().getType()){
            case CARD:{
                RequireCards requireCards = (RequireCards)card.getRequirementsStrategy();
                List<String> encodedInfo = new ArrayList<>();
                for(DevelopmentCard.Info info: requireCards.getRequiredCards()){
                    encodedInfo.add(info.toString());
                }
                leaderCard.setDevelopmentCardRequirement(encodedInfo);
                break;}
            case RESOURCE:{
                RequireResources requireResources = (RequireResources) card.getRequirementsStrategy();
                leaderCard.setResourceRequirement(ResourceList.getAllColor(requireResources.getResource().getAllMarble()));
                break;
            }
        }
        return leaderCard;
    }

    public List<LeaderCard> getLeaderCard() {
        return viewLeaderCard;
    }

    @Override
    public void accept(ModelEventHandler handler) {
        handler.handle(this);
    }
}
