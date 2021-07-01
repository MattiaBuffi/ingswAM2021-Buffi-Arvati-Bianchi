package it.polimi.ingsw.Model.Marble;

import java.io.Serializable;

/**
 * Represent a generic marble
 */
public abstract class Marble implements Serializable {

    private static final long serialVersionUID = -821734160554897214L;

    public enum Color {
        RED,
        WHITE,
        YELLOW,
        GREY,
        BLUE,
        PURPLE;
    }

    public abstract Color getColor();

    /**
     * Method for accepting handler of the visitor pattern
     * @param handler
     */
    public abstract void accept(MarbleHandler handler);

}
