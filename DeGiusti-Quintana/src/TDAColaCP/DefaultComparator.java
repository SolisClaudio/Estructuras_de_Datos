package TDAColaCP;

/**
 * Clase DefaultComparator.
 * @author De Giusti Camila - Quintana Alejo Joaquin.
 *
 * @param <E>
 */
public class DefaultComparator<E> implements java.util.Comparator<E> {
	
	/**
	 * Devuelve un entero producto de comparar a y b.
	 * @param E a, primer elemento a comparar.
	 * @param E b, segundo elemento a comparar.
	 */
	public int compare(E a, E b) throws ClassCastException {
		return (-1*((Comparable<E>) a).compareTo(b));
	}
}
