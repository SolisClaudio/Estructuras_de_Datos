package Auxiliares;

/**
 * Clase Entrada<K,V>
 * @author Nahuel Contreras, Nicolas Marini.
 * Clase que implementa Entry<K,V>.
 */
public class Entrada<K,V> implements Entry<K,V> {
	
	public K clave;
	public V valor;
	
	/**
	 * Constructor de la clase Entrada<K,V>, que inicializa sus atributos principales.
	 * @param K clave de la entrada.
	 * @param V valor de la entrada.
	 */
	public Entrada(K key, V value) {
		clave = key;
		valor = value;
	}
	
	/**
	 * Retorna la clave de la Entrada<K,V>
	 * @return K clave de la Entrada<K,V>.
	 */
	public K getKey() {
		return clave;
	}
	
	/**
	 * Retorna el valor de la Entrada<K,V>
	 * @return V valor de la Entrada<K,V>.
	 */
	public V getValue() {return valor;}
	
	/**
	 * Asigna una nueva clave por parametro.
	 * @param parametro clave a asignar.
	 */
	public void setKey(K parametro) {clave = parametro;}
	
	/**
	 * Asigna un nuevo valor por parametro.
	 * @param parametro valor a asignar.
	 */
	public void setValue(V parametro) {valor = parametro;}
	
	public String toString() {return "(" + clave.toString() + "," + valor.toString() + ")";}

}
