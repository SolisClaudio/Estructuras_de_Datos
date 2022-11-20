package TDAColaCP;
/**
 * 
 * @author Leon Baguhan Sanchez Yuyprayseree y Simon Paillan
 *
 * @param <E> Parametro generico.
 */
public class Comparador<E> implements java.util.Comparator<E> {

	/**
	 * Comparador de dos entradas genericas.
	 */
	@SuppressWarnings("unchecked")
	public int compare(E a, E b) {
		
		return ((Comparable<E>) a).compareTo(b); // si le sacas el menos queda como el original
	}
	
	

}
