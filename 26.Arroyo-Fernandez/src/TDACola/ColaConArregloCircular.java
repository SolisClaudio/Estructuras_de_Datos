package TDACola;

import Exceptions.EmptyQueueException;

public class ColaConArregloCircular <E> implements Queue<E>{
	
	private int head;
	private int tail;
	private E[] queue;


	public ColaConArregloCircular() {
		head = 0;
		tail = 0;
		queue = (E[]) new Object[20];
	}
	
	@Override
	public int size() {
		return ((queue.length)-head+tail)%queue.length;
	}
	 
	
	
	@Override
	public boolean isEmpty() {
		return tail == head;
	}
	
	@Override
	public E front() throws EmptyQueueException{
		if (isEmpty()) {
			throw new EmptyQueueException ("Es imposible solicitar el frente de una cola vacia.");
		}
		return queue[head];
	}

	@Override
	public void enqueue (E element) {
			if (size()==queue.length-1)
				expandirCola();
			queue[(tail)%queue.length] = element;
			tail = (tail+1)%(queue.length);
		}
	
	@Override
	public E dequeue () throws EmptyQueueException{
		if (isEmpty()) 
			throw new EmptyQueueException ("No hay elementos en la cola para entregar.");
		E toReturn = queue[head];
		queue[head] = null;
		head = (head+1)%(queue.length);
		return toReturn;		
	}
	
	
	/**
	 * Metodo privado que duplica el tamaño del arreglo de la cola circular, e inserta en el los elementos que contenia 
	 * anteriormente.
	 */
	private void expandirCola() {
		E[] newQueue = (E[]) new Object[queue.length*2];
		try {
			int i = 0;
			while(!isEmpty()) {
				newQueue[i] = dequeue();
				i++;
			}
			head = 0;
			tail = i;
			queue = newQueue;
		}catch(EmptyQueueException e){
			System.out.println(e.getMessage());
		}

	}
	

}
