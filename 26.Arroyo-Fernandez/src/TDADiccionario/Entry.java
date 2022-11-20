package TDADiccionario;

/**
 * 
 * @author MaxiC
 *
 * @param <K> clave
 * @param <V> valor
 */
public interface Entry<K,V>	{
	
	/**
	 * 
	 * @return clave de entrada
	 */
	public K getKey(); 
	
	/**
	 * 
	 * @return valor de entrada
	 */
	public V getValue(); 

}
