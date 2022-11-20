package Exception;
/**
 * 
 * @author Leon Baguhan Sanchez Yuyprayseree y Simon Paillan
 *
 */
public class EmptyStackException extends Exception {
	/**
	 * Excepcion que aparece cuando la pila se encuentra vacia.
	 * @param msg Mensaje de error cuando la pila esta vacia.
	 */
	public EmptyStackException (String msg)
	{
		
		super(msg);
	}
}
