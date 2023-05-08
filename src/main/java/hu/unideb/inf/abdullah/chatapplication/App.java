package hu.unideb.inf.abdullah.chatapplication;

import hu.unideb.inf.abdullah.chatapplication.sockets.ChatServer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    private static Scene scene;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Login.fxml"));
        Parent root = fxmlLoader.load();
        scene = new Scene(root, 600, 520);
        ((HelloController)fxmlLoader.getController()).setModel(new Model());
        stage.setTitle("Chat!");
        stage.setScene(scene);
        stage.show();
        //Running the server
        ChatServer server = new ChatServer();
        Thread serverThread = new Thread(server);
        serverThread.start();
    }
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml));
        return fxmlLoader.load();
    }


    public static void main(String[] args) {
        launch();
    }
}