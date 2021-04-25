package it.polimi.ingsw.Model.Player;

import it.polimi.ingsw.Model.Marble.*;

import java.util.List;

public class StateBuyResource extends PlayerState {

    private class marbleHandler implements MarbleVisitor{

        @Override
        public void visit(RedMarble marble) {
            context.getFaithToken().advance();
        }

        @Override
        public void visit(WhiteMarble marble) {
        }

        @Override
        public void visit(ResourceMarble marble) {
        }
    }

    @Override
    protected void invalidAction() {

    }

    @Override
    protected void storeResource(MarbleColor color, String storageId) {
        if(true){
            return;
        }
        context.getResourceStorage().take(color, storageId);
    }

    @Override
    protected void buyResources(List<Marble> resources) {
        if (true) {
            return;
        }
        //...
    }


}