package it.polimi.ingsw.Model.Marble;

public class MarbleFactory {

    //singleton instances of all supported Marbles colors
    private static RedMarble redMarbleInstance = new RedMarble();
    private static WhiteMarble whiteMarbleInstance = new WhiteMarble();
    private static ResourceMarble blueMarbleInstance = new ResourceMarble(MarbleColor.BLUE);
    private static ResourceMarble yellowMarbleInstance = new ResourceMarble(MarbleColor.YELLOW);
    private static ResourceMarble purpleMarbleInstance = new ResourceMarble(MarbleColor.PURPLE);
    private static ResourceMarble grayMarbleInstance = new ResourceMarble(MarbleColor.GREY);

    protected static Marble marbleSelector(MarbleColor color){
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

    public static Marble getMarble(MarbleColor color) throws AssertionError{
        Marble marble = marbleSelector(color);
        if(marble == null){
            throw new AssertionError("Unknown marble color argument. Received: "+ color);
        } else {
            return marble;
        }
    }


}
