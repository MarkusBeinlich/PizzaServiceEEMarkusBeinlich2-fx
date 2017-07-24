/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.beinlich.markus.pizzaserviceeemarkusbeinlich.fx;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Markus
 */
public class OrderTable {

    public final IntegerProperty customerId;
    public final StringProperty firstName;
    public final StringProperty lastName;
    public final IntegerProperty orderId;
    public final ObjectProperty<LocalDateTime> orderDate;
    public final StringProperty sessionId;
    public final StringProperty ipAddress;
    public final StringProperty name;
    public final StringProperty description;
    public final ObjectProperty<BigDecimal> price;
    public final IntegerProperty quantity;

    public OrderTable() {
        this(null, null, null, null, null, null, null, null, null, null, null);
    }

    public OrderTable(Integer customerId, String firstName, String lastName, Integer orderId,
            LocalDateTime orderDate, String sessionId, String ipAddress, String name,
            String description, BigDecimal price, Integer quantity) {
        this.customerId = new SimpleIntegerProperty(customerId);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        if (orderId == null) {
            orderId = -1;
        }
        this.orderId = new SimpleIntegerProperty(orderId);
        this.orderDate = new SimpleObjectProperty<>(orderDate);
        this.sessionId = new SimpleStringProperty(sessionId);
        this.ipAddress = new SimpleStringProperty(ipAddress);
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.price = new SimpleObjectProperty<>(price);
        this.quantity = new SimpleIntegerProperty(quantity);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String value) {
        firstName.set(value);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String value) {
        lastName.set(value);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String value) {
        description.set(value);
    }

    public StringProperty DescriptionProperty() {
        return description;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String value) {
        name.set(value);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getIpAddress() {
        return ipAddress.get();
    }

    public void setIpAddress(String value) {
        ipAddress.set(value);
    }

    public StringProperty ipAddressProperty() {
        return ipAddress;
    }

    public String getSessionId() {
        return sessionId.get();
    }

    public void setSessionId(String value) {
        sessionId.set(value);
    }

    public StringProperty sessionIdProperty() {
        return sessionId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate.get();
    }

    public void setOrderDate(LocalDateTime value) {
        orderDate.set(value);
    }

    public ObjectProperty<LocalDateTime> orderDateProperty() {
        return orderDate;
    }

    public BigDecimal getPrice() {
        return price.get();
    }

    public void setPrice(BigDecimal value) {
        price.set(value);
    }

    public ObjectProperty<BigDecimal> priceProperty() {
        return price;
    }

    public int getCustomerId() {
        return customerId.get();
    }

    public void setCustomerId(int value) {
        customerId.set(value);
    }

    public IntegerProperty customerIdProperty() {
        return customerId;
    }

    public int getOrderId() {
        return orderId.get();
    }

    public void setOrderId(int value) {
        orderId.set(value);
    }

    public IntegerProperty orderIdProperty() {
        return orderId;
    }

    public int getQuantity() {
        return quantity.get();
    }

    public void setQuantity(int value) {
        quantity.set(value);
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }
}
