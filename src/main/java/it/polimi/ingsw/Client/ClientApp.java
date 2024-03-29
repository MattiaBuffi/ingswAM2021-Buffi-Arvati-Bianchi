package it.polimi.ingsw.Client;

import it.polimi.ingsw.Message.ModelEventHandler;
import it.polimi.ingsw.Network.ConnectionHandler;
import it.polimi.ingsw.Utils.Observer;

import java.io.IOException;

import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class ClientApp implements ConnectionHandler.ShutdownHandler {


    private Controller controller;
    private ViewBackEnd backEnd;
    private ExecutorService executor;

    private Runnable resetScreen;


    //Here runnable is used as a functional interface that has no input and no output. still, it might be confusing
    public ClientApp(Runnable resetScreen) {
        this.executor = Executors.newCachedThreadPool();
        this.resetScreen = resetScreen;
    }

    public void setBackEnd(ViewBackEnd backEnd) {
        this.backEnd = backEnd;
    }

    private Socket getSocket(String ip, int port) throws IOException {
        Socket socket = new Socket(ip,port);
        return socket;
    }


    public boolean onlineController(String ip, int port){

        if(!validIP(ip)){
            return false;
        }

        try {
            this.controller = new OnlineController(getSocket(ip,port), executor, this);
        } catch (IOException e) {
            System.err.println("Socket creation failed");
            return false;
        }

        this.controller.addObserver(backEnd);
        backEnd.addObserver(controller);
        return true;

    }

    public void localController(){
        backEnd.setUsername("player1");
        this.controller = new LocalController("player1", backEnd);
        backEnd.addObserver(controller);
    }


    public void removeController(){
        if(controller != null){
            this.backEnd.removeObserver(controller);
            this.controller.disconnect();
            this.controller = null;
            resetScreen.run();
        }
    }


    public void quit(){

        removeController();

        try {
            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        } finally {
            if (!executor.isTerminated()) {
                executor.shutdownNow();
            }
        }

    }


    @Override
    public void close(ConnectionHandler connection) {
        removeController();
    }


    private boolean validIP(String ip) {
        if (ip == null || ip.isEmpty()) return false;
        ip = ip.trim();
        if ((ip.length() < 6) & (ip.length() > 15)) return false;

        try {
            Pattern pattern = Pattern.compile("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
            Matcher matcher = pattern.matcher(ip);
            return matcher.matches();
        } catch (PatternSyntaxException ex) {
            return false;
        }
    }



}
