package it.polimi.ingsw.Network;


import java.net.Socket;
import java.util.concurrent.Executor;



public class AsyncConnectionHandler extends ConnectionHandler {


    public static class ConnectionBuilder{

        private Executor executor;
        private Socket socket;

        public ConnectionBuilder(Executor executor,Socket socket){
            this.socket = socket;
            this.executor = executor;
        }

        public AsyncConnectionHandler build(EventHandler handler) {
            return new AsyncConnectionHandler(socket, handler, executor);
        }


    }


    private final Executor executor;
    private final EventHandler handler;


    private AsyncConnectionHandler(Socket socket, EventHandler handler, Executor executor) {
        super(socket);
        this.handler = handler;
        this.executor = executor;
        executor.execute(this);
    }


    @Override
    protected void handleReadMessage(Object event) {
        synchronized (executor){
            executor.execute(()->handler.handleEvent(event));
        }
    }



}
