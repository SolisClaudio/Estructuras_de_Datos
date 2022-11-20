package Exceptions;

/**
 * Clase InvalidPositionException, extiende Exception.
 * @author De Giusti Camila - Quintana Alejo Joaquin.
 *
 */
public class InvalidPositionException extends Exception {
	/**
	 * Crea una instancia de InvalidPositionException.
	 * @param msg mensaje de Error.
	 */
	public InvalidPositionException(String msg) {
		super(msg);
	}
}
