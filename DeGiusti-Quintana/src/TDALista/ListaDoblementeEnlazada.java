package TDALista;
import Exceptions.*;
import TDACola.*;
import TDAPila.*;
import java.util.Iterator;


/**
 * Clase ListaDoblemente Enlazada, implementa la interfaz PositionList.
 * @author De Giusti Camila - Quintana Alejo Joaquin.
 *
 * @param <E>
 */
public class ListaDoblementeEnlazada<E> implements PositionList<E> {
	private DNodo<E> head,tail;
	private int size;
	
	/**
	 * Crea una instancia de ListaDoblementeEnlazada.
	 */
	public ListaDoblementeEnlazada(){
		head = new DNodo<E>(null, null, null);
		tail = new DNodo<E>(null, head, null);
		head.setSiguiente(tail);
		size = 0;
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
		return size == 0;
	}
	
	/**
	 * Devuelve la posici�n del primer elemento de la lista. 
	 * @return Posici�n del primer elemento de la lista.
	 * @throws EmptyListException si la lista est� vac�a.
	 */
	public Position<E> first() throws EmptyListException{
		if (size == 0) {
			throw new EmptyListException("Lista vacía");
		}
		return head.getSiguiente();
	}
	
	/**
	 * Devuelve la posici�n del �ltimo elemento de la lista. 
	 * @return Posici�n del �ltimo elemento de la lista.
	 * @throws EmptyListException si la lista est� vac�a.
	 * 
	 */
	public Position<E> last() throws EmptyListException{
		if(size == 0) {
			throw new EmptyListException("Lista vacía");
		}
		return tail.getAnterior();
	}
	
	/**
	 * Devuelve la posici�n del elemento siguiente a la posici�n pasada por par�metro.
	 * @param p Posici�n a obtener su elemento siguiente.
	 * @return Posici�n del elemento siguiente a la posici�n pasada por par�metro.
	 * @throws InvalidPositionException si el posici�n pasada por par�metro es inv�lida o la lista est� vac�a.
	 * @throws BoundaryViolationException si la posici�n pasada por par�metro corresponde al �ltimo elemento de la lista.
	 */
	public Position<E> next(Position<E> p) throws InvalidPositionException,BoundaryViolationException{
		DNodo<E> aux = checkPosition(p);
		if (aux.getSiguiente().equals(tail)) {
			throw new BoundaryViolationException("No se puede obtener el siguiente del último elemento");
		}
		return aux.getSiguiente();
	}
	
	/**
	 * Devuelve la posici�n del elemento anterior a la posici�n pasada por par�metro.
	 * @param p Posici�n a obtener su elemento anterior.
	 * @return Posici�n del elemento anterior a la posici�n pasada por par�metro.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida o la lista est� vac�a.
	 * @throws BoundaryViolationException si la posici�n pasada por par�metro corresponde al primer elemento de la lista.
	 */
	public Position<E> prev(Position<E> p) throws InvalidPositionException,BoundaryViolationException{
		DNodo<E> aux = checkPosition(p);
		if (aux.getAnterior().equals(head)) {
			throw new BoundaryViolationException("No se puede pedir el previo al primer elemento");
		}
		return aux.getAnterior();
	}
	
	/**
	 * Inserta un elemento al principio de la lista.
	 * @param element Elemento a insertar al principio de la lista.
	 */
	public void addFirst(E element) {
		DNodo<E> nuevo = new DNodo<E>(element,head,head.getSiguiente());
		head.getSiguiente().setAnterior(nuevo);
		head.setSiguiente(nuevo);
		size++;
	}
	
	/**
	 * Inserta un elemento al final de la lista.
	 * @param element Elemento a insertar al final de la lista.
	 */
	public void addLast(E element) {
		DNodo<E> nuevo = new DNodo<E>(element,tail.getAnterior(),tail);
		tail.getAnterior().setSiguiente(nuevo);
		tail.setAnterior(nuevo);
		size++;
	}
	
	/**
	 * Inserta un elemento luego de la posici�n pasada por par�matro.
	 * @param p Posici�n en cuya posici�n siguiente se insertar� el elemento pasado por par�metro.
	 * @param element Elemento a insertar luego de la posici�n pasada como par�metro.
	 * @throws InvalidPositionException si la posici�n es inv�lida o la lista est� vac�a.
	 */
	public void addAfter(Position<E> p, E element) throws InvalidPositionException{
		DNodo<E> aux = checkPosition(p);
		DNodo<E> nuevo = new DNodo<E>(element,aux,aux.getSiguiente());
		aux.getSiguiente().setAnterior(nuevo);
		aux.setSiguiente(nuevo);
		size++;
	}
	
	/**
	 * Inserta un elemento antes de la posici�n pasada como par�metro.
	 * @param p Posici�n en cuya posici�n anterior se insertar� el elemento pasado por par�metro. 
	 * @param element Elemento a insertar antes de la posici�n pasada como par�metro.
	 * @throws InvalidPositionException si la posici�n es inv�lida o la lista est� vac�a.
	 */
	public void addBefore(Position<E> p, E element) throws InvalidPositionException{
		DNodo<E> aux = checkPosition(p);
		DNodo<E> nuevo = new DNodo<E>(element,aux.getAnterior(),aux);
		aux.getAnterior().setSiguiente(nuevo);
		aux.setAnterior(nuevo);
		size++;
	}
	
	/**
	 * Remueve el elemento que se encuentra en la posici�n pasada por par�metro.
	 * @param p Posici�n del elemento a eliminar.
	 * @return element Elemento removido.
	 * @throws InvalidPositionException si la posici�n es inv�lida o la lista est� vac�a.
	 */	
	public E remove(Position<E> p) throws InvalidPositionException{
		if(size == 0) {
			throw new InvalidPositionException("Lista vacía");
		}
		DNodo<E> aux = checkPosition(p);
		aux.getAnterior().setSiguiente(aux.getSiguiente());
		aux.getSiguiente().setAnterior(aux.getAnterior());
		size--;
		return aux.element();
	}
	
/**
	
	 * Establece el elemento en la posici�n pasados por par�metro. Reemplaza el elemento que se encontraba anteriormente en esa posici�n y devuelve el elemento anterior.
	 * @param p Posici�n a establecer el elemento pasado por par�metro.
	 * @param element Elemento a establecer en la posici�n pasada por par�metro.
	 * @return Elemento anterior.
	 * @throws InvalidPositionException si la posici�n es inv�lida o la lista est� vac�a.	 
	 */
	public E set(Position<E> p, E element) throws InvalidPositionException{
		if(size == 0) {
			throw new InvalidPositionException("Lista vacía");
		}
		DNodo<E> aux = checkPosition(p);
		E ant = aux.element();
		aux.setElement(element);
		return ant;
	}
	
	/**
	 * Devuelve un un iterador de todos los elementos de la lista.
	 * @return Un iterador de todos los elementos de la lista.
	 */
	public Iterator<E> iterator(){
		return(new ElementIterator(this));
	}
	
	/**
	 * Devuelve una colecci�n iterable de posiciones.
	 * @return Una colecci�n iterable de posiciones.
	 */
	public Iterable<Position<E>> positions(){
		PositionList<Position<E>> p = new ListaDoblementeEnlazada<Position<E>>();
		try {
			if(size != 0) {
				Position<E> pos = first();
				while(pos != last()) {
					p.addLast(pos);
					pos = next(pos);
				}
				p.addLast(pos);
			}
		}
		catch(EmptyListException | InvalidPositionException | BoundaryViolationException e) {
			System.out.println(e.getMessage());
		}
		return (Iterable<Position<E>>) p;
	}
	/**
	 * Chequea que la posición pasada por parámetro sea válida.
	 * @param p Posición.
	 * @return DNodo.
	 * @throws InvalidPositionException en caso de que la posición sea nula, inválida o se haya eliminado previamente.
	 */
	private DNodo<E> checkPosition(Position<E> p) throws InvalidPositionException{
		try{
			if (p == null) {
				throw new InvalidPositionException("Posición nula");
			}
			if (p.element() == null) {
				throw new InvalidPositionException("Elemento nulo");
			}
			return(DNodo<E>) p;
		}
		catch(ClassCastException e) {
			throw new InvalidPositionException(e.getMessage());
		}
	}
}
