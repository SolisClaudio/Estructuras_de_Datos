package TDAArbol;

import java.util.Iterator;

import Excepciones.BoundaryViolationException;
import Excepciones.EmptyListException;
import Excepciones.EmptyTreeException;
import Excepciones.InvalidOperationException;
import Excepciones.InvalidPositionException;
import Auxiliares.Position;
import Auxiliares.TNodo;
import TDALista.PositionList;
import TDALista.ListaDoblementeEnlazada;

public class Arbol<E> implements Tree<E> {
	
	private int tamanio;
	private TNodo<E> raiz;
	
	public Arbol() {
		tamanio=0;
		raiz=null;
	}
	@Override
	public int size() {
		return tamanio;
		}

	@Override
	public boolean isEmpty() {
		return tamanio==0;
	}

	@Override
	public Iterator<E> iterator() {
		PositionList<E> retorno = new ListaDoblementeEnlazada<E>();
		if(tamanio!=0)
			recorridoPreRotulos(retorno,raiz);
		return retorno.iterator();
	}

	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> retorno = new ListaDoblementeEnlazada<Position<E>>();
		if(raiz!=null)
			recorridoPrePositions(retorno,raiz);
		return retorno;
	}
	
	private void recorridoPrePositions(PositionList<Position<E>> lista,TNodo<E> raiz) {
		lista.addLast(raiz);
		for(TNodo<E> w : raiz.getHijos())
				recorridoPrePositions(lista,w);
	}
	private void recorridoPreRotulos(PositionList<E> lista,TNodo<E> raiz) {
		lista.addLast(raiz.element());
		for(TNodo<E> w : raiz.getHijos())
				recorridoPreRotulos(lista,w);
	}

	@Override
	public E replace(Position<E> v, E e) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(v);
		E retorno = nodo.element();
		nodo.setElemento(e);
		return retorno;
	}
	
	private TNodo<E> checkPosition(Position<E> p) throws InvalidPositionException{
		if(tamanio==0 | p==null) throw new InvalidPositionException("Error, la posicion ingresada no corresponde a una posicion valida para este arbol");
		TNodo<E> nodo = null;
		try{
			nodo= (TNodo<E>) p;
		}
		catch(ClassCastException e) {
			throw new InvalidPositionException("Error, la posicion ingresada no coresponde a una posicion valida para este arbol");
		}
		return nodo;
	}

	@Override
	public Position<E> root() throws EmptyTreeException {
		if(tamanio==0) throw new EmptyTreeException("Error, no se puede obtener la raiz de un arbol vacio");
		return raiz;
	}

	@Override
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		TNodo<E> nodo = checkPosition(v);
		TNodo<E> retorno = nodo.getPadre();
		if(retorno == null) throw new BoundaryViolationException("Error, no se puede obtener el padre de la raiz del arbol");
		return retorno;
		}

	@Override
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(v);
		PositionList<Position<E>> retorno = new ListaDoblementeEnlazada<Position<E>>();
		for(TNodo<E> w : nodo.getHijos())
			 retorno.addLast(w);
		return retorno;
		
	}

	@Override
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(v);
		return !nodo.getHijos().isEmpty();
	}

	@Override
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(v);
		return nodo.getHijos().isEmpty();
	}

	@Override
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(v);
		return nodo==raiz;
	}

	@Override
	public void createRoot(E e) throws InvalidOperationException {
		if(tamanio!=0) throw new InvalidOperationException("Error, no se puede crear una raiz si el arbol no esta vacio");
		raiz = new TNodo<E>(e,null);
		tamanio++;
	}

	@Override
	public Position<E> addFirstChild(Position<E> p, E e) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(p);
		TNodo<E> retorno = new TNodo<E>(e,nodo);
		tamanio++;
		nodo.getHijos().addFirst(retorno);
		return retorno;
	}

	@Override
	public Position<E> addLastChild(Position<E> p, E e) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(p);
		TNodo<E> retorno = new TNodo<E>(e,nodo);
		tamanio++;
		nodo.getHijos().addLast(retorno);
		return retorno;
	}

	@Override
	public Position<E> addBefore(Position<E> p, Position<E> rb, E e) throws InvalidPositionException {
		TNodo<E> nodo =  checkPosition(p);
		TNodo<E> nodorb = checkPosition(rb);
		Position<TNodo<E>> posicionDeRBEnLista = null;
		TNodo<E> nuevoNodo = new TNodo<E>(e,nodo);
		boolean encontrado = false;
		Iterator<Position<TNodo<E>>> it = nodo.getHijos().positions().iterator();
		while(it.hasNext() && !encontrado) {
			posicionDeRBEnLista = it.next();
			encontrado = posicionDeRBEnLista.element() == nodorb;
		}
		if(!encontrado) throw new InvalidPositionException("Error, rb no corresponde a un nodo hijo de p");
		nodo.getHijos().addBefore(posicionDeRBEnLista, nuevoNodo);
		tamanio++;
		return nuevoNodo;
	}
	
	@Override
	public Position<E> addAfter(Position<E> p, Position<E> lb, E e) throws InvalidPositionException {
		TNodo<E> nodo =  checkPosition(p);
		TNodo<E> nodolb = checkPosition(lb);
		Position<TNodo<E>> posicionDeRBEnLista = null;
		TNodo<E> nuevoNodo = new TNodo<E>(e,nodo);
		boolean encontrado = false;
		Iterator<Position<TNodo<E>>> it = nodo.getHijos().positions().iterator();
		while(it.hasNext() && !encontrado) {
			posicionDeRBEnLista = it.next();
			encontrado = posicionDeRBEnLista.element() == nodolb;
		}
		if(!encontrado) throw new InvalidPositionException("Error, rb no corresponde a un nodo hijo de p");
		nodo.getHijos().addAfter(posicionDeRBEnLista, nuevoNodo);
		tamanio++;
		
		return nuevoNodo;
	}

	@Override
	public void removeExternalNode(Position<E> p) throws InvalidPositionException {
		TNodo<E> nodo  = checkPosition(p);
		if(!nodo.getHijos().isEmpty()) throw new InvalidPositionException("Error, el nodo que se intenta eliminar no corresponde a un nodo externo del arbol");
		if(tamanio == 1) //El nodo que se intenta eliminar es la raiz del arbol/
			raiz = null;
		else { //el nodo que se intenta eliminar es un nodo externo distinto a la hoja del arbol
			Position<TNodo<E>> posicionNodoEnLista = null;
			PositionList<TNodo<E>> hermanosNodo = nodo.getPadre().getHijos();
			Iterator<Position<TNodo<E>>> it = hermanosNodo.positions().iterator();
			boolean encontrado = false;
			while(it.hasNext() && !encontrado) {
				posicionNodoEnLista = it.next();
				encontrado = posicionNodoEnLista.element() == nodo ;
			}
			if(!encontrado) throw new InvalidPositionException("Error, la posicion no corresponde a una posicion valida");
				hermanosNodo.remove(posicionNodoEnLista);
		}
		nodo.setPadre(null);
		nodo.setElemento(null);
		tamanio--;
	}
			
	

	@Override
	public void removeInternalNode(Position<E> p) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(p);
		if(nodo.getHijos().size()==0) throw new InvalidPositionException("Error, el nodo ingresado no corresponde a un nodo hoja del arbol");
		try{
			if(nodo==raiz) {
				if(nodo.getHijos().size()>1) throw new InvalidPositionException("Error, no se puede eliminar la raiz del arbol si tiene mas de un hijo.");
				raiz=nodo.getHijos().first().element();
				nodo.getHijos().remove(nodo.getHijos().first());
				raiz.setPadre(null);
			}
			else {
				Position<TNodo<E>> posicionNodoEnLista = null;
				PositionList<TNodo<E>> hermanosNodo = nodo.getPadre().getHijos();
				Iterator<Position<TNodo<E>>> it = hermanosNodo.positions().iterator();
				boolean encontrado = false;
				while(it.hasNext() && !encontrado) {
					posicionNodoEnLista = it.next();
					encontrado = posicionNodoEnLista.element() == nodo ;
				}
				if(!encontrado) throw new InvalidPositionException("Error, la posicion no corresponde a una posicion valida");
				for (TNodo<E> hijos : nodo.getHijos()) {
					hijos.setPadre(nodo.getPadre());
					hermanosNodo.addBefore(posicionNodoEnLista, hijos);
				}
				hermanosNodo.remove(posicionNodoEnLista);
			}
			tamanio--;
		}
		catch(EmptyListException e) { e.printStackTrace(); } 
	}

	@Override
	public void removeNode(Position<E> p) throws InvalidPositionException {
		if(isExternal(p))
			removeExternalNode(p);
		else removeInternalNode(p);
		
	}
	
	public Tree<E> clone(){
		Tree<E> retorno = new Arbol<E>();
		try {
			if(raiz!=null) {
				retorno.createRoot(raiz.element());
				cloneRec(retorno,retorno.root(),raiz);
				}
			}
		catch(InvalidOperationException | EmptyTreeException e) { e.printStackTrace(); }
		return retorno;
	}
	
	private void cloneRec(Tree<E> Arbol, Position<E> p, TNodo<E> nodo) {
		try {
			Position<E> pNueva;
			for(TNodo<E> nodoNuevo : nodo.getHijos()) {
				pNueva = Arbol.addLastChild(p, nodoNuevo.element());
				cloneRec(Arbol,pNueva,nodoNuevo);
			}
		}
		catch(InvalidPositionException e) {e.printStackTrace();}
		
	}
	
	public String toString() {
		String retorno = "";
		if(raiz!=null)
			retorno = toStringRec(raiz);
		return retorno;
	}
	
	private String toStringRec(TNodo<E> nodo) {
		String retorno ="";
		if(!nodo.getHijos().isEmpty()) {
			retorno = ""+nodo.element()+"(";
			for(TNodo<E> aux : nodo.getHijos())
				retorno = retorno + toStringRec(aux);
			retorno = retorno + ")";
		}
		else retorno = " "+nodo.element();
		return retorno;
	}

}
