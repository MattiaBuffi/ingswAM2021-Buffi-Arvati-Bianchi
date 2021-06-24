package it.polimi.ingsw.Client.GUI.FXMLControllers.PopUp;

import it.polimi.ingsw.Client.App;
import it.polimi.ingsw.Model.Marble.Marble;
import javafx.scene.image.Image;

public class ResourceViewer {

    protected Marble.Color getColor(String color){
        Marble.Color colorToReturn = null;
        switch (color){
            case "YELLOW":
                colorToReturn = Marble.Color.YELLOW;
                break;
            case "PURPLE":
                colorToReturn = Marble.Color.PURPLE;
                break;
            case "BLUE":
                colorToReturn = Marble.Color.BLUE;
                break;
            case "GREY":
                colorToReturn = Marble.Color.GREY;
                break;
        }
        return colorToReturn;
    }

    protected Image getMarbleImage(Marble.Color color){
        return new Image(App.class.getResourceAsStream("images/marbles/" + color.toString() + ".png"));
    }

    protected Image getResourceImage(Marble.Color color){
        Image image = null;
        switch (color){
            case YELLOW:
                image = new Image(App.class.getResourceAsStream("images/token/coin.png"));
                break;
            case PURPLE:
                image = new Image(App.class.getResourceAsStream("images/token/servant.png"));
                break;
            case BLUE:
                image = new Image(App.class.getResourceAsStream("images/token/shield.png"));
                break;
            case GREY:
                image = new Image(App.class.getResourceAsStream("images/token/stone.png"));
                break;
        }
        return image;
    }
}
