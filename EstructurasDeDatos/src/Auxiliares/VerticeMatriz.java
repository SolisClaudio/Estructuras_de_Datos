package Auxiliares;
import TDAMapeo.MapeoConHashAbierto;
public class VerticeMatriz<V>  implements Vertex<V> {
	protected V element;
	protected int indice;
	protected Position<VerticeMatriz<V>> posicion;
	
	public VerticeMatriz(V elemento, int in) {
		element=elemento;
		indice = in;
	}
	public V element() {
		return element;
	}
	public void setElement(V elemento) {
		element=elemento;
	}
	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	public Position<VerticeMatriz<V>> getPosicion() {
		return posicion;
	}

	public void setPosicion(Position<VerticeMatriz<V>> posicion) {
		this.posicion = posicion;
	}
	
}
