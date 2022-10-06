package TDACola;

import Excepciones.EmptyQueueException;

public class ColaConArregloCircular<E> implements Queue<E> {
	
	protected int f, r ; 
	protected E[] arreglo;
	
	
	@SuppressWarnings("unchecked")
	public  ColaConArregloCircular() { 
		f=0;
		r=0;
		arreglo = (E[]) new Object[10];
	}
	
	public E dequeue() throws EmptyQueueException { 
		if ( f== r) throw new EmptyQueueException("Error, no se puede desencolar de una cola vacia");
		E retorno= arreglo[f];
		arreglo [f]= null ; 
		f= (f+1)%arreglo.length ; 
		return retorno;
	}
	private void extenderArreglo(int N) {
		@SuppressWarnings("unchecked")
		E[] nuevoArreglo= (E[]) new Object[N*2];
		for (int i = 0; i<N; i++){
			nuevoArreglo[i]= arreglo[f];
			arreglo[f]= null;
			f= (f+1)%N;
		}
		arreglo= nuevoArreglo;
		f= 0;
		r= N-1;
	}
	
	public void enqueue(E element) {
		int N= arreglo.length;
		if ((N-f+r)%N == N-1 ) extenderArreglo(N);
		arreglo[r]= element ; 
		r= (r+1)%arreglo.length;
		
	}
	
	public int size() {
		int  N= arreglo.length;
		return (N-f+r)%N;
		
	}
	
	public boolean isEmpty() {
		return f == r;
	}
	
	public E front() throws EmptyQueueException {
		
	if(f == r) throw new EmptyQueueException("Error, no se puede obtener el frente de una cola vacia");
	return arreglo[f];
	}
	
	
	
}
