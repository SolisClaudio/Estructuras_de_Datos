package TDALista;

import java.util.Iterator;

import Exception.BoundaryViolationException;
import Exception.EmptyListException;
import Exception.InvalidPositionException;
/**
 * 
 * @author Leon Baguhan Sanchez Yuyprayseree y Simon Paillan
 * 
 * @param <E> Elemento generico.
 */
public class Lista <E> implements PositionLista <E> {
	
	protected int tamanio;
	protected Nodo <E> cabeza;
	protected Nodo <E> cola;
	
	/**
	 * Crea una listaDoblementeEnlazada con sentinelas vacia.
	 */
	
	public Lista ()
	{
		tamanio =0;
		cabeza = new Nodo <E> (null,null,null); // asigno el sentinela en el principio de la lista
		cola = new Nodo <E>(cabeza , null,null);// asigno el sentinela en el final de la lista
		cabeza.setNext(cola);// le asigno al primer sentinela su siguiente que es el ultimo sentinela
	}
	
	private Nodo<E> checkPosition (Position <E> p) throws InvalidPositionException
	{
		if(p == null || p == cabeza || p == cola)
			throw new InvalidPositionException ("Posicion nula");
		
		try 
		{
		  Nodo <E> aux = (Nodo <E>)p;
		  if(aux.getNext()== null || aux.getPrev() == null )
			throw new InvalidPositionException ("La posicion no esta en la lista");
		
		 return aux;
		}
		catch (ClassCastException e)
		{
			throw new InvalidPositionException ("la Posicion no pertence a la lista");
		}
		
		
		
	}
	
	/**
	 * Consulta la cantidad de elementos de la lista.
	 * @return La cantidad de elementos de la lista.
	 */
	public int size() {
		
		return tamanio;
	}

	/**
	 * Consulta si la lista esta vacia.
	 * @return Verdadero si la lista esta vacia y falso en caso contrario.
	 */
	public boolean isEmpty() {
		
		return tamanio == 0;
	}

	/**
	 * Devuelve la posicion del primer elemento de la lista.
	 * @return La posicion del primer elemento de la lista.
	 * @throws EmptyListException si la lista esta vacia.
	 */
	public Position<E> first() throws EmptyListException {
		if (isEmpty ())
			throw new EmptyListException ("Lista vacia");
		
		return cabeza.getNext();
	}

	/**
	 * Devuelve la posicion del ultimo elemento de la lista.
	 * @return La posicion del ultimo elemento de la lista.
	 * @throws EmptyListException si la lista esta vacia o si la lista esta vacia.
	 */
	public Position<E> last() throws EmptyListException {
		if(isEmpty())
			throw new EmptyListException ("Lista vacia");
		
		
		return cola.getPrev();
	}

	/**
	 * Devuelve la posicion del elemento siguiente de la posicion pasada por parametro.
	 * @param p Posicion a obtener su siguiente.
	 * @return La posicion siguiente del pasado por parametro.
	 * @throws InvalidPositionException si la posicion  pasada por parametro no es valida o si la lista esta vacia.
	 * @throws BoundaryViolationException si la posicion pasada por parametro corresponde al ultimo elemento de la lista.
	 */
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		Nodo <E> aux = checkPosition (p);
		Nodo<E> next = aux.getNext();
		if (next == cola)
			throw new BoundaryViolationException ("No se puede devolver la siguiente a a la ultima");
		
		return next;
	}

	/**
	 * Devuelve la posicion del elemento previo de la posicion pasada por parametro.
	 * @param p Posicion a obtener su previo.
	 * @return La posicion previo del pasado por parametro.
	 * @throws InvalidPositionException si la posicion  pasada por parametro no es valida o si la lista esta vacia.
	 * @throws BoundaryViolationException si la posicion pasada por parametro corresponde al primer elemento de la lista.
	 */
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		Nodo <E> aux = checkPosition (p);
		Nodo<E> prev = aux.getPrev();
		if(prev == cabeza)
			throw new BoundaryViolationException ("No se puede devolver la previa a a la ultima");
		
		return prev;
	}

	/**
	 * Inserta un elemento al principio de la lista.
	 */
	public void addFirst(E element) 
	{
		Nodo<E> nuevo = new Nodo <E> (cabeza,element,cabeza.getNext());
		
		cabeza.getNext().setPrev(nuevo);
		cabeza.setNext(nuevo);
		tamanio++;
		
	}

	/**
	 * Inserta un elemento al final de la lista.
	 */
	public void addLast(E element) {
		Nodo<E> nuevo = new Nodo<E>(cola.getPrev() , element , cola);
		cola.getPrev().setNext(nuevo);
		cola.setPrev(nuevo);
		tamanio++;
	}

	/**
	 * Inserta un elemento en la posicion siguiente a la posicion pasa por parametro.
	 * @param p Posicion a la cual se le insetara adelante un elemento.
	 * @param element Elemento a insertar.
	 * @throws InvalidPositionException si la posicion pasada por parametro no es valid o es nula.
	 */
	public void addAfter(Position<E> p, E element) throws InvalidPositionException 
	{
		Nodo<E> n = checkPosition (p);
		Nodo<E> nuevo = new Nodo<E>(n,element,n.getNext());
		
		n.getNext().setPrev(nuevo);
		n.setNext(nuevo);
		tamanio++;
	}

	/**
	 * Inserta un elemento en la posicion anterior a la posicion pasa por parametro.
	 * @param p Posicion a la cual se le insetara detras un elemento.
	 * @param element Elemento a insertar.
	 * @throws InvalidPositionException si la posicion pasada por parametro no es valid o es nula.
	 */
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		Nodo<E> n = checkPosition (p);
		Nodo<E> nuevo = new Nodo<E>(n.getPrev(),element,n);
		
		n.getPrev().setNext(nuevo);
		n.setPrev(nuevo);
		tamanio++;
	}

	/**
	 * Remueve el elemento que se encuntre en la posicion pasada por parametro.
	 * @param p Posicion del elemento a eliminar.
	 * @throws InvalidPositionException si la posicion pasada por parametro no es valid o es nula.
	 */
	public E remove(Position<E> p) throws InvalidPositionException {
		Nodo<E> n = checkPosition (p);
		Nodo<E> previo = n.getPrev();
		Nodo<E> next = n.getNext();
		previo.setNext(next);
		next.setPrev(previo);
		
		E ElementoEliminado = n.element();
		n.setNext(null);
		n.setPrev(null);
		n.setElemento(null);
		tamanio--;
		return ElementoEliminado;
	}

	/**
	 * Establece un elemento en la posicion pasada por parametro. Reemplaza el elemento en esa posicion y devuelve el elemento reemplazado.
	 * @param p Posicion en la que se quiere establecer un elemento.
	 * @param element Elemento a establecer.
	 * @return Elemento reemplazado.
	 * @throws InvalidPositionException si la posicion pasada por parametro no es valid o es nula.
	 */
	public E set(Position<E> p, E element) throws InvalidPositionException {
		Nodo<E> n = checkPosition (p);
		
		
		E ElementoReemplazado = n.element();
		n.setElemento(element);
		
		return ElementoReemplazado;
	}

	/**
	 * Devuelve un Iterador con todos los elementos de esta lista.
	 * @return Iterador con todos los elementos de la lista.
	 */
	public Iterator<E> iterator() {
		
		return new ElementIterator <E>(this);
	}

	/**
	 * Devuelve una coleccion iterable de posiciones.
	 * @return Coleccion iterable de posiciones.
	 */
	public Iterable<Position<E>> positions() {
		PositionLista <Position<E>> aux = new Lista<Position<E>> ();
		if(!isEmpty() )
		{
			try 
			{
				Position <E> posicion = first();
				boolean seguir = true;
				while (seguir)
				{
					aux.addLast(posicion);
					if(posicion == last())
					{
						seguir =false;
					}
					else
					{
						posicion = next (posicion);
					}
				}
			}
			catch (EmptyListException | BoundaryViolationException | InvalidPositionException e)
			{}
		}
		return aux;
	}

}
