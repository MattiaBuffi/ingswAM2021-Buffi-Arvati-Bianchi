package it.polimi.ingsw.Model;


import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.Model.*;
import it.polimi.ingsw.Message.ModelEventHandler;
import it.polimi.ingsw.Model.ActionTokens.ActionDeck;
import it.polimi.ingsw.Model.CardMarket.CardMarket;
import it.polimi.ingsw.Model.CardMarket.NullCard;
import it.polimi.ingsw.Model.CardMarket.PurchasableCard;
import it.polimi.ingsw.Model.CardStorage.Selection.SelectBasic;
import it.polimi.ingsw.Model.CardStorage.Selection.SelectDevelopmentCard;
import it.polimi.ingsw.Model.CardStorage.Selection.SelectLeader;
import it.polimi.ingsw.Model.LeaderCard.LeaderCard;
import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.States.StateGameEnded;
import it.polimi.ingsw.Model.Player.States.StateWait;
import it.polimi.ingsw.Model.Player.User;
import it.polimi.ingsw.Model.ResourceMarket.ResourceMarket;
import it.polimi.ingsw.Model.VaticanRoute.VaticanRoute;
import it.polimi.ingsw.Model.VaticanRoute.VaticanToken;
import it.polimi.ingsw.Parser.LeaderCardParser.CardParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Game implements GameHandler {


    private gameStrategy strategy;

    private ActionDeck actionDeck;


    private List<Player> players;
    private CardMarket cardMarket;
    private ResourceMarket resourceMarket;
    private VaticanRoute vaticanRoute;

    private int currentPlayer;

    private Broadcaster broadcaster;

    private boolean lastTurn;


    /**
     * Create the Game
     * @param users List of Players
     */
    public<U extends User> Game(List<U> users){

        this.players = new ArrayList<>();
        this.broadcaster = new Broadcaster();
        this.resourceMarket = new ResourceMarket(broadcaster);
        this.cardMarket = new CardMarket(broadcaster);
        this.vaticanRoute = new VaticanRoute(broadcaster,this);


        List<LeaderCard> leaderCards = CardParser.getLeaderCard();
        Collections.shuffle(leaderCards);
        Collections.shuffle(users);


        for (int i = 0; i < users.size(); i++) {

            VaticanToken token = vaticanRoute.addPlayer(users.get(i).getUsername());
            players.add(new Player(users.get(i), i,token, leaderCards.subList((4*i), 4+(4*i)),this, broadcaster));
            broadcaster.notifyAllPlayers(new PlayersSetup(users.get(i).getUsername(), i));

        }


        if(users.size() ==1){
            VaticanToken blackCrossToken = vaticanRoute.addPlayer("cpu");
            this.actionDeck = new ActionDeck(cardMarket, blackCrossToken,this, broadcaster);
            this.strategy = new SinglePlayerStrategy();
        } else {
            this.strategy = new MultiPlayerStrategy();
        }


        broadcaster.sendMessages(null, "Setup");


        for (Player p: players){
            p.notifyUser(new AvailableLeaderCard(p.getLeaderCards()));
            p.notifyAllPlayers(new ChestUpdate(p.getResourceStorage().getResources()));

        }


    }

    /**
     *
     * @param username name of the player
     * @return reference to the Player
     */
    private Player getPlayerByUsername(String username){
        for (Player p: players){
            if(username.equals( p.getUser().getUsername())){
                return p;
            }
        }
        return null;
    }


    /**
     * Send a message to All players when a player buys a card
     * @param username name of the player
     * @param x coordinate x of the card the player wants to buy in the Card Market
     * @param y coordinate x of the card the player wants to buy in the Card Market
     * @param cardPosition position where player wants to store the card in the Production Pane
     */
    public void buyCard(String username, int x, int y, int cardPosition){

        Player player = getPlayerByUsername(username);

        if(player == null){
            return;
        }

        PurchasableCard card = cardMarket.getCard(x ,y );

        if(card == null){
            broadcaster.emptyMessages();
            return;
        }

        if (!player.buyCard(card, cardPosition)){
            broadcaster.emptyMessages();
            return;
        }

        broadcaster.sendMessages(username, "bought Development Card");

    }

    /**
     * Send a message to All players when a player buys Resources
     * @param username name of the Player
     * @param position value of the Resources
     */
    public void buyResources(String username, int position){

        Player player = getPlayerByUsername(username);

        if(player == null){
            return;
        }

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
        broadcaster.sendMessages(username, "bought marbles from market");

    }

    /**
     * Send a message to All players when a player Deposit Resources
     * @param username name of the Player
     * @param color identifies the Marble that the player wants to deposit
     * @param shelfPosition identifies where the player wants to deposit the Marble
     */
    public void storeResource(String username, Marble.Color color, int shelfPosition){

        Player player = getPlayerByUsername(username);

        if(player == null){
            return;
        }


        if(!player.storeResource(color, shelfPosition)){
            broadcaster.emptyMessages();
            return;
        }

        broadcaster.sendMessages(username, "marble stored");

    }

    /**
     * Send a message to All players when a player Move Resources
     * @param username name of the player
     * @param firstShelf Identify the shelf from which the player wants to move resources
     * @param secondShelf Identify the shelf where the player wants to move resources
     */
    public void moveResources(String username, int firstShelf, int secondShelf){

        Player player = getPlayerByUsername(username);

        if(player == null){
            return;
        }

        if(!player.moveResources(firstShelf, secondShelf)){
            broadcaster.emptyMessages();
            return;
        }

        broadcaster.sendMessages(username, "resources moved");

    }

    /**
     * Send a message to All players when a player use a Basic Production
     * @param username name of the player
     * @param input_1 identifies the Marble that the player wants to use
     * @param input_2 identifies the Marble that the player wants to use
     * @param output identifies the Marble that the player wants to receive
     */
    public void basicProduction(String username, Marble.Color input_1,Marble.Color input_2,Marble.Color output){

        Player player = getPlayerByUsername(username);

        if(player == null){
            return;
        }

        if(!player.production(new SelectBasic(input_1, input_2, output))){
            broadcaster.emptyMessages();
            return;
        }

        broadcaster.sendMessages(username, "production");

    }

    /**
     * Send a message to All players when a player use a Production Card
     * @param username name of the player
     * @param cardPosition position of the card in the Production Pane
     */
    public void cardProduction(String username, int cardPosition){

        Player player = getPlayerByUsername(username);

        if(player == null){
            return;
        }

        if(!player.production(new SelectDevelopmentCard(cardPosition))){
            broadcaster.emptyMessages();
            return;
        }

        broadcaster.sendMessages(username, "production");
    }


    /**
     * Send a message to All players when a player use a Leader Production
     * @param username name of the player
     * @param cardId Id of the Leader Card
     * @param outputColor Identifies the Marble that the player wants to receive
     */
    public void leaderProduction(String username, String cardId, Marble.Color outputColor){

        Player player = getPlayerByUsername(username);

        if(player == null){
            return;
        }

        if(!player.production(new SelectLeader(cardId, outputColor))){
            broadcaster.emptyMessages();
            return;
        }

        broadcaster.sendMessages(username, "production");

    }

    /**
     * Send a message to All players when a player activate a Leader Card
     * @param username name of the player
     * @param cardId Id of the Leader Card
     */
    public void activateLeaderCard(String username, String cardId){

        Player player = getPlayerByUsername(username);

        if(player == null){
            return;
        }

        if(!player.activateLeader(cardId)){
            broadcaster.emptyMessages();
            return;
        }

        broadcaster.sendMessages(username, "Leader card activated");

    }

    /**
     * Send a message to All players when a player discard a Leader Card
     * @param username name of the player
     * @param cardId Id of the Leader Card
     */
    public void discardLeaderCard(String username, String cardId){
        //state.discardCard(username, cardId);
        Player player = getPlayerByUsername(username);

        if(player == null){
            return;
        }

        if(!player.discardLeader(cardId)){
            broadcaster.emptyMessages();
            return;
        }

        broadcaster.sendMessages(username, "discarded Leader Card");
    }

    /**
     * Send a message to All players when a player ends his turn
     * @param username name of the player
     */
    public void endTurn(String username){

        Player player = getPlayerByUsername(username);

        if(player == null){
            return;
        }

        if(!player.endTurn()){
            broadcaster.emptyMessages();
            return;
        }
        broadcaster.sendMessages(username, "turn ended");

    }


    /**
     * Send a message to All players when a player disconnect from the game
     * @param username name of the player
     */
    public void disconnect(String username){

        Player disconnectedPlayer = getPlayerByUsername(username);
        disconnectedPlayer.getUser().removeAllObserver();
        players.remove(disconnectedPlayer);

        switch (players.size()){
            case 0:
                terminateGame();
                return;
            case 1:
                broadcaster.notifyAllPlayers(new EndGame(players.get(0).getUser().getUsername()));
                break;
            default:
                broadcaster.notifyAllPlayers(new EndGame(getWinner()));
                break;
        }

        broadcaster.sendMessages(username, "game ended: player disconnected");
        terminateGame();

    }




    @Override
    public void endTurn() {
        strategy.endTurn();
    }



    @Override
    public void startGame() {
        for(Player p: players){
            if (!p.isReady()){
                return;
            }
        }

        currentPlayer = 0;
        players.get(currentPlayer).setActive();

    }



    @Override
    public void endGame() {
        strategy.endGame();
    }




    private int getPlayerFinalPoints(Player player){
        StateGameEnded.setState(player);
        int points = player.getVictoryPoints();
        broadcaster.notifyAllPlayers(new VictoryPointsUpdate(player.getUser().getUsername(), points));
        return points;
    }




    /**
     * @return the Username of the Winner
     */
    private String getWinner(){

        int maxPoint = 0;
        Player winner = players.get(0);

        for (int i = 0; i < players.size(); i++) {

            int playerPoint = getPlayerFinalPoints(players.get(i));


            if( playerPoint > maxPoint){
                maxPoint = playerPoint;
                winner = players.get(i);
            } else if(playerPoint == maxPoint){

                int playerResourceSize = players.get(i).getResourceStorage().getResources().getSize();
                int winnerResourceSize = winner.getResourceStorage().getResources().getSize();

                if(playerResourceSize > winnerResourceSize){
                    maxPoint = playerPoint;
                    winner = players.get(i);;
                } else if(playerResourceSize == winnerResourceSize){
                    if( i < players.indexOf(winner)){
                        maxPoint = playerPoint;
                        winner = players.get(i);
                    }
                }
            }
        }


        return winner.getUser().getUsername();
    }






    /**
     * Terminate the game
     */
    private void terminateGame(){

        for (Player p: players){
            p.getUser().removeAllObserver();
        }

    }


    private interface gameStrategy{

        void endTurn();

        void endGame();

    }


    /**
     * Identifies Single Player Override methods
     */
    private class SinglePlayerStrategy implements  gameStrategy{

        @Override
        public void endTurn() {
            actionDeck.playToken();
            players.get(currentPlayer).setActive();
            broadcaster.notifyAllPlayers(new VictoryPointsUpdate(players.get(currentPlayer).getUser().getUsername(), players.get(currentPlayer).getVictoryPoints()));
        }

        @Override
        public void endGame() {

            for (VaticanToken token: vaticanRoute.getTokenList()){
                if(token.getPosition() == VaticanRoute.LAST_POSITION){
                    players.get(currentPlayer).notifyUser(new EndGame(token.getOwner()));
                    broadcaster.sendMessages(players.get(0).getUser().getUsername(), "Game ended: Reached vatican route ending");
                    terminateGame();

                    return;
                }
            }

            if(players.get(currentPlayer).getCardStorage().getCards().size() == 7){
                players.get(currentPlayer).notifyUser(new EndGame(players.get(currentPlayer).getUser().getUsername()));
                broadcaster.sendMessages(players.get(0).getUser().getUsername(), "Game ended: 7th development card bought");
                terminateGame();

                return;
            }

            for (int i = 0; i < 4; i++) {
                if(cardMarket.getCard(i, 2) == NullCard.get()) {
                    players.get(currentPlayer).notifyUser(new EndGame("cpu"));
                    broadcaster.sendMessages(players.get(0).getUser().getUsername(), "Game ended: market has an empty column");
                    terminateGame();
                }
            }


            players.get(currentPlayer).notifyUser(new EndGame("cpu"));



        }

    }

    /**
     * Identifies MultiPlayer Override methods
     */
    private class MultiPlayerStrategy implements  gameStrategy{

        /**
         *  Set next Player as active
         */
        private void setNextPlayer(){
            currentPlayer = (currentPlayer+1)%players.size();
            players.get(currentPlayer).setActive();
        }


        @Override
        public void endTurn() {

            setNextPlayer();


            if(lastTurn){
                if(currentPlayer == 0){

                    String winner = getWinner();

                    broadcaster.notifyAllPlayers(new EndGame(winner));
                    broadcaster.sendMessages(players.get(0).getUser().getUsername(), "game is ended");
                    terminateGame();
                }
            } else {
                for (Player p: players){
                    broadcaster.notifyAllPlayers(new VictoryPointsUpdate(p.getUser().getUsername(), p.getVictoryPoints()));
                }
            }


        }

        @Override
        public void endGame() {
            lastTurn = true;
            //endTurn();
        }

    }


    /**
     * Identifies the Message Broadcaster
     */
    private class Broadcaster implements EventBroadcaster{

        private List<Message<ModelEventHandler>> messages;

        public Broadcaster(){
            messages = new ArrayList<>();
        }


        public void sendMessages(String username, String message){

            for (Player p: players){
                p.notifyUser(new ModelUpdate(username, message, new ArrayList<>(messages)));
            }
            messages.clear();
        }

        public void emptyMessages(){
            messages.clear();
        }


        @Override
        public void notifyAllPlayers(Message<ModelEventHandler> event) {
            messages.add(event);
        }

        @Override
        public void notifyUser(Message<ModelEventHandler> event) {
            players.get(currentPlayer).getUser().notify(event);
        }

    }
}

