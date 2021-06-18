package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Message.ClientMessage.GameSize;
import it.polimi.ingsw.Message.ClientMessage.Login;



public class ServerController extends GameController {



    public void handle(Login event) {

    }

    public void handle(GameSize event) {

    }

    public void update(Object event) {
        System.out.println("-Received: " + event);
    }



}
