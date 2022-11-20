package TDAColaCP;

import java.util.Comparator;

import Exception.EmptyPriorityQueueException;
import Exception.InvalidKeyException;
/**
 * 
 * @author Leon Baguhan Sanchez Yuyprayseree y Simon Paillan
 *
 * @param <K> Clave generica.
 * @param <V> Valor generica.
 */
public class ColaConPrioridad<K,V> implements PriorityQueue<K,V> {

	protected Entrada <K,V> [] elementos;
	protected Comparator <K> comp;
	protected int size;
	
	/**
	 * Crea una nueva ColaConPrioridad con cantidad de entradas dados y Comparador.
	 * @param max Cantidad de entradas.
	 * @param c   Comparador
	 */
	@SuppressWarnings("unchecked")
	public ColaConPrioridad (int max,Comparator<K> c )
	{
		elementos =(Entrada<K,V> []) new Entrada [max];
		comp = c;
		size = 0;
	}
	/**
	 * Crea una nueva ColaConPrioridad vacia.
	 */
	public ColaConPrioridad() {
		this(100, new Comparador<K>());
	}

	/**
	 * Crea un nuevo ColaConPrioridad con 100 entradas y un comparador dado .
	 * 
	 * @param c Comparador
	 */
	public ColaConPrioridad(Comparator<K> c) {
		this(100, c);
	}
	
	/**
	 *Consulta la cantidad de elementos de la cola.
	 *@return La cantidad de elementos.
	 */
	public int size() {
		
		return size;
	}

	/**
	 * Consulta si la cola esta vacia.
	 * @return Verdadero si la cola es vacia y falso en el caso contrario.
	 */
	public boolean isEmpty() {
		
		return size==0;
	}

	/**
	 * Devuelve la entrada con menor prioridad.
	 * @return El elemento de menor prioridad.
	 * @throws EmptyPriorityQueueException si la cola esta vacia.
	 */
	public Entry<K, V> min() throws EmptyPriorityQueueException {
		if(isEmpty())
			throw new EmptyPriorityQueueException("La Cola esta vacia ");
		
		return elementos[1];
	}

	/**
	 * Inserta y devuelve una entrada con valor key-value .
	 * @param key Clave de la entrada a insertar.
	 * @param value Valor de la entrada a insertar.
	 * @throws InvalidKeyException si key no es valida.
	 * @return Entrada nueva.
	 */
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		
		if(size == elementos.length-1)
		{
			Entrada<K, V>[] aux = (Entrada<K,V> []) new Entrada [3*elementos.length];
			for(int i= 1 ; i< elementos.length ; i++ )
				aux[i] = elementos[i];
				
			elementos = aux;
		}
		
		Entrada <K,V> entrada = new Entrada <K,V> (checkKey(key),value);
		
		boolean seguir = true;
		int indice = size;
		size++;   // mucho muy importante que esto valla antes que elementos [size]
		elementos[size] = entrada;
		while (seguir && indice >1)
		{
			Entrada <K,V> hijo = elementos [indice];
			Entrada <K,V> padre= elementos [indice / 2];
			if(comp.compare(hijo.getKey(), padre.getKey()) < 0) // busca los mayores antes era  " < "
			{
				Entrada <K,V> entradaAux = elementos [indice];
				elementos[indice] = elementos[indice/2];
				elementos[indice/2] = entradaAux;
				indice /= 2;
			}
			else
				seguir = false;
		}
		return entrada;
	}

	/**
	 * Remueve y devuelve la entrada con menor prioridad de la cola.
	 * @return Entrada con menor prioridad.
	 * @throws  EmptyPriorityQueueException si la cola esta vacia.
	 */
	public Entry<K, V> removeMin() throws EmptyPriorityQueueException {
		if(size == 0)
			throw new EmptyPriorityQueueException ("La cola esta vacia");
		Entry<K,V> aux = elementos [1];
		elementos[1] = elementos[size];
		elementos[size] = null;
		size--;
		int i = 1;
		boolean seguir = true;
		
		while (seguir)
		{
			int hi = i*2;
			int hd = (i*2)+1;
			boolean hasLeft = hi <= size;
			boolean hasRight = hd <= size;
			if (!hasLeft)
				seguir = false;
			else
			{
				int menor;
				if(hasRight) 
				{
					if(comp.compare(elementos[hi].getKey(), elementos[hd].getKey() )<0 )  // busca los mayores antes era  " < "
						menor = hi;
					else
						menor = hd;
				}
				else
					menor=hi;
					
				if(comp.compare(elementos[i].getKey(), elementos[menor].getKey()) >0 ) // busca los mayores antes era  " > "
					{
					Entrada <K,V> aux1 = elementos[i];
					elementos [i] = elementos [menor];
					elementos [menor] = aux1;
					i = menor;
					}
				else 
					seguir =false;
			}
		 }
		return aux;
	}
	/**
	 * Verifica si la clave es valida.
	 * @param key Clave a verificar.
	 * @return Devuelve la clave si esta es valida.
	 * @throws InvalidKeyException si la clave es valida o si no esta vacia.
	 */
	private K checkKey(K key) throws InvalidKeyException {
		try {
			comp.compare(key, key);
		} catch (ClassCastException | NullPointerException e) {
			throw new InvalidKeyException("error: la clave no es comparable");
		}
		return key;
	}
}
