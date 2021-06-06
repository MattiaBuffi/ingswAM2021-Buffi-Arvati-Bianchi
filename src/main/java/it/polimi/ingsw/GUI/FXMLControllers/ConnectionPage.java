package it.polimi.ingsw.GUI.FXMLControllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import it.polimi.ingsw.App;

import java.io.IOException;

public class ConnectionPage {
    @FXML
    TextField tfIpAddress;
    @FXML
    TextField tfPortNumber;

    public void connect(){
        /**
         * TODO: control on input
         */
        System.out.println("CONNECTED");
        System.out.println("- " + tfIpAddress.getText());
        System.out.println("- " + tfPortNumber.getText());

        try {
            App.setRoot("username_page");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
