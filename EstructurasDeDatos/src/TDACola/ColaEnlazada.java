package TDACola;

import Excepciones.EmptyQueueException;

public class ColaEnlazada<E> implements Queue<E> {
	protected int size;
	protected Nodo<E> head, tail;
	
	public ColaEnlazada() {
		size= 0;
		head= new Nodo<E>(null,null);
		tail= head;
	}
	public E front() throws EmptyQueueException { 
		if(size == 0) throw new EmptyQueueException("Error, no se puede obtener el frente de una cola vacia");
		return head.getElemento();
	}
	
	public E dequeue() throws EmptyQueueException{
		if(size == 0) throw new EmptyQueueException("Error, no se puede obtener el frente de una cola vacia");
		Nodo<E> aux= head;
		E retorno= head.getElemento();
		head= head.getSiguiente();
		aux.setSiguiente(null);
		aux.set(null);
		size--;
		return retorno;
	}
	
	public void enqueue(E element) {
		Nodo<E> aux= new Nodo<E>(element,null);
		if(size!= 0)
			tail.setSiguiente(aux);
		else
			head= aux;
		tail= aux;
		size++;
	}
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}

}
