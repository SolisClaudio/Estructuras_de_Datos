package Exceptions;

/**
 * 
 * @author MaxiC
 *
 */
@SuppressWarnings("serial")
public class RegistroInvalidoException extends Exception {
	
	/**
	 * lanza la excepcion si el logue es invalido
	 * @param msj registro invalido
	 */
	public RegistroInvalidoException(String msj) {
		super(msj);
	}
}
