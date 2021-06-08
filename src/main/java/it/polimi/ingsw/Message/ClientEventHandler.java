package it.polimi.ingsw.Message;

import it.polimi.ingsw.Message.ClientMessages.*;

public interface ClientEventHandler {

    void handle(Login event);

    void handle(GameSize event);

    void handle(ActivateLeaderCard event);

    void handle(DiscardLeaderCard event);

    void handle(BuyDevelopmentCard event);

    void handle(TakeResources event);

    void handle(EndTurn event);

    void handle(BasicProduction event);

    void handle(CardProduction event);

    void handle(LeaderCardProduction event);
}
