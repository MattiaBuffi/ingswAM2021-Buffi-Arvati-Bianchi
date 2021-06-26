package it.polimi.ingsw.Client.GUI.FXMLControllers.Game;

import it.polimi.ingsw.Client.App;
import it.polimi.ingsw.Client.GUI.Layout;
import it.polimi.ingsw.Client.ViewBackEnd;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.Map;

public class VaticanRoutePane implements Layout,GameTab  {
    @FXML
    public ImageView cross1, cross2, cross3, cross4;
    @FXML
    public ImageView popeToken1, popeToken2, popeToken3;

    /*private final int[] CROSS_SHIFT_X = {0, 94, 94, 0, 0, 94, 94, 94, 94, 94, 0, 0, 94, 94, 94, 94, 94, 0, 0, 94, 94, 94, 94, 94, 94};
    private final int[] CROSS_SHIFT_Y = {0, 0, 0, -62, -62, 0, 0, 0, 0, 0, 62, 62, 0, 0, 0, 0, 0, -62, -62, 0, 0, 0, 0, 0, 0};
    private final int[] INITIAL_POS_X = {68, 115, 68, 115};
    private final int[] INITIAL_POS_Y = {148, 201, 201, 148};*/

    private final Position[] CROSS_SHIFT = {new Position(0,0), new Position(94,0), new Position(94,0), new Position(0,-62),
            new Position(0,-62), new Position(94,0), new Position(94,0), new Position(94,0), new Position(94,0),
            new Position(94,0), new Position(0,62), new Position(0,62), new Position(94,0), new Position(94,0),
            new Position(94,0), new Position(94,0), new Position(94,0),new Position(0,-62), new Position(0,-62),
            new Position(94,0), new Position(94,0), new Position(94,0), new Position(94,0), new Position(94,0),
            new Position(94,0)};
    private final Position[] INITIAL_POSITION = {new Position(68,148), new Position(115,201), new Position(68,201), new Position(115,148)};
    private ImageView[] crossArray;

    private ViewBackEnd backEnd;
    private final Map<String, ImageView> imageViewMap = new HashMap<>();
    private final Map<String, Position> crossInitialPositions = new HashMap<>();


    @Override
    public void setup(ViewBackEnd backEnd) {

        this.backEnd = backEnd;
        crossArray = new ImageView[]{cross1, cross2, cross3, cross4};

    }

    @Override
    public void update() {

    }



    private void initializeCrosses() {
        int playersNumber = backEnd.getModel().players.size();
        if(playersNumber == 1){
            cross1.setVisible(true);
            imageViewMap.put(backEnd.getMyUsername(), cross1);
            crossInitialPositions.put(backEnd.getMyUsername(), INITIAL_POSITION[0]);
            cross2.setImage(new Image(App.class.getResourceAsStream("images/token/blackCross.png")));
            imageViewMap.put("blackCross", cross2);//??
            crossInitialPositions.put(backEnd.getMyUsername(), INITIAL_POSITION[1]);
        } else {
            for (int i = 0; i < backEnd.getModel().players.size(); i++) {
                crossArray[i].setVisible(true);
                imageViewMap.put(backEnd.getModel().players.get(i).getUsername(), crossArray[i]);
                crossInitialPositions.put(backEnd.getModel().players.get(i).getUsername(), INITIAL_POSITION[i]);
            }
        }
    }

    public void updateCross(String username, int position){
        setPosition(imageViewMap.get(username), crossInitialPositions.get(username), position);
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

    private class Position{
        private final Integer x;
        private final Integer y;

        private Position(Integer x, Integer y) {
            this.x = x;
            this.y = y;
        }
    }
}
