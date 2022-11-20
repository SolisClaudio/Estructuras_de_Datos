package TDAGrafo;
import TDALista.PositionList;
import Excepciones.EmptyQueueException;
import Auxiliares.ArcoMatriz;
import Auxiliares.Edge;
import Auxiliares.Vertex;
import Auxiliares.VerticeMatriz;
import Excepciones.BoundaryViolationException;
import Excepciones.EmptyListException;
import Excepciones.InvalidEdgeException;
import Excepciones.InvalidKeyException;
import Excepciones.InvalidVertexException;
import Position.Position;
import TDACola.ColaEnlazada;
import TDACola.Queue;
import TDALista.ListaDoblementeEnlazada;
import Excepciones.InvalidPositionException;

public class GrafoConMatriz<V,E> implements Graph<V,E> {
	
	protected ArcoMatriz<V,E>[][] matriz;
	protected PositionList<VerticeMatriz<V>> listaVertices;
	protected PositionList<ArcoMatriz<V,E>> listaArcos;
	protected int cantidadDeVertices;
	
	public GrafoConMatriz() {
		matriz = (ArcoMatriz<V,E>[][]) new ArcoMatriz[10][10];
		listaVertices = new ListaDoblementeEnlazada<VerticeMatriz<V>>();
		listaArcos =  new ListaDoblementeEnlazada<ArcoMatriz<V,E>>();
		cantidadDeVertices=0;
	}
	 public Iterable<Vertex<V>> vertices(){
		 PositionList<Vertex<V>> retorno =  new ListaDoblementeEnlazada<Vertex<V>>();
		 for(VerticeMatriz<V> e : listaVertices)
			 retorno.addLast(e);
		 return retorno;
	 }
	 
	 public Iterable<Edge<E>> edges(){
		 PositionList<Edge<E>> retorno = new ListaDoblementeEnlazada<Edge<E>>();
		 for(ArcoMatriz<V,E> e: listaArcos) {
			 retorno.addFirst(e);
		 }
		 return retorno;
	 }
	 
	 public Iterable<Edge<E>> incidentEdges(Vertex<V> v) throws InvalidVertexException{
		 if(v==null) throw new InvalidVertexException("Error, el vertice ingresado es ivnalido");
		 PositionList<Edge<E>> retorno =  new ListaDoblementeEnlazada<Edge<E>>();
		 try {
			 VerticeMatriz<V> vertice = (VerticeMatriz<V>) v;
			 int numeroVertice = vertice.getIndice();
			 for(int i = 0 ; i<cantidadDeVertices ; i++)
				 if(matriz[numeroVertice][i] !=null)
					 retorno.addLast(matriz[numeroVertice][i]);
		 }
		 catch(ClassCastException e) { throw new InvalidVertexException("Error, el vertice pasado por parametro no corresponde a un vertice valido en este grafo");}
		 return retorno;
	 }
	 
	 public  Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws InvalidVertexException , InvalidEdgeException{
		 if(v == null) throw new InvalidVertexException("Error, el nodo ingresado es invalido");
		 if(e==null) throw new InvalidEdgeException("Error, arco invalido");
		 VerticeMatriz<V> retorno=null;
		 try {
			 VerticeMatriz<V> vertice = (VerticeMatriz<V>) v;
			 ArcoMatriz<V,E> arco = (ArcoMatriz<V,E>) e;
			 if(v==arco.getV1())
					 retorno = arco.getV2();
				 else if(v==arco.getV2())
					 retorno = arco.getV1();
				 else throw new InvalidEdgeException("Error, arco ivnalido");
		 }
		 catch(ClassCastException p) { throw new InvalidVertexException("Error"); } 
		 return retorno;
	 }
	 
	 public Vertex<V> [] endvertices(Edge<E> e) throws InvalidEdgeException{
		 if(e==null) throw new InvalidEdgeException("Error, arco invalido");
		 Vertex<V>[] retorno  = (Vertex<V>[]) new Vertex[2];
		 try {
			 ArcoMatriz<V,E> arco = (ArcoMatriz<V,E>) e;
			 retorno[0]=arco.getV1();
			 retorno[1]=arco.getV2();
	 }
	 catch(ClassCastException p) { throw new InvalidEdgeException("Error, arco invalido");}
	 return retorno;
	 }
	 
	 public boolean areAdjacent(Vertex<V> v,Vertex<V> w) throws InvalidVertexException{
		 if(v == null) throw new InvalidVertexException("Error, el nodo ingresado es invalido");
		 if(w == null) throw new InvalidVertexException("Error, el nodo ingresado es invalido");
		 boolean retorno =false;
		 try {
			 VerticeMatriz<V> v1 = (VerticeMatriz<V>) v; 
			 VerticeMatriz<V> v2 = (VerticeMatriz<V>) w;
			 retorno = matriz[v1.getIndice()][v2.getIndice()]!=null;
		 }
		 catch(ClassCastException p) { throw new InvalidVertexException("Error, vertice invalido"); } 
		 return retorno ;
	 }
	 
	 public V replace(Vertex<V> v, V x) throws InvalidVertexException{
		 if(v == null) throw new InvalidVertexException("Error, el nodo ingresado es invalido");
		 V retorno = v.element() ;
		 try {
			 VerticeMatriz<V> vertice = (VerticeMatriz<V>) v;
			 vertice.setElement(x);
		 }
		 catch(ClassCastException p) {throw new InvalidVertexException("Error, vertice invalido"); } 
		 return retorno;
		 
	 }
	 
	 public Vertex<V> insertVertex(V x){
		 if(cantidadDeVertices==matriz.length) extenderMatriz();
		 VerticeMatriz<V> nuevoVertice = new VerticeMatriz<V>( x , cantidadDeVertices++ );
		 try{
			 listaVertices.addLast(nuevoVertice);
		 	 nuevoVertice.setPosicion(listaVertices.last());
		 	 
		 }
		 catch(EmptyListException e) { e.printStackTrace();}
		 return nuevoVertice;
	 }
	 
	 public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e) throws InvalidVertexException{
		 if(v == null) throw new InvalidVertexException("Error, el nodo ingresado es invalido");
		 if(w == null) throw new InvalidVertexException("Error, el nodo ingresado es invalido");
		 ArcoMatriz<V,E> nuevoArco = null;
		 try {
			 VerticeMatriz<V> v1 = (VerticeMatriz<V>) v;
			 VerticeMatriz<V> v2 = (VerticeMatriz<V>) w;
			 nuevoArco = new ArcoMatriz<V,E>(e , v1 , v2);
			 matriz[v1.getIndice()][v2.getIndice()] =nuevoArco;
			 matriz[v2.getIndice()][v1.getIndice()] =nuevoArco;
			 listaArcos.addLast(nuevoArco);
			 nuevoArco.setPosicion(listaArcos.last());
		 }
		 catch(ClassCastException p) { throw new InvalidVertexException("Error, vertice invalido"); }
		 catch(EmptyListException p) {p.printStackTrace();}
		 return nuevoArco ;
	 }
	 
	 public V removeVertex(Vertex<V> v) throws InvalidVertexException {
		 if(v == null) throw new InvalidVertexException("Error, el nodo ingresado es invalido"); 
		 V retorno = v.element();
		 try {
			 VerticeMatriz<V> vertice = (VerticeMatriz<V>) v;
			 Position<VerticeMatriz<V>> puntero;
			 if(vertice.getPosicion()==listaVertices.last())
				 puntero=null;
			 	else puntero = listaVertices.next(vertice.getPosicion());
			 while(puntero!=null) {
				 puntero.element().setIndice(puntero.element().getIndice()-1);
				 if(puntero==listaVertices.last())
					 puntero=null;
				 else puntero = listaVertices.next(puntero);
				 }
			 int i,e;
			 e = vertice.getIndice();
			 for(i = e; i<=cantidadDeVertices ; i++)
				 if(matriz[i][e] != null)
					removeEdge(matriz[i][e]);
			 i=e;
			 while(i<cantidadDeVertices) {
				 while(e<cantidadDeVertices)
					 matriz[i][e]= matriz[i][++e];
				 i++;
				 e=cantidadDeVertices;
			 }
			 vertice.setElement(null);
			 listaVertices.remove(vertice.getPosicion());
			 cantidadDeVertices--;
		 }
		 catch(ClassCastException e) { throw new InvalidVertexException("Error, el vertice ingresado no corresponde a un vertice valido");}
		 catch(InvalidPositionException | BoundaryViolationException | InvalidEdgeException | EmptyListException e) { e.printStackTrace(); } 
		 return retorno;
		 }
	 
	 public E removeEdge(Edge<E> e) throws InvalidEdgeException{
		 E retorno  = e.element();
		 try {
			 ArcoMatriz<V,E> arco = checkEdge(e);
			 int i , l ; 
			 i = arco.getV1().getIndice();
			 l = arco.getV2().getIndice();
			 matriz[i][l] = matriz [l][i] = null;
			 arco.setElement(null);;
			 arco.setV1(null);
			 arco.setV2(null);
			 listaArcos.remove(arco.getPosicion());
		 }
		 catch(InvalidPositionException o) {
			 o.printStackTrace();
			 
		 }
		 return retorno;
	 }
	 
	 private void extenderMatriz() {
		 ArcoMatriz<V,E> [][] nuevaMatriz = (ArcoMatriz<V,E>[][]) new ArcoMatriz[matriz.length*2][matriz.length*2];	 
		 for(int i=0;i<cantidadDeVertices;i++)
			 for(int e=0 ; e<cantidadDeVertices ; e++)
				 nuevaMatriz[i][e]=matriz[i][e];
		 matriz=nuevaMatriz;
	 }
	 public void DFSShearch(){
		 try {   for(VerticeMatriz<V> e : listaVertices)
		        e.put(ESTADO , NO_VISITADO );
		    for(VerticeMatriz<V> e : listaVertices)
		        if(e.get(ESTADO) == NO_VISITADO )
		            DFS(e);
		 }
		 catch (InvalidKeyException e) { e.printStackTrace(); } 
		}

		private void DFS(VerticeMatriz<V> v){
		    System.out.print(v.element());
		    
		    int indice = v.getIndice();
		    VerticeMatriz<V> vertice ; 
		try {
			v.put(ESTADO, VISITADO);
			for (int i = 0 ; i<listaVertices.size() ; i++) {
		        if(matriz[indice][i]!=null){
		            vertice = (VerticeMatriz<V> ) opposite( v , matriz[indice][i]);           
		            if(vertice.get(ESTADO) == NO_VISITADO ) 
		                DFS(vertice);
		        }    
		    }
		}
		catch (InvalidVertexException | InvalidEdgeException | InvalidKeyException e ) { e.printStackTrace(); } 
		}
		public void BFSearch(){
		    try{
		        for(VerticeMatriz<V> v : listaVertices)
		            v.put(ESTADO,NO_VISITADO);
		        for(VerticeMatriz<V> v : listaVertices)
		            if(v.get(ESTADO)==NO_VISITADO)
		                BFS(v);
		    }
		    catch(InvalidKeyException p) { p.printStackTrace();}
		}

		private void BFS(VerticeMatriz<V> v){
		    try{
		        Queue<Vertex<V>> cola = new ColaEnlazada<Vertex<V>>();
		        cola.enqueue(v);
		        v.put(ESTADO , VISITADO);
		        Vertex<V> v1,v2 ; 
		        while(!cola.isEmpty()){
		            v1 = cola.dequeue();
		            System.out.println(v1.element());
		            for(Edge<E> e : incidentEdges(v1)){
		                v2 = opposite(v1,e);
		                if(v2.get(ESTADO)==NO_VISITADO){
		                    v2.put(ESTADO,VISITADO);
		                    cola.enqueue(v2);
		                }
		            }
		        }
		    }
		    catch(InvalidKeyException | EmptyQueueException | InvalidEdgeException | InvalidVertexException p ){ p.printStackTrace();}
		}
	 
		private ArcoMatriz<V,E> checkEdge(Edge<E> arco) throws InvalidEdgeException{
			if(arco == null) throw new InvalidEdgeException("Error, el arco ingresado no es valido");
			ArcoMatriz<V,E> retorno = null;
			try {
				retorno = (ArcoMatriz<V,E>) arco;
			}
			catch(ClassCastException e) {
				throw new InvalidEdgeException("Error, el arco ingresado no es un arco valido para este grafo.");
			}
			return retorno;
		}
	 
	 
}