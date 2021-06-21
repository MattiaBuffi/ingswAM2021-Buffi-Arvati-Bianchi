package it.polimi.ingsw.Server;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.Model.ErrorUpdate;
import it.polimi.ingsw.Message.Model.UsernameSelected;
import it.polimi.ingsw.Message.ModelEventHandler;

import java.util.*;
import java.util.function.Consumer;

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

    public void addUsername(Client client, String username){
        if(validUsername( client::update, username)){
            usernameSet.add(username);
            client.setUsername(username);
            client.send(new UsernameSelected(username));
        }
    }


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
