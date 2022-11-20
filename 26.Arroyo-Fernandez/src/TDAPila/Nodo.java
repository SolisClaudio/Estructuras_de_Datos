package TDAPila;



public class Nodo <E>{
	private E element;
	private Nodo<E> next;
	
	public Nodo (E e, Nodo<E> n){
		element = e;
		next = n;
	}
	
	//Setters 
	public void setElement(E e) {
		element = e;
	}
	public void setNext(Nodo<E> n) {
		next = n;
	}
	
	//Getters
	public E getElement() {
		return element;
	}
	
	public Nodo<E> getNext(){
		return next;
	}

}
