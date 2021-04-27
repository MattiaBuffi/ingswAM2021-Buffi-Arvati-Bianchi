package it.polimi.ingsw.Model.ProductionCard;

import it.polimi.ingsw.Model.Marble.ResourceList;

public class DevelopmentCard extends ProductionCard{

    private int level;
    private DevelopmentCardColor color;

    public DevelopmentCard(String id, ResourceList require, ResourceList produce, int level, DevelopmentCardColor color) {
        super(id,require, produce);

        this.level= level;
        this.color = color;
    }

    public int getLevel() {
        return level;
    }

    public DevelopmentCardColor getColor() {
        return color;
    }


}
