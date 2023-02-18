package projectJava.mainJava.view.controller;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import projectJava.mainJava.MainApplication;

import java.awt.*;
import java.io.File;

public class ChoiseController extends Controller{
    @Override
    public void onResume(){}

    @FXML
    public void loadCourier(){
        MainApplication.getMainController().showView("/projectJava/mainJava/courierMainView.fxml");
    }

    @FXML
    public void loadClient(){
        MainApplication.getMainController().showView("/projectJava/mainJava/courierMainView.fxml");
    }

    @FXML
    private void launchDialog() {

        try {

            File pdfFile = new File("Relazione&PowerPoint/RAD.pdf");
            if (pdfFile.exists()) {

                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(pdfFile);
                } else {
                    System.out.println("Awt Desktop is not supported!");
                }

            } else {
                System.out.println("File is not exists!");
            }

            System.out.println("Done");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
