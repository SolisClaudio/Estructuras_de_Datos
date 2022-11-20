package Exceptions;

/**
 * Implementa la excepcion de la cola vacia
 * @author MaxiC
 */
@SuppressWarnings("serial")
public class EmptyPriorityQueueException extends Exception{

	/**
	 * Modela una excepci�n que advierte que la pila esta vacia
	 * @param msg es el mensaje que va a ser mostrado
	 */
	public EmptyPriorityQueueException(String msg) {
		super(msg);
	}

}
