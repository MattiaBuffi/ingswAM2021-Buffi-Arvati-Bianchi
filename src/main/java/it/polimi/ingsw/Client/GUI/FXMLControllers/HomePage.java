package it.polimi.ingsw.Client.GUI.FXMLControllers;

import it.polimi.ingsw.Client.App;
import it.polimi.ingsw.Client.GUI.Layout;
import it.polimi.ingsw.Client.ViewBackEnd;

public class HomePage implements Layout {

    private final String NEXT_SCENE[] = new String[]{"connection_page", "..."};
    private ViewBackEnd backEnd;

    @Override
    public void setup(ViewBackEnd backEnd) {
        System.out.println("HomePage");
        this.backEnd = backEnd;
    }

    public void setupOnlineGame(){
        App.setScene(NEXT_SCENE[0]);
    }

    public void setupOfflineGame(){
        App.setScene("game_board");
        backEnd.localGame();
    }

}
