/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import modelo.GrafoBipartido;
import modelo.Informacion;
import modelo.Persona;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class VistaPerfilControlador implements Initializable {

    private Persona persona;
    @FXML
    private Label labelNombre;
    @FXML
    private Label labelApellido;
    @FXML
    private Button btnBuscarPareja;
    @FXML
    private Button btnEditarPerfil;
    @FXML
    private Button btnBorrarPerfil;
    @FXML
    private Button btnParejasCompatibles;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        labelApellido.setText(Informacion.persona.getApellido());
        labelNombre.setText(Informacion.persona.getNombre());
        
    }    

    @FXML
    private void btnBuscarParejaAccion(ActionEvent event) {
            Informacion.preparar();
            int vertexCount = Informacion.lista.getNum();
            int vertexCountIncludingSourceAndSink = vertexCount +2;
            
            
		ArrayList<String> array = new ArrayList<String>();
		int[] verticesIzq = new int[Informacion.hombres.getNum()];
		int[] verticesDer = new int[Informacion.mujeres.getNum()];

		for(int i=0; i<Informacion.hombres.getNum(); i++) {
			array.add(Informacion.hombres.getPersonas().get(i).getNombre());
			verticesIzq[i] = Informacion.hombres.getPersonas().get(i).getComida();
		}
		for(int i=0; i<Informacion.mujeres.getNum(); i++) {
			array.add(Informacion.mujeres.getPersonas().get(i).getNombre());
			verticesDer[i] = Informacion.mujeres.getPersonas().get(i).getComida();
		}
                
		

		int source = vertexCount;	
		int sink = vertexCount+1;

		

		GrafoBipartido graph1BipartiteMatcher = new GrafoBipartido(vertexCountIncludingSourceAndSink, array);
		for(int i=0; i<Informacion.hombres.getNum(); i++) {
			for(int j=0; j<Informacion.hombres.getPersonas().get(i).apunta.size(); j++) {
				graph1BipartiteMatcher.agregarBorde(i, j);
			}
		}
		
		graph1BipartiteMatcher.conectarFuenteConMitadIzq(source, verticesIzq);
		graph1BipartiteMatcher.conectarSinkConDerechaMitad(sink, verticesDer);

		System.out.println("Running Bipartite Matching on Graph 1");
		ArrayList<String> matches = graph1BipartiteMatcher.encontrarMaxFlow(source, sink);
                for (int i = 0; i < matches.size(); i++) {
                    System.out.println(matches.get(i));  
            
                }
                
    }

    @FXML
    private void btnEPAccion(ActionEvent event) {
        
        try {
            
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaEditarPerfil.fxml"));
        
        Parent root = loader.load();
        
        VistaEditarPerfilControlador controlador = loader.getController();
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.show();
        
        //stage.setOnCloseRequest(e -> controlador.closeWindow());
        
        Stage myStage = (Stage) this.btnEditarPerfil.getScene().getWindow();
        myStage.close();
        
        
        } catch (IOException e) {
             System.out.println(e.getMessage());
        }
        
    }

    @FXML
    private void btnBorrarAccion(ActionEvent event) {
        if (Informacion.lista.existe(Informacion.persona.getNombre(), Informacion.persona.getApellido())) {
             Informacion.lista.eliminar(Informacion.persona.getNombre(),Informacion.persona.getApellido());
             
              try {
            
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaInicio.fxml"));

                    Parent root = loader.load();



                    Scene scene = new Scene(root);
                    Stage stage = new Stage();

                    stage.setScene(scene);
                    stage.show();

                    Stage myStage = (Stage) this.btnBorrarPerfil.getScene().getWindow();
                    myStage.close();


                } catch (IOException e) {
                     System.out.println(e.getMessage());
                }
            Informacion.lista.escribir();
            Informacion.lista.leer();
        }
    }

    @FXML
   
    
     public void closeWindow() {
        
        try {
            
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaInicio.fxml"));
        
        Parent root = loader.load();
        
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.show();
        
        } catch (IOException e) {
             System.out.println(e.getMessage());
        }
        
    }

   
    
}
