package it.polimi.ingsw.Model.Marble;

import java.io.Serializable;

public abstract class Marble implements Serializable {

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
