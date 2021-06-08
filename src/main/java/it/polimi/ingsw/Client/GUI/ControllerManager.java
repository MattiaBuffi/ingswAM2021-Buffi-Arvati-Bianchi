package it.polimi.ingsw.Client.GUI;

import it.polimi.ingsw.Client.GUI.FXMLControllers.*;
import it.polimi.ingsw.Client.View.MessageHandler;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Utils.Observable;

public class ControllerManager {
    private static CardsMarketTab cardsMarketTab;
    private static ResourceMarketTab resourceMarketTab;
    private static ProductionTab productionTab;
    private static VaticanRoutePane vaticanRoutePane;
    private static StorageTab storageTab;
    private static ScoreboardTab scoreboardTab;

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

}
