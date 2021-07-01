package it.polimi.ingsw.Client;

import it.polimi.ingsw.Controller.GameController;
import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.ModelEventHandler;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player.User;
import it.polimi.ingsw.Utils.Observable;
import it.polimi.ingsw.Utils.Observer;

import java.util.ArrayList;
import java.util.List;

public class LocalController extends Controller {

    private GameController controller;


    public LocalController(String username, Observer<Message<ModelEventHandler >> modelObserver){

        User user = new User(username);
        user.addObserver(modelObserver);

        List<User> userList= new ArrayList<>();
        userList.add(user);

        this.controller = new GameController(new Game(userList),username );
    }


    @Override
    public void disconnect() {
        //endGame
    }

    @Override
    public void update(Message<ClientEventHandler> event) {
        event.accept(controller);
    }




}
