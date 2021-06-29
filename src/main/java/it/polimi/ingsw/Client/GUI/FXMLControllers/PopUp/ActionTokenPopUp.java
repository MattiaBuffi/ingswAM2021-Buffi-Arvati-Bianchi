package it.polimi.ingsw.Client.GUI.FXMLControllers.PopUp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.awt.*;

public class ActionTokenPopUp {
    @FXML
    Label messageLabel;

    public void initData(String message){
        messageLabel.setText(message);
    }
}
