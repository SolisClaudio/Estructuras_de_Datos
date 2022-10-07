package TDALista;
import Auxiliares.Position;
import Auxiliares.DNodo;

import java.util.Iterator;

import Excepciones.BoundaryViolationException;
import Excepciones.EmptyListException;
import Excepciones.InvalidPositionException;
public class ListaDoblementeEnlazada<E> implements PositionList<E> {
	protected DNodo<E> header,trailer ; 
	protected int tamanio;
	
	public ListaDoblementeEnlazada() {
		header = new DNodo<E>(null, null, null);
		trailer =  new DNodo<E>(header, null, null);
		header.setSiguiente(trailer);
		tamanio = 0;
	}
	
	public int size() { 
		return tamanio ; 
	}
	
	public boolean isEmpty() { 
		return tamanio == 0; 
	}
	
	public Position<E> first() throws EmptyListException{
		if(tamanio == 0) throw new EmptyListException("Error, no se puede obtener el primer elemento de una lista vacia");
		return header.getSiguiente();
	}
	
	public Position<E> last() throws EmptyListException {
		if(tamanio == 0) throw new EmptyListException("Error, no se puede obtener el ultimo elemento de una lista vacia");
		return trailer.getAnterior();
	}
	
	public void addFirst(E element) {
		DNodo<E> nuevoNodo = new DNodo<E>(header, header.getSiguiente(), element);
		header.getSiguiente().setAnterior(nuevoNodo);
		header.setSiguiente(nuevoNodo);
		tamanio++;
	}
	
	public void addLast(E element) {
		DNodo<E> nuevoNodo = new DNodo<E>(trailer.getAnterior(), trailer, element);
		trailer.getAnterior().setSiguiente(nuevoNodo);
		trailer.setAnterior(nuevoNodo);
		tamanio++;
	}
	
	private DNodo<E> checkPosition(Position<E> p) throws InvalidPositionException{
		if(p == null || tamanio == 0) throw new InvalidPositionException("Error, la posicion ingresada no corresponde a una posicion valida");
		try{
			DNodo<E> nodo = (DNodo<E>) p;
			return nodo;
		}
		catch(ClassCastException e) { throw new InvalidPositionException("Error, la posicion ingresada no corresponde a una posicion valida"); }
	}
	

	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException { 
		DNodo<E> nodo = checkPosition(p);
		if(nodo == header.getSiguiente()) throw new BoundaryViolationException("Error, no se puede obtener la posicion del elemetno previo al primer elemento de la lista");
		return nodo.getAnterior();
		
	}
	
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException{
		DNodo<E> nodo = checkPosition(p);
		if ( nodo.getSiguiente() == trailer) throw new BoundaryViolationException("Error, no se puede obtener la posicion del elemento siguiente al ultimo elemnto de la lista");
		return nodo.getSiguiente();
	}
	
	public void addAfter(Position<E> p, E element) throws InvalidPositionException { 
		DNodo<E> nodo = checkPosition(p);
		DNodo<E> nuevoNodo = new DNodo<E>(nodo, nodo.getSiguiente(),element);
		nodo.getSiguiente().setAnterior(nuevoNodo);
		nodo.setSiguiente(nuevoNodo);
		tamanio++;
	}
	
	public void addBefore(Position<E> p , E element) throws InvalidPositionException{ 
		DNodo<E> nodo = checkPosition(p);
		DNodo<E> nuevoNodo =  new DNodo<E>(nodo.getAnterior(), nodo, element);
		nodo.getAnterior().setSiguiente(nuevoNodo);
		nodo.setAnterior(nuevoNodo);
		tamanio++;
	}
	
	public E remove(Position<E> p ) throws InvalidPositionException {
		E retorno;
		if(tamanio == 0) throw new InvalidPositionException("Error, la lista esta vacia, la posicion ingresada no corresponde a una posicion valida");
		DNodo<E> nodo = checkPosition(p);
		nodo.getAnterior().setSiguiente(nodo.getSiguiente());
		nodo.getSiguiente().setAnterior(nodo.getAnterior());
		nodo.setSiguiente(null);
		nodo.setAnterior(null);
		retorno = nodo.element();
		nodo.setElement(null);
		tamanio--;
		return retorno ;
	}
	
	public Iterable<Position<E>> positions(){
		PositionList<Position<E>> retorno = new ListaDoblementeEnlazada<Position<E>>();
		DNodo<E> pivote = header.getSiguiente();
		while(pivote != trailer) {
			retorno.addLast(pivote);
			pivote = pivote.getSiguiente();
		}
		return retorno;
	}
	
	public Iterator<E> iterator(){
		return new ElementIterator<E>(this);
	}
	
	public E set(Position<E> p, E element ) throws InvalidPositionException {
		E retorno; 
		if(tamanio == 0) throw new InvalidPositionException("Error, la posicion ingresada no corresponde a una posicion valida");
		DNodo<E> nodo =  checkPosition(p);
		retorno =  nodo.element();
		nodo.setElement(element);
		return retorno ; 
	}
	
}
