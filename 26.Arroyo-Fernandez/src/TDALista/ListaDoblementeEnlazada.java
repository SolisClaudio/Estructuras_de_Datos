package TDALista;

import java.util.Iterator;

import Exceptions.BoundaryViolationException;
import Exceptions.EmptyListException;
import Exceptions.InvalidPositionException;

public class ListaDoblementeEnlazada<E> implements PositionList<E> {

	protected DNodo<E> trailer;
	protected DNodo<E> header;
	protected int tamanio;
	
	public ListaDoblementeEnlazada() {
		header = new DNodo<E>(null);
		trailer = new DNodo<E>(null);
		header.setNext(trailer);
		header.setPrev(null);
		trailer.setPrev(header);
		trailer.setNext(null);
		tamanio = 0;
	}
	
	@Override
	public int size() {
		
		return tamanio;
	}

	@Override
	public boolean isEmpty() {
		
		return tamanio == 0;
	}

	@Override
	public Position<E> first() throws EmptyListException {
		
		if (isEmpty())
			throw new EmptyListException("LISTA VACIA");
		
		return header.getNext();
	}

	@Override
	public Position<E> last() throws EmptyListException {
		if (isEmpty())
			throw new EmptyListException("LISTA VACIA");
		
		return trailer.getPrev();
	}

	@Override
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		
		DNodo<E> n = checkPosition(p);
		
		if (n == trailer.getPrev())
			throw new BoundaryViolationException("Next: Suiguiente de ultimo");
		
		return n.getNext();
	}
	
	private DNodo<E> checkPosition(Position<E> p) throws InvalidPositionException{
		DNodo<E> retorno = null;
		try {
			
			if (p == null)
				throw new InvalidPositionException("POSICION NULA");
//			if (p.element() == null)
//				throw new InvalidPositionException("p ELIMINADA PREVIAMENTE");
			if (p == header)
				throw new InvalidPositionException("POSICION INVALIDA");
			if (p == trailer)
				throw new InvalidPositionException("POSICION INVALIDA");
			if(tamanio==0)
				throw new InvalidPositionException("");
			
			retorno =  (DNodo<E>) p;
			
		}catch(ClassCastException e) {
			throw new InvalidPositionException("p NO ES UN NODO DE LISTA");
			
		}
		return retorno;
	}

	@Override
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		DNodo<E> n = checkPosition(p);
		DNodo<E> prev = n.getPrev();
		
		if (prev == header)
			throw new BoundaryViolationException("NO HAY UN ANTERIOR PARA LA PRIMERA POSICION");
		
		return prev;
	}

	@Override
	public void addFirst(E element) {
		
		DNodo<E> nuevo = new DNodo<E>(element,null,null);
		
		if (tamanio == 0) {
			header.getNext().setPrev(nuevo);
			nuevo.setNext(header.getNext());
			nuevo.setPrev(header);
			header.setNext(nuevo);
			trailer.setPrev(nuevo);
		}
		else {
			header.getNext().setPrev(nuevo);
			nuevo.setNext(header.getNext());
			nuevo.setPrev(header);
			header.setNext(nuevo);
		}
		
		tamanio++;
		
	}

	@Override
	public void addLast(E element) {
		
		
		DNodo<E> nuevo = new DNodo<E>(element,trailer,trailer.getPrev());
		trailer.getPrev().setNext(nuevo);
		trailer.setPrev(nuevo);
		tamanio++;
		
	}

	@Override
	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		
		DNodo<E> pos = checkPosition(p);
		DNodo<E> nuevo = new DNodo<E>(element);
		nuevo.setNext(pos.getNext());
		nuevo.setPrev(pos);
		nuevo.getNext().setPrev(nuevo);
		pos.setNext(nuevo);
		tamanio++;
		
	}

	@Override
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		
		checkPosition(p);
		try {
			if (p == first())
				addFirst(element);
			else {
				addAfter(prev(p),element);
			}
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public E remove(Position<E> p) throws InvalidPositionException {
		
		DNodo<E> v = checkPosition(p);
		
		if (tamanio == 0)
			throw new InvalidPositionException("LISTA VACIA");
		
		tamanio--;
		DNodo<E> vPrev = v.getPrev();
		DNodo<E> vNext = v.getNext();
		vPrev.setNext(vNext);
		vNext.setPrev(vPrev);
		E vElem = v.element();
		// Desliga la posicion de la lista y la hace invalida
		v.setNext(null);
		v.setPrev(null);
		v.setElement(null);
		return vElem;
	}

	@Override
	public E set(Position<E> p, E element) throws InvalidPositionException {
		
		DNodo<E> n = checkPosition(p);
		E aux = n.element();
		n.setElement(element);
		
		return aux;
	}
	
	@Override
	public Iterator<E> iterator() {
		
		// Creo un elementIteratorsobre la lista this a iterar;
			return new ElementIterator<E>(this);
		}


	
	@Override
	public Iterable<Position<E>> positions() {
			
		PositionList<Position<E>> p = new ListaDoblementeEnlazada<>();
		if (!isEmpty()) {
				
			Position<E> pos = header.getNext();
				
			try {
				while (pos != last()) {
					p.addLast(pos);
					pos = next(pos);
				}
			} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			p.addLast(pos);
		}
			
		return p;
	}

}
