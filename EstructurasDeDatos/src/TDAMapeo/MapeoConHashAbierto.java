package TDAMapeo;

import Auxiliares.Entry;
import Excepciones.InvalidKeyException;
import Excepciones.InvalidPositionException;
import java.util.Iterator;
import Auxiliares.Entrada;
import Auxiliares.Position;
import TDALista.PositionList;
import TDALista.ListaDoblementeEnlazada;

public class MapeoConHashAbierto<K, V> implements Map<K, V> {
	
	private PositionList<Entrada<K,V>> [] buckets;
	private int n, N;
	private float factorDeCarga = 0.5f;
	
	
	@SuppressWarnings("unchecked")
	public MapeoConHashAbierto() {
		N=13;
		n=0;
		buckets =(PositionList<Entrada<K,V>>[]) new ListaDoblementeEnlazada[13];
		for(int i = 0 ; i<13 ; i++)
			buckets[i] = new ListaDoblementeEnlazada<Entrada<K,V>>();
	}
	
	private int nextPrimo(int n) {
		int retorno = n ;
		boolean esPrimo = false;
		boolean cumple = true ; 
		while ( !esPrimo ) {
			retorno++;
			cumple=true;
			for(int i = 2; i<retorno-1 && cumple; i++)
				if(retorno % i == 0) cumple=false;
			esPrimo = cumple;
		}
		return retorno;
		
	}
	
	@SuppressWarnings("unchecked")
	private void reHash() throws InvalidKeyException{
		Iterator<Entry<K,V>> entradas = this.entries().iterator();
		Entry<K,V> entrada ; 
		N = nextPrimo(N);
		N = N+10;
		n = 0;
		buckets =(PositionList<Entrada<K,V>>[]) new ListaDoblementeEnlazada[N];
		for(int i=0 ; i<N ; i++)
			buckets[i] =  new ListaDoblementeEnlazada<Entrada<K,V>>();
		while(entradas.hasNext()) {
			entrada = entradas.next();
			this.put(entrada.getKey(), entrada.getValue());
		}
	}
	
	private int hashThisKey(K key) throws InvalidKeyException {
		if(key==null) throw new InvalidKeyException("Error, una clave nula no corresponde a una clave valida para este mapeo.");
		return key.hashCode()%N;
		
	}
	
	@Override
	public int size() {
		return n;
	}

	@Override
	public boolean isEmpty() {
		return n==0;
	}

	@Override
	public V get(K key) throws InvalidKeyException {
		V retorno = null ;
		boolean encontrado = false;
		Iterator<Entrada<K,V>> i = buckets[hashThisKey(key)].iterator();
		Entrada<K,V> aux = null;
		while(i.hasNext() && !encontrado ) {
			aux=i.next();
			encontrado= aux.getKey().equals(key);
			
		}
		if(encontrado) retorno = aux.getValue();
		return retorno ;
	}

	@Override
	public V put(K key, V value) throws InvalidKeyException {
		if(n/N > factorDeCarga) reHash();
		V retorno = null ;
		boolean encontrado=false;
		Iterator<Entrada<K,V>> i = buckets[hashThisKey(key)].iterator();
		Entrada<K,V> aux = null;
		while(i.hasNext() && !encontrado ) {
			aux = i.next();
			encontrado = aux.getKey().equals(key);
			
		}
		if(encontrado) {
			retorno = aux.getValue();
			aux.setValue(value);
		}
		else {
			buckets[hashThisKey(key)].addFirst( new Entrada<K,V>( key, value ) );
			n++;
		}
		return retorno ;
	}

	@Override
	public V remove(K key) throws InvalidKeyException {
		int numBucket = hashThisKey(key);
		V retorno = null ;
		boolean encontrado = false ;
		Iterator<Position<Entrada<K,V>>> i = buckets[numBucket].positions().iterator(); 
		Position<Entrada<K,V>> aux = null ;
		while(i.hasNext() && !encontrado ) {
			aux = i.next();
			encontrado = aux.element().getKey().equals(key);
		}
		try{
			if(encontrado) {
			retorno = aux.element().getValue();
			n--;
			buckets[numBucket].remove(aux);
		}
		}
		catch(InvalidPositionException e) { e.printStackTrace(); } 
		
		return retorno;
	}

	@Override
	public Iterable<K> keys() {
		PositionList<K> retorno = new ListaDoblementeEnlazada<K>();
		Iterator<Entrada<K,V>> entradas;
		for ( int i = 0; i<N; i++) {
			entradas=buckets[i].iterator();
			while(entradas.hasNext())
				retorno.addLast(entradas.next().getKey());
		}
		return retorno;
			
	}

	@Override
	public Iterable<V> values() {
		PositionList<V> retorno = new ListaDoblementeEnlazada<V>();
		Iterator<Entrada<K,V>> entradas;
		for ( int i = 0; i<N; i++) {
			entradas = buckets[i].iterator();
			while(entradas.hasNext())
				retorno.addLast(entradas.next().getValue());
		}
		return retorno;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> retorno = new ListaDoblementeEnlazada<Entry<K,V>>();
		Iterator<Entrada<K,V>> entradas;
		for ( int i = 0; i<N; i++) {
			entradas=buckets[i].iterator();
			while(entradas.hasNext())
				retorno.addLast(entradas.next());
		}
		return retorno;
	}

}
