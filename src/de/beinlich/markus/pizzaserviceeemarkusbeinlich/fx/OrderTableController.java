/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.beinlich.markus.pizzaserviceeemarkusbeinlich.fx;

import de.beinlich.markus.pizzaservice.model.OrderEntry;
import de.beinlich.markus.pizzaservice.model.OrderHeader;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

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

    private ConnectionFactory connectionFactory;
    private Session session;
    private Destination destination;
    private Connection connection;

    /**
     * The constructor. The constructor is called before the initialize()
     * method.
     */
    public OrderTableController() {
        try {
            System.out.println("OrderTAbleController");
            Properties p = new Properties();
            p.put(Context.INITIAL_CONTEXT_FACTORY,
                    "com.sun.jndi.fscontext.RefFSContextFactory");
            p.put(Context.PROVIDER_URL,"file:///C:/imq_admin_objects");
            Context context = new InitialContext(p);
            connectionFactory = (ConnectionFactory) context.lookup(
                    "jms/PizzaTopicConnectionFactory");
            destination = (Destination) context.lookup(
                    "jms/PizzaTopic");
            try {
                connection = connectionFactory.createConnection();
                session = connection.createSession(
                        false,
                        Session.CLIENT_ACKNOWLEDGE);
                MessageConsumer consumer = session.createConsumer(destination);
                consumer.setMessageListener(new OrderMessageListener());
                connection.start();

            } catch (JMSException ex) {
                Logger.getLogger(OrderTableController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NamingException ex) {
            Logger.getLogger(OrderTableController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public class OrderMessageListener implements MessageListener {

        @Override
        public void onMessage(javax.jms.Message _message) {
            try {
                System.out.println("OrderMessageListener");
                OrderHeader oh;
                oh = (OrderHeader) ((ObjectMessage) _message).getObject();
                _message.acknowledge();
                ObservableList<OrderTable> orderTable;
                orderTable = mainApp.getOrderTable();
                for (OrderEntry oe : oh.getOrderEntries()) {
                    orderTable.add(new OrderTable(oh.getCustomer().getCustomerId(),
                            oh.getCustomer().getFirstName(),
                            oh.getCustomer().getLastName(),
                            oh.getOrderId(),
                            oh.getOrderDate(),
                            oh.getSessionId(),
                            oh.getIpAddress(),
                            oe.getName(),
                            oe.getDescription(),
                            oe.getPrice(),
                            oe.getQuantity()
                    ));
                }
            } catch (JMSException ex) {
                ex.printStackTrace();
            }
        }
    }
}
