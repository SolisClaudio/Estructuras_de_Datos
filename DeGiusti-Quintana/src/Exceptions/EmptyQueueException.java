package Exceptions;

/**
 * Clase EmptyQueueException, extiende Exception.
 * @author De Giusti Camila - Quintana Alejo Joaquin.
 *
 */
public class EmptyQueueException extends Exception {
	/**
	 * Crea una instancia de EmptyQueueException.
	 * @param msg mensaje de Error.
	 */
	public EmptyQueueException(String msg) {
		super(msg);
	}
}
