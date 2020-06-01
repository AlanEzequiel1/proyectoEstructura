package controlador;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modelo.Informacion;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        try {
            Informacion.lista.leer();
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/vista/VistaInicio.fxml"));
            Pane ventana = (Pane) loader.load();
            primaryStage.setTitle("LIGANDO");

            // Show the scene containing the root layout.
            Scene scene = new Scene(ventana);
            
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
