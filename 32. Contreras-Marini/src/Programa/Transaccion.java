package Programa;

import java.util.Date;

public class Transaccion {
	private CuentaBancaria emisor;
	private CuentaBancaria receptor;
	private String tipo;
	private float monto;
	private Date fechaActual;
	
	public Transaccion(CuentaBancaria paraEmisor, CuentaBancaria paraReceptor, String paraTipo, float paraMonto,Date paraFecha) {
		emisor = paraEmisor;
		receptor = paraReceptor;
		tipo = paraTipo;
		monto = paraMonto;
		fechaActual = paraFecha;
	}
	
	public CuentaBancaria getEmisor() {
		return emisor;
	}
	
	public CuentaBancaria getReceptor() {
		return receptor;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public float getMonto() {
		return monto;
	}
	
	public Date getFecha() {
		return fechaActual;
	}
	
	public String toString() {
		return emisor.getNombre() + ", " + emisor.getApellido() + " DNI:" + emisor.getDNI() + 
			   " transfirió: $" + monto + " en forma de "+ tipo + " a "
			  + receptor.getNombre() + ", "+ receptor.getApellido() + " DNI: " + receptor.getDNI() + 
			  "Fecha: " + fechaActual.toString();
		
	}
	
}
