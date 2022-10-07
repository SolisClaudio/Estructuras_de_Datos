package Auxiliares;

public class DNodo<E> implements Position<E> {
	private DNodo<E> anterior, siguiente;
	private E element;
	
	public DNodo(DNodo<E> anterior , DNodo<E> siguiente, E element) {
		this.anterior = anterior ;
		this.siguiente = siguiente ;
		this.element = element ;
	}

	public DNodo<E> getAnterior() {
		return anterior;
	}

	public void setAnterior(DNodo<E> anterior) {
		this.anterior = anterior;
	}

	public DNodo<E> getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(DNodo<E> siguiente) {
		this.siguiente = siguiente;
	}

	public E element() {
		return element;
	}

	public void setElement(E element) {
		this.element = element;
	}
	
	

}
