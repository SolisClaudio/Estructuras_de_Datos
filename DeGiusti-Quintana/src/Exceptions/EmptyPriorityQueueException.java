package Exceptions;

/**
 * Clase EmptyPriorityQueueException, extiende Exception.
 * @author De Giusti Camila - Quintana Alejo Joaquin.
 *
 */
public class EmptyPriorityQueueException extends Exception {
	/**
	 * Crea una instancia de EmptyPriorityQueueException.
	 * @param msg mensaje de Error.
	 */
	public EmptyPriorityQueueException(String msg) {
		super(msg);
	}
}
