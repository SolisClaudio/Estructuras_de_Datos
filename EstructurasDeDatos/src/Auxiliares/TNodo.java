package Auxiliares;

import TDALista.ListaDoblementeEnlazada;
import TDALista.PositionList;


public class TNodo<E> implements Position<E> {
	private E element;
	private PositionList<TNodo<E>> hijos;
	private TNodo<E> padre;
	
	public TNodo(E element , TNodo<E> padre ) {
		this.element = element ; 
		this.padre = padre;
		hijos = new ListaDoblementeEnlazada<TNodo<E>>();
	}
	@Override
	public E element() {
		return element;
	}
	
	public TNodo<E> getPadre(){
		return padre;
	}
	
	public void setPadre(TNodo<E> p) {
		padre = p;
	}
	
	public void setElemento(E element) {
		this.element = element;
	}
	
	public PositionList<TNodo<E>> getHijos(){
		return hijos;
	}
	

}
