package it.polimi.ingsw.GUI.FXMLControllers;

import it.polimi.ingsw.App;

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
