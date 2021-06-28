package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.ClientMessages.*;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Utils.Observer;

public class GameController implements Observer<Message<ClientEventHandler>>, ClientEventHandler {

    private Game game;
    private String username;


    public GameController(Game game, String username) {
        this.game = game;
        this.username = username;
    }


    @Override
    public void handle(ActivateLeaderCard event) {
        game.activateLeaderCard(username, event.getCardId());
    }

    @Override
    public void handle(DiscardLeaderCard event) {
        game.discardLeaderCard(username, event.getCardId());
    }

    @Override
    public void handle(BuyDevelopmentCard event) {
        game.buyCard(username, event.getX(), event.getX(), event.getProductionColumn());
    }

    @Override
    public void handle(TakeResources event) {
        game.buyResources(username, event.getSelection());
    }

    @Override
    public void handle(BasicProduction event) {
        game.basicProduction(username, event.getFirstInput(), event.getSecondInput(), event.getOutput());
    }

    @Override
    public void handle(CardProduction event) {
        game.cardProduction(username, event.getCardPosition());
    }

    @Override
    public void handle(LeaderCardProduction event) {
        game.leaderProduction(username, event.getCardId(), event.getOut());
    }

    @Override
    public void handle(MoveResources event) {
        game.moveResources(username, event.getStartPos(), event.getEndPos());
    }

    @Override
    public void handle(DepositResource event) {
        game.storeResource(username, event.getColor(), event.getShelf());
    }



    @Override
    public void handle(EndTurn event) {
        game.endTurn(username);
    }

    @Override
    public void handle(Login event) {

    }

    @Override
    public void handle(GameSize event) {

    }

    @Override
    public void handle(Ping event) {

    }



    @Override
    public void update(Message<ClientEventHandler> event) {
        event.accept(this);
    }



}
