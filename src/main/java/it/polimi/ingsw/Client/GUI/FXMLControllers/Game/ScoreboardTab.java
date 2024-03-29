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
import java.util.*;


public class ScoreboardTab implements Layout, GameTab {

    @FXML
    Label player1, player2, player3, player4;
    @FXML
    Label points1, points2, points3, points4;
    @FXML
    HBox hbox1, hbox2, hbox3, hbox4;

    private HBox[] hboxArray;
    private Label[] playersLabelsArray;
    private Label[] pointsLabelsArray;

    private Label selection = null;
    private List<PlayerScore> players = new ArrayList<>();
    private ViewBackEnd backEnd;
    private boolean firstUpdate = true;


    @Override
    public void setup(ViewBackEnd backEnd) {

        this.backEnd = backEnd;
        hboxArray = new HBox[]{hbox1, hbox2, hbox3, hbox4};
        playersLabelsArray = new Label[]{player1, player2, player3, player4};
        pointsLabelsArray = new Label[]{points1, points2, points3, points4};

    }

    @Override
    public void update() {
        if(firstUpdate) setupView();
        updatePoints();
        updateView();
    }

    private void setupView(){

        try {
            List<Player> playerList = backEnd.getModel().players;

            for (Player player : playerList) {
                players.add(new PlayerScore(player.getUsername()));
            }
        } catch (NullPointerException e){
            System.err.println("Players not initialized!");
            e.printStackTrace();
        }

        for(int i=0; i<players.size(); i++){
            playersLabelsArray[i].setText(players.get(i).username);
            hboxArray[i].setVisible(true);
        }

        firstUpdate = false;
    }

    public void viewPlayerSelected() {
        if(selection != null && !selection.getText().equals(backEnd.getMyUsername()) && !selection.getText().equals("cpu")){
            try {
                PopUpManager.showPlayerBoardPopUp(selection.getText(), backEnd);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void updatePoints() {
        for(PlayerScore p: players){
            p.setPoints(backEnd.getModel().getPlayer(p.getUsername()).getVictoryPoints());
        }
        Comparator<PlayerScore> comparator = Comparator.comparingInt(PlayerScore::getPoints);
        players.sort(comparator);
        Collections.reverse(players);
    }

    private void updateView() {
        for(int i=0; i<players.size(); i++){
            playersLabelsArray[i].setText(players.get(i).getUsername());
            pointsLabelsArray[i].setText(String.valueOf(players.get(i).getPoints()));
        }
    }

    public void playerSelected(MouseEvent mouseEvent) {

        cleanSelection();
        selection = (Label) mouseEvent.getSource();
        selection.getParent().getStyleClass().add("bg_beige_chiaro");

    }

    private void cleanSelection(){
        if(selection != null){
            if(selection == player1){
                hbox1.getStyleClass().clear();
            } else if(selection == player2){
                hbox2.getStyleClass().clear();
            } else if(selection == player3){
                hbox3.getStyleClass().clear();
            } else if(selection == player4){
                hbox4.getStyleClass().clear();
            }
        }
    }

    public List<String> getUsernameScoreboard(){
        List<String> tmp = new ArrayList<>();
        for(PlayerScore p: players){
            tmp.add(p.username);
        }
        return tmp;
    }

    public List<Integer> getPointsScoreboard(){
        List<Integer> tmp = new ArrayList<>();
        for(PlayerScore p: players){
            tmp.add(p.points);
        }
        return tmp;
    }

    public ViewBackEnd getBackEnd() {
        return backEnd;
    }

    public static class PlayerScore{
        private final String username;
        private Integer points;

        public PlayerScore(String username) {
            this.username = username;
            points = 0;
        }

        public PlayerScore(String username, Integer points) {
            this.username = username;
            this.points = points;
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
