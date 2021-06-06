package it.polimi.ingsw.Model.Marble;

public class ResourceMarble extends Marble  {

    private final Color color;

    protected ResourceMarble(Color color){
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    public void accept(MarbleHandler handler){
        handler.handle(this);
    }



}
