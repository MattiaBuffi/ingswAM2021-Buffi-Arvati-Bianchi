package it.polimi.ingsw.Network;


import it.polimi.ingsw.Utils.Observable;
import it.polimi.ingsw.Utils.Observer;



public class VirtualView extends Observable implements EventHandler, Observer {

    private ConnectionHandler connection;


    public VirtualView(AsyncConnectionHandler.ConnectionBuilder connectionBuilder) {

        this.connection =  connectionBuilder.build(this);

    }

    public ConnectionHandler getConnection(){
        return connection;
    }


    @Override
    public void handleEvent(Object event) {
        this.notify(event);
    }


    @Override
    public void update(Object event) {
        connection.send(event);
    }

}
