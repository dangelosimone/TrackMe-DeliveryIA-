package projectJava.mainJava.sample.entity;

public class Client extends Entity {
    private String name, surname;
    private String address, city, state;
    private int cap;

    private int phone;
    private String email;

    private User user;

    private Client(String id, String name, String surname, User user) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.user = user;
    }


    /* Costruttore vuoto nel caso venga istanziato un cliente senza attributi */
    public Client() {
        this(null, null, null,null);
    }

    /**
     * Metodo che definisce se due Client sono uguali
     * @param obj Oggetto di classe Object
     * @return restituisce true se i due Client sono uguali invece invece restituisce false se sono diversi utilizzando equals della classe Object
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Client))
            return false;
        Client client = (Client) obj;
        return client.name.equals(name) && client.surname.equals(surname) && client.address.equals(address)
                && client.city.equals(city) && client.state.equals(state) && client.cap == cap;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCap() {
        return cap;
    }

    public String getCity() {
        return city;
    }

    public void setCap(int cap) {
        this.cap = cap;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public String getEmail() {
        return email;
    }

    public int getPhone() {
        return phone;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumberPhone(int phone) {
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
