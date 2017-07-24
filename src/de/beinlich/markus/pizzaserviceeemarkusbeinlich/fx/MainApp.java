package de.beinlich.markus.pizzaserviceeemarkusbeinlich.fx;

import de.beinlich.markus.pizzaservice.ejb.OrderTableEjbRemote;
import de.beinlich.markus.pizzaservice.model.OrderEntry;
import de.beinlich.markus.pizzaservice.model.OrderHeader;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class MainApp extends Application {

    private final ObservableList<OrderTable> orderTable = FXCollections.observableArrayList();
    private Stage stage;
    private static final OrderTableEjbRemote orderTableEjb = lookupOrderTableEjbRemote();

    public MainApp() {
//        orderTable.add(new OrderTable(1, "Markus", "Beinlich", 1,
//                LocalDateTime.now(), "4711", "127.0.0.1", "Pizza1",
//                "Salami, Tomaten", new BigDecimal(5), 2));
//        orderTable.add(new OrderTable(2, "Peter", "Müller", 2,
//                LocalDateTime.now(), "4711", "127.0.0.1", "Pizza3",
//                "Salami, Tomaten", new BigDecimal(6), 2));
//        orderTable.add(new OrderTable(1, "Markus", "Beinlich", 1,
//                LocalDateTime.now(), "4711", "127.0.0.1", "Pizza3",
//                "Salami, Tomaten", new BigDecimal(6), 2));
        List<OrderHeader> orders;
        OrderHeader orderHeader = null;
        orders = orderTableEjb.getAllOrderHeader();
        System.out.println("Orders2: " );
        for (OrderHeader oh : orders) {
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
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("/OrderTable.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/OrderTable.fxml"));

        Scene scene = new Scene(loader.load());
//        scene.getStylesheets().add("/styles/Styles.css");

        stage.setTitle("Ordertable");
        stage.setScene(scene);
        stage.show();

        OrderTableController controller = loader.getController();
        controller.setMainApp(this);
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        launch(args);
    }

    public ObservableList<OrderTable> getOrderTable() {
        return orderTable;
    }

    private static OrderTableEjbRemote lookupOrderTableEjbRemote() {
        try {
            Context c = new InitialContext();
            return (OrderTableEjbRemote) c.lookup("ejb/orderTableEjb");
        } catch (NamingException ne) {
            System.out.println("" + ne);
            throw new RuntimeException(ne);
        }
    }
}
