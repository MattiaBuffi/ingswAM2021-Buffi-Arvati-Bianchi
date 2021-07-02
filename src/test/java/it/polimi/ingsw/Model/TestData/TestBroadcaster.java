package it.polimi.ingsw.Model.TestData;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Model.EventBroadcaster;

import java.util.ArrayList;
import java.util.List;

public class TestBroadcaster implements EventBroadcaster {


    public List<Message> messages;

    public TestBroadcaster(){
        messages = new ArrayList<>();
    }

    @Override
    public void notifyAllPlayers(Message event) {
        messages.add(event);
    }

    @Override
    public void notifyUser(Message event) {
        messages.add(event);
    }

}