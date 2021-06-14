package it.polimi.ingsw.Client.GUI.FXMLControllers.Game;

import it.polimi.ingsw.Client.App;
import it.polimi.ingsw.Client.GUI.Layout;
import it.polimi.ingsw.Client.ViewBackEnd;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class VaticanRoutePane implements Layout  {
    @FXML
    public ImageView cross1, cross2, cross3, cross4;
    @FXML
    public ImageView popeToken1, popeToken2, popeToken3;

    private final int[] CROSS_SHIFT_X = {0, 94, 94, 0, 0, 94, 94, 94, 94, 94, 0, 0, 94, 94, 94, 94, 94, 0, 0, 94, 94, 94, 94, 94, 94};
    private final int[] CROSS_SHIFT_Y = {0, 0, 0, -62, -62, 0, 0, 0, 0, 0, 62, 62, 0, 0, 0, 0, 0, -62, -62, 0, 0, 0, 0, 0, 0};
    private final int[] INITIAL_POS_X = {68, 115, 68, 115};
    private final int[] INITIAL_POS_Y = {148, 201, 201, 148};
    private ImageView[] crossArray;



    private ViewBackEnd backEnd;

    @Override
    public void setup(ViewBackEnd backEnd) {
        this.backEnd = backEnd;
        crossArray = new ImageView[]{cross1, cross2, cross3, cross4};
        intializeCrosses();
    }

    private void intializeCrosses() {
        if(backEnd.getModel().players.size() == 1){
            cross1.setVisible(true);
            cross2.setImage(new Image(App.class.getResourceAsStream("images/token/blackCross.png")));
        } else {
            for (int i = 0; i < backEnd.getModel().players.size(); i++) {
                crossArray[i].setVisible(true);
            }
        }
    }

    public void updateRoute(){
        for(int i=0; i<backEnd.getModel().players.size(); i++){
            updateCross(i);
        }
    }

    private void updateCross(int index){
        int faithPoints = backEnd.getModel().getPlayer(backEnd.getModel().players.get(index).getUsername()).getFaithPoints();

        crossArray[index].setLayoutX(INITIAL_POS_X[index] + xToAdd(faithPoints));
        crossArray[index].setLayoutY(INITIAL_POS_Y[index] + yToAdd(faithPoints));
    }

    private int xToAdd(int faithPoints){
        int x_toAdd = 0;
        for(int i=0; i<faithPoints; i++){
            x_toAdd += CROSS_SHIFT_X[i];
        }
        return x_toAdd;
    }

    private int yToAdd(int faithPoints){
        int y_toAdd = 0;
        for(int i=0; i<faithPoints; i++){
            y_toAdd += CROSS_SHIFT_Y[i];
        }
        return y_toAdd;
    }

    public void activatePopeFavor(int pos){
        switch(pos){
            case 1:
                popeToken1.setImage(new Image(App.class.getResourceAsStream("images/token/popeFavorON_1.png")));
                break;
            case 2:
                popeToken2.setImage(new Image(App.class.getResourceAsStream("images/token/popeFavorON_2.png")));
                break;
            case 3:
                popeToken3.setImage(new Image(App.class.getResourceAsStream("images/token/popeFavorON_3.png")));
                break;
        }
    }

    /**
     * TODO: In light model missing reference to pope favor
     */

}
