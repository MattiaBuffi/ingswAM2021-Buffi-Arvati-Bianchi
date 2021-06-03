package it.polimi.ingsw.GUI.FXMLControllers;

import it.polimi.ingsw.App;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;

public class NumberOfPlayerPage {
    @FXML
    ChoiceBox<Integer> choiceBox;

    public void goToWaitingRoom(){
        System.out.println("ITEM - " + choiceBox.getSelectionModel().getSelectedItem());
        /*try {
            showPopUp();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    private void showPopUp() throws IOException {
        App.showPopUp("waiting_page");
    }
}
