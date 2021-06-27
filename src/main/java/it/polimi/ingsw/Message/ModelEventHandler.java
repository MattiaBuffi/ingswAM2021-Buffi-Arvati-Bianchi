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
        public void handle(MarketResourceTaken event) {
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
        public void handle(ResourceMarketExtra event) {
            invalidMessage();
        }

        @Override
        public void handle(GameSizeRequest event) {
            invalidMessage();
        }

        @Override
        public void handle(UsernameSelected event) {
            invalidMessage();
        }

        @Override
        public void handle(WaitingPlayersUpdate event) {
            invalidMessage();
        }

        @Override
        public void handle(VaticanReport event) {
            invalidMessage();
        }

        @Override
        public void handle(VaticanRoutePosition event) {
            invalidMessage();
        }

        @Override
        public void handle(LeaderCardActivation event) {
            invalidMessage();
        }

        @Override
        public void handle(ActionTokenPlayed event) {
            invalidMessage();
        }

        @Override
        public void handle(ActivePlayer event) {
            invalidMessage();
        }

        @Override
        public void handle(AvailableLeaderCard event) {
            invalidMessage();
        }

        @Override
        public void handle(PlayersSetup event) {
            invalidMessage();
        }

        @Override
        public void handle(ResourceSetup event) {
            invalidMessage();
        }
    }

    void handle(VaticanReport event);

    void handle(VaticanRoutePosition event);

    void handle(ChestUpdate event);

    void handle( DevelopmentCardBuyUpdate event);

    void handle( ErrorUpdate error);

    void handle( MarketResourceAvailable event);

    void handle(MarketResourceTaken event);

    void handle( MarketCardUpdate event);

    void handle( ModelUpdate event);

    void handle( ProductionBufferUpdate event);

    void handle( ResourceMarketUpdate event);

    void handle( ShelfUpdate event);

    void handle(ResourceMarketExtra event);

    void handle(LeaderCardActivation event);

    void handle(GameSizeRequest event);

    void handle(UsernameSelected event);

    void handle(WaitingPlayersUpdate event);

    void handle(ActionTokenPlayed event);

    void handle(ActivePlayer event);

    void handle(AvailableLeaderCard event);

    void handle(PlayersSetup event);

    void handle(ResourceSetup event);


}
