package it.polimi.ingsw.Client.GUI.FXMLControllers.PopUp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.awt.*;

public class ErrorPopup {

    @FXML
    Label errorMessage;

    public void setMessage(String s){
        errorMessage.setText(s);
    }
}
