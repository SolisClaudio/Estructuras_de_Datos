package Exceptions;


/**
 * 
 * @author MaxiC
 *
 */
public class EmptyQueueException extends Exception{
	private static final long serialVersionUID = 1L;

	/**
	 * lanza la excepcion so la cola esta vacia
	 * @param msj cola vacia
	 */
	public EmptyQueueException(String msj) {
		super(msj);
	}
}

