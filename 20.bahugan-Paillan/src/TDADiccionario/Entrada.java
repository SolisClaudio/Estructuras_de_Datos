package TDADiccionario;


/**
 * 
 * @author Leon Baguhan Sanchez Yuyprayseree y Simon Paillan
 *
 * @param <K> Clave generica.
 * @param <V> Valor generica.
 */
public class Entrada<K,V> implements Entry<K,V> {

	protected K clave;
	protected V valor;
	
	/**
	 * Crea una entrada con un par clave-valor.
	 * @param clave Clave a insertar en la entrada.
	 * @param valor	Valor a insertar en la entrada.
	 */
	public Entrada (K clave , V valor)
	{
		this.clave = clave;
		this.valor = valor;
	}
	
	/**
	 * Consulta la clave de la entrada.
	 * 
	 */
	public K getKey() {
		
		return clave;
	}

	/**
	 * Consulta el valor de la entrada.
	 */
	public V getValue() {
	
		return valor;
	}

	/**
	 * Establece la clave de la entrada.
	 * @param clave Clave pasada por parametro a establecer en la entrada.
	 */
	public void setKey (K clave)
	{
		this.clave = clave;
	}
	/**
	 * Establece el valor de la entrada.
	 * @param valor Valor pasada por parametro a establecer en la entrada.
	 */
	public void setValue (V valor)
	{
		this.valor = valor;
	}
	/**
	 * Muestra el estado interno de la entrada.
	 */
	public String toString ()
	{
		return ("Clave "+getKey()+" valor "+getValue());
	}
}
