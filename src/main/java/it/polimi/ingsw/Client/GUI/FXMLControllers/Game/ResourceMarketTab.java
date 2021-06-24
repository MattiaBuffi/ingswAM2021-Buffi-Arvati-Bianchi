package it.polimi.ingsw.Client.GUI.FXMLControllers.Game;

//import it.polimi.ingsw.Utils.StaticMessageObservable;
import it.polimi.ingsw.Client.App;
import it.polimi.ingsw.Client.GUI.Layout;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ClientMessages.TakeResources;
import it.polimi.ingsw.Model.Marble.Marble;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class ResourceMarketTab implements Layout{
    @FXML
    ImageView ivMarble00, ivMarble01, ivMarble02, ivMarble03, ivMarble10, ivMarble11, ivMarble12, ivMarble13, ivMarble20, ivMarble21, ivMarble22, ivMarble23, ivMarbleBonus;
    @FXML
    ImageView leaderPower1, leaderPower2;
    @FXML
    Button arrow1, arrow2, arrow3, arrow4, arrow5, arrow6, arrow7;
    @FXML
    Rectangle rectangle1, rectangle2, rectangle3, rectangle4, rectangle5, rectangle6, rectangle7;


    private int selection = -1;
    private ImageView[][] rowsColumnSelectable;
    private ImageView[] leaderImageViews;

    private ViewBackEnd backEnd;

    @Override
    public void setup(ViewBackEnd backEnd) {
        System.out.println("ResourceMarketTab");
        this.backEnd = backEnd;

        ImageView[] column1 = new ImageView[]{ivMarble00, ivMarble10, ivMarble20};
        ImageView[] column2 = new ImageView[]{ivMarble01, ivMarble11, ivMarble21};
        ImageView[] column3 = new ImageView[]{ivMarble02, ivMarble12, ivMarble22};
        ImageView[] column4 = new ImageView[]{ivMarble03, ivMarble13, ivMarble23};
        ImageView[] row1 = new ImageView[]{ivMarble23, ivMarble22, ivMarble21, ivMarble20};
        ImageView[] row2 = new ImageView[]{ivMarble13, ivMarble12, ivMarble11, ivMarble10};
        ImageView[] row3 = new ImageView[]{ivMarble03, ivMarble02, ivMarble01, ivMarble00};
        rowsColumnSelectable = new ImageView[][]{column1, column2, column3, column4, row1, row2, row3};
        leaderImageViews = new ImageView[]{leaderPower1, leaderPower2};
    }


    public void takeResources(){
        if(selection != -1){
            TakeResources message = new TakeResources(selection);
            backEnd.notify(message);
        }
    }

    public void updateMarket(List<Marble> marbles, int position) {
        List<Marble.Color> colors = new ArrayList<>();
        for(Marble m: marbles){
            colors.add(m.getColor());
        }
        for(int i=0; i<colors.size(); i++){
            rowsColumnSelectable[position - 1][i].setImage(getMarbleImage(colors.get(i)));
        }
    }

    public void updateBonusMarble(Marble.Color color){
        ivMarbleBonus.setImage(getMarbleImage(color));
    }

    public void showLeaderMarble(int index, Marble.Color color){
        leaderImageViews[index].setImage(getLeaderPowerImage(color));
    }

    public Image getMarbleImage(Marble.Color color){
        return new Image(App.class.getResourceAsStream("images/marbles/" + color.toString() + ".png"));
    }

    private Image getLeaderPowerImage(Marble.Color color) {
        return new Image(App.class.getResourceAsStream("images/leaderPowers/leaderMarble" + color.toString() + ".png"));
    }

    private void get(int position, Rectangle rectangle){
        hideRectangle(selection);
        this.selection = position;
        rectangle.setVisible(true);
    }

    public void getColumn1() {
        get(1, rectangle1);
    }

    public void getColumn2() {
        get(2, rectangle2);
    }

    public void getColumn3() {
        get(3, rectangle3);
    }

    public void getColumn4() {
        get(4, rectangle4);
    }

    public void getRow1() {
        get(7, rectangle7);
    }

    public void getRow2() {
        get(6, rectangle6);
    }

    public void getRow3() {
        get(5, rectangle5);
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


}
