package Auxiliares;

public class Nodo<E> implements Position<E>{
	
	private Nodo<E> siguiente;
	private E element;
	
	public Nodo(Nodo<E> nodo , E e){
		siguiente = nodo;
		element = e ;
	}
	
	public Nodo<E> getSiguiente() {
		return siguiente;
	}
	
	public void setSiguiente(Nodo<E> siguiente) {
		this.siguiente = siguiente;
	}
	
	public E element() {
		return element;
	}
	
	public void setElement(E element) {
		this.element = element;
	}

}
