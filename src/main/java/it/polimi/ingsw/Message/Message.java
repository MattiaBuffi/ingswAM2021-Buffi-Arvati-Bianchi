package it.polimi.ingsw.Message;

public interface Message<T> {

    void accept(T handler);

}
