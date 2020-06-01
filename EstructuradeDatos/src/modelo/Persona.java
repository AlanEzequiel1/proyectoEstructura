
package modelo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Persona {
	//Atributos 
        public static ArrayList<Integer> apunta= new ArrayList<>();
	private String nombre;
	private String apellido;
	private boolean[] gustos;
	private int comida;
	private boolean sexo;
	
	public Persona() {
		this.nombre = "Nombre";
		this.apellido = "Apellido";
		this.gustos = new boolean[15];
		this.sexo = true;
		this.comida = 0;
	}
	
	public Persona(String nombre, String apellido, boolean sexo, boolean[] gustos, int comida) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.gustos = gustos;
		this.sexo = sexo;
		this.comida = comida;
	}
	
	//Metodos setteres y getters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public boolean[] getGustos() {
		return gustos;
	}

	public void setGustos(boolean[] gustos) {
		this.gustos = gustos;
	}

	public int getComida() {
		return comida;
	}

	public void setComida(int comida) {
		this.comida = comida;
	}

	public boolean isSexo() {
		return sexo;
	}

	public void setSexo(boolean sexo) {
		this.sexo = sexo;
	}


	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", apellido=" + apellido + ", gustos=" + Arrays.toString(gustos)
				+ ", comida=" + comida + ", sexo=" + sexo + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		if (apellido == null) {
			if (other.apellido != null)
				return false;
		} else if (!apellido.equals(other.apellido))
			return false;
		if (comida != other.comida)
			return false;
		if (!Arrays.equals(gustos, other.gustos))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (sexo != other.sexo)
			return false;
		return true;
	}

}