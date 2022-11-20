package TDALista;

public class DNodo<E> implements Position<E> {

	protected E element;
	protected DNodo<E> prev;
	protected DNodo<E> next;
	
	
	public E element() {
		return element;
	}
	
	public DNodo(E item,DNodo<E> n,DNodo<E> p) {
		element = item;
		this.next = n;
		this.prev =p;
	}
	
	DNodo(E element) {
		this(element,null,null);
	}

	public DNodo<E> getPrev() {
		return prev;
	}

	public void setPrev(DNodo<E> ant) {
		this.prev = ant;
	}

	public DNodo<E> getNext() {
		return next;
	}

	public void setNext(DNodo<E> sig) {
		this.next = sig;
	}

	@Override
	public String toString() {
		return "DNodo [elemento=" + element + ", ant=" + prev + ", sig=" + next + "]";
	}

	public void setElement(E element) {
		this.element = element;
		// TODO Auto-generated method stub
		
	}
	
	
	
	

}
