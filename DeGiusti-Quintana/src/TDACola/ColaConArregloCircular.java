package TDACola;
import Exceptions.*;
/**
 * Clase ColaConArregloCircular utilizando una estructura con arreglo circular, implementa la interfaz Queue.
 * @author De Giusti Camila - Quintana Alejo Joaquin
 *
 * @param <E>
 */
public class ColaConArregloCircular<E> implements Queue<E> {
	private int front;
	private int rear;
	private E[] arreglo;
	private static final int longitud = 10;
	
	/**
	 * Crea una instancia de ColaConArregloCircular.
	 */
	public ColaConArregloCircular() {
		front = 0;
		rear = 0;
		arreglo = (E[]) new Object[longitud];
	}
	/**
	 * Devuelve la cantidad de elementos en la cola.
	 * @return Cantidad de elementos en la cola.
	 */
	public int size() {
		return ((arreglo.length - front + rear) % arreglo.length);
	}
	/**
	 * Consulta si la cola est� vac�a.
	 * @return Verdadero si la cola est� vac�a, falso en caso contrario.
	 */
	public boolean isEmpty() {
		return front == rear;
	}
	/**
	 * Inspecciona el elemento que se encuentra en el frente de la cola.
	 * @return Elemento que se encuentra en el frente de la cola.
	 * @throws EmptyQueueException si la cola est� vac�a.
	 */
	public E front() throws EmptyQueueException{
		if(isEmpty()) {
			throw new EmptyQueueException("No se puede obtener el frente de una cola vacía");
		}
		return arreglo[front];
	}
	/**
	 * Inserta un elemento en el fondo de la cola.
	 * @param element Nuevo elemento a insertar.
	 */
	public void enqueue(E elem) {
		if(size() == arreglo.length -1) {
			aumentarArreglo();
		}
		arreglo[rear] = elem;
		rear = (rear+1)% arreglo.length;
	}
	/**
	 * Remueve el elemento en el frente de la cola.
	 * @return Elemento removido.
	 * @throws EmptyQueueException si la cola est� vac�a.
	 */
	public E dequeue() throws EmptyQueueException {
		E retorno;
		if((arreglo.length - front + rear) % arreglo.length == 0)
			throw new EmptyQueueException("La cola se encuenctra vacia");
		retorno = arreglo[front];
		arreglo[front] = null;
		front = (front + 1) % arreglo.length;
		return retorno;
	}
	/**
	 * Duplica el tamaño del arreglo.
	 */
	private void aumentarArreglo() {
		E[] nuevoArreglo = (E[]) new Object[arreglo.length*2];
		int size = (arreglo.length - front + rear) % arreglo.length;
		for(int i = 0 ; i < size ; i++) {
			nuevoArreglo[i] = arreglo[front];
			front = (front + 1) % arreglo.length;
		}
		arreglo = nuevoArreglo;
		nuevoArreglo = null;
		front = 0;
		rear = size;
	}
}
