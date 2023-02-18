package projectJava.mainJava.control.service;

import projectJava.mainJava.sample.entity.User;
import projectJava.mainJava.sample.table.Table;
import projectJava.mainJava.sample.table.TableProvider;

import java.util.Vector;

public class UserService extends GenericService<User> {
    public UserService() {
        super(TableProvider.TableName.UTENTE);
    }

    /**
     * Metodo per registrare un corriere.
     * @param user il corriere da registrare.
     * @return true se la registrazione è stata effettuata con successo, false altrimenti.
     */
    public boolean registration(User user) {
        if (discoverCourierByEmail(user.getEmail()).isEmpty()) {
            insert(user);
            return true;
        }

        return false;
    }

    /**
     Metodo per scoprire tutti i corrieri con una specifica email.
     @param email l'email dei corrieri da scoprire.
     @return un Vector con tutti i corrieri con quell'email.
     */
    private Vector<User> discoverCourierByEmail(String email) {
        return select(courier -> courier.getEmail().equals(email));
    }


    /**
     * Metodo per effettuare il login di un corriere.
     * @param email l'email del corriere che vuole effettuare il login.
     * @param password la password del corriere che vuole effettuare il login.
     * @return true se l'autenticazione è avvenuta con successo, false altrimenti.
     */
    public User login(String email, String password) {
        Vector<User> users = select(courier -> courier.getEmail().equals(email) && courier.getPassword().equals(password));
        if (users.size() > 0)
            return users.get(0);
        return null;
    }

    /**
     * Converti un oggetto Courier in una stringa.
     * @param user l'oggetto Courier da convertire.
     * @return la stringa creata.
     */
    @Override
    public String fromEntityToString(User user) {
        return user.getID() + Table.delimiter + user.getName() + Table.delimiter + user.getSurname()
                + Table.delimiter + user.getEmail()
                + Table.delimiter + user.getPassword()
                + Table.delimiter + user.isClient();
    }

    /**
     * Converti una stringa in un oggetto Courier.
     * @param s la stringa da convertire.
     * @return l'oggetto Courier creato.
     */
    @Override
    public User toEntityFromString(String s) {
        String[] info = s.split(Table.delimiter);
        return new User(info[0], info[1], info[2], info[3], info[4], Boolean.parseBoolean(info[5]));
    }
}