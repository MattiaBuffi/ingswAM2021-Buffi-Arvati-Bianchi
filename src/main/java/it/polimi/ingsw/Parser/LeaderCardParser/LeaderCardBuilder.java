package it.polimi.ingsw.Parser.LeaderCardParser;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import it.polimi.ingsw.Model.LeaderCard.ActivationStrategy.ActivationStrategy;
import it.polimi.ingsw.Model.LeaderCard.LeaderCard;
import it.polimi.ingsw.Model.LeaderCard.RequirementsStrategy.RequireCards;
import it.polimi.ingsw.Model.LeaderCard.RequirementsStrategy.RequirementStrategy;
import it.polimi.ingsw.Model.ProductionCard.DevelopmentCard;
import it.polimi.ingsw.Parser.Decoder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class LeaderCardBuilder {

    private class LeaderBuilder{

        private RequirementStrategy requirementStrategy;
        private ActivationStrategy activationStrategy;
        private String id;
        private int victoryPoints;

        public LeaderBuilder setRequirementStrategy(RequirementStrategy requirementStrategy) {
            this.requirementStrategy = requirementStrategy;
            return this;
        }

        public LeaderBuilder setActivationStrategy(ActivationStrategy activationStrategy) {
            this.activationStrategy = activationStrategy;
            return this;
        }

        public LeaderBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public LeaderBuilder setVictoryPoints(int victoryPoints) {
            this.victoryPoints = victoryPoints;
            return this;
        }

        public LeaderCard build(){
            return new LeaderCard(id, victoryPoints,requirementStrategy, activationStrategy);
        }

    }


    @SerializedName("RequirementType")
    @Expose
    private String id;
    @SerializedName("RequirementType")
    @Expose
    private String requirementType;
    @SerializedName("Requirement")
    @Expose
    private List<String> requirement;
    @SerializedName("Victory_Points")
    @Expose
    private Integer victory_Points;
    @SerializedName("EffectType")
    @Expose
    private String effectType;
    @SerializedName("ColorEffect")
    @Expose
    private String colorEffect;


    public LeaderCard getCard(){
        return new LeaderBuilder()
                .setId(this.id)
                .setVictoryPoints(this.victory_Points)
                .setRequirementStrategy(Decoder.decodeRequirementStrategy(requirementType, requirement))
                .setActivationStrategy(Decoder.decodeActivationStrategy(effectType, colorEffect))
                .build();
    }






}