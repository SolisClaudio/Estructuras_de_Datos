package TDAPila;
import Excepciones.EmptyStackException;
public class PilaEnlazada<E> implements Stack<E> {
	private Nodo<E> head;
	private int tama�o;
	
	public PilaEnlazada() {
		head=null;
		tama�o=0;
	}
	
	public E top() throws EmptyStackException {
		 if(tama�o==0) throw new EmptyStackException  ("Error, no se puede obtener el tope de una pila vacia");
		 return head.getElemento();
	}
	
	public void push (E element) {
		head =  new Nodo<E>(element, head);
		tama�o++;
	}
	
	public E pop() throws EmptyStackException { 
		Nodo<E> nodoAux = head;
		if(tama�o==0) throw new EmptyStackException("Error, no se puede desapilar de una pila vacia");
		tama�o--;
		head =head.getSiguiente();
		nodoAux.setSiguiente(null);
		return nodoAux.getElemento();
	}
	
	public boolean isEmpty() { return tama�o==0 ; }
	
	public int size() { return tama�o ; }
	
}
