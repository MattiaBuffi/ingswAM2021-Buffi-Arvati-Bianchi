package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Message.ClientMessages.*;
import it.polimi.ingsw.Message.SingleClientEventHandler;
import it.polimi.ingsw.Utils.Observer;

public class GameController implements Observer, SingleClientEventHandler {

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

    @Override
    public void update(Object event) {

    }

}
