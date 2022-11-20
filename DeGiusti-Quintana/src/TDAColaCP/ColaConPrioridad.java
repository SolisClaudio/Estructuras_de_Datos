package TDAColaCP;
import Auxiliar.Entry;
import Exceptions.EmptyPriorityQueueException;

import java.util.Comparator;
import Exceptions.*;
/**
 * Clase ColaConPrioridad, implementada con un heap.
 * @author De Giusti Camila - Quitana Alejo Joaquin.
 *
 * @param <K>
 * @param <V>
 */
public class ColaConPrioridad<K, V> implements PriorityQueue<K, V> {

	private Entrada<K, V>[] componentes;
	private Comparator<K> comp;
	private int size;

	/**
	* Crea un nuevo Heap con Comparador y cantidad de entradas dados.
	* 
	* @param c   Comparador
	* @param max Cantidad de entradas.
	*/
	public ColaConPrioridad(Comparator<K> c, int max) {
		componentes = new Entrada[max];
		comp = c;
		size = 0;
	}
	/**
	* Crea un nuevo Heap con Comparador dado y 200 entradas.
	* 
	* @param c Comparador.
	*/
	public ColaConPrioridad(Comparator<K> c) {
		this(c, 200);
	}
	/**
	 * Consulta la cantidad de componentes de la cola.
	 * @return Cantidad de componentes de la cola.
	 */
	public int size() {
		return size;
	}
	/**
	 * Consulta si la cola est� vac�a.
	 * @return Verdadero si la cola est� vac�a, falso en caso contrario.
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	/**
	 * Devuelve la entrada con menor prioridad de la cola.
	 * @return Entrada con menor prioridad.
	 * @throws EmptyPriorityQueueException si la cola est� vac�a.
	 */
	public Entry<K, V> min() throws EmptyPriorityQueueException {
		if (size == 0)
			throw new EmptyPriorityQueueException("error: CP vacia");
		return componentes[1];
	}
	/**
	 * Inserta un par clave-valor y devuelve la entrada creada.
	 * @param key Clave de la entrada a insertar.
	 * @param value Valor de la entrada a insertar.
	 * @return Entrada creada.
	 * @throws InvalidKeyException si la clave es inv�lida.
	 */
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		if (size == componentes.length - 1) {
			Entrada<K, V>[] A = (Entrada<K, V>[]) new Entrada[componentes.length * 2];
			for (int i = 1; i < componentes.length; i++)
				A[i] = componentes[i];
			componentes = A;
		}
		K claveAux = checkKey(key);
		Entrada<K, V> e = new Entrada<K, V>(claveAux, value);
		size++;
		componentes[size] = e;

		boolean seguir = true;
		int i = size;
		while (i > 1 && seguir) {
			
			Entrada<K, V> hijo = componentes[i];
			Entrada<K, V> padre = componentes[i / 2];
			
			if (comp.compare(padre.getKey(), hijo.getKey()) > 0) {
				Entrada<K, V> aux = componentes[i];
				componentes[i] = componentes[i / 2];
				componentes[i / 2] = aux;
				i /= 2;
			} 
			else {
				seguir = false;
			}
		}
		return e;
	}
	/**
	 * Chequea que la clave pasada por parámetro sea válida.
	 * @param key una clave.
	 * @throws InvalidKeyException si la clave por parámetro es inválida.
	 */
	private K checkKey(K key) throws InvalidKeyException {
		try {
			comp.compare(key, key);
		}catch(ClassCastException | NullPointerException e){
			throw new InvalidKeyException("error: la clave no es comparable");
		}
		return key;
	}
	/**
	 * Remueve y devuelve la entrada con menor prioridad de la cola.
	 * @return Entrada con menor prioridad.
	 * @throws EmptyPriorityQueueException si la cola est� vac�a.
	 */
//	public Entry<K, V> removeMin() throws EmptyPriorityQueueException {
//		if (size == 0) {
//			throw new EmptyPriorityQueueException("error al eliminar: cola vacia");
//		}
//		Entrada<K, V> aux = componentes[1];
//		componentes[1] = componentes[size];
//		componentes[size] = null;
//		size--;
//		
//		int i = 1;
//		boolean seguir = true;
//		while (seguir) {
//			int hijoIzquierdo = i * 2;
//			int hijoDerecho = (i * 2) + 1;
//			
//			boolean hasLeft = hijoIzquierdo <= size;
//			boolean hasRight = hijoDerecho <= size;
//			if (!hasLeft) {
//				seguir = false;
//			}
//			else {
//				int menor = hijoIzquierdo;
//				
//				if(hasRight && comp.compare(componentes[hijoIzquierdo].getKey(), componentes[hijoDerecho].getKey()) > 0){
//						menor = hijoDerecho;
//				}
//				if(comp.compare(componentes[menor].getKey(), componentes[i].getKey()) < 0){
//					Entrada<K, V> aux2 = componentes[i];
//					componentes[i] = componentes[menor];
//					componentes[menor] = aux2;
//					i = menor;
//				} else
//					seguir = false;
//			}
//		}
//		return aux;
//	}
	public Entry<K,V> removeMin()throws EmptyPriorityQueueException{
		if (size == 0) throw new EmptyPriorityQueueException("Error, no se puede obtener el minimo de una cola con prioridad vacia.");
		Entrada<K,V> aux = componentes [1];
		componentes[1] = componentes[size];
		componentes[size] = aux ;
		size--; 
		int pos = 1 ;
		int hijoIzq, hijoDer, menor ;
		boolean ordenado = false ;
		while(pos <= size && !ordenado) {
			hijoIzq = pos*2 ;
			hijoDer = pos*2 + 1 ;
			if (hijoIzq <= size) {//Si tiene hijo izquierdo
				if(hijoDer <=  size) { //Si tiene hijo derecho
					if(comp.compare(componentes[hijoIzq].getKey(), componentes[hijoDer].getKey()) < 0 ) { //si el izquierdo es el menor
						menor = hijoIzq ;
					}
					else menor = hijoDer;
				}
				else menor = hijoIzq;
				//Verifico si esta ordenado
				if( comp.compare(componentes[pos].getKey(), componentes[menor].getKey()) > 0){
					aux = componentes [menor];
					componentes[menor] = componentes[pos];
					componentes[pos] = aux ;
					pos = menor ;
				}
				else ordenado = true ;
			}
			else ordenado = true;
		}
		aux = componentes[size+1];
		componentes[size + 1] = null ;
		return aux;
	}
}