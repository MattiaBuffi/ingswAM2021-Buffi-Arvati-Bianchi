package it.polimi.ingsw.Client.GUI.FXMLControllers.Game;

import it.polimi.ingsw.Client.App;

import it.polimi.ingsw.Client.GUI.FXMLControllers.PopUp.PopUpManager;
import it.polimi.ingsw.Client.GUI.Layout;
import it.polimi.ingsw.Client.ViewBackEnd;
import it.polimi.ingsw.Client.ModelData.ReducedDataModel.Shelf;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.ResourceList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.List;


public class StorageTab implements Layout, GameTab {
    @FXML
    ImageView res1_1, res2_1, res2_2, res3_1, res3_2, res3_3, resLeader1_1, resLeader1_2, resLeader2_1, resLeader2_2,
            leaderStorage1, leaderStorage2;
    @FXML
    Label yellowValue, purpleValue, blueValue, greyValue;
    @FXML
    Rectangle shelfSel1, shelfSel2, shelfSel3, shelfSel4, shelfSel5;

    private Rectangle[] rectArray;
    private ImageView[] shelf_1;
    private ImageView[] shelf_2;
    private ImageView[] shelf_3;
    private ImageView[] shelf_4;
    private ImageView[] shelf_5;
    private int selection = 0;

    private ViewBackEnd backEnd;

    @Override
    public void setup(ViewBackEnd backEnd) {
        System.out.println("StorageTab");
        this.backEnd = backEnd;

        resetValue();

        shelf_1 = new ImageView[]{res1_1};
        shelf_2 = new ImageView[]{res2_1, res2_2};
        shelf_3 = new ImageView[]{res3_1, res3_2, res3_3};
        shelf_4 = new ImageView[]{resLeader1_1, resLeader1_2};
        shelf_5 = new ImageView[]{resLeader2_1, resLeader2_2};

        rectArray = new Rectangle[]{shelfSel1, shelfSel2, shelfSel3, shelfSel4, shelfSel5};
    }

    @Override
    public void update() {
        manageResourceBuffer();
        updateShelves();
        updateChest();
    }

    public ViewBackEnd getBackEnd(){
        return backEnd;
    }


    public void manageResourceBuffer() {
        System.out.println("BUFFER: " + backEnd.getModel().resourceMarketBuffer.size());

        List<Marble> marbles = backEnd.getModel().resourceMarketBuffer;
        System.out.println("marbles: " + marbles);

        if(marbles.size() != 0) {
            try {
                PopUpManager.showDepositResourcePopUp(marbles.get(0), this, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public void updateShelves() throws NullPointerException {
        List<Shelf> shelves = backEnd.getModel().getPlayer(backEnd.getMyUsername()).getShelves();

        for(int i = 0; i<shelves.size(); i++){
            switch (i){
                case 0:
                    updateSingleShelf(shelf_1, shelves.get(i));
                    break;
                case 1:
                    updateSingleShelf(shelf_2, shelves.get(i));
                    break;
                case 2:
                    updateSingleShelf(shelf_3, shelves.get(i));
                    break;
                case 3:
                    updateSingleShelf(shelf_4, shelves.get(i));
                    break;
                case 4:
                    updateSingleShelf(shelf_5, shelves.get(i));
                    break;
            }
        }
    }

    public void updateChest() throws NullPointerException{
        ResourceList resourceList = backEnd.getModel().getPlayer(backEnd.getMyUsername()).getChest();
        Marble.Color[] colors = new Marble.Color[]{Marble.Color.YELLOW, Marble.Color.PURPLE, Marble.Color.BLUE, Marble.Color.GREY};

        for(Marble.Color color: colors){
            changeResourceValue(color, resourceList.getSize(color));
        }
    }



    private void updateSingleShelf(ImageView[] ivShelf, Shelf shelf){
        for(int i=0; i<shelf.maxSize; i++){
            if(i<shelf.size) {
                ivShelf[i].setImage(getResourceImage(shelf.color));
            } else {
                ivShelf[i].imageProperty().set(null); //Not sure!!
            }
        }
    }



    public void moveResources() throws IOException {
        PopUpManager.showMoveResourcePopUp(selection, this);
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

    public void showLeaderPower(String id){
        if(!leaderStorage1.isVisible()){
            leaderStorage1.setVisible(true);
            leaderStorage1.setImage(getLeaderPowerImage(id));
        }
    }

    private void resetValue(){
        yellowValue.setText("0");
        purpleValue.setText("0");
        blueValue.setText("0");
        greyValue.setText("0");
    }

    public void res11selected() {
        showSelection(shelfSel1, 0);
    }

    public void res21selected() {
        showSelection(shelfSel2, 1);
    }

    public void res22selected() {
        showSelection(shelfSel2, 1);
    }

    public void res31selected() {
        showSelection(shelfSel3, 2);
    }

    public void res32selected() {
        showSelection(shelfSel3, 2);
    }

    public void res33selected() {
        showSelection(shelfSel3, 2);
    }

    public void resLeader1selected() {
        showSelection(shelfSel4, 3);
    }

    public void resLeader2selected() {
        showSelection(shelfSel5, 4);
    }

    private void showSelection(Rectangle rectangle, int shelf){
        rectArray[selection].setVisible(false);
        rectangle.setVisible(!rectangle.isVisible());
        selection = shelf;
    }


    private Image getLeaderPowerImage(String id) {
        Image image = null;
        switch (id){
            case "5":
                image = new Image(App.class.getResourceAsStream("images/leaderPowers/leaderStorageGREY.png"));
                break;
            case "6":
                image = new Image(App.class.getResourceAsStream("images/leaderPowers/leaderStoragePURPLE.png"));
                break;
            case "7":
                image = new Image(App.class.getResourceAsStream("images/leaderPowers/leaderStorageBLUE.png"));
                break;
            case "8":
                image = new Image(App.class.getResourceAsStream("images/leaderPowers/leaderStorageYELLOW.png"));
                break;
        }
        return image;
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



}
