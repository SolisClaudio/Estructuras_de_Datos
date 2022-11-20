package Exceptions;

/**
 * 
 * @author MaxiC
 *
 */
@SuppressWarnings("serial")
public class InvalidPositionException extends Exception {
	
	/**
	 * Lanza la excepcion si la Posision es invalida
	 * @param msj posicion invalida
	 */
	public InvalidPositionException(String msj) {
		super(msj);
	}

}
