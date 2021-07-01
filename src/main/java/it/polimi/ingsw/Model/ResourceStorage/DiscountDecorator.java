package it.polimi.ingsw.Model.ResourceStorage;

import it.polimi.ingsw.Model.Marble.Marble;
import it.polimi.ingsw.Model.Marble.ResourceList;



/**
 *  decoratore di PlayerStorage. riduce le richieste di prelievo a seconda della quantita discount
 */
public class DiscountDecorator implements ResourceStorage {

    private final Marble.Color discount;
    private ResourceStorage storage;


    public DiscountDecorator(ResourceStorage storage, Marble.Color discount) {
        this.storage = storage;
        this.discount = discount;
    }


    /**
     *  inoltra la richiesta deposit allo storage decorato
     */
    @Override
    public boolean deposit(ResourceList resourceList) {
        return storage.deposit(resourceList);
    }


    /**
     *  inoltra la richiesta withdrawla allo storage decorato ridotta della quantita discount
     */
    @Override
    public boolean withdrawal(ResourceList resourceList) {
        return storage.withdrawal(resourceList.subtract(discount));
    }


    /**
     *  ritorna le risorse contenute nello storage decorato più la qunatita discpunt
     */
    @Override
    public ResourceList getResources() {
        return storage.getResources().sum(discount);
    }


}