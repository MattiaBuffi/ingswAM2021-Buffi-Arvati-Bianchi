package it.polimi.ingsw.Model.Marble;

public abstract class Marble {

    public enum Color {
        RED,
        WHITE,
        YELLOW,
        GREY,
        BLUE,
        PURPLE;
    }

    public abstract Color getColor();

    public abstract void accept(MarbleHandler handler);

}
