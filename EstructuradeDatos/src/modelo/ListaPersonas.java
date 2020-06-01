/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class ListaPersonas {
	//Atributos
	private int  num;
	private ArrayList<Persona> personas;
	
	public ListaPersonas() {
		this.setPersonas(new ArrayList<Persona>());
		this.setNum(0);
		
	}
	
	public void agregarPersona(Persona persona) {
		if(this.num == 20) {
			 Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null, "Ya has alcanzado el numero maximo de candidatos :( ");
		}else {
			this.getPersonas().add(persona);
                        Informacion.personas.add(persona.getNombre()+" "+persona.getApellido());
			this.num += 1;
		}
	}
	
	
	public String toString() {
		String resp = "";
		for(int i=0; i<getNum(); i++ ) {
			//resp += this.productos[i];
			String var = this.getPersonas().get(i).getNombre() + this.getPersonas().get(i).getApellido();
			resp += var +", ";
			
		}
		return resp;
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
	//Producto prueba = new Producto();
	//prueba.getCopy( Informacion.lista.productos[i]);
	
	public boolean existe(String nombre, String apellido) {
		boolean var=false;
		for(int i=0; i<this.num; i++) {
			if(this.getPersonas().get(i).getNombre().equals(nombre) && this.getPersonas().get(i).getApellido().equals(apellido)) {
				var=true;
				break;
			}else {
				continue;
			}
		}
		return var;
	}
	
	public void eliminar(String nombre, String apellido) {
		
		for(int i=0; i<this.num; i++) {
			
			if(this.getPersonas().get(i).getNombre().equals(nombre) && this.getPersonas().get(i).getApellido().equals(apellido)) {
				this.getPersonas().remove(i);
                                Informacion.personas.remove(Informacion.persona);
				this.num -= 1;
				break;
			}else {
				continue;
			}
		}
	}
	
	//Escribire y leere archivos
	public void escribir() {
		//Esto sobreescribe lo que haya 
		 try{
	            PrintWriter pw=new PrintWriter(new FileWriter(System.getProperty("user.home")+File.separator+"Documents"+File.separator+"Aplicacion.txt"));
	     
	            for(int i=0; i<getNum(); i++ ) {
	            	String var = this.getPersonas().get(i).getNombre();
	            	String var1 = this.getPersonas().get(i).getApellido();
	            	boolean var3 = this.getPersonas().get(i).isSexo();
	            	
	            	String gustos="";
	            	for(int j=0; j<this.getPersonas().get(i).getGustos().length; j++) {
	            		gustos += this.getPersonas().get(i).getGustos()[j] + "," ;
	            	}
	            	String var4 = gustos;
	            	
	            	
	            	int var5 = this.getPersonas().get(i).getComida() ;
	            	pw.println(var + "," +  var1  + "," + var3 + "," + var4 + var5);
	    		}
	            
	            pw.close();
	            //System.out.println("LISTO");
	            
	        }catch(IOException ex){
	            System.out.println("Error: "+ex);
	        }
		}
	
	public void leer() {
		try{
	           BufferedReader bf=new BufferedReader(new FileReader(System.getProperty("user.home")+File.separator+"Documents"+File.separator+"Aplicacion.txt"));
	            String linea;
	            Persona productoprueba;
	            while((linea=bf.readLine())!=null){
	                //System.out.println(linea);
	                String lista[] = linea.split(","); 
	                
	                boolean[] gustos = new boolean[15];
	                
	                for(int i=2; i<15+2;i++) {
	                	gustos[i-2] = Boolean.parseBoolean(lista[i]);
	                	//System.out.println( lista[i] );
	                }
	                

	                
	                productoprueba = new Persona(lista[0], lista[1], Boolean.parseBoolean(lista[2]), gustos, parseInt(lista[18]) );
	                this.agregarPersona(productoprueba);
	                  
	            }

	            /*String linea=bf.readLine();
	            System.out.println(linea);
	            System.out.println(bf.readLine());*/
	            
	        }catch(FileNotFoundException ex){
	        	
	        	JOptionPane.showMessageDialog(null, "No se encontro el archivo \nPor defecto se creara un archivo en tus documentos llamado \"Aplicacion.txt\"  ");
	        	this.escribir();
	            
	        }catch(IOException ex){
	        	
	        	JOptionPane.showMessageDialog(null, "No se pudo abrir el archivo");
	        }
	}

	private boolean[] parseBoolean(String[] split) {
		// TODO Auto-generated method stub
		return null;
	}

	private int parseInt(String string) {
		// TODO Auto-generated method stub
		return 0;
	}

	public ArrayList<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(ArrayList<Persona> personas) {
		this.personas = personas;
	}
	
	public static void main(String args[]) {
		ListaPersonas lista = new ListaPersonas();
		lista.leer();
		
		System.out.println( lista.personas.get(0) );
		System.out.println( lista.personas.get(1) );
		
		boolean[] gustos = {true, false, false, false, true, false, false, false, true, 
				false, false, false, true, false, false};
		
		Persona persona0 = new Persona("Paulina", "Montoya", false, gustos, 1 );
		Persona persona1 = new Persona("Jorge", "Salgado", true, gustos, 3 );
		
		lista.agregarPersona(persona0);
		lista.agregarPersona(persona1);
		
		lista.escribir();
	}
}