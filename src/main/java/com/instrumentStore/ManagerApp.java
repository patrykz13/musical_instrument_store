package com.instrumentStore;

import com.instrumentStore.controller.InitApplicationController;
import com.instrumentStore.controller.MainFrameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class ManagerApp extends Application {
    private static Stage mainStage;
    NumberFormat numberFormat;
    @Override
    public void start(Stage primaryStage) {
        try {
            Locale.setDefault(new Locale("en", "US"));
            numberFormat = NumberFormat.getCurrencyInstance(new Locale("en", "US"));
            setMainStage(primaryStage);
            FXMLLoader loader = new FXMLLoader();
            ResourceBundle bundle  = ResourceBundle.getBundle("bundles.messages");
            System.out.println(bundle);
            loader.setResources(bundle);
            loader.setLocation(getClass().getResource("/fxml/InitApplication.fxml"));
            loader.load();
            InitApplicationController display = loader.getController();
            display.setLocale(numberFormat);
            Parent root = loader.getRoot();
            primaryStage.setTitle(bundle.getString("application.title"));
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
