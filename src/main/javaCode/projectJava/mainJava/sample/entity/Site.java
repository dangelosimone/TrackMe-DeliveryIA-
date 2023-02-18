package projectJava.mainJava.sample.entity;

public class Site extends Entity{
    private final String name;
    private final String address;
    private final String city;

    //costruttore entity sede
    public Site(String id, String name, String address, String city){
        super(id);
        this.name = name;
        this.address = address;
        this.city = city;
    }

    public Site(String name, String address, String city){
        this(null, name, address, city);
    }

    @Override
    public String getID() {
        return super.getID();
    }

    public String getName(){
        return name;
    }
    public String getAddress(){
        return address;
    }
    public String getCity(){
        return city;
    }
}
