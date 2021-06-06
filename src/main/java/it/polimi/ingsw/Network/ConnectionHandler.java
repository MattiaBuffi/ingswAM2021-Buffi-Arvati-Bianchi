package it.polimi.ingsw.Network;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;



public abstract class ConnectionHandler<IN, OUT> implements Runnable {

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private boolean running;


    public ConnectionHandler(Socket socket){
        this.socket = socket;
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

    protected abstract void handleReadMessage(IN event);


    @Override
    public void run(){

        this.running = true;

        while(running){
            try {
                @SuppressWarnings("unchecked")
                IN event = (IN) in.readObject();
                handleReadMessage(event);
            } catch( ClassNotFoundException | IOException e){
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
