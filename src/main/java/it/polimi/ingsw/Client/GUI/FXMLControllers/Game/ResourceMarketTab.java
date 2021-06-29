package it.polimi.ingsw.Client.GUI.FXMLControllers.Game;

import it.polimi.ingsw.Client.App;
import it.polimi.ingsw.Client.GUI.Layout;
import it.polimi.ingsw.Client.ModelData.ResourceMarket;
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

public class ResourceMarketTab implements Layout, GameTab{
    @FXML
    ImageView ivMarble00, ivMarble01, ivMarble02, ivMarble03, ivMarble10, ivMarble11, ivMarble12, ivMarble13, ivMarble20, ivMarble21, ivMarble22, ivMarble23, ivMarbleBonus;
    @FXML
    ImageView leaderPower1, leaderPower2;
    @FXML
    Button arrow1, arrow2, arrow3, arrow4, arrow5, arrow6, arrow7;
    @FXML
    Rectangle rectangle1, rectangle2, rectangle3, rectangle4, rectangle5, rectangle6, rectangle7;


    private int selection = -1;
    private ImageView[][] resourceMatrix;

    private ViewBackEnd backEnd;

    @Override
    public void setup(ViewBackEnd backEnd) {
        this.backEnd = backEnd;

        resourceMatrix = new ImageView[][]{{ivMarble00, ivMarble01, ivMarble02, ivMarble03},
                {ivMarble10, ivMarble11, ivMarble12, ivMarble13},
                {ivMarble20, ivMarble21, ivMarble22, ivMarble23}};
    }

    @Override
    public void update() {
        ResourceMarket market = backEnd.getModel().resourceMarket;

        for (int i = 0; i < 4; i++) {

            List<Marble.Color> colors = new ArrayList<>();

            for(Marble m: market.get(i)){
                colors.add(m.getColor());
            }

            for(int j=0; j<colors.size(); j++){
                resourceMatrix[j][i].setImage(getMarbleImage(colors.get(j)));
            }
        }

        ivMarbleBonus.setImage(getMarbleImage(market.getBonusMarble().getColor()));
    }



    public void takeResources(){
        if(selection != -1){
            TakeResources message = new TakeResources(selection);
            hideRectangle(selection);
            backEnd.notify(message);
        }
    }

    public void showLeaderPower(String id){
        if(!leaderPower1.isVisible()){
            leaderPower1.setVisible(true);
            leaderPower1.setImage(getLeaderPowerImage(id));
        } else {
            leaderPower2.setVisible(true);
            leaderPower2.setImage(getLeaderPowerImage(id));
        }
    }



    private void get(int position, Rectangle rectangle){
        hideRectangle(selection);
        this.selection = position;
        rectangle.setVisible(true);
    }


    public void getColumn1() {
        get(0, rectangle1);
    }

    public void getColumn2() {
        get(1, rectangle2);
    }

    public void getColumn3() {
        get(2, rectangle3);
    }

    public void getColumn4() {
        get(3, rectangle4);
    }

    public void getRow1() {
        get(4, rectangle7);
    }

    public void getRow2() {
        get(5, rectangle6);
    }

    public void getRow3() {
        get(6, rectangle5);
    }

    private void hideRectangle(int cardSelected){
        switch (cardSelected){
            case 0:
                rectangle1.setVisible(false);
                break;
            case 1:
                rectangle2.setVisible(false);
                break;
            case 2:
                rectangle3.setVisible(false);
                break;
            case 3:
                rectangle4.setVisible(false);
                break;
            case 4:
                rectangle5.setVisible(false);
                break;
            case 5:
                rectangle6.setVisible(false);
                break;
            case 6:
                rectangle7.setVisible(false);
                break;
        }
    }


    public Image getMarbleImage(Marble.Color color){
        return new Image(App.class.getResourceAsStream("images/marbles/" + color.toString() + ".png"));
    }

    private Image getLeaderPowerImage(String id) {
        Image image = null;
        switch (id){
            case "9":
                image = new Image(App.class.getResourceAsStream("images/leaderPowers/leaderMarblePURPLE.png"));
                break;
            case "10":
                image = new Image(App.class.getResourceAsStream("images/leaderPowers/leaderMarbleBLUE.png"));
                break;
            case "11":
                image = new Image(App.class.getResourceAsStream("images/leaderPowers/leaderMarbleGREY.png"));
                break;
            case "12":
                image = new Image(App.class.getResourceAsStream("images/leaderPowers/leaderMarbleYELLOW.png"));
                break;
        }
        return image;
    }



}
