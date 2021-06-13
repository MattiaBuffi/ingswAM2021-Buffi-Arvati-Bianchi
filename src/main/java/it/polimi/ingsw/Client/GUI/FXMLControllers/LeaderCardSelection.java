package it.polimi.ingsw.Client.GUI.FXMLControllers;

import it.polimi.ingsw.Client.GUI.Layout;
import it.polimi.ingsw.Client.ViewBackEnd;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class LeaderCardSelection implements Layout{
    @FXML
    ImageView leaderCard1, leaderCard2, leaderCard3, leaderCard4;
    @FXML
    Rectangle rect1, rect2, rect3, rect4;
    private boolean[] selection = {false, false, false, false};
    private ArrayList<Integer> indexSelected = new ArrayList<>();
    private Rectangle[] rectArray;


    private ViewBackEnd backEnd;

    @Override
    public void setup(ViewBackEnd backEnd) {
        this.backEnd = backEnd;

        /**
         * TODO: Initialization of image views
         */
        rectArray = new Rectangle[]{rect1, rect2, rect3, rect4};
    }


    public void selectCard(int position, Rectangle rectangle){
        selection[position] = !selection[position];
        rectangle.setVisible(selection[position]);
        if(indexSelected.size() == 2){
            hideRectangle();
        }
        indexSelected.add(position+1);
    }



    public void cardOneSelected() {
        /*
        selection[0] = !selection[0];
        rect1.setVisible(selection[0]);
        if(indexSelected.size() == 2){
            hideRectangle();
        }
        indexSelected.add(1);*/
        selectCard(0, rect1);
    }

    public void cardTwoSelected() {
        /*
        selection[1] = !selection[1];
        rect2.setVisible(selection[1]);
        if(indexSelected.size() == 2){
            hideRectangle();
        }
        indexSelected.add(2);*/
        selectCard(1, rect2);
    }

    public void cardThreeSelected() {
        /*
        selection[2] = !selection[2];
        rect3.setVisible(selection[2]);
        if(indexSelected.size() == 2){
            hideRectangle();
        }
        indexSelected.add(3);
         */
        selectCard(2, rect3);
    }

    public void cardFourSelected() {
        /*
        selection[3] = !selection[3];
        rect4.setVisible(selection[3]);
        if(indexSelected.size() == 2){
            hideRectangle();
        }
        indexSelected.add(4);*/
        selectCard(3, rect4);
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
