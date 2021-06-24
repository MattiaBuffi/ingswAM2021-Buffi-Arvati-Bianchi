package it.polimi.ingsw.Client.GUI.FXMLControllers.PopUp;

import it.polimi.ingsw.Client.App;
import it.polimi.ingsw.Client.GUI.FXMLControllers.Game.StorageTab;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.Shelf;
import it.polimi.ingsw.Model.Marble.Marble;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;


public class MoveResourcePopup extends ResourceViewer{

    @FXML
    ImageView resource1, resource2, resource3;
    @FXML
    ChoiceBox<String> shelfSelector;

    private StorageTab mainController;

    public void initData(StorageTab mainController, int shelfSelected) {
        this.mainController = mainController;

        ImageView[] resourceArray = new ImageView[]{resource1, resource2, resource3};
        List<String> choiceBoxList = new ArrayList<>();

        List<Shelf> shelves = mainController.getBackEnd().getModel().getPlayer(mainController.getBackEnd().getMyUsername()).getShelves();
        Shelf shelf = shelves.get(shelfSelected);

        for(int i=0; i<shelves.size(); i++){
            if(i<3){
                choiceBoxList.add(String.valueOf(i + 1));
            } else {
                choiceBoxList.add("Leader " + (i - 2));
            }
        }

        for(int i=0; i<shelf.size; i++){
            resourceArray[i].setImage(getResourceImage(shelf.color));
        }

        shelfSelector.setItems(FXCollections.observableList(choiceBoxList));
    }

    public void moveResource() {
        if(shelfSelector.getSelectionModel().getSelectedItem() != null){
             // MOVE RESOURCE MESSAGE
        }
    }
}
