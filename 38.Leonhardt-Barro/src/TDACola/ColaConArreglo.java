package TDACola;

import Exceptions.*;

/**
 * Implementacion Cola con arreglo circular
 * @author Jonathan Alejandro Leonhardt,LU : 134726.
 */
public class ColaConArreglo<E> implements Queue<E> {
    
	private E[] q;
	private int frente,rabo;
	private static final int longitud=10;
	
	/**
	 * Crea una cola con arreglor circular de tamaño =10 e inicializa frente y rabo.
	 */
	public ColaConArreglo() {
		 q= (E[]) new Object[longitud];
		 frente=0;
		 rabo=0;
	}
	
	/**
	 * Devuelve la cantidad de elementos en la cola.
	 * @return Cantidad de elementos en la cola.
	 */
	public int size() {
		return (q.length-frente+rabo)%q.length;
	}
    
	/**
	 * Consulta si la cola está vacía.
	 * @return Verdadero si la cola está vacía, falso en caso contrario.
	 */
	public boolean isEmpty() {
		return frente==rabo;
	}
    
	/**
	 * Inspecciona el elemento que se encuentra en el frente de la cola.
	 * @return Elemento que se encuentra en el frente de la cola.
	 * @throws EmptyQueueException si la cola está vacía.
	 */
	public E front() throws EmptyQueueException {
		if (frente==rabo)
			throw new EmptyQueueException("Cola vacia");
		return q[frente];
	}
	
	/**
	 * Inserta un elemento en el fondo de la cola.
	 * @param element Nuevo elemento a insertar.
	 */
	public void enqueue(E elem) {
		if(size()==q.length-1) {
			E[] aux= copiar(frente);
			rabo=size();
		    frente=0;
			q=aux;
		}
		q[rabo]=elem;
		rabo=(rabo+1)%q.length;
	}

	/**
	 * Remueve el elemento en el frente de la cola.
	 * @return Elemento removido.
	 * @throws EmptyQueueException si la cola está vacía.
	 */
	public E dequeue() throws EmptyQueueException {
		E aux;
		if(frente==rabo)
			throw new EmptyQueueException("Cola vacia");
		else {
			aux= q[frente];
			q[frente]=null;
			frente=(frente+1)%q.length;
		}
		return aux;
	}
    
	/**
	 * Crea un arreglo con el doble de tamaño y copia sus elementos
	 * @param Frente del arreglo
	 * @return Un arreglo con el doble tamaño
	 */
	private E[] copiar(int n) {
		E[] aux=(E[]) new Object[2*q.length];
		  for(int j=0;j<size();j++){
			  aux[j]=q[n];
			  n=(n+1)%q.length;  
		  }
		 return aux;
	}
}
