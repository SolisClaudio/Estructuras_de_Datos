package Excepciones;

@SuppressWarnings("serial")
public class InvalidKeyException extends Exception {
	public InvalidKeyException(String msg) {
		super(msg);
	}
}
