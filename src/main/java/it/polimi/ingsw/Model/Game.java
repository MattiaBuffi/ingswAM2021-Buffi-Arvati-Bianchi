package it.polimi.ingsw.Model;


import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.Model.ModelUpdate;
import it.polimi.ingsw.Message.ModelEventHandler;
import it.polimi.ingsw.Model.ActionTokens.ActionDeck;
import it.polimi.ingsw.Model.CardMarket.CardMarket;
import it.polimi.ingsw.Model.CardMarket.PurchasableCard;
import it.polimi.ingsw.Model.CardStorage.Selection.SelectBasic;
import it.polimi.ingsw.Model.CardStorage.Selection.SelectDevelopmentCard;
import it.polimi.ingsw.Model.CardStorage.Selection.SelectLeader;
import it.polimi.ingsw.Model.LeaderCard.LeaderCard;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.User;
import it.polimi.ingsw.Model.ResourceMarket.ResourceMarket;
import it.polimi.ingsw.Model.VaticanRoute.VaticanRoute;
import it.polimi.ingsw.Model.VaticanRoute.VaticanToken;
import it.polimi.ingsw.Parser.LeaderCardParser.CardParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game implements TurnHandler, GameTerminator{

    private gameState state;
    private gameStrategy strategy;

    private ActionDeck actionDeck;


    private List<Player> players;
    private CardMarket cardMarket;
    private ResourceMarket resourceMarket;
    private VaticanRoute vaticanRoute;

    private int currentPlayer;

    private Broadcaster broadcaster;




    public<U extends User> Game(List<U> users){

        this.players = new ArrayList<>();
        this.broadcaster = new Broadcaster();

        this.resourceMarket = new ResourceMarket(broadcaster);
        this.cardMarket = new CardMarket(broadcaster);
        this.vaticanRoute = new VaticanRoute(broadcaster,this);

        List<LeaderCard> leaderCards = CardParser.getLeaderCards();
        Collections.shuffle(leaderCards);


        for (int i = 0; i < users.size(); i++) {
            VaticanToken token = new VaticanToken(vaticanRoute, users.get(i).getUsername());
            vaticanRoute.addPlayer(token);
            players.add(new Player(users.get(i), token, leaderCards.subList((4*i), 4+(4*i)), this, this));
        }


        if(users.size() ==1){
            VaticanToken blackCrossToken = new VaticanToken(vaticanRoute, "cpu");
            this.actionDeck = new ActionDeck(cardMarket, blackCrossToken,this);
        }

        this.state = new SetupState(users.size());

    }


    private Player getPlayerByUsername(String username){
        for (Player p: players){
            if(username.equals( p.getUser().getUsername())){
                return p;
            }
        }
        return null;
    }


    public void buyCard(String username, int x, int y, int cardPosition){

        Player player = getPlayerByUsername(username);

        PurchasableCard card = cardMarket.getCard(x ,y );

        if(card == null){
            broadcaster.emptyMessages();
            return;
        }

        if (!player.buyCard(card, cardPosition)){
            broadcaster.emptyMessages();
            return;
        }

        broadcaster.sendMessages();

    }

    public void buyResources(String username, int position){

        Player player = getPlayerByUsername(username);

        List<Marble> marbles = resourceMarket.get(position);

        if(marbles.isEmpty()){
            broadcaster.emptyMessages();
            return;
        }

        if(!player.buyResources(marbles)){
            broadcaster.emptyMessages();
            return;
        }

        resourceMarket.insertExtra(position);
        broadcaster.sendMessages();

    }

    public void storeResource(String username, Marble.Color color, int shelfPosition){

        Player player = getPlayerByUsername(username);

        if(!player.storeResource(color, shelfPosition)){
            broadcaster.emptyMessages();
            return;
        }

        broadcaster.sendMessages();

    }

    public void basicProduction(String username, Marble.Color input_1,Marble.Color input_2,Marble.Color output){

        Player player = getPlayerByUsername(username);

        if(!player.production(new SelectBasic(input_1, input_2, output))){
            broadcaster.emptyMessages();
            return;
        }

        broadcaster.sendMessages();

    }

    public void cardProduction(String username, int cardPosition){

        Player player = getPlayerByUsername(username);

        if(!player.production(new SelectDevelopmentCard(cardPosition))){
            broadcaster.emptyMessages();
            return;
        }

        broadcaster.sendMessages();
    }


    public void leaderProduction(String username, String cardId, Marble.Color outputColor){

        Player player = getPlayerByUsername(username);

        if(!player.production(new SelectLeader(cardId, outputColor))){
            broadcaster.emptyMessages();
            return;
        }

        broadcaster.sendMessages();

    }


    public void moveResources(String username, int firstShelf, int secondShelf){

        Player player = getPlayerByUsername(username);

        if(!player.moveResources(firstShelf, secondShelf)){
            broadcaster.emptyMessages();
            return;
        }

        broadcaster.sendMessages();

    }


    public void activateLeaderCard(String username, String cardId){

        Player player = getPlayerByUsername(username);

        if(!player.activateLeader(cardId)){
            broadcaster.emptyMessages();
            return;
        }

        broadcaster.sendMessages();

    }


    public void discardLeaderCard(String username, String cardId){
        state.discardCard(username, cardId);
    }



    @Override
    public void endTurn() {
        strategy.endTurn();
    }



    @Override
    public void endGame() {
        strategy.endGame();
    }




    private interface gameState {
        void discardCard(String username, String cardId);

    }

    private class SetupState implements gameState{

        private int missing;

        public SetupState(int numPlayer) {
            missing = 2*numPlayer;
        }

        @Override
        public void discardCard(String username, String cardId) {

            Player player = getPlayerByUsername(username);

            if(! player.discardLeader(cardId)){
                broadcaster.emptyMessages();
                return;
            }

            missing-= 1;
            if(missing== 0){
                state = new DefaultState();
            }
            broadcaster.sendMessages();
        }
    }


    private class DefaultState implements gameState{

        @Override
        public void discardCard(String username, String cardId) {

            Player player = getPlayerByUsername(username);
            if(! player.discardLeader(cardId)){
                broadcaster.emptyMessages();
                return;
            }
            broadcaster.sendMessages();
        }

    }



    private interface gameStrategy{

        void endTurn();

        void endGame();

    }

    private class SinglePlayerStrategy implements  gameStrategy{

        @Override
        public void endTurn() {

        }

        @Override
        public void endGame() {

        }
    }

    private class MultiPlayerStrategy implements  gameStrategy{

        @Override
        public void endTurn() {
            currentPlayer = (currentPlayer+1)%players.size();
            players.get(currentPlayer).setActive();
            broadcaster.sendMessages();
        }

        @Override
        public void endGame() {

        }
    }




    private class Broadcaster implements EventBroadcaster{

        private List<Message<ModelEventHandler>> messages;

        public Broadcaster(){
            messages = new ArrayList<>();
        }


        public void sendMessages(){
            for (Player p: players){
                p.notifyUser(new ModelUpdate(messages));
            }
            messages.clear();
        }

        public void emptyMessages(){
            messages.clear();
        }


        @Override
        public void notifyAllPlayers(Message event) {
            //messages.add(event);
        }

        @Override
        public void notifyUser(Message event) {
            //currentPlayer.getUser().notify(event);
        }

    }





}

