package it.polimi.ingsw.Client.GUI.FXMLControllers.Game;


import it.polimi.ingsw.Client.GUI.Layout;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ClientMessages.CardProduction;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.ImageView;

public class ProductionTab implements Layout {

    @FXML
    ImageView ivProductionLeader;
    @FXML
    ImageView ivLeftCard1, ivLeftCard2, ivLeftCard3, ivCenterCard1, ivCenterCard2, ivCenterCard3, ivRightCard1, ivRightCard2, ivRightCard3;
    @FXML
    Button buttonBasicProduction, buttonLeftProduction, buttonCenterProduction, buttonRightProduction;
    @FXML
    CheckBox checkLeaderProduction, checkBasicProduction;


    private ViewBackEnd backEnd;
    @Override
    public void setup(ViewBackEnd backEnd) {
        System.out.println("ProductionTabSetup");
        this.backEnd = backEnd;

    }







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
        backEnd.notify(message);
    }


}
