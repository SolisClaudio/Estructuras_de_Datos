package TDAMapeo;

import Entrada.Entry;
import Excepciones.InvalidKeyException;
import Entrada.Entrada;
import TDALista.PositionList;
import TDALista.ListaDoblementeEnlazada;

public class MapeoConHashCerrado<K, V> implements Map<K, V> {
	private Entrada<K,V> [] arreglo; 
	private int n ;
	private int N;
	private Entrada<K,V> DISPONIBLE;
	
	public MapeoConHashCerrado() {
		n=0;
		DISPONIBLE = new Entrada<K,V>(null, null);
		N=13;
		arreglo = (Entrada<K,V>[]) new Entrada[13];
	}
	@Override
	public int size() {
		return n;
	}

	@Override
	public boolean isEmpty() {
		return n==0;
	}
	
	
	private int hashThisKey(K k) throws InvalidKeyException{
		if(k==null) throw new InvalidKeyException("Error, la clave ingresada no corresponde a una clave valida en este mapeo");
		return k.hashCode()%N;
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
	
	private void reHash() throws InvalidKeyException{
		Iterable<Entry<K, V>> entradas = entries();
		N=nextPrimo(N);
		n=0;
		arreglo = (Entrada<K,V>[]) new Entrada[13];
		for(Entry<K,V> e : entradas)
			this.put(e.getKey(),e.getValue());
		
	}
	@Override
	public V get(K key) throws InvalidKeyException {
		V retorno = null ;
		int puesto = hashThisKey(key);
		boolean ubicado = false ; 
		while(!ubicado) 
			if(arreglo[puesto]==null) 
				ubicado = true;
			else {
				if(arreglo[puesto] == DISPONIBLE)
					puesto = (puesto + 1)%N;
				else {
						if(arreglo[puesto].getKey().equals(key)) {
							retorno = arreglo[puesto].getValue();
							ubicado = true;
						}
						puesto = (puesto + 1)%N;
							
				}
					
				
			}
		return retorno;
	}

	@Override
	public V put(K key, V value) throws InvalidKeyException {
		if(n/N > 0.5F) this.reHash();

		System.out.println("Boludo");
		V retorno = null ;
		int puesto = hashThisKey(key);
		System.out.println("N = " + N + "n =" + n );
		boolean ubicado = false ; 
		while(!ubicado) {
			
			if(arreglo[puesto]==null) {
				arreglo[puesto]=new Entrada<K,V>(key,value);
				ubicado = true;
				n++;
			}
			else {
				if(arreglo[puesto] == DISPONIBLE)
					puesto = (puesto + 1)%N;
				else {
						ubicado = arreglo[puesto].getKey().equals(key);
						if(ubicado) {
							retorno = arreglo[puesto].getValue();
							arreglo[puesto].setValue(value);
						}
						puesto = (puesto + 1)%N;
							
				}}
					
				
			}
		return retorno;
			
	}

	@Override
	public V remove(K key) throws InvalidKeyException {
		V retorno = null ;
		int puesto = hashThisKey(key);
		boolean ubicado = false ; 
		while(!ubicado) 
			if(arreglo[puesto]==null) 
				ubicado = true;
			else {
				if(arreglo[puesto] == DISPONIBLE)
					puesto = (puesto + 1)%N;
				else {
						if(arreglo[puesto].getKey().equals(key)) {
							retorno = arreglo[puesto].getValue();
							ubicado = true;
							arreglo[puesto] = DISPONIBLE;
							n--;
						}
						puesto = (puesto + 1)%N;
							
				}
					
				
			}
		return retorno;
		
	}

	@Override
	public Iterable<K> keys() {
		PositionList<K> retorno = new ListaDoblementeEnlazada<K>();
		for(int i = 0 ; i<N ;i++) {
			if(arreglo[i]!=null  && arreglo[i]!=DISPONIBLE)
				retorno.addLast(arreglo[i].getKey());
		}
		return retorno;
		
	}

	@Override
	public Iterable<V> values() {
		PositionList<V> retorno = new ListaDoblementeEnlazada<V>();
		for(int i = 0 ; i<N ;i++) {
			if(arreglo[i]!=null  && arreglo[i]!=DISPONIBLE)
				retorno.addLast(arreglo[i].getValue());
		}
		return retorno;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> retorno = new ListaDoblementeEnlazada<Entry<K,V>>();
		for(int i = 0 ; i<N ;i++) {
			if(arreglo[i]!=null  && arreglo[i]!=DISPONIBLE)
				retorno.addLast(arreglo[i]);
		}
		return retorno;
	}

}
