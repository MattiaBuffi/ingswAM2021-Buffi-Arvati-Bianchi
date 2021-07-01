package it.polimi.ingsw.Client.GUI.FXMLControllers.PopUp;

import it.polimi.ingsw.Client.GUI.FXMLControllers.Game.ScoreboardTab;
import it.polimi.ingsw.Client.ViewBackEnd;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.List;

public class EndGamePopUp {
    @FXML
    Label title;
    @FXML
    ListView lvUsernames, lvPoints;

    private List<String> usernames;
    private List<Integer> points;

    private final String[] TITLE_MESSAGE = {"GAME OVER!", "YOU WIN"};

    public void initData(ScoreboardTab mainController){
        if(mainController.getBackEnd().getModel().winner.equals(mainController.getBackEnd().getMyUsername())){
            title.setText(TITLE_MESSAGE[1]);
        } else {
            title.setText(TITLE_MESSAGE[0]);
        }

        usernames = mainController.getUsernameScoreboard();
        points = mainController.getPointsScoreboard();

        lvUsernames.setItems(FXCollections.observableList(usernames));
        lvPoints.setItems(FXCollections.observableList(points));
    }

    public void backToHome() {
        //Coming back to home
    }
}
