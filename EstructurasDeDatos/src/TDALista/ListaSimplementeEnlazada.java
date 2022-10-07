package TDALista;

import Auxiliares.Position;
import Auxiliares.Nodo;
import java.util.Iterator;
import Excepciones.BoundaryViolationException;
import Excepciones.EmptyListException;
import Excepciones.InvalidPositionException;

public class ListaSimplementeEnlazada<E> implements PositionList<E>{
		private Nodo<E> head;
		private int tamanio;
		
		public ListaSimplementeEnlazada() {
			head=null;
			tamanio=0;
		}
		
		public int size() {
			return tamanio;
		}
		
		public boolean isEmpty() {
			return tamanio == 0;
		}
		
		public Position<E> first() throws EmptyListException {
			if(tamanio == 0) throw new EmptyListException("Error, no se puede obtener el primer elemento de una lista vacia");
			return head;
		}
		
		public Position<E> last() throws EmptyListException {
			if(tamanio == 0) throw new EmptyListException("Error, no se puede obtener el primer elemento de una lista vacia");
			Nodo<E> aux = head;
			while(aux.getSiguiente()!=null)
				aux = aux.getSiguiente();
			return aux;
		}
		
		private Nodo<E> checkPosition(Position<E> p) throws InvalidPositionException {
			Nodo<E> retorno=null;
			try {
				if(p==null || tamanio==0) throw new InvalidPositionException("Error, la posicion ingresada no corresponde a una posicion valida.");
				retorno = (Nodo<E>) p;
			}
			catch(ClassCastException e) { throw new InvalidPositionException("Error, la posicion ingresada no corresponde a una posicion valida."); }
			return retorno;
		}
		
		public E set(Position<E> p, E element) throws InvalidPositionException{
			Nodo<E> nodo =  checkPosition(p);
			E retorno= nodo.element();
			nodo.setElement(element);
			return retorno;
		}
		public void addFirst(E element) {
			Nodo<E> nodo = new Nodo<E>(head,element);
			head=nodo;
			tamanio++;
			
		}
		public void addLast(E element) {
			Nodo<E> aux = head; 
			Nodo<E> nodo = new Nodo<E>(null,element);
			if(tamanio==0) head= nodo;
				else {
					while(aux.getSiguiente()!=null)
						aux=aux.getSiguiente();
					aux.setSiguiente(nodo);
				}
			tamanio++;
		}
		public void addAfter(Position<E> p, E element) throws InvalidPositionException{
			Nodo<E> nodo = checkPosition(p);
			Nodo<E> nuevoNodo =  new Nodo<E>(nodo.getSiguiente(),element);
			nodo.setSiguiente(nuevoNodo);
			tamanio++;
		}
		public void addBefore(Position<E> p , E element) throws InvalidPositionException{
			Nodo<E> aux= head;
			Nodo<E> nodo = checkPosition(p);
			Nodo<E> nuevoNodo =  new Nodo<E>(nodo,element);
			if(nodo!=head) {
				while(aux.getSiguiente()!=nodo)
					aux=aux.getSiguiente();
				aux.setSiguiente(nuevoNodo);
			}
			else head=nuevoNodo;
			
			tamanio++;
		}
		public E remove(Position<E> p) throws InvalidPositionException{
			E retorno ;
			Nodo<E> aux= head;
			Nodo<E> nodo = checkPosition(p);
			if(nodo!=head) {
				while(aux.getSiguiente()!=nodo)
					aux=aux.getSiguiente();
				aux.setSiguiente(nodo.getSiguiente());
			}
			else head=nodo.getSiguiente();
			nodo.setSiguiente(null);
			retorno = nodo.element();
			nodo.setElement(null);
			tamanio--;
			return retorno;
		}
		
		public Iterator<E> iterator(){
			return new ElementIterator<E>(this);
		}
		public Iterable<Position<E>> positions(){
			PositionList<Position<E>> retorno = new ListaSimplementeEnlazada<Position<E>>();
			if(tamanio!=0) {
				Nodo<E> nodo = head;
				while(nodo!=null) {
					retorno.addLast(nodo);
					nodo=nodo.getSiguiente();
				}
			}
			return retorno;
		}
		public Position<E> prev(Position<E> p) throws InvalidPositionException,BoundaryViolationException{
			Nodo<E> nodo = checkPosition(p);
			if(nodo==head) throw new BoundaryViolationException("Error, no se puede obtner la posicion previa al primer elemento de la lista.");
			Nodo<E> aux =  head; 
			while(aux.getSiguiente()!=nodo && aux.getSiguiente()!=null)
				aux=aux.getSiguiente();
			return aux;
		}
		public Position<E> next(Position<E> p) throws InvalidPositionException,BoundaryViolationException{
			Nodo<E> nodo = checkPosition(p);
			if(nodo.getSiguiente()==null) throw new BoundaryViolationException("Error, no se puede obtener el elemento siguiente del ultimo elemento de la lista.");
			return nodo.getSiguiente();
		}
		public void Invertir() {
			Nodo<E> ultimo , aux , punta ;
			ultimo=head;
			if(tamanio!=0) {
				while(ultimo.getSiguiente()!=null)
					ultimo=ultimo.getSiguiente();
			punta=head;
			while(ultimo!=head) {
				aux=head;
				head=punta.getSiguiente();
				punta.setSiguiente(head.getSiguiente());
				head.setSiguiente(aux);;
				
			}
			
			}
		}
}
