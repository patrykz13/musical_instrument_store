package com.instrumentStore.controller;

import com.instrumentStore.ManagerApp;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
/**
 * <p>InitApplicationController class.</p>
 *
 * @author Patryk Zdral
 * @version $Id: $Id
 */
public class InitApplicationController  implements Initializable{

    public ImageView germanFlag;
    public ImageView britishFlag;
    public ImageView polishFlag;
    NumberFormat numberFormatter;

    @FXML
    private void buttonLoadApplication_onAction() {
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(getClass().getClassLoader().getResource("fxml/MainFrame.fxml"));
            ResourceBundle resourceBundle = ResourceBundle.getBundle("bundles.messages");
            loader.setResources(resourceBundle);
            loader.load();
            MainFrameController display = loader.getController();
            display.setLocale(numberFormatter);
            Parent parent = loader.getRoot();
            Stage stage = ManagerApp.getMainStage();
            stage.setTitle(resourceBundle.getString("application.title"));
            Scene scene = new Scene(parent);
            stage.setScene(scene);
        } catch (IOException ioEcx) {
            Logger.getLogger(MainFrameController.class.getName()).log(Level.SEVERE, null, ioEcx);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        germanFlag.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {


            @Override
            public void handle(MouseEvent event) {
                init("de");
                numberFormatter = NumberFormat.getCurrencyInstance(new Locale("de","DE"));
                event.consume();
            }
        });

        britishFlag.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                init("en");
                numberFormatter = NumberFormat.getCurrencyInstance(new Locale("en","US"));
                event.consume();
            }
        });

        polishFlag.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                numberFormatter = NumberFormat.getCurrencyInstance(new Locale("pl","PL"));
                init("pl");
                event.consume();
            }
        });
    }

    void init(String string) {
        try {

            Locale.setDefault(new Locale(string));
            ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/InitApplication.fxml"));
            loader.setResources(bundle);
            loader.load();

            Parent parent = loader.getRoot();
            Stage stage = ManagerApp.getMainStage();
            stage.setScene(new Scene(parent));
            stage.setTitle(bundle.getString("application.title"));


        } catch (IOException ioEcx) {
            Logger.getLogger(InitApplicationController.class.getName()).log(Level.SEVERE, null, ioEcx);

        }
    }

    public void setLocale(NumberFormat numberFormat) {
        this.numberFormatter = numberFormat;
    }
}
