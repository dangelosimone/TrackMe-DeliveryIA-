package projectJava.mainJava.view.controller;

import javafx.beans.property.SimpleStringProperty;

public class Detail {

    private final SimpleStringProperty code;
    private final SimpleStringProperty vehicle;
    private final SimpleStringProperty weight;
    private final SimpleStringProperty status;

    public Detail(String code, String vehicle, String weight, String status){
        this.code = new SimpleStringProperty(code);
        this.vehicle = new SimpleStringProperty(vehicle);
        this.weight = new SimpleStringProperty(weight);
        this.status = new SimpleStringProperty(status);
    }

    public String getCode() {
        return code.get();
    }

    public SimpleStringProperty codeProperty() {
        return code;
    }

    public String getVehicle() {
        return vehicle.get();
    }

    public SimpleStringProperty vehicleProperty() {
        return vehicle;
    }

    public String getWeight() {
        return weight.get();
    }

    public SimpleStringProperty weightProperty() {
        return weight;
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }
}
