package TDALista;

/**
 * Clase Nodo
 * @author Jonathan Alejandro Leonhardt,LU:134726
 */
public class Nodo<E> implements Position<E> {

	private E elem;
	private Nodo<E> next, prev;

	/**
	 * Crea un nodo 
	 * @param p Nodo previo
	 * @param n Nodo siguiente
	 * @param e Elemento del nodo a crear 
	 */
	public Nodo(Nodo<E> p, Nodo<E> n, E e) {
		prev = p;
		next = n;
		elem = e;
	}

	/**
	 * Crea un nodo
	 * @param e Elemento del nodo a crear
	 */
	public Nodo(E e) {
		this(null, null, e);
	}

	/**
	 * Consulta el elemento del nodo
	 * @return Elemento del nodo
	 */
	public E element() {
		return elem;
	}

	/**
	 * Consulta el Nodo previo 
	 * @return Nodo previo
	 */
	public Nodo<E> getPrev() {
		return prev;
	}

	/**
	 * Consulta el Nodo siguiente 
	 * @return Nodo siguiente
	 */
	public Nodo<E> getNext() {
		return next;
	}

	/**
	 * Asigna un elemento a un nodo
	 * @param e Elemento a asignar
	 */
	public void setElemento(E e) {
		elem = e;
	}

	/**
	 * Asigna el nodo siguiente 
	 * @param n Nodo a asignar
	 */
	public void setNext(Nodo<E> n) {
		next = n;
	}

	/**
	 * Asigna el nodo previo
	 * @param p Nodo a asignar
	 */
	public void setPrev(Nodo<E> p) {
		prev = p;
	}

}
