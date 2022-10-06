package TDAPila;
import Excepciones.EmptyStackException;
public class PilaEnlazada<E> implements Stack<E> {
	private Nodo<E> head;
	private int tamanio;
	
	public PilaEnlazada(){
		head= null;
		tamanio= 0;
	}
	
	public E top()throws EmptyStackException{
		 if(tamanio == 0) throw new EmptyStackException  ("Error, no se puede obtener el tope de una pila vacia");
		 return head.getElemento();
	}
	
	public void push (E element){
		head=  new Nodo<E>(element, head);
		tamanio++;
	}
	
	public E pop()throws EmptyStackException{ 
		Nodo<E> nodoAux= head;
		if(tamanio == 0) throw new EmptyStackException("Error, no se puede desapilar de una pila vacia");
		tamanio--;
		head= head.getSiguiente();
		nodoAux.setSiguiente(null);
		return nodoAux.getElemento();
	}
	
	public boolean isEmpty() { 
		return tamanio == 0;
	}
	
	public int size() { 
		return tamanio;
	}
	
}
