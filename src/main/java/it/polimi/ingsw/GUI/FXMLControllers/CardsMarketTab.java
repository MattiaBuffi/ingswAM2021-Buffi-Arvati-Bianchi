package it.polimi.ingsw.GUI.FXMLControllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.util.Arrays;

public class CardsMarketTab {

    @FXML
    private ImageView ivGreenCardLvl1, ivGreenCardLvl2, ivGreenCardLvl3,
            ivBlueCardLvl1, ivBlueCardLvl2, ivBlueCardLvl3,
            ivYellowCardLvl1,  ivYellowCardLvl2, ivYellowCardLvl3,
            ivPurpleCardLvl1, ivPurpleCardLvl2, ivPurpleCardLvl3;
    @FXML
    private Rectangle rectangle00, rectangle01, rectangle02, rectangle03,
            rectangle10, rectangle11, rectangle12, rectangle13,
            rectangle20, rectangle21, rectangle22, rectangle23;

    private final ImageView[][] cardsMatrix = new ImageView[][]{{ivGreenCardLvl3, ivBlueCardLvl3, ivYellowCardLvl3, ivPurpleCardLvl3},
            {ivGreenCardLvl2, ivBlueCardLvl2, ivYellowCardLvl2, ivPurpleCardLvl2},
            {ivGreenCardLvl1, ivBlueCardLvl1, ivYellowCardLvl1, ivPurpleCardLvl1}};;

    private int cardSelected = -1;

    public void buyCardSelected(){
        System.out.println(cardSelected);
        System.out.println(Arrays.deepToString(cardsMatrix));
    }

    private void hideRectangle(int cardSelected){
        switch (cardSelected){
            case 0:
                rectangle00.setVisible(false);
                break;
            case 1:
                rectangle01.setVisible(false);
                break;
            case 2:
                rectangle02.setVisible(false);
                break;
            case 3:
                rectangle03.setVisible(false);
                break;
            case 4:
                rectangle10.setVisible(false);
                break;
            case 5:
                rectangle11.setVisible(false);
                break;
            case 6:
                rectangle12.setVisible(false);
                break;
            case 7:
                rectangle13.setVisible(false);
                break;
            case 8:
                rectangle20.setVisible(false);
                break;
            case 9:
                rectangle21.setVisible(false);
                break;
            case 10:
                rectangle22.setVisible(false);
                break;
            case 11:
                rectangle23.setVisible(false);
                break;
        }
    }

    public void greenCardLvl3selected() {
        hideRectangle(cardSelected);
        cardSelected = 0;
        rectangle00.setVisible(true);
    }

    public void greenCardLvl2selected() {
        hideRectangle(cardSelected);
        cardSelected = 4;
        rectangle10.setVisible(true);
    }

    public void greenCardLvl1selected() {
        hideRectangle(cardSelected);
        cardSelected = 8;
        rectangle20.setVisible(true);
    }

    public void blueCardLvl3selected() {
        hideRectangle(cardSelected);
        cardSelected = 1;
        rectangle01.setVisible(true);
    }

    public void blueCardLvl2selected() {
        hideRectangle(cardSelected);
        cardSelected = 5;
        rectangle11.setVisible(true);
    }

    public void blueCardLvl1selected() {
        hideRectangle(cardSelected);
        cardSelected = 9;
        rectangle21.setVisible(true);
    }

    public void yellowCardLvl3selected() {
        hideRectangle(cardSelected);
        cardSelected = 2;
        rectangle02.setVisible(true);
    }

    public void yellowCardLvl2selected() {
        hideRectangle(cardSelected);
        cardSelected = 6;
        rectangle12.setVisible(true);
    }

    public void yellowCardLvl1selected() {
        hideRectangle(cardSelected);
        cardSelected = 10;
        rectangle22.setVisible(true);
    }

    public void purpleCardLvl3selected() {
        hideRectangle(cardSelected);
        cardSelected = 3;
        rectangle03.setVisible(true);
    }

    public void purpleCardLvl2selected() {
        hideRectangle(cardSelected);
        cardSelected = 7;
        rectangle13.setVisible(true);
    }

    public void purpleCardLvl1selected() {
        hideRectangle(cardSelected);
        cardSelected = 11;
        rectangle23.setVisible(true);
    }
}
