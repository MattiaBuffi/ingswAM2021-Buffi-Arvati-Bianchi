package it.polimi.ingsw.Client;

import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.ClientMessages.Ping;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.ModelEventHandler;
import it.polimi.ingsw.Network.ConnectionHandler;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.Executor;

public class OnlineController extends Controller {

    private ConnectionHandler<Message<ModelEventHandler>, Message<ClientEventHandler>> connectionHandler;
    private KeepConnectionAlive connectionDaemon;

    public OnlineController(Socket socket, Executor executor, ConnectionHandler.ShutdownHandler closer) throws IOException {
        this.connectionHandler = new ConnectionHandler<>(socket, this::notify, closer);
        this.connectionDaemon = new KeepConnectionAlive();
        executor.execute(connectionHandler);
        executor.execute(connectionDaemon);
    }


    @Override
    public void disconnect() {
        this.connectionHandler.stop();
        this.connectionDaemon.stop();
    }

    @Override
    public void update(Message<ClientEventHandler> event) {
        connectionHandler.send(event);
    }

    private class KeepConnectionAlive implements Runnable{

        public boolean running;

        public KeepConnectionAlive() {
            this.running = true;
        }

        public void stop(){
            this.running = false;
        }

        @Override
        public void run() {
            while (running){
                connectionHandler.send(new Ping());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored){

                }
            }

        }
    }

}
