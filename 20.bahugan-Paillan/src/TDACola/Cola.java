package TDACola;

import Exception.EmptyQueueException;
/**
 * 
 * @author Leon Baguhan Sanchez Yuyprayseree y Simon Paillan.
 *
 * @param <E> parametro generico.
 */
public class Cola<E> implements Queue <E> {
	
	protected E [] cola;
	protected int i; 
	protected int f; 
	protected static final int tamanio= 10;
	
	/**
	 * Crea una cola con arreglo circular con un tamaño 10.
	 */
	
	public Cola ()
	{
		cola = (E[]) new Object[tamanio];
		i = 0;
		f= 0;
	}
	
	
	public int size() {
		
		return (cola.length - i + f) % cola.length;
		
	}
	
	/**
	 * Consulta si la cola esta vacia.
	 * @return Verdadero si la cola esta vacia y falso en caso contrario.
	 */
	public boolean isEmpty() {
		
		return i == f;
	}

	/**
	 * Inspecciona el elemento al frente de la cola.
	 * @throws EmptyQueueException si la cola esta vacia.
	 * @return Devuelve el elemento al frente de la cola.
	 */
	public E front() throws EmptyQueueException {
		// retorna el elemento al frente de la cola (i)
		if(isEmpty())
			throw new EmptyQueueException ("Cola vacia");
		
		return cola[i];
	}

	/**
	 * Encola un elemento al final de la cola.
	 * @param element elemento a insertar.
	 */
	public void enqueue(E element) {
		if(size () == cola.length-1)
		{
			E[] aux = copiar (i);
			f= size();
			i = 0;
			
			cola = aux;
		}
		
		cola[f] = element;
		f= (f+1) % cola.length;
	}
	
	/**
	 * Metodo privado que genera un nuevo arreglo con mayor tamaño
	 * @param n Inicio de la cola.
	 * @return Una nueva cola con mayor tamaño.
	 */
	private E[] copiar (int n)
	{
		E[] nuevaCola = (E[]) new Object [2*cola.length]; 
		
		for(int j= 0 ; j < size() ; j++)
		{
			nuevaCola [j] = cola [n];
			n = (n+1)% cola.length;
		}
		
		
		return nuevaCola;
	}
	
	/**
	 * Elimina el elemento al frente de la cola.
	 * @throws EmptyQueueException	cuando la cola esta vacia.
	 * @return Elemento eliminado.
	 */
	
	public E dequeue() throws EmptyQueueException {
		if(i== f )
			throw new EmptyQueueException ("Cola vacia");
			
			E  ElementoEliminado = cola[i]; // guardo el elemento a borrar
			cola[i] = null; // borro el primer elemento de la cola
			i = (i+1) % cola.length;// cambio el indice del primer elemento de la cola
		return ElementoEliminado;
	}

}
