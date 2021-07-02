package it.polimi.ingsw.Client.GUI.FXMLControllers.Game;

import it.polimi.ingsw.Client.App;
import it.polimi.ingsw.Client.GUI.Layout;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.LeaderCard;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ClientMessages.ActivateLeaderCard;
import it.polimi.ingsw.Message.ClientMessages.DiscardLeaderCard;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeaderCardsTab implements Layout, GameTab{
    @FXML
    ImageView leaderCard1, leaderCard2;
    @FXML
    Rectangle rectangle1, rectangle2;

    private ImageView[] ivArray;
    private Rectangle[] rectArray;
    private List<String> leaderCardsIds;
    private Map<String, ImageView> cardsMap;
    private Map<ImageView, Rectangle> ivMap;
    private int selection = -1;
    private ViewBackEnd backEnd;


    @Override
    public void setup(ViewBackEnd backEnd) {

        this.backEnd = backEnd;

        ivArray = new ImageView[]{leaderCard1, leaderCard2};
        rectArray = new Rectangle[]{rectangle1, rectangle2};
        cardsMap = new HashMap<>();
        ivMap = new HashMap<>();
    }

    @Override
    public void update() {
        List<String> cards = new ArrayList<>();
        Map<String, LeaderCard> lcMap = new HashMap<>();
        for(LeaderCard lc: backEnd.getModel().getPlayer(backEnd.getMyUsername()).getLeaderCard()){
            cards.add(lc.getId());
            lcMap.put(lc.getId(), lc);
        }

        for(String s: leaderCardsIds){
            if(cards.contains(s)){
                if(lcMap.get(s).isActive()){
                    ivMap.get(cardsMap.get(s)).setVisible(false);
                }
            } else {
                ivMap.get(cardsMap.get(s)).setVisible(false);
                cardsMap.get(s).setVisible(false);
            }
        }
    }


    public void activateLeaderCard() {
        ActivateLeaderCard message = new ActivateLeaderCard(leaderCardsIds.get(selection));
        backEnd.notify(message);
    }

    public void discardLeaderCard() {
        DiscardLeaderCard message = new DiscardLeaderCard(leaderCardsIds.get(selection));
        backEnd.notify(message);
    }

    public void cardOneSelected() {
        selection = 0;
        rectangle2.setFill(Color.BLACK);
        rectangle1.setFill(Color.WHITE);
    }

    public void cardTwoSelected() {
        selection = 1;
        rectangle1.setFill(Color.BLACK);
        rectangle2.setFill(Color.WHITE);
    }

    private Image getImage(String cardID){
        String BASE_PATH = "images/cards/leaderCards/";
        return new Image(App.class.getResourceAsStream(BASE_PATH + cardID + ".png"));
    }

    public void showLeaderCard(List<String> id){
        leaderCardsIds = id;
        leaderCard1.setImage(getImage(leaderCardsIds.get(0)));
        leaderCard2.setImage(getImage(leaderCardsIds.get(1)));
        for(int i=0; i<leaderCardsIds.size(); i++){
            cardsMap.put(leaderCardsIds.get(i), ivArray[i]);
            ivMap.put(ivArray[i], rectArray[i]);
        }
    }

    public ViewBackEnd getBackend() {
        return backEnd;
    }
}
