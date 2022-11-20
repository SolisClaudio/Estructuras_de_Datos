package TDALista;

/**
 * 
 * @author Leon Baguhan Sanchez Yuyprayseree y Simon Paillan
 */
public class Nodo<E> implements Position<E>{
	
	private E element;
	private Nodo <E> next;
	private Nodo<E> pre; 
	/**
	 * Crea un nodo que conoce a su nodo previo y siguiente.
	 * @param prev Nodo previo al actual.
	 * @param element Elemento que se le asigna al nodo.
	 * @param sig Nodo siguiente al actual.
	 */
	public Nodo (Nodo <E> prev , E element ,Nodo <E> sig)
	{	
		pre = prev;
		this.element = element;
		next = sig;
	}
	
	/**
	 * Retorna el elemento del nodo.
	 * @return Elemento del nodo.
	 */
	public E element() {
		
		return element;
	}
	/**
	 *Obtiene el nodo siguiente del actual.
	 * @return Nodo siguiente.
	 */
	public Nodo <E> getNext ()
	{
		return next;
		
	}
	/**
	 * Establece el nodo siguiente.
	 * @param n Nodo a establecer en la posicion siguiente.
	 */
	public void setNext (Nodo<E> n ) 
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
	
	/**
	 * Obtiene el nodo previo al actual.
	 * @param p Nodo a establecer en la posicion previa.
	 */
	public void setPrev(Nodo<E> p)
	{
		pre = p;
		
	}
	/**
	 *Obtiene el nodo siguiente del actual.
	 * @return Nodo previo.
	 */
	public Nodo<E> getPrev()
	{
		return pre;
	}
}


