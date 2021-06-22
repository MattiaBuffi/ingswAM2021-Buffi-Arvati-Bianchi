package it.polimi.ingsw.Network;


import it.polimi.ingsw.Utils.Observable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.Executor;
import java.util.function.Consumer;


public class ConnectionHandler<IN, OUT> extends Observable implements Runnable {

    public interface ShutdownHandler {
        void close(ConnectionHandler connection);
    }

    public static class Builder<A, B>{

        private Executor executor;
        private Socket socket;

        public Builder(Executor executor,Socket socket){
            this.socket = socket;
            this.executor = executor;
        }

        public ConnectionHandler<A, B> build(int readTimeout, Consumer<A> handler, ShutdownHandler closer) throws IOException {
            ConnectionHandler connectionHandler = new ConnectionHandler(socket, readTimeout, handler, closer);
            executor.execute(connectionHandler);
            return connectionHandler;
        }

    }



    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    private boolean running;
    private ShutdownHandler closer;

    private final Consumer<IN> readHandler;

    private int socketTimeout;

    public ConnectionHandler(Socket socket, int readTimeout, Consumer<IN> readHandler, ShutdownHandler closer) throws IOException {

        this.socket = socket;
        this.socketTimeout = readTimeout;
        this.readHandler = readHandler;
        this.closer = closer;

        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());
    }

    public ConnectionHandler(Socket socket,Consumer<IN> readHandler, ShutdownHandler closer) throws IOException {

        this.socket = socket;
        this.socketTimeout = 0;
        this.readHandler = readHandler;
        this.closer = closer;

        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());

    }



    public boolean isRunning() {
        return running;
    }

    public void stop() {
        this.running = false;
        shutdown();
    }


    public synchronized void send(OUT event) {
        try {
            out.reset();
            out.writeObject(event);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void handleReadMessage(IN event){
        readHandler.accept(event);
    }


    @Override
    public void run(){

        this.running = true;

        while(running){
            try {
                this.socket.setSoTimeout(socketTimeout);
                @SuppressWarnings("unchecked")
                IN event = (IN) in.readObject();
                handleReadMessage(event);
            } catch( ClassNotFoundException e){
                e.printStackTrace();
                break;
            } catch (IOException e){
                closer.close(this);
                break;
            }
        }

    }


    protected void shutdown(){

        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }





}