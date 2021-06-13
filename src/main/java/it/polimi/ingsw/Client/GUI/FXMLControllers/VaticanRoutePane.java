package it.polimi.ingsw.Client.GUI.FXMLControllers;

import it.polimi.ingsw.Client.App;
import it.polimi.ingsw.Client.GUI.ControllerManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class VaticanRoutePane implements Initializable {
    @FXML
    public ImageView cross1, cross2, cross3, cross4;
    @FXML
    public ImageView popeToken1, popeToken2, popeToken3;

    private final int[] CROSS_SHIFT_X = {0, 94, 94, 0, 0, 94, 94, 94, 94, 94, 0, 0, 94, 94, 94, 94, 94, 0, 0, 94, 94, 94, 94, 94, 94};
    private final int[] CROSS_SHIFT_Y = {0, 0, 0, -62, -62, 0, 0, 0, 0, 0, 62, 62, 0, 0, 0, 0, 0, -62, -62, 0, 0, 0, 0, 0, 0};
    private final int[] INITIAL_POS_X = {68, 115, 68, 115};
    private final int[] INITIAL_POS_Y = {148, 201, 201, 148};
    private ImageView[] crossArray;

    public void updateRoute(){
        for(int i=0; i<ControllerManager.getModel().players.size(); i++){
            updateCross(i);
        }
    }

    private void updateCross(int index){
        int faithPoints = ControllerManager.getModel().getPlayer(ControllerManager.username).getFaithPoints();

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



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ControllerManager.addController(this);
        crossArray = new ImageView[]{cross1, cross2, cross3, cross4};
    }

}
