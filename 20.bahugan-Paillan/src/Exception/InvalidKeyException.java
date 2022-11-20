package Exception;
/**
 * 
 * @author Leon Baguhan Sanchez Yuyprayseree y Simon Paillan
 *
 */
public class InvalidKeyException extends Exception {
	/**
	 * Excepcion que aparece cuando la clave ingresada no es valida.
	 * @param msg Mensaje de error cuando la clave ingresada no es valida.
	 */
	public InvalidKeyException (String msg)
	{
		super(msg);
	}
}
