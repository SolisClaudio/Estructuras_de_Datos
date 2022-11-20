package Auxiliares;

import TDALista.ListaDoblementeEnlazada;
import TDALista.PositionList;
import TDAMapeo.MapeoConHashAbierto;
public class Vertice<V,E> implements Vertex<V> {
	protected V rotulo;
	protected PositionList<Arco<V,E>> listaDeArcos;
	protected Position<Vertice<V,E>> posicion;
	
	public Vertice(V rotulo) {
		this.rotulo=rotulo;
		listaDeArcos  = new ListaDoblementeEnlazada<Arco<V,E>>();
		posicion=null;
	}
	public V element() {
		return rotulo;
	}

	public void setRotulo(V element) {
		this.rotulo = element;
	}

	public PositionList<Arco<V, E>> getListaDeArcos() {
		return listaDeArcos;
	}

	public void setListaDeArcos(PositionList<Arco<V, E>> listaDeArcos) {
		this.listaDeArcos = listaDeArcos;
	}

	public Position<Vertice<V,E>> getPosicion() {
		return posicion;
	}

	public void setPosicion(Position<Vertice<V,E>> posicion) {
		this.posicion = posicion;
	}

}
