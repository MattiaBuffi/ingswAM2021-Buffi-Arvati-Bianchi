package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Message.MultipleClientEventHandler;
import it.polimi.ingsw.Message.ClientMessages.GameSize;
import it.polimi.ingsw.Message.ClientMessages.Login;
import it.polimi.ingsw.ServerModel.ServerModel;

public class ServerController extends GameController implements MultipleClientEventHandler {

    private ServerModel model;

    @Override
    public void handle(Login event) {
        System.out.println(event.getUsername());
    }

    @Override
    public void handle(GameSize event) {

    }

    @Override
    public void update(Object event) {
        System.out.println("-Received: " + event);
    }


}
