package TDACola;

public class Nodo <E>{
	private E elemento;
	private Nodo<E> siguiente;

	public Nodo( E elemento,Nodo<E> siguiente) {
			this.elemento = elemento;
			this.siguiente = siguiente;
	}
	
	public void set(E element) { this.elemento = element; }
	
	public E getElemento() { return elemento; }
	
	public void setSiguiente (Nodo<E> nodo) { siguiente = nodo; }
	
	public Nodo<E> getSiguiente(){  return siguiente ; }


}
