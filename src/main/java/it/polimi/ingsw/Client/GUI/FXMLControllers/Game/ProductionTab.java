package it.polimi.ingsw.Client.GUI.FXMLControllers.Game;


import it.polimi.ingsw.Client.App;
import it.polimi.ingsw.Client.GUI.Layout;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.ClientMessages.BasicProduction;
import it.polimi.ingsw.Message.ClientMessages.CardProduction;
import it.polimi.ingsw.Message.ClientMessages.LeaderCardProduction;
import it.polimi.ingsw.Message.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ProductionTab implements Layout {

    @FXML
    ImageView ivProductionLeader;
    @FXML
    ImageView ivLeftCard1, ivLeftCard2, ivLeftCard3, ivCenterCard1, ivCenterCard2, ivCenterCard3, ivRightCard1, ivRightCard2, ivRightCard3;
    @FXML
    CheckBox checkLeaderProduction, checkBasicProduction, checkLeftProduction, checkCenterProduction, checkRightProduction;

    private ViewBackEnd backEnd;
    private CheckBox[] checkBoxArray;

    @Override
    public void setup(ViewBackEnd backEnd) {
        System.out.println("ProductionTabSetup");
        this.backEnd = backEnd;

        checkBoxArray = new CheckBox[]{checkLeaderProduction, checkBasicProduction, checkLeftProduction, checkCenterProduction, checkRightProduction};
    }


    private void cardProduction(int column){
        CardProduction message = new CardProduction(column);
        backEnd.notify(message);
    }

    public void showLeaderCardPower(){
        checkLeaderProduction.setVisible(true);
        ivProductionLeader.setVisible(true);
        // Choose right image for leader production iv
    }

    private Image getImage(int cardID){
        return new Image(App.class.getResourceAsStream("images/cards/developmentCards/DC_" + cardID + ".png"));
    }

    public void produce() {
        List<Message<ClientEventHandler>> productionMessages = new ArrayList<>();
        for(CheckBox cb: checkBoxArray){
            if(cb.isSelected()){
                if(cb==checkBasicProduction){
                    //productionMessages.add(new BasicProduction(COLOR));
                }else if(cb==checkLeftProduction){
                    productionMessages.add(new CardProduction(1));
                }else if(cb==checkCenterProduction){
                    productionMessages.add(new CardProduction(2));
                }else if(cb==checkRightProduction){
                    productionMessages.add(new CardProduction(3));
                }else if(cb==checkLeaderProduction){
                    //productionMessages.add(new LeaderCardProduction(...));
                }
            }
        }
        notifyProductions(productionMessages);
    }

    private void notifyProductions(List<Message<ClientEventHandler>> productionMessages) {
        for(Message<ClientEventHandler> m: productionMessages){
            backEnd.notify(m);
        }
    }

}
