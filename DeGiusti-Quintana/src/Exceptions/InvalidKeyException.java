package Exceptions;

/**
 * Clase InvalidKeyException, extiende Exception.
 * @author De Giusti Camila - Quintana Alejo Joaquin.
 *
 */
public class InvalidKeyException extends Exception {
	/**
	 * Crea una instancia de InvalidKeyException
	 * @param msg mensaje de Error.
	 */
	public InvalidKeyException(String msg) {
		super(msg);
	}
}
