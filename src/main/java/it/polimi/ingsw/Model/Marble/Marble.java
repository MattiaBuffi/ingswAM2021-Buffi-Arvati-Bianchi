package it.polimi.ingsw.Model.Marble;

public abstract class Marble {

    public abstract MarbleColor getColor();

    public abstract void accept(MarbleVisitor visitor);

}
