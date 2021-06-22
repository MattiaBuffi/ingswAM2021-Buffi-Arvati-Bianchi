package it.polimi.ingsw.Client.GUI.FXMLControllers;

import it.polimi.ingsw.Client.App;
import it.polimi.ingsw.Client.GUI.Layout;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Message.ClientMessages.DepositResource;
import it.polimi.ingsw.Model.Marble.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ResourceAvailablePopup implements Layout {
    @FXML
    ImageView ivMarble;
    @FXML
    Pane shelfSelectionPane;
    @FXML
    ChoiceBox<Integer> shelfSelector;
    @FXML
    ChoiceBox<String> colorSelector;

    private ColorHandler handler = new ColorHandler();
    private boolean whiteMarble = false;
    private Marble marble;
    private ViewBackEnd backEnd;

    @Override
    public void setup(ViewBackEnd backEnd) {
        this.backEnd = backEnd;
    }

    public void depositResource() {
        if(shelfSelector.getSelectionModel().getSelectedItem() != null){
            if(!whiteMarble){
                DepositResource message = new DepositResource(marble.getColor(), shelfSelector.getSelectionModel().getSelectedItem());
                backEnd.notify(message);
            } else {
                if(colorSelector.getSelectionModel().getSelectedItem() != null) {
                    DepositResource message = new DepositResource(getColor(colorSelector.getSelectionModel().getSelectedItem()),
                            shelfSelector.getSelectionModel().getSelectedItem());
                    backEnd.notify(message);
                }
            }
        }
    }

    private Marble.Color getColor(String color){
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

    public void discardResource() {
        Stage stage = (Stage) ivMarble.getScene().getWindow();
        stage.close();
    }

    public void initData(Marble marble){
        marble.accept(handler);
        this.marble = marble;
    }

    public Image getMarbleImage(Marble.Color color){
        return new Image(App.class.getResourceAsStream("images/marbles/" + color.toString() + ".png"));
    }

    private class ColorHandler implements MarbleHandler {
        @Override
        public void handle(RedMarble marble) {}

        @Override
        public void handle(WhiteMarble marble) {}

        @Override
        public void handle(ResourceMarble marble){
            whiteMarble = false;
            ivMarble.setImage(getMarbleImage(marble.getColor()));
        }

        @Override
        public void handle(SelectableMarble marble){
            shelfSelectionPane.setVisible(true);
            whiteMarble = true;
            ivMarble.setImage(getMarbleImage(Marble.Color.WHITE));

            List<String> colors = new ArrayList<>();
            for(Marble.Color c: marble.getSelectableColors()){
                colors.add(c.toString());
            }

            colorSelector.setItems(FXCollections.observableList(colors));
        }
    }
}
