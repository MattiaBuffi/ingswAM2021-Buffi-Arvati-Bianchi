package it.polimi.ingsw.Client;


import javax.swing.text.View;

public class ClientApp implements ModeSelector{



    public static void main( String[] args )
    {

        if(args.length>0){
            for(int i = 0; i < args.length; i++) {
                switch (args[i]){
                    case "-cli":
                        //new cli app;
                        break;
                    case "-gui":
                        //new Gui app
                        break;
                }
            }
        } else {
            //gui app
        }

    }


    private View view;



    @Override
    public void localGame() {

    }

    @Override
    public void onlineGame(int port, int ip) {

    }




}
