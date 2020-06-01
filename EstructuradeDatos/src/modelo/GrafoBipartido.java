/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.*;

public class GrafoBipartido {

	private ArrayList<ArrayList<Borde>> grafo;		
	private ArrayList<String> obtenerIndice;	
	private int contVertice;		

	private Borde[] bordeA;
	private boolean[] verticeMarcado;	
	private int flow;


	public GrafoBipartido(int contVertice, ArrayList<String> obtenerIndice){
		this.contVertice = contVertice;
		this.obtenerIndice = obtenerIndice;

		grafo = new ArrayList<>(contVertice);		
		for(int i=0; i<contVertice; ++i){
			grafo.add(new ArrayList<>());
		}
	}

	public void agregarBorde(int delVertice, int alVertice){
		Borde nuevoBorde = new Borde(delVertice, alVertice);	
		grafo.get(delVertice).add(nuevoBorde);		
		grafo.get(alVertice).add(nuevoBorde);
	}


	public void conectarFuenteConMitadIzq(int fuente, int[] verticesIzquierdosMitad){
		for(int IndiceVertice : verticesIzquierdosMitad){
			this.agregarBorde(fuente, IndiceVertice);
		}
	}

	public void conectarSinkConDerechaMitad(int sink, int[] verticesDerechoMitad){
		for(int IndiceVertice : verticesDerechoMitad){
			this.agregarBorde(IndiceVertice, sink);
		}
	}
	
	public ArrayList<String> encontrarMaxFlow(int fuente, int sink){
		bordeA = new Borde[contVertice];
		ArrayList<String> resp = new ArrayList<String>();
		while(existeCamino(fuente, sink)){
			int aumentarFlow = 1;	
			
			String temp = "Matched encontrado:  "+(obtenerIndice.get( bordeA[sink].getOtroNodoFinal(sink) ) )+" & ";
			System.out.print("Matched encontrado  "+(obtenerIndice.get( bordeA[sink].getOtroNodoFinal(sink) ) )+" & ");	
			
			for(int i=sink; i!=fuente; i=bordeA[i].getOtroNodoFinal(i)){
				if(bordeA[i].getOtroNodoFinal(i)==fuente){
					
					temp+=obtenerIndice.get(i);
					System.out.println(obtenerIndice.get(i));
					
				}
				aumentarFlow = Math.min(aumentarFlow, bordeA[i].capacidadResidualA(i));
			}
			
			for(int i=sink; i!=fuente; i=bordeA[i].getOtroNodoFinal(i)){ 
				bordeA[i].aumentarFlow(i, aumentarFlow);
			}
			flow+=aumentarFlow;
		}
		System.out.println("\nPares Maximos Encontrados = "+flow);
		return resp;
	}
	
	public boolean existeCamino(int fuente, int sink){
		verticeMarcado = new boolean[contVertice];		
		verticeMarcado[fuente] = true;		

		primeraBusqueda(fuente, sink);	

		return verticeMarcado[sink];	//if it reached the sink, then a path was found
	}
	
	public void primeraBusqueda(int v, int sink){
		if(v==sink){	
			return;
		}
		
		for(Borde borde : grafo.get(v)){
			int otroNodoFinal = borde.getOtroNodoFinal(v);
			if(!verticeMarcado[otroNodoFinal] && borde.capacidadResidualA(otroNodoFinal)>0 ){
				
				bordeA[otroNodoFinal] = borde;	
				verticeMarcado[otroNodoFinal] = true;		
				primeraBusqueda(otroNodoFinal, sink);	
			}
		}
	}

	class Borde{
		private static final int capacidadBorde = 1;
		private int delVertice;		
		private int alVertice;
		private int capacidad;	
		private int flow;

		public Borde(int delVertice, int alVertice){
			this(delVertice, alVertice, capacidadBorde);
		}

		public Borde(int delVertice, int alVertice, int capacidad){
			this.delVertice = delVertice;
			this.alVertice = alVertice;
			this.capacidad = capacidad;
		}
		
		public int getOtroNodoFinal(int vertice){
			if(vertice==delVertice){
				return alVertice;
			}
			return delVertice;
		}
		
		public int getCapacidad(){
			return capacidad;
		}
		
		public int getFlow(){
			return flow;
		}
		
		public int capacidadResidualA(int vertice){
			if(vertice==delVertice){
				return flow;
			}
			return (capacidad-flow);
		}
		
		public void aumentarFlow(int vertice, int cambioFlow){
			if(vertice==delVertice){
				flow = flow-cambioFlow;
			}
			else{
				flow = flow+cambioFlow;
			}
		}
		
		@Override
		public String toString(){
			return "(" + delVertice+" --> "+alVertice + ")";
		}
	}

	public static void main(String[] args){
		int contVertice = 10;
		int verticeFuenteSink = contVertice +2;
		ArrayList<String> nombres = new ArrayList<String>(Arrays.asList("Jorge", "Hector", "Paco", "Miguel", "Alan", "Pau", "Melinda", "Sofia", "Luisa", "Daniela"));
		nombres.add("Hombres");
		nombres.add("Mujeres");

		int fuente = contVertice;	
		int sink = contVertice+1;

		
		int[] verticesIzq ={0, 1, 2, 3, 4};
		int[] verticesDer = {5, 6, 7, 8, 9};	

		GrafoBipartido graph1BipartiteMatcher = new GrafoBipartido(verticeFuenteSink, nombres);
		graph1BipartiteMatcher.agregarBorde(0, 5);
		graph1BipartiteMatcher.agregarBorde(0, 6);
		graph1BipartiteMatcher.agregarBorde(1, 6);
		graph1BipartiteMatcher.agregarBorde(2, 5);
		graph1BipartiteMatcher.agregarBorde(2, 7);
		graph1BipartiteMatcher.agregarBorde(2, 8);
		graph1BipartiteMatcher.agregarBorde(3, 6);
		graph1BipartiteMatcher.agregarBorde(3, 9);
		graph1BipartiteMatcher.agregarBorde(4, 6);
		graph1BipartiteMatcher.agregarBorde(4, 9);

		graph1BipartiteMatcher.conectarFuenteConMitadIzq(fuente, verticesIzq);
		graph1BipartiteMatcher.conectarSinkConDerechaMitad(sink, verticesDer);

		System.out.println("Bipartite Matching");
		graph1BipartiteMatcher.encontrarMaxFlow(fuente, sink);
		System.out.println("\n");
		
		
		int contVertice1 = 11;
		int verticeFuenteSink1 = contVertice1 +2;

		ArrayList<String> nombres1 = new ArrayList<String>(Arrays.asList("Jorge", "Paco", "Alan", "Samuel", "Arnold", "Tony", "Ximena", "Paulina", "Daniela", "Melissa", "Renata"));
		nombres1.add("Hombres");	
		nombres1.add("Mujeres");

		int fuente1 = contVertice1;	
		int sink1 = contVertice1+1;

		int[] leftHalfVertices2 ={0, 1, 2, 3, 4, 5};
		int[] rightHalfVertices2 = {6, 7, 8, 9, 10};	

		GrafoBipartido graph2BipartiteMatcher = new GrafoBipartido(verticeFuenteSink1, nombres1);
		graph2BipartiteMatcher.agregarBorde(0, 7);
		graph2BipartiteMatcher.agregarBorde(1, 6);
		graph2BipartiteMatcher.agregarBorde(2, 7);
		graph2BipartiteMatcher.agregarBorde(3, 6);
		graph2BipartiteMatcher.agregarBorde(3, 8);
		graph2BipartiteMatcher.agregarBorde(3, 9);
		graph2BipartiteMatcher.agregarBorde(4, 7);
		graph2BipartiteMatcher.agregarBorde(4, 9);
		graph2BipartiteMatcher.agregarBorde(5, 8);
		graph2BipartiteMatcher.agregarBorde(5, 10);

		graph2BipartiteMatcher.conectarFuenteConMitadIzq(fuente1, leftHalfVertices2);
		graph2BipartiteMatcher.conectarSinkConDerechaMitad(sink1, rightHalfVertices2);

		System.out.println("Bipartite Matching");
		graph2BipartiteMatcher.encontrarMaxFlow(fuente1, sink1);

	}
}