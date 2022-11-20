package Exceptions;
/**
 * Clase EmptyListException, extiende Exception.
 * @author De Giusti Camila - Quintana Alejo Joaquin.
 *
 */
public class EmptyListException extends Exception {
	/**
	 * Crea una instancia de EmptyListException.
	 * @param msg mensaje de Error.
	 */
	public EmptyListException(String msg) {
		super(msg);
	}
}
