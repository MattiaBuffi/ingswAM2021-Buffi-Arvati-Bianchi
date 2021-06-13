package it.polimi.ingsw.Client.GUI.FXMLControllers;

import it.polimi.ingsw.Client.App;
import it.polimi.ingsw.Client.GUI.ControllerManager;

import it.polimi.ingsw.Client.View.ModelData.ReducedDataModel.Shelf;
import it.polimi.ingsw.Controller.ClientMessageController;
import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.ResourceList;
import it.polimi.ingsw.Utils.Observable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class StorageTab extends Observable<Message<ClientEventHandler>> implements Initializable {
    @FXML
    ImageView res1_1, res2_1, res2_2, res3_1, res3_2, res3_3, resLeader1, resLeader2;
    @FXML
    Label yellowValue, purpleValue, blueValue, greyValue;
    @FXML
    Pane leaderStorageContainer;
    private ImageView[] shelf_1;
    private ImageView[] shelf_2;
    private ImageView[] shelf_3;

    public void updateShelves() {
        List<Shelf> shelves = ControllerManager.getModel().getPlayer(ControllerManager.username).getShelves();
        for(Shelf s: shelves){
            if(s.getMaxSize() == 1){
                updateSingleShelf(shelf_1, s);
            } else if(s.getMaxSize() == 2){
                updateSingleShelf(shelf_2, s);
            } else {
                updateSingleShelf(shelf_3, s);
            }
        }
    }

    public void updateChest() {
        ResourceList resourceList = ControllerManager.getModel().getPlayer(ControllerManager.username).getChest();
        Marble.Color[] colors = new Marble.Color[]{Marble.Color.YELLOW, Marble.Color.PURPLE, Marble.Color.BLUE, Marble.Color.GREY};

        for(Marble.Color color: colors){
            switch (color){
                case YELLOW:
                    yellowValue.setText(String.valueOf(resourceList.getSize(color)));
                    break;
                case PURPLE:
                    purpleValue.setText(String.valueOf(resourceList.getSize(color)));
                    break;
                case BLUE:
                    blueValue.setText(String.valueOf(resourceList.getSize(color)));
                    break;
                case GREY:
                    greyValue.setText(String.valueOf(resourceList.getSize(color)));
                    break;
            }
        }
    }

    private void updateSingleShelf(ImageView[] ivShelf, Shelf shelf){
        for(int i=0; i<shelf.getMaxSize(); i++){
            if(i<shelf.getSize()) {
                ivShelf[i].setImage(getResourceImage(shelf.getColor()));
            } else {
                ivShelf[i].imageProperty().set(null); //Not sure!!
            }
        }
    }

    private void changeResourceValue(Marble.Color color, int value){
        switch (color){
            case YELLOW:
                yellowValue.setText(String.valueOf(value));
                break;
            case PURPLE:
                purpleValue.setText(String.valueOf(value));
                break;
            case BLUE:
                blueValue.setText(String.valueOf(value));
                break;
            case GREY:
                greyValue.setText(String.valueOf(value));
                break;
        }
    }

    private Image getResourceImage(Marble.Color color){
        Image resource = null;
        switch (color){
            case YELLOW:
                resource = new Image(App.class.getResourceAsStream("images/token/coin.png"));
                break;
            case PURPLE:
                resource = new Image(App.class.getResourceAsStream("images/token/servant.png"));
                break;
            case BLUE:
                resource = new Image(App.class.getResourceAsStream("images/token/shield.png"));
                break;
            case GREY:
                resource = new Image(App.class.getResourceAsStream("images/token/stone.png"));
                break;
        }
        return resource;
    }

    private void resetValue(){
        yellowValue.setText("0");
        purpleValue.setText("0");
        blueValue.setText("0");
        greyValue.setText("0");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        leaderStorageContainer.setVisible(false);
        resetValue();
        ControllerManager.addController(this);
        addObserver(new ClientMessageController());

        shelf_1 = new ImageView[]{res1_1};
        shelf_2 = new ImageView[]{res2_1, res2_2};
        shelf_3 = new ImageView[]{res3_1, res3_2, res3_3};

    }
}
