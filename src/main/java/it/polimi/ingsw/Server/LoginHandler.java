package it.polimi.ingsw.Server;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.Model.ErrorUpdate;
import it.polimi.ingsw.Message.Model.UsernameSelected;
import it.polimi.ingsw.Message.ModelEventHandler;

import java.util.*;
import java.util.function.Consumer;

/**
 * Manage the Login Procedure
 */
public class LoginHandler {

    private int maxLength;
    private Set<String> illegalNames;

    private HashSet<String> usernameSet;


    public LoginHandler(int maxLength, Set<String> illegalNames){
        usernameSet = new HashSet<>();
        this.maxLength = maxLength;
        this.illegalNames = illegalNames;
    }

    public LoginHandler(int maxLength){
        usernameSet = new HashSet<>();
        this.maxLength = maxLength;
        this.illegalNames = Collections.emptySet();
    }


    public Set<String> getUsername(){
        return new HashSet<>(usernameSet);
    }

    public void removeUsername(String username){
        usernameSet.remove(username);

    }

    /**
     * Add Player username to the usernameSet
     * Set the Player username
     * Send a UsernameSelected message to the player
     * @param client Instance of client
     * @param username username of the Player
     * @return True if Procedure finish correctly
     */
    public boolean addUsername(Client client, String username){

        username = username.toLowerCase();

        if(!validUsername( client::update, username)){
            return false;
        }

        usernameSet.add(username);
        client.setUsername(username);
        client.send(new UsernameSelected(username));
        return true;

    }

    /**
     * Check if the username selected by the player is Valid
     * @param messageHandler message Handler
     * @param username username of the player
     * @return True if the username is Valid
     */
    public boolean validUsername(Consumer<Message<ModelEventHandler>> messageHandler, String username){

        if(usernameSet.contains(username)){
            messageHandler.accept(new ErrorUpdate("selected username is already taken"));
            return false;
        }

        if(username.length() == 0){
            messageHandler.accept(new ErrorUpdate("username must be at least 1 character long"));
            return false;
        }

        if(username.length() >= maxLength){
            messageHandler.accept(new ErrorUpdate("username can have only "+maxLength+" character" ));
            return false;
        }

        for (String s: illegalNames){
            if (username.equals(s)){
                messageHandler.accept(new ErrorUpdate("selected username is not allowed"));
                return false;
            }
        }

        for (int i = 0; i < maxLength && i<username.length(); i++) {
            if(Character.isAlphabetic(username.charAt(i)) || Character.isDigit(username.charAt(i))){
                continue;
            } else {
                messageHandler.accept(new ErrorUpdate("only alphabetic and digit character are allowed"));
                return false;
            }
        }

        return true;

    }


}
