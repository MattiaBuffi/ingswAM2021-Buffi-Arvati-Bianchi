package it.polimi.ingsw.Model.Player.MarketBuffer;

import it.polimi.ingsw.Model.Marble.*;
import it.polimi.ingsw.Model.Player.Player;

import java.util.List;

public abstract class PlayerMarbleHandler {

    protected Player player;
    protected ResourceList buffer;
    private MarbleVisitor handler;

    protected abstract void handleRed(RedMarble marble);

    protected abstract void handleWhite(WhiteMarble marble);

    protected abstract void handleResource(ResourceMarble marble);



    public ResourceList getAvailableResource(){
        return new ResourceList().sum(buffer);
    }

    public boolean remove(MarbleColor color){
        if(buffer.getSize(color) == 0){
            return false;
        }
        buffer.pop(color);
        return true;
    }

    public void fill(List<Marble> marbles){
        for(Marble m: marbles){
            m.accept(handler);
        }
    }

    public abstract void empty();


    private class MarbleHandler implements MarbleVisitor{

        @Override
        public void visit(RedMarble marble) {
            handleRed(marble);
        }

        @Override
        public void visit(WhiteMarble marble) {
            handleWhite(marble);
        }

        @Override
        public void visit(ResourceMarble marble) {
            handleResource(marble);
        }
    }

}
