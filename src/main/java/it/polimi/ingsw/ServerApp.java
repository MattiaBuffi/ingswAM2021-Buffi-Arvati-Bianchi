package it.polimi.ingsw;

import it.polimi.ingsw.Controller.ServerController;
import it.polimi.ingsw.Network.*;
import it.polimi.ingsw.ServerModel.ServerModel;


import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class ServerApp implements SocketHandler {

    public static void main( String[] args )
    {
        ServerApp app = null;

        if(args.length>0){
            for(int i = 0; i < args.length; i++) {
                switch (args[i]){
                    case "-port":
                        app = new ServerApp(Integer.getInteger(args[i+1]));
                        break;
                }
            }
        } else {
            app = new ServerApp(1337);
        }

        app.start();

    }

    private final int port;


    private ServerModel model;
    private ServerController controller;
    private SocketCreator networkManager;

    private List<VirtualView> connections;

    private ExecutorService executor;


    private ServerApp(int port){
        this.port = port;
        this.model = new ServerModel();
        this.controller = new ServerController();
        this.networkManager = new SocketCreator(port, this);
        this.connections = new ArrayList<>();
        this.executor = Executors.newCachedThreadPool();
    }


    public void start(){
        executor.execute(networkManager);
        new Thread(()->serverInterface()).start();
    }


    @Override
    public void handleSocket(Socket socket) {
        try {

            VirtualView view = new VirtualView(new ServerConnectionHandler.ConnectionBuilder()
                                                    .setExecutor(executor)
                                                    .setSocket(socket));
            connections.add(view);
            view.addObserver(controller);


            model.connect(view);
            /*
            test = new testHandler(socket);
            executor.execute(test);
            */

        } catch (Exception e) {
           e.printStackTrace();
        }

    }

    private void shutdown(){

        for (VirtualView v: connections){
            v.getConnection().stop();
        }

        networkManager.stop();

        try {
            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        } finally {
            if (!executor.isTerminated()) {
                System.err.println("Executor shutdown timed out. Invoking force quit");
                executor.shutdownNow();
            } else {
                System.out.println("Shutdown completed");
            }
        }

    }

    private void serverInterface(){

        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println("Server Ready");

        while(true){

            input = scanner.nextLine().toLowerCase();

            switch (input){
                case "quit":
                    System.out.println("Server closed");
                    shutdown();
                    System.exit(0);
                    break;
                case "state":
                    System.out.println("Connected:" + connections.size());
                    break;
                case "ip":
                    try(final DatagramSocket socket = new DatagramSocket()){
                        socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
                        System.out.println("ip: "+ socket.getLocalAddress().getHostAddress());
                    } catch (SocketException | UnknownHostException e) {
                        e.printStackTrace();
                    }
                case "port":
                    System.out.println("port: " + port);
                    break;
                default:
                    System.out.println("Invalid Action");
            }

        }

    }




    /*

    private testHandler test;

    private class testHandler extends ConnectionHandler {

        public testHandler(Socket socket) {
            super(socket);
        }

        @Override
        protected void handleReadMessage(Object event) {
            System.out.println("-Received: " + event);
        }

    }*/











}
