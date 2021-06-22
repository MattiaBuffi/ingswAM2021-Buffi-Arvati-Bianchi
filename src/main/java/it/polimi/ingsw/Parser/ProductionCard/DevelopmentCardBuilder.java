package it.polimi.ingsw.Parser.ProductionCard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import it.polimi.ingsw.Model.CardMarket.CardMarket;
import it.polimi.ingsw.Model.CardMarket.PurchasableCard;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.ResourceList;
import it.polimi.ingsw.Model.ProductionCard.DevelopmentCard;
import it.polimi.ingsw.Parser.Decoder;

import java.util.List;

public class DevelopmentCardBuilder {

    private class CardBuilder{
        private String id;
        private int victoryPoints;
        private ResourceList require;
        private ResourceList produce;
        private int level;
        private DevelopmentCard.Color color;
        private ResourceList cost;


        private PurchasableCard getCard(CardMarket market){
            return new PurchasableCard(market, cost, new DevelopmentCard(id,require,produce,level,color,victory_Points));
        }

        public CardBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public CardBuilder setVictoryPoint(int point) {
            this.victoryPoints = point;
            return this;
        }

        public CardBuilder setRequire(ResourceList require) {
            this.require = require;
            return this;
        }

        public CardBuilder setProduce(ResourceList produce) {
            this.produce = produce;
            return this;
        }

        public CardBuilder setLevel(int level) {
            this.level = level;
            return this;
        }

        public CardBuilder setColor(DevelopmentCard.Color color) {
            this.color = color;
            return this;
        }

        public CardBuilder setCost(ResourceList cost) {
            this.cost = cost;
            return this;
        }
    }


    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Color")
    @Expose
    private DevelopmentCard.Color color;
    @SerializedName("Level")
    @Expose
    private Integer level;
    @SerializedName("Victory_Points")
    @Expose
    private Integer victory_Points;
    @SerializedName("Cost")
    @Expose
    private List<String> cost;
    @SerializedName("Require")
    @Expose
    private List<String> require;
    @SerializedName("Produce")
    @Expose
    private List<String> produce;


    public PurchasableCard getCard(CardMarket market){
        return new CardBuilder()
                .setId(id)
                .setColor(getColor())
                .setLevel(getLevel())
                .setVictoryPoint(getVictory_Points())
                .setRequire(getRequire())
                .setProduce(getProduce())
                .setCost(getCost())
                .getCard(market);
    }


    private DevelopmentCard.Color getColor() {
        return color;
    }

    private Integer getLevel() {
        return level;
    }

    private Integer getVictory_Points() {
        return victory_Points;
    }

    private ResourceList getCost() {
        return Decoder.decodeList(cost);
    }

    private ResourceList getRequire() {
        return Decoder.decodeList(require);
    }

    private ResourceList getProduce() {
        return Decoder.decodeList(produce);
    }



}