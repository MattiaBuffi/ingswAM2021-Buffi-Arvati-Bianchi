package it.polimi.ingsw.Utils;

import java.util.ArrayList;
import java.util.List;

public class Observable<T> {

    private final List<Observer<T>> observers = new ArrayList<>();


    public void addObserver(Observer<T> observer){
        synchronized (observers) {
            observers.add(observer);
        }
    }

    public void removeAllObserver(){
       observers.clear();
    }

    public void removeObserver(Observer<T> observer){
        synchronized (observers) {
            observers.remove(observer);
        }
    }

    public void notify(T event){
        synchronized (observers) {
            for(Observer<T> observer : observers){
                observer.update(event);
            }
        }
    }



}
