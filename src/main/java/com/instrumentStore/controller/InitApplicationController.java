package com.instrumentStore.controller;

import com.instrumentStore.ManagerApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
/**
 * <p>InitApplicationController class.</p>
 *
 * @author Patryk Zdral
 * @version $Id: $Id
 */
public class InitApplicationController {

    @FXML
    private void buttonLoadApplication_onAction() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/MainFrame.fxml"));
            loader.load();

            MainFrameController display = loader.getController();


            Parent parent = loader.getRoot();
            Stage stage = ManagerApp.getMainStage();
            stage.setScene(new Scene(parent));
        } catch (IOException ioEcx) {
            Logger.getLogger(InitApplicationController.class.getName()).log(Level.SEVERE, null, ioEcx);

        }
    }

}
