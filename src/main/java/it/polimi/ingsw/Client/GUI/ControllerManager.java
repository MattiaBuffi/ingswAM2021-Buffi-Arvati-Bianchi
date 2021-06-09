package it.polimi.ingsw.Client.GUI;

import it.polimi.ingsw.Client.App;
import it.polimi.ingsw.Client.GUI.FXMLControllers.*;
import it.polimi.ingsw.Client.View.MessageHandler;
import it.polimi.ingsw.Client.View.ModelData.ViewModel;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Utils.Observable;

import java.util.List;

public class ControllerManager {
    private static CardsMarketTab cardsMarketTab;
    private static ResourceMarketTab resourceMarketTab;
    private static ProductionTab productionTab;
    private static VaticanRoutePane vaticanRoutePane;
    private static StorageTab storageTab;
    private static ScoreboardTab scoreboardTab;
    private static ViewModel model;

    public static void linkModel(ViewModel model) {
        ControllerManager.model = model;
    }

    public static void addController(CardsMarketTab cardsMarketTab){
        ControllerManager.cardsMarketTab = cardsMarketTab;
    }

    public static void addController(ResourceMarketTab resourceMarketTab) {
        ControllerManager.resourceMarketTab = resourceMarketTab;
    }

    public static void addController(ProductionTab productionTab) {
        ControllerManager.productionTab = productionTab;
    }

    public static void addController(VaticanRoutePane vaticanRoutePane) {
        ControllerManager.vaticanRoutePane = vaticanRoutePane;
    }

    public static void addController(StorageTab storageTab) {
        ControllerManager.storageTab = storageTab;
    }

    public static void addController(ScoreboardTab scoreboardTab) {
        ControllerManager.scoreboardTab = scoreboardTab;
    }

    public static void updateResourceMarket(List<Marble.Color> colors, int position) {
        //ResourceMarketTab.SOMETHING
    }

    public static ViewModel getModel(){
        return model;
    }

    public static void showError(String error){

    }

    public static void updateShelves(){
        storageTab.updateShelves();
    }

    public static void updateCardMarket(int x, int y, int id){
        cardsMarketTab.updateCard(x, y, id);
    }

    public static void updateChest() {
        storageTab.updateChest();
    }
}
