package it.polimi.ingsw.Client.GUI.FXMLControllers.Game;

//import it.polimi.ingsw.Utils.StaticMessageObservable;
import it.polimi.ingsw.Client.GUI.Layout;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ClientMessages.TakeResources;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class ResourceMarketTab implements Layout{
    @FXML
    ImageView ivMarble00, ivMarble01, ivMarble02, ivMarble03, ivMarble10, ivMarble11, ivMarble12, ivMarble13, ivMarble20, ivMarble21, ivMarble22, ivMarble23, ivMarbleBonus;
    @FXML
    Button arrow1, arrow2, arrow3, arrow4, arrow5, arrow6, arrow7;
    @FXML
    Rectangle rectangle1, rectangle2, rectangle3, rectangle4, rectangle5, rectangle6, rectangle7;

    private int selection = -1;



    private ViewBackEnd backEnd;

    @Override
    public void setup(ViewBackEnd backEnd) {
        System.out.println("ResourceMarketTab");
        this.backEnd = backEnd;

    }




    public void takeResources(){
        if(selection != -1){
            TakeResources message = new TakeResources(selection);
            backEnd.notify(message);
        }
    }


    private void get(int position, Rectangle rectangle){
        hideRectangle(selection);
        this.selection = position;
        rectangle.setVisible(true);
    }

    public void getColumn1() {
        get(1, rectangle1);
         /*
        hideRectangle(selection);
        selection = 1;
        rectangle1.setVisible(true);*/
    }

    public void getColumn2() {
        get(2, rectangle2);
        /*
        hideRectangle(selection);
        selection = 2;
        rectangle2.setVisible(true);*/
    }

    public void getColumn3() {
        get(3, rectangle3);
        /*
        hideRectangle(selection);
        selection = 3;
        rectangle3.setVisible(true);*/
    }

    public void getColumn4() {
        get(4, rectangle4);
        /*
        hideRectangle(selection);
        selection = 4;
        rectangle4.setVisible(true);
        */
    }

    public void getRow1() {
        get(7, rectangle7);
        /*
        hideRectangle(selection);
        selection = 7;
        rectangle7.setVisible(true);
        */
    }

    public void getRow2() {
        get(6, rectangle6);
        /*
        hideRectangle(selection);
        selection = 6;
        rectangle6.setVisible(true);

         */
    }

    public void getRow3() {
        get(5, rectangle5);
        /*
        hideRectangle(selection);
        selection = 5;
        rectangle5.setVisible(true);

         */
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
