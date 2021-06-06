package it.polimi.ingsw.Message.Model;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.ModelEventHandler;

public class ErrorUpdate implements Message<ModelEventHandler> {

    private String errorCode;
    private String errorMessage;

    public ErrorUpdate(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public void accept(ModelEventHandler handler) {
        handler.handle(this);
    }
}
