package TDADiccionario;

import java.util.Iterator;

import Exception.InvalidEntryException;
import Exception.InvalidKeyException;
import Exception.InvalidPositionException;
import TDALista.Lista;
import TDALista.Position;
import TDALista.PositionLista;

/**
 * 
 * @author Leon Baguhan Sanchez Yuyprayseree y Simon Paillan
 * 
 * @param <K> Clave generica.
 * @param <V> Valor genrico.
 */
public class DiccionarioHashAbierto <K,V> implements Dictionary <K,V> {

	protected Lista<Entrada<K, V>>[] lista;
	protected int n ;
	protected int N = 13 ;
	protected final double factorDeCarga = 0.9;
	
	/**
	 * Crea un diccionario con hash abierto.
	 * 
	 */
	
	@SuppressWarnings("unchecked")
	public DiccionarioHashAbierto()
	{
		lista= (Lista <Entrada<K, V>> []) new Lista[N];
		n = 0;
		for(int i = 0 ; i < lista.length ; i++)
			lista[i]= new Lista <Entrada<K,V>>();
		
	}
	
	
	/**
	 * Consulta la cantidad de entradas del diccionario.
	 * @return Cantidad de entradas.
	 */
	public int size() {
		
		return n;
	}

	/**
	 * Consulta si el diccionario esta vacio.
	 * @return Verdadero si esta vacio y falso en el caso contrario.
	 */
	
	public boolean isEmpty() {
		
		return n==0;
	}

	/**
	 * Busca una entrada con una clave igual y la retorna ,en caso contrario retorna nulo.
	 * @param key Clave a buscar.
	 * @return Entrada encontrada.
	 * @throws InvalidKeyException si la clave no es valida o no esta en el diccionario.
	 */
	public Entry<K, V> find(K key) throws InvalidKeyException {
		if(key == null )
			throw new InvalidKeyException ("La clave no es valida ");
		Entry<K,V> retenido = null;
		int c = hash(key);
		Iterator <Entrada <K,V>> ite = lista[c].iterator();
		boolean esta = false;
		Entrada <K,V> actual = ((ite.hasNext()? ite.next() :null));
		
		while (!esta && actual != null)
		{
			if(actual.getKey().equals(key))
			{
				esta = true;
				retenido = actual;
			}
			else
				actual = ((ite.hasNext()? ite.next() :null));
		}
		
		return retenido;
	}

	/**
	 * Retorna una coleccion iterable con todas la entradas con clave iguale a la clave pasada por parametro.
	 * @param key Clave de la entradas a buscar.
	 * @return Una coleccion iterable con las entradas encontradas.
	 * @throws InvalidKeyException si la clave no es valida o no esta en el diccionario.
	 */
	public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
		if(key == null)
			throw new InvalidKeyException ("La clave no es valida");
		PositionLista<Entry <K,V>> toda = new Lista<Entry<K,V>>();
		int clave = hash(key);
		for(Entry <K,V> elem : lista[clave])
		{
			if(key.equals(elem.getKey()))
				toda.addLast(elem);
			
		}
		
		return toda;
	}

	/**
	 * Inserta una entrada con valores key-value en el diccionario y retorna la entrada creada.
	 * @param key Clave de la entrada a crear.
	 * @param value Valor de la entrada a crear.
	 * @return La entrada creada.
	 * @throws InvalidKeyException si la clave no es valida o no esta en el diccionario.
	 */
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		if(key == null)
			throw new InvalidKeyException ("La clave no es valida ");
		if(n/N >= 0.9)
			rehash();
		int clave = hash (key);
		PositionLista <Entrada<K,V>> aux = lista[clave];
		Entrada <K,V> nueva = new Entrada <K,V> (key,value);
		aux.addLast(nueva);
		n++;
		return nueva;
	}

	/**
	 * Remueve una entrada del diccionario y la retorna.
	 * @param e Entrada a remover.
	 * @return La entrada removida.
	 * @throws InvalidEntryException si la clave no es valida.
	 */
	public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
		if (e == null ) 
			throw new InvalidEntryException ("La clave no es valida");
		int clave = hash(e.getKey());
		PositionLista<Entrada<K,V>> l = lista[clave];
		Position<Entrada<K,V>> cursor = null;
		Iterator <Position<Entrada <K,V>>> ite = l.positions().iterator();
		Entry<K,V>salida=null;
		while(ite.hasNext() && salida == null)
		{
			cursor=ite.next();
			if(cursor.element().equals(e))
			{
				salida = cursor.element();
				
				try {
					l.remove(cursor);
				} catch (InvalidPositionException e1) {
					e1.getMessage();
				}
				n--;
			}
		}
		if(salida == null)
		 throw new InvalidEntryException ("La salida es invalida ");
		return salida;
	}

	/**
	 * Devuelve una coleccion iterable de todas las entradas del diccionario.
	 * @return Coleccion iterable de todas entradas.
	 */
	public Iterable<Entry<K, V>> entries() {
		PositionLista<Entry<K,V>> nueva = new Lista <Entry<K,V>>();
		for(int i = 0; i<N ;i++)
		{
			for(Entry<K,V> el : lista[i])
			{
				nueva.addLast(el);
			}
		}
		
		return nueva;
	}
	
	/**
	 * Agranda la estructura del programa.
	 * 
	 */
	private void rehash() {
		Iterable<Entry<K, V>> entradas = entries();
		N = proximo_primo(N * 2);
		lista= (Lista<Entrada<K, V>>[]) new Lista[N];
		n = 0;
		for (int i = 0; i < N; i++)
			lista[i] = new Lista<Entrada<K, V>>();
		for (Entry<K, V> e : entradas)
			try {
				insert(e.getKey(), e.getValue());
			} catch (InvalidKeyException ex) {
				ex.getMessage();
			}

	}
	/**
	 * Busca el siguiente promo del numero por parametro.
	 * @param n Numero pasado por parametro.
	 * @return E El proximo primo de n.
	 */
	private int proximo_primo(int n) {
		boolean es = false;
		n++;
		while (!es) {
			if (esPrimo(n))
				es = true;
			else
				n++;

		}
		return n;
	}
	/**
	 * Verifica si el numero pasado por parametro es primo.
	 * @param n Numero a verificar.
	 * @return Verdadero si el numero es primo y falso en caso contrario.
	 */
	private boolean esPrimo(int n) {
		boolean es = false;
		int divisor = 2;
		while (divisor < n && !es) {
			if (n % divisor == 0)
				es = true;
			else
				divisor++;

		}

		return es;
	}
	/**
	 * Identifica en que balde esta el arreglo.
	 * @param key Clave pasada por parametro.
	 * @return  La ubicacion de la cubeta con respeto a la clave.
	 */
	private int hash(K key) 
	{
		return key.hashCode() % N;
	}
}
