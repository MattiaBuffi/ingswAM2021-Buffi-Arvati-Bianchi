package it.polimi.ingsw.Client.GUI.FXMLControllers;

import it.polimi.ingsw.Client.App;

import java.io.IOException;

public class HomePage {

    public void goToConnectionPage(){
        System.out.println("AAAAAAAAAAAAAAAHHHHHHHHHH");

        try {
            App.setRoot("connection_page");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
