package TDALista;
/**
 * Clase DNodo, representa un nodo de una Lista, implementa la interfaz Position.
 * @author  De Giusti Camila - Quintana Alejo Joaquin.
 *
 * @param <E>
 */
public class DNodo<E> implements Position<E>{
	private E element;
	private DNodo<E> anterior;
	private DNodo<E> siguiente;
	
	/**
	 * Crea una instancia de DNodo.
	 * @param e elemento.
	 * @param ant DNodo anterior.
	 * @param sig DNodo siguiente.
	 */
	public DNodo(E e, DNodo<E> ant, DNodo<E> sig) {
		element = e;
		anterior = ant;
		siguiente = sig;
	}
	/**
	 * Modifica el valor del Dnodo siguiente.
	 * @param sig Dnodo siguiente.
	 */
	public void setSiguiente(DNodo<E> sig) {
		siguiente = sig;
	}
	/**
	 * Modifica el valor del DNodo anterior.
	 * @param ant DNodo anterior.
	 */
	public void setAnterior(DNodo<E> ant) {
		anterior = ant;
	}
	/**
	 * Modifica el valor del elemento.
	 * @param e elemento.
	 */
	public void setElement(E e) {
		element = e;
	}
	/**
	 * Devuelve un elemento de una posición.
	 * @return el elemento de una posición.
	 */
	public E element() {
		return element;
	}
	/**
	 * Devuelve el DNodo siguiente.
	 * @return siguiente.
	 */
	public DNodo<E> getSiguiente(){
		return siguiente;
	}
	/**
	 * Devuelve el DNodo anterior.
	 * @return anterior.
	 */
	public DNodo<E> getAnterior(){
		return anterior;
	}
}