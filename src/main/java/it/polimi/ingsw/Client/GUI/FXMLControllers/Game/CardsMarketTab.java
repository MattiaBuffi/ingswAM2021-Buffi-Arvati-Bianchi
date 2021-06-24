package it.polimi.ingsw.Client.GUI.FXMLControllers.Game;

import it.polimi.ingsw.Client.App;
//import it.polimi.ingsw.Utils.StaticMessageObservable;
import it.polimi.ingsw.Client.GUI.Layout;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ClientMessages.BuyDevelopmentCard;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.ResourceMarble;
import it.polimi.ingsw.Model.Marble.SelectableMarble;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class CardsMarketTab implements Layout{

    @FXML
    ImageView ivGreenCardLvl1, ivGreenCardLvl2, ivGreenCardLvl3,
            ivBlueCardLvl1, ivBlueCardLvl2, ivBlueCardLvl3,
            ivYellowCardLvl1,  ivYellowCardLvl2, ivYellowCardLvl3,
            ivPurpleCardLvl1, ivPurpleCardLvl2, ivPurpleCardLvl3;
    @FXML
    Rectangle rectangle00, rectangle01, rectangle02, rectangle03,
            rectangle10, rectangle11, rectangle12, rectangle13,
            rectangle20, rectangle21, rectangle22, rectangle23;

    @FXML
    ImageView leaderDiscount1, leaderDiscount2;
    @FXML
    ChoiceBox<Integer> columnChoice;

    private ImageView[][] cardsMatrix;
    private ImageView[] leaderDiscount;

    private int cardSelected = -1;
    private int cardSelectedX = -1;
    private int cardSelectedY = -1;


    private ViewBackEnd backEnd;

    public void setup(ViewBackEnd backEnd) {
        System.out.println("CardsMarketCard");
        this.backEnd = backEnd;

        cardsMatrix = new ImageView[][]{{ivGreenCardLvl3, ivBlueCardLvl3, ivYellowCardLvl3, ivPurpleCardLvl3},
                {ivGreenCardLvl2, ivBlueCardLvl2, ivYellowCardLvl2, ivPurpleCardLvl2},
                {ivGreenCardLvl1, ivBlueCardLvl1, ivYellowCardLvl1, ivPurpleCardLvl1}};
        leaderDiscount = new ImageView[]{leaderDiscount1, leaderDiscount2};

        updateAllCards();
    }

    public void buyCardSelected(){
        if(cardSelectedX != -1 && cardSelectedY != -1){
            if(columnChoice.getSelectionModel().getSelectedItem() != null) {
                System.out.println(columnChoice.getSelectionModel().getSelectedItem());
                BuyDevelopmentCard message = new BuyDevelopmentCard(cardSelectedX, cardSelectedY, columnChoice.getSelectionModel().getSelectedItem());
                backEnd.notify(message);
            }
        }
    }

    public void updateCard(int x, int y, int id){
        cardsMatrix[y][x].setImage(getCardImage(id));
    }

    public void updateAllCards(){
        for(int i=0;i<3;i++){
            for(int j=0;j<4;j++){
                //cardsMatrix[i][j].setImage(getImage(Integer.parseInt(backEnd.getModel().cardMarket.getCard(i,j).id)));
            }
        }
    }

    public void showLeaderDiscount(int index, Marble.Color color){
        leaderDiscount[index].setImage(getLeaderPowerImage(color));
    }

    private Image getCardImage(int cardID){
        return new Image(App.class.getResourceAsStream("images/cards/developmentCards/DC_" + cardID + ".png"));
    }

    private Image getLeaderPowerImage(Marble.Color color) {
        return new Image(App.class.getResourceAsStream("images/leaderPowers/leaderDiscount" + color.toString() + ".png"));
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


    private void selectCard(int cardSelected, Rectangle rectangle, int x, int y){
        hideRectangle(this.cardSelected);
        this.cardSelected = cardSelected;
        rectangle.setVisible(true);
        setCoordinate(x,y);
    }

    public void greenCardLvl3selected() {
        selectCard(0, rectangle00, 0, 0);
    }

    public void greenCardLvl2selected() {
        selectCard(4, rectangle10, 1, 0);
    }

    public void greenCardLvl1selected() {
        selectCard(8, rectangle20, 2, 0);
    }

    public void blueCardLvl3selected() {
        selectCard(1, rectangle01, 0, 1);
    }

    public void blueCardLvl2selected() {
        selectCard(5, rectangle11, 0, 2);
    }

    public void blueCardLvl1selected() {
        selectCard(9, rectangle21, 2, 1);
    }

    public void yellowCardLvl3selected() {
        selectCard(2, rectangle02, 0, 2);
    }

    public void yellowCardLvl2selected() {
        selectCard(6, rectangle12, 1, 2);
    }

    public void yellowCardLvl1selected() {
        selectCard(10, rectangle22, 2, 2);
    }

    public void purpleCardLvl3selected() {
        selectCard(3, rectangle03, 0, 3);
    }

    public void purpleCardLvl2selected() {
        selectCard(7, rectangle13, 1, 3);
    }

    public void purpleCardLvl1selected() {
        selectCard(11, rectangle23, 2, 3);
    }

    private void setCoordinate(int x, int y){
        cardSelectedX = x;
        cardSelectedY = y;
    }

}
