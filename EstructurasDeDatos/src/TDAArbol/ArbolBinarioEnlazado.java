package TDAArbol;

import java.util.Iterator;

import Excepciones.BoundaryViolationException;
import Excepciones.EmptyTreeException;
import Excepciones.InvalidOperationException;
import Excepciones.InvalidPositionException;
import Auxiliares.Position;
import Auxiliares.BNodo;
import TDALista.PositionList;
import TDALista.ListaDoblementeEnlazada;

public class ArbolBinarioEnlazado<E> implements BinaryTree<E> {
	protected BNodo<E> root;
	protected int size;
	
	public ArbolBinarioEnlazado() {
		root=null;
		size=0;
	}
	
	private BNodo<E> checkPosition(Position<E> p) throws InvalidPositionException{
		BNodo<E> retorno=null;
		if(size==0 || p==null) throw new InvalidPositionException("Error, la posicion ingresa es invalida.");
		try {
			retorno= (BNodo<E>) p;
		}
		catch(ClassCastException e) { throw new InvalidPositionException("Error, la posicion ingresada es invalida.");}
		return retorno;
	}
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return root==null;
	}

	@Override
	public Iterator<E> iterator() {
		PositionList<E> retorno=new ListaDoblementeEnlazada<E>();
		if(root!=null) this.iteratorRecursivo(retorno,root);
		return retorno.iterator();
	}
	
	private void iteratorRecursivo(PositionList<E> lista, BNodo<E> raiz) {
		if(raiz!= null) {
			lista.addLast(raiz.element());
			if(root.getLeftChild()!=null)
				iteratorRecursivo(lista, raiz.getLeftChild());
			if(root.getRightChild()!=null)
				iteratorRecursivo(lista, raiz.getRightChild());
	
		}
	}
	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> retorno=new ListaDoblementeEnlazada<Position<E>>();
		if(root!=null) this.positionsRecursivo(retorno,root);
		return retorno;
	}
	
	private void positionsRecursivo(PositionList<Position<E>> lista, BNodo<E> raiz) {
		if(raiz!=null) {
			lista.addLast(raiz);
			if(root.getLeftChild()!=null)
				positionsRecursivo(lista, raiz.getLeftChild());
			if(root.getRightChild()!=null)
				positionsRecursivo(lista, raiz.getRightChild());
		}
	}

	@Override
	public E replace(Position<E> v, E e) throws InvalidPositionException {
		BNodo<E> nodo = checkPosition(v);
		E retorno = nodo.element();
		nodo.setElement(e);
		return retorno;
	}

	@Override
	public Position<E> root() throws EmptyTreeException {
		if(root==null) throw new EmptyTreeException("Error, no se puede obtener la raiz de un arbol vacio");
		return root;
	}

	@Override
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		BNodo<E> nodo = checkPosition(v);
		if(nodo==root) throw new BoundaryViolationException("Error, no se puede obtener el padre de la raiz del arbol");
		return nodo.getPadre();
	}

	@Override
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		BNodo<E> nodo = checkPosition(v);
		PositionList<Position<E>> retorno= new ListaDoblementeEnlazada<Position<E>>();
		if(nodo.getLeftChild()!=null) retorno.addLast(nodo.getLeftChild());
		if(nodo.getRightChild()!=null) retorno.addLast(nodo.getRightChild());
		return retorno;
	}

	@Override
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		BNodo<E> nodo= checkPosition(v);
		return nodo.getLeftChild()!=null || nodo.getRightChild()!=null;
	}

	@Override
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		BNodo<E> nodo= checkPosition(v);
		return nodo.getLeftChild()==null && nodo.getRightChild()==null;
		
	}

	@Override
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		BNodo<E> nodo = checkPosition(v);
		return nodo==root;
	}

	@Override
	public Position<E> left(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		BNodo<E> nodo = checkPosition(v);
		BNodo<E> retorno  = nodo.getLeftChild();
		if(retorno==null) throw new BoundaryViolationException("Error, no se puede obtener el hijo izquierdo de un nodo sin hijo izquierdo");
		return retorno;
	}

	@Override
	public Position<E> right(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		BNodo<E> nodo = checkPosition(v);
		BNodo<E> retorno  = nodo.getRightChild();
		if(retorno==null) throw new BoundaryViolationException("Error, no se puede obtener el hijo derecho de un nodo sin hijo izquierdo");
		return retorno;
	}

	@Override
	public boolean hasLeft(Position<E> v) throws InvalidPositionException {
		BNodo<E> nodo = checkPosition(v);
		return nodo.getLeftChild()!=null;
	}

	@Override
	public boolean hasRight(Position<E> v) throws InvalidPositionException {
		BNodo<E> nodo = checkPosition(v);
		return nodo.getRightChild()!=null;
	}

	@Override
	public Position<E> createRoot(E r) throws InvalidOperationException {
		if(root!=null) throw new InvalidOperationException("Error, no se puede crear una nueva raiz en un arbol que no esta vacio");
		root=new BNodo<E>(r,null,null,null);
		size=1;
		return root;
	}

	@Override
	public Position<E> addLeft(Position<E> v, E r) throws InvalidOperationException, InvalidPositionException {
		BNodo<E> nodo = checkPosition(v);
		if(nodo.getLeftChild()!=null) throw new InvalidOperationException("Error, no se puede agregar un hijo izquierdo a un nodo que ya tiene hijo izquierdo");
		BNodo<E> nuevoNodo = new BNodo<E>(r,nodo,null,null);
		size++;
		nodo.setLeftChild(nuevoNodo);
		return nuevoNodo;
	}

	@Override
	public Position<E> addRight(Position<E> v, E r) throws InvalidOperationException, InvalidPositionException {
		BNodo<E> nodo = checkPosition(v);
		if(nodo.getRightChild()!=null) throw new InvalidOperationException("Error, no se puede agregar un hijo derecho a un nodo que ya tiene hijo derecho");
		BNodo<E> nuevoNodo = new BNodo<E>(r,nodo,null,null);
		size++;
		nodo.setRightChild(nuevoNodo);
		return nuevoNodo;
	}

	@Override
	public E remove(Position<E> v) throws InvalidOperationException, InvalidPositionException {
		BNodo<E> nodo = checkPosition(v);
		BNodo<E> hijo=null;
		BNodo<E> padre=null;
		E retorno =nodo.element();
		if(nodo.getLeftChild()!=null)
			hijo = nodo.getLeftChild();
		if(nodo.getRightChild()!=null && hijo!=null)
			throw new InvalidOperationException("Error, no se puede eliminar un nodo que tiene mas de un hijo;");
			else hijo=nodo.getRightChild();
		if(nodo!=root) {
			padre=nodo.getPadre();
			if(padre.getLeftChild()==nodo) 
				padre.setLeftChild(hijo);
			else padre.setRightChild(hijo);
		}
		else root=hijo;
		nodo.setPadre(null);
		nodo.setLeftChild(null);
		nodo.setElement(null);
		nodo.setRightChild(null);
		if(hijo!=null)
			hijo.setPadre(padre);
		size--;
		return retorno;
	}

	@Override
	public void attach(Position<E> r, BinaryTree<E> T1, BinaryTree<E> T2) throws InvalidPositionException {
		BNodo<E> nodo = checkPosition(r);
		if(isInternal(nodo)) throw new InvalidPositionException("Error");
		Position<E> p1=null;
		Position<E> p2 = null;
		try {
			if(!T1.isEmpty()) p1 = T1.root();
			if(!T2.isEmpty()) p2 = T2.root();
			attachRecursivo(nodo,T1,p1,T2,p2);
		}
		catch(EmptyTreeException e) { e.printStackTrace(); } 
	}
	private void attachRecursivo(BNodo<E> nodo, BinaryTree<E> T1, Position<E> p1, BinaryTree<E> T2, Position<E> p2) {
		try{Position<E> subArbol1=null;
			Position<E> subArbol2=null;
			if(p1!=null) {
				size++;
				nodo.setLeftChild(new BNodo<E>(p1.element(),nodo,null,null));
				if(T1.hasLeft(p1))
					subArbol1= T1.left(p1);
				if(T1.hasRight(p1))
					subArbol2= T1.right(p1);
				attachRecursivo(nodo.getLeftChild(), T1, subArbol1, T1, subArbol2);
			}
			subArbol1=null;
			subArbol2=null;
			if(p2!=null) {
				size++;
				nodo.setRightChild(new BNodo<E>(p2.element(),nodo,null,null));
				if(T2.hasLeft(p2))
					subArbol1= T2.left(p2);
				if(T2.hasRight(p2))
					subArbol2= T2.right(p2);
				attachRecursivo(nodo.getRightChild(), T2, subArbol1, T2, subArbol2);
			}
	}
		catch(InvalidPositionException | BoundaryViolationException e) { e.printStackTrace();}
}
	public BinaryTree<E> clone(){
		BinaryTree<E> retorno = new ArbolBinarioEnlazado<E>();
		try{
			if(root!=null){
			retorno.createRoot(root.element());
			clonarRec(root , retorno , retorno.root());
			}
		}
		catch(InvalidOperationException | EmptyTreeException e){e.printStackTrace();}
		return retorno;
	}

	private void clonarRec(BNodo<E> raiz, BinaryTree<E> arbol, Position<E> p){
		try{
			if(raiz.getLeftChild()!=null){
				arbol.addLeft(p,raiz.getLeftChild().element());
				clonarRec(raiz.getLeftChild(),arbol,arbol.left(p));
			} 
			if(raiz.getRightChild()!=null){
				arbol.addRight(p,raiz.getRightChild().element());
				clonarRec(raiz.getRightChild(),arbol,arbol.right(p));
			} 
		}
		catch(InvalidPositionException | InvalidOperationException | BoundaryViolationException e){e.printStackTrace();}
	}
}
