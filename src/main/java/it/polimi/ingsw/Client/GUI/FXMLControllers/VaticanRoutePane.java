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
    private int[] positions = {0, 0, 0, 0};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ControllerManager.addController(this);
    }

    public void advanceCross1(){
        positions[0]++;
        cross1.setLayoutX(cross1.getLayoutX() + CROSS_SHIFT_X[positions[0]]);
        cross1.setLayoutY(cross1.getLayoutY() + CROSS_SHIFT_Y[positions[0]]);
    }

    public void advanceCross2(){
        positions[1]++;
        cross2.setLayoutX(cross2.getLayoutX() + CROSS_SHIFT_X[positions[1]]);
        cross2.setLayoutY(cross2.getLayoutY() + CROSS_SHIFT_Y[positions[1]]);
    }

    public void advanceCross3(){
        positions[2]++;
        cross3.setLayoutX(cross3.getLayoutX() + CROSS_SHIFT_X[positions[2]]);
        cross3.setLayoutY(cross3.getLayoutY() + CROSS_SHIFT_Y[positions[2]]);
    }

    public void advanceCross4(){
        positions[3]++;
        cross4.setLayoutX(cross4.getLayoutX() + CROSS_SHIFT_X[positions[3]]);
        cross4.setLayoutY(cross4.getLayoutY() + CROSS_SHIFT_Y[positions[3]]);
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
}
