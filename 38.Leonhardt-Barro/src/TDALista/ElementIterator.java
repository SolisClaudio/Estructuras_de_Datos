package TDALista;

import java.util.Iterator;
import java.util.NoSuchElementException;

import Exceptions.*;

/**
 * Implementacion ElementIterator<E>
 * @author Jonathan Alejandro Leonhardt,LU:134724
 */
public class ElementIterator<E> implements Iterator<E> {
	
	protected PositionList<E> list;
	protected Position<E> cursor;
	
/**	
 * Crea un iterador 
 * @param l es elementos de una lista 
 */
	public ElementIterator(PositionList <E> l) {
		list=l;
		if(list.isEmpty())
			cursor=null;
		else
			try {
				cursor=list.first();
			} catch (EmptyListException e) {
				e.printStackTrace();
			}
	}

/**
 * Cosulta si todavia hay elementos en la iteracion 
 * @return Verdadero si la iteracion tiene mas elementos,en caso contrario retorna falso
 */
	public boolean hasNext() {
		return cursor!=null;
	}

/**
 * Cosulta el siguiente elemento de la iteracion 
 * @return Elemento 
 * @throws NoSuchElementException si anteriormente no se controlo hasNext()
 */
	public E next() throws NoSuchElementException {
		if(cursor==null)
			throw new NoSuchElementException("Error: No hay siguiente");
		E toReturn= cursor.element();
		try {
			cursor= (cursor==list.last())? null: list.next(cursor);
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		}
		
		return toReturn;
	}

}