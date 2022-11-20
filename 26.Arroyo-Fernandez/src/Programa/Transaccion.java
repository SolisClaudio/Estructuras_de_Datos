package Programa;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author Arroyo Tomas (126078) - Fernandez Maximo (131672)
 */
public class Transaccion {
	
	protected char tipo;
	protected float monto;
	protected String fecha;
	protected String hora;
	protected CuentaBancaria emisor;
	protected CuentaBancaria receptor;
	
	//CONSTRUCTOR
	/**
	 * inicializa la transaccion con tipo t, monto m, emisor e, receptor r, fecha y hora actual
	 * @param t tipo de la transacicon
	 * @param m monto de la transacicon
	 * @param e emisor de la transaccion
	 * @param r receptor de la transaccion
	 */
	public Transaccion(char t,float m,CuentaBancaria e,CuentaBancaria r) {
		tipo = t;
		monto = m;
		emisor = e;
		receptor = r;
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
		Date f = new Date();
		
		fecha = formatoFecha.format(f);
		hora = formatoHora.format(f);
		
	}
	
	
	/**
	 * Devuelve el dia en que se realizo la transaccion
	 * @return dia
	 */
	public int getDia() {
		int toReturn = (Character.getNumericValue(fecha.charAt(0)) * 10 + Character.getNumericValue(fecha.charAt(1)) );
		return toReturn;
	}
	
	/**
	 * Devuelve el mes en que se realizo la transaccion
	 * @return mes en que se realizo la transaccion
	 */
	public int getMes() {
		int toReturn = (Character.getNumericValue(fecha.charAt(3)) * 10 + Character.getNumericValue(fecha.charAt(4)) );
		return toReturn;
	}
	
	/**
	 * Devuelve el anio en que se realizo la transaccion
	 * @return anio en que se realizo la transaccion
	 */
	public int getAnio() {
		int toReturn = (Character.getNumericValue(fecha.charAt(6)) * 1000 + Character.getNumericValue(fecha.charAt(7)) * 100 + Character.getNumericValue(fecha.charAt(8)) *10 + Character.getNumericValue(fecha.charAt(9)) );
		return toReturn;
	}
	
	/**
	 * Consulta el tipo de la transacicon
	 * @return tipo de la transaccion
	 */
	public char getTipo() {
		return tipo;
	}
	
	/**
	 * Consulta monto de la transaccion
	 * @return monto de la transaccion
	 */
	public float getMonto() {
		return monto;
	}
	
	/**
	 * Consulta fecha de la transaccion
	 * @return fecha de la transaccion
	 */
	public String getFecha() {
		return fecha;
	}
	
	/**
	 * Consulta hora de la transaccion
	 * @return hora de la transacicon
	 */
	public String getHora() {
		return hora;
	}
	
	/**
	 * Consulta la cuenta emisora
	 * @return cuenta emisora
	 */
	public CuentaBancaria getEmisor() {
		return emisor;
	}
	
	/**
	 * Consulta la cuenta receptora
	 * @return cuenta receptora
	 */
	public CuentaBancaria getReceptor() {
		return receptor;
	}
	
}
