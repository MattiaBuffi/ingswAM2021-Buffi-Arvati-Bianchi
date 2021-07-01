package it.polimi.ingsw.Model.Marble;

/**
 * Creator of the factory method for marbles management. All the marbles supported are singleton instances
 */
public class MarbleFactory {

    private static RedMarble redMarbleInstance = new RedMarble();
    private static WhiteMarble whiteMarbleInstance = new WhiteMarble();
    private static ResourceMarble blueMarbleInstance = new ResourceMarble(Marble.Color.BLUE);
    private static ResourceMarble yellowMarbleInstance = new ResourceMarble(Marble.Color.YELLOW);
    private static ResourceMarble purpleMarbleInstance = new ResourceMarble(Marble.Color.PURPLE);
    private static ResourceMarble grayMarbleInstance = new ResourceMarble(Marble.Color.GREY);

    /**
     * Return the singleton instance of the marble with the color as the one specified in the parameter
     * @param color Color of the marble to get
     * @return The singleton instance of the marble request
     */
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

    /**
     * Return the instance of the marble of the selected color
     * @param color Color of the request marble
     * @return The marble of the selected color
     * @throws AssertionError if the color not exist in the marbleSelector
     */
    public static Marble getMarble(Marble.Color color) throws AssertionError{
        Marble marble = marbleSelector(color);
        if(marble == null){
            throw new AssertionError("Unknown marble color argument. Received: "+ color);
        } else {
            return marble;
        }
    }


}
