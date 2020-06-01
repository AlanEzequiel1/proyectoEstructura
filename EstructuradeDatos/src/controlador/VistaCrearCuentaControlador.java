/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import modelo.Informacion;
import modelo.Persona;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class VistaCrearCuentaControlador implements Initializable {
    
    private ObservableList<String> comidas = FXCollections.observableArrayList();
    private Persona persona;
    @FXML
    private TextArea txtaNombre;
    @FXML
    private TextArea txtaApellido;
    
    @FXML
    private CheckBox ckbx1,
                    ckbx2,
                    ckbx3,
                    ckbx4,
                    ckbx7,
                    ckbx5,
                    ckbx8,
                    ckbx6,
                    ckbx9,
                    ckbx10,
                    ckbx11,
                    ckbx12,
                    ckbx13,
                    ckbx14,
                    ckbx15;
    
    @FXML
    private Button btnCrearCuenta;
    @FXML
    private RadioButton rbtnHombre;
    @FXML
    private RadioButton rbtnMujer;
    @FXML
    private ChoiceBox<String> cbxComidas;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ToggleGroup tg = new ToggleGroup();
        this.rbtnHombre.setToggleGroup(tg);
        this.rbtnMujer.setToggleGroup(tg);
        rbtnHombre.setSelected(true);
       comidas.add("Comida Italiana");
       comidas.add("Comida China");
       comidas.add("Comida Española");
       comidas.add("Comida Mexicana");
       comidas.add("Comida Francesa");
       comidas.add("Comida Japonesa");
       cbxComidas.setItems(comidas);
       
    }    

   

    public void closeWindow() {
        
        try {
            
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaInicio.fxml"));
        
        Parent root = loader.load();
        
       
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.show();
        
        Stage myStage = (Stage) this.btnCrearCuenta.getScene().getWindow();
        myStage.close();
        
        
        } catch (IOException e) {
             System.out.println(e.getMessage());
        }
        
    }

    @FXML
    private void ClickCrearCuenta(ActionEvent event) {
        try {
            
        String nombre = txtaNombre.getText();
        String apellido = txtaApellido.getText();
        boolean sexo;
        if (rbtnHombre.isSelected()) {
             sexo = true;
        }else{
             sexo=false;
        }
        boolean[] intereses = new boolean[15];
        
       
        if (ckbx1.isSelected()) {
               intereses[0] = true;
        }else{
            intereses[0] = false;
        }
        if (ckbx2.isSelected()) {
               intereses[1] = true;
        }else{
            intereses[1] = false;
        }
        if (ckbx3.isSelected()) {
               intereses[2] = true;
        }else{
            intereses[2] = false;
        }
        if (ckbx4.isSelected()) {
               intereses[3] = true;
        }else{
            intereses[3] = false;
        }
        if (ckbx5.isSelected()) {
               intereses[4] = true;
        }else{
            intereses[4] = false;
        }
        if (ckbx6.isSelected()) {
               intereses[5] = true;
        }else{
            intereses[5] = false;
        }
        if (ckbx7.isSelected()) {
               intereses[6] = true;
        }else{
            intereses[6] = false;
        }
        if (ckbx8.isSelected()) {
               intereses[7] = true;
        }else{
            intereses[7] = false;
        }
        if (ckbx9.isSelected()) {
               intereses[8] = true;
        }else{
            intereses[8] = false;
        }
        if (ckbx10.isSelected()) {
               intereses[9] = true;
        }else{
            intereses[9] = false;
        }
        if (ckbx11.isSelected()) {
               intereses[10] = true;
        }else{
            intereses[10] = false;
        }
        if (ckbx12.isSelected()) {
               intereses[11] = true;
        }else{
            intereses[11] = false;
        }
        if (ckbx13.isSelected()) {
               intereses[12] = true;
        }else{
            intereses[12] = false;
        }
        if (ckbx14.isSelected()) {
               intereses[13] = true;
        }else{
            intereses[13] = false;
        }
        if (ckbx15.isSelected()) {
               intereses[14] = true;
        }else{
            intereses[14] = false;
        }
        
        String comida = cbxComidas.getValue();
        int comidaNum;
        if (comida.equals("Comida Italiana")) {
            comidaNum = 1;
        }else if (comida.equals("Comida China")) {
            comidaNum = 2;
        }else if (comida.equals("Comida Española")) {
            comidaNum = 3;
        }else if (comida.equals("Comida Mexicana")) {
            comidaNum = 4;
        }else if (comida.equals("Comida Francesa")) {
            comidaNum = 5;
        }else {
            comidaNum = 6;
        }
        persona = new Persona(nombre, apellido, sexo, intereses, comidaNum);
        Informacion.lista.agregarPersona(persona);
        Informacion.lista.escribir();
        this.closeWindow();
        } catch (Exception e) {
            
        }
    }


}
