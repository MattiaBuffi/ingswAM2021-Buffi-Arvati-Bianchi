package it.polimi.ingsw.Client.View;

import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.Model.ErrorUpdate;
import it.polimi.ingsw.Message.Model.ModelUpdate;
import it.polimi.ingsw.Message.ModelEventHandler;

public interface View {

    void updateView(ModelUpdate message);

    void displayError(ErrorUpdate message);
}
