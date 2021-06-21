package it.polimi.ingsw.Network;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.Executor;
import java.util.function.Consumer;


public class ConnectionHandler<IN, OUT> implements Runnable {



    public static class Builder<A, B>{

        private Executor executor;
        private Socket socket;

        public Builder(Executor executor,Socket socket){
            this.socket = socket;
            this.executor = executor;
        }

        public ConnectionHandler<A, B> build(Consumer<A> handler) {
            ConnectionHandler connectionHandler = new ConnectionHandler(socket, handler);
            executor.execute(connectionHandler);
            return connectionHandler;
        }

    }



    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private boolean running;

    private final Consumer<IN> readHandler;

    public ConnectionHandler(Socket socket, Consumer<IN> readHandler){
        this.socket = socket;
        this.readHandler = readHandler;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                @SuppressWarnings("unchecked")
                IN event = (IN) in.readObject();
                handleReadMessage(event);
            } catch( ClassNotFoundException e){
                e.printStackTrace();
                break;
            } catch (IOException e){
                //e.printStackTrace();//commented out to avoid crash when client disconnect. need fix
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