package TDAColaCP;
import java.util.Comparator;

import Auxiliares.Entrada;
import Auxiliares.Entry;
import Exceptions.EmptyPriorityQueueException;
import Exceptions.InvalidKeyException;

/**
 * Clase ColaConPriordadHeap<K,V>
 * @author Nahuel Contreras, Nicolas Marini.
 * Clase que implementa PriorityQueue<K,V> con Heap.
 */
public class ColaConPrioridadHeap<K,V> implements PriorityQueue<K,V> {
	
	protected Entrada<K,V> [] elementos;
	protected Comparador<K> comp;
	protected int tamanio;
	
	/**
	 * Constructor de la clase ColaConPrioridad, que llama a otro constructor de la misma.
	 * @param maxElems cantidad maxima de elementos.
	 * @param parametroComp comparador de la clase.
	 */
	public ColaConPrioridadHeap(int maxElems, Comparador<K> parametroComp) {
		elementos = (Entrada<K,V> []) new Entrada[maxElems];
		comp = parametroComp;
		tamanio = 0;
	}
	
	/**
	 * Constructor de la clase ColaConPrioridad, que llama a otro constructor de la misma.
	 * @param maxElems cantidad maxima de elementos.
	 */
	public ColaConPrioridadHeap(int maxElems) {
		this(maxElems,new Comparador<K>());
	}
	
	public ColaConPrioridadHeap(Comparador<K> compa) {
		tamanio=0 ;
		elementos =(Entrada<K,V> []) new Entrada[10];
		this.comp = compa;
	}
	
	
	public int size() {
		return tamanio;
	}
	
	public boolean isEmpty() {
		return tamanio == 0;
	}
	
	public Entry<K,V> min() throws EmptyPriorityQueueException{
		if(tamanio == 0)
			throw new EmptyPriorityQueueException("No se puede retornar el minimo, porque no hay nada ingresado previamente");
		return elementos[1];
	} 
	
	public Entry<K,V> insert(K key, V value) throws InvalidKeyException{
		if (tamanio == elementos.length - 1) {
			Entrada<K, V>[] Aux = (Entrada<K, V>[]) new Entrada[elementos.length * 3];
			for (int i = 1; i < elementos.length; i++)
				Aux[i] = elementos[i];
			elementos = Aux;
		}
		
		Entrada<K,V> retorno = new Entrada<K,V>(checkKey(key), value);
		tamanio++;
		elementos[tamanio] = retorno;
		
		int i = tamanio;
		boolean seguir = true;
		while(i > 1 && seguir) {
			Entrada<K,V> elemActual = elementos[i];
			Entrada<K,V> elemPadre = elementos[i/2];
			if( comp.compare(elemPadre.getKey(), elemActual.getKey()) > 0) {
				Entrada<K,V> aux = elementos[i];
				elementos[i] = elementos[i/2];
				elementos[i/2] = aux;
				i /= 2;
			} else 
				seguir = false;
		}
		return retorno;
	}
	
	public Entry<K,V> removeMin() throws EmptyPriorityQueueException{
		if(tamanio == 0)
			throw new EmptyPriorityQueueException("Error, no se puede eliminar en una cola vacia");
		Entrada<K,V> retorno = elementos[1];
		elementos[1] = elementos[tamanio];
		elementos[tamanio] = null;
		tamanio--;
		int i = 1;
		boolean seguir = true;
		while(seguir) {
			int hijoIzquierdo = i * 2;
			int hijoDerecho = (i * 2) + 1;
				
			boolean tieneHijoIzquierdo = hijoIzquierdo <= tamanio;
			boolean tieneHijoDerecho = hijoDerecho <= tamanio;
			
			if(!tieneHijoIzquierdo)
				seguir = false;
			else {
				int menor = hijoIzquierdo;
				if(tieneHijoDerecho) 
					if(comp.compare(elementos[hijoIzquierdo].getKey(), elementos[hijoDerecho].getKey()) > 0)
						menor = hijoDerecho;
				if (comp.compare(elementos[menor].getKey(), elementos[i].getKey()) < 0) {
					Entrada<K, V> aux = elementos[i];
					elementos[i] = elementos[menor];
					elementos[menor] = aux;
					i = menor;
				} else 
					seguir = false;
			}
		}
		return retorno;
	}

	private K checkKey(K key) throws InvalidKeyException {
		try {
			comp.compare(key, key);
		} catch (ClassCastException | NullPointerException e) {
			throw new InvalidKeyException("error: la clave no es comparable");
		}
		return key;
	}
}

