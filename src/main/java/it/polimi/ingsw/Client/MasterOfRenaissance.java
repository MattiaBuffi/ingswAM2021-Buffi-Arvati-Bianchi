package it.polimi.ingsw.Client;

import it.polimi.ingsw.Client.CLI.Cli;
import it.polimi.ingsw.Server.ServerApp;






public class MasterOfRenaissance {

    public static void main(String[] args){

        if(args.length==0){
            Cli.main(args);
        } else if(args.length> 0){

            for (String arg : args) {
                switch (arg) {
                    case "-cli":
                        Cli.main(args);
                        return;
                    case "-gui":
                        App.main(args);
                        return;
                    case "-server":
                        ServerApp.main(args);
                        return;
                }
            }

            System.out.println("need to start this application with one of these flags[\"-cli\",\"-gui\",\"-server\"]");



        }





    }

}