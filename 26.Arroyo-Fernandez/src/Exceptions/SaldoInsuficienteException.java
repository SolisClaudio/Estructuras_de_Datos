package Exceptions;


/**
 * 
 * @author MaxiC
 *
 */
public class SaldoInsuficienteException extends Exception {
	
	/**
	 * lanza la excepcion si el saldo es insuficiente
	 * @param msj saldo insuficiente
	 */
	public SaldoInsuficienteException(String msj) {
		super(msj);
	}

}
