package it.polimi.ingsw.Client;

import java.io.IOException;

import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class ClientApp {


    private Controller controller;
    private ViewBackEnd backEnd;


    private Socket getSocket(String ip, int port){
        try {
            Socket socket = new Socket(ip,port);
            return socket;
        } catch (IOException e) {
            //return something???
            e.printStackTrace();
        }
        System.out.println("WHY????");
        return null;
    }


    public void onlineController(String ip, int port){

        if(!validIP(ip)){
            return;
        }

        this.controller = new OnlineController(getSocket(ip,port));
        this.controller.addObserver(backEnd);
        backEnd.addObserver(controller);

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
