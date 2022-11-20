package Exceptions;

/**
 * 
 * @author MaxiC
 *
 */
public class InvalidKeyException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Lanza la excepcion si la clave es invalida
	 * @param msg clave invalida
	 */
	public InvalidKeyException(String msg){
		super(msg);
	}
}
