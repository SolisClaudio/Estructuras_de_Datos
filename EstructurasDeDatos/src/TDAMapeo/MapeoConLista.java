package TDAMapeo;

import Auxiliares.Entry;
import java.util.Iterator;
import Auxiliares.Entrada;
import Excepciones.InvalidKeyException;
import Excepciones.InvalidPositionException;
import TDALista.PositionList;
import TDALista.ListaDoblementeEnlazada;
import Auxiliares.Position;

public class MapeoConLista<K, V> implements Map<K, V> {
	
	private PositionList<Entrada<K,V>> lista;
	
	public MapeoConLista() {
		lista = new ListaDoblementeEnlazada<Entrada<K,V>>();
	}
	
	@Override
	public int size() {
		return lista.size();
	}

	@Override
	public boolean isEmpty() {
		return lista.isEmpty();
	}

	@Override
	public V get(K key) throws InvalidKeyException {
		if(key == null) throw new InvalidKeyException("Error, la clave ingresada no corresponde a una clave valida");
		V retorno = null ;
		Iterator<Entrada<K,V>> i = lista.iterator();
		boolean encontrado =  false;
		Entrada<K,V> aux = null;
		while (i.hasNext() && !encontrado) {
			aux = i.next();
			encontrado = aux.getKey().equals(key);
		}
		if (encontrado) retorno = aux.getValue();
		return retorno;
		
	} 

	@Override
	public V put(K key, V value) throws InvalidKeyException {
		if(key == null) throw new InvalidKeyException("Error, la clave ingresada no corresponde a una clave valida");
		V retorno = null ;
		Iterator<Entrada<K,V>> i = lista.iterator();
		boolean encontrado =  false;
		Entrada<K,V> aux = null;
		while (i.hasNext() && !encontrado) {
			aux = i.next();
			encontrado = aux.getKey().equals(key);
			
		}
		if(encontrado){
			retorno = aux.getValue();
			aux.setValue(value);
		} 
		else lista.addLast(new Entrada<K,V>(key,value));
		return retorno;
	}

	@Override
	public V remove(K key) throws InvalidKeyException {
		if(key == null) throw new InvalidKeyException("Error, la clave ingresada no corresponde a una clave valida");
		V retorno = null ; 
		Iterator<Position<Entrada<K,V>>> i = lista.positions().iterator();
		boolean encontrado = false;
		Position<Entrada<K,V>> aux = null;
		while ( i.hasNext() && !encontrado){
			aux = i.next();
			encontrado = aux.element().getKey().equals(key);

		} 
	try {	if(encontrado){
			retorno = aux.element().getValue();
			lista.remove(aux);
		}
	}
	catch(InvalidPositionException e) { e.printStackTrace(); } 
		return retorno;
	}

	@Override
	public Iterable<K> keys() {
		Iterator<Entrada<K,V>> i = lista.iterator();
		PositionList<K> retorno  =  new ListaDoblementeEnlazada<K>();
		while ( i.hasNext())
			retorno.addLast(i.next().getKey());
		return retorno;
	}

	@Override
	public Iterable<V> values() {
		Iterator<Entrada<K,V>> i =  lista.iterator();
		PositionList<V> retorno  =  new ListaDoblementeEnlazada<V>();
		while (i.hasNext())
			retorno .addLast(i.next().getValue());
		return retorno;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		Iterator<Entrada<K,V>> i =  lista.iterator();
		PositionList<Entry<K,V>> retorno  =  new ListaDoblementeEnlazada<Entry<K,V>>();
		while ( i.hasNext())
			retorno.addLast(i.next());
		return retorno;
	}

}
