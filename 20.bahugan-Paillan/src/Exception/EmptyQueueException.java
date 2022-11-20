package Exception;
/**
 * 
 * @author Leon Baguhan Sanchez Yuyprayseree y Simon Paillan
 *
 */
public class EmptyQueueException extends Exception {
	/**
	 * Excepcion que aparece cuando la cola se encuentra vacia.
	 * @param msg Mensaje de error cuando la cola esta vacia.
	 */
	public EmptyQueueException (String msg)
	{
		super(msg);
		
	}
}
