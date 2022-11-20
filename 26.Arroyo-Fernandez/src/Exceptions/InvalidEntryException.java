package Exceptions;

/**
 * 
 * @author MaxiC
 *
 */
public class InvalidEntryException extends Exception{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Lanza la excepcion si la entrada es invalida
	 * @param msg entrada invalida
	 */
	public InvalidEntryException(String msg){
		super(msg);
	}
}
