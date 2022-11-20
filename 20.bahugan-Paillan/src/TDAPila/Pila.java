package TDAPila;

import Exception.EmptyStackException;
/**
 * 
 * @author @author Leon Baguhan Sanchez Yuyprayseree y Simon Paillan
 *
 * @param <E> Elemento Generico
 */
public class Pila<E> implements Stack <E>{

	protected int tamanio ;
	protected NodoPila<E> tope;
	
	/**
	 * Crea una pila vacia.
	 */
	public Pila() 
	{
		tamanio = 0;
		tope = null;
	}
	
	/**
	 * Consulta la cantidad de elementos de la pila.
	 * @return La cantida de elementos.
	 */
	public int size() {
		
		return tamanio;
	}

	/**
	 * Consulta si la pila esta vacia.
	 * @return Verdadero si la pila esta vacia y falso en caso contrario.
	 */
	public boolean isEmpty() {
		
		return tamanio == 0;
	}

	/**
	 * Inspecciona el elemento en el tope de la pila.
	 * @return Elemento en el tope de la pila.
	 * @throws EmptyStackException si la pila esta vacia.
	 */
	public E top() throws EmptyStackException {
		if(isEmpty( ))
			throw new EmptyStackException ("Pila vacia");
		
		return tope.element(); 
	}
	/**
	 * Inserta un elemento en el tope de la pila.
	 * @param element Elemento a insertar.
	 */
	public void push(E element) {
		
		NodoPila <E> aux = new NodoPila <E> (element,tope);
		tope = aux; 
		tamanio++;
	}
	/**
	 * Elimina el elemento en el tope de la pila.
	 * @return Elemento eliminado de la pila.
	 * @throws EmptyStackException Si la pila esta vacia.
	 */
	public E pop() throws EmptyStackException {
	if(isEmpty ())
		throw new EmptyStackException("Pila vacia");
		E elementoEliminado = tope.element();
		tope = tope.getNext();
		tamanio--;
		return elementoEliminado;
	}
}
