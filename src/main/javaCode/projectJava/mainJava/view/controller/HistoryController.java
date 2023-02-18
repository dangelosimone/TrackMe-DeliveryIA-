package projectJava.mainJava.view.controller;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import projectJava.mainJava.MainApplication;
import projectJava.mainJava.control.service.PackageService;
import projectJava.mainJava.control.service.util.ServiceProvider;
import projectJava.mainJava.sample.entity.Collo;

import java.util.Vector;

public class HistoryController extends Controller{

    private PackageService packageService;
    private Vector<Collo> packs;

    @FXML
    private TableView<Detail> tableView;
    @FXML
    private TableColumn<Detail,String> trackingCode;
    @FXML
    private TableColumn<Detail,String> vehicle;
    @FXML
    private TableColumn<Detail,String> weight;
    @FXML
    private TableColumn<Detail,String> status;
    @FXML
    public void loadBack(){
        MainApplication.getMainController().showView("/projectJava/mainJava/choiseCourierView.fxml");
    }

    /**
     * Metodo che inizializza la tabella mostrando i dati del pacco.
     * Utilizzando i metodi forniti dalla classe {@link PackageService} per recuperare i dati dal database.
     */
    public void initialize(){
        packageService = (PackageService) ServiceProvider.getService(Collo.class);
        packs = packageService.selectAll();
        Vector<Detail> list = new Vector<>();
        trackingCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        vehicle.setCellValueFactory(new PropertyValueFactory<>("vehicle"));
        weight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        for(Collo c: packs){
            list.add(new Detail(c.getID(),c.getVeicolo().getType() + " con targa: "+ c.getVeicolo().getID(),String.valueOf(c.getWeight()),c.getSite()));
        }
        tableView.setItems(FXCollections.observableList(list));

    }

    @Override
    public void onResume(){}

}
