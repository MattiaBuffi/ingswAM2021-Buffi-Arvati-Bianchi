package it.polimi.ingsw.Model.Marble;

import java.util.ArrayList;
import java.util.List;

/**
 * Represent a white marble that can be converted into other colors thanks to a leader card activation.
 */
public class SelectableMarble extends Marble {

    private List<Color> selectableColors;

    public SelectableMarble(Color color){
        this.selectableColors = new ArrayList<>();
        selectableColors.add(color);
    }

    public List<Color> getSelectableColors() {
        return selectableColors;
    }

    public void addColor(Color color){
        selectableColors.add(color);
    }

    @Override
    public Color getColor() {
        return Color.WHITE;
    }


    @Override
    public void accept(MarbleHandler handler) {
        handler.handle(this);
    }

}
