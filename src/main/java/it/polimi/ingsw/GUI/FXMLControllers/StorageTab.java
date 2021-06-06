package it.polimi.ingsw.GUI.FXMLControllers;

import it.polimi.ingsw.Model.Marble.MarbleColor;
import it.polimi.ingsw.Model.ProductionCard.DevelopmentCardColor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

import static it.polimi.ingsw.Model.Marble.MarbleColor.*;

public class StorageTab implements Initializable {
    @FXML
    ImageView res1_1, res2_1, res2_2, res3_1, res3_2, res3_3, resLeader1, resLeader2;
    @FXML
    Label yellowValue, purpleValue, blueValue, greyValue;
    @FXML
    Pane leaderStorageContainer;

    private void changeResourceValue(MarbleColor color, int value){
        switch (color){
            case YELLOW:
                yellowValue.setText(String.valueOf(value));
                break;
            case PURPLE:
                purpleValue.setText(String.valueOf(value));
                break;
            case BLUE:
                blueValue.setText(String.valueOf(value));
                break;
            case GREY:
                greyValue.setText(String.valueOf(value));
                break;
        }
    }

    private void resetValue(){
        yellowValue.setText("0");
        purpleValue.setText("0");
        blueValue.setText("0");
        greyValue.setText("0");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        leaderStorageContainer.setVisible(false);
        resetValue();
    }
}
