package TDALista;
import Exceptions.*;
import java.util.Iterator;
import Auxiliares.DNodo;
import Auxiliares.Position;

/**
 * Clase ListaDoblementeEnlazada<E>
 * @author Nahuel Contreras, Nicolas Marini.
 * Clase que implementa PositionList<E> con una estructura de DNodos<E> enlazados y DNodos<E> centinelas.
 */
public class ListaDoblementeEnlazada<E> implements PositionList<E> {
	
	private DNodo<E> cabeza;
	private DNodo<E> rabo;
	private int tamanio;
	
	/**
	 * Constructor de la clase ListaDoblementeEnlazada, asigna valores predeterminados a los atributos de la clase.
	 */
	public ListaDoblementeEnlazada() {
		cabeza = new DNodo<E>(null);
		rabo = new DNodo<E>(null);
		cabeza.setPrev(null);
		cabeza.setNext(rabo);
		rabo.setPrev(cabeza);
		rabo.setNext(null);
		tamanio = 0;
	}
	
	public Position<E> first() throws EmptyListException{
		if(tamanio == 0)
			throw new EmptyListException("Error, no se puede dar el primer elemento, no se ingreso nada previamente.");
		return cabeza.getNext();
	}
	
	public Position<E> last() throws EmptyListException{
		if(tamanio == 0)
			throw new EmptyListException("Error, no se puede dar el último elemento, no se ingreso nada previamente.");
		return rabo.getPrev();
	}
	
	public Position<E> prev(Position<E> parametro) throws InvalidPositionException, BoundaryViolationException{
		DNodo<E> recibido = checkPosition(parametro);
		if(tamanio == 0)
			throw new InvalidPositionException("Error, no se puede dar el elemento previo, no se ingreso nada previamente");
		if(recibido.getPrev() == cabeza)
			throw new BoundaryViolationException("Error, lo dado esta en primer lugar, no hay nada previamente");
		
		return recibido.getPrev();
	}
	
	public Position<E> next(Position<E> parametro) throws InvalidPositionException, BoundaryViolationException{
		DNodo<E> recibido = checkPosition(parametro);
		if(tamanio == 0)
			throw new InvalidPositionException("Error, no se puede agregar siguente, no se agrego nada previamente");
		if(recibido.getNext() == rabo)
			throw new BoundaryViolationException("Error, es el último elemento");
		return recibido.getNext();
	}
	
	public E set(Position<E> parametro, E e) throws InvalidPositionException{
		DNodo<E> recibido = checkPosition(parametro);
		E retorno = recibido.element();
		
		if(tamanio == 0)
			throw new InvalidPositionException("Error no se ingreso nada, no se puede cambiar el elemento de lo ingresado");
		
		recibido.setElemento(e);
		return retorno;
	}
	
	public void addFirst(E e) {
		DNodo<E> recibido = new DNodo<E>(e);
		
		recibido.setNext(cabeza.getNext());
		cabeza.getNext().setPrev(recibido);
		cabeza.setNext(recibido);
		recibido.setPrev(cabeza);
		
		tamanio++;
	}
	
	
	public void addLast(E e) {
		DNodo<E> recibido = new DNodo<E>(e);
		
		rabo.getPrev().setNext(recibido);
		recibido.setPrev(rabo.getPrev());
		rabo.setPrev(recibido);
		recibido.setNext(rabo);
		
		tamanio++;
	}
	
	public void addBefore(Position<E> parametro, E e) throws InvalidPositionException {
		
		DNodo<E> recibido = checkPosition(parametro);
		DNodo<E> nuevo = new DNodo<E>(e);
		
		if(tamanio == 0)
			throw new InvalidPositionException("Error no se ingreso nada previamente, no se puede agregar previamente");
		
		recibido.getPrev().setNext(nuevo);
		nuevo.setNext(recibido);
		nuevo.setPrev(recibido.getPrev());
		recibido.setPrev(nuevo);
		
		tamanio++;
	}
	
	public void addAfter(Position<E> parametro, E e) throws InvalidPositionException{
		
		DNodo<E> recibido = checkPosition(parametro);
		DNodo<E> nuevo = new DNodo<E>(e);
		
		if(tamanio == 0)
			throw new InvalidPositionException("Error no se ingreso nada previamente, no se puede agregar despues.");
		
		nuevo.setNext(recibido.getNext());
		nuevo.setPrev(recibido);
		recibido.setNext(nuevo);
		nuevo.getNext().setPrev(nuevo);
		
		tamanio++;
	}
	
	public E remove(Position<E> p) throws InvalidPositionException{
		
		DNodo<E> recibido = checkPosition(p);
		DNodo<E> anterior;
		DNodo<E> proximo;
		
		if(tamanio == 0)
			throw new InvalidPositionException("Error, no se puede eliminar lo ingresado, no se ingreso nada previamente");
		
		anterior = recibido.getPrev();
		proximo = recibido.getNext();
		E retorno = recibido.element();
		
		anterior.setNext(proximo);
		proximo.setPrev(anterior);
		
		tamanio--;
		
		return retorno;
	}
	
	public int size() {
		return tamanio;
	}
	
	public boolean isEmpty() {
		return (tamanio == 0);
	}
	
	public Iterator<E> iterator(){
		return new ElementIterator<E>(this);
	}
	
	
	public Iterable<Position<E>> positions(){
		PositionList<Position<E>> retorno = new ListaDoblementeEnlazada<Position<E>>();
		if(!isEmpty()) {
			try {
				Position<E> pos = first();
				boolean seguir = true;
				while(seguir) {
					retorno.addLast(pos);
					if(pos == last())
						seguir = false;
					else
						pos = next(pos);
				}
			} catch(EmptyListException | BoundaryViolationException | InvalidPositionException e) {
				e.getMessage();
			}
		}
		
		return retorno;
	}
	
	
	private DNodo<E> checkPosition(Position<E> p) throws InvalidPositionException{
		try {
			if(p == null || p == cabeza || p == rabo)
				throw new InvalidPositionException("Error posicion invalida");
			if(p.element() == null)
				throw new InvalidPositionException("Lo ingreado fue eliminado previamente");
			
			return (DNodo<E>) p;
			
		} catch(ClassCastException e) {
			throw new InvalidPositionException("Lo ingresado no pertenece a la lista");
		}
	}
	
}
