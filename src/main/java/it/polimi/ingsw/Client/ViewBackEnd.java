package it.polimi.ingsw.Client;

import it.polimi.ingsw.Client.ModelData.ViewModel;
import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.ModelEventHandler;
import it.polimi.ingsw.Utils.Observable;
import it.polimi.ingsw.Utils.Observer;

public class ViewBackEnd extends Observable<Message<ClientEventHandler>> implements Observer<Message<ModelEventHandler>> {


    private ClientApp app;

    private ViewModel model;

    private ModelEventHandler eventHandler;



    public ViewBackEnd(ClientApp app){
        this.app = app;
    }


    public boolean connectToServer(String ip, int port){
        return app.onlineController( ip, port);
    }

    public void localGame(){
        //app.localController();
    }

    public void disconnectController(){
        //app.removeController();
    }



    public void setEventHandler(ModelEventHandler handler){
        this.eventHandler = handler;
    }




    public void setModel(ViewModel model){
        this.model = model;
    }

    public ViewModel getModel() {
        return model;
    }


    @Override
    public void update(Message<ModelEventHandler> event) {
        System.out.println("backend received a message");
        event.accept(eventHandler);
    }



}