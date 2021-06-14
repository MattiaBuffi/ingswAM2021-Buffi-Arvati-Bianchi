package it.polimi.ingsw.Client.GUI.FXMLControllers.Game;

import it.polimi.ingsw.Client.GUI.Layout;
import it.polimi.ingsw.Client.ViewBackEnd;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;


public class ScoreboardTab implements Layout {

    @FXML
    Label player1, player2, player3, player4;
    @FXML
    Label points1, points2, points3, points4;
    @FXML
    HBox hbox1, hbox2, hbox3, hbox4;

    private Label[] playersArray;
    private Label[] pointsArray;
    private HBox[] hboxArray;

    private int selection = -1; //why not set the active Hbox???

    private ViewBackEnd backEnd;


    @Override
    public void setup(ViewBackEnd backEnd) {
        System.out.println("ScoreBoardTab");
        playersArray = new Label[]{player1, player2, player3, player4};
        pointsArray = new Label[]{points1, points2, points3, points4};
        hboxArray = new HBox[]{hbox1, hbox2, hbox3, hbox4};
        this.backEnd = backEnd;
        setupView();
    }

    public void viewPlayerSelected() {
        if(selection!=-1 && !playersArray[selection].getText().equals(backEnd.getMyUsername())){
            backEnd.getModel().getPlayer(playersArray[selection].getText());
        }
    }

    private void setupView(){
        for(int i=0; i<backEnd.getModel().players.size(); i++){
            //playersArray[i].setText(backEnd.getModel().players.get(i).getUsername());
            hboxArray[i].setVisible(true);
        }
    }

    public void playerSelected(MouseEvent mouseEvent) {
        Label tmp = (Label) mouseEvent.getSource();
        if(tmp.equals(player1)){
            cleanSelection();
            selection = 0;
            hbox1.getStyleClass().add("bg_beige_chiaro");
        }else if(tmp.equals(player2)){
            cleanSelection();
            selection = 1;
            hbox2.getStyleClass().add("bg_beige_chiaro");
        } else if(tmp.equals(player3)){
            cleanSelection();
            selection = 2;
            hbox3.getStyleClass().add("bg_beige_chiaro");
        } else if(tmp.equals(player4)){
            cleanSelection();
            selection = 3;
            hbox4.getStyleClass().add("bg_beige_chiaro");
        }
    }

    private void cleanSelection(){
        switch (selection){
            case 1:
                hbox1.getStyleClass().clear();
                break;
            case 2:
                hbox2.getStyleClass().clear();
                break;
            case 3:
                hbox3.getStyleClass().clear();
                break;
            case 4:
                hbox4.getStyleClass().clear();
                break;
        }
    }

}
