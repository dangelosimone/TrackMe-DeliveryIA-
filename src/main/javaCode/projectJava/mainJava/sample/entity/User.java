package projectJava.mainJava.sample.entity;

public class User extends Entity {
    private final String name, surname, email, password;
    private final boolean client;

    public User(String id, String name, String surname, String email, String password, boolean client) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.client = client;
    }

    public User(String name, String surname, String email, String password, boolean client){
        this(null, name, surname,email,password, client);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User user)
            return user.name.equals(name) && user.surname.equals(surname)
                && user.email.equals(email) && user.password.equals(password)
                && user.client == client;
        return false;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isClient() {
        return client;
    }
}
