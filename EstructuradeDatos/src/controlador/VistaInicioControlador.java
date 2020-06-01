
package controlador;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modelo.Informacion;
import modelo.ListaPersonas;


public class VistaInicioControlador implements Initializable {
    
    
    @FXML
    private ChoiceBox<String> cbxInicioSesion;
    @FXML
    private Button btnCrearCuenta;
    @FXML
    private Button btnIniciar;

    /**
     * Initializes the controller class.
     */
    @Override
    
    
    public void initialize(URL url, ResourceBundle rb) {
        //Metemos un for que meta a todas las personas dentro del archivo
       cbxInicioSesion.setItems(Informacion.personas);
       btnIniciar.setDisable(true);
       
        
    }    
    @FXML
    private void ClickCrearCuenta(ActionEvent event) {
        try {
            
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaCrearCuenta.fxml"));
        
        Parent root = loader.load();
        
        VistaCrearCuentaControlador controlador = loader.getController();
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.show();
        
        stage.setOnCloseRequest(e -> controlador.closeWindow());
        
        Stage myStage = (Stage) this.btnCrearCuenta.getScene().getWindow();
        myStage.close();
        
        
        } catch (IOException e) {
             System.out.println(e.getMessage());
        }
        
    }
    
    @FXML
    private void ClickIniciar(ActionEvent event) {
        
        String persona = cbxInicioSesion.getValue();
        String nombre="";
        String apellido="";
        int aux =0;
        StringTokenizer st = new StringTokenizer(persona);
         while (st.hasMoreTokens()) {
             if (aux==0) {
                 nombre = st.nextToken();
             }else{
                 apellido = st.nextToken();
             }
            aux++;
         }
         
         Informacion.buscaPersona(nombre, apellido);
        try {
            
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaPerfil.fxml"));
        
        Parent root = loader.load();
        
        VistaPerfilControlador controlador = loader.getController();
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.show();
        
        stage.setOnCloseRequest(e -> controlador.closeWindow());
        
        Stage myStage = (Stage) this.btnIniciar.getScene().getWindow();
        myStage.close();
        
        
        } catch (IOException e) {
             System.out.println(e.getMessage());
        }
        
    }

    @FXML
    private void Accionchoicebox(MouseEvent event) {
        btnIniciar.setDisable(false);
    }



    
    
}
