package Exceptions;

/**
 * Clase BoundaryViolationException, extiende Exception.
 * @author De Giusti Camila - Quintana Alejo Joaquin.
 *
 */
public class BoundaryViolationException extends Exception {
	/**
	 * Se crea una instancia de BoundaryViolationException.
	 * @param msg mensaje de Error.
	 */
	public BoundaryViolationException(String msg){
		super(msg);
	}
}
