package it.polimi.ingsw.Model.Marble;

public class WhiteMarble extends Marble {

    protected WhiteMarble(){}

    @Override
    public Color getColor() {
        return Color.WHITE;
    }

    public void accept(MarbleHandler handler){
        handler.handle(this);
    }



}
