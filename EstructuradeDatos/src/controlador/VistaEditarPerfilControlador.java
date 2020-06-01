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
public class VistaEditarPerfilControlador implements Initializable {

     private ObservableList<String> comidas = FXCollections.observableArrayList();
    
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
    private Button btnGuardar;
    @FXML
    private RadioButton rbtnHombre;
    @FXML
    private RadioButton rbtnMujer;
    @FXML
    private ChoiceBox<String> cbxComidas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ToggleGroup tg = new ToggleGroup();
        this.rbtnHombre.setToggleGroup(tg);
        this.rbtnMujer.setToggleGroup(tg);
       comidas.add("Comida Italiana");
       comidas.add("Comida China");
       comidas.add("Comida Española");
       comidas.add("Comida Mexicana");
       comidas.add("Comida Francesa");
       comidas.add("Comida Japonesa");
       cbxComidas.setItems(comidas);
        if (Informacion.persona.isSexo()) {
            rbtnHombre.setSelected(true);
        }else{
            rbtnMujer.setSelected(true);
        }
        txtaNombre.setText(Informacion.persona.getNombre());
        txtaApellido.setText(Informacion.persona.getApellido());
        if (Informacion.persona.getComida()==1) {
            cbxComidas.setValue("Comida Italiana");
        }else if (Informacion.persona.getComida()==2) {
            cbxComidas.setValue("Comida China");
        }else if (Informacion.persona.getComida()==3) {
            cbxComidas.setValue("Comida Española");
        }else if (Informacion.persona.getComida()==4) {
            cbxComidas.setValue("Comida Mexicana");
        }else if (Informacion.persona.getComida()==5) {
            cbxComidas.setValue("Comida Francesa");
        }else if (Informacion.persona.getComida()==6) {
            cbxComidas.setValue("Comida Japonesa");
        }
        boolean[] intereses = Informacion.persona.getGustos();
        if (intereses[0]==true) {
            ckbx1.setSelected(true);
        }
        if (intereses[1]==true) {
            ckbx2.setSelected(true);
        }
        if (intereses[2]==true) {
            ckbx3.setSelected(true);
        }
        if (intereses[3]==true) {
            ckbx4.setSelected(true);
        }
        if (intereses[4]==true) {
            ckbx5.setSelected(true);
        }
        if (intereses[5]==true) {
            ckbx6.setSelected(true);
        }
        if (intereses[6]==true) {
            ckbx7.setSelected(true);
        }
        if (intereses[7]==true) {
            ckbx8.setSelected(true);
        }
        if (intereses[8]==true) {
            ckbx9.setSelected(true);
        }
        if (intereses[9]==true) {
            ckbx10.setSelected(true);
        }
        if (intereses[10]==true) {
            ckbx11.setSelected(true);
        }
        if (intereses[11]==true) {
            ckbx12.setSelected(true);
        }
        if (intereses[12]==true) {
            ckbx13.setSelected(true);
        }
        if (intereses[13]==true) {
            ckbx14.setSelected(true);
        }
        if (intereses[14]==true) {
            ckbx15.setSelected(true);
        }
       
    }    
    
    @FXML
    private void ClickGuardar(ActionEvent event) {
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
        }
        if (ckbx2.isSelected()) {
               intereses[1] = true;
        }
        if (ckbx3.isSelected()) {
               intereses[2] = true;
        }
        if (ckbx4.isSelected()) {
               intereses[3] = true;
        }
        if (ckbx5.isSelected()) {
               intereses[4] = true;
        }
        if (ckbx6.isSelected()) {
               intereses[5] = true;
        }
        if (ckbx7.isSelected()) {
               intereses[6] = true;
        }
        if (ckbx8.isSelected()) {
               intereses[7] = true;
        }
        if (ckbx9.isSelected()) {
               intereses[8] = true;
        }
        if (ckbx10.isSelected()) {
               intereses[9] = true;
        }
        if (ckbx11.isSelected()) {
               intereses[10] = true;
        }
        if (ckbx12.isSelected()) {
               intereses[11] = true;
        }
        if (ckbx13.isSelected()) {
               intereses[12] = true;
        }
        if (ckbx14.isSelected()) {
               intereses[13] = true;
        }
        if (ckbx15.isSelected()) {
               intereses[14] = true;
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
        
        Informacion.persona.setApellido(apellido);
        Informacion.persona.setNombre(nombre);
        Informacion.persona.setComida(comidaNum);
        Informacion.persona.setSexo(sexo);
        Informacion.persona.setGustos(intereses);
        
        Informacion.lista.escribir();
        this.closeWindow();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
     public void closeWindow() {
        
        try {
            
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaPerfil.fxml"));
        
        Parent root = loader.load();
        
       
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.show();
        
        Stage myStage = (Stage) this.btnGuardar.getScene().getWindow();
        myStage.close();
        
        
        } catch (IOException e) {
             System.out.println(e.getMessage());
        }
        
    }
    
}
