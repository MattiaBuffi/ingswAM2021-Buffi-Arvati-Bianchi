package it.polimi.ingsw.Network;


import java.net.Socket;
import java.util.concurrent.Executor;



public class ServerConnectionHandler extends ConnectionHandler {


    public static class ConnectionBuilder{

        private Executor executor;
        private EventHandler handler;
        private Socket socket;

        public ConnectionBuilder setExecutor(Executor executor) {
            this.executor = executor;
            return this;
        }

        public ConnectionBuilder setHandler(EventHandler handler) {
            this.handler = handler;
            return this;
        }

        public ConnectionBuilder setSocket(Socket socket) {
            this.socket = socket;
            return this;
        }

        public ServerConnectionHandler build() throws Exception {

            if(executor == null){
                throw new Exception();
            }
            if(handler == null){
                throw new Exception();
            }
            if(socket == null){
                throw new Exception();
            }

            return new ServerConnectionHandler(socket, handler, executor);

        }

    }


    private final Executor executor;
    private final EventHandler handler;


    private ServerConnectionHandler(Socket socket, EventHandler handler, Executor executor) {
        super(socket, (T)->{} );//fix
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
