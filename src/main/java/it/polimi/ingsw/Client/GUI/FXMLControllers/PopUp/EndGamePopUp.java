package it.polimi.ingsw.Client.GUI.FXMLControllers.PopUp;

import it.polimi.ingsw.Client.GUI.FXMLControllers.Game.ScoreboardTab;
import it.polimi.ingsw.Client.ModelData.Player;
import it.polimi.ingsw.Client.ViewBackEnd;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EndGamePopUp {
    @FXML
    Label title;
    @FXML
    ListView lvUsernames, lvPoints;

    private List<String> usernames;
    private List<Integer> points;

    private List<ScoreboardTab.PlayerScore> scoreboard;

    private final String[] TITLE_MESSAGE = {"GAME OVER!", "YOU WIN"};

    public void initData(ScoreboardTab mainController){
        scoreboard = new ArrayList<>();

        if(mainController.getBackEnd().getModel().winner.equals(mainController.getBackEnd().getMyUsername())){
            title.setText(TITLE_MESSAGE[1]);
        } else {
            title.setText(TITLE_MESSAGE[0]);
        }

        for(Player p: mainController.getBackEnd().getModel().players){
            scoreboard.add(new ScoreboardTab.PlayerScore(p.getUsername(), p.getVictoryPoints()));
        }

        Comparator<ScoreboardTab.PlayerScore> comparator = Comparator.comparingInt(ScoreboardTab.PlayerScore::getPoints);
        scoreboard.sort(comparator);
        Collections.reverse(scoreboard);

        usernames = getUsernamesList(scoreboard);
        points = getPointsList(scoreboard);

        lvUsernames.setItems(FXCollections.observableList(usernames));
        lvPoints.setItems(FXCollections.observableList(points));
    }

    public void close() {
        Stage stage = (Stage) title.getScene().getWindow();
        stage.close();
    }

    public List<String> getUsernamesList(List<ScoreboardTab.PlayerScore> list){
        List<String> tmp = new ArrayList<>();
        for(ScoreboardTab.PlayerScore p: list){
            tmp.add(p.getUsername());
        }
        return tmp;
    }

    public List<Integer> getPointsList(List<ScoreboardTab.PlayerScore> list){
        List<Integer> tmp = new ArrayList<>();
        for(ScoreboardTab.PlayerScore p: list){
            tmp.add(p.getPoints());
        }
        return tmp;
    }
}
