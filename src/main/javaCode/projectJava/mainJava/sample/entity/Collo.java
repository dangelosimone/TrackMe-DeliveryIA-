package projectJava.mainJava.sample.entity;

public class Collo extends Entity {
    private final Client sender, receiver;
    private final double weight;
    private Vehicle vehicle;
    private String site;


    public Collo(String code, Client sender, Client receiver, double weight) {
        this(code, sender, receiver, weight,null,null);
    }

    public Collo(String code, Client sender, Client receiver, double weight, Vehicle vehicle, String site) {
        super(code);
        this.sender = sender;
        this.receiver = receiver;
        this.weight = weight;
        this.vehicle = vehicle;
        this.site = site;
    }

    public Client getSender() {
        return sender;
    }

    public Client getReceiver() {
        return receiver;
    }

    public double getWeight() {
        return weight;
    }

    public Vehicle getVeicolo() {
        return vehicle;
    }

    public String getSite() {
        return site;
    }

    public void setVeicolo(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setSite(String site){
        this.site = site;
    }

}
