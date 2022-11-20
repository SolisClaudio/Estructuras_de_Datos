package ProyectoInterfaz;

import java.text.SimpleDateFormat;
import java.util.Calendar;
/**
 * 
 * @author Leon Baguhan Sanchez Yuyprayseree y Simon Paillan.
 *
 */
public class Transaccion {

	private String destinatario;
	private String remitente;
	private int monto;
	private String fecha;
	/**
	 * Crea una transaccion con un destinatario , remitente y monto.
	 * @param dest Cadena de caracteres con el destinatario de la transaccion.
	 * @param remi Cadena de caracteres con el remitente de la transaccion.
	 * @param mon  Monto monetario de la transaccion.
	 */
	public Transaccion(String dest, String remi, int mon) {
		destinatario = dest;
		remitente = remi;
		monto = mon;
		fecha = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
	}
	/**
	 * Consulta por la cadena del destinatario de la transaccion.
	 * @return Una cadena con el destinatario de la transaccion.
	 */
	public String getDestinatario() {
		return destinatario;
	}
	/**
	 * Consulta por la cadena del remitented e la transaccion.
	 * @return Una cadena con el remitente de la transaccion.
	 */
	public String getRemitente() {
		return remitente;
	}
	/**
	 * Consulta por el monto de la transaccion.
	 * @return El monto de la transaccion.
	 */
	public int getMonto() {
		return monto;
	}
	/**
	 * Consulta por la fecha de la transaccion.
	 * @return La fecha de la transaccion.
	 */
	public String getFecha() {
		return fecha;
	}
	/**
	 * Consulta el estado interno de la transaccion.
	 */
	public String toString() {
		return "destinatario: "+destinatario+" remitente: "+remitente + " monto: "+monto + " fecha: "+fecha;
		
	}
}
