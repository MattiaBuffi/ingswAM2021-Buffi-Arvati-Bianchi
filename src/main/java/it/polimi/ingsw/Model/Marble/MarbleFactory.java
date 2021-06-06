package it.polimi.ingsw.Model.Marble;

public class MarbleFactory {

    //singleton instances of all supported Marbles colors
    private static RedMarble redMarbleInstance = new RedMarble();
    private static WhiteMarble whiteMarbleInstance = new WhiteMarble();
    private static ResourceMarble blueMarbleInstance = new ResourceMarble(Marble.Color.BLUE);
    private static ResourceMarble yellowMarbleInstance = new ResourceMarble(Marble.Color.YELLOW);
    private static ResourceMarble purpleMarbleInstance = new ResourceMarble(Marble.Color.PURPLE);
    private static ResourceMarble grayMarbleInstance = new ResourceMarble(Marble.Color.GREY);

    protected static Marble marbleSelector(Marble.Color color){
        switch (color) {
            case RED:
                return redMarbleInstance;
            case WHITE:
                return whiteMarbleInstance;
            case GREY:
                return grayMarbleInstance;
            case BLUE:
                return blueMarbleInstance;
            case PURPLE:
                return purpleMarbleInstance;
            case YELLOW:
                return yellowMarbleInstance;
            default:
                return null;
        }
    }

    public static Marble getMarble(Marble.Color color) throws AssertionError{
        Marble marble = marbleSelector(color);
        if(marble == null){
            throw new AssertionError("Unknown marble color argument. Received: "+ color);
        } else {
            return marble;
        }
    }


}
