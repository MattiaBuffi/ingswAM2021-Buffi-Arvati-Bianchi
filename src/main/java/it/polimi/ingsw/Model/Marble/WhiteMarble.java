package it.polimi.ingsw.Model.Marble;

public class WhiteMarble extends Marble {

    protected WhiteMarble(){}

    @Override
    public MarbleColor getColor() {
        return MarbleColor.WHITE;
    }

    public void accept(MarbleVisitor visitor){
        visitor.visit(this);
    }



}
