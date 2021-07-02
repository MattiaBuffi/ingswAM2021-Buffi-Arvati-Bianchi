package it.polimi.ingsw.Client.GUI.FXMLControllers.Game;


import it.polimi.ingsw.Client.App;
import it.polimi.ingsw.Client.GUI.FXMLControllers.PopUp.PopUpManager;
import it.polimi.ingsw.Client.GUI.FXMLControllers.PopUp.ResourceViewer;
import it.polimi.ingsw.Client.GUI.Layout;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.DevelopmentCardData;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.ClientMessages.BasicProduction;
import it.polimi.ingsw.Message.ClientMessages.CardProduction;
import it.polimi.ingsw.Message.ClientMessages.LeaderCardProduction;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Model.Marble.Marble;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductionTab extends ResourceViewer implements Layout, GameTab {

    @FXML
    ImageView ivProductionLeader1, ivProductionLeader2;
    @FXML
    ImageView ivLeftCard1, ivLeftCard2, ivLeftCard3, ivCenterCard1, ivCenterCard2, ivCenterCard3, ivRightCard1, ivRightCard2, ivRightCard3;
    @FXML
    CheckBox checkLeaderProduction1, checkLeaderProduction2, checkBasicProduction, checkLeftProduction, checkCenterProduction, checkRightProduction;
    @FXML
    ChoiceBox<String> in1, in2, out, resLeader1, resLeader2;

    private ViewBackEnd backEnd;
    private CheckBox[] checkBoxArray;
    private ImageView[][] cardGrid;
    private Map<CheckBox, String> leaderPowerMap= new HashMap<>();

    @Override
    public void setup(ViewBackEnd backEnd) {

        this.backEnd = backEnd;

        checkBoxArray = new CheckBox[]{checkLeaderProduction1, checkLeaderProduction2, checkBasicProduction, checkLeftProduction, checkCenterProduction, checkRightProduction};
        cardGrid = new ImageView[][]{{ivLeftCard1, ivLeftCard2, ivLeftCard3},{ivCenterCard1, ivCenterCard2, ivCenterCard3},{ivRightCard1, ivRightCard2, ivRightCard3}};
    }

    @Override
    public void update() throws NullPointerException {
        List<List<DevelopmentCardData>> cards = backEnd.getModel().getPlayer(backEnd.getMyUsername()).getProductions();

        for(int i=0; i<3; i++){
            showCards(i, cards.get(i));
        }
    }

    private void showCards(int column, List<DevelopmentCardData> cardList){
        for(int i=0; i<cardList.size(); i++){
            cardGrid[column][i].setImage(getDevCardImage(cardList.get(i).id));
        }
    }

    public void showLeaderPower(String id){
        if(!checkLeaderProduction1.isVisible()) {
            leaderPowerMap.put(checkLeaderProduction1, "LC_" + id);
            checkLeaderProduction1.setVisible(true);
            resLeader1.setVisible(true);
            ivProductionLeader1.setImage(getLeaderCardPower(id));
        } else {
            leaderPowerMap.put(checkLeaderProduction2, "LC_" + id);
            checkLeaderProduction2.setVisible(true);
            resLeader2.setVisible(true);
            ivProductionLeader2.setImage(getLeaderCardPower(id));
        }
    }

    private Image getLeaderCardPower(String id) {
        Image image = null;
        switch(id){
            case "13":
                image = new Image(App.class.getResourceAsStream("images/leaderPowers/leaderProductionBLUE.png"));
                break;
            case "14":
                image = new Image(App.class.getResourceAsStream("images/leaderPowers/leaderProductionPURPLE.png"));
                break;
            case "15":
                image = new Image(App.class.getResourceAsStream("images/leaderPowers/leaderProductionGREY.png"));
                break;
            case "16":
                image = new Image(App.class.getResourceAsStream("images/leaderPowers/leaderProductionYELLOW.png"));
                break;
        }
        return image;
    }

    private Image getDevCardImage(String cardID){
        return new Image(App.class.getResourceAsStream("images/cards/developmentCards/" + cardID + ".png"));
    }

    public void produce() {
        List<Message<ClientEventHandler>> productionMessages = new ArrayList<>();
        for(CheckBox cb: checkBoxArray){
            if(cb.isSelected()){
                if(cb==checkBasicProduction){
                    if(allChoiceBoxSelected()){
                        productionMessages.add(new BasicProduction(getColor(in1.getSelectionModel().getSelectedItem()),
                                getColor(in2.getSelectionModel().getSelectedItem()), getColor(out.getSelectionModel().getSelectedItem())));
                    } else {
                        try {
                            PopUpManager.showErrorPopUp("For basic production, you have to select two input resources and one for the output");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }else if(cb==checkLeftProduction){
                    productionMessages.add(new CardProduction(0));
                }else if(cb==checkCenterProduction){
                    productionMessages.add(new CardProduction(1));
                }else if(cb==checkRightProduction){
                    productionMessages.add(new CardProduction(2));
                }else if(cb == checkLeaderProduction1){
                    if(resLeader1.getSelectionModel().getSelectedItem() != null){
                        productionMessages.add(new LeaderCardProduction(leaderPowerMap.get(cb), getColor(resLeader1.getSelectionModel().getSelectedItem())));
                    } else {
                        try {
                            PopUpManager.showErrorPopUp("Select which type of resource you want for the leader production");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else if(cb == checkLeaderProduction2){
                    if(resLeader2.getSelectionModel().getSelectedItem() != null){
                        productionMessages.add(new LeaderCardProduction(leaderPowerMap.get(cb), getColor(resLeader2.getSelectionModel().getSelectedItem())));
                    } else {
                        try {
                            PopUpManager.showErrorPopUp("Select which type of resource you want for the leader production");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            if(cb.isSelected()) cb.setSelected(false);
        }
        notifyProductions(productionMessages);
    }

    private boolean allChoiceBoxSelected() {
        if(in1.getSelectionModel().getSelectedItem() != null && in2.getSelectionModel().getSelectedItem() != null &&
                out.getSelectionModel().getSelectedItem() != null){
            return true;
        } else {
            return false;
        }
    }

    private void notifyProductions(List<Message<ClientEventHandler>> productionMessages) {
        for(Message<ClientEventHandler> m: productionMessages){
            backEnd.notify(m);
        }
    }

}
