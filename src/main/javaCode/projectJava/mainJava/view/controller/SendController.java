package projectJava.mainJava.view.controller;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import projectJava.mainJava.MainApplication;
import projectJava.mainJava.control.genetics.Cromosoma;
import projectJava.mainJava.control.genetics.Popolazione;
import projectJava.mainJava.control.service.ClientService;
import projectJava.mainJava.control.service.PackageService;
import projectJava.mainJava.control.service.VehicleService;
import projectJava.mainJava.control.service.util.ServiceProvider;
import projectJava.mainJava.sample.entity.Client;
import projectJava.mainJava.sample.entity.Collo;
import projectJava.mainJava.sample.entity.Vehicle;

import java.util.Collections;
import java.util.HashMap;
import java.util.Vector;

public class SendController extends Controller {
    @FXML
    private ChoiceBox<String> senderChooser, receiverChooser;
    @FXML
    private TextField weightField;
    @FXML
    private Label errorLabel;

    private final double maxWeight = 100.0;
    private final HashMap<String, Client> clientsMap;
    private final PackageService packageService;
    private final VehicleService vehicleService;
    private Vector<Vehicle> vehicles;
    private final Popolazione population;

    public SendController() {
        super();
        clientsMap = new HashMap<>();
        vehicleService = (VehicleService) ServiceProvider.getService(Vehicle.class);
        packageService = (PackageService) ServiceProvider.getService(Collo.class);
        population = new Popolazione(100);
    }

    /**
     * Metodo che inizializza la classe richiamando la funzione
     */
    public void initialize() {
        populateChooser();
        ChangeListener<Number> listener = (observable, oldValue, newValue) -> {
            if (newValue.intValue() == clientsMap.size())

                MainApplication.getMainController().showView("/projectJava/mainJava/clientView.fxml");
        };

        senderChooser.getSelectionModel().selectedIndexProperty().addListener(listener);
        receiverChooser.getSelectionModel().selectedIndexProperty().addListener(listener);
    }

    /**
     * Metodo che gestisce l'invio di un pacco.
     */
    @FXML
    public void send() {
        errorLabel.setTextFill(Color.RED);
        Client sender = clientsMap.get(senderChooser.getValue());
        Client receiver = clientsMap.get(receiverChooser.getValue());
        if (sender == receiver) {
            errorLabel.setText("The receiver cannot match the sender!");
            return;
        } else if (sender == null) {
            errorLabel.setText("Sender is empty");
            return;
        }else if(receiver == null){
            errorLabel.setText("Receiver is empty!");
            return;
        }
        double weight = 0;
        try {
            weight = Double.parseDouble(weightField.getText());
        }catch(NumberFormatException a ){
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("weight must be a numeric value");
            return;
        }
        if(weight <= maxWeight) {
            Collo pack = new Collo(null, sender, receiver, weight);
            packageService.insert(pack);
            launchDialog(pack.getID());
            sendVehicles(pack);
            MainApplication.getMainController().showView("/projectJava/mainJava/choiseView.fxml");
        }else{
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("Max weight 100!");
        }
    }

    /**
     * Metodo privato che invia i veicoli per la consegna dei pacchi
     * @param collo passiamo i colli come argomento
     */
    private void sendVehicles(Collo collo) {
        Vector<Collo> packs = packageService.selectNotSent();
        if (vehicles == null) {
            VehicleService vehicleService = (VehicleService) ServiceProvider.getService(Vehicle.class);
            vehicles = vehicleService.selectAll();
        }
        while(!packs.isEmpty() && !vehicles.isEmpty()) {
            Cromosoma top = null;
            for (Vehicle v : vehicles) {
                Cromosoma solution = population.discoverBestSolutionForSingleVehicle(v, packs, 10);
                System.out.println("Veicolo : " + solution.getVehicle().getType() + " " + solution.getVehicle().getCapacity());
                if (top == null || solution.ratioWeight() > top.ratioWeight()) {
                    top = solution;
                }
            }
            if(top==null) {
                return;
            }
            for (Collo c : top) {
                c.setVeicolo(top.getVehicle());
                top.getVehicle().setCapacity(top.getVehicle().getCapacity()-c.getWeight());
                packageService.update(c);
                vehicleService.update(top.getVehicle());
                packs.remove(c);
                System.out.println("Il collo " + c.getID() + " è stato assegnato al veicolo " + top.getVehicle().getID());
            }
            vehicles.remove(top.getVehicle());
        }
    }


    /**
     * Metodo che mostra una finestra di dialogo con informazioni su un codice di tracciamento.
     * @param trackCode il codice di tracciamento da mostrare nella finestra di dialogo.
     */
    private void launchDialog(String trackCode) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Utilizza il codice " + trackCode + " per tracciare la tua spedizione");
        alert.show();
    }

    @FXML
    public void loadBack(){
        MainApplication.getMainController().showView("/projectJava/mainJava/homeView.fxml");
    }

    /**
     * Metodo per ripopolare il menu a tendina con l'elenco dei clienti.
     */
    @Override
    public void onResume() {
        populateChooser();
    }

    /**
     * Metodo per ottenere tutti i clienti dal database e popolare i menu a tendina.
     */
    private void populateChooser() {
        ClientService clientService = (ClientService) ServiceProvider.getService(Client.class);
        Vector<Client> clients = clientService.selectByUser(LoginController.getLogged());
        Vector<String> names = new Vector<>();

        clientsMap.clear();
        for (Client client : clients) {
            String name = client.getName() + " " + client.getSurname();
            names.add(name);
            clientsMap.put(name, client);
            Collections.sort(names);
        }
        names.add("+ Add Address");
        updateChooser(senderChooser, names);
        updateChooser(receiverChooser, names);
    }

    /**
     * Metodo per aggiornare i menu a tendina con l'elenco dei clienti.
     * @param chooser Il menu a tendina da aggiornare.
     * @param names L'elenco dei nomi dei clienti da inserire nel menu a tendina.
     */
    private void updateChooser(ChoiceBox<String> chooser, Vector<String> names) {
        int selected = chooser.getSelectionModel().getSelectedIndex();
        chooser.setItems(FXCollections.observableList(names));
        if (selected < names.size()-1)
            chooser.getSelectionModel().select(selected);
    }
}
