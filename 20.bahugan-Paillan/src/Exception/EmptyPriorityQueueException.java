package Exception;
/**
 * 
 * @author Leon Baguhan Sanchez Yuyprayseree y Simon Paillan
 *
 */
public class EmptyPriorityQueueException extends Exception {
	/**
	 * Excepcion que aparece cuando la cola con prioridad se encuentra vacia.
	 * @param msg Mensaje de error cuando la cola con prioridad esta vacia.
	 */
	public EmptyPriorityQueueException (String msg)
	{
		super(msg);
		
	}
}
