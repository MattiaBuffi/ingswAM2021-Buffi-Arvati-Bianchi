package it.polimi.ingsw.Model.Marble;

/**
 * Represent a red marble
 */
public class RedMarble extends Marble{

    protected RedMarble(){};

    @Override
    public Color getColor() {
        return Color.RED;
    }

    public void accept(MarbleHandler handler){
        handler.handle(this);
    }

}
