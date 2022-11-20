package Auxiliar;

/**
 * Interfaz Entry.
 * @author De Giusti Camila - Quintana Alejo Joaquin.
 *
 * @param <K>
 * @param <V>
 */
public interface Entry<K,V> {
	/**
	 * Devuelve una clave K.
	 * @return K que representa la clave.
	 */
	public K getKey();
	/**
	 * Devuelve un valor V
	 * @return V que representa el valor.
	 */
	public V getValue();
}
