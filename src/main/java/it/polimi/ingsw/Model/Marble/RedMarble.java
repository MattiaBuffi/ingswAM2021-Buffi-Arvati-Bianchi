package it.polimi.ingsw.Model.Marble;




public class RedMarble extends Marble{

    protected RedMarble(){};

    @Override
    public MarbleColor getColor() {
        return MarbleColor.RED;
    }

    public void accept(MarbleVisitor visitor){
        visitor.visit(this);
    }

}
