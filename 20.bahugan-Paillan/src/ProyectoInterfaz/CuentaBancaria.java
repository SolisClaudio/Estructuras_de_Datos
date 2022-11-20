package ProyectoInterfaz;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;

import Exception.*;
import TDACola.*;
import TDAColaCP.*;
import TDALista.*;
import TDAPila.*;
import TDADiccionario.*;
/**
 * 
 * @author Leon Baguhan Sanchez Yuyprayseree y Simon Paillan.
 *
 */
public class CuentaBancaria {
	
	private String Nombre;
	private String Apellido;
	private String dni;
	private int CantidadDinero;
	private PositionLista<Transaccion> RegistroTransacciones = new Lista<Transaccion>();
	
	/**
	 * Crea una cuenta bancaria ingresando un nombre, apellido, DNI y una cantidad de dinero. 
	 * @param nombre Nombre del usuario.
	 * @param apellido Apellido del usuario.
	 * @param d	DNI del usuario.
	 * @param plata Cantidad de saldo en la cuenta.
	 */
	public CuentaBancaria(String nombre, String apellido, String d, int plata) {
		Nombre = nombre;
		Apellido = apellido;
		dni = d;
		CantidadDinero = plata;
	}
	/**
	 * Crea una cuenta bancaria ingresando un nombre, apellido y un DNI.
	 * @param nombre Nombre del usuario.
	 * @param apellido Apellido del usuario.
	 * @param d	DNI del usuario.
	 */
	public CuentaBancaria(String nombre, String apellido, String d) {
		Nombre = nombre;
		Apellido = apellido;
		dni = d;
	}
	/**
	 * Ingresa una cantidad de saldo a la cuenta.
	 * @param plata Cantidad de dinero a ingresar.
	 * @param nom Cuenta de origen.
	 */
	public void ingresarDinero(int plata, String nom){
		CantidadDinero = CantidadDinero + Math.abs(plata);
		Transaccion nueva = new Transaccion(Nombre+" "+Apellido,nom,Math.abs(plata));
		RegistroTransacciones.addFirst(nueva);
	}
	/**
	 * Ingresa un monto a retirar o transferir.
	 * @param plata	Cantidad de dinero a retirar.
	 * @param DNI	Destinatario del dinero.
	 */
	public void retirarDinero(int plata,String DNI) {
		if (Math.abs(plata) < CantidadDinero) {
			CantidadDinero = CantidadDinero - Math.abs(plata);
			Transaccion nueva = new Transaccion(DNI,Nombre+" "+Apellido,-Math.abs(plata));
			RegistroTransacciones.addFirst(nueva);
		}
	}
	/**
	 * Consulta el saldo de la cuenta.
	 * @return Cantidad de saldo de la cuenta.
	 */
	public int consultarSaldo() {
		return CantidadDinero;
	}
	/**
	 * Consulta el nombre y apellido del propietario de la cuenta.
	 * @return	Nombre y apellido del propietario.
	 */
	public String nombreCompleto() {
		return Nombre + " " + Apellido;
	}
	/**
	 * Consulta el dinero.
	 * @return Cantida de dinero.
	 */
	public int dinero() {
		return CantidadDinero;
	}
	/**
	 * Consulta el apellido del propietario de la cuenta.
	 * @return Apellido del propietario de la cuenta.
	 */
	public String apellido() {
		return Apellido;
	}
	/**
	 * Verifica que la contraseña ingresada sea correcta.
	 * @param Contra Contraseña recibida por parametro.
	 * @return Verdadero si la contraseña es correcta y negativo en caso contrario.
	 */
	public boolean verificarContra(String Contra) {
		Queue<Character> auxcola = new Cola<Character>();			
		Stack<Character> auxpila = new Pila<Character>();
		boolean cumple = true;
		
		try {
			if (Contra.length() != (Apellido.length()*3)+1)		//verifico que la contraseña tenga el tamaño	correcto
				return false;
			for (int i = 0; i<Apellido.length() && cumple;i++) {		//verifico que el incio de la contraseña coincida con el nombre
				if (Apellido.charAt(i) == Contra.charAt(i))		
					auxpila.push(Contra.charAt(i));				//si ambos coinciden almaceno la letra en una pila para invertir el nombre
				else
					cumple=false;									//en caso de no ser iguales se detiene el bucle 
			}
			
			int cursor=Apellido.length()+1;							
			
			if(Contra.charAt(cursor-1) != 'x')					//verifico la ubicacion del caracter x
				cumple = false;
			
			while (!auxpila.isEmpty() && cumple) {					//mientras la pila no este vacia
				if (Contra.charAt(cursor)==auxpila.top()){		//verifico que la contraseña y la pila sean iguales
					auxcola.enqueue(auxpila.pop());					//guardo los caracteres en una cola
					cursor++;
				}
				else
					cumple = false;
			}
			
			while (!auxcola.isEmpty() && cumple) {					//mientras la cola no este vacia
				if (Contra.charAt(cursor)==auxcola.front()){	//verifico que la contraseña y la cola sean iguales
					auxcola.dequeue();
					cursor++;
				}
				else
					cumple = false;
			}
		}
		catch(EmptyStackException | EmptyQueueException e) {
			e.printStackTrace();
		}
		
	return cumple;
	}
	/**
	 * Retorna una coleccion iterable de las fechas de transaccion de la cuenta.
	 * @param n	si
	 * @return Una coleccion de iterable de transacciones.
	 */
	public Iterable<Position<Transaccion>> Registro(int n) {
		Lista<Transaccion> nueva = new Lista<Transaccion>();		
		Iterator<Transaccion> it = RegistroTransacciones.iterator();
		int cont=1;
		
		while(it.hasNext() && cont<=n) {	//con un iterador y un contador recorro la lista de transacciones, se detiene si recorri toda la lista o llegue al numero indicado de operaciones;
			nueva.addLast(it.next());									
			cont++;
		}
		
		return nueva.positions();
	}
	/**
	 * Retorna una lista con una cantidad especifica las mayores transacciones.
	 * @param N Cantidad de transacciones a mostrar.
	 * @return Lista con las mayores transacciones.
	 */
	public Lista<Transaccion> MayoresTransacciones(int N){
		ColaConPrioridad<Integer,Transaccion> cola = new ColaConPrioridad<Integer,Transaccion>();
		Lista<Transaccion> lista = new Lista<Transaccion>();
		
		try {
			for(Transaccion m : RegistroTransacciones) {
				cola.insert(Math.abs(m.getMonto()), m);
			}
			int cont = 0;
			while(!cola.isEmpty() && cont<N) {
				lista.addLast(cola.removeMin().getValue());
				cont++;
			}
		}
		catch (InvalidKeyException | EmptyPriorityQueueException e) {
			
		}
		
		return lista;
	}
	/**
	 * Retorna un diccionario con una monto especifico.
	 * @param N Monto pasado por parametro.
	 * @return Diccionario con transacciones con un monto especifico.
	 */
	public Dictionary<Integer,Transaccion> TransaccionesMontoN(int N) {
		Dictionary<Integer,Transaccion> nuevo = new DiccionarioHashAbierto<Integer,Transaccion>();
		
		try {
			for(Transaccion n : RegistroTransacciones) {			//recorro toda la lista de transacciones
				if(Math.abs(n.getMonto()) == N) {					//si el monto es igual al solicitado
						nuevo.insert(Math.abs(n.getMonto()),n);		//lo inserto en un diccionario con Key de monto de la transaccion y Value la transaccion en si 
				}
			}
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		
		return nuevo;
	}
	/**
	 * Retorna un iterable con todas la transacciones de debito mayores a un monto n.
	 * @param n Monto a superar que es pasada por parametro.
	 * @return Una coleccion iterable de las transaccionesd e debito mayores a n.
	 */
	public Iterable<Position<Transaccion>> CreditosMayores(int n) {		
		Lista<Transaccion> nueva = new Lista<Transaccion>();
		Iterator<Transaccion> it = RegistroTransacciones.iterator();
		
		while(it.hasNext()) {
			Transaccion actual = it.next();
			if (actual.getMonto() > n )			//se recorre la lista en busca de montos mayores a n
				nueva.addLast(actual);			//(es de importante decir que en este diseño los creditos se representan en Integers positivos) 
		}
		
		return nueva.positions();
	}
	/**
	 * Retorna un iterable con todas la transacciones mayores a un monto n.
	 * @param n Monto a superar que es pasada por parametro.
	 * @return Una coleccion iterable de las transacciones mayores a n.
	 */
	public Iterable<Position<Transaccion>> DebitosMayores(int n){
		Lista<Transaccion> nueva = new Lista<Transaccion>();
		Iterator<Transaccion> it = RegistroTransacciones.iterator();
		
		while(it.hasNext()) {
			Transaccion actual = it.next();
			if (-actual.getMonto() > n )			//se recorre la lista en busca de montos opuestos mayores a n
				nueva.addLast(actual);				//(es de importante decir que en este diseño los debitos se representan en Integers negativos) 
		}
		
		return nueva.positions();
	}
	/**
	 * Retorna un iterable con las transacciones de debito-credito o ambas que superen un determinado monto n.
	 * @param n Numero que es pasado por parametro.
	 * @return Un iterable con las transacciones que sean mayor a n.
	 */
	public Iterable<Position<Transaccion>> TodosMayores(int n){
		Lista<Transaccion> nueva = new Lista<Transaccion>();
		Iterator<Transaccion> it = RegistroTransacciones.iterator();	
		
		while(it.hasNext()) {						//se recorre la lista en busca de montos cuyo valores absolutos mayores a n
			Transaccion actual = it.next();
			if (Math.abs(actual.getMonto())>n)
				nueva.addLast(actual);
			}
		
		return nueva.positions();
	}
	/**
	 * Retorna un iterable con las transacciones en una fecha determianda.
	 * @param f Fecha pasada por parametro, para ver las transacciones.
	 * @return Un iterable con las transacciones realizadas en esa fecha.
	 */
	public Iterable<Position<Transaccion>> TransaccionesEnFecha(String f){ //verifica toda la lista, puede ser mas eficiente
		Lista<Transaccion> nueva = new Lista<Transaccion>();
		try {
			Iterator<Transaccion> it = RegistroTransacciones.iterator();
			boolean termine = false;
			
			while(it.hasNext() && !termine) {							//busca toda la lista en busca de fechas iguales a las pasadas por parametro
				Transaccion actual = it.next();
				
				DateFormat df = new SimpleDateFormat("yyyy/MM/dd");		//convertimos la fecha pasada por parametro para utilizarla
				Calendar cal1  = Calendar.getInstance();
				cal1.setTime(df.parse(f));	
				
				Calendar cal2  = Calendar.getInstance();				//convertimos la fecha de la transaccion actual
				cal2.setTime(df.parse(actual.getFecha()));
				
				if (cal2.equals(cal1)) {								//verificamos que sean iguales
					nueva.addLast(actual);
				}
				
				if(cal2.after(cal1))									//si la fecha del parametro esta despues de la fecha de la transaccion ya no habra transacciones que revisar
					termine=true;
				
			}
		} catch (ParseException e) {
			
		}
		
		return nueva.positions();
	}
	/**
	 * Calcula el monto en una determinada fecha.
	 * @param n Fecha pasada por parametro que se utiliza para el calculo.
	 * @return 	El monto gastado en una fecha determinada.
	 */
	public int CalcularMontoFecha(String n) {
		int aux = CantidadDinero;											//aux sera igual a la cantidad de dinero actualmente
		
			try {
				Position<Transaccion> pos = RegistroTransacciones.last();
				boolean llegue = false;
				
				while(pos != RegistroTransacciones.first() && !llegue) {	//mientras no llegue a la fecha y no estoy en el primer elemento
					if(ComprobarFecha(pos.element().getFecha(),n)) {		//compruebo que no me pase de la fecha
						aux = aux - pos.element().getMonto();				//resto el valor de la trasaccion 
						pos = RegistroTransacciones.prev(pos);				//avanzo en la lista
					}
					else
						llegue=true;
					}
				
				if(ComprobarFecha(pos.element().getFecha(),n)) 				//si llegue al final de la lista y aun no me pase de la lista entro en un ultimo if
					aux = aux - pos.element().getMonto();
				
			} catch (EmptyListException  | InvalidPositionException
					| BoundaryViolationException e) {
				//e.printStackTrace();
			}
		
		return aux;
	}
	
	
	//---------------------------------------------------------
	
	/**
	 * Verifica si las fechas son validas.
	 * @param fecha1 s
	 * @param fecha2 s
	 * @return Verdadero si cumple y falso en caso contrario.
	 */
	private boolean ComprobarFecha(String fecha1, String fecha2){
		boolean cumple = false;
		
		try {
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd");		//creamos la fecha1
			Calendar cal1  = Calendar.getInstance();
			cal1.setTime(df.parse(fecha1));							
			
			DateFormat df2 = new SimpleDateFormat("yyyy/MM/dd");	//creamos la fecha2
			Calendar cal2  = Calendar.getInstance();
			cal2.setTime(df2.parse(fecha2));
			
			if (cal1.after(cal2))									//si la fecha2 esta despues de la fecha 1 retornara true
				cumple = true;
			else if(cal1.equals(cal2))								//si son iguales retornara true
				cumple = true;
				else
					cumple = false;									//si la fecha2 esta despues de la fecha 1 retornara false 
		
		} catch (ParseException | IllegalArgumentException e) {	}
		
		return cumple;
	}
	

	
}
	
