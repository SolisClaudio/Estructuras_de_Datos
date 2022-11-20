package TDADiccionario;
import Auxiliar.Entry;
import java.util.Iterator;
import TDALista.*;
import Exceptions.*;
import TDALista.ListaDoblementeEnlazada;
/**
 * Clase Diccionario utilizando Tabla Hash Abiero, implementa la interfaz Dictionary.
 * @author  Quintana Alejo Joaquin - De Giusti Camila.
 * @param <K>
 * @param <V>
 */
public class Diccionario<K,V> implements Dictionary<K,V> {
	private int n,N;
	private static final float factor = 0.5f;
	private PositionList<Entrada<K,V>> arregloBucket[]; 
	
	/**
	 * Crea una instancia de Diccionario.
	 */
	public Diccionario() {
		N = 11;
		n = 0;
		arregloBucket = new ListaDoblementeEnlazada[N];
		for(int i = 0; i < N; i++) {
			arregloBucket[i] = new ListaDoblementeEnlazada<Entrada<K,V>>();
		}
	}
	/**
	 * Consulta el n�mero de entradas del diccionario.
	 * @return N�mero de entradas del diccionario.
	 */
	public int size() {
		return n;
	}
	/**
	 * Consulta si el diccionario est� vac�o.
	 * @return Verdadero si el diccionario est� vac�o, falso en caso contrario.
	 */
	public boolean isEmpty() {
		return n == 0;
	}
	/**
	 * Busca una entrada con clave igual a una clave dada y la devuelve, si no existe retorna nulo.
	 * @param key Clave a buscar.
	 * @return Entrada encontrada.
	 * @throws InvalidKeyException si la clave pasada por par�metro es inv�lida.
	 */
	public Entrada<K,V> find(K key) throws InvalidKeyException{
		checkKey(key);
		int valorHash = hashThisKey(key);
		Iterator<Entrada<K,V>> it = arregloBucket[valorHash].iterator();		
		boolean encontre = false;
		Entrada<K,V> aux = null;
		
		while(it.hasNext() && !encontre) {
			aux = it.next();
			if(aux.getKey().equals(key)) {
				encontre = true;
			}
		}
		if(!encontre) {
			aux = null;
		}
		return aux;
	}
	/**
	 * Retorna una colecci�n iterable que contiene todas las entradas con clave igual a una clave dada.
	 * @param key Clave de las entradas a buscar.
	 * @return Colecci�n iterable de las entradas encontradas.
	 * @throws InvalidKeyException si la clave pasada por par�metro es inv�lida.
	 */
	public Iterable<Entry<K,V>> findAll(K key) throws InvalidKeyException{
		checkKey(key);
		PositionList<Entry<K,V>> valoresDeKey = new ListaDoblementeEnlazada<Entry<K,V>>();
		int valorHash = hashThisKey(key);
		for(Entry<K,V> p: arregloBucket[valorHash]) {
			if(p.getKey().equals(key)) {
				valoresDeKey.addLast(p);
			}
		}
		return valoresDeKey;
	}
	/**
	 * Inserta una entrada con una clave y un valor dado en el diccionario y retorna la entrada creada.
	 * @param key Clave de la entrada a crear.
	 * @return value Valor de la entrada a crear.
	 * @throws InvalidKeyException si la clave pasada por par�metro es inv�lida.
	 */
	public Entry<K,V> insert(K key, V value) throws InvalidKeyException{
		checkKey(key);
		Entrada<K,V> nueva = new Entrada<K,V>(key, value);
		int valorHash = hashThisKey(key);
		arregloBucket[valorHash].addLast(nueva);
		n++;
		return nueva;
	}
	/**
	 * Remueve una entrada dada en el diccionario y devuelve la entrada removida.
	 * @param e Entrada a remover.
	 * @return Entrada removida.
	 * @throws InvalidEntryException si la entrada no est� en el diccionario o es inv�lida.
	 */
	public Entry<K,V> remove(Entry<K,V> e) throws InvalidEntryException{
		if(e == null) {
			throw new InvalidEntryException("Entrada nula");
		}
		int valorHash;
		try {
			valorHash = hashThisKey(e.getKey());		
		}catch(InvalidKeyException p) {
			throw new InvalidEntryException("Clave de entrada inválida");
		}
		Entrada<K,V> aRetornar = null;
		Position<Entrada<K,V>> aux = null;
		boolean removido = false;
		Iterator<Position<Entrada<K,V>>> it = arregloBucket[valorHash].positions().iterator();
		
		while(it.hasNext() && !removido) {
			aux = it.next();
			if(aux.element().getKey().equals(e.getKey()) && aux.element().getValue().equals(e.getValue())) {
				try {
					aRetornar = arregloBucket[valorHash].remove(aux);
					removido = true;
				}catch(InvalidPositionException n) {
					throw new InvalidEntryException("La entrada no pertenece al diccionario");
				}
			}								
		}
		if(!removido) {
			throw new InvalidEntryException("Entrada inválida");
		}	
		n--;
		return aRetornar;
	}
	/**
	 * Retorna una colecci�n iterable con todas las entradas en el diccionario.
	 * @return Colecci�n iterable de todas las entradas.
	 */
	public Iterable<Entry<K,V>> entries(){
		PositionList<Entry<K,V>> entradas = new ListaDoblementeEnlazada<Entry<K,V>>();
		for(int i = 0; i < N; i++) {
			for(Position<Entrada<K,V>> p : arregloBucket[i].positions()) {
				entradas.addLast(p.element());				
			}
		}
		return entradas;
	}
	/**
	 * Agranda el tamaño de la tabla Hash y ubica las entradas en la tabla nueva.
	 */
	private void reHash() {
		Iterable<Entry<K,V>> entradas= entries();
		N = nextPrimo(N) ; 
		n = 0;
		arregloBucket = (PositionList<Entrada<K,V>> []) new ListaDoblementeEnlazada[N];
		for (int i = 0; i < N; i++) 
			arregloBucket[i]= new ListaDoblementeEnlazada<Entrada<K,V>>();
		try {
			for(Entry<K,V> e: entradas)
				this.insert(e.getKey(),e.getValue());
		}
		catch(InvalidKeyException e) {
			System.out.println(e.getMessage());
		}
	}
	/**
	 * Devuelve el siguiente número primo al pasado como parámetro.
	 * @param n, entero n.
	 * @return entero que representa el siguiente primo.
	 */
	private int nextPrimo(int n) {
		int num = n + 1;
		int primo = 0;
		while(primo == 0) {
			int cantD=0;
			int div = 1;
			while(cantD <= 1 && div < num) {
				if(num % div == 0) {
					cantD++;
				}
				div++;
			}
			if(div==num && cantD==1) {
				primo = num;
			}
			else {
				num++;
			}
		}
		return primo;
	}
	/**
	 * Chequea que la clave pasada por parámetro sea válida.
	 * @param key una clave.
	 * @throws InvalidKeyException si la clave por parámetro es inválida.
	 */
	private void checkKey(K key) throws InvalidKeyException{
		if(key == null)
			throw new InvalidKeyException("Clave inválida");
	}
	/**
	 * Devuelve el valor hash.
	 * @param key una clave.
	 * @return int que representa el valor hash.
	 * @throws InvalidKeyException si la clase es inválida.
	 */
	private int hashThisKey(K key) throws InvalidKeyException{
		checkKey(key);
		return Math.abs(key.hashCode() % N);
	}
}