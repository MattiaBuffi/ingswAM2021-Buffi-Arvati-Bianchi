package it.polimi.ingsw.Client;

import it.polimi.ingsw.Client.GUI.Layout;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {


    private static Scene scene;
    private static ViewBackEnd backEnd;
    private ClientApp app;


    @Override
    public void start(Stage stage) throws IOException {
        app = new ClientApp( ()-> App.setScene("home_page") );
        backEnd = ViewBackEnd.getGUiBackend(app);

        app.setBackEnd(backEnd);

        scene = new Scene(loadFXML("home_page"));
        stage.setScene(scene);
        stage.setResizable(true);

        stage.show();
    }

    @Override
    public void stop() throws Exception {
        app.quit();
        super.stop();
    }

    public static void setScene(String fxml) {
        try{
            scene.setRoot(loadFXML(fxml));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Parent loadFXML(String fxml) throws IOException {

        FXMLLoader loader = new FXMLLoader(App.class.getResource("layouts/" + fxml + ".fxml"));//the ".." might not work in the jar
        Parent root = loader.load();

        try{
            Layout layout = loader.getController();
            layout.setup(backEnd);
        }catch (ClassCastException e){
            e.printStackTrace();
            System.err.println("\n"+fxml+": selected layout doesn't implements Layout interface");
        }

        return root;

    }

    public static void main(String[] args) {
        launch();
    }

}