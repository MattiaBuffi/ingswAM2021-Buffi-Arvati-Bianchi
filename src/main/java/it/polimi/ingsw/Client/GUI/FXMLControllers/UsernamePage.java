package it.polimi.ingsw.Client.GUI.FXMLControllers;

import it.polimi.ingsw.Client.App;
import it.polimi.ingsw.Client.GUI.Layout;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ClientMessages.Login;
import it.polimi.ingsw.Message.Model.ErrorUpdate;
import it.polimi.ingsw.Message.Model.UsernameSelected;
import it.polimi.ingsw.Message.Model.VaticanReport;
import it.polimi.ingsw.Message.ModelEventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UsernamePage extends ModelEventHandler.Default implements Layout {

    @FXML
    Label usernameLabel;
    @FXML
    TextField tfUsername;


    private ViewBackEnd backEnd;

    @Override
    public void setup(ViewBackEnd backEnd) {
        this.backEnd = backEnd;
        this.backEnd.setEventHandler(this);
    }


    public void checkUsername() {

        backEnd.notify(new Login(tfUsername.getText()));
        tfUsername.clear();

    }


    @Override
    public void handle(ErrorUpdate error) {
        usernameLabel.setText(error.getErrorMessage());
        usernameLabel.setVisible(true);
        System.out.println("error update");
    }


    @Override
    public void handle(UsernameSelected event) {
        App.setScene("waiting_page");
        backEnd.setUsername(event.getUsername());
    }


    @Override
    public void invalidMessage() {
        usernameLabel.setText("illegal action");
        usernameLabel.setVisible(true);
        System.out.println("invalid action");
    }







}
