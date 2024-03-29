package it.polimi.ingsw.Model.Marble;

/**
 * Represent a marble that is not white or red
 */
public class ResourceMarble extends Marble  {

    private static final long serialVersionUID = -1627778740212593900L;
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
