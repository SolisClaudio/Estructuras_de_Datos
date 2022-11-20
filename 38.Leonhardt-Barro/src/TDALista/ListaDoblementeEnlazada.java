package TDALista;

import java.util.Iterator;
import Exceptions.*;

/** 
 * @author Jonathan Alejandro Leonhardt,LU:134726
 *
 */
public class ListaDoblementeEnlazada<E> implements PositionList<E> {

	protected int size;
	protected Nodo<E> header, trailer;

/**
 * Crea una ListaDoblementeEnlazada
 */
	public ListaDoblementeEnlazada() {
		size = 0;
		header = new Nodo<E>(null, null, null);
		trailer = new Nodo<E>(header, null, null);
		header.setNext(trailer);
	}

	/**
	 * Consulta si una posicion es valida
	 * @param p Para validar que es una posicion valida
	 * @return El nodo asociado a la posicion 
	 * @throws InvalidPositionException p no es una posicion valida
	 */
	private Nodo<E> checkPosition(Position<E> p) throws InvalidPositionException {
		if (p == null || p == header || p == trailer)
			throw new InvalidPositionException("Se ha pasado una posicion nula");
		try {
			Nodo<E> temp = (Nodo<E>) p;
			if ((temp.getPrev() == null) || (temp.getNext() == null))
				throw new InvalidPositionException("La posicion no pertenece a una lista valida");
			return temp;
		} catch (ClassCastException e) {
			throw new InvalidPositionException("La posicion es de un tipo incorrecto para esta lista");
		}
	}

	/**
	 * Consulta la cantidad de elementos de la lista.
	 * @return Cantidad de elementos de la lista.
	 */	
	public int size() {
		return size;
	}

	/**
	 * Consulta si la lista est� vac�a.
	 * @return Verdadero si la lista est� vac�a, falso en caso contrario.
	 */
	public boolean isEmpty() {
		return (size == 0);
	}

	/**
	 * Devuelve la posici�n del primer elemento de la lista. 
	 * @return Posici�n del primer elemento de la lista.
	 * @throws EmptyListException si la lista est� vac�a.
	 */
	public Position<E> first() throws EmptyListException {
		if (isEmpty())
			throw new EmptyListException("La lista esta vacia");
		return header.getNext();
	}

	/**
	 * Devuelve la posici�n del �ltimo elemento de la lista. 
	 * @return Posici�n del �ltimo elemento de la lista.
	 * @throws EmptyListException si la lista est� vac�a.
	 * 
	 */
	public Position<E> last() throws EmptyListException {
		if (isEmpty())
			throw new EmptyListException("La lista esta vacia");
		return trailer.getPrev();
	}	
	
	/**
	 * Devuelve la posici�n del elemento siguiente a la posici�n pasada por par�metro.
	 * @param p Posici�n a obtener su elemento siguiente.
	 * @return Posici�n del elemento siguiente a la posici�n pasada por par�metro.
	 * @throws InvalidPositionException si el posici�n pasada por par�metro es inv�lida o la lista est� vac�a.
	 * @throws BoundaryViolationException si la posici�n pasada por par�metro corresponde al �ltimo elemento de la lista.
	 */
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		Nodo<E> v = checkPosition(p);
		Nodo<E> next = v.getNext();
		if (next == trailer)
			throw new BoundaryViolationException("No se puede retornar la posicion siguiente a la ultima");
        return next;
	}	
	
	/**
	 * Devuelve la posici�n del elemento anterior a la posici�n pasada por par�metro.
	 * @param p Posici�n a obtener su elemento anterior.
	 * @return Posici�n del elemento anterior a la posici�n pasada por par�metro.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida o la lista est� vac�a.
	 * @throws BoundaryViolationException si la posici�n pasada por par�metro corresponde al primer elemento de la lista.
	 */
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
	    Nodo<E> v = checkPosition(p);
		Nodo<E> prev = v.getPrev();
		if (prev == header)
			throw new BoundaryViolationException("No se puede retornar el elemento anterior al primero");
		return prev;
	}

	/**
	 * Inserta un elemento al principio de la lista.
	 * @param element Elemento a insertar al principio de la lista.
	 */
	public void addFirst(E element) {
		size++;
		Nodo<E> newNode = new Nodo<E>(header, header.getNext(), element);
		header.getNext().setPrev(newNode);
		header.setNext(newNode);
	}
	
	/**
	 * Inserta un elemento al final de la lista.
	 * @param element Elemento a insertar al final de la lista.
	 */
	public void addLast(E e) {
		Nodo<E> newNode = new Nodo<E>(trailer.getPrev(), trailer, e);
		size++;
		trailer.getPrev().setNext(newNode);
		trailer.setPrev(newNode);
		}
	
	/**
	 * Inserta un elemento luego de la posici�n pasada por par�matro.
	 * @param p Posici�n en cuya posici�n siguiente se insertar� el elemento pasado por par�metro.
	 * @param element Elemento a insertar luego de la posici�n pasada como par�metro.
	 * @throws InvalidPositionException si la posici�n es inv�lida o la lista est� vac�a.
	 */
	public void addAfter(Position<E> p, E e) throws InvalidPositionException {
		Nodo<E> v = checkPosition(p);
		size++;
		Nodo<E> newNode = new Nodo<E>(v, v.getNext(), e);
		v.getNext().setPrev(newNode);
		v.setNext(newNode);
	}
	
	/**
	 * Inserta un elemento antes de la posici�n pasada como par�metro.
	 * @param p Posici�n en cuya posici�n anterior se insertar� el elemento pasado por par�metro. 
	 * @param element Elemento a insertar antes de la posici�n pasada como par�metro.
	 * @throws InvalidPositionException si la posici�n es inv�lida o la lista est� vac�a.
	 */
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		Nodo<E> v = checkPosition(p);
		size++;
		Nodo<E> newNode = new Nodo<E>(v.getPrev(), v, element);
		v.getPrev().setNext(newNode);
		v.setPrev(newNode);
	}

	/**
	 * Remueve el elemento que se encuentra en la posici�n pasada por par�metro.
	 * @param p Posici�n del elemento a eliminar.
	 * @return element Elemento removido.
	 * @throws InvalidPositionException si la posici�n es inv�lida o la lista est� vac�a.
	 */	
	public E remove(Position<E> p) throws InvalidPositionException {
		Nodo<E> v = checkPosition(p);
		size--;
		Nodo<E> vPrev = v.getPrev();
		Nodo<E> vNext = v.getNext();
		vPrev.setNext(vNext);
		vNext.setPrev(vPrev);
		E vElem = v.element();
		v.setNext(null);
		v.setPrev(null);
		v.setElemento(null);
		return vElem;
	}

    /**
	 * Establece el elemento en la posici�n pasados por par�metro. Reemplaza el elemento que se encontraba anteriormente en esa posici�n y devuelve el elemento anterior.
	 * @param p Posici�n a establecer el elemento pasado por par�metro.
	 * @param element Elemento a establecer en la posici�n pasada por par�metro.
	 * @return Elemento anterior.
	 * @throws InvalidPositionException si la posici�n es inv�lida o la lista est� vac�a.	 
	 */
	public E set(Position<E> p, E element) throws InvalidPositionException {
		Nodo<E> v = checkPosition(p);
		E oldElt = v.element();
		v.setElemento(element);

		return oldElt;
	}

	/**
	 * Devuelve un un iterador de todos los elementos de la lista.
	 * @return Un iterador de todos los elementos de la lista.
	 */
	public Iterator<E> iterator() {
		return new ElementIterator<E>(this);
	}

	/**
	 * Devuelve una colecci�n iterable de posiciones.
	 * @return Una colecci�n iterable de posiciones.
	 */
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> LP = new ListaDoblementeEnlazada<Position<E>>();
		if (!isEmpty()) {
			try {
				Position<E> pos = first();
				boolean seguir = true;
				while (seguir) {
					LP.addLast(pos);
					if (pos == last())
						seguir = false;
					else
						pos = next(pos);
				    }
			   } catch (EmptyListException | BoundaryViolationException | InvalidPositionException e) {
			     }
		  }
	   return LP;
	}
}
