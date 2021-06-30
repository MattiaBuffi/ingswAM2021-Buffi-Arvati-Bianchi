package it.polimi.ingsw.Model.ProductionCard;

import it.polimi.ingsw.Model.Marble.ResourceList;



/**
 *  Represent a development card
 */
public class DevelopmentCard extends ProductionCard{

    public enum Color {
        GREEN,
        BLUE,
        YELLOW,
        PURPLE;
    }


    /**
     * Describe level and color on the development card
     */
    public static class Info {

        public final int level;
        public final Color color;

        public Info(int level, Color color) {
            this.level = level;
            this.color = color;
        }

        public String toString(){
            return level+"-"+String.valueOf(color).toUpperCase().charAt(0);
        }

    }


    private int victoryPoint;
    private Info info;

    public DevelopmentCard(String id, ResourceList require, ResourceList produce, int level,
                           Color color, int victoryPoint) {
        super(id,require, produce);

        this.victoryPoint = victoryPoint;
        this.info = new Info(level, color);

    }


    public Info getInfo(){
        return info;
    }

    public int getVictoryPoint() {
        return victoryPoint;
    }

    public int getLevel() {
        return info.level;
    }

    public Color getColor() {
        return info.color;
    }


}
