package TDALista;

import java.util.Iterator;
import java.util.NoSuchElementException;
import Exceptions.*;
/**
 * Clase ElementIterator, implementa la interfaz Iterator.
 * @author  De Giusti Camila - Quintana Alejo Joaquin.
 *
 * @param <E>
 */
public class ElementIterator<E> implements Iterator<E> {
	protected PositionList<E> list;
	protected Position<E> cursor;
	
	/*
	 * Crea una instancia de elementIterator.
	 * @param l lista.
	 */
	public ElementIterator(PositionList<E> l) {
		list = l;
		try{
			if(list.isEmpty()) 
		
			cursor = null;
		
		else 
			cursor = list.first();
		}catch(EmptyListException e) {
			System.out.println(e.getMessage());
		}
	}
	/**
	 * Verifica si quedan o no elementos por recorrer en la secuencia.
	 * @return Verdadero si aún quedan elementos por recorrer en la secuencia, falso en caso contrario.
	 */
	public boolean hasNext() {
		
		return cursor != null;
		
	}
	/**Devuelve el próximo elemento a recorrer de una secuencia.
	 * @return el próximo elemento a recorrer de la secuencia.
	 * @throws si el elemento es inválido.
	 */
	public E next() throws NoSuchElementException {
		E aRetornar = cursor.element();
		if(cursor == null) {
			throw new NoSuchElementException("Elemento invalido");
		}
		else {
			try {
				if(cursor == list.last()) {
					cursor = null;
				}
				else {
					cursor = list.next(cursor);
				}
			}catch(EmptyListException | InvalidPositionException | BoundaryViolationException e) {
				System.out.println(e.getMessage());
			}
		}
		return aRetornar;
	}
}