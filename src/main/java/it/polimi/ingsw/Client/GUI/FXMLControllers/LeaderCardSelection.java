package it.polimi.ingsw.Client.GUI.FXMLControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LeaderCardSelection implements Initializable {
    @FXML
    ImageView leaderCard1, leaderCard2, leaderCard3, leaderCard4;
    @FXML
    Rectangle rect1, rect2, rect3, rect4;
    private boolean[] selection = {false, false, false, false};
    private ArrayList<Integer> indexSelected = new ArrayList<>();
    private Rectangle[] rectArray;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /**
         * TODO: Initialization of image views
         */
        rectArray = new Rectangle[]{rect1, rect2, rect3, rect4};
    }


    public void cardOneSelected() {
        selection[0] = !selection[0];
        rect1.setVisible(selection[0]);
        if(indexSelected.size() == 2){
            hideRectangle();
            indexSelected.add(1);
        } else {
            indexSelected.add(1);
        }
    }

    public void cardTwoSelected() {
        selection[1] = !selection[1];
        rect2.setVisible(selection[1]);
        if(indexSelected.size() == 2){
            hideRectangle();
            indexSelected.add(2);
        } else {
            indexSelected.add(2);
        }
    }

    public void cardThreeSelected() {
        selection[2] = !selection[2];
        rect3.setVisible(selection[2]);
        if(indexSelected.size() == 2){
            hideRectangle();
            indexSelected.add(3);
        } else {
            indexSelected.add(3);
        }
    }

    public void cardFourSelected() {
        selection[3] = !selection[3];
        rect4.setVisible(selection[3]);
        if(indexSelected.size() == 2){
            hideRectangle();
            indexSelected.add(4);
        } else {
            indexSelected.add(4);
        }
    }

    private void hideRectangle(){
        rectArray[indexSelected.get(0) - 1].setVisible(false);
        selection[indexSelected.get(0) - 1] = false;
        indexSelected.remove(0);
    }

    public void discardLeaderCards() {
        /**
         * Discard leader card based on selection
         */
    }
}
