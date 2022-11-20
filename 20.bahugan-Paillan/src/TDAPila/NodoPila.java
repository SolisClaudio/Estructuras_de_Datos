package TDAPila;

/**
 * 
 * @author Leon Baguhan Sanchez Yuyprayseree y Simon Paillan
 * 
 * @param <E> Elemento generico
 */
public class NodoPila<E> {
	
	private E element;
	private NodoPila <E> next;
	/**
	 * Crea un Nodo con un elemento.
	 * @param element Elemento a establecer.
	 */
	public NodoPila (E element)
	{
		this.element = element;
		
	}
	/**
	 * Crea un nodo que conoce a su nodo siguiente.
	 * @param element Elemento a establecer.
	 * @param siguent Nodo siguiente que es pasado por parametro. 
	 */
	public NodoPila (E element , NodoPila <E> siguent)
	{
		this.element = element;
		next = siguent;
		
	}
	/**
	 * Retorna el elemento del nodo.
	 * @return Elemento del nodo.
	 */
	public E element() {
		return element;
	}
	/**
	 * Obtiene el nodo siguiente al actual.
	 * @return Nodo siguiente.
	 */
	public NodoPila <E> getNext ()
	{
		return next;
		
	}
	/**
	 * Establece el nodo siguiente al actual.
	 * @param n Nodo siguiente a establecer.
	 */
	public void setNext (NodoPila<E> n ) 
	{
		next = n;
	}
	/**
	 * Establece el elemento del nodo actual.
	 * @param ele Elemento que se establece en el nodo.
	 */
	public void setElemento (E ele)
	{
		element = ele;
		
	}
	
}


