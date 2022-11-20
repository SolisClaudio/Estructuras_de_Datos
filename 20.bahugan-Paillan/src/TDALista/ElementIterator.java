package TDALista;

import java.util.Iterator;
import java.util.NoSuchElementException;

import Exception.BoundaryViolationException;
import Exception.EmptyListException;
import Exception.InvalidPositionException;
/**
 * 
 * @author Leon Baguhan Sanchez Yuyprayseree y Simon Paillan
 */
public class ElementIterator <E> implements Iterator <E>{

	protected PositionLista <E> lista;
	protected Position <E> cursor;
	/**
	 * Crea un iterador a partir de una lista que es pasada por parametro, junto a un cursor para recorerla.
	 * @param l
	 */
	public ElementIterator  (PositionLista <E> l)
	{
		
		lista = l;
		if(lista.isEmpty() == true)
			cursor = null;
		else
		{
			try
			{
				cursor = l.first();
			}
			catch (EmptyListException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Consulta si el iterador tiene siguiente.
	 */
	public boolean hasNext() {
		// consulto si hay algo que iterar
		return cursor!=null;
	}

	/**
	 * Mueve el lente del iterador al siguiente elemento.
	 */
	public E next() throws NoSuchElementException
	{
		if(cursor == null)
			throw new NoSuchElementException ("No hay siguiente");
		E resultado = cursor.element();
		
		try {
			cursor= (cursor==lista.last())? null: lista.next(cursor);
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		}
		return resultado;
		
	}

}
