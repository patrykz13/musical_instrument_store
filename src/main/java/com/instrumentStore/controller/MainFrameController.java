package com.instrumentStore.controller;

import com.instrumentStore.CustomMessageBox;
import com.instrumentStore.ManagerApp;
import com.instrumentStore.store.Phone;
import com.instrumentStore.store.Warehouse;
import com.instrumentStore.store.XmlParser;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class MainFrameController implements Initializable {
    @FXML
    public TextField textFieldAmount;
    @FXML
    public DatePicker textFieldDateOfProduction;
    @FXML
    public TextField textFieldPrice;
    @FXML
    public Button buttonFilePath;
    @FXML
    public TextField textFieldProducent;
    @FXML
    public TextField textFieldMemory;
    @FXML
    public TextField textFieldModel;
    @FXML
    public Label labelProductProducentEmpty;
    @FXML
    public Label labelProductModelEmpty;
    @FXML
    public Label labelProductAmountEmpty;
    @FXML
    public Label labelProductDateOfProductionEmpty;
    @FXML
    public Label labelProductMemoryEmpty;
    @FXML
    public Label labelProductPriceEmpty;
    @FXML
    public ImageView imageViewProductPhoto;
    @FXML
    public ImageView germanFlag;
    @FXML
    public ImageView britishFlag;
    @FXML
    public ImageView polishFlag;
    @FXML TableView<Phone>tableViewPhone;
    @FXML TableColumn<Phone,String>tableColumnProducent;
    @FXML TableColumn<Phone,String>tableColumnModel;
    @FXML TableColumn<Phone,Integer>TableColumnMemory;
    @FXML TableColumn<Phone,Date>tableColumnDateOfProduction;
    @FXML TableColumn<Phone,Double>tableColumnPrice;
    //private ObservableList<Phone> phoneObservableList = FXCollections.observableArrayList();
    private Phone phone;
    private Warehouse warehouse;
    private String filePath;
    String xmlPath;
    Locale currentLocale;
    private static DateFormat dateFormatter;
    private static NumberFormat numberFormatter;
    private ResourceBundle resourceBundle;
    private CustomMessageBox customMessageBox;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customMessageBox = new CustomMessageBox();
        resourceBundle = resources;
        this.tableColumnProducent.setCellValueFactory(new PropertyValueFactory<>("producent"));
        this.tableColumnModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        this.tableColumnDateOfProduction.setCellValueFactory(new PropertyValueFactory<>("dateOfProduction"));
        this.tableColumnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        this.TableColumnMemory.setCellValueFactory(new PropertyValueFactory<>("memory"));

        currentLocale=resources.getLocale();
       // numberFormatter.setCurrency(new Currency(currentLocale.getCountry()));
        dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT, currentLocale);
        germanFlag.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                numberFormatter = NumberFormat.getCurrencyInstance(Locale.GERMANY);
                init("de");
                event.consume();
            }
        });

        britishFlag.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                numberFormatter = NumberFormat.getCurrencyInstance(new Locale("en","US"));
                init("en");
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

        tableViewPhone.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Phone>() {

            @Override
            public void changed(ObservableValue<? extends Phone> observable, Phone oldValue, Phone newValue) {
                phone = tableViewPhone.getSelectionModel().getSelectedItem();
                System.out.println(phone.getProducent());
                System.out.println(phone.getModel());
                System.out.println(phone.getPhotoPath());
                System.out.println(phone.getPrice());
                System.out.println(phone.getDateOfProduction());
                filePath=phone.getPhotoPath();
                File f = new File(filePath);
                Image img = new Image(f.toURI().toString());
                imageViewProductPhoto.setImage(img);
                labelProductProducentEmpty.setText(phone.getProducent());
                labelProductModelEmpty.setText(phone.getModel());
                labelProductMemoryEmpty.setText(phone.getMemory().toString());
                labelProductAmountEmpty.setText(phone.getAmount().toString());
                labelProductDateOfProductionEmpty.setText(dateFormatter.format(phone.getDateOfProduction()));
                String moneyString = numberFormatter.format(phone.getPrice());
                System.out.println(moneyString);
                labelProductPriceEmpty.setText(numberFormatter.format(phone.getPrice()));

            }
        });
    }



    void init(String string) {
        FXMLLoader loader = new FXMLLoader();
        try {
            Locale.setDefault(new Locale(string));
            loader.setLocation(getClass().getClassLoader().getResource("fxml/MainFrame.fxml"));
            ResourceBundle resourceBundle = ResourceBundle.getBundle("bundles.messages");
            loader.setResources(resourceBundle);
            loader.load();
            MainFrameController display = loader.getController();
            display.fillTableViewAfterGUIReload(warehouse);
            Parent parent = loader.getRoot();
            Stage stage = ManagerApp.getMainStage();
            stage.setTitle(resourceBundle.getString("application.title"));
            Stage currentStage = (Stage) tableViewPhone.getScene().getWindow();
            Scene scene = new Scene(parent, currentStage.getWidth() - 16.0, currentStage.getHeight() - 39.5);
            stage.setScene(scene);
        } catch (IOException ioEcx) {
            Logger.getLogger(MainFrameController.class.getName()).log(Level.SEVERE, null, ioEcx);
        }
    }

    private void fillTableViewAfterGUIReload(Warehouse warehouse) {
        this.warehouse = warehouse;
        //System.out.println(warehouse.getItemFromListOfPhones(0).getProducent());
        if (warehouse != null){
            ObservableList<Phone> data = tableViewPhone.getItems();
            for(int i=0;i<warehouse.getListOfPhones().size();i++){
                data.add( warehouse.getItemFromListOfPhones(i));
            }
        }
    }

    @FXML
    void buttonChoosePhotoPath_onAction() {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("C:\\Users\\Patryk Zdral\\IdeaProjects\\phone_store"));
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            filePath = selectedFile.toString();
        }
    }

    @FXML
    void buttonLoad_onAction() {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("C:\\Users\\Patryk Zdral\\IdeaProjects\\phone_store"));
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("xml Files","*.xml"));
        File selectedFile = fc.showOpenDialog(null);
        if(selectedFile !=null){
            xmlPath = selectedFile.toString();
            XmlParser x = new XmlParser();
            ObservableList<Phone> data = tableViewPhone.getItems();
            Warehouse warehouse = x.readFromXMLFile(xmlPath);
            for(int i=0;i<warehouse.getListOfPhones().size();i++){
               data.add( warehouse.getItemFromListOfPhones(i));
            }
            //phoneObservableList.addAll(warehouse.getListOfPhones());

        }
    }

    @FXML
    void buttonSave_onAction() {
        XmlParser xml = new XmlParser();
        ObservableList<Phone> data = tableViewPhone.getItems();
        warehouse =new Warehouse();
        warehouse.setListOfPhones(data);
        xml.saveToXMLFile(warehouse,"test");
    }

    @FXML
    void buttonShowStatistics_onAction() {

    }

    @FXML
    void buttonAdd_onAction() {
        try{
            ObservableList<Phone> data = tableViewPhone.getItems();
            SimpleDateFormat firstDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            LocalDate localDate = textFieldDateOfProduction.getValue();
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            if (warehouse == null)
                warehouse = new Warehouse();
            Phone phone = new Phone(textFieldProducent.getText(),textFieldModel.getText(),filePath.toString(),Integer.parseInt(textFieldMemory.getText()),date,Integer.parseInt(textFieldAmount.getText()),Double.parseDouble(textFieldPrice.getText()));
            warehouse.addToListOfPhones(phone);


            data.add(phone);

        }catch (Exception e) {
            customMessageBox.showMessageBox(Alert.AlertType.WARNING, resourceBundle.getString("alert.warning.title"), resourceBundle.getString("alert.warning.header.add_phone"), resourceBundle.getString("alert.warning.context.add_phone")).showAndWait();
        }

    }

    @FXML
    void buttonDelete_onAction() {
        if(tableViewPhone.getSelectionModel().getSelectedItem()!=null){
            tableViewPhone.getItems().remove((tableViewPhone.getSelectionModel().getSelectedItem()));
            warehouse.getListOfPhones().remove((tableViewPhone.getSelectionModel().getSelectedItem()));
        }
    }

    public void setLocale(NumberFormat numberFormat) {
        this.numberFormatter = numberFormat;
    }
}