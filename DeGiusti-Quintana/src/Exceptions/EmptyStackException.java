package Exceptions;

/**
 * Clase EmptyStackException, extiende Exception.
 * @author De Giusti Camila - Quintana Alejo Joaquin.
 *
 */
public class EmptyStackException extends Exception {
	/**
	 * Crea una instancia de EmptyStackException.
	 * @param msg mensaje de error.
	 */
	public EmptyStackException(String msg) {
		super(msg);
	}
}