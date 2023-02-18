package projectJava.mainJava.view.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import projectJava.mainJava.MainApplication;
import projectJava.mainJava.control.service.UserService;
import projectJava.mainJava.control.service.util.ServiceProvider;
import projectJava.mainJava.sample.entity.User;

public class SignupController extends Controller {
    @FXML
    private Label errorLabel;
    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField passConfirmField;
    @FXML
    private CheckBox clientCheckBox;

    /**
     * Metodo utilizzato per la registrazione del Courier con vari controlli
     */
    @FXML
    public void onSubmitSignup() {
        String idCourier = getIDCourier();
        String name = nameField.getText();
        String surname = surnameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String confirmPassword = passConfirmField.getText();
        boolean client = clientCheckBox.isSelected();

        if (name.isEmpty())
            errorLabel.setText("Il campo nome è vuoto!");
        else if (surname.isEmpty())
            errorLabel.setText("Il campo cognome è vuoto!");
        else if (email.isEmpty())
            errorLabel.setText("Il campo email è vuoto!");
        else if (password.isEmpty())
            errorLabel.setText("Il campo password è vuoto!");
        else if (confirmPassword.isEmpty())
            errorLabel.setText("Il campo conferma password è vuoto!");
        else if (!password.equals(confirmPassword))
            errorLabel.setText("Le password non coincidono!");
        else if (password.length() < 8)
            errorLabel.setText("La password è troppo debole!");
        else {
            User user = new User(idCourier, name, surname, email, password, client);
            UserService service = (UserService) ServiceProvider.getService(User.class);
            boolean success = service.registration(user);
            if (success) {
                launchDialog();
                MainApplication.getMainController().showView("/projectJava/mainJava/loginView.fxml");
            } else errorLabel.setText("L'email è già presente nel nostro database!");
        }
    }

    @FXML
    public void loadBack() {
        MainApplication.getMainController().showView("/projectJava/mainJava/courierMainView.fxml");
    }

    @Override
    public void onResume() {
    }

    public String getIDCourier() {

        // choose a Character random from this String
        String language = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(10);

        for (int i = 0; i < 10; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index = (int) (language.length() * Math.random());

            // add Character one by one in end of sb
            sb.append(language.charAt(index));
        }

        return sb.toString();
    }

    private void launchDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Registrazione avvenuta con Successo");
        alert.show();
    }
}


