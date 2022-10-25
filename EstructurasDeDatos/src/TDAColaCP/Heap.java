package TDAColaCP;

import Auxiliares.Entry;

import java.util.Comparator;

import Auxiliares.Entrada;
import Excepciones.EmptyPriorityQueueException;
import Excepciones.InvalidKeyException;

public class Heap<K, V> implements PriorityQueue<K, V> {
	protected int size;
	protected Entrada<K,V> [] elementos;
	protected Comparator<K> comparador;
	
	public Heap(Comparator<K> comp) {
		size=0 ;
		elementos =(Entrada<K,V> []) new Entrada[10];
		comparador = comp;
	}
	
	/**
	 * Consulta la cantidad de elementos de la cola.
	 * @return Cantidad de elementos de la cola.
	 */	
	public int size() {
		return size;
	}
	
	/**
	 * Consulta si la cola est� vac�a.
	 * @return Verdadero si la cola est� vac�a, falso en caso contrario.
	 */
	public boolean isEmpty() {
		return size == 0 ;
	}
	
	/**
	 * Devuelve la entrada con menor prioridad de la cola.
	 * @return Entrada con menor prioridad.
	 * @throws EmptyPriorityQueueException si la cola est� vac�a.
	 */
	public Entry<K,V> min()throws EmptyPriorityQueueException{
		if(size == 0) throw new EmptyPriorityQueueException("Error, no se puede obtener el minimo de una cola vacia.");
		return elementos[1];
	}
	
	/**
	 * Inserta un par clave-valor y devuelve la entrada creada.
	 * @param key Clave de la entrada a insertar.
	 * @param value Valor de la entrada a insertar.
	 * @return Entrada creada.
	 * @throws InvalidKeyException si la clave es inv�lida.
	 */
	public Entry<K,V> insert(K key,V value) throws InvalidKeyException{
		checkKey(key);
		Entrada<K,V> nuevaEntrada = new Entrada<K,V>(key, value);
		Entrada<K,V> padre, actual;
		if(size == elementos.length -1 ) extenderArreglo();
		size++;
		elementos[size] = nuevaEntrada;
		int posicion = size;
		padre = elementos[posicion/2];
		actual = elementos[posicion];
		while(posicion!= 1 && comparador.compare(padre.getKey() , actual.getKey()) > 0) {
			nuevaEntrada = padre;
			elementos[ posicion/2 ] = actual;
			elementos[ posicion ] = nuevaEntrada ;
			posicion = posicion/2 ;
			padre = elementos[posicion/2];
			actual = elementos[posicion];
		}
		return elementos[posicion];
	}
	
	/**
	 * Remueve y devuelve la entrada con menor prioridad de la cola.
	 * @return Entrada con menor prioridad.
	 * @throws EmptyPriorityQueueException si la cola est� vac�a.
	 */
	public Entry<K,V> removeMin()throws EmptyPriorityQueueException{
		if (size == 0) throw new EmptyPriorityQueueException("Error, no se puede obtener el minimo de una cola con prioridad vacia.");
		Entrada<K,V> aux = elementos [1];
		elementos[1] = elementos[size];
		elementos[size] = aux ;
		size--; 
		int pos = 1 ;
		int hijoIzq, hijoDer, menor ;
		boolean ordenado = false ;
		while(pos <= size && !ordenado) {
			hijoIzq = pos*2 ;
			hijoDer = pos*2 + 1 ;
			if (hijoIzq <= size) {//Si tiene hijo izquierdo
				if(hijoDer <=  size) { //Si tiene hijo derecho
					if(comparador.compare(elementos[hijoIzq].getKey(), elementos[hijoDer].getKey()) < 0 ) { //si el izquierdo es el menor
						menor = hijoIzq ;
					}
					else menor = hijoDer;
				}
				else menor = hijoIzq;
				//Verifico si esta ordenado
				if( comparador.compare(elementos[pos].getKey(), elementos[menor].getKey()) > 0){
					aux = elementos [menor];
					elementos[menor] = elementos[pos];
					elementos[pos] = aux ;
					pos = menor ;
				}
				else ordenado = true ;
			}
			else ordenado = true;
		}
		aux = elementos[size+1];
		elementos[size + 1] = null ;
		return aux;
	}
	
	private void extenderArreglo() {
		Entrada<K,V>[] nuevoArreglo = (Entrada<K,V> []) new Entrada[elementos.length * 2];
		for(int i = 0 ; i<= size; i++)
			nuevoArreglo[i] = elementos[i];
		elementos = nuevoArreglo ;
	}
	
	private void checkKey(K key) throws InvalidKeyException {
		if( key == null) throw new InvalidKeyException("Error, clave invalida");
	}
}
