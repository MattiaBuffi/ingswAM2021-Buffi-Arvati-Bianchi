package it.polimi.ingsw.ServerModel;

import it.polimi.ingsw.Model.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Lobby {


    private ServerModel server;
    private List<User> users;
    private int size;
    //private Executor executor;


    public Lobby(ServerModel server){
        this.server = server;
        users = new ArrayList<>();
        size = 0;
       // executor = Executors.newSingleThreadExecutor(); //for timeout
    }




    private void gameStart(){

        if( this.size > users.size() ){
            notifyAllUser(users, "Waiting for players. Currently connected: "+ size);
            return;
        }

        List<User> players = users.subList(0, size-1);
        server.addGame(new Game(players));
        users = new ArrayList<>(users.subList(size, users.size()));
        users.get(0).notify("set game size");

    }



    public void join(User user){

        if(users.size() == 0){
            users.add(user);
            user.notify("set game size");
            //start timer
            return;
        } else {
            users.add(user);
            gameStart();
        }

    }


    public void setGameSize(User user, int size){

        if(user != users.get(0)){
            user.notify("illegal action");
            return;
        }

        if(this.size == 0 ){
            user.notify("game size already set");
            return;
        }

        if( size>4 || size<1 ){
            user.notify("illegal size");
            return;
        }

        this.size = size;

    }


    private void notifyAllUser(List<User> userList, Object message){
        for(User u: userList){
            u.notify(message);
        }
    }




}
