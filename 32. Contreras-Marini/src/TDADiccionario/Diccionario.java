package TDADiccionario;
import java.util.Iterator;

import Auxiliares.Entrada;
import Auxiliares.Entry;
import Auxiliares.Position;
import Exceptions.InvalidEntryException;
import Exceptions.InvalidKeyException;
import Exceptions.InvalidPositionException;
import TDALista.ListaDoblementeEnlazada;
import TDALista.PositionList;

/**
 * Clase Diccionario<K,V>
 * @author Nahuel Contreras, Nicolas Marini.
 * Clase que implementa Dictionary<K,V> con una ListaDoblementeEnlazada<Entry<K,V>>.
 */
public class Diccionario<K,V> implements Dictionary<K,V>{
	
	protected ListaDoblementeEnlazada<Entry<K,V>> D;
	
	/**
	 * Constructor de la clase Diccionario, que inicializa su atributo principal.
	 */
	public Diccionario() {
		D = new ListaDoblementeEnlazada<Entry<K,V>>();
	}
	
	public int size() {
		return D.size();
	}
	
	public boolean isEmpty() {
		return D.isEmpty();
	}
	
	public Entry<K,V> find(K key) throws InvalidKeyException{
		K parametro = checkKey(key);
		Entry<K,V> retorno = null;
		Entry<K,V> aux;
		
		Iterator<Entry<K,V>> it = D.iterator();
		while(it.hasNext() && retorno == null) {
			aux = it.next();
			if(aux.getKey().equals(parametro))
				retorno = aux;
		}
		return retorno;
	}
	
	public Iterable<Entry<K,V>> findAll(K key) throws InvalidKeyException{
		K parametro = checkKey(key);
		PositionList<Entry<K,V>> retorno = new ListaDoblementeEnlazada<Entry<K,V>>();
		for(Position<Entry<K,V>> entrada: D.positions()) 
			if(entrada.element().getKey().equals(parametro)) 
				retorno.addLast(entrada.element());			
		
		return retorno;
	} 
	
	public Entry<K,V> insert(K key, V value) throws InvalidKeyException{
		K parametro = checkKey(key);
		Entry<K,V> retorno = new Entrada<K,V>(parametro,value);
		D.addLast(retorno);
		
		return retorno;	
	}
	
	public Entry<K,V> remove(Entry<K,V> e) throws InvalidEntryException{
		Entry<K,V> parametro = checkEntry(e);
		Entry<K,V>retorno = null;
		Position<Entry<K,V>> aux = null;
		Iterator<Position<Entry<K,V>>> it = D.positions().iterator();
		while(retorno == null && it.hasNext()) {
			aux = it.next();
			if(aux.element() == e)
				retorno = aux.element();
		}
		if(retorno == null)
			throw new InvalidEntryException("La entrada no se encuentra en este conjunto");
		try {
			if(retorno != null)
				D.remove(aux);
		} catch(InvalidPositionException s) {
			s.getMessage();
		}
		return retorno;
	}
	
	public Iterable<Entry<K,V>> entries(){
		PositionList<Entry<K,V>> retorno = new ListaDoblementeEnlazada<Entry<K,V>>();
		
		for(Position<Entry<K,V>> posicion: D.positions())
			retorno.addLast(posicion.element());
		
		return retorno;
	}
	
	private Entry<K,V> checkEntry(Entry<K,V> parametro) throws InvalidEntryException{
		if(parametro == null)
			throw new InvalidEntryException("Entrada eliminada previamente");
		else if(parametro.getKey() == null)
			throw new InvalidEntryException("Valor de Key dada, es nula");
		return parametro;
	}
	private K checkKey(K key) throws InvalidKeyException{
		if(key == null)
			throw new InvalidKeyException("La key es nula");
		return key;
	}
	
}
