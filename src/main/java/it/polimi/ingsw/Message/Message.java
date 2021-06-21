package it.polimi.ingsw.Message;

import java.io.Serializable;

public interface Message<T> extends Serializable {

    void accept(T handler);

}
