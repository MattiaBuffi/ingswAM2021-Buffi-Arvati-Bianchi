package it.polimi.ingsw.Client.GUI.FXMLControllers;


import it.polimi.ingsw.Client.GUI.ControllerManager;
import it.polimi.ingsw.Controller.ClientMessageController;
import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.ClientMessages.CardProduction;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Utils.Observable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductionTab extends Observable<Message<ClientEventHandler>> implements Initializable {
    @FXML
    ImageView ivProductionLeader;
    @FXML
    ImageView ivLeftCard1, ivLeftCard2, ivLeftCard3, ivCenterCard1, ivCenterCard2, ivCenterCard3, ivRightCard1, ivRightCard2, ivRightCard3;
    @FXML
    Button buttonBasicProduction, buttonLeftProduction, buttonCenterProduction, buttonRightProduction;
    @FXML
    CheckBox checkLeaderProduction, checkBasicProduction;

    public void activateBasicProduction() {
    }

    public void activateLeftProduction() {
        //checkBasicProduction.setVisible(true);
        //checkLeaderProduction.setVisible(true);
        cardProduction(0);
    }

    public void activateCenterProduction() {
        cardProduction(1);
    }

    public void activateRightProduction() {
        cardProduction(2);
    }

    private void cardProduction(int column){
        CardProduction message = new CardProduction(column);
        notify(message);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ControllerManager.addController(this);
        addObserver(new ClientMessageController());
    }
}
