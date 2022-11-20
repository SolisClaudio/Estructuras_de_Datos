package Auxiliares;
import TDAMapeo.MapeoConHashAbierto;
public class ArcoMatriz<V,E> implements Edge<E>{
	protected Position<ArcoMatriz<V,E>> posicion;
	private VerticeMatriz<V> v1;
	protected VerticeMatriz<V> v2;
	protected E element;
	
	public ArcoMatriz(E elemento , VerticeMatriz<V> vertice1 , VerticeMatriz<V> vertice2 ) {
		v1=vertice1;
		v2=vertice2;
		element=elemento;
		
	}
	public E element() {
		return element;
	}
	public Position<ArcoMatriz<V,E>> getPosicion() {
		return posicion;
	}
	public void setPosicion(Position<ArcoMatriz<V,E>> posicion) {
		this.posicion = posicion;
	}
	public VerticeMatriz<V> getV1() {
		return v1;
	}
	public void setV1(VerticeMatriz<V> v1) {
		this.v1 = v1;
	}
	public VerticeMatriz<V> getV2() {
		return v2;
	}
	public void setV2(VerticeMatriz<V> v2) {
		this.v2 = v2;
	}
	public void setElement(E element) {
		this.element = element;
	}
}
