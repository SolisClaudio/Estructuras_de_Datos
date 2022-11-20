package TDAArbol;
import Nodos.*;
import Exceptions.*;
public class Prueba {
public static void main(String [] args) {
		
		ArbolBinarioEnlazado<Character> arbol1 = new ArbolBinarioEnlazado<Character>();
		Position<Character> pos2 = null;
		Position<Character> pos3 = null;
		Position<Character> pos4 = null;
		Position<Character> pos5 = null;
		Position<Character> pos6 = null;
		Position<Character> pos7 = null;
		Position<Character> pos8 = null;
		Position<Character> pos9 = null;
		Position<Character> pos10 = null;
		Position<Character> pos11 = null;
		
		
		try {
			
			arbol1.createRoot('/'); 
			pos2 = arbol1.addRight(arbol1.root(),'+');
			pos3 = arbol1.addLeft(arbol1.root(), '*');
			pos4 = arbol1.addLeft(pos3, '+');
			pos5 = arbol1.addRight(pos3, '3');
			pos6 = arbol1.addLeft(pos4, '3');
			pos7 = arbol1.addRight(pos4, '1');
			pos8 = arbol1.addRight(pos2, '2'); 
			pos9 = arbol1.addLeft(pos2, '-');
			pos10 = arbol1.addLeft(pos9, '9');
			pos11 = arbol1.addRight(pos9, '5');
			Entrada<Integer, Integer> ejercicio = contar(arbol1);
			System.out.println("Numeros: "+ejercicio.getKey()+"Operadores: "+ejercicio.getValue());
			
		}
		catch(InvalidOperationException | InvalidPositionException | EmptyTreeException  e1) {
			
			System.out.println(e1.getMessage());
			e1.printStackTrace();
		}
		
		
		 /*
		System.out.println(arbol1.esPropio());*/
		
//		Integer elem = ancestro(pos2, pos6, arbol1); 
//		ListaDE<Integer> lista = (ListaDE<Integer>) camino(pos2, pos6, arbol1);
//		System.out.println("Elem: "+elem);
//		System.out.println("Lista: "+lista);
//		arbol2 = (ArbolBinarioEnlazado<Integer>) espejo(arbol1);
//		System.out.println("ARBOL 2: ");
//		arbol2.niveles();
		
	}
public static Entrada<Integer, Integer> contar (BinaryTree<Character> A) {

	Entrada<Integer, Integer> entrada = new Entrada<Integer, Integer>(0, 0); 
	
	int cn = 0;
	int cop = 0; 
	try {
		if(!A.isEmpty())
			contarAux(A.root(), A, cn, cop, entrada); 	
	}
	catch(EmptyTreeException e) {
		e.printStackTrace();
	}
	
	return entrada; 
}

private static void contarAux(Position<Character> p, BinaryTree<Character> A, int cn, int cop, Entrada<Integer, Integer> entrada) {
	try {
		if(A.isInternal(p)) {
			cop++;
			entrada.put(cn, cop);
				contarAux(A.left(p), A, cn, cop, entrada);
				contarAux(A.right(p), A, cn, cop, entrada);
		}
		else {
			
			cn++;
			entrada.put(cn, cop);}
	}
	catch(InvalidPositionException | BoundaryViolationException e) {
		
		System.out.println(e.getMessage());
	} 		
}
}
