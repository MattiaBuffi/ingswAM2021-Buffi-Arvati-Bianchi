package it.polimi.ingsw.Model.Marble;

/**
 * Represent a white marble
 */
public class WhiteMarble extends Marble {

    private static final long serialVersionUID = 5442355404580851850L;

    protected WhiteMarble(){}

    @Override
    public Color getColor() {
        return Color.WHITE;
    }

    public void accept(MarbleHandler handler){
        handler.handle(this);
    }



}
