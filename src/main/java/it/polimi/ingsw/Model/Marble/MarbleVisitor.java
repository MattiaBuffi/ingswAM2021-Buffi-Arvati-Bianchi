package it.polimi.ingsw.Model.Marble;



public interface MarbleVisitor {

    void visit(RedMarble marble);
    void visit(WhiteMarble marble);
    void visit(ResourceMarble marble);

}
