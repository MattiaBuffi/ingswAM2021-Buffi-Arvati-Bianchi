package it.polimi.ingsw.Model.Marble;


/**
 * Interface for handling marbles by visitor pattern
 */
public interface MarbleHandler {

    void handle(RedMarble marble);
    void handle(WhiteMarble marble);
    void handle(ResourceMarble marble);
    void handle(SelectableMarble marble);

}
