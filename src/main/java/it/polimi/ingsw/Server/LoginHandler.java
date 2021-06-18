package it.polimi.ingsw.Server;

import java.util.HashSet;

public class LoginHandler {

    private static int MAX_USERNAME_LENGTH = 12;
    private static String[] ILLEGAL_NAMES = {"CPU" , "cpu"};

    private HashSet<String> usernameSet;

    public LoginHandler(){
        usernameSet = new HashSet<>();
    }

    public void addUsername(String username){
        usernameSet.add(username);
    }

    public boolean usernameIsTaken(String username){
        return usernameSet.contains(username);
    }

    public boolean validUsername(String username){

        if(username.length() == 0){
            return false;
        }

        for (String s: ILLEGAL_NAMES){
            if (username.equals(s)){
                return false;
            }
        }

        for (int i = 0; i < MAX_USERNAME_LENGTH; i++) {
            if(Character.isAlphabetic(username.charAt(i))){
                continue;
            } else {
                return false;
            }
        }

        return true;

    }


}
