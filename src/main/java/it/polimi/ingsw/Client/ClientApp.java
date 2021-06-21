package it.polimi.ingsw.Client;

import com.sun.javafx.iio.ios.IosDescriptor;

import java.io.IOException;

import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class ClientApp {


    private Controller controller;
    private ViewBackEnd backEnd;
    private Executor executor;

    public ClientApp() {
        this.executor = Executors.newCachedThreadPool();
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
            this.controller = new OnlineController(getSocket(ip,port), executor);
        } catch (IOException e) {
            return false;
        }

        this.controller.addObserver(backEnd);
        backEnd.addObserver(controller);
        return true;
    }

    public void localController(){
        this.controller = new LocalController();
        this.controller.addObserver(backEnd);
        backEnd.addObserver(controller);
    }


    public void removeController(){
        this.backEnd.removeObserver(controller);
        this.controller.disconnect();
        this.controller = null;
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
