package TDADiccionario;

import java.util.Iterator;

import Auxiliares.Entrada;
import Auxiliares.Entry;
import Auxiliares.Position;
import Excepciones.InvalidEntryException;
import Excepciones.InvalidKeyException;
import Excepciones.InvalidPositionException;
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
		//if(n/N > factorDeCarga) reHash();
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

	@Override
	public int size() {
		return n;
	}

	@Override
	public boolean isEmpty() {
		return n == 0;
	}

	@Override
	public Entry<K, V> find(K key) throws InvalidKeyException {
		Iterator<Entrada<K,V>> bucket = buckets[ hashThisKey(key) ].iterator();
		Entry<K,V> aux = null;
		boolean encontrado = false;
		while(bucket.hasNext() && !encontrado) {
			aux = bucket.next();
			if(aux.getKey().equals(key)) encontrado = true;
		}
		if(!encontrado) aux = null;
		return aux;
	}

	@Override
	public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
		PositionList<Entry<K,V>> retorno = new ListaDoblementeEnlazada<Entry<K,V>>();
		PositionList<Entrada<K,V>> bucket = buckets[ hashThisKey(key) ];
		for(Entrada<K,V> en  : bucket){
			if(en.getKey().equals(key)) retorno.addLast(en);
		}
		return retorno;
	}

	@Override
	public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
		Entrada<K,V> entrada = checkEntry( e );
		try {
			PositionList<Entrada<K,V>> bucket = buckets[ hashThisKey(e.getKey())];
			Iterator<Position<Entrada<K,V>>> it = bucket.positions().iterator();
			Position<Entrada<K,V>> aEliminar = null;
			boolean encontrado = false;
			while(it.hasNext() && !encontrado) {
				aEliminar = it.next();
				encontrado = aEliminar.element() == entrada;
			}
			if(!encontrado) throw new InvalidEntryException("Error, entrada no encontrada.");
			bucket.remove(aEliminar);
			n--;
		}
		catch(InvalidKeyException c) {
			throw new InvalidEntryException("Error, entrada invalida.");
		}
		catch(InvalidPositionException ex) {
			ex.printStackTrace();
		}
		return entrada;
	}
	
	private Entrada<K,V> checkEntry(Entry<K,V> e) throws InvalidEntryException {
		if(e == null) throw new InvalidEntryException("Error, entradaInvalida");
		Entrada<K,V> retorno = null;
		try{
			retorno = (Entrada<K,V>) e;
		}
		catch( ClassCastException c) {
			throw new InvalidEntryException("Error, la clave ingresda no corresponde a una entrada valida.");
		}
		return retorno;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> retorno = new ListaDoblementeEnlazada<>();
		for(int i = 0 ; i<N ; i++) {
			for(Entrada<K,V> e : buckets[i])
				retorno.addLast(e);
		}
		return retorno;
	}
	
}
