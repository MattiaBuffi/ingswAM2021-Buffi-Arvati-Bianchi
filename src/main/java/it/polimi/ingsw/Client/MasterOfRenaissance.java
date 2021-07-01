package it.polimi.ingsw.Client;

import it.polimi.ingsw.Client.CLI.Cli;
import it.polimi.ingsw.Server.ServerApp;

import java.util.ArrayList;
import java.util.List;


public class MasterOfRenaissance {

    public static void main(String[] args){

        List<String> argsList = new ArrayList<>(List.of(args));



        if(argsList.size()==0){
            Cli.main(args);
        } else {

            for (String arg : argsList) {
                switch (arg) {
                    case "-cli":
                        Cli.main(  argsList.toArray(new String[argsList.size()])  );
                        return;
                    case "-gui":
                        App.main(  argsList.toArray(new String[argsList.size()]) );
                        return;
                    case "-server":
                        argsList.remove("-server");
                        ServerApp.main(  argsList.toArray(new String[argsList.size()]) );
                        return;
                }
            }

            System.out.println("need to start this application with one of these flags[\"-cli\",\"-gui\",\"-server\"]");

        }





    }

}