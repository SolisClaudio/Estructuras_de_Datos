package TDALista;

import Excepciones.BoundaryViolationException;
import Excepciones.EmptyListException;
import java.util.*;
import Auxiliares.Position;
import Excepciones.InvalidPositionException;

public class ElementIterator<E> implements Iterator<E> {
	private Position<E> cursor;
	private PositionList<E> list;
	
	public ElementIterator(PositionList<E> p) {
		list=p;
		try { 
			if(!list.isEmpty()) 
				cursor=list.first();
			else cursor=null;
		}
		catch(EmptyListException e) { e.printStackTrace();}
		
	}
	
	public boolean hasNext() {
		return cursor!=null;
	}
	
	public E next() throws NoSuchElementException{
		E retorno;
		if(cursor == null) throw new NoSuchElementException("Error, no hay elemento siguiente");
		retorno = cursor.element();
		try{
			if(cursor!=list.last()) cursor = list.next(cursor);
				else cursor = null; 
		}
		catch(EmptyListException | InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace(); 
		}
		return retorno;
	}

}
