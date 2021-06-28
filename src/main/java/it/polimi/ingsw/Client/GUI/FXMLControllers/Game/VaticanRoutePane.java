package it.polimi.ingsw.Client.GUI.FXMLControllers.Game;

import it.polimi.ingsw.Client.App;
import it.polimi.ingsw.Client.GUI.Layout;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ClientMessages.EndTurn;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VaticanRoutePane implements Layout,GameTab  {
    @FXML
    public ImageView cross1, cross2, cross3, cross4;
    @FXML
    public ImageView popeToken1, popeToken2, popeToken3;

    private final Position[] CROSS_SHIFT = {new Position(94,0), new Position(94,0), new Position(0,-62),
            new Position(0,-62), new Position(94,0), new Position(94,0), new Position(94,0), new Position(94,0),
            new Position(94,0), new Position(0,62), new Position(0,62), new Position(94,0), new Position(94,0),
            new Position(94,0), new Position(94,0), new Position(94,0),new Position(0,-62), new Position(0,-62),
            new Position(94,0), new Position(94,0), new Position(94,0), new Position(94,0), new Position(94,0),
            new Position(94,0)};
    private final Position[] INITIAL_POSITION = {new Position(68,148), new Position(115,201), new Position(68,201), new Position(115,148)};
    private ImageView[] crossArray;

    private ViewBackEnd backEnd;
    private boolean firstUpdate = true;
    private final Map<String, ImageView> imageViewMap = new HashMap<>();
    private final Map<String, Position> crossInitialPositions = new HashMap<>();
    private List<String> usernames = new ArrayList<>();


    @Override
    public void setup(ViewBackEnd backEnd) {

        this.backEnd = backEnd;
        crossArray = new ImageView[]{cross1, cross2, cross3, cross4};
    }

    @Override
    public void update() {
        if(firstUpdate) initializeCrosses();
        updateCrosses();
    }

    private void initializeCrosses() {
        if(backEnd.getModel().players.size() == 1){
            cross1.setVisible(true);
            imageViewMap.put(backEnd.getMyUsername(), cross1);
            crossInitialPositions.put(backEnd.getMyUsername(), INITIAL_POSITION[0]);

            cross2.setImage(new Image(App.class.getResourceAsStream("images/token/blackCross.png")));
            cross2.setVisible(true);
            imageViewMap.put("cpu", cross2);
            crossInitialPositions.put("cpu", INITIAL_POSITION[1]);

            usernames.add(backEnd.getMyUsername());
            usernames.add(backEnd.getMyUsername());
        } else {
            for (int i = 0; i < backEnd.getModel().players.size(); i++) {
                crossArray[i].setVisible(true);
                imageViewMap.put(backEnd.getModel().players.get(i).getUsername(), crossArray[i]);
                crossInitialPositions.put(backEnd.getModel().players.get(i).getUsername(), INITIAL_POSITION[i]);
                usernames.add(backEnd.getModel().players.get(i).getUsername());
            }
        }
        firstUpdate = false;
    }

    private void updateCrosses() {
        for(String s: usernames){
            setPosition(imageViewMap.get(s), crossInitialPositions.get(s), backEnd.getModel().vaticanRoute.getPlayerFaithPoint(s));
        }
    }

    private void setPosition(ImageView cross, Position initialPosition, int position){
        cross.setLayoutX(initialPosition.x + xToAdd(position));
        cross.setLayoutY(initialPosition.y + yToAdd(position));
    }

    private int xToAdd(int faithPoints){
        int x_toAdd = 0;
        for(int i=0; i<faithPoints; i++){
            x_toAdd += CROSS_SHIFT[i].x;
        }
        return x_toAdd;
    }

    private int yToAdd(int faithPoints){
        int y_toAdd = 0;
        for(int i=0; i<faithPoints; i++){
            y_toAdd += CROSS_SHIFT[i].y;
        }
        return y_toAdd;
    }

    public void activatePopeFavor(int pos){
        switch(pos){
            case 1:
                popeToken1.setImage(new Image(App.class.getResourceAsStream("images/token/popeFavorON_1.png")));
                break;
            case 2:
                popeToken2.setImage(new Image(App.class.getResourceAsStream("images/token/popeFavorON_2.png")));
                break;
            case 3:
                popeToken3.setImage(new Image(App.class.getResourceAsStream("images/token/popeFavorON_3.png")));
                break;
        }
    }

    public void endTurn() {
        EndTurn message = new EndTurn();
        backEnd.notify(message);
    }

    private class Position{
        private final Integer x;
        private final Integer y;

        private Position(Integer x, Integer y) {
            this.x = x;
            this.y = y;
        }
    }
}
