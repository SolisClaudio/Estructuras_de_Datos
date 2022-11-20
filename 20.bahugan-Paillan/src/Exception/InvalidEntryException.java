package Exception;
/**
 * 
 * @author Leon Baguhan Sanchez Yuyprayseree y Simon Paillan
 *
 */
public class InvalidEntryException extends Exception {
	/**
	 * Excepcion que aparece cuando la entrada es invalida.
	 * @param msg Mensaje de error cuando la entrada es invalida.
	 */
	public InvalidEntryException (String msg)
	{
		
		super(msg);
	}
}
