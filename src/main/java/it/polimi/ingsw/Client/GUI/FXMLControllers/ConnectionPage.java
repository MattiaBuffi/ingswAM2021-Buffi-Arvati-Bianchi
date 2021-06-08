package it.polimi.ingsw.Client.GUI.FXMLControllers;

import it.polimi.ingsw.Controller.ClientMessageController;
import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Utils.Observable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import it.polimi.ingsw.Client.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ConnectionPage extends Observable<Message<ClientEventHandler>> implements Initializable {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addObserver(new ClientMessageController());
    }
}
