package it.polimi.ingsw.Client.GUI.FXMLControllers.Game;

import it.polimi.ingsw.Client.App;
import it.polimi.ingsw.Client.GUI.Layout;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ClientMessages.ActivateLeaderCard;
import it.polimi.ingsw.Message.ClientMessages.DiscardLeaderCard;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class LeaderCardsTab implements Layout, GameTab{
    @FXML
    ImageView leaderCard1, leaderCard2;
    @FXML
    Rectangle rectangle1, rectangle2;

    private List<String> leaderCardsIds;
    private int selection = -1;
    private ViewBackEnd backEnd;


    @Override
    public void setup(ViewBackEnd backEnd) {
        System.out.println("LeaderCardTab");
        this.backEnd = backEnd;

        //System.out.println(getClass().getResource(""));
    }

    @Override
    public void update() {

    }


    public void activateLeaderCard() {
        switch (selection){
            case 0:
                rectangle1.setVisible(false);
                break;
            case 1:
                rectangle2.setVisible(false);
                break;
        }
        ActivateLeaderCard message = new ActivateLeaderCard(leaderCardsIds.get(selection));
        backEnd.notify(message);
    }

    public void discardLeaderCard() {
        System.out.println(selection);
        switch (selection){
            case 0:
                leaderCard1.setVisible(false);
                rectangle1.setVisible(false);
                leaderCard1.setDisable(true);
                break;
            case 1:
                leaderCard2.setVisible(false);
                rectangle2.setVisible(false);
                leaderCard2.setDisable(true);
                break;
        }
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
        String BASE_PATH = "images/cards/leaderCards/LC_";
        return new Image(App.class.getResourceAsStream(BASE_PATH + cardID + ".png"));
    }

    public void showLeaderCard(List<String> id){
        leaderCardsIds = id;
        leaderCard1.setImage(getImage(leaderCardsIds.get(0)));
        leaderCard2.setImage(getImage(leaderCardsIds.get(1)));
    }



}
