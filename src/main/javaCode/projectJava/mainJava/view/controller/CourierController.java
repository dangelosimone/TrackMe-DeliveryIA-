package projectJava.mainJava.view.controller;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import projectJava.mainJava.MainApplication;
import projectJava.mainJava.control.genetics.Popolazione;
import projectJava.mainJava.control.service.PackageService;
import projectJava.mainJava.control.service.SedeService;
import projectJava.mainJava.control.service.VehicleService;
import projectJava.mainJava.control.service.util.ServiceProvider;
import projectJava.mainJava.sample.entity.Collo;
import projectJava.mainJava.sample.entity.Site;
import javafx.scene.control.Label;
import projectJava.mainJava.sample.entity.Vehicle;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Vector;
import java.util.Collections;

public class CourierController extends Controller {

    private final SedeService sedeService;
    private final HashMap<String, Site> sedeMaps;
    private final Popolazione popolazione;
    private final PackageService packageService;
    private final VehicleService vehicleService;

    @FXML
    private ChoiceBox<String> senderChooser;

    @FXML
    private TextField textField;

    @FXML
    private Label errorLabel;

    private static String deliveredString;
    private Vector<Collo> packs;

    @Override
    public void onResume() {}

    /**
     Metodo che viene chiamato per tornare alla schermata precedente.
     */
    @FXML
    public void loadBack(){
        MainApplication.getMainController().showView("/projectJava/mainJava/choiseCourierView.fxml");
    }

    public CourierController() {
        super();
        sedeMaps = new HashMap<>();
        packageService = (PackageService) ServiceProvider.getService(Collo.class);
        sedeService = (SedeService) ServiceProvider.getService(Site.class);
        vehicleService = (VehicleService) ServiceProvider.getService(Vehicle.class);
        popolazione = new Popolazione(100);
    }

    /**
     Metodo che gestisce la consegna del pacchetto.
     */
    public void delivered(){
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm").format(new java.util.Date());
        packs = packageService.selectAll();
        for (Collo c : packs) {
            if(c.getID().equals(textField.getText())){
                if(!(c.getSite().equals(c.getReceiver().getAddress()))){
                    c.setSite("Delivered: " + c.getReceiver().getName() + " " + c.getReceiver().getSurname() + ", " + c.getReceiver().getAddress()+ ", " +c.getReceiver().getCity() + " -- Date: " + timeStamp);
                    deliveredString = "Delivered:" + c.getReceiver().getName()+ " " + c.getReceiver().getSurname() + ", " +  c.getReceiver().getAddress()+" , "+c.getReceiver().getCity() + " -- Date: " + timeStamp;
                    packageService.update(c);
                    c.getVeicolo().setCapacity(c.getVeicolo().getCapacity() + c.getWeight());
                    vehicleService.update(c.getVeicolo());
                }
            }
        }
        MainApplication.getMainController().showView("/projectJava/mainJava/courierMainView.fxml");
    }

    /**
     * Metodo che inizializza la classe. Viene popolato il ChoiceBox con le sedi disponibili.
     * Viene anche impostato un listener sul ChoiceBox, che fa in modo che se l'utente seleziona l'ultima voce del ChoiceBox, viene chiamato il metodo delivered.
     */
    public void initialize() {
        populateChooser();
        ChangeListener<Number> listener = (observable, oldValue, newValue) -> {
            if (newValue.intValue() == sedeMaps.size())
                delivered();
        };
        senderChooser.getSelectionModel().selectedIndexProperty().addListener(listener);
    }


    /**
     * Metodo che popola il ChoiceBox con le sedi disponibili.
     * Viene utilizzato il SedeService per recuperare tutte le sedi presenti nel sistema e vengono aggiunte alla ChoiceBox.
     */
    private void populateChooser() {
        SedeService sedeService = (SedeService) ServiceProvider.getService(Site.class);
        Vector<Site> sites = sedeService.selectAll();
        Vector<String> name = new Vector<>();
        sedeMaps.clear();
        for (Site site : sites) {
            String nome = site.getName()+ "," + site.getAddress()+"," + site.getCity();
            name.add(nome);
            sedeMaps.put(nome, site);
            Collections.sort(name);
        }
        updateChooser(senderChooser, name);
    }

    /**
     Metodo che aggiorna i valori presenti nella ChoiceBox.
     @param chooser ChoiceBox da aggiornare
     @param names nuovi valori da inserire nella ChoiceBox
     */
    private void updateChooser(ChoiceBox<String> chooser, Vector<String> names) {
        int selected = chooser.getSelectionModel().getSelectedIndex();
        chooser.setItems(FXCollections.observableList(names));
        if (selected < names.size()-1)
            chooser.getSelectionModel().select(selected);
    }

    /**
     * Metodo che consente di aggiornare lo stato del collo.
     */
    @FXML
    public void updateStatus(){
        Site site = sedeMaps.get(senderChooser.getValue());
        packs = packageService.selectAll();
        if(site != null){
            String name_site = site.getName();
            String address_site = site.getAddress();
            String city_site = site.getCity();
            for (Collo c : packs) {
                if(c.getID().equals(textField.getText())&&(!(c.getSite().equals(deliveredString)))){
                    c.setSite(name_site+","+ address_site + "," +city_site);
                    packageService.update(c);
                    deliveredString = "";
                    errorLabel.setText("Nuova Sede associata al Collo");
                }else if(c.getSite().equals(deliveredString)){
                    errorLabel.setTextFill(Color.RED);
                    errorLabel.setText("Pacco gia consegnato");
                    return;
                }
            }
        }
        MainApplication.getMainController().showView("/projectJava/mainJava/courierMainView.fxml");
    }

}

