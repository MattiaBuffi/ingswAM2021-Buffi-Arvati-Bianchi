package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Message.MultipleClientEventHandler;
import it.polimi.ingsw.Message.ClientMessage.GameSize;
import it.polimi.ingsw.Message.ClientMessage.Login;
import it.polimi.ingsw.ServerModel.ServerModel;



public class ServerController extends GameController implements MultipleClientEventHandler {

    private ServerModel model;


    @Override
    public void handle(Login event) {

    }

    @Override
    public void handle(GameSize event) {

    }

    @Override
    public void update(Object event) {
        System.out.println("-Received: " + event);
    }



}
