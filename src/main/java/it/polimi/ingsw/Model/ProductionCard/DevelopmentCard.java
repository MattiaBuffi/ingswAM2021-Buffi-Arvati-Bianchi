package it.polimi.ingsw.Model.ProductionCard;

import it.polimi.ingsw.Model.Marble.ResourceList;

public class DevelopmentCard extends ProductionCard{

    private int level;
    private int color;

    public DevelopmentCard(ResourceList require, ResourceList produce) {
        super(require, produce);
    }


    public int getLevel() {
        return level;
    }

    public int getColor() {
        return color;
    }


}
