package projectJava.mainJava.control.service;

import projectJava.mainJava.control.service.util.ServiceProvider;
import projectJava.mainJava.sample.entity.User;
import projectJava.mainJava.sample.entity.util.ClientBuilder;
import projectJava.mainJava.sample.table.Table;
import projectJava.mainJava.sample.table.TableProvider;
import projectJava.mainJava.sample.entity.Client;

import java.util.Vector;

/**
 La classe ClientService estende GenericService[Client]  e fornisce funzionalità specifiche per la gestione dei clienti.
 */
public class ClientService extends GenericService<Client> {

    /**
     Costruttore che inizializza il nome della tabella associata a questo servizio.
     */
    public ClientService() {
        super(TableProvider.TableName.CLIENTS);
    }

    /**
     * Trova il record del cliente corrispondente al parametro passato.
     * @param client il cliente per cui cercare il record.
     * @return il cliente trovato o null se non c'è nessun cliente con i parametri specificati.
     */
    public Client findRecord(Client client) {
        Vector<Client> clients = select(client::equals);
        if (!clients.isEmpty())
            return clients.get(0);
        return null;
    }

    public Vector<Client> selectByUser(User user) {
        return select(client -> client.getUser().equals(user));
    }

    /**
     Converti una stringa in un oggetto Client.
     @param s la stringa da convertire.
     @return l'oggetto Client creato.
     */
    @Override
    public Client toEntityFromString(String s) {
        String[] info = s.split(Table.delimiter);
        ClientBuilder cb = new ClientBuilder();
        cb.setId(info[0]).setName(info[1]).setSurname(info[2])
        .setState(info[3])
        .setCity(info[4])
        .setCap(Integer.parseInt(info[5]))
        .setAddress(info[6])
        .setEmail(info[7])
        .setNumberPhone(Integer.parseInt(info[8]));
        UserService userService = (UserService) ServiceProvider.getService(User.class);
        User user = userService.select(info[9]);
        cb.setUser(user);
        return cb.getClient();
    }

    /**
     Converti un oggetto Client in una stringa.
     @param client l'oggetto Client da convertire.
     @return la stringa creata.
     */
    @Override
    public String fromEntityToString(Client client) {
        return client.getID() + Table.delimiter + client.getName() + Table.delimiter + client.getSurname()
                + Table.delimiter + client.getState() + Table.delimiter + client.getCity()
                + Table.delimiter + client.getCap() + Table.delimiter + client.getAddress()
                + Table.delimiter + client.getEmail() + Table.delimiter + client.getPhone()
                + Table.delimiter + client.getUser().getID();
    }
}
