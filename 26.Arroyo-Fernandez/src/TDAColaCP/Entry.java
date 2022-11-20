package TDAColaCP;

/**
 * 
 * @author MaxiC
 *
 * @param <K> clave
 * @param <V> valor
 */
public interface Entry<K,V>{
	/**
	 * 
	 * @return key de la entrada
	 */
	public K getKey();
	
	/**
	 * 
	 * @return valor de la entrada
	 */
	public V getValue();
}
