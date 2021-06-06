package it.polimi.ingsw.Model.ProductionCard;

import it.polimi.ingsw.Model.Marble.ResourceList;

public class DevelopmentCard extends ProductionCard{

    public enum Color {
        GREEN,
        BLUE,
        YELLOW,
        PURPLE;
    }

    public static class Info {

        public final int level;
        public final Color color;

        public Info(int level, Color color) {
            this.level = level;
            this.color = color;
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
