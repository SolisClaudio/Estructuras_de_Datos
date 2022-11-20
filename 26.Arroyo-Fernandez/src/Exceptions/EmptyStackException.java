package Exceptions;

/**
 * 
 * @author MaxiC
 *
 */
public class EmptyStackException extends Exception{
	private static final long serialVersionUID = 1L;

		/**
		 * Lanza la excepcion si la pila esta vacia
		 * @param msj pila vacia
		 */
	public EmptyStackException(String msj) {
		super(msj);
	}
}
