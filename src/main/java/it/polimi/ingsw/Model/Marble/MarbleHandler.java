package it.polimi.ingsw.Model.Marble;



public interface MarbleHandler {

    void handle(RedMarble marble);
    void handle(WhiteMarble marble);
    void handle(ResourceMarble marble);
    void handle(SelectableMarble marble);

}
