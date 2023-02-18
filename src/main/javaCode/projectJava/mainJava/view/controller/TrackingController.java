package projectJava.mainJava.view.controller;

import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import projectJava.mainJava.MainApplication;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;
import projectJava.mainJava.control.service.PackageService;
import projectJava.mainJava.control.service.util.ServiceProvider;
import projectJava.mainJava.sample.entity.Collo;

import java.util.Vector;

public class TrackingController extends Controller {

    @FXML
    private TextField idTrack;
    @FXML
    private Label errorLabel;
    private Vector<Collo> packs;

    /**
     * Metodo associato ad un Buttom che permette di tracciare il pacco se esiste e nel caso in cui non esistesse gestire con un errorLabel
     */
    @FXML
    public void onTrace() {
        String idT = idTrack.getText();
        PackageService pack = (PackageService) ServiceProvider.getService(Collo.class);
        packs = pack.selectAll();
        if (pack.track(idT)) {
            for (Collo c : packs) {
                errorLabel.setTextFill(Color.GREEN);
                errorLabel.setText(c.getSite());
            }
        } else{
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("Codice Pacco Inesistente");
        }
    }

    @FXML
    public void loadBack(){
        MainApplication.getMainController().showView("/projectJava/mainJava/homeView.fxml");
    }
    @Override
    public void onResume() {}
}
