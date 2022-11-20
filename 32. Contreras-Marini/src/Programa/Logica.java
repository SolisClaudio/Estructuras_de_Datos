package Programa;

import java.awt.Font;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Auxiliares.Entrada;
import Auxiliares.Entry;
import Auxiliares.Position;
import Exceptions.*;
import TDACola.ColaCircular;
import TDACola.Queue;
import TDAColaCP.ColaConPrioridadHeap;
import TDADiccionario.Diccionario;
import TDADiccionario.Dictionary;
import TDALista.ListaDoblementeEnlazada;
import TDALista.PositionList;
import TDAPila.Pila;


/**
 * Clase Logica
 * @author Nahuel Contreras, Nicolas Marini.
 * Clase encargada de logica del programa.
 */
public class Logica {
	
	private CuentaBancaria cuenta;
	private GUI parteGrafica;
	
	/**
	 * Constructor de la clase Logica, que tambien asigna el "parametroGrafico" al atributo "parteGrafia"
	 * @param parametroGrafico cuenta pasada por parametro.
	 */
	public Logica( GUI parametroGrafico) {
		parteGrafica = parametroGrafico;
	}
	
	/**
	 * Setea la cuenta bancaria a una pasada por parametro.
	 * @param parametro cuenta pasada por parametro.
	 */
	public void setCuenta(CuentaBancaria parametro) {
		cuenta = parametro;
	} 	
	
	/**
	 * Verifica que los datos esten correctamente ingresados.
	 * @param apellido apellido del usuario.
	 * @param clave clave ingresada.
	 * @return verdadero si la clave es correcta, falso en caso contrario.
	 */
	public boolean validarDatosInicioSesion(String nombre, String apellido, String clave, JTextField monto) {
		boolean retorno = true;
		if (monto.getText().equals(""))
			monto.setText("0");
		
		if(nombre.equals("")) {
			retorno = false;
			JOptionPane.showMessageDialog(null, "Error, hace falta ingresar su nombre.","Error",JOptionPane.ERROR_MESSAGE);}
		else if (apellido.equals("")) {
			retorno = false;
			JOptionPane.showMessageDialog(null, "Error, hace falta ingresar su apellido.","Error",JOptionPane.ERROR_MESSAGE);}
		else if (clave.equals("")) {
			retorno = false;
			JOptionPane.showMessageDialog(null, "Error, hace falta ingresar su clave.","Error",JOptionPane.ERROR_MESSAGE);}
		else if(!testearClave(apellido,clave)) {
			retorno = false;
			JOptionPane.showMessageDialog(null, "Error, la clave y el apellido no se corresponden.","Error",JOptionPane.ERROR_MESSAGE);}
		
		return retorno;
	}
	
	/**
	 * Verifica que la clave este correcta.
	 * @param apellido apellido del usuario.
	 * @param clave clave ingresada.
	 * @return verdadero si la clave es correcta, falso en caso contrario.
	 */
	private boolean testearClave(String apellido, String clave) {
		boolean valido = true;
		ColaCircular<Character> ordenApellido = new ColaCircular<Character>();
		Pila<Character> ordenApellidoInvertido = new Pila<Character>();
		ColaCircular<Character> claveEnCola = new ColaCircular<Character>();

		int tamanioMaximoClave = (apellido.length() * 3) + 1;

		for (int i = 0; i < apellido.length(); i++) {
			ordenApellido.enqueue(apellido.charAt(i));
			ordenApellidoInvertido.push(apellido.charAt(i));
		}

		for (int i = 0; i < apellido.length(); i++)
			ordenApellidoInvertido.push(apellido.charAt(i));

		for (int i = 0; i < clave.length(); i++)
			claveEnCola.enqueue(clave.charAt(i));


		if (tamanioMaximoClave != clave.length())
			valido = false;

		try {
			if (valido) {
				while (valido && ordenApellido.size() > 0) {
					if (ordenApellido.front() == claveEnCola.front()) {
						ordenApellido.dequeue();
						claveEnCola.dequeue();
					} else
						valido = false;
				}
				

				if (claveEnCola.front() != 'x')
					valido = false;
				else
					claveEnCola.dequeue();
				

				while (valido && ordenApellidoInvertido.size() > 0) {
					if (ordenApellidoInvertido.top() == claveEnCola.front()) {
						ordenApellidoInvertido.pop();
						claveEnCola.dequeue();
					} else
						valido = false;
				}
				

			}
		} catch (EmptyQueueException | EmptyStackException | IndexOutOfBoundsException s) {
			s.getMessage();
		}
		
		return valido;
	}
	
	/**
	 * Verifica que todos los datos ingresados sean válidos.
	 * @param parametroNombre nombre del propietario de otra cuenta.
	 * @param parametroApellido apellido del propietario de otra cuenta.
	 * @param parametroDNI DNI del propietario de otra cuenta.
	 * @param parametroMonto monto ingresado para la transaccion.
	 * @param parametroTipo tipo de la transaccion.
	 */
	public void validarDatosPreTrans(String parametroNombre, String parametroApellido, String parametroDNI, String parametroMonto, String parametroTipo) {
		if(parametroNombre.equals("")) 
			JOptionPane.showMessageDialog(null, "Error, hace falta ingresar el nombre.","Error",JOptionPane.ERROR_MESSAGE);
		else if (parametroApellido.equals("")) 
			JOptionPane.showMessageDialog(null, "Error, hace falta ingresar el apellido.","Error",JOptionPane.ERROR_MESSAGE);
		else if (parametroDNI.equals("")) 
			JOptionPane.showMessageDialog(null, "Error, hace falta ingresar el DNI.","Error",JOptionPane.ERROR_MESSAGE);
		else if (parametroMonto.equals("")) 
			JOptionPane.showMessageDialog(null, "Error, hace falta ingresar el monto.","Error",JOptionPane.ERROR_MESSAGE);
		else if(parametroTipo == "Debito" && Float.valueOf(parametroMonto) > cuenta.getSaldo()) {
			JOptionPane.showMessageDialog(null, "Saldo insuficiente","Error",JOptionPane.ERROR_MESSAGE);
		} else
			almacenarCrearTransaccion(parametroNombre,parametroApellido,parametroDNI,parametroMonto, parametroTipo); 
	}

	
	/**
	 * Crea una transaccion y la almacena en la cuenta bancaria.
	 * @param parametroNombre nombre del propietario de otra cuenta.
	 * @param parametroApellido apellido del propietario de otra cuenta.
	 * @param parametroDNI DNI del propietario de otra cuenta.
	 * @param parametroMonto monto ingresado para la transaccion.
	 * @param parametroTipo tipo de la transaccion.
	 */
	private void almacenarCrearTransaccion(String parametroNombre, String parametroApellido, String parametroDNI, String parametroMonto, String parametroTipo) {
		
		CuentaBancaria nuevaCuenta = new CuentaBancaria(parametroNombre,parametroApellido,Integer.parseInt(parametroDNI),0f);
		
		Date fecha = new Date();  
		Calendar calendario = GregorianCalendar.getInstance(); 
		calendario.setTime(fecha);  

		Transaccion nuevaTransaccion = null;
		
		if(parametroTipo == "Debito") {
			cuenta.setSaldo(cuenta.getSaldo() - Float.parseFloat(parametroMonto));
			nuevaTransaccion = new Transaccion(cuenta,nuevaCuenta,parametroTipo,Float. parseFloat(parametroMonto), fecha);}
		else {
			cuenta.setSaldo(cuenta.getSaldo() + Float.parseFloat(parametroMonto));
			nuevaTransaccion = new Transaccion(nuevaCuenta,cuenta,parametroTipo,Float. parseFloat(parametroMonto), fecha);}
		
		JOptionPane.showMessageDialog(null, "Transaccion realizada correctamente!.");
		cuenta.agregarTransaccion(nuevaTransaccion);
	}
	
	/**
	 * Consulta las ultimas transacciones que realizo una cuenta en base a un valor por parametro.
	 * @param parametro cantidad de transacciones a mostrar.
	 */
	public void consultaUltimasN(String parametro) {
		if (parametro.equals("")) {
			JOptionPane.showMessageDialog(null, "Error, hace falta ingresar la cantidad de transacciones.","Error",JOptionPane.ERROR_MESSAGE);
		}else {			
			if(cuenta.getTransacciones().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Error, No existen transacciones realizadas previamente.");
			}else {
				
				int cantidad = Integer.valueOf(parametro);
				
				Iterator<Transaccion> it = cuenta.getTransacciones().iterator();
				PositionList<Transaccion> retorno = new ListaDoblementeEnlazada<Transaccion>();
				Transaccion aux;
	
				if(cantidad > cuenta.getTransacciones().size())
					cantidad = cuenta.getTransacciones().size();
				
				while(it.hasNext() && cantidad > 0) {
					aux = it.next();
					retorno.addLast(aux);
					cantidad--;
				}
				
				parteGrafica.imprimirTransacciones(retorno);
			}	
		}
	}
	
	/**
	 * Consulta las ultimas transacciones de mas valor que realizó una cuenta en base a un valor por parametro.
	 * @param parametro cantidad de transacciones a mostrar.
	 */
	public void consultaUltimasOrdenadas(String parametro) {
		try {
			if(parametro.equals(""))
				JOptionPane.showMessageDialog(null, "Error, hace falta ingresar el valor.","Error",JOptionPane.ERROR_MESSAGE);
			else if(cuenta.getTransacciones().size() == 0) 
				JOptionPane.showMessageDialog(null, "Error, usted no tiene transacciones para consultar.","Error",JOptionPane.ERROR_MESSAGE);
			else {
				int cantidadABuscar = Integer.valueOf(parametro);
				int cantidadAEliminar;
				if(cantidadABuscar >= cuenta.getTransacciones().size())
					cantidadAEliminar = 0;
				else {
					cantidadAEliminar = cuenta.getTransacciones().size() - cantidadABuscar;
				}
				
				ColaConPrioridadHeap<Float,Transaccion> retorno = new ColaConPrioridadHeap<Float,Transaccion>(cantidadABuscar);
				Iterator<Transaccion> it = cuenta.getTransacciones().iterator();
				Transaccion aux;
				
				while(it.hasNext()) {
					aux = it.next();
					retorno.insert(aux.getMonto(), aux);
				}
				
				while(cantidadAEliminar != 0) {
					retorno.removeMin();
					cantidadAEliminar--;
				}
				
				parteGrafica.imprimirTransacciones(retorno);
			}
		}catch(EmptyPriorityQueueException | InvalidKeyException s) {
			s.printStackTrace();
		}	
	}
	
	/**
	 * Consulta las transacciones con valor igual a un valor dado.
	 * @param valorAComparar valor que se compara.
	 */
	public void consultaMismoVTransacciones(String valorAComparar) {
		Dictionary<Float,Transaccion> Aux = new Diccionario<Float,Transaccion>();
		Iterator<Transaccion> it = cuenta.getTransacciones().iterator();
		Entry<Float,Transaccion> cursorDiccionario;
		Transaccion cursorLista;
		float comparar;
		
		if(cuenta.getTransacciones().size() == 0)
			JOptionPane.showMessageDialog(null, "Error, usted no tiene transacciones para consultar.","Error",JOptionPane.ERROR_MESSAGE);
		else if(valorAComparar.equals("")) 
			JOptionPane.showMessageDialog(null, "Error, usted no ingreso el valor necesario.","Error",JOptionPane.ERROR_MESSAGE); 
		else {
			comparar = Float.valueOf(valorAComparar);
			try {
				while(it.hasNext()) {
					cursorLista = it.next();
					Aux.insert(cursorLista.getMonto(), cursorLista);
				}
				parteGrafica.imprimirTransacciones(Aux.findAll(comparar));
			}catch(InvalidKeyException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Consulta las transacciones que se realizaron en una fecha dada.
	 * @param parametroDia dia a comparar.
	 * @param parametroMes mes a comparar.
	 * @param parametroAnio anio a comparar.
	 */
	public void consultaMismaFechaTransacciones(String parametroDia, String parametroMes, String parametroAnio) {
		
		if(verificarFecha(parametroDia,parametroMes,parametroAnio)) {
			int dia = Integer.valueOf(parametroDia);
			int mes = Integer.valueOf(parametroMes);
			int anio = Integer.valueOf(parametroAnio);
			
			PositionList<Transaccion> retorno = new ListaDoblementeEnlazada<Transaccion>();
			Iterator<Transaccion> it = cuenta.getTransacciones().iterator();
			Transaccion aux;
		
			while(it.hasNext()) {
				aux = it.next();
				
				if(aux.getFecha().getYear() + 1900 == anio &&
					aux.getFecha().getMonth() + 1 == mes &&
					aux.getFecha().getDate() == dia)
				{
					retorno.addLast(aux);
				}
		}
			
			parteGrafica.imprimirTransacciones(retorno);
		}		
	}
	
	/**
	 * Consulta las transacciones que tengan un valor superior a un valor dado por parametro y pertenezcan a un tipo dado por parametro.
	 * @param valorASuperar valor a superar por la transaccion.
	 * @param tipo tipo de la transaccion.
	 */
	public void consultaSuperarVTransacciones(String valorASuperar, String tipo) {
		if(cuenta.getTransacciones().size() == 0)
			JOptionPane.showMessageDialog(null, "Error, no tiene transacciones para consultar.","Error",JOptionPane.ERROR_MESSAGE);
		else if(valorASuperar.equals(""))
			JOptionPane.showMessageDialog(null, "Error, no se ingresó valor.","Error",JOptionPane.ERROR_MESSAGE);
		else {
			float valor = Integer.valueOf(valorASuperar);
			Iterator<Transaccion> it = cuenta.getTransacciones().iterator();
			PositionList<Transaccion> retorno = new ListaDoblementeEnlazada<Transaccion>();
			Transaccion aux;
			if(tipo.equals("ambas")) 
				while(it.hasNext()) {
					aux = it.next();
					if(aux.getMonto() > valor)
						retorno.addLast(aux);
				}
			else
				while(it.hasNext()) {
					aux = it.next();
					if(aux.getMonto() > valor && aux.getTipo().equals(tipo))
						retorno.addLast(aux);
				}
			
			parteGrafica.imprimirTransacciones(retorno);
		}
	} 

	/**
	 * Consulta el saldo en una fecha dada por parametro.
	 * @param dia dia ingresado.
	 * @param mes mes ingresado.
	 * @param anio anio ingresado.
	 */
	public void consultarMontoEnFecha(String dia, String mes, String anio) {
		if(verificarFecha(dia,mes,anio)) {
			
			Calendar cal = Calendar.getInstance();
			cal.set(Integer.valueOf(anio),Integer.valueOf(mes), Integer.valueOf(dia));
			Date fechaDate = cal.getTime();
			
			Iterator<Transaccion> it = cuenta.getTransacciones().iterator();
			Transaccion aux;
			float valorActual = cuenta.getSaldo();			
			
			
			
			while(it.hasNext()) {
				aux = it.next();
				if(aux.getFecha().after(fechaDate)) 
					if(aux.getTipo() == "Crédito")
						valorActual -= aux.getMonto();
					else
						valorActual += aux.getMonto();				
			}
			
			parteGrafica.generadorCartel(valorActual,fechaDate);
		}
	}
	
	/**
	 * Verifica que la fecha ingresada por parametro, es correcta.
	 * @param parametroDia dia ingresado.
	 * @param parametroMes mes ingresado.
	 * @param parametroAnio anio ingresado.
	 * @return verdadero si la fecha es valida, falso en caso contrario.
	 */
	private boolean verificarFecha(String parametroDia, String parametroMes, String parametroAnio) {
		boolean retorno = true;
		int dia;
		int mes;
		int anio;
		if(parametroDia.equals("")) {
			retorno = false;
			JOptionPane.showMessageDialog(null, "Error, falta ingresar el dia.","Error",JOptionPane.ERROR_MESSAGE);}
		else if(parametroMes.equals("")) {
			retorno = false;
			JOptionPane.showMessageDialog(null, "Error, falta ingresar el mes.","Error",JOptionPane.ERROR_MESSAGE);}
		else if(parametroAnio.equals("")) {
			retorno = false;
			JOptionPane.showMessageDialog(null, "Error, falta ingresar el anio.","Error",JOptionPane.ERROR_MESSAGE);}
		else {
			dia = Integer.valueOf(parametroDia);
			mes = Integer.valueOf(parametroMes);
			anio = Integer.valueOf(parametroAnio);
		
			if(dia > 31 || dia < 0) {
				retorno = false;
				JOptionPane.showMessageDialog(null, "Error, el dia ingresado es incorrecto.","Error",JOptionPane.ERROR_MESSAGE);}
			else if(mes <= 0 || mes > 12) {
				retorno = false;
				JOptionPane.showMessageDialog(null, "Error, el mes ingresado es incorrecto.","Error",JOptionPane.ERROR_MESSAGE);}
		}
		return retorno;
	}
}
