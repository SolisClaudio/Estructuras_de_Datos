package TDAPila;
import Excepciones.EmptyStackException;
public class PilaconArreglo<E> implements Stack<E>{
	protected E[] arreglo;
	protected int size;
	
	public PilaconArreglo() {
		size=0;
		arreglo = (E[]) new Object[10];
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size==0;
	}
	
	public E top() throws EmptyStackException{
		if(size==0) throw new EmptyStackException("Error, no se puede obtener el tope de una pila vacia");
		return arreglo[size-1];
	}
	
	public E pop() throws EmptyStackException{
		E retorno;
		if(size==0) throw new EmptyStackException ( "Error, no se puede desapilar una pila vacia");
		size--;
		retorno=arreglo[size];
		arreglo[size]=null;
		return retorno;
	}
	
	private void extenderTamaño() {
		E[] nuevoArreglo = (E[]) new Object[size*2];
		for(int i=0 ; i<size ; i++) {
			nuevoArreglo[i] = arreglo[i];
			arreglo[i]=null;
		}
		arreglo=nuevoArreglo;
	}
	
	public void push(E element) {
		if(size==arreglo.length-1) this.extenderTamaño();
		arreglo[size]=element;
		size++;
	}
	public void invertir() {
		E aux;
		int puntero1=0;
		int puntero2=size-1;
		while(puntero1!=puntero2 && puntero2!=puntero1-1) {
			aux=arreglo[puntero1];
			arreglo[puntero1]=arreglo[puntero2];
			arreglo[puntero2]= aux;
			puntero1++;
			puntero2--;
		}
	}
}
