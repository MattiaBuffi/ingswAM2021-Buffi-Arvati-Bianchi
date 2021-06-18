package it.polimi.ingsw.Model.Player.ResourceMarket;

import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.MarbleHandler;

import java.util.List;
import java.util.Set;

public interface ResourceMarketHandler extends MarbleHandler{

    int size();

    Set<Marble.Color> getColors();

    boolean take(Marble.Color color);

    void empty();

    void handleMarbles(List<Marble> marbles);

}
