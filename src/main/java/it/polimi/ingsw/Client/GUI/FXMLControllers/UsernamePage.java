package it.polimi.ingsw.Client.GUI.FXMLControllers;

import it.polimi.ingsw.Controller.ClientMessageController;
import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.ClientMessages.Login;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Utils.Observable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import it.polimi.ingsw.Client.App;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UsernamePage extends Observable<Message<ClientEventHandler>> implements Initializable {
    @FXML
    Label usernameLabel;
    @FXML
    TextField tfUsername;

    public void checkUsername() {
        usernameLabel.setVisible(true);

        Login message = new Login(tfUsername.getText());
        notify(message);

        try {
            App.setRoot("number_of_player_page");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addObserver(new ClientMessageController());
    }
}
