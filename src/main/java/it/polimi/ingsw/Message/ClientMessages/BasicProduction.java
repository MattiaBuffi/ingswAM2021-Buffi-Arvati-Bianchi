package it.polimi.ingsw.Message.ClientMessages;

import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Model.Marble.Marble;

/**
 * Message to report the basic production. The field is an array of Marble.Color built as follow:
 * - Position 0: Color of the first input resource
 * - Position 1: Color of the second input resource
 * - Position 2: Color of the output resource
 */
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
