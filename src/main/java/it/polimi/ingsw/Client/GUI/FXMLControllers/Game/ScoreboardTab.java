package it.polimi.ingsw.Client.GUI.FXMLControllers.Game;

import it.polimi.ingsw.Client.GUI.FXMLControllers.PopUp.PopUpManager;
import it.polimi.ingsw.Client.GUI.Layout;
import it.polimi.ingsw.Client.ModelData.Player;
import it.polimi.ingsw.Client.ViewBackEnd;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class ScoreboardTab implements Layout, GameTab {

    @FXML
    Label player1, player2, player3, player4;
    @FXML
    Label points1, points2, points3, points4;
    @FXML
    HBox hbox1, hbox2, hbox3, hbox4;

    private Label[] playersLabelsArray;
    private Label[] pointsLabelsArray;
    private HBox[] hboxArray;

    private int selection = -1;
    private List<PlayerScore> players = new ArrayList<>();
    private ViewBackEnd backEnd;


    @Override
    public void setup(ViewBackEnd backEnd) {

        System.out.println("ScoreBoardTab");
        playersLabelsArray = new Label[]{player1, player2, player3, player4};
        pointsLabelsArray = new Label[]{points1, points2, points3, points4};
        hboxArray = new HBox[]{hbox1, hbox2, hbox3, hbox4};
        this.backEnd = backEnd;

    }

    @Override
    public void update() {

    }



    public void viewPlayerSelected() {
        if(selection!=-1 && !playersLabelsArray[selection].getText().equals(backEnd.getMyUsername())){
            try {
                PopUpManager.showPlayerBoardPopUp(playersLabelsArray[selection].getText(), backEnd);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setupView(){
        for(Player p: backEnd.getModel().players){
            players.add(new PlayerScore(p.getUsername()));
        }
        for(int i=0; i<players.size(); i++){
            playersLabelsArray[i].setText(players.get(i).getUsername());
            hboxArray[i].setVisible(true);
        }
    }

    public void updateScoreboard(){
        updatePoints();
        updateView();
    }

    private void updatePoints() {
        for(PlayerScore p: players){
            p.setPoints(backEnd.getModel().getPlayer(p.getUsername()).getVictoryPoints());
        }
        Comparator<PlayerScore> comparator = Comparator.comparing(PlayerScore::getPoints);
        players.sort(comparator);
    }

    private void updateView() {
        for(int i=0; i<players.size(); i++){
            playersLabelsArray[i].setText(players.get(i).getUsername());
            pointsLabelsArray[i].setText(String.valueOf(players.get(i).getPoints()));
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



    private class PlayerScore{
        private final String username;
        private Integer points;

        public PlayerScore(String username) {
            this.username = username;
            points = 0;
        }

        public String getUsername() {
            return username;
        }

        public Integer getPoints() {
            return points;
        }

        public void setPoints(int points) {
            this.points = points;
        }
    }
}
