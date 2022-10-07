package Auxiliares;

public class BNodo<E> implements Position<E> {
	E element;
	BNodo<E> rightChild, leftChild, padre;
	
	public BNodo<E> getPadre() {
		return padre;
	}
	public void setPadre(BNodo<E> padre) {
		this.padre = padre;
	}
	public BNodo(E elemento, BNodo<E> padre, BNodo<E> hijoIzquierdo, BNodo<E> hijoDerecho) {
		element = elemento;
		rightChild = hijoDerecho;
		leftChild = hijoIzquierdo;
		this.padre = padre;	
	}
	public E element() { 
		return element; 
	}
	public BNodo<E> getRightChild() {
		return rightChild;
	}
	public void setRightChild(BNodo<E> rightChild) {
		this.rightChild = rightChild;
	}
	public BNodo<E> getLeftChild() {
		return leftChild;
	}
	public void setLeftChild(BNodo<E> leftChild) {
		this.leftChild = leftChild;
	}
	public void setElement(E element) {
		this.element = element;
	} 

}
