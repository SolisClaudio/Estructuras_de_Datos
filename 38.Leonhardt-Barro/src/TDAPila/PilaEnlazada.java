package TDAPila;
import Exceptions.*;

/**
 * Implementacion Pila Simplemente Enlazada
 * @author Jonathan Alejandro Leonhardt, LU:134726
 */

public class PilaEnlazada<E> implements Stack<E>{
	
	protected Nodo<E> head;
	protected int tamanio;
	
	/**
	 * Crea una pila simplemente enlazada 
	 */

	public PilaEnlazada(){
		head=null;
		tamanio=0;
	}
	
	/**
	 * Consulta la cantidad de elementos de la pila.
	 * @return Cantidad de elementos de la pila.
	 */
	public int size() {
		return tamanio;
	}

	/**
	 * Consulta si la pila est� vac�a.
	 * @return Verdadero si la pila est� vac�a, falso en caso contrario.
	 */
	public boolean isEmpty() {
		return tamanio==0;
	}

	
	/**
	 * Examina el elemento que se encuentra en el tope de la pila.
	 * @return Elemento que se encuentra en el tope de la pila.
	 * @throws EmptyStackException si la pila est� vac�a. 
	 */
	public E top() throws EmptyStackException {
		if(isEmpty()) throw new EmptyStackException("Pila vacia");
		return head.getElemento();
	}

	/**
	 * Inserta un elemento en el tope de la pila.
	 * @param element Elemento a insertar.
	 */
	public void push(E element) {
		Nodo<E> aux = new Nodo<E>(element,head) ;
		head=aux;
		tamanio++;}
	
	/**
	 * Remueve el elemento que se encuentra en el tope de la pila.
	 * @return Elemento removido.
	 * @throws EmptyStackException si la pila est� vac�a. 
	 */
	public E pop() throws EmptyStackException {
		if(isEmpty()) throw new EmptyStackException("Pila vacia");
		E aux =head.getElemento();
		head =head.getSiguiente();
		tamanio--;
		return aux;
	}
}
