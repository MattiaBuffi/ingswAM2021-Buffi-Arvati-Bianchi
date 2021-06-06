package it.polimi.ingsw.Message;

import it.polimi.ingsw.Message.Model.*;

public interface ModelEventHandler {

    void handle(ChestUpdate event);

    void handle( DevelopmentCardBuyUpdate event);

    void handle( ErrorUpdate error);

    void handle( MarketResourceAvailable event);

    void handle(MarketResourceTaken marketResourceTaken);

    void handle( MarketCardUpdate event);

    void handle( ModelUpdate event);

    void handle( ProductionBufferUpdate event);

    void handle( ResourceMarketUpdate event);

    void handle( ShelfUpdate event);

    void handle(ResourceMarketExtra resourceMarketExtra);

}
