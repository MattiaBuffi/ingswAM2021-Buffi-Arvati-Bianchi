package it.polimi.ingsw.Client.GUI.FXMLControllers;

import it.polimi.ingsw.Client.App;
import it.polimi.ingsw.Client.GUI.Layout;
import it.polimi.ingsw.Client.ViewBackEnd;

public class HomePage implements Layout {

    private ViewBackEnd backEnd;
    @Override
    public void setup(ViewBackEnd backEnd) {
        this.backEnd = backEnd;
    }

    public void initialize(){
        System.out.println("HomePage");
        this.backEnd = backEnd;
    }



    public void goToConnectionPage(){
        App.setScene("connection_page");
    }




}
