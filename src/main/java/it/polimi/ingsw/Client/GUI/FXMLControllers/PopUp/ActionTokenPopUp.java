package it.polimi.ingsw.Client.GUI.FXMLControllers.PopUp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * PopUp that show the message of the action token activated for the single player game.
 */
public class ActionTokenPopUp {
    @FXML
    Label messageLabel;

    public void initData(String message){
        messageLabel.setText(message);
    }
}
