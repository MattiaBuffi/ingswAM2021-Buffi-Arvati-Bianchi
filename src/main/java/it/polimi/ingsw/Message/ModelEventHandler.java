package it.polimi.ingsw.Message;

import it.polimi.ingsw.Message.Model.*;

public interface ModelEventHandler {

    abstract class Default implements ModelEventHandler{

        public abstract void invalidMessage();


        @Override
        public void handle(ChestUpdate event) {
            invalidMessage();
        }

        @Override
        public void handle(DevelopmentCardBuyUpdate event) {
            invalidMessage();
        }

        @Override
        public void handle(ErrorUpdate error) {
            invalidMessage();
        }

        @Override
        public void handle(MarketResourceAvailable event) {
            invalidMessage();
        }

        @Override
        public void handle(MarketResourceTaken marketResourceTaken) {
            invalidMessage();
        }

        @Override
        public void handle(MarketCardUpdate event) {
            invalidMessage();
        }

        @Override
        public void handle(ModelUpdate event) {
            invalidMessage();
        }

        @Override
        public void handle(ProductionBufferUpdate event) {
            invalidMessage();
        }

        @Override
        public void handle(ResourceMarketUpdate event) {
            invalidMessage();
        }

        @Override
        public void handle(ShelfUpdate event) {
            invalidMessage();
        }

        @Override
        public void handle(ResourceMarketExtra resourceMarketExtra) {
            invalidMessage();
        }
    }




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
