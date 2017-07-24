/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import de.beinlich.markus.pizzaserviceeemarkusbeinlich.fx.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author Markus
 */
public class OrderTableController {

    @FXML
    private TableView<OrderTable> orderTable;
    @FXML
    private TableColumn<OrderTable, Integer> customerIdColumn;
    @FXML
    private TableColumn<OrderTable, String> firstNameColumn;
    @FXML
    private TableColumn<OrderTable, String> lastNameColumn;
    @FXML
    private TableColumn<OrderTable, Integer> orderIdColumn;
    @FXML
    private TableColumn<OrderTable, LocalDateTime> orderDateColumn;
    @FXML
    private TableColumn<OrderTable, String> nameColumn;
    @FXML
    private TableColumn<OrderTable, BigDecimal> priceColumn;
    @FXML
    private TableColumn<OrderTable, Integer> quantityColumn;
//    
    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public OrderTableController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        customerIdColumn.setCellValueFactory(cellData -> cellData.getValue().customerIdProperty().asObject());
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        orderIdColumn.setCellValueFactory(cellData -> cellData.getValue().orderIdProperty().asObject());
        orderDateColumn.setCellValueFactory(cellData -> cellData.getValue().orderDateProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        quantityColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
                
        
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        orderTable.setItems(mainApp.getOrderTable());
    }
}
