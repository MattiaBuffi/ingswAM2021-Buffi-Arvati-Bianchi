package it.polimi.ingsw.Model;

public interface GameHandler {

    /**
     * Check if the condition for starting the game and, if they are satisfied, start the game.
     */
    void startGame();

    /**
     * Switch the active player
     */
    void endTurn();

    /**
     * Start the end game procedure
     */
    void endGame();

}
