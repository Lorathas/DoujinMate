package org.lorathas.doujinmate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main extends Application {

    private Settings settings;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        var diContainer = DaggerDiContainer.create();

        settings = diContainer.settings();
        settings.initializeEnvironment();

        new Thread(diContainer.importTaskConsumer()).start();

        var loader = new FXMLLoader(getClass().getClassLoader().getResource("app.fxml"));
        loader.setController(diContainer.appController());

        Parent root = loader.load();
        primaryStage.setTitle("DoujinMate");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }
}
