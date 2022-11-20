package TDAGrafo;

import java.util.Iterator;

import Auxiliares.Arco;
import Auxiliares.Edge;
import Auxiliares.Vertex;
import Auxiliares.Vertice;
import Excepciones.EmptyListException;
import Excepciones.InvalidEdgeException;
import Excepciones.InvalidPositionException;
import Excepciones.InvalidVertexException;
import TDALista.ListaDoblementeEnlazada;
import TDALista.PositionList;

public class GrafoConListaAdyacencias<V,E> implements Graph<V,E> {
	protected PositionList<Vertice<V,E>> listaVertices ; 
	protected PositionList<Arco<V,E>> listaArcos ;
	
	public GrafoConListaAdyacencias() {
		listaVertices =  new ListaDoblementeEnlazada<Vertice<V,E>>();
		listaArcos =  new ListaDoblementeEnlazada<Arco<V,E>>();
		
	}
	
	public Iterable<Vertex<V>> vertices(){
		PositionList<Vertex<V>> retorno = new ListaDoblementeEnlazada<Vertex<V>>();
		for(Vertice<V,E> v : listaVertices)
			retorno.addLast(v);
		return retorno;
		
	}
	
	public Iterable<Edge<E>> edges(){
		PositionList<Edge<E>> retorno = new ListaDoblementeEnlazada<Edge<E>>();
		for(Arco<V,E> e : listaArcos)
			retorno.addLast(e);
		return retorno;
	}
	
	public Iterable<Edge<E>> incidentEdges(Vertex<V> v) throws InvalidVertexException{
		if(v==null) throw new InvalidVertexException("Error, vertice invalido");
		PositionList<Edge<E>> retorno = new ListaDoblementeEnlazada<Edge<E>>();
		try {
			Vertice<V,E> vertice = (Vertice<V,E>) v;
			for(Arco<V,E> e : vertice.getListaDeArcos())
				retorno.addLast(e);
			}
		catch(ClassCastException e) { throw new InvalidVertexException("Error, verticeInvalido");
		}
		return retorno;
	}
	
	public Vertex<V> opposite(Vertex<V> v , Edge<E> e) throws InvalidEdgeException, InvalidVertexException {
		Vertex<V> retorno = null ;
		if(v==null) throw new InvalidVertexException("Error, vertice invalido");
		if(e==null) throw new InvalidEdgeException("Error, arco invalido");
		Vertice<V,E> vertice = null ; 
		Arco<V,E> arco = null;
		try { vertice = (Vertice<V,E>) v;
		}
		catch(ClassCastException p) { 
			throw new InvalidVertexException("Error, vertice invalido");
		}
		try {
			arco = (Arco<V,E>) e;
			}
		catch(ClassCastException p) { 
			throw new InvalidEdgeException("Error, arco invalido");
		}
		if(arco.getV1()==v)
			retorno = arco.getV2();
		else if(arco.getV2() == v)
				retorno = arco.getV1();
			else throw new InvalidEdgeException("Error"); 
		return retorno ;
		}
	
	public boolean areAdjacent(Vertex<V> v , Vertex<V> w) throws InvalidVertexException{
		if(v == null | w == null ) throw new InvalidVertexException("Error, vertice invalido");
		Vertice<V,E> vertice1 = null ; 
		Vertice<V,E> vertice2 = null ;
		boolean retorno = false ;
		try {
			vertice1 = (Vertice<V,E>) v ;
			vertice2 = (Vertice<V,E>) w ;
			Iterator<Arco<V, E>> it = vertice1.getListaDeArcos().iterator();
			while(it.hasNext() && !retorno)
				retorno = vertice2==opposite(vertice1, it.next());
		}
		catch(ClassCastException p) {
			throw new InvalidVertexException("Error, vertices invalidos");
		}
		catch(InvalidEdgeException p) { p.printStackTrace();}
		return retorno;
	}
	
	public Vertex<V>[] endvertices(Edge<E> e) throws InvalidEdgeException{
		if(e==null) throw new InvalidEdgeException("Error, arco invalido");
		Vertex<V>[] retorno = (Vertex<V>[]) new Vertex[2];
		try {
			Arco<V,E> arco = (Arco<V,E>) e;
			retorno[0] = arco.getV1();
			retorno[1] = arco.getV2();
		}
		catch(ClassCastException p) { throw new InvalidEdgeException("Error, arco invalido");
		}
		return retorno ;
	}
	
	public V replace(Vertex<V> v , V x) throws InvalidVertexException{
		if(v == null) throw new InvalidVertexException("Error, vertice invalido");
		V retorno = v.element();
		try {
			Vertice<V,E> vertice = (Vertice<V,E>) v;
			vertice.setRotulo(x);
			}
		catch(ClassCastException p) { throw new InvalidVertexException("Error, vertice invalido");}
		return retorno;
	}
	public Edge<E> insertEdge(Vertex<V> v , Vertex<V> w , E e) throws InvalidVertexException{
		if(v==null | w==null) throw new InvalidVertexException("Error, vertices invalidos");
		Arco<V,E> arco = null ;
		try {
			Vertice<V,E> vertice1 = (Vertice<V,E>) v;
			Vertice<V,E> vertice2 = (Vertice<V,E>) w;
			arco = new Arco<V,E>(vertice1 , vertice2 , e);
			listaArcos.addLast(arco);
			arco.setPosicionListaDeArcos(listaArcos.last());
			vertice1.getListaDeArcos().addLast(arco);
			arco.setPosicionV1(vertice1.getListaDeArcos().last());
			vertice2.getListaDeArcos().addLast(arco);
			arco.setPosicionV2(vertice2.getListaDeArcos().last());
		}
		catch(ClassCastException p) { 
			throw new InvalidVertexException("Error, vertice invalido");
			}
		catch(EmptyListException p) {
			p.printStackTrace();
			}
		return arco;
	}
	public Vertex<V> insertVertex(V x) {
		Vertice<V,E> retorno = new Vertice<V,E>(x);
		listaVertices.addLast(retorno);
		try {
			retorno.setPosicion(listaVertices.last());
		}
		catch(EmptyListException e) { e.printStackTrace(); }
		return retorno;
	}
	public E removeEdge(Edge<E> e) throws InvalidEdgeException{
		if(e==null) throw new InvalidEdgeException("Error, arco invalido");
		E retorno = e.element();
		try {
			Arco<V,E> arco = (Arco<V,E>) e;
			listaArcos.remove(arco.getPosicionListaDeArcos());
			arco.getV1().getListaDeArcos().remove(arco.getPosicionV1());
			arco.getV2().getListaDeArcos().remove(arco.getPosicionV2());
			arco.setPosicionListaDeArcos(null);
			arco.setPosicionV1(null);
			arco.setPosicionV2(null);;
			arco.setRotulo(null);
			arco.setV1(null);;
			arco.setV2(null);
			
		}
		catch(InvalidPositionException p) {p.printStackTrace();}
		catch(ClassCastException p) { throw new InvalidEdgeException("Error, arco invalido");}
		return retorno;
	}
	
	public V removeVertex(Vertex<V> v) throws InvalidVertexException{
		if(v==null) throw new InvalidVertexException("Error, vertice nulo");
		V retorno = v.element();
		try {
			Vertice<V,E> vertice = (Vertice<V,E>) v;
			for(Arco<V,E> e : vertice.getListaDeArcos())
				removeEdge(e);
			listaVertices.remove(vertice.getPosicion());
			vertice.setListaDeArcos(null);
			vertice.setPosicion(null);;
			vertice.setRotulo(null);
		}
		catch(ClassCastException p) { throw new InvalidVertexException("Error, vertice invalido"); }
		catch(InvalidPositionException| InvalidEdgeException p) {p.printStackTrace(); } 
		return retorno;
	}
	
	
	

}
