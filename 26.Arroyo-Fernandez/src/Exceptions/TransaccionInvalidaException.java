package Exceptions;

/**
 * 
 * @author MaxiC
 *
 */
public class TransaccionInvalidaException extends Exception {
	
	/**
	 * lanza la excepcion so la transaccion es invalida
	 * @param msj transaccion invalida
	 */
	public TransaccionInvalidaException(String msj) {
		super(msj);
	}
}
