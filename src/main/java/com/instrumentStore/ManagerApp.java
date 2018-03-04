package com.instrumentStore;

import com.instrumentStore.controller.InitApplicationController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class ManagerApp extends Application {
    private static Stage mainStage;

    @Override
    public void start(Stage primaryStage) {
        try {
            setMainStage(primaryStage);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/InitApplication.fxml"));
            loader.load();


            Parent root = loader.getRoot();
            primaryStage.setTitle("musical instrument store management");
            primaryStage.setScene(new Scene(root, 600, 150));
            primaryStage.getIcons().add(new Image("/images/tone.png"));


            primaryStage.show();
        } catch (IOException ioEcx) {
            Logger.getLogger(ManagerApp.class.getName()).log(Level.SEVERE, null, ioEcx);
        }
    }

    @Override
    public void stop() {
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getMainStage() {
        return mainStage;
    }

    public static void setMainStage(Stage mainStage) {
        ManagerApp.mainStage = mainStage;
    }

}
