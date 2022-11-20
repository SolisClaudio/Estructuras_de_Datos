package Programa;

import java.util.Date;

/**
 * Clase Transaccion
 * @author Quintana Alejo Joaquin - De Giusti Camila
 */

public class Transaccion {
	//Atributos
	private Date fecha;
	private String tipo;
	private float monto;
	private String nombre;
	private String apellido;
	private String dni;
	
	/**
	 * Se crea una instancia de la clase Transaccion.
	 * @param tipo, de la transaccion. (Debito o credito).
	 * @param monto, de la transaccion.
	 * @param nombre, asociado a la transaccion.
	 * @param apellido, asociado a la transaccion.
	 * @param dni, asociado a la transaccion.
	 */
	
	public Transaccion(String tipo, float monto, String nombre, String apellido, String dni) {
		fecha = new Date();
		this.tipo = tipo;
		this.monto = monto;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
	}
	/**
	 * Devuelve toda la informacion de la transaccion.
	 * @return String, que contiene toda la informacion de la transaccion.
	 */
	
	public String toString() {
		return fecha.toString() + " | " + tipo + " de " + monto + " | " + nombre + " " + apellido + " " + dni;
	}
	
	/**
	 * Devuelve el tipo de la transaccion. (Debito o credito).
	 * @return String, que representa el tipo de transacción.
	 */
	
	public String getTipo() {
		return tipo;
	}
	
	/**
	 * Devuelve el nombre asociado a la transaccion.
	 * @return String, que representa el nombre.
	 */
	
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Devuelve el apellido asociado a la transaccion.
	 * @return String, que representa el apellido.
	 */
	
	public String getApellidoBen() {
		return apellido;
	}
	
	/**
	 * Devuelve el dni asociado a la transaccion.
	 * @return String, que representa el DNI.
	 */
	
	public String getDNI() {
		return dni;
	}
	
	/**
	 * Devuelve el monto de la transaccion.
	 * @return float, que representa el monto.
	 */
	
	public float getMonto() {
		return monto;
	}
	
	/**
	 * Devuelve la fecha en la que se realizo la transaccion.
	 * @return Date, representa una fecha.
	 */
	
	public Date getFecha() {
		return fecha;
	}
}