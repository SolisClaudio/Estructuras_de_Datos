package TDAColaCP;
import Auxiliar.Entry;
/**
 * Clase Entrada.
 * @author Quintana Alejo Joaquín - De Giusti Camila.
 *
 * @param <K>
 * @param <V>
 */
public class Entrada<K,V> implements Entry<K,V> {
	private K key;
	private V value;
	
	/**
	 * Crea una instancia de Entrada.
	 * @param clave
	 * @param valor
	 */
	public Entrada(K clave, V valor) {
		key = clave;
		value = valor;
	}
	/**
	 * Devuelve una clave K.
	 * @return K que representa la clave.
	 */
	public K getKey() {
		return key;
	}
	/**
	 * Devuelve una clave K.
	 * @return K que representa la clave.
	 */
	public V getValue() {
		return value;
	}
	/**
	 * Modifica el valor de la clave por la clave que pasa como parámetro.
	 * @param clave nueva.
	 */
	public void setKey(K clave) {
		key = clave;
	}
	/**
	 * Modifica value por el valor que pasa como parámetro.
	 * @param valor nuevo
	 */
	public void seyValue(V valor) {
		value = valor;
	}
}