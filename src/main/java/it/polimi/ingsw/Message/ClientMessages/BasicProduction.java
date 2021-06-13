package it.polimi.ingsw.Message.ClientMessages;

import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Model.Marble.Marble;

public class BasicProduction implements Message<ClientEventHandler> {


    private Marble.Color[] production;

    public BasicProduction(Marble.Color in1, Marble.Color in2, Marble.Color out){
        production = new Marble.Color[]{in1, in2, out};
    }

    public Marble.Color getFirstInput(){
        return production[0];
    }

    public Marble.Color getSecondInput(){
        return production[0];
    }

    public Marble.Color getOutput(){
        return production[0];
    }

    @Override
    public void accept(ClientEventHandler handler) {
        handler.handle(this);
    }
}
