package TDALista;

import java.util.Iterator;

import Exceptions.BoundaryViolationException;
import Exceptions.EmptyListException;
import Exceptions.InvalidPositionException;

//UTILIZO LA INTERFACE ITERATOR QUE PROVEE JAVA
public class ElementIterator<E> implements Iterator<E> {

	protected PositionList<E> list; //LISTA A ITERAR
	protected Position<E> cursor; //POSICION DEL ELEMENTO CORRIENTE
	
	protected ElementIterator(PositionList<E> l) {
		list = l; //GUARDO LA REFERENCIA A LA LISTA A ITERAR
		if (list.isEmpty()) cursor = null; // SI LA LISTA ESTA VACIA, LA POSICION CORRIENTE ES NULA
		else
			try {
				cursor = l.first(); // SINO LA POSICION CORRIENTE ES LA PRIMERA DE LA LISTA
			} catch (EmptyListException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
	
	//HAY SIGUIENTE SI EL CURSOR NO ESTA MAS ALLA DE LA ULTIMA POSICION
	public boolean hasNext() {
		
		return cursor != null;
	}

	
	public E next() {
		E toReturn = cursor.element();//SALVO EL ELEMENTO 
		try {
			cursor = (cursor == list.last()) ? null : list.next(cursor); //AVANZO A LA SIGUIENTE POSICION
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return toReturn; //RETORNO EL ELEMENTO SALVADO
	}

}
