package it.polimi.ingsw.Client.GUI.FXMLControllers;

import it.polimi.ingsw.Client.GUI.Layout;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ClientMessages.Login;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UsernamePage implements Layout {

    @FXML
    Label usernameLabel;
    @FXML
    TextField tfUsername;


    private ViewBackEnd backEnd;

    @Override
    public void setup(ViewBackEnd backEnd) {
        this.backEnd = backEnd;
    }




    public void checkUsername() {

        usernameLabel.setVisible(true);

        Login message = new Login(tfUsername.getText());
        backEnd.notify(message);

    }



}
