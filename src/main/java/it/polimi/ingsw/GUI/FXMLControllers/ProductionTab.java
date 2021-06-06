package it.polimi.ingsw.GUI.FXMLControllers;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.ImageView;

public class ProductionTab {
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
    }

    public void activateCenterProduction() {
    }

    public void activateRightProduction() {
    }
}
