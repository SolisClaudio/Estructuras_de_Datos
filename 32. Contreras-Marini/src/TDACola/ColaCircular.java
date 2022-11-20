package TDACola;
import Exceptions.EmptyQueueException;

/**
 * Clase ColaCircular<E>
 * @author Nahuel Contreras, Nicolas Marini.
 * Clase que implementa Queue<E> con una estructura circular.
 */
public class ColaCircular<E> implements Queue<E> {
	
	private E[] cola;
	private int frente;
	private int rabo;
	private static final int longitud = 10;
	
	/**
	 * Constructor de la clase ColaCircular, asigna valores predeterminados a los atributos de la clase.
	 */
	public ColaCircular() {
		cola = (E[]) new Object[longitud];
		frente = rabo = 0;
	}
	
	public int size() {
		return (cola.length - frente + rabo) % cola.length;
	}
	
	public boolean isEmpty() {
		return (size() == 0); 
	}
	
	public void enqueue(E element) {
		if(size() == cola.length - 1) {
			E[] aux = copiar(frente);
			rabo = size();
			frente = 0;
			cola = aux;
		}
		cola[rabo] = element;
		rabo = (rabo+1) % cola.length;
	}
	
	public E dequeue() throws EmptyQueueException {
		if(isEmpty())
			throw new EmptyQueueException("No se puede eliminar el último elemento, porque no hay nada ingresado");
		E aux = cola[frente];
		cola[frente]= null;
		frente =(frente+1)%cola.length;
		return aux;
	}
	
	public E front() throws EmptyQueueException{
		if(isEmpty())
			throw new EmptyQueueException("No se puede inspeccionar el último elemento, porque no hay nada ingresado");
		return cola[frente];
	}
	
	
	/*
	 * Crea una cola el doble de tamaño, con todos los elementos ordenados de la cola que reciba el mensaje
	 * @param parametro Es la primera posicion de la cola que se retornará
	 * @return La nueva cola con todos los elementos ordenados de la cola que reciba el mensaje
	 */
	private E[] copiar(int parametro) {
		E[] retorno = (E[]) new Object[cola.length * 2];
		for(int aux = 0; aux < size(); aux++) {
			retorno[aux]=cola[parametro];
			parametro=(parametro+1) % cola.length;
		}
		return retorno;	
	}
}
