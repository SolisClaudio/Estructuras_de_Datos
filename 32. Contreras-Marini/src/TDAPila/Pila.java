package TDAPila;
import Auxiliares.Nodo;

import Exceptions.EmptyStackException;

/**
 * Clase Pila<E>
 * @author Nahuel Contreras, Nicolas Marini.
 * Clase que implementa Stack<E> con una estructura simplemente enlazada.
 */
public class Pila<E> implements Stack<E> {
	
	private Nodo<E> cabeza;
	private int tamanio;
	
	/**
	 * Constructor de la clase Pila<E>, asigna valores predeterminados a los atributos de la clase.
	 */
	public Pila() {
		cabeza = null;
		tamanio = 0;
	}
	
	public int size() {
		return tamanio;
	}
	
	public boolean isEmpty() {
		return tamanio == 0;
	}
	
	public E  top() throws EmptyStackException{
		if(isEmpty())
			throw new EmptyStackException("Error: no se puede mostrar el último elemento ingresado, porque no se encuentra nada almacenado.");
		return cabeza.getElemento();
	}
	
	public void push(E element) {
		Nodo<E> nuevo = new Nodo<E>(element,cabeza);
		cabeza = nuevo;		
		tamanio++;
	}

	public E pop() throws EmptyStackException{
		if(isEmpty())
			throw new EmptyStackException("Error: no se puede eliminar el último elemento, porque no se encuentra nada almacenado.");
		E retorno = cabeza.getElemento();
		cabeza = cabeza.getSiguiente();
		tamanio--;
		return retorno;
	}
	
	
}
