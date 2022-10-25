package TDADiccionario;

import Entrada.Entrada;
import Entrada.EntradaComparable;
import Entrada.Entry;
import TDALista.PositionList;
import TDALista.ListaDoblementeEnlazada;
import Excepciones.EmptyListException;
import Excepciones.InvalidEntryException;
import Excepciones.InvalidKeyException;
import Excepciones.InvalidPositionException;
import ArbolBB.*;
import Position.Position;


public class DiccionarioABB<K extends Comparable<K>, V> implements Dictionary<K, V> {
	
	protected ABB<EntradaComparable<K,PositionList<Entry<K,V>>>> ABB ; 
    protected int size;

    
    public DiccionarioABB(){
        ABB = new ABB<EntradaComparable<K, PositionList<Entry<K,V>>>>();
        size=0;
    }
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@Override
	public Entry<K, V> find(K key) throws InvalidKeyException {
		if(key==null) throw new InvalidKeyException("Error, clave nula");
		Entry<K,V> retorno = null ; 
		NodoABB<EntradaComparable<K,PositionList<Entry<K,V>>>> nodo = ABB.buscar(new EntradaComparable<K,PositionList<Entry<K,V>>>(key,null));
		try {
		if(nodo.element()!= null ) {
			PositionList<Entry<K,V>> listaDeEntradas = nodo.element().getValue();
			retorno = listaDeEntradas.first().element();
		}
		}
		catch(EmptyListException e) { e.printStackTrace();}
		return retorno;
	}

	@Override
	public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
		if(key==null) throw new InvalidKeyException("Error, clave nula");
		PositionList<Entry<K,V>> retorno = new ListaDoblementeEnlazada<Entry<K,V>>();
		NodoABB<EntradaComparable<K,PositionList<Entry<K,V>>>> nodo = ABB.buscar(new EntradaComparable<K,PositionList<Entry<K,V>>>(key,null));
		if(nodo.element()!=null) {
			PositionList<Entry<K,V>> listaDeEntradas = nodo.element().getValue();
			for(Entry<K,V> entradas : listaDeEntradas) 
				retorno.addLast(entradas);
		}
		return retorno;
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		if ( key == null) throw new InvalidKeyException("Error, clave invalida");
		Entry<K,V> nuevaEntrada = new Entrada<K,V>(key,value);
		NodoABB<EntradaComparable<K,PositionList<Entry<K,V>>>> nodo = ABB.buscar(new EntradaComparable<K,PositionList<Entry<K,V>>>(key,null));
		if(nodo.element()==null) //No hay ningun nodo con clave key, nodo corresponde a un nodo dummy
				ABB.expandir(nodo, new EntradaComparable<K, PositionList<Entry<K,V>>>(key, new ListaDoblementeEnlazada<Entry<K,V>>()));
		nodo.element().getValue().addFirst(nuevaEntrada);
		size++;
		return  nuevaEntrada;
	}

	@Override
	public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
		if(e == null) throw new InvalidEntryException("Error, entrada nula");
		NodoABB<EntradaComparable<K, PositionList<Entry<K,V>>>> nodo = ABB.buscar(new EntradaComparable<K, PositionList<Entry<K,V>>>(e.getKey(), null ));
		if(nodo.element() == null ) throw new InvalidEntryException("Error, entrada invalida");
		try {
			for(Position<Entry<K,V>> posicion : nodo.element().getValue().positions())
				if(posicion.element() == e) {
					nodo.element().getValue().remove(posicion);
					if(nodo.element().getValue().isEmpty())
						ABB.eliminar(nodo.element());
					size--;
					return e;
				}
			throw new InvalidEntryException("Error, entrada invalida");
		}
		catch(InvalidPositionException p) { p.printStackTrace(); }
		return null;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> retorno = new ListaDoblementeEnlazada<Entry<K,V>>();
		entriesAux(ABB.getRaiz(), retorno);
		return retorno;
	}
	
	private void entriesAux(NodoABB<EntradaComparable<K,PositionList<Entry<K,V>>>> nodo, PositionList<Entry<K,V>> lista) {
		if(nodo.element()!=null) {
			entriesAux(nodo.getHijoIzquierdo(), lista);
			for(Entry<K,V> entrada : nodo.element().getValue())
				lista.addLast(entrada);
			entriesAux(nodo.getHijoDerecho(), lista);
		}
	}

}
