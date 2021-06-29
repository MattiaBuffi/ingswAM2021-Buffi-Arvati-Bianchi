package it.polimi.ingsw.Client;

import it.polimi.ingsw.Client.ModelData.ViewModel;
import it.polimi.ingsw.Message.ClientEventHandler;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Message.Model.ActivePlayer;
import it.polimi.ingsw.Message.ModelEventHandler;
import it.polimi.ingsw.Utils.Observable;
import it.polimi.ingsw.Utils.Observer;
import javafx.application.Platform;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ViewBackEnd extends Observable<Message<ClientEventHandler>> implements Observer<Message<ModelEventHandler>> {

    private String username;

    private ClientApp app;

    private ViewModel model;

    private ModelEventHandler eventHandler;

    private BiConsumer<Message<ModelEventHandler>, ModelEventHandler> eventHandlerBiConsumer;


    private void cliEventDispatcher(Message<ModelEventHandler> event){
        event.accept(eventHandler);
    }


    public ViewBackEnd(ClientApp app, BiConsumer<Message<ModelEventHandler>, ModelEventHandler> eventHandlerBiConsumer){
        this.app = app;
        this.eventHandlerBiConsumer = eventHandlerBiConsumer;
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

    public String getMyUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void update(Message<ModelEventHandler> event) {
        //eventHandlerBiConsumer.accept(event, eventHandler);
        Platform.runLater(() -> event.accept(eventHandler));
    }


}
