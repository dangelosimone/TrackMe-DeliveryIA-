package projectJava.mainJava.view.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import projectJava.mainJava.MainApplication;
import projectJava.mainJava.control.service.ClientService;
import projectJava.mainJava.control.service.util.ServiceProvider;
import projectJava.mainJava.sample.entity.Client;
import projectJava.mainJava.sample.entity.util.ClientBuilder;

public class ClientController extends Controller {
    @FXML
    private TextField nameField, surnameField;
    @FXML
    private TextField countryField, townField, CAPField, addressField;
    @FXML
    private TextField emailField, phoneField;

    @FXML
    private Label errorLabel;

    private int num;
    /**
     * Metodo invocato quando viene premuto il tasto per aggiungere il nuovo indirizzo
     */
    @FXML
    public void addAddress() {
        ClientBuilder builder = new ClientBuilder();
        builder.setName(nameField.getText());
        builder.setSurname(surnameField.getText());
        builder.setState(countryField.getText());
        builder.setCity(townField.getText());
        builder.setAddress(addressField.getText());
        builder.setEmail(emailField.getText());
        builder.setUser(LoginController.getLogged());

        try{
            num = Integer.parseInt(phoneField.getText());
            builder.setNumberPhone(num);
        }catch(NumberFormatException a){
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("Phone Number must be a numeric value");
            return;
        }
        try{
            num = Integer.parseInt(CAPField.getText());
            builder.setCap(num);
        }catch(NumberFormatException a){
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("CAP must be a numeric value");
            return;
        }

        ClientService service = (ClientService) ServiceProvider.getService(Client.class);
        if (service.findRecord(builder.getClient()) == null)
            service.insert(builder.getClient());

        MainApplication.getMainController().navigateBack();
    }

    @Override
    public void onResume() {}

    @FXML
    public void loadBack(){
        MainApplication.getMainController().showView("/projectJava/mainJava/sendView.fxml");
    }

}
