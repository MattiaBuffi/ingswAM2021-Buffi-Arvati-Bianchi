package it.polimi.ingsw.Client.GUI.FXMLControllers.PopUp;

import it.polimi.ingsw.Client.App;
import it.polimi.ingsw.Client.ModelData.Player;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.DevelopmentCardData;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.LeaderCard;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.Shelf;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Model.Marble.Marble;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class PlayerBoard {

    @FXML
    Label playerUsername, coinValue, servantValue, shieldValue, stoneValue;
    @FXML
    ImageView devCard1, devCard2, devCard3, leaderCard1, leaderCard2;

    private ImageView[] leaderCardArray, devCardsArray;
    private Label[] values;
    private int[] resources = new int[4];
    private Player player;

    public void initData(String username, ViewBackEnd backend) {
        player = backend.getModel().getPlayer(username);
        playerUsername.setText(username.toUpperCase() + " BOARD");

        leaderCardArray = new ImageView[]{leaderCard1, leaderCard2};
        devCardsArray = new ImageView[]{devCard1, devCard2, devCard3};
        values = new Label[]{coinValue, servantValue, shieldValue, stoneValue};

        showData();
    }

    private void showData() {
        setLeaderCards();
        setDevCards();
        setResourceValues();
    }

    private void setResourceValues() {
        resources[0] = player.getChest().getSize(Marble.Color.YELLOW);
        resources[1] = player.getChest().getSize(Marble.Color.PURPLE);
        resources[2] = player.getChest().getSize(Marble.Color.BLUE);
        resources[3] = player.getChest().getSize(Marble.Color.GREY);

        for(Shelf s: player.getShelves()){
            if(s.color == null){
                continue;
            }
            switch (s.color){
                case YELLOW:
                    resources[0] += s.size;
                    break;
                case PURPLE:
                    resources[1] += s.size;
                    break;
                case BLUE:
                    resources[2] += s.size;
                    break;
                case GREY:
                    resources[3] += s.size;
                    break;
                default:
                    break;
            }
        }

        for(int i=0; i<4; i++){
            values[i].setText(String.valueOf(resources[i]));
        }
    }

    private void setLeaderCards(){
        List<LeaderCard> leaderCards = player.getLeaderCard();

        for(int i=0; i<leaderCards.size(); i++){
            if(leaderCards.get(i).isActive()){
                leaderCardArray[i].setImage(getLeaderCardImage(leaderCards.get(i).getId()));
            }
        }
    }

    private void setDevCards(){
        List<String> ids = new ArrayList<>();

        for(List<DevelopmentCardData> devCardList: player.getProductions()){
            if(devCardList.size() != 0) {
                ids.add(devCardList.get(devCardList.size() - 1).id);
            }
        }

        for(int i=0; i<ids.size(); i++){
            devCardsArray[i].setImage(getDevCardImage(ids.get(i)));
        }
    }

    private Image getLeaderCardImage(String cardID){
        return new Image(App.class.getResourceAsStream("images/cards/leaderCards/LC_" + cardID + ".png"));
    }

    private Image getDevCardImage(String cardID){
        return new Image(App.class.getResourceAsStream("images/cards/developmentCards/DC_" + cardID + ".png"));
    }
}
