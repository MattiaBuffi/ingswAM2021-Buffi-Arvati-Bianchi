package it.polimi.ingsw.Model.ProductionCard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import it.polimi.ingsw.Model.CardMarket.CardMarket;
import it.polimi.ingsw.Model.CardMarket.PurchasableCard;
import it.polimi.ingsw.Model.CardMarket.PurchasableCardBasic;
import it.polimi.ingsw.Model.Marble.MarbleColor;
import it.polimi.ingsw.Model.Marble.ResourceList;
import it.polimi.ingsw.Model.ProductionCard.DevelopmentCard;
import it.polimi.ingsw.Model.ProductionCard.DevelopmentCardColor;

import java.util.List;

public class DevelopmentCardBuilder {

    private class CardBuilder{
        private String id;
        private int victoryPoints;
        private ResourceList require;
        private ResourceList produce;
        private int level;
        private DevelopmentCardColor color;
        private ResourceList cost;


        private PurchasableCard getCard(CardMarket market){
            return new PurchasableCardBasic(market, cost, new DevelopmentCard(id,require,produce,level,color));
        }

        public CardBuilder setId(String id) {
            this.id = id;
            return null;
        }

        public CardBuilder setVictoryPoint(int point) {
            this.victoryPoints = point;
            return null;
        }

        public CardBuilder setRequire(ResourceList require) {
            this.require = require;
            return null;
        }

        public CardBuilder setProduce(ResourceList produce) {
            this.produce = produce;
            return null;
        }

        public CardBuilder setLevel(int level) {
            this.level = level;
            return null;
        }

        public CardBuilder setColor(DevelopmentCardColor color) {
            this.color = color;
            return null;
        }

        public CardBuilder setCost(ResourceList cost) {
            this.cost = cost;
            return null;
        }
    }

    @SerializedName("Color")
    @Expose
    private DevelopmentCardColor color;
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


    protected PurchasableCard buildCard(CardMarket market){
        return new CardBuilder()
                .setId("")
                .setColor(getColor())
                .setLevel(getLevel())
                .setVictoryPoint(getVictory_Points())
                .setRequire(getRequire())
                .setProduce(getProduce())
                .setCost(getCost())
                .getCard(market);
    }




    private ResourceList decodeList(List<String> list){
        ResourceList decodedList = new ResourceList();
        for(String s: list){
            decodedList.add(decodeColor(s.charAt(1)), Character.getNumericValue(s.charAt(0)));
        }
        return decodedList;
    }

    private MarbleColor decodeColor(Character string){
        switch (string){
            case 'R':
                return MarbleColor.RED;
            case 'W':
                return MarbleColor.WHITE;
            case 'Y':
                return MarbleColor.YELLOW;
            case 'G':
                return MarbleColor.GREY;
            case 'B':
                return MarbleColor.BLUE;
            case 'P':
                return MarbleColor.PURPLE;
            default:
                return null;
        }
    }


    private DevelopmentCardColor getColor() {
        return null;
    }

    private Integer getLevel() {
        return level;
    }

    private Integer getVictory_Points() {
        return victory_Points;
    }

    private ResourceList getCost() {
        return decodeList(cost);
    }

    private ResourceList getRequire() {
        return decodeList(require);
    }

    private ResourceList getProduce() {
        return decodeList(produce);
    }



}