package it.polimi.ingsw.Client.GUI.FXMLControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;


public class ScoreboardTab {

    @FXML
    Label player1, player2, player3, player4;
    @FXML
    Label points1, points2, points3, points4;
    @FXML
    HBox hbox1, hbox2, hbox3, hbox4;

    private int selection = -1;

    public void viewPlayerSelected() {
    }

    public void playerSelected(MouseEvent mouseEvent) {
        Label tmp = (Label) mouseEvent.getSource();
        if(tmp.equals(player1)){
            cleanSelection();
            selection = 1;
            hbox1.getStyleClass().add("bg_beige_chiaro");
        }else if(tmp.equals(player2)){
            cleanSelection();
            selection = 2;
            hbox2.getStyleClass().add("bg_beige_chiaro");
        } else if(tmp.equals(player3)){
            cleanSelection();
            selection = 3;
            hbox3.getStyleClass().add("bg_beige_chiaro");
        } else if(tmp.equals(player4)){
            cleanSelection();
            selection = 4;
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
