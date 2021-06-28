package it.polimi.ingsw.Client.GUI.FXMLControllers.PopUp;

import it.polimi.ingsw.Client.GUI.FXMLControllers.Game.StorageTab;
import it.polimi.ingsw.Model.Marble.MarbleFactory;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;

public class StartingResourcesPopUp extends ResourceViewer{

    @FXML
    ChoiceBox<String> resSelector1, resSelector2;

    private int resourceNumber;
    private StorageTab mainController;

    public void chooseResources() throws IOException {
        if(resourceNumber == 1) {
            if (resSelector1.getSelectionModel().getSelectedItem() != null) {
                PopUpManager.showDepositResourcePopUp(MarbleFactory.getMarble(getColor(resSelector1.getSelectionModel().getSelectedItem())), mainController, false);
            }
        } else {
            if (resSelector1.getSelectionModel().getSelectedItem() != null && resSelector2.getSelectionModel().getSelectedItem() != null) {
                PopUpManager.showDepositResourcePopUp(MarbleFactory.getMarble(getColor(resSelector1.getSelectionModel().getSelectedItem())), mainController, false);
                PopUpManager.showDepositResourcePopUp(MarbleFactory.getMarble(getColor(resSelector2.getSelectionModel().getSelectedItem())), mainController, false);
            }
        }
        Stage stage = (Stage) resSelector1.getScene().getWindow();
        stage.close();
    }

    public void initData(StorageTab mainController, int resourceNumber) {
        this.mainController = mainController;
        this.resourceNumber = resourceNumber;
        if(resourceNumber == 2){
            resSelector2.setVisible(true);
        }
    }
}
