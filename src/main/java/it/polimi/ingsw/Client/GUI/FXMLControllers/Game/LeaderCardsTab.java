package it.polimi.ingsw.Client.GUI.FXMLControllers.Game;

import it.polimi.ingsw.Client.App;
import it.polimi.ingsw.Client.GUI.Layout;
import it.polimi.ingsw.Client.ViewBackEnd;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class LeaderCardsTab implements Layout{
    @FXML
    ImageView leaderCard1, leaderCard2;
    @FXML
    Rectangle rectangle1, rectangle2;
    private int selection = -1;


    private ViewBackEnd backEnd;

    public void setup(ViewBackEnd backEnd) {
        System.out.println("LeaderCardTab");
        this.backEnd = backEnd;

        leaderCard1.setImage(getImage(1));
        leaderCard2.setImage(getImage(2)); //what if there is only one card???
        System.out.println(getClass().getResource(""));
    }


    public void activateLeaderCard() {
        switch (selection){
            case 1:
                rectangle1.setVisible(false);
                break;
            case 2:
                rectangle2.setVisible(false);
                break;
        }
        //ActivateLeaderCard message = new ActivateLeaderCard();
        //notify(message);
    }

    public void discardLeaderCard() {
        System.out.println(selection);
        switch (selection){
            case 1:
                leaderCard1.setVisible(false);
                rectangle1.setVisible(false);
                leaderCard1.setDisable(true);
                break;
            case 2:
                leaderCard2.setVisible(false);
                rectangle2.setVisible(false);
                leaderCard2.setDisable(true);
                break;
        }
        //DiscardLeaderCard message = new DiscardLeaderCard(); TAKE ID FROM LIGHT MODEL!
        //notify(message);
    }

    public void cardOneSelected() {
        selection = 1;
        rectangle2.setFill(Color.BLACK);
        rectangle1.setFill(Color.WHITE);
    }

    public void cardTwoSelected() {
        selection = 2;
        rectangle1.setFill(Color.BLACK);
        rectangle2.setFill(Color.WHITE);
    }

    private Image getImage(int cardID){
        String BASE_PATH = "images/cards/leaderCards/LC_";
        return new Image(App.class.getResourceAsStream(BASE_PATH + cardID + ".png"));
    }



}
