package Programa;
import TDALista.PositionList;

import java.util.Iterator;

import javax.swing.JOptionPane;

import TDALista.ListaDoblementeEnlazada;

public class CuentaBancaria {
	private String nombre;
	private String apellido;
	private int DNI;
	private float saldo;
	private PositionList<Transaccion> listaTransaccion;
	
	public CuentaBancaria(String paraNombre, String paraApellido, int paraDNI, float paraSaldo) {
		nombre = paraNombre;
		apellido = paraApellido;
		DNI = paraDNI;
		saldo = paraSaldo;
		listaTransaccion = new ListaDoblementeEnlazada<Transaccion>();
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public int getDNI() {
		return DNI;
	}
	
	public float getSaldo() {
		return saldo;
	}
	
	public void setNombre(String parametro) {
		nombre = parametro;
	}
	
	public void setApellido(String parametro) {
		apellido = parametro;
	}
	
	public void setDNI(int parametro) {
		DNI = parametro;
	}

	public void setSaldo(float parametro) {
		saldo = parametro;
	}
	
	public PositionList<Transaccion> getTransacciones(){
		return listaTransaccion;
	}
	
	public void agregarTransaccion(Transaccion transParaAgregar) {
		listaTransaccion.addFirst(transParaAgregar);
	}
}
