package it.polimi.ingsw.Message.ClientMessage;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.SingleClientEventHandler;

public class BasicProduction implements Message<SingleClientEventHandler> {
    private String[] production;

    public BasicProduction(String in1, String in2, String out){
        production = new String[]{in1, in2, out};
    }

    @Override
    public void accept(SingleClientEventHandler handler) {
        handler.handle(this);
    }
}
