package it.polimi.ingsw.Message;

import it.polimi.ingsw.Message.ClientMessages.*;

public interface ClientEventHandler {



    abstract class Default implements ClientEventHandler{

        public abstract void invalidMessage();

        @Override
        public void handle(ActivateLeaderCard event) {
            invalidMessage();
        }

        @Override
        public void handle(DiscardLeaderCard event) {
            invalidMessage();
        }

        @Override
        public void handle(BuyDevelopmentCard event) {
            invalidMessage();
        }

        @Override
        public void handle(TakeResources event) {
            invalidMessage();
        }

        @Override
        public void handle(EndTurn event) {
            invalidMessage();
        }

        @Override
        public void handle(BasicProduction event) {
            invalidMessage();
        }

        @Override
        public void handle(CardProduction event) {
            invalidMessage();
        }

        @Override
        public void handle(LeaderCardProduction event) {
            invalidMessage();
        }

        @Override
        public void handle(Login event) {
            invalidMessage();
        }

        @Override
        public void handle(GameSize event) {
            invalidMessage();
        }

        @Override
        public void handle(Ping event) {
            invalidMessage();
        }
    }



    void handle(ActivateLeaderCard event);

    void handle(DiscardLeaderCard event);

    void handle(BuyDevelopmentCard event);

    void handle(TakeResources event);

    void handle(EndTurn event);

    void handle(BasicProduction event);

    void handle(CardProduction event);

    void handle(LeaderCardProduction event);


    void handle(Login event);

    void handle(GameSize event);

    void handle(Ping event);

}