package Exceptions;

/**
 * 
 * @author MaxiC
 *
 */
public class LogueoInvalidoException extends Exception {
	
	/**
	 * Lanza la excepcion si el logue es invalido
	 * @param msj logueo invalido
	 */
	public LogueoInvalidoException(String msj) {
		super(msj);
	}
}