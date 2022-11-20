package TDAPila;
import Exceptions.*;
/**
 * Clase Pila, implementa la Interfaz Stack y utiliza una estructura simplemente enlazada mateniendo referencia al tope de la pila.
 * @author  De Giusti Camila - Quintana Alejo Joaquin.
 *
 * @param <E>
 */
public class PilaConEnlaces<E> implements Stack<E>{
	private Nodo<E> head;
	private int tam;
	/**
	 * Constructor de la clase PilaConEnlaces.
	 */
	public PilaConEnlaces() {
		head = null;
		tam = 0;
	}
	/**
	 * Consulta si la pila esta vacia.
	 * @return Verdadero si la pila esta vacia, falso en caso contrario.
	 */
	public boolean isEmpty() {
		return tam == 0;
	}
	/**
	 * Consulta la cantidad de elementos de la pila.
	 * @return Cantidad de elementos de la pila.
	 */
	public int size() {
		return tam;
	}
	/**
	 * Inserta un elemento en el tope de la pila.
	 * @param element Elemento a insertar.
	 */
	public void push(E elem) { 
		Nodo<E> aux = new Nodo<E>(elem);
		aux.setElement(elem);
		aux.setSiguiente(head);
		head = aux;
		tam++;
	}
	/**
	 * Remueve el elemento que se encuentra en el tope de la pila.
	 * @return Elemento removido.
	 * @throws EmptyStackException si la pila esta vacia. 
	 */
	public E pop() throws EmptyStackException{
		if(isEmpty()) {
			throw new EmptyStackException("No se puede eliminar el tope de una pila vacía");
		}
		E aux = head.element();
		head = head.getSiguiente();
		tam--;
		return aux;
	}
	/**
	 * Examina el elemento que se encuentra en el tope de la pila.
	 * @return Elemento que se encuentra en el tope de la pila.
	 * @throws EmptyStackException si la pila esta vacia. 
	 */
	public E top() throws EmptyStackException{
		if(isEmpty()) {
			throw new EmptyStackException("No se puede obtener el tope de una pila vacía");
		}
		return head.element();
	}
}