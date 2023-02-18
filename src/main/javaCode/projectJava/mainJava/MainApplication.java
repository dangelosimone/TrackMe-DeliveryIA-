package projectJava.mainJava;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import projectJava.mainJava.view.controller.MainController;

import java.io.File;
import java.io.IOException;

public class MainApplication extends Application {
    private static MainController mainController;

    /**
     Metodo per avviare l'applicazione.
     @param stage lo stage su cui mostrare la vista principale dell'applicazione.
     */
    @Override
    public void start(Stage stage) throws IOException {
        String homeDir = System.getProperty("user.home");
        File txtFile = new File(homeDir + "/.wanted");
        String os = System.getProperty("os.name");
        if(txtFile.exists() && !txtFile.isDirectory()) {
            launchView(stage);
        } else{
            try {
                txtFile.createNewFile();
                if(os.equals("Linux")||os.equals("Mac OS X")) {
                    ProcessBuilder pb = new ProcessBuilder("bash","src/script.sh");
                    Process p = pb.start();
                    launchView(stage);
                }else{
                    launchDialog();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     Metodo per avviare la vista principale dell'applicazione.
     @param stage lo stage su cui mostrare la vista.
     */
    public void launchView(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/projectJava/mainJava/mainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("TrackMe");
        stage.setResizable(false);
        stage.setFullScreen(false);
        stage.setScene(scene);
        stage.show();
    }


    /**
     Metodo principale per avviare l'applicazione.
     @param args eventuali argomenti da passare al metodo principale.
     */
    public static void main(String[] args) {
        launch();
    }


    /**
     * Metodo per caricare una vista.
     * @param path il percorso della vista da caricare.
     * @param <T> il tipo della vista da caricare.
     * @return la vista caricata.
     */
    public static <T> T loadView(String path) {
        try {
            return new FXMLLoader(MainApplication.class.getResource(path)).load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo per ottenere il MainController.
     * @return il MainController corrente.
     */
    public static MainController getMainController() {
        return mainController;
    }

    /**
     * Metodo per impostare il MainController.
     * @param mainController il nuovo MainController da impostare.
     */
    public static void setMainController(MainController mainController) {
        MainApplication.mainController = mainController;
    }

    /**
     * Metodo per mostrare un dialog di informazione all'utente.
     * Mostra un messaggio di errore se il programma non trova la cartella Prog3_DirectoryProject nella home dell'utente
     */
    private void launchDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Copia la cartella ProjectProg3 che si trova nella directory ProgettoProg3 nella home[UTENTE] del PC e riavvia il programma");
        alert.show();
    }
}
