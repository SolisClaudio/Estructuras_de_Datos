package TDAMapeo;

import Auxiliares.Entrada;
import Auxiliares.Entry;
import Excepciones.InvalidKeyException;
import TDALista.ListaDoblementeEnlazada;
import TDALista.PositionList;
import ArbolBB.*;
public class MapeoABB<K extends Comparable<K>, V> implements Map<K, V> {
	private ABB<EntradaComparable<K,V>> ABB;
	int size;
	
	
	public MapeoABB() {
		size=0;
		ABB= new ABB<EntradaComparable<K,V>>();
	}
	@Override
	public int size() {
		// Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		//  Auto-generated method stub
		return size==0;
	}

	@Override
	public V get(K key) throws InvalidKeyException {
		if(key==null) throw new InvalidKeyException("Error, la clave ingresada corresponde a una clave nula");
		NodoABB<EntradaComparable<K,V>> retorno = ABB.buscar(new EntradaComparable<K,V>(key, null ));
		if(retorno.element()==null)
			return null;
		else return retorno.element().getValue();
	}

	@Override
	public V put(K key, V value) throws InvalidKeyException {
		if(key == null ) throw new InvalidKeyException("Error, la clave ingresada corresponde a una clave invalida");
		EntradaComparable<K,V> nuevaEntrada = new EntradaComparable<K,V>(key,value);
		NodoABB<EntradaComparable<K,V>> nodo = ABB.buscar(nuevaEntrada);
		V retorno = null;
		if(nodo.element()!=null) {
			nodo.element().setValue(value);
			retorno = nodo.element().getValue();
		}
		else {
			size++;
			
			ABB.expandir(nodo, nuevaEntrada);
		}
		return retorno ; 
	}

	@Override
	public V remove(K key) throws InvalidKeyException {
		if(key==null) throw new InvalidKeyException("Error, la clave ingresada corresponde a una clave nula");
		V retorno = null ; 
		EntradaComparable<K,V> entrada = new EntradaComparable<K,V>(key,null);
		NodoABB<EntradaComparable<K,V>> nodo = ABB.buscar(entrada);
		if(nodo.element()!=null) {
			size-- ;
			retorno = nodo.element().getValue();
		}
		ABB.eliminar(entrada);
		return retorno ; 
	}
	
	@Override
	public Iterable<K> keys() {
		NodoABB<EntradaComparable<K,V>> nodo = ABB.getRaiz();
		PositionList<K> retorno  = new ListaDoblementeEnlazada<K>();
		keysRecursivo(nodo, retorno); 
		return retorno;
	}

	
	private void keysRecursivo ( NodoABB<EntradaComparable<K,V>> nodo , PositionList<K> lista) {
		if(nodo.element()!= null ) {
			keysRecursivo(nodo.getHijoIzquierdo() ,lista);
			lista.addLast(nodo.element().getKey());
			keysRecursivo(nodo.getHijoDerecho(), lista);
		}
	}

	@Override
	public Iterable<V> values() {
		NodoABB<EntradaComparable<K,V>> nodo = ABB.getRaiz();
		PositionList<V> retorno  = new ListaDoblementeEnlazada<V>();
		valuesRecursivo(nodo, retorno);
		return retorno;
	}
	private void valuesRecursivo ( NodoABB<EntradaComparable<K,V>> nodo , PositionList<V> lista) {
		if(nodo.element()!= null ) {
			valuesRecursivo(nodo.getHijoIzquierdo() ,lista);
			lista.addLast(nodo.element().getValue());
			valuesRecursivo(nodo.getHijoDerecho(), lista);
		}
	}
	@Override
	public Iterable<Entry<K, V>> entries() {
		NodoABB<EntradaComparable<K,V>> nodo = ABB.getRaiz();
		PositionList<Entry<K,V>> retorno  = new ListaDoblementeEnlazada<Entry<K,V>>();
		entriesRecursivo(nodo, retorno);
		return retorno;
	}
	private void entriesRecursivo ( NodoABB<EntradaComparable<K,V>> nodo , PositionList<Entry<K,V>> lista) {
		if(nodo.element()!= null ) {
			entriesRecursivo(nodo.getHijoIzquierdo() ,lista);
			lista.addLast(nodo.element());
			entriesRecursivo(nodo.getHijoDerecho(), lista);
		}
	}
}
