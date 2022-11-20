package Programa;
import java.util.Date;
import java.util.Iterator;
import Auxiliar.Entry;
import Exceptions.*;
import TDACola.ColaConArregloCircular;
import TDACola.Queue;
import TDAColaCP.ColaConPrioridad;
import TDAColaCP.DefaultComparator;
import TDAColaCP.PriorityQueue;
import TDADiccionario.Diccionario;
import TDADiccionario.Dictionary;
import TDALista.ListaDoblementeEnlazada;
import TDALista.Position;
import TDALista.PositionList;
import TDAPila.PilaConEnlaces;
import TDAPila.Stack;

/**
 * Clase Cuenta Bancaria.
 * @author Quintana Alejo Joaquin - De Giusti Camila.
 */

public class CuentaBancaria {
	//Atributos
	private float saldo;
	public static float saldoInicial ;
	private String DNI;
	private String nombre;
	private String apellido;
	private PositionList<Transaccion> transacciones;
	
	/**
	 * Se crea una instancia de CuentaBancaria, sin saldo inicial.
	 * @param DNI, de la persona dueña de la cuenta.
	 * @param Nombre, de la persona dueña de la cuenta.
	 * @param Apellido, de la persona dueña de la cuenta.
	 */
	
	public CuentaBancaria(String DNI, String Nombre, String Apellido) {
		saldo = 0;
		saldoInicial = 0;
		this.DNI = DNI;
		this.nombre = Nombre;
		this.apellido = Apellido;
		transacciones = new ListaDoblementeEnlazada<Transaccion>();
	}
	
	/**
	 * Se crea una instancia de CuentaBancaria, con un saldo inicial.
	 * @param DNI, de la persona dueña de la cuenta.
	 * @param Nombre, de la persona dueña de la cuenta.
	 * @param Apellido, de la persona dueña de la cuenta.
	 * @param SaldoInicial, al creal la cuenta.
	 */
	
	public CuentaBancaria(String DNI, String Nombre, String Apellido, float SaldoInicial) {
		saldo = SaldoInicial;
		this.saldoInicial = SaldoInicial;
		this.DNI = DNI;
		this.nombre = Nombre;
		this.apellido = Apellido;
		transacciones = new ListaDoblementeEnlazada<Transaccion>();
	}
	
	/**
	 * Devuelve el nombre asociado a la cuenta bancaria.
	 * @return Nombre.
	 */
	
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Devuelve el apellido asociado a la cuenta bancaria. 
	 * @return Apellido.
	 */
	
	public String getApellido() {
		return apellido;
	}
	
	/**
	 * Devuelve el DNI asociado a la cuenta bancaria.
	 * @return DNI.
	 */
	
	public String getDni() {
		return DNI;
	}
	
	/**
	 * Devuelve el saldo actual de la cuenta.
	 * @return saldo.
	 */
	
	public String getSaldo() {
		return ""+saldo;
	}
	
	/**
	 * Devuelve una lista que contiene el historial de transacciones de la cuenta.
	 * @return transacciones.
	 */
	
	public PositionList<Transaccion> getHistorial(){
		return transacciones;
	}
	
	
	/**
	 * Consulta si la clase pasada por parámetro es correcta.
	 * @param clave.
	 * @return True, si el ingreso es valido. False, en caso contrario.
	 */
	
	public boolean ValidarIngreso(String clave) {
		boolean valido = true;
		int cursor = 0;
		Stack<Character> pila = new PilaConEnlaces<Character>();
		Queue<Character> cola = new ColaConArregloCircular<Character>();
		
		try {
			while(clave.charAt(cursor) != 'x' && cursor < apellido.length()) {
				cola.enqueue(apellido.charAt(cursor));
				pila.push(clave.charAt(cursor));
				cursor++;
			}
			
			cursor++;
			
			while(!pila.isEmpty() && valido) {
				if(!(pila.pop().equals(clave.charAt(cursor)))) {
					valido = false;
				}
				else {
					cursor++;
				}
			}
			
			while(!cola.isEmpty() && valido) {
				pila.push(cola.dequeue());
			}
			
			while(!pila.isEmpty() && valido) {
				if(!(pila.pop().equals(clave.charAt(cursor)))) {
					valido = false;
				}
				else {
					cursor++;
				}
			}
		}
		catch(EmptyStackException | EmptyQueueException e) {
			System.out.println(e.getMessage());
		}
		return valido;
	}
	
	/**
	 * Se crea una instancia de transferencia de tipo debito y se agrega al historial.
	 * @param monto
	 * @param nombreBenef
	 * @param apellidoBenef
	 * @param dniBenef
	 * @return True, si la transferencia se pudo realizar. False, en caso contrario.
	 */
	
	public boolean transferenciaDebito(float monto, String nombreBenef, String apellidoBenef, String dniBenef) {
		boolean haySaldo = true;
		String debito = "Debito";
		if(saldo == 0 || monto > saldo) {
			haySaldo = false;
		}
		else {
			Transaccion t = new Transaccion(debito,monto,nombreBenef,apellidoBenef,dniBenef);
			saldo -= monto;
			transacciones.addFirst(t);
		}
		return haySaldo;
	}
	
	/**
	 * Se crea una instancia de transferencia de tipo credito y se agrega al historial.
	 * @param monto
	 * @param nombre
	 * @param apellido
	 * @param dni
	 * @return True, si la transferencia se pudo realizar. False, en caso contrario.
	 */
	
	public boolean transferenciaCredito(float monto, String nombre, String apellido, String dni) {
		boolean c = false;
		if(monto > 0) {
			c = true;
			String credito = "Credito";
			saldo += monto;
			Transaccion t = new Transaccion(credito,monto,nombre,apellido,dni);
			transacciones.addFirst(t);
		}
		return c;
	}
	
	/**
	 * Devuelve las ultimas n transacciones del historial.
	 * @param n, cantidad de transacciones a retornar.
	 * @return String con las transacciones una debajo de la otra.
	 */
	
	public String ultimasNTransacciones(int n){
		String toReturn = "";
			if(transacciones.size() >= n) {
				Iterator<Transaccion> it = transacciones.iterator();
				int cont = 0;
				while(it.hasNext() && cont != n) {
					cont++;
					toReturn = toReturn + "|"+it.next().toString()+"\n";
				}
			}
		return toReturn;
	}
	
	/**
	 * Devuelve las ultimas n transacciones de mayor valor del historial.
	 * @param n, cantidad de transacciones a retornar.
	 * @return String que representa las transacciones.
	 */
	
	public String NtransaccionesMayorValor(int n){
		String toReturn = "";
		DefaultComparator<Float> comp = new DefaultComparator<Float>();
		PriorityQueue<Float,Transaccion> colaCP = new ColaConPrioridad<Float,Transaccion>(comp,transacciones.size());
		try{
			for(Position<Transaccion> p : transacciones.positions()) {
				colaCP.insert(p.element().getMonto(), p.element());	
			}
			for(int i = 0; i < n; i++) {
				toReturn = toReturn + "|" +colaCP.removeMin().getValue().toString()+"\n";
			}
		}
		catch(InvalidKeyException | EmptyPriorityQueueException e) {
			System.out.println(e.getMessage());
		}
		return toReturn;
	}
	
	/**
	 * Devuelve todas las transacciones del mismo valor que el parametro.
	 * @param montoABuscar, que representa las transacciones que posean el mismo monto.
	 * @return String, que representa las transacciones.
	 */
	
	public String filtrarMismoValor(Float montoABuscar){
		//Aclarar en la GUI que si el valor es <= a 0 retorna null.
		String toReturn = "";
		Dictionary<Float,Transaccion> d = new Diccionario<Float,Transaccion>();
		try{
			if(montoABuscar > 0) {
				for(Position<Transaccion> p : transacciones.positions()) {
					if(p.element().getMonto() == montoABuscar) {
						d.insert(p.element().getMonto(), p.element());
					}
				}
			}
			for(Entry<Float,Transaccion> entrada : d.findAll(montoABuscar)) {
				toReturn = toReturn + "|" + entrada.getValue().toString()+"\n";
			}
		}
		catch(InvalidKeyException e) {
			System.out.println(e.getMessage());
		}
		return toReturn;
	}
	
	/**
	 * Devuelve todas las transacciones mayores a un valor pasado por parametro, segun el tipo pedido.
	 * @param montoAFiltrar, que representa las transacciones que superen dicho monto.
	 * @param eleccion, que representa el tipo de la transaccion. (Debito, credito o ambas).
	 * @return String, que representa las transacciones.
	 */
	public String filtrarMayores(Float montoAFiltrar, String eleccion) {
		String toReturn = "";
		if(eleccion.equals("ambas")) {
			for(Position<Transaccion> p : transacciones.positions()) {
				if(p.element().getMonto() > montoAFiltrar) {
					toReturn = toReturn + "|" + p.element().toString()+"\n";
				}
			}
		}
		else {
			if(eleccion.equals("debito")) {
				for(Position<Transaccion> p : transacciones.positions()) {
					if(p.element().getMonto() > montoAFiltrar && p.element().getTipo().equals("Debito")) {
						toReturn = toReturn + "|" + p.element().toString()+"\n";
					}
				}
			}
			else {
				if(eleccion.equals("credito")) {
					for(Position<Transaccion> p : transacciones.positions()) {
						if(p.element().getMonto() > montoAFiltrar && p.element().getTipo().equals("Credito")) {
							toReturn = toReturn + "|" + p.element().toString()+"\n";
						}
					}
				}
			}
		}
		return toReturn;
	}
	
	/**
	 * Devuelve todas las transacciones realizadas en una determinada fecha.
	 * @param date, representa una fecha.
	 * @return String, transacciones realizadas en esa fecha.
	 */
	
	public String transaccionesEnUnaFecha(Date date) {
		String toReturn = "";
		for(Position<Transaccion> p : transacciones.positions()) {
			if(p.element().getFecha().getDay() == date.getDay() && p.element().getFecha().getMonth() == date.getMonth() && p.element().getFecha().getYear() == date.getYear()) {
				toReturn = toReturn + "|" +p.element().toString()+"\n";
			}
		}
		return toReturn;
	}
	
	/**
	 * Devuelve el saldo de la cuenta en una fecha determinada.
	 * @param date, representa una fecha.
	 * @return String, saldo de la cuenta en la fecha solicitada. 
	 */
	
	public String saldoCuentaDetDia(Date date) {
		String toReturn = "";
		float saldo = saldoInicial;
		for(Position<Transaccion> p : transacciones.positions()) {
			if(p.element().getFecha().getDay() == date.getDay() && p.element().getFecha().getMonth() == date.getMonth() && p.element().getFecha().getYear() == date.getYear()) {
				if(p.element().getTipo().equals("Debito")) {
					saldo -= p.element().getMonto();
				}
				else {
					saldo += p.element().getMonto();
				}
			}
		}
		toReturn = "El saldo en "+ date.toString()+" era de: "+saldo;
		return toReturn;
	}
}