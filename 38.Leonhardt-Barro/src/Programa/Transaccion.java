package Programa;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.time.Instant;
public class Transaccion {
	protected String tipo;
	protected String nombre;
	protected String apellido;
	protected int dni;
    protected int monto;
    protected Date fecha;
    protected int hora;
	
    public Transaccion(String tipo,int dni,int monto,int mes,int dia,int hora){
		this.tipo=tipo;
		this.dni=dni;
		this.monto=monto;
		this.hora=hora;
		fecha = new Date(2022,mes,dia);
	}
    public Transaccion(String tipo,String nombre,String apellido,int monto,int mes,int dia,int hora){
		this.tipo=tipo;
		this.nombre=nombre;
		this.apellido=apellido;
		this.monto=monto;
		this.hora=hora;
		fecha = new Date(2022,mes,dia);
	}
   
	public String toString() {
		if(tipo.charAt(0)=='D') {
			return "Destinatario:"+dni+" "+tipo+" "+ monto+" "+transformarfecha()+" "+hora+"H";
		}else return nombre+" "+apellido+" "+tipo+" "+ monto+" "+transformarfecha()+" "+hora+"H";
	}
	private String transformarfecha() {
		return fecha.getYear() + "/" +fecha.getMonth()+ "/" + fecha.getDate();
     }
	
	
	public String getTipo() {
		return tipo;
	}
	public String getNombre() {
		return nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public int getDni() {
		return dni;
	}
	public int getMonto() {
		return monto;
	}
	public Date getFecha() {
		return fecha;
	}
	public int getHora() {
		return hora;
	}
}

