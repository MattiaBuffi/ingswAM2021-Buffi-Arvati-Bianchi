package it.polimi.ingsw.Client.GUI.FXMLControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import it.polimi.ingsw.Client.App;

import java.io.IOException;

public class UsernamePage {
    @FXML
    Label usernameLabel;

    public void checkUsername() {
        usernameLabel.setVisible(true);

        /**
         * TODO: Control if player has to create the game (first player)
         */

        try {
            App.setRoot("number_of_player_page");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
