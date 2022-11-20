package Exceptions;

/**
 * Clase InvalidEntryException, extiende Exception.
 * @author De Giusti Camila - Quintana Alejo Joaquin.
 *
 */
public class InvalidEntryException extends Exception {
	/**
	 * Crea una instancia de InvalidEntryException.
	 * @param msg mensaje de Error.
	 */
	public InvalidEntryException(String msg) {
		super(msg);
	}
}
