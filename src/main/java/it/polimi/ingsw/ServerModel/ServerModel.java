package it.polimi.ingsw.ServerModel;


import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player.User;
import it.polimi.ingsw.Utils.Observer;

import java.util.*;


public class ServerModel {

    private HashMap<String, ServerUser> users;
    private HashSet<String> usernameSet;

    private Lobby lobby;
    private List<Game> games;


    public ServerModel(){
        this.users = new HashMap<>();
        this.usernameSet = new HashSet<>();
        this.lobby = new Lobby(this);
        this.games = new ArrayList<>();
    }



    public void connect(Observer observer){
        ServerUser user = new ServerUser(UUID.randomUUID().toString());
        user.addObserver(observer);
        users.put(user.getUserId(), user);
        lobby.join(user);
        user.notify("user id:" + user.getUserId());
    }


    public void setUsername(String userId, String username){
        ServerUser user = users.get(userId);


        if(user == null){
            //error
        }

        if(user.getUsername() != null){
            user.notify("username already set");
        }

        if(usernameSet.contains(username)){
            user.notify("username already taken");
        }

        user.setUsername(username);
        user.notify("username set:"+ username);

    }


    public void setGameSize(String userId, int size){
        ServerUser user = users.get(userId);

        if(user == null){
            //error
        }

        lobby.setGameSize(user, size);

    }


    protected void addGame(Game game){
        games.add(game);
    }


}

