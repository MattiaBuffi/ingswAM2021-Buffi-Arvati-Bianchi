package it.polimi.ingsw.Client.GUI.FXMLControllers.PopUp;

import it.polimi.ingsw.Client.App;
import it.polimi.ingsw.Client.GUI.FXMLControllers.Game.LeaderCardsTab;
import it.polimi.ingsw.Client.GUI.Layout;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.LeaderCard;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ClientMessages.DiscardLeaderCard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

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
    private ImageView[] imageArray;
    private List<LeaderCard> leaderCards;
    private LeaderCardsTab mainController;


    private ViewBackEnd backEnd;

    @Override
    public void setup(ViewBackEnd backEnd) {
        this.backEnd = backEnd;

        rectArray = new Rectangle[]{rect1, rect2, rect3, rect4};
        imageArray = new ImageView[]{leaderCard1, leaderCard2, leaderCard3, leaderCard4};
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
        selectCard(0, rect1);
    }

    public void cardTwoSelected() {
        selectCard(1, rect2);
    }

    public void cardThreeSelected() {
        selectCard(2, rect3);
    }

    public void cardFourSelected() {
        selectCard(3, rect4);
    }


    private void hideRectangle(){
        rectArray[indexSelected.get(0) - 1].setVisible(false);
        selection[indexSelected.get(0) - 1] = false;
        indexSelected.remove(0);
    }


    public void discardLeaderCards() {
        List<String> id = new ArrayList<>();
        for(int i=0; i<selection.length; i++){
            if(!selection[i]){
                DiscardLeaderCard message = new DiscardLeaderCard(leaderCards.get(i).getId());
                backEnd.notify(message);
            } else {
                id.add(leaderCards.get(i).getId());
            }
        }

        mainController.showLeaderCard(id);

        Stage stage = (Stage) leaderCard1.getScene().getWindow();
        stage.close();
    }

    public void initData(List<LeaderCard> leaderCards, LeaderCardsTab mainController) {
        this.leaderCards = leaderCards;
        this.mainController = mainController;
        showLeaderCard();
    }

    private void showLeaderCard(){
        for(int i=0; i<leaderCards.size(); i++){
            imageArray[i].setImage(getCardImage(leaderCards.get(i).getId()));
        }
    }

    private Image getCardImage(String cardID){
        return new Image(App.class.getResourceAsStream("images/cards/developmentCards/LC_" + cardID + ".png"));
    }
}
