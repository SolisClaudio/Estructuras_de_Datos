package Programa;

import java.util.Date;
import java.util.Iterator;

import Exceptions.*;
import TDALista.*;
import TDACola.*;
import TDAPila.*;
import TDAColaConPrioridad.*;
import TDADiccionario.*;
import TDADiccionario.Entry;

public class CuentaBancaria {

	protected String nombre;
	protected String apellido;
	protected String contrasenia;
	protected int monto;
	protected int dni;
    protected PositionList<Transaccion> Transacciones;
	
    public CuentaBancaria(String nom ,String ape,String contraS,int dni) throws InvalidContraseniaException{
		nombre=nom;
		apellido=ape;
		this.dni=dni;
		if(verificarContraseña(contraS)==false) throw new InvalidContraseniaException("Contraseña incorrecta"); 
			this.contrasenia=contraS;
		Transacciones= new ListaDoblementeEnlazada<Transaccion>();
		monto=0;
		
	}
    
	public CuentaBancaria(String nom ,String ape,String contra,int dni,int monto) throws InvalidContraseniaException {
		nombre=nom;
		apellido=ape;
		this.dni=dni;
		if(verificarContraseña(contra)==false) throw new InvalidContraseniaException("Contraseña incorrecta"); 
		this.contrasenia=contra;
		Transacciones= new ListaDoblementeEnlazada<Transaccion>();
		this.monto=monto;
	}
	
	public void transferencias(int valor,int destinatario,int mes,int dia,int hora) throws InvalidMontoException  {//3
		if(SaldoEnFecha(mes,dia)-valor<0)throw new InvalidMontoException ("Saldo insuficiente");
		monto=monto-valor;
		Transaccion nuevo = new Transaccion("Debito",destinatario,valor,mes,dia,hora);
		insertarOrdenado(nuevo);
	}
	
	public void depositos(String nom,String ape,int valor,int mes,int dia,int hora) {//4
	monto=monto+valor;
	Transaccion nuevo = new Transaccion("Credito",nom,ape,valor,mes,dia,hora);
	insertarOrdenado(nuevo);
	}
	
	private void insertarOrdenado(Transaccion nuevo) {
		boolean inserto=false;
		if(Transacciones.isEmpty()) {
			inserto=true;
			Transacciones.addFirst(nuevo);
		}else {
	          Position<Transaccion> aux;			
	          Iterator<Position<Transaccion>> it = Transacciones.positions().iterator();
	          while(it.hasNext() && !inserto) {
	        	  aux=it.next();
	        	  if(nuevo.getFecha().compareTo(aux.element().getFecha())<0) {
	        		  try {
						Transacciones.addBefore(aux, nuevo);
					} catch (InvalidPositionException e) {
						e.printStackTrace();
					}
	        		  inserto=true;
	        	  }
	        	  if(nuevo.getFecha().compareTo(aux.element().getFecha())==0) {
	        		  if(nuevo.getHora()<aux.element().getHora()){
	        			  try {
							Transacciones.addBefore(aux, nuevo);
						} catch (InvalidPositionException e) {
							e.printStackTrace();
						}
	            		  inserto=true;  
	        		  }
	        	  }
	          }
		}
		if(!inserto) {
		Transacciones.addLast(nuevo);
		}
	}
	
	public PositionList<Transaccion> UltimosN(int n) throws InvalidNException{ //5
		if(n>Transacciones.size()) throw new InvalidNException("No existen N Trasacciones");
		PositionList<Transaccion> retornar= new ListaDoblementeEnlazada<Transaccion>();
		Stack<Transaccion> aux = new PilaEnlazada<Transaccion>();
		Iterator<Transaccion> it = Transacciones.iterator();
		Transaccion aux2;
		while(it.hasNext()) {
			aux2=it.next();
			aux.push(aux2);
		}
		int cont=0;
		while(cont!=n && !aux.isEmpty()) {
			try {
				retornar.addLast(aux.pop());
				cont++;
			} catch (EmptyStackException e) {
				e.printStackTrace();
			}
		}
		return retornar;
	}
	
    public PositionList<Transaccion> nMasValor(int n){ //6
		PositionList<Transaccion> retornar= new ListaDoblementeEnlazada<Transaccion>();
		Iterator<Transaccion> it = Transacciones.iterator();
		PriorityQueue<Integer,Transaccion> cola = new HeapPQueue<Integer,Transaccion>();
		Stack<Transaccion> pilaAux= new PilaEnlazada<Transaccion>();
		Transaccion aux;
		int cont = 0;
		while(it.hasNext()) {
			aux=it.next();
			try {
				cola.insert(aux.getMonto(), aux);
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			}
		}
		while(!cola.isEmpty()) {
			try {
				pilaAux.push(cola.removeMin().getValue());
			} catch (EmptyPriorityQueueException e) {
				e.printStackTrace();
			}
		}
		while(cont!=n && !pilaAux.isEmpty()) {
			try {
				retornar.addLast(pilaAux.pop());
				cont++;
			} catch (EmptyStackException e) {
				e.printStackTrace();
			}
		}
		return retornar;
	}
	
	public PositionList<Transaccion> MismoValor(int valor){ //7
		PositionList<Transaccion> retornar= new ListaDoblementeEnlazada<Transaccion>();
		Iterator<Transaccion> it = Transacciones.iterator();
		Dictionary<Integer,Transaccion> DiccAux = new DiccionarioHashAbierto<Integer,Transaccion>();
		Transaccion aux2;
		while(it.hasNext()) {
			aux2=it.next();
			try {
				DiccAux.insert(aux2.getMonto(), aux2);
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			}
		}
		try {
			Iterator<Entry<Integer, Transaccion>> it2=DiccAux.findAll(valor).iterator();
		    while(it2.hasNext()) {
		    	aux2=it2.next().getValue();
		    	retornar.addLast(aux2);
		    }
		
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		return retornar;
	}
	
	public PositionList<Transaccion> TransaccionesFecha(int dia ,int mes){ //8
		PositionList<Transaccion> retornar= new ListaDoblementeEnlazada<Transaccion>();		
		Date fecha = new Date(2022,mes,dia);
		Iterator<Transaccion> it = Transacciones.iterator();
		Transaccion aux;
		while(it.hasNext()) {
			aux=it.next();
			if((aux.getFecha().compareTo(fecha))==0) {
				retornar.addLast(aux);
			}
		}
		return retornar;
	}

	public PositionList<Transaccion> TransaccionesSuperenN(char tipo,int n){
		PositionList<Transaccion> retornar= new ListaDoblementeEnlazada<Transaccion>();		
		Iterator<Transaccion> it = Transacciones.iterator();
		Transaccion aux;
		if(tipo=='D') {
			while(it.hasNext()) {
				aux=it.next();
				if((aux.getTipo().charAt(0)=='D') && (aux.getMonto()>n)) {
					retornar.addLast(aux);
				}
			}
		}else if(tipo=='C') {
			     while(it.hasNext()) {
				       aux=it.next();
				        if((aux.getTipo().charAt(0)=='C') && (aux.getMonto()>n)) {
					        retornar.addLast(aux);
				        }
		     	   }
		      }else {
		    	  while(it.hasNext()) {
				       aux=it.next();
				        if((aux.getMonto()>n)) {
					        retornar.addLast(aux);
				        }
		     	   }
		      }
		return retornar;
	}
	
	public int SaldoEnFecha(int mes,int dia) {
		int retornar=0;
		Iterator<Transaccion> it = Transacciones.iterator();
		Transaccion aux;
		boolean llegoAFecha=false;
		Date fecha = new Date(2022,mes,dia);
		while(it.hasNext() && !llegoAFecha) {
			aux=it.next();
			if(fecha.compareTo(aux.getFecha())>0) {
				 if(aux.getTipo().charAt(0)=='D') {
					 retornar=retornar-aux.getMonto();
				 }else {
					 retornar=retornar+aux.getMonto();
				 }
			     }else if(fecha.compareTo(aux.getFecha())==0) {
				            if(aux.getTipo().charAt(0)=='D') {
					           retornar=retornar-aux.getMonto();
				            }else {
					              retornar=retornar+aux.getMonto();
				                  }
		          }
			if(fecha.compareTo(aux.getFecha())<0){
				llegoAFecha=true;}
		}
		return retornar;
	}
	
	
	private boolean verificarContraseña(String contra) {

		boolean verifica=true;
		for(int i=0;i<apellido.length();i++) {
			if(contra.charAt(i)!=apellido.charAt(i)) {
				verifica=false;
			}
		}		
		Queue<Character> aux = crearContrasenia();
		if(aux.size()==contra.length()) {
			for(int j=0;j<contra.length() && verifica;j++) {
				try {
					   if(aux.dequeue()!=contra.charAt(j)) {
		                  verifica=false;
					   }
				    } catch (EmptyQueueException e) {
					    e.printStackTrace();
				}
			}
			
		}else verifica=false;
		return verifica ;
	}
	
    private Queue<Character> crearContrasenia() {
		Queue<Character> aux = new ColaConArreglo<Character>(); 
		Stack<Character> aux2 = new PilaEnlazada<Character>();
		Stack<Character> aux3 = new PilaEnlazada<Character>();
		for(int i=0;i<apellido.length();i++) {
			aux.enqueue(apellido.charAt(i));
			aux2.push(apellido.charAt(i));
			aux3.push(apellido.charAt(i));
		}
		aux.enqueue('x');
		try {
		       while(!aux2.isEmpty()) {
			      aux.enqueue(aux2.pop());
			   }	
		       while(!aux3.isEmpty()) {
			      aux.enqueue(aux3.pop());
			   }	
		    } catch (EmptyStackException e) {
			     e.printStackTrace();
		}
		
		return aux;
	}

	public String toString() {
		return "Nombre:"+nombre+" Apellido:"+apellido+" Dni:"+dni+" Saldo:"+ monto ;	
	}
	
	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public int getMonto() {
		return monto;
	}

	public int getDni() {
		return dni;
	}

	public PositionList<Transaccion> getTransacciones() {
		return Transacciones;
	}
}
