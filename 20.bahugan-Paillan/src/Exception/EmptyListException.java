package Exception;
/**
 * 
 * @author Leon Baguhan Sanchez Yuyprayseree y Simon Paillan
 *
 */
public class EmptyListException extends Exception{
	/**
	 * Excepcion que aparece cuando la lista se encuentra vacia.
	 * @param args Mensaje de error cuando la lista esta vacia.
	 */
	public EmptyListException (String args)
	{
		
		super(args);
	}
}
