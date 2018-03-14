package com.instrumentStore.controller;

import com.ibm.icu.text.PluralRules;
import com.instrumentStore.ManagerApp;
import com.instrumentStore.store.Phone;
import com.instrumentStore.store.Warehouse;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class StatisticsController implements Initializable {
    public Label labelPhones;
    public Label labelbytes;
    public Label labelPrice;
    private Warehouse warehouse;
    public ImageView germanFlag;
    public ImageView britishFlag;
    public ImageView polishFlag;
    NumberFormat  numberFormatter;
    ResourceBundle bundleResources;
    PluralRules pluralRules;
    int size;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        bundleResources = resources;
        pluralRules = PluralRules.forLocale(Locale.getDefault());


    }

    void reload( String string){
        try {

            Locale.setDefault(new Locale(string));
            ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/Statistics.fxml"));
            loader.setResources(bundle);
            loader.load();
            StatisticsController con = loader.getController();
            con.init(this.warehouse,numberFormatter);
            Parent parent = loader.getRoot();
            Stage stage = ManagerApp.getMainStage();
            stage.setScene(new Scene(parent));
            stage.setTitle(bundle.getString("application.title"));


        } catch (IOException ioEcx) {
            Logger.getLogger(InitApplicationController.class.getName()).log(Level.SEVERE, null, ioEcx);

        }
    }


    public void init(Warehouse warehouse,NumberFormat numberFormatter) {
        this.numberFormatter=numberFormatter;
        if(warehouse==null){
            warehouse=new Warehouse();
        }
        else{
            this.warehouse=warehouse;
        }

        Integer numberOfPhones =0;
        Integer numberOfBytes =0;
        Double sumOfPrices = Double.parseDouble("0");
        List<Phone> listOfPhones = warehouse.getListOfPhones();
        for (Phone phone : listOfPhones){
            numberOfPhones+=phone.getAmount();
            numberOfBytes+=phone.getMemory();
            sumOfPrices+=phone.getPrice()*phone.getAmount();

        }
        labelPhones.setText(MessageFormat
                .format(bundleResources.getString("number_of_phones.plural_form." + pluralRules
                        .select(numberOfPhones)), numberOfPhones));


        labelbytes.setText(MessageFormat
                .format(bundleResources.getString("number_of_bytes.plural_form." + pluralRules
                        .select(numberOfBytes)), numberOfBytes));
        String moneyString = numberFormatter.format(sumOfPrices);
        //System.out.println(labelPrice.getText());
        labelPrice.setText(bundleResources.getString("sum_of_prices")+"  "+moneyString);


    }
}
