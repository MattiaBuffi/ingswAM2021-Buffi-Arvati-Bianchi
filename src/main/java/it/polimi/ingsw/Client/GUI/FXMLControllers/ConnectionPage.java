package it.polimi.ingsw.Client.GUI.FXMLControllers;

import it.polimi.ingsw.Client.GUI.Layout;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.Model.ErrorUpdate;
import it.polimi.ingsw.Message.ModelEventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ConnectionPage extends ModelEventHandler.Default implements Layout {

    @FXML
    TextField tfIpAddress;
    @FXML
    TextField tfPortNumber;


    private ViewBackEnd backEnd;

    @Override
    public void setup(ViewBackEnd backEnd) {
        this.backEnd = backEnd;
    }





    public void connect(){
        backEnd.connectToServer(tfIpAddress.getText(), Integer.parseInt(tfPortNumber.getText()));
        //App.setRoot("username_page");
    }



    @Override
    public void invalidMessage() {

    }

    @Override
    public void handle(ErrorUpdate error) {

    }




}
