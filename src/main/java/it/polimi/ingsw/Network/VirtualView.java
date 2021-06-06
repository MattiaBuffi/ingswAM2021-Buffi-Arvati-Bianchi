package it.polimi.ingsw.Network;


import it.polimi.ingsw.Utils.Observable;
import it.polimi.ingsw.Utils.Observer;



public class VirtualView extends Observable implements EventHandler, Observer {

    private ConnectionHandler connection;


    public VirtualView(ServerConnectionHandler.ConnectionBuilder connectionBuilder) throws Exception {

        this.connection =  connectionBuilder.setHandler(this).build();

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
