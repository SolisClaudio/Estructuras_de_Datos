package Auxiliares;
import TDALista.PositionList;
import TDALista.ListaDoblementeEnlazada;

/**
 * Clase TNodo<E>
 * @author Nahuel Contreras, Nicolas Marini.
 * Clase que implementa Position<E>.
 */
public class TNodo<E> implements Position<E>{

	private E elemento;
	private TNodo<E> padre;
	private PositionList<E> hijos;
	
	/**
	 * Constructor de la clase TNodo, que inicializa sus atributos principales.
	 * @param E elemento.
	 * @param padre TNodo<E> padre del TNodo<E> actual.
	 */
	public TNodo(E e, TNodo<E> padre) {
		elemento = e;
		this.padre = padre;
		hijos = new ListaDoblementeEnlazada();
	}
	
	public E element() {
		return elemento;
	}

	/**
	 * Retorna el padre del TNodo<E> actual.
	 * @return
	 */
	public Position<E> getPadre(){
		return padre;
	}
	
	/**
	 * Retorna una PositionList<E> de los hijos del TNodo<E> actual.
	 * @return PositionList<E> de hijos.
	 */
	public PositionList<E> getHijos(){
		return hijos;
	}
	
	/**
	 * Asigna un nuevo elemento pasado por parametro.
	 * @param element elemento nuevo.
	 */
	public void setElement(E element) {
		elemento = element;
	}
	
	/**
	 * Asigna un nuevo TNodo<E> como padre del TNodo<E> actual
	 * @param padre nuevo padre del TNodo<E> actual.
	 */
	public void setPadre(TNodo<E> padre) {
		this.padre = padre;
	}
	
	/**
	 * Asigna una nueva PositionList<E> de hijos dada por parametro
	 * @param hijos PositionList<E> nueva.
	 */
	public void setHijos(PositionList<E> hijos) {
		this.hijos = hijos;
	}
}
