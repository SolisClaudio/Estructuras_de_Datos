package Exception;
/**
 * 
 * @author Leon Baguhan Sanchez Yuyprayseree y Simon Paillan
 *
 */
public class BoundaryViolationException extends Exception {
	/**
	 * Excepcion que aparece cuando la posicion se sale del limite de la lista.
	 * @param msg Mensaje de error cuando la posicion se sale del limite de la lista.
	 */
	public BoundaryViolationException (String msg)
	{
		super(msg);
		
	}
}
