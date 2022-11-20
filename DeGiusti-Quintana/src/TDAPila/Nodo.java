package TDAPila;
/**
 * Clase Nodo, representa el nodo de una Pila. Implementa la interfaz Position.
 * @author  Quintana Alejo Joaquín - De Giusti Camila.
 *
 * @param <E>
 */
public class Nodo<E> implements Position<E> {
	private E element;
	private Nodo<E> siguiente;
	
	/**
	 * Crea una instancia de Nodo.
	 * @param e elemento.
	 * @param sig nodo siguiente.
	 */
	public Nodo(E e, Nodo<E> sig) {
		element = e;
		siguiente = sig;
	}
	/**
	 * Constructor de la clase Nodo.
	 * @param e elemento.
	 */
	public Nodo(E e) {
		element = e;
		siguiente = null;
	}
	/**
	 * Consulta el elemento de una posición.
	 * @return el elemento de una posición.
	 */
	public E element() {
		return element;
	}
	/**
	 * Consulta el nodo siguiente a un nodo.
	 * @return el nodo siguiente.
	 */
	public Nodo<E> getSiguiente(){
		return siguiente;
	}
	/**
	 * Modifica el nodo siguiente.
	 * @param e Nodo.
	 */
	public void setSiguiente(Nodo<E> e) {
		siguiente = e;
	}
	/**
	 * Modifica el elemento.
	 * @param e elemento.
	 */
	public void setElement(E e) {
		element = e;
	}
}