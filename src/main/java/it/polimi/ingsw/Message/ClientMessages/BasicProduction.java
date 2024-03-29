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

    private static final long serialVersionUID = 5233473999590757917L;
    private Marble.Color in1;
    private Marble.Color in2;
    private Marble.Color out;

    public BasicProduction(Marble.Color in1, Marble.Color in2, Marble.Color out) {
        this.in1 = in1;
        this.in2 = in2;
        this.out = out;
    }

    public Marble.Color getFirstInput(){
        return in1;
    }

    public Marble.Color getSecondInput(){
        return in2;
    }

    public Marble.Color getOutput(){
        return out;
    }

    @Override
    public void accept(ClientEventHandler handler) {
        handler.handle(this);
    }
}
