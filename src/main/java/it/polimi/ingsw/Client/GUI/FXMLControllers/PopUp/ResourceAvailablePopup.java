package it.polimi.ingsw.Client.GUI.FXMLControllers.PopUp;

import it.polimi.ingsw.Client.GUI.FXMLControllers.Game.StorageTab;
import it.polimi.ingsw.Message.ClientMessages.DepositResource;
import it.polimi.ingsw.Model.Marble.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ResourceAvailablePopup extends ResourceViewer {
    @FXML
    ImageView ivMarble;
    @FXML
    Pane shelfSelectionPane, marbleSelectionPane;
    @FXML
    ChoiceBox<Integer> shelfSelector;
    @FXML
    ChoiceBox<String> colorSelector;
    @FXML
    Button discardButton;

    private final ColorHandler handler = new ColorHandler();
    private boolean whiteMarble = false;
    private Marble marble;
    private StorageTab mainController;


    public void depositResource() {
        if(shelfSelector.getSelectionModel().getSelectedItem() != null){
            if(!whiteMarble){
                DepositResource message = new DepositResource(marble.getColor(), shelfSelector.getSelectionModel().getSelectedItem() - 1);
                mainController.getBackEnd().notify(message);
            } else {
                if(colorSelector.getSelectionModel().getSelectedItem() != null) {
                    DepositResource message = new DepositResource(getColor(colorSelector.getSelectionModel().getSelectedItem()),
                            shelfSelector.getSelectionModel().getSelectedItem() - 1);
                    mainController.getBackEnd().notify(message);
                }
            }
        }
    }

    public void discardResource() {
        Stage stage = (Stage) ivMarble.getScene().getWindow();
        stage.close();
    }

    public void initData(Marble marble, StorageTab mainController, boolean discardVisibility){
        marble.accept(handler);
        this.marble = marble;
        this.mainController = mainController;

        discardButton.setVisible(discardVisibility);
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
            marbleSelectionPane.setVisible(true);
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
