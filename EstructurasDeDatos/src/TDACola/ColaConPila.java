package TDACola;

import TDAPila.Stack;
import TDAPila.PilaEnlazada;
import Excepciones.EmptyStackException;
import Excepciones.EmptyQueueException;

public class ColaConPila<E> implements Queue<E> {
	Stack<E> pila;
	
	public ColaConPila() {
		pila= new PilaEnlazada<E>();
	}
	
	public int size() { return pila.size(); } 
	
	public boolean isEmpty() { return pila.isEmpty(); }
	
	public void enqueue(E element){
		try { 
			Stack<E> aux = new PilaEnlazada<E>();
			while(!pila.isEmpty())
				aux.push(pila.pop());
			pila.push(element);
			while(!aux.isEmpty())
				pila.push(aux.pop());
			
		}
		catch(EmptyStackException e) { e.printStackTrace(); } 
	}
	
	public E dequeue()  throws EmptyQueueException{
		if(pila.isEmpty()) throw new EmptyQueueException("Error, no se puede desapilar de una cola vacia");
		E retorno= null;
		try { 
			retorno= pila.pop();
		}
		catch (EmptyStackException e) { e.printStackTrace(); } 
		return retorno;
	}

	public E front() throws EmptyQueueException{
		if(pila.isEmpty()) throw new EmptyQueueException("Error, no se puede desapilar de una cola vacia");
		E retorno= null;
		try { 
			retorno= pila.top();
		}
		catch (EmptyStackException e) { e.printStackTrace(); } 
		return retorno;
	}
}
