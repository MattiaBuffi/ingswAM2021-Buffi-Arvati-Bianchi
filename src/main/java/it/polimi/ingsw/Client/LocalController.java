package it.polimi.ingsw.Client;

import it.polimi.ingsw.Controller.GameController;
import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.ModelEventHandler;
import it.polimi.ingsw.Utils.Observable;
import it.polimi.ingsw.Utils.Observer;

public class LocalController extends Controller {

    private GameController controller;


    public LocalController(){
        this.controller = new GameController();
    }


    @Override
    public void disconnect() {

    }

    @Override
    public void update(Message<ClientEventHandler> event) {
        event.accept(controller);
    }


}
