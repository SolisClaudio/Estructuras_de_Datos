package Exception;
/**
 * 
 * @author Leon Baguhan Sanchez Yuyprayseree y Simon Paillan
 *
 */
public class InvalidPositionException  extends Exception {
	/**
	 * Excepcion que aparece cuando la posicion de la lista es invalida o no existe.
	 * @param msg Mensaje de error cuando la posicion de la lista es invalida o no existe.
	 */
	public InvalidPositionException (String msg)
	{
		super(msg);
		
	}
}
