package Auxiliares;
import Exceptions.InvalidPositionException;

/**
 * Clase DNodo<E>
 * @author Nahuel Contreras, Nicolas Marini.
 * Clase que implementa Position<E>.
 */
public class DNodo<E> implements Position<E> {
	
	private E elemento;
	private DNodo<E> previo;
	private DNodo<E> siguiente;
	
	/**
	 * Constructor de la clase DNodo<E>, que inicializa su atributo principal.
	 * @param e elemento;
	 */
	public DNodo(E e) {
		elemento = e;
	}
	
	/**
	 * Asigna DNodo<E> anterior al actual.
	 * @param parametro DNodo<E> anterior al actual;
	 */
	public void setPrev(DNodo<E> parametro){
		previo = parametro;
	}
	
	/**
	 * Asigna DNodo<E> siguiente al actual.
	 * @param parametro DNodo<E> siguiente al actual;
	 */
	public void setNext(DNodo<E> parametro){
		siguiente = parametro;
	}
	
	/**
	 * Retorna DNodo<E> anterior al actual.
	 * @return Dnodo<E> anterior.
	 */
	public DNodo<E> getPrev(){
		return previo;
	}
	
	/**
	 * Retorna DNodo<E> siguiente al actual.
	 * @return Dnodo<E> siguiente.
	 */
	public DNodo<E> getNext(){
		return siguiente;
	}
	
	/**
	 * Asigna un nuevo elemento al DNodo<E> por parametro.
	 * @param parametro elemento a asignar.
	 */
	public void setElemento(E parametro){
		elemento = parametro;
	}
	
	public E element() {
		return elemento;
	}
	
}
