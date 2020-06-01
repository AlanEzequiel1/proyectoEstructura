
package modelo;


import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Informacion {
	
	public static ListaPersonas lista = new ListaPersonas();
	
        public static ObservableList<String> personas = FXCollections.observableArrayList();

	public static Persona persona = new Persona();
        public static ListaPersonas hombres = new ListaPersonas();
	public static ListaPersonas mujeres = new ListaPersonas();
	
	public static void buscaPersona(String nombre, String apellido) {
		for(int i=0; i<lista.getNum(); i++) {
			if(lista.getPersonas().get(i).getNombre().equals(nombre) && lista.getPersonas().get(i).getApellido().equals(apellido)) {
				persona = lista.getPersonas().get(i);
			}
		}
	}
        
        public static void preparar() {
		//crear hombres y mujeres
		for(int i=0; i<lista.getNum(); i++) {
			if(lista.getPersonas().get(i).isSexo() == true) {
				hombres.agregarPersona(lista.getPersonas().get(i));
			}else {
				mujeres.agregarPersona(lista.getPersonas().get(i));
			}
		}
		 //apuntarlos entre sí
		for(int i=0; i<hombres.getNum(); i++) {
			for(int j=0; i<mujeres.getNum(); i++) {
				if( hallarCoincidencia(hombres.getPersonas().get(i), mujeres.getPersonas().get(j)) ) {
					hombres.getPersonas().get(i).apunta.add(j);
				}
			}
		}
	}
	
	public static boolean hallarCoincidencia(Persona hombre, Persona mujer) {
		int cont = 0;
		boolean resp = false;
		
		for(int i=0; i<hombre.getGustos().length; i++) {
			if( hombre.getGustos()[i] == mujer.getGustos()[i] ) {
				cont++;
			}
		}
		if( hombre.getComida() == mujer.getComida() ) {
			cont++;
		}
		if(cont>=10) {
			resp = true;
		}
		
		return resp;
	}
}