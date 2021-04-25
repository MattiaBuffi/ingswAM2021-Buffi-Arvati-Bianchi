package it.polimi.ingsw.Model.Marble;

public class ResourceMarble extends Marble  {

    private final MarbleColor color;

    protected ResourceMarble(MarbleColor color){
        this.color = color;
    }

    @Override
    public MarbleColor getColor() {
        return color;
    }

    public void accept(MarbleVisitor visitor){
        visitor.visit(this);
    }



}
