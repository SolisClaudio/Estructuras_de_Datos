package Auxiliares;

public class Arco<V,E> implements Edge<E> {
	
	
	protected Position<Arco<V,E>> posicionListaDeArcos;
	protected Position<Arco<V,E>> posicionV1;
	protected Position<Arco<V,E>> posicionV2;
	protected Vertice<V,E> v1;
	protected Vertice<V,E> v2;
	protected E rotulo;
	
	
	public Arco(Vertice<V,E> v1, Vertice<V,E> v2, E rotulo) {
		this.v1 = v1 ;
		this.v2 = v2 ; 
		this.rotulo = rotulo ;
	}
	
	public E element() {
		return rotulo;
	}
	
	public void setRotulo(E element) {
		this.rotulo = element;
	}
	
	public Position<Arco<V,E>> getPosicionListaDeArcos() {
		return posicionListaDeArcos;
	}
	
	public void setPosicionListaDeArcos(Position<Arco<V,E>> posicionListaDeArcos) {
		this.posicionListaDeArcos = posicionListaDeArcos;
	}
	
	public Position<Arco<V,E>> getPosicionV1() {
		return posicionV1;
	}
	
	public void setPosicionV1(Position<Arco<V,E>> posicionV1) {
		this.posicionV1 = posicionV1;
	}
	
	public Position<Arco<V,E>> getPosicionV2() {
		return posicionV2;
	}
	
	public void setPosicionV2(Position<Arco<V,E>> posicionV2) {
		this.posicionV2 = posicionV2;
	}
	
	public Vertice<V,E> getV1() {
		return v1;
	}
	
	public void setV1(Vertice<V,E> v1) {
		this.v1 = v1;
	}
	
	public Vertice<V,E> getV2() {
		return v2;
	}
	
	public void setV2(Vertice<V,E> v2) {
		this.v2 = v2;
	}
	
}
