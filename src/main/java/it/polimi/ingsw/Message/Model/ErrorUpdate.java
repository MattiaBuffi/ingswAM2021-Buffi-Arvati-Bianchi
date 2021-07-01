package it.polimi.ingsw.Message.Model;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.ModelEventHandler;

public class ErrorUpdate implements Message<ModelEventHandler> {

    private static final long serialVersionUID = -5979965940561722231L;
    //private String errorCode;
    private String errorMessage;

    public ErrorUpdate(String errorMessage) {
        //this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    /*
    public String getErrorCode() {
        return errorCode;
    }*/

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public void accept(ModelEventHandler handler) {
        handler.handle(this);
    }
}
