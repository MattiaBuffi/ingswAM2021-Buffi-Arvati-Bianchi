package it.polimi.ingsw.Client.GUI.FXMLControllers;

import it.polimi.ingsw.Client.App;
import it.polimi.ingsw.Client.GUI.ControllerManager;
//import it.polimi.ingsw.Utils.StaticMessageObservable;
import it.polimi.ingsw.Controller.ClientMessageController;
import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.ClientMessages.TakeResources;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Utils.Observable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ResourceMarketTab extends Observable<Message<ClientEventHandler>> implements Initializable {
    @FXML
    ImageView ivMarble00, ivMarble01, ivMarble02, ivMarble03, ivMarble10, ivMarble11, ivMarble12, ivMarble13, ivMarble20, ivMarble21, ivMarble22, ivMarble23, ivMarbleBonus;
    @FXML
    Button arrow1, arrow2, arrow3, arrow4, arrow5, arrow6, arrow7;
    @FXML
    Rectangle rectangle1, rectangle2, rectangle3, rectangle4, rectangle5, rectangle6, rectangle7;

    private int selection = -1;
    private ImageView[][] rowsColumnSelectable;

    public void takeResources(){
        if(selection != -1){
            TakeResources message = new TakeResources(selection);
            notify(message);
        }
    }

    public void updateMarket(List<Marble.Color> colors, int position) {
        int i = 0;
        for(Marble.Color color: colors){
            rowsColumnSelectable[position - 1][i].setImage(getMarbleImage(color));
            i++;
        }
    }

    public Image getMarbleImage(Marble.Color color){
        return new Image(App.class.getResourceAsStream("images/marbles/" + color.toString() + ".png"));
    }

    public void getColumn1() {
        hideRectangle(selection);
        selection = 1;
        rectangle1.setVisible(true);
    }

    public void getColumn2() {
        hideRectangle(selection);
        selection = 2;
        rectangle2.setVisible(true);
    }

    public void getColumn3() {
        hideRectangle(selection);
        selection = 3;
        rectangle3.setVisible(true);
    }

    public void getColumn4() {
        hideRectangle(selection);
        selection = 4;
        rectangle4.setVisible(true);
    }

    public void getRow1() {
        hideRectangle(selection);
        selection = 7;
        rectangle7.setVisible(true);
    }

    public void getRow2() {
        hideRectangle(selection);
        selection = 6;
        rectangle6.setVisible(true);
    }

    public void getRow3() {
        hideRectangle(selection);
        selection = 5;
        rectangle5.setVisible(true);
    }

    private void hideRectangle(int cardSelected){
        switch (cardSelected){
            case 1:
                rectangle1.setVisible(false);
                break;
            case 2:
                rectangle2.setVisible(false);
                break;
            case 3:
                rectangle3.setVisible(false);
                break;
            case 4:
                rectangle4.setVisible(false);
                break;
            case 5:
                rectangle5.setVisible(false);
                break;
            case 6:
                rectangle6.setVisible(false);
                break;
            case 7:
                rectangle7.setVisible(false);
                break;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ControllerManager.addController(this);
        addObserver(new ClientMessageController());

        ImageView[] column1 = new ImageView[]{ivMarble00, ivMarble10, ivMarble20};
        ImageView[] column2 = new ImageView[]{ivMarble01, ivMarble11, ivMarble21};
        ImageView[] column3 = new ImageView[]{ivMarble02, ivMarble12, ivMarble22};
        ImageView[] column4 = new ImageView[]{ivMarble03, ivMarble13, ivMarble23};
        ImageView[] row1 = new ImageView[]{ivMarble23, ivMarble22, ivMarble21, ivMarble20};
        ImageView[] row2 = new ImageView[]{ivMarble13, ivMarble12, ivMarble11, ivMarble10};
        ImageView[] row3 = new ImageView[]{ivMarble03, ivMarble02, ivMarble01, ivMarble00};
        rowsColumnSelectable = new ImageView[][]{column1, column2, column3, column4, row1, row2, row3};
    }
}
