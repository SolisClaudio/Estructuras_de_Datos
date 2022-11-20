package Exceptions;

/**
 * 
 * @author MaxiC
 *
 */
public class BoundaryViolationException extends Exception {
	
	/**
	 * lanza la excepcion si de va del arreglo
	 * @param msj Se pasa del arreglo
	 */
	public BoundaryViolationException(String msj) {
		super(msj);
	}

}
