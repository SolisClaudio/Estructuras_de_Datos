package TDADiccionario;

import java.util.Iterator;

import Auxiliares.Entrada;
import Auxiliares.Entry;
import Excepciones.InvalidKeyException;
import TDALista.ListaDoblementeEnlazada;
import TDALista.PositionList;

public class DiccionarioConHashAbierto<K,V> implements Dictionary<K,V> {
	private PositionList<Entrada<K,V>> [] buckets;
	private int n,N;
	private float factorDeCarga = 0.5f;
	
	public DiccionarioConHashAbierto() {
		N=13;
		n=0;
		buckets =(PositionList<Entrada<K,V>>[]) new ListaDoblementeEnlazada[13];
		for(int i = 0 ; i<13 ; i++)
			buckets[i] = new ListaDoblementeEnlazada<Entrada<K,V>>();
	}
	
	public Entry<K,V> insert(K key, V value) throws InvalidKeyException {
		if(n/N > factorDeCarga) reHash();
		Entry<K,V> nuevaEntrada = new Entrada<K,V>(key, value);
		buckets[hashThisKey(key)].addFirst(new Entrada<K,V>(key,value));
		n++;
		return nuevaEntrada ;
	}
	private int hashThisKey(K key) throws InvalidKeyException {
		if(key==null) throw new InvalidKeyException("Error, una clave nula no corresponde a una clave valida para este mapeo.");
		return key.hashCode()%N;
		
	}
	private void reHash() throws InvalidKeyException{
		Iterator<Entry<K,V>> entradas = this.entries().iterator();
		Entry<K,V> entrada ; 
		N=nextPrimo(N);
		n=0;
		buckets =(PositionList<Entrada<K,V>>[]) new ListaDoblementeEnlazada[N];
		for(int i=0 ; i<N ; i++)
			buckets[i] =  new ListaDoblementeEnlazada<Entrada<K,V>>();
		while(entradas.hasNext()) {
			entrada=entradas.next();
			this.insert(entrada.getKey(), entrada.getValue());
		}
	}
	private int nextPrimo(int n) {
		int retorno = n ;
		boolean esPrimo = false;
		boolean cumple = true ; 
		while ( !esPrimo ) {
			retorno++;
			cumple=true;
			for(int i = 2 ; i<retorno-1 && cumple ; i++)
				if(retorno % i == 0) cumple=false;
			esPrimo = cumple;
		}
		return retorno;
		
	}
	
}
