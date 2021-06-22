package it.polimi.ingsw.Server;

import it.polimi.ingsw.Controller.GameController;
import it.polimi.ingsw.Network.ConnectionHandler;
import it.polimi.ingsw.Network.SocketCreator;
import it.polimi.ingsw.Network.SocketHandler;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class NewServerApp implements Server, SocketHandler {



    public static void main( String[] args ) {
        NewServerApp app = null;

        if(args.length>0){
            for(int i = 0; i < args.length; i++) {
                switch (args[i]){
                    case "-port":
                        app = new NewServerApp(Integer.getInteger(args[i+1]));
                        i+= 1;
                        break;
                }
            }
        } else {
            app = new NewServerApp(1337);
        }

        app.start();

    }


    private final int port;


    private ExecutorService executor;


    private SocketCreator networkManager;
    private List<Client> connectedClients;

    private final LoginHandler loginHandler;
    private final Lobby lobby;


    private NewServerApp(int port){
        this.port = port;
        this.networkManager = new SocketCreator(port, this);

        this.executor = Executors.newCachedThreadPool();

        connectedClients = new ArrayList<>();
        lobby = new Lobby();
        loginHandler = new LoginHandler(12);
    }


    public void start(){
        executor.execute(networkManager);
        new Thread(()->serverInterface()).start();
    }


    @Override
    public void handleSocket(Socket socket) {
        try {
            synchronized (connectedClients){
                ConnectionHandler.Builder builder = new ConnectionHandler.Builder(executor, socket);
                connectedClients.add(new ClientHandler(this, builder, new GameController()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private synchronized void shutdown(){

        for (Client client: connectedClients){
           client.disconnect();
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
                    System.out.println("Connected:" + connectedClients.size());
                    break;
                case "ip":

                    break;
                case "port":
                    System.out.println("port: " + port);
                    break;
                default:
                    System.out.println("Invalid Action");
            }

        }

    }


    @Override
    public void login(Client client, String username){
        synchronized (loginHandler){
            loginHandler.addUsername(client, username);
            joinGame(client);
        }
    }

    @Override
    public void joinGame(Client client){
        synchronized (lobby){
            lobby.join(client);
        }
    }

    @Override
    public void createGame(Client client, int size){
        synchronized (lobby){
            lobby.setGameSize(client, size);
        }
    }

    @Override
    public void removeClient(Client client){

        if(client.isActive()){
            client.disconnect();
        }

        synchronized (connectedClients){
            connectedClients.remove(client);
        }

        synchronized (loginHandler){
            loginHandler.removeUsername(client.getUsername());
        }


    }








}
