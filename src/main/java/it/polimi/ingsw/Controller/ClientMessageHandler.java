package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.ClientMessages.*;

public class ClientMessageHandler implements ClientEventHandler {

    @Override
    public void handle(Login event) {
        System.out.println(event.getUsername());
    }

    @Override
    public void handle(GameSize event) {

    }

    @Override
    public void handle(ActivateLeaderCard event) {

    }

    @Override
    public void handle(DiscardLeaderCard event) {

    }

    @Override
    public void handle(BuyDevelopmentCard event) {
        System.out.println("Take card in position: " + event.getX() + "-" + event.getY());
    }

    @Override
    public void handle(TakeResources event) {
        System.out.println("Take resources from selection: " + event.getSelection());
    }

    @Override
    public void handle(EndTurn event) {

    }

    @Override
    public void handle(BasicProduction event) {

    }

    @Override
    public void handle(CardProduction event) {

    }

    @Override
    public void handle(LeaderCardProduction event) {

    }
}
