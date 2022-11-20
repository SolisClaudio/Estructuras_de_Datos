package TDALista;
import java.util.*;
import java.lang.*;
import java.util.NoSuchElementException;
import Exceptions.*;
import Auxiliares.Position;

/**
 * Clase ElementIterator<E>
 * @author Nahuel Contreras, Nicolas Marini.
 * Clase que implementa Iterator<E>.
 */
public class ElementIterator<E> implements Iterator<E> {
	protected PositionList<E> lista;
	protected Position<E> cursor;

	public ElementIterator(PositionList<E> L) {
		try {
			lista = L;
			if(lista.isEmpty())
				cursor = null;
			else 
				cursor = lista.first();
		} catch(EmptyListException s) {
			s.printStackTrace();
		}
	}
	
	public boolean hasNext() {
		return cursor != null;
	}
			
	public E next() throws NoSuchElementException {
		E retorno = null;
		if(cursor == null)
			throw new NoSuchElementException("Error: No hay siguiente");
			
		try {
				
			retorno = cursor.element();
			cursor = (cursor == lista.last()) ? null : lista.next(cursor);
				
		}catch(EmptyListException | BoundaryViolationException | InvalidPositionException s) {
			s.getMessage();
		}
			
		return retorno;
	}
	/*
	public Iterator<E> iterator(){
		return new ElementIterator<E>( lista );
	}
	*/
}