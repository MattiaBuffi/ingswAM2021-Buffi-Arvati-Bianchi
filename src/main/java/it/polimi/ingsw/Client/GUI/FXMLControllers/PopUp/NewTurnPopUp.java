package it.polimi.ingsw.Client.GUI.FXMLControllers.PopUp;

import it.polimi.ingsw.Client.ViewBackEnd;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class NewTurnPopUp {

    @FXML
    Label usernameLabel;

    private void setMessage(String s){
        usernameLabel.setText(s);
    }

    public void initData(String username, ViewBackEnd backEnd){
        if(username.equals(backEnd.getMyUsername())){
            setMessage("YOUR");
        } else {
            setMessage(username);
        }
    }
}
