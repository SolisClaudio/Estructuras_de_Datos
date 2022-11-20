package Exception;

/**
 * 
 * @author Leon Baguhan Sanchez Yuyprayseree y Simon Paillan
 *
 */
public class FullStackException extends Exception {
	/**
	 * Excepcion que aparece cuando la pila se encuentra llena.
	 * @param msg Mensaje que aparece cuando la pila esta llena.
	 */
	public FullStackException (String msg)
	{
		
		super(msg);
	}
}
