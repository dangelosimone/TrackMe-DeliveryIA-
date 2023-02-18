package projectJava.mainJava.view.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import projectJava.mainJava.control.service.UserService;
import projectJava.mainJava.MainApplication;
import projectJava.mainJava.control.service.util.ServiceProvider;
import projectJava.mainJava.sample.entity.User;
import projectJava.mainJava.sample.entity.Entity;

public class LoginController extends Controller {
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorLabel;

    private static User logged;

    /**
     * Metodo utilizzato per il login del Courier
     */
    @FXML
    protected void onSubmitLogin() {
        String email = emailField.getText();
        String password = passwordField.getText();
        UserService userService = (UserService) ServiceProvider.getService(User.class);
        logged = userService.login(email,password);

        if (logged!=null) {
            errorLabel.setText("");
            if (logged.isClient())
                MainApplication.getMainController().showView("/projectJava/mainJava/homeView.fxml");
            else MainApplication.getMainController().showView("/projectJava/mainJava/choiseCourierView.fxml");
        } else errorLabel.setText("Email o password errati!");
    }

    @FXML
    public void loadBack(){
        MainApplication.getMainController().showView("/projectJava/mainJava/courierMainView.fxml");
    }

    @Override
    public void onResume() {}

    public static User getLogged(){
        return logged;
    }
}