package projectJava.mainJava.sample.entity;

public class Vehicle extends Entity {
    private final String type;
    private double capacity;

    public Vehicle(String codice, String type, double capacity) {
        super(codice);
        this.type = type;
        this.capacity = capacity;
    }

    public Vehicle(String type, double capacity){
        this(null, type, capacity);
    }

    public String getType() {
        return type;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity){
        this.capacity = capacity;
    }
}
