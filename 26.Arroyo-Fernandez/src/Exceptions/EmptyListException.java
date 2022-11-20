package Exceptions;

/**
 * implementa la excepcion de la lista vacia
 * @author MaxiC
 *
 */
@SuppressWarnings("serial")
public class EmptyListException extends Exception {
	
	/**
	 * ñamza que la lista esta vacia
	 * @param msj la lista esta vacia
	 */
	public EmptyListException(String msj) {
		super(msj);
	}
}
