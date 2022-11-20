package TDAPila;

/**
	 * Implementacion Clase Nodo
	 * @author Jonathan Alejandro Leonhardt, LU:134726
	 */

	public class Nodo<E> {

		private E elemento;
		private Nodo<E> siguiente;
	    
		/**
		 * Crea un nodo y le asigna el elemento
		 * @param elemento a asignar
		 */
		public Nodo(E elem) {
			elemento = elem;
			siguiente = null;
		}

		/**
		 * Crea un nodo ,le asigna el elemento y asigna el siguiente
		 * @param elemento a asignar y nodo a asignar
		 */
		public Nodo(E elem, Nodo<E> sig) {
			elemento = elem;
			siguiente = sig;
		}
	    
		/**
		 * Consulta el elemento del nodo
		 * @return el elemento.
		 */
		public E getElemento() {
			return elemento;
		}
		
		/**
		 * Asigna un elemento E al nodo 
		 * @param E elemento a asignar.
		 */
		public void setElemento(E elem) {
			elemento = elem;
		}
	    
		/**
		 * Consulta el nodo siguiente
		 * @return el siguiente.
		 */
		public Nodo<E> getSiguiente() {
			return siguiente;
		}
		
		/**
		 * Asigna el nodo siguente  
		 * @param element Elemento a insertar.
		 */
		public void setSiguiente(Nodo<E> e) {
			siguiente=e;
		}
	}
