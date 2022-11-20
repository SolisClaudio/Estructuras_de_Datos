package Programa;

import java.awt.EventQueue;
import java.util.Iterator;

import Exceptions.EmptyPriorityQueueException;
import Exceptions.InvalidKeyException;
import Exceptions.LogueoInvalidoException;
import Exceptions.RegistroInvalidoException;
import Exceptions.SaldoInsuficienteException;
import Exceptions.TransaccionInvalidaException;
import GUI.LogInFrame;
import TDAColaCP.PriorityQueue;
import TDADiccionario.Entry;
import TDALista.ListaDoblementeEnlazada;
import TDALista.PositionList;

/**
 * @author Arroyo Tomas (126078) - Fernandez Maximo (131672)
 *
 */
public class Logica {
	private static PositionList<CuentaBancaria> cuentas;
	private static CuentaBancaria sesionActual;

	/**
	 * Crea una cuenta bancaria
	 */
	public Logica() {
		cuentas = new ListaDoblementeEnlazada<CuentaBancaria>();
		setSesionActual(null);
	}
	

	/**
	 * Perimte crear una cuenta nueva y aniadirl al listado de cuentas bancarias
	 * @param nombre del propietario
	 * @param apellido del priopietario
	 * @param dni del propietario
	 * @param saldo del propietario
	 * @throws RegistroInvalidoException si alguno de los campos esta vacio
	 */
	public void signIn(String nombre, String apellido, int dni, float saldo) throws RegistroInvalidoException {
		if(!nombre.equals("") && !apellido.equals(""))
		cuentas.addLast(new CuentaBancaria(nombre, apellido, dni, saldo));
		else
			throw new RegistroInvalidoException ("Campos vacios");
	}
	
	/**
	 * permite el acceso a la manipulacion de la cuenta
	 * @param clave contrasenia de la cuenta
	 * @param nombre de la cuenta
	 * @param apellido de la cuenta
	 * @param dni de la cuenta
	 * @throws LogueoInvalidoException si algun dato es incorrecto
	 */
	public void logIn(String clave, String nombre, String apellido, int dni) throws LogueoInvalidoException{
		CuentaBancaria cuenta = buscarCuenta(dni);
		if ((cuenta!=null) && (nombre.equals(cuenta.getNombre())) && (apellido.equals(cuenta.getApellido())) && cuenta.validarCadena(clave)) {
			setSesionActual(cuenta);
		}
		else {throw new LogueoInvalidoException("Datos incorrectos");}
	}
	

	
	/**
	 * Permite el debito de dinero a una valida
	 * @param monto a debitar
	 * @param dni de la cuenta receptora del dinero
	 * @throws TransaccionInvalidaException si el beneficiario es invalido
	 */
	public void debito(float monto, int dni) throws TransaccionInvalidaException{
		try{
			CuentaBancaria beneficiario = buscarCuenta(dni);
			if (beneficiario != null) {
				sesionActual.debito(monto, beneficiario);
				beneficiario.credito(monto,getSesionActual()); //Las transferencias se ven reflejadas desde el lado del receptor como un credito
			}
			else {
				throw new TransaccionInvalidaException("Beneficiario invalido");
			}
		}catch(SaldoInsuficienteException e) {
			throw new TransaccionInvalidaException(e.getMessage());
			}
	}
	
	/**
	 * Permite acreditar dinero a una cuenta valida 
	 * @param monto a acreditar
	 * @param dni del emisor
	 * @throws TransaccionInvalidaException si el emisor es invalido
	 */
	public void credito(float monto, int dni) throws TransaccionInvalidaException{ //solo recibe dinero

		CuentaBancaria emisor = buscarCuenta(dni);
		if (emisor != null) {
			sesionActual.credito(monto,emisor);
		}
		else {
			throw new TransaccionInvalidaException("Emisor invalido");
		}
	}

	/**
	 * Muestra las transacciones de un mismo valor k
	 * @param k valor de la transaccion a buscar
	 * @throws InvalidKeyException si el valor es invalido
	 * @return lista con transacciones de valor k
	 */
	public PositionList<Transaccion> transaccionesValorK(float k) throws InvalidKeyException {
		PositionList<Transaccion> toReturn = new ListaDoblementeEnlazada<Transaccion>();
		try {
			for(Entry<Float, Transaccion> e : sesionActual.transaccionesMismoValor().findAll(k))
				toReturn.addFirst(e.getValue());
		} catch (InvalidKeyException e) {
			throw new InvalidKeyException ("Ingrese un valor valido");
		}
		return toReturn;
	}
	

	/**
	 * Devuelve una lista iterable con f transacciones con mayor valor
	 * @param f numero de transacciones a insertar
	 * @return Retorna una lista con n transaccionnes mayores que f
	 */

	public Iterable<Transaccion> nMayores(float f){
		PositionList<Transaccion> toReturn = new ListaDoblementeEnlazada<Transaccion>();
		PriorityQueue <Float,Transaccion> todasLasTransacciones = sesionActual.transaccionesPorValor();
		try {
			while(!todasLasTransacciones.isEmpty() && todasLasTransacciones.size() > f)
					todasLasTransacciones.removeMin();
			while(!todasLasTransacciones.isEmpty())
				toReturn.addFirst(todasLasTransacciones.removeMin().getValue());
		} catch (EmptyPriorityQueueException e) {
			System.out.println("Error en el manejor de la estructura");
		}
		return toReturn;
	}
	
	/**
	 * Busca la cuenta bancaria de la persona perteneciente al dni ingresado
	 * @param dni de la cuenta a buscar
	 * @return cuenta bancaria asociada al dni pasado por parametro
	 */
	private CuentaBancaria buscarCuenta(int dni) {
		boolean encontre = false;
		CuentaBancaria toReturn = null;
		Iterator<CuentaBancaria> it = cuentas.iterator();
		while(!encontre && it.hasNext()) {
			CuentaBancaria aux = it.next();
			if (aux.getDNI() == dni) {
				encontre = true;
				toReturn = aux;
			}
		}
				return toReturn;
	}
	
	
	/**
	 * Devuelve la sesion iniciada anteriormente
	 * @return sesion que fue iniciada anteriormente, returna null en caso de no haber iniciado sesion anteriormente
	 */
	public static CuentaBancaria getSesionActual() {
		return sesionActual;
	}
	/**
	 * Asigna la sesion pasada por parametro a la sesion actual
	 * @param sesionActual sesion actual
	 */
	public static void setSesionActual(CuentaBancaria sesionActual) {
		Logica.sesionActual = sesionActual;
	}
}
//Se asume que el apellido no contiene el caracter x