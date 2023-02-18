package projectJava.mainJava.sample.entity.util;

import projectJava.mainJava.sample.entity.Client;
import projectJava.mainJava.sample.entity.User;

public class ClientBuilder {
    private final Client client;

    /**
     * Costruttore della classe ClientBuilder
     */
    public ClientBuilder() {
        client = new Client();
    }

    public ClientBuilder setName(String name) {
        client.setName(name);
        return this;
    }

    public ClientBuilder setId(String id){
        client.setID(id);
        return this;
    }


    public ClientBuilder setSurname(String surname) {
        client.setSurname(surname);
        return this;
    }

    public ClientBuilder setState(String state) {
        client.setState(state);
        return this;
    }

    public ClientBuilder setCity(String city) {
        client.setCity(city);
        return this;
    }

    public ClientBuilder setCap(int cap) {
        client.setCap(cap);
        return this;
    }

    public ClientBuilder setAddress(String indirizzo) {
        client.setAddress(indirizzo);
        return this;
    }

    public ClientBuilder setEmail(String email) {
        client.setEmail(email);
        return this;
    }

    public ClientBuilder setNumberPhone(int phone) {
        client.setNumberPhone(phone);
        return this;
    }

    public ClientBuilder setUser(User user){
        client.setUser(user);
        return this;
    }

    public Client getClient() {
        return client;
    }
}
