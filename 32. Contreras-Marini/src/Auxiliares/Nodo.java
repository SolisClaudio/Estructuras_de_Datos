package Auxiliares;

/**
 * Clase Nodo<E>
 * @author Nahuel Contreras, Nicolas Marini.
 * Clase que implementa Position<E>.
 */
public class Nodo<E> implements Position<E> {
	private Nodo<E> siguiente;
	private E elemento;
	
	/**
	 * Constructor de la clase Nodo<E>, que inicializa sus atributos principales.
	 * @param E elemento.
	 * @param s Nodo siguiente al actual.
	 */
	public Nodo(E e, Nodo<E> s) {
		elemento = e;
		siguiente = s;
	}
	
	public E element() {return elemento;}
	
	/**
	 * Asigna un nuevo Nodo<E> siguiente al actual.
	 * @param p Nodo<E> siguiente.
	 */
	public void setSiguiente(Nodo<E> p) { siguiente = p;}
	
	/**
	 * Retorna el Nodo<E> siguiente al actual.
	 * @return Nodo<E> siguiente al actual.
	 */
	public Nodo<E> getSiguiente(){ return siguiente;}
	
	/**
	 * Retorna el elemento de Nodo<E>.
	 * @return elemento a retornar.
	 */
	public E getElemento() {return elemento;}
	
	/**
	 * Asigna un nuevo elemento pasado por parametro.
	 * @param e elemento nuevo.
	 */
	public void setElemento(E e) {elemento = e;}
}
