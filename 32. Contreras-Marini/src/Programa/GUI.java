package Programa;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.awt.event.ActionEvent;

import javax.swing.text.*;

import Auxiliares.Entry;
import Exceptions.*;

import java.awt.Font;

import TDACola.ColaCircular;
import TDAColaCP.PriorityQueue;
import TDADiccionario.Dictionary;
import TDALista.PositionList;
import TDAPila.Pila;

/**
 * Clase GUI
 * @author Nahuel Contreras, Nicolas Marini.
 * Clase encargada de la interface grafica del programa. 
 */
public class GUI {

	private JFrame FrameInicioSesion, FrameBase;
	private JTextField tFNombre, tFApellido, tFMontoIni;
	private JPasswordField pFContrasenia;
	private JLabel lblNombre, lblApellido, lblClave, lblMontoInicial;
	private JButton btnIniSes;
	private Logica programaLogica;
	private CuentaBancaria cuentaActual;
	private JTable tTablaHistorial;
	private JScrollPane ContenerdorHistorial;
	private JTextField tFNombre_Credito, tFApellido_Credito, tFDNI_Credito, tFMonto_Credito;
	private JTextField tFNombre_Debito, tFApellido_Debito, tFDNI_Debito, tFMonto_Debito;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.FrameInicioSesion.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public GUI() {
		initializeInicioSesion();
	}

	private void initializeInicioSesion() {
		programaLogica = new Logica(this);
		
		FrameInicioSesion = new JFrame();
		FrameInicioSesion.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 11));
		FrameInicioSesion.setBounds(100, 100, 311, 425);
		FrameInicioSesion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FrameInicioSesion.getContentPane().setLayout(null);

		tFNombre = new JTextField();
		tFNombre.setBounds(69, 64, 151, 20);
		FrameInicioSesion.getContentPane().add(tFNombre);
		tFNombre.setColumns(10);

		tFApellido = new JTextField();
		tFApellido.setBounds(69, 124, 151, 20);
		FrameInicioSesion.getContentPane().add(tFApellido);
		tFApellido.setColumns(10);

		pFContrasenia = new JPasswordField();
		pFContrasenia.setBounds(69, 180, 151, 20);
		FrameInicioSesion.getContentPane().add(pFContrasenia);

		tFMontoIni = new JTextField();
		tFMontoIni.setColumns(10);
		tFMontoIni.setBounds(69, 242, 151, 20);
		FrameInicioSesion.getContentPane().add(tFMontoIni);
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNombre.setBounds(69, 43, 151, 14);
		FrameInicioSesion.getContentPane().add(lblNombre);

		lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblApellido.setBounds(69, 101, 151, 14);
		FrameInicioSesion.getContentPane().add(lblApellido);

		lblClave = new JLabel("Clave:");
		lblClave.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblClave.setBounds(69, 155, 151, 14);
		FrameInicioSesion.getContentPane().add(lblClave);

		lblMontoInicial = new JLabel("Monto Inicial:");
		lblMontoInicial.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMontoInicial.setBounds(69, 217, 151, 14);
		FrameInicioSesion.getContentPane().add(lblMontoInicial);

		// --------------------Botón INICIO DE SESION--------------------
		btnIniSes = new JButton("Iniciar Sesión");
		btnIniSes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnIniSes.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(programaLogica.validarDatosInicioSesion(tFNombre.getText(), tFApellido.getText(), pFContrasenia.getText(), tFMontoIni)) {
					
					cuentaActual = new CuentaBancaria(tFNombre.getText(), tFApellido.getText(), 1223334444 , Integer.parseInt(tFMontoIni.getText()));
					programaLogica.setCuenta(cuentaActual);
					initializeFrameBase(tFNombre.getText(), tFApellido.getText(), pFContrasenia.getText(),tFMontoIni.getText());
					FrameInicioSesion.setEnabled(false);
					FrameInicioSesion.setVisible(false);
					FrameBase.setVisible(true);
					FrameBase.setEnabled(true);
					
				}
			}
		});
		btnIniSes.setBounds(80, 297, 128, 37);
		FrameInicioSesion.getContentPane().add(btnIniSes);

		// ---------------------------------------------------------------------------------------------------------------------
		
		// Esta parte hace que se pueda ingresar solo lo que se debería en cada TextField
		tFNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				tFApellido.setEditable(true);
				pFContrasenia.setEditable(true);
				tFMontoIni.setEditable(true);
				if (k.getKeyCode() == 8 || k.getKeyCode() == 127  || k.getKeyCode() == 20  || k.getKeyCode() >= 65 && k.getKeyCode() <= 90) {
					tFNombre.setEditable(true);
				} else {
					tFNombre.setEditable(false);
				}
			}
		});
		tFApellido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				tFNombre.setEditable(true);
				pFContrasenia.setEditable(true);
				tFMontoIni.setEditable(true);
				if (k.getKeyCode() == 8 || k.getKeyCode() == 127  || k.getKeyCode() == 20  || k.getKeyCode() >= 65 && k.getKeyCode() <= 90) {
					tFApellido.setEditable(true);
				} else {
					tFApellido.setEditable(false);
				}
			}
		});
		pFContrasenia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				tFNombre.setEditable(true);
				tFApellido.setEditable(true);
				tFMontoIni.setEditable(true);
				if (k.getKeyCode() == 8 || k.getKeyCode() == 127  || k.getKeyCode() == 20  || k.getKeyCode() >= 65 && k.getKeyCode() <= 90) {
					pFContrasenia.setEditable(true);
				} else {
					pFContrasenia.setEditable(false);
				}
			}
		});
		tFMontoIni.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				tFNombre.setEditable(true);
				tFApellido.setEditable(true);
				pFContrasenia.setEditable(true);
				if (k.getKeyCode() == 8  || k.getKeyCode() == 127 || k.getKeyChar() >= '0' && k.getKeyChar() <= '9') {
					tFMontoIni.setEditable(true);
				} else {
					tFMontoIni.setEditable(false);
				}
			}
		});
		// ---------------------------------------------------------------------------------------------------------------------
	}

	

	private void initializeFrameBase(String parametroNombre, String parametroApellido, String parametroContrasenia,
			String parametroMonto) {

		// -------Necesario para que el saldo se tenga que imprimir como dinero-------
		double MontoDoble = new Double(parametroMonto).doubleValue();
		Locale Localidad = new Locale("en", "US");
		Currency Moneda = Currency.getInstance(Localidad);
		NumberFormat FormatoDeNumero = NumberFormat.getCurrencyInstance(Localidad);
		// JLabel lblSaldoActual = new JLabel(FormatoDeNumero.format(MontoDoble));
		// (Si se modifica la parte grafica, es necesario esta parte)
		// --------------------------------------------------------------------------

		// -----------------------Parte Grafica--------------------------------------
		FrameBase = new JFrame();
		FrameBase.setBounds(100, 100, 1163, 405);
		FrameBase.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FrameBase.getContentPane().setLayout(null);

		JLabel lblBienvenido = new JLabel("Bienvenido!");
		lblBienvenido.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBienvenido.setBounds(10, 11, 139, 20);
		FrameBase.getContentPane().add(lblBienvenido);
		
		JLabel lblContNombreApelli = new JLabel(parametroNombre + "," + parametroApellido);
		lblContNombreApelli.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblContNombreApelli.setBounds(20, 29, 329, 20);
		FrameBase.getContentPane().add(lblContNombreApelli);

		JLabel lblSaldoActual = new JLabel(FormatoDeNumero.format(MontoDoble));
		lblSaldoActual.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSaldoActual.setBounds(60, 61, 393, 20);
		FrameBase.getContentPane().add(lblSaldoActual);

		JLabel lblSaldo = new JLabel("Saldo:");
		lblSaldo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSaldo.setBounds(10, 61, 53, 20);
		FrameBase.getContentPane().add(lblSaldo);
		
		JLabel lblTransferencia = new JLabel("Transferencia - Debito");
		lblTransferencia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTransferencia.setBounds(20, 136, 164, 14);
		FrameBase.getContentPane().add(lblTransferencia);
		lblTransferencia.setVisible(true);
		
		JLabel lblIngreseNombre_Debito = new JLabel("Ingrese Nombre del beneficiario");
		lblIngreseNombre_Debito.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIngreseNombre_Debito.setBounds(31, 186, 201, 20);
		FrameBase.getContentPane().add(lblIngreseNombre_Debito);
		lblIngreseNombre_Debito.setVisible(true);
		
		JLabel lblIngreseApellido_Debito = new JLabel("Ingrese Apellido del beneficiario");
		lblIngreseApellido_Debito.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIngreseApellido_Debito.setBounds(31, 231, 201, 20);
		FrameBase.getContentPane().add(lblIngreseApellido_Debito);
		lblIngreseApellido_Debito.setVisible(true);
		
		JLabel lblIngreseDNI_Debito = new JLabel("Ingrese DNI del beneficiario");
		lblIngreseDNI_Debito.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIngreseDNI_Debito.setBounds(31, 276, 201, 20);
		FrameBase.getContentPane().add(lblIngreseDNI_Debito);
		lblIngreseDNI_Debito.setVisible(true);
		
		JLabel lblIngreseMonto_Debito = new JLabel("Ingrese Monto");
		lblIngreseMonto_Debito.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIngreseMonto_Debito.setBounds(31, 321, 201, 20);
		FrameBase.getContentPane().add(lblIngreseMonto_Debito);
		lblIngreseMonto_Debito.setVisible(true);
		
		JLabel lblIngreseNombre_Credito = new JLabel("Ingrese Nombre del que ingresa");
		lblIngreseNombre_Credito.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIngreseNombre_Credito.setBounds(31, 186, 201, 20);
		FrameBase.getContentPane().add(lblIngreseNombre_Credito);
		lblIngreseNombre_Credito.setVisible(false);
		
		JLabel lblIngreseApellido_Credito = new JLabel("Ingrese Apellido del que ingresa");
		lblIngreseApellido_Credito.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIngreseApellido_Credito.setBounds(31, 231, 201, 20);
		FrameBase.getContentPane().add(lblIngreseApellido_Credito);
		lblIngreseApellido_Credito.setVisible(false);

		JLabel lblIngreseDNI_Credito = new JLabel("Ingrese DNI del que ingresa");
		lblIngreseDNI_Credito.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIngreseDNI_Credito.setBounds(31, 276, 201, 20);
		FrameBase.getContentPane().add(lblIngreseDNI_Credito);
		lblIngreseDNI_Credito.setVisible(false);
		
		JLabel lblIngreseMonto_Credito = new JLabel("Ingrese Monto");
		lblIngreseMonto_Credito.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIngreseMonto_Credito.setBounds(31, 321, 201, 20);
		FrameBase.getContentPane().add(lblIngreseMonto_Credito);
		lblIngreseMonto_Credito.setVisible(false);

		tFNombre_Debito = new JTextField();
		tFNombre_Debito.setBounds(230, 188, 164, 20);
		FrameBase.getContentPane().add(tFNombre_Debito);
		tFNombre_Debito.setColumns(10);
		
		tFApellido_Debito = new JTextField();
		tFApellido_Debito.setColumns(10);
		tFApellido_Debito.setBounds(230, 233, 164, 20);
		FrameBase.getContentPane().add(tFApellido_Debito);
		
		tFDNI_Debito = new JTextField();
		tFDNI_Debito.setColumns(10);
		tFDNI_Debito.setBounds(230, 278, 164, 20);
		FrameBase.getContentPane().add(tFDNI_Debito);
		
		tFMonto_Debito = new JTextField();
		tFMonto_Debito.setColumns(10);
		tFMonto_Debito.setBounds(230, 323, 164, 20);
		FrameBase.getContentPane().add(tFMonto_Debito);
		
		tFNombre_Credito = new JTextField();
		tFNombre_Credito.setBounds(230, 188, 164, 20);
		FrameBase.getContentPane().add(tFNombre_Credito);
		tFNombre_Credito.setColumns(10);
		tFNombre_Credito.setVisible(false);
		
		tFApellido_Credito = new JTextField();
		tFApellido_Credito.setColumns(10);
		tFApellido_Credito.setBounds(230, 233, 164, 20);
		FrameBase.getContentPane().add(tFApellido_Credito);
		tFApellido_Credito.setVisible(false);

		tFDNI_Credito = new JTextField();
		tFDNI_Credito.setColumns(10);
		tFDNI_Credito.setBounds(230, 278, 164, 20);
		FrameBase.getContentPane().add(tFDNI_Credito);
		tFDNI_Credito.setVisible(false);
		
		tFMonto_Credito = new JTextField();
		tFMonto_Credito.setColumns(10);
		tFMonto_Credito.setBounds(230, 323, 164, 20);
		FrameBase.getContentPane().add(tFMonto_Credito);
		tFMonto_Credito.setVisible(false);
		
		JButton btnEnviarDatos = new JButton("Enviar");
		btnEnviarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tFNombre_Credito.isVisible()) 
					programaLogica.validarDatosPreTrans(tFNombre_Credito.getText() ,tFApellido_Credito.getText() ,tFDNI_Credito.getText() ,tFMonto_Credito.getText(),"Credito");
				else 
					programaLogica.validarDatosPreTrans(tFNombre_Debito.getText() ,tFApellido_Debito.getText() ,tFDNI_Debito.getText() ,tFMonto_Debito.getText(),"Debito");
				lblSaldoActual.setText( FormatoDeNumero.format( new Double( cuentaActual.getSaldo() ).doubleValue() ) );
			}
		});
		btnEnviarDatos.setBounds(422, 304, 95, 39);
		FrameBase.getContentPane().add(btnEnviarDatos);
		
		JButton btnTransferenciaDeDebito = new JButton("Transferencia de Debito");
		btnTransferenciaDeDebito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				lblTransferencia.setVisible(true);
				btnEnviarDatos.setVisible(true);
				lblTransferencia.setText("Transferencia - Debito");
				

				lblIngreseNombre_Debito.setVisible(true);
				lblIngreseApellido_Debito.setVisible(true);
				lblIngreseDNI_Debito.setVisible(true);
				lblIngreseMonto_Debito.setVisible(true);
				tFNombre_Debito.setVisible(true);			tFNombre_Debito.setEnabled(true);
				tFApellido_Debito.setVisible(true);			tFApellido_Debito.setEnabled(true);
				tFDNI_Debito.setVisible(true);				tFDNI_Debito.setEnabled(true);
				tFMonto_Debito.setVisible(true);	    	tFMonto_Debito.setEnabled(true);
				
				
				lblIngreseNombre_Credito.setVisible(false);
				lblIngreseApellido_Credito.setVisible(false);
				lblIngreseDNI_Credito.setVisible(false);
				lblIngreseMonto_Credito.setVisible(false);
				tFNombre_Credito.setVisible(false);				tFNombre_Credito.setText("");
				tFApellido_Credito.setVisible(false);			tFApellido_Credito.setText("");
				tFDNI_Credito.setVisible(false);      			tFDNI_Credito.setText("");
				tFMonto_Credito.setVisible(false);				tFMonto_Credito.setText("");
				
			}
		});
		btnTransferenciaDeDebito.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTransferenciaDeDebito.setBounds(10, 92, 201, 33);
		FrameBase.getContentPane().add(btnTransferenciaDeDebito);
		
		JButton btnTransferenciaDeCredito = new JButton("Transferencia de Credito");
		btnTransferenciaDeCredito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				lblTransferencia.setVisible(true);
				btnEnviarDatos.setVisible(true);
				lblTransferencia.setText("Transferencia - Credito");
				
				
				lblIngreseNombre_Debito.setVisible(false);
				lblIngreseApellido_Debito.setVisible(false);
				lblIngreseDNI_Debito.setVisible(false);
				lblIngreseMonto_Debito.setVisible(false);
				tFNombre_Debito.setVisible(false);         tFNombre_Debito.setText("");
				tFApellido_Debito.setVisible(false); 		tFApellido_Debito.setText("");
				tFDNI_Debito.setVisible(false);				tFDNI_Debito.setText("");
				tFMonto_Debito.setVisible(false);			tFMonto_Debito.setText("");
				
				
				lblIngreseNombre_Credito.setVisible(true); 
				lblIngreseApellido_Credito.setVisible(true);
				lblIngreseDNI_Credito.setVisible(true);
				lblIngreseMonto_Credito.setVisible(true);
				tFNombre_Credito.setVisible(true);          tFNombre_Credito.setEnabled(true);
				tFApellido_Credito.setVisible(true);	    tFApellido_Credito.setEnabled(true);
				tFMonto_Credito.setVisible(true);		    tFMonto_Credito.setEnabled(true);
				tFDNI_Credito.setVisible(true);				tFDNI_Credito.setEnabled(true);
				
			}
		});
		btnTransferenciaDeCredito.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTransferenciaDeCredito.setBounds(224, 92, 201, 33);
		FrameBase.getContentPane().add(btnTransferenciaDeCredito);
		
		//------------------------------------Historial---------------------------------
		Object[][] datos = {};
		Object[] nombreColumnas = {"DNI Emisor", "Nombre Emisor", "DNI Beneficiario","Monto","Tipo","Fecha"};
		
		ContenerdorHistorial = new JScrollPane();
		ContenerdorHistorial.setBounds(546, 161, 575, 180);
		
		FrameBase.getContentPane().add(ContenerdorHistorial);
		tTablaHistorial = new JTable(datos,nombreColumnas);
		ContenerdorHistorial.setViewportView(tTablaHistorial);
		//-------------------------------------------------------------------------------
		JLabel lblConsultar = new JLabel("Seleccione su consulta:");
		lblConsultar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblConsultar.setBounds(546, 28, 180, 23);
		FrameBase.getContentPane().add(lblConsultar);
		
		JButton btnUltimasNTrans = new JButton("Últimas n transacciones");
		btnUltimasNTrans.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFrame frameIngresarCantTrans = new JFrame();
				frameIngresarCantTrans.setBounds(100, 100, 333, 208);
				frameIngresarCantTrans.getContentPane().setLayout(null);
				
				JLabel lbl = new JLabel("Ingrese la cantidad de transacciones");
				lbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lbl.setBounds(41, 42, 229, 14);
				frameIngresarCantTrans.getContentPane().add(lbl);
				
				JTextField tFIngresoNulti = new JTextField();
				tFIngresoNulti.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tFIngresoNulti.setBounds(89, 79, 126, 31);
				frameIngresarCantTrans.getContentPane().add(tFIngresoNulti);
				tFIngresoNulti.setColumns(10);
				
				JButton btnEnviarNtransacciones = new JButton("Enviar");
				btnEnviarNtransacciones.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						programaLogica.consultaUltimasN(tFIngresoNulti.getText());
					}
				});
				
				btnEnviarNtransacciones.setBounds(112, 121, 89, 23);
				frameIngresarCantTrans.getContentPane().add(btnEnviarNtransacciones);
				
				tFIngresoNulti.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent k) {
						if (k.getKeyCode() == 8 || k.getKeyCode() == 127  || k.getKeyChar() >= '0' && k.getKeyChar() <= '9') {
							tFIngresoNulti.setEditable(true);
						} else {
							tFIngresoNulti.setEditable(false);
						}
					}
				});
				frameIngresarCantTrans.setVisible(true);
				
			}
		});
		btnUltimasNTrans.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUltimasNTrans.setBounds(556, 61, 262, 23);
		FrameBase.getContentPane().add(btnUltimasNTrans);
		
		JButton btnUltimasNTransOrde = new JButton("Últimas n transacciones ordenadas");
		btnUltimasNTransOrde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frameUltimasOrdenadas = new JFrame();
				frameUltimasOrdenadas.setBounds(100, 100, 333, 208);
				frameUltimasOrdenadas.getContentPane().setLayout(null);
				
				JLabel lblIngreseValorOrdenado = new JLabel("Ingrese el valor a consultar");
				lblIngreseValorOrdenado.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblIngreseValorOrdenado.setBounds(68, 31, 168, 23);
				frameUltimasOrdenadas.getContentPane().add(lblIngreseValorOrdenado);
				
				JLabel lblParaMostrarDe = new JLabel("para mostrar de forma ordenada");
				lblParaMostrarDe.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblParaMostrarDe.setBounds(49, 54, 211, 23);
				frameUltimasOrdenadas.getContentPane().add(lblParaMostrarDe);
				
				JTextField tFIngresoValorOrden = new JTextField();
				tFIngresoValorOrden.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tFIngresoValorOrden.setBounds(89, 79, 126, 31);
				frameUltimasOrdenadas.getContentPane().add(tFIngresoValorOrden);
				tFIngresoValorOrden.setColumns(10);
				
				JButton btnBotonEnviarOrden = new JButton("Enviar");
				btnBotonEnviarOrden.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						programaLogica.consultaUltimasOrdenadas(tFIngresoValorOrden.getText());
					}
				});
				btnBotonEnviarOrden.setBounds(110, 121, 89, 23);
				frameUltimasOrdenadas.getContentPane().add(btnBotonEnviarOrden);
				
				tFIngresoValorOrden.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent k) {
						if (k.getKeyCode() == 8  || k.getKeyCode() == 127 || k.getKeyChar() >= '0' && k.getKeyChar() <= '9') {
							tFIngresoValorOrden.setEditable(true);
						} else {
							tFIngresoValorOrden.setEditable(false);
						}
					}
				});
				
				frameUltimasOrdenadas.setVisible(true);
			}
			
		});
		btnUltimasNTransOrde.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUltimasNTransOrde.setBounds(556, 95, 262, 23);
		FrameBase.getContentPane().add(btnUltimasNTransOrde);
		
		JButton btnTransMismoValor = new JButton("Transacciones de un mismo valor");
		btnTransMismoValor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFrame frIngresarVAComparar = new JFrame();
				frIngresarVAComparar.setBounds(100, 100, 333, 208);
				frIngresarVAComparar.getContentPane().setLayout(null);
				
				JLabel lbl = new JLabel("Ingrese el valor a consultar");
				lbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lbl.setBounds(68, 40, 168, 14);
				frIngresarVAComparar.getContentPane().add(lbl);
				
				JTextField tFIngresoValorConsultar = new JTextField();
				tFIngresoValorConsultar.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tFIngresoValorConsultar.setBounds(89, 79, 126, 31);
				frIngresarVAComparar.getContentPane().add(tFIngresoValorConsultar);
				tFIngresoValorConsultar.setColumns(10);
				
				JButton btnEnviarValorConsulta = new JButton("Enviar");
				btnEnviarValorConsulta.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						programaLogica.consultaMismoVTransacciones(tFIngresoValorConsultar.getText());
					}
				});
				
				btnEnviarValorConsulta.setBounds(110, 121, 89, 23);
				frIngresarVAComparar.getContentPane().add(btnEnviarValorConsulta);
				
				tFIngresoValorConsultar.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent k) {
						if (k.getKeyCode() == 8 || k.getKeyCode() == 127  || k.getKeyChar() >= '0' && k.getKeyChar() <= '9') {
							tFIngresoValorConsultar.setEditable(true);
						} else {
							tFIngresoValorConsultar.setEditable(false);
						}
					}
				});
				
				frIngresarVAComparar.setVisible(true);
			}
		});
		btnTransMismoValor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTransMismoValor.setBounds(556, 127, 262, 23);
		FrameBase.getContentPane().add(btnTransMismoValor);
		
		JButton btnTransMismaFecha = new JButton("Transacciones de una fecha");
		btnTransMismaFecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame panelFecha = new JFrame();
				panelFecha.setBounds(100, 100, 341, 264);
				panelFecha.getContentPane().setLayout(null);
				
				JLabel lblTextoIngrFecha = new JLabel("Ingrese Fecha");
				lblTextoIngrFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblTextoIngrFecha.setBounds(109, 30, 96, 23);
				panelFecha.getContentPane().add(lblTextoIngrFecha);
				
				JTextField tFDia = new JTextField();
				tFDia.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tFDia.setBounds(161, 74, 44, 20);
				panelFecha.getContentPane().add(tFDia);
				tFDia.setColumns(10);
				
				JTextField tFMes = new JTextField();
				tFMes.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tFMes.setColumns(10);
				tFMes.setBounds(161, 105, 44, 20);
				panelFecha.getContentPane().add(tFMes);
				
				JTextField tFAnio = new JTextField();
				tFAnio.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tFAnio.setColumns(10);
				tFAnio.setBounds(161, 136, 44, 20);
				panelFecha.getContentPane().add(tFAnio);
				
				JLabel lblInrgDia = new JLabel("Dia:");
				lblInrgDia.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblInrgDia.setBounds(105, 74, 46, 14);
				panelFecha.getContentPane().add(lblInrgDia);
				
				JLabel lblInrgMes = new JLabel("Mes:");
				lblInrgMes.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblInrgMes.setBounds(105, 105, 46, 14);
				panelFecha.getContentPane().add(lblInrgMes);
				
				JLabel lblInrgAnio = new JLabel("Anio");
				lblInrgAnio.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblInrgAnio.setBounds(105, 136, 46, 14);
				panelFecha.getContentPane().add(lblInrgAnio);
				
				JButton btnEnvioFecha = new JButton("Enviar");
				btnEnvioFecha.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						programaLogica.consultaMismaFechaTransacciones(tFDia.getText(),tFMes.getText(), tFAnio.getText());
					}
				});
				btnEnvioFecha.setBounds(109, 177, 89, 23);
				panelFecha.getContentPane().add(btnEnvioFecha);
				
				tFDia.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent k) {
						tFMes.setEditable(true);
						tFAnio.setEditable(true);
						if (k.getKeyCode() == 8 || k.getKeyCode() == 127  || k.getKeyChar() >= '0' && k.getKeyChar() <= '9') {
							tFDia.setEditable(true);
						} else {
							tFDia.setEditable(false);
						}
					}
				});
				tFMes.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent k) {
						tFDia.setEditable(true);
						tFAnio.setEditable(true);
						if (k.getKeyCode() == 8 || k.getKeyCode() == 127  || k.getKeyChar() >= '0' && k.getKeyChar() <= '9') {
							tFMes.setEditable(true);
						} else {
							tFMes.setEditable(false);
						}
					}
				});
				tFAnio.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent k) {
						tFDia.setEditable(true);
						tFMes.setEditable(true);
						if (k.getKeyCode() == 8 || k.getKeyCode() == 127  || k.getKeyChar() >= '0' && k.getKeyChar() <= '9') {
							tFAnio.setEditable(true);
						} else {
							tFAnio.setEditable(false);
						}
					}
				});
				
				panelFecha.setVisible(true);
			}
		});
		btnTransMismaFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTransMismaFecha.setBounds(843, 60, 262, 23);
		FrameBase.getContentPane().add(btnTransMismaFecha);
		
		JButton btnTransValorSuperior = new JButton("Transacciones con valor superior a ");
		btnTransValorSuperior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFrame panelSuperarValor = new JFrame();
				panelSuperarValor.setBounds(100, 100, 319, 215);
				panelSuperarValor.getContentPane().setLayout(null);
				
				JLabel lblTextoConsulta = new JLabel("Ingrese valor: ");
				lblTextoConsulta.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblTextoConsulta.setBounds(20, 33, 108, 17);
				panelSuperarValor.getContentPane().add(lblTextoConsulta);
				
				JTextField tFValorASuperar = new JTextField();
				tFValorASuperar.setBounds(148, 33, 86, 20);
				panelSuperarValor.getContentPane().add(tFValorASuperar);
				tFValorASuperar.setColumns(10);
				
				JRadioButton rdbtnDebito = new JRadioButton("Debito");
				rdbtnDebito.setFont(new Font("Tahoma", Font.PLAIN, 14));
				rdbtnDebito.setBounds(116, 107, 78, 23);
				panelSuperarValor.getContentPane().add(rdbtnDebito);
				
				JRadioButton rdbtnCredito = new JRadioButton("Credito");
				rdbtnCredito.setFont(new Font("Tahoma", Font.PLAIN, 14));
				rdbtnCredito.setBounds(193, 107, 79, 23);
				panelSuperarValor.getContentPane().add(rdbtnCredito);
				
				JRadioButton rdbtnAmbas = new JRadioButton("Ambas");
				rdbtnAmbas.setSelected(true);
				rdbtnAmbas.setFont(new Font("Tahoma", Font.PLAIN, 14));
				rdbtnAmbas.setBounds(35, 107, 79, 23);
				panelSuperarValor.getContentPane().add(rdbtnAmbas);
				
				JLabel lblIngreseTipoDe = new JLabel("Ingrese tipo de transferencia a consultar: ");
				lblIngreseTipoDe.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblIngreseTipoDe.setBounds(20, 83, 269, 17);
				panelSuperarValor.getContentPane().add(lblIngreseTipoDe);
				
				JButton btnEnviarSuperarValor = new JButton("Enviar");
				btnEnviarSuperarValor.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(rdbtnAmbas.isSelected()){
							programaLogica.consultaSuperarVTransacciones(tFValorASuperar.getText(),"ambas");
						}else if(rdbtnCredito.isSelected())
							programaLogica.consultaSuperarVTransacciones(tFValorASuperar.getText(), "Credito");
						else
							programaLogica.consultaSuperarVTransacciones(tFValorASuperar.getText(), "Debito");
					}
				});
				btnEnviarSuperarValor.setFont(new Font("Tahoma", Font.PLAIN, 14));
				btnEnviarSuperarValor.setBounds(98, 142, 89, 23);
				panelSuperarValor.getContentPane().add(btnEnviarSuperarValor);
				
				tFValorASuperar.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent k) {
						if (k.getKeyCode() == 8 || k.getKeyCode() == 127  || k.getKeyChar() >= '0' && k.getKeyChar() <= '9') {
							tFValorASuperar.setEditable(true);
						} else {
							tFValorASuperar.setEditable(false);
						}
					}
				});
				ButtonGroup grupoBotones = new ButtonGroup();
				grupoBotones.add(rdbtnAmbas);
				grupoBotones.add(rdbtnCredito);
				grupoBotones.add(rdbtnDebito);
				
				panelSuperarValor.setVisible(true);
			}
		});
		btnTransValorSuperior.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTransValorSuperior.setBounds(843, 95, 262, 23);
		FrameBase.getContentPane().add(btnTransValorSuperior);
		
		JButton btnSaldoFecha = new JButton("Saldo en cierta fecha");
		btnSaldoFecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame panelFecha = new JFrame();
				panelFecha.setBounds(100, 100, 341, 264);
				panelFecha.getContentPane().setLayout(null);
				
				JLabel lblTextoIngrFecha = new JLabel("Ingrese Fecha");
				lblTextoIngrFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblTextoIngrFecha.setBounds(105, 11, 96, 23);
				panelFecha.getContentPane().add(lblTextoIngrFecha);
				
				JLabel lblParaConocerSu = new JLabel("para conocer su saldo esa fecha");
				lblParaConocerSu.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblParaConocerSu.setBounds(53, 31, 219, 23);
				panelFecha.getContentPane().add(lblParaConocerSu);
				
				JTextField tFDia = new JTextField();
				tFDia.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tFDia.setBounds(161, 74, 44, 20);
				panelFecha.getContentPane().add(tFDia);
				tFDia.setColumns(10);
				
				JTextField tFMes = new JTextField();
				tFMes.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tFMes.setColumns(10);
				tFMes.setBounds(161, 105, 44, 20);
				panelFecha.getContentPane().add(tFMes);
				
				JTextField tFAnio = new JTextField();
				tFAnio.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tFAnio.setColumns(10);
				tFAnio.setBounds(161, 136, 44, 20);
				panelFecha.getContentPane().add(tFAnio);
				
				JLabel lblInrgDia = new JLabel("Dia:");
				lblInrgDia.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblInrgDia.setBounds(105, 74, 46, 14);
				panelFecha.getContentPane().add(lblInrgDia);
				
				JLabel lblInrgMes = new JLabel("Mes:");
				lblInrgMes.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblInrgMes.setBounds(105, 105, 46, 14);
				panelFecha.getContentPane().add(lblInrgMes);
				
				JLabel lblInrgAnio = new JLabel("Anio");
				lblInrgAnio.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblInrgAnio.setBounds(105, 136, 46, 14);
				panelFecha.getContentPane().add(lblInrgAnio);
				
				JButton btnEnvioFecha = new JButton("Enviar");
				btnEnvioFecha.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						programaLogica.consultarMontoEnFecha(tFDia.getText(),tFMes.getText(),tFAnio.getText());
					}
				});
				btnEnvioFecha.setBounds(109, 177, 89, 23);
				panelFecha.getContentPane().add(btnEnvioFecha);
				
				
				
				tFDia.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent k) {
						tFMes.setEditable(true);
						tFAnio.setEditable(true);
						if (k.getKeyCode() == 8 || k.getKeyCode() == 127 || k.getKeyChar() >= '0' && k.getKeyChar() <= '9') {
							tFDia.setEditable(true);
						} else {
							tFDia.setEditable(false);
						}
					}
				});
				tFMes.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent k) {
						tFDia.setEditable(true);
						tFAnio.setEditable(true);
						if (k.getKeyCode() == 8 || k.getKeyCode() == 127 || k.getKeyChar() >= '0' && k.getKeyChar() <= '9') {
							tFMes.setEditable(true);
						} else {
							tFMes.setEditable(false);
						}
					}
				});
				tFAnio.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent k) {
						tFDia.setEditable(true);
						tFMes.setEditable(true);
						if (k.getKeyCode() == 8 || k.getKeyCode() == 127 || k.getKeyChar() >= '0' && k.getKeyChar() <= '9') {
							tFAnio.setEditable(true);
						} else {
							tFAnio.setEditable(false);
						}
					}
				});
			
				panelFecha.setVisible(true);
			}
		});
		btnSaldoFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSaldoFecha.setBounds(843, 129, 262, 23);
		FrameBase.getContentPane().add(btnSaldoFecha);
		
		
		//Esta parte es para que los ingresos sean los correspondientes
		tFNombre_Debito.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				tFApellido_Debito.setEditable(true);
				tFDNI_Debito.setEditable(true);
				tFMonto_Debito.setEditable(true);
				if (k.getKeyCode() == 8 || k.getKeyCode() == 127  || k.getKeyCode() == 20  || k.getKeyCode() >= 65 && k.getKeyCode() <= 90) {
					tFNombre_Debito.setEditable(true);
				} else {
					tFNombre_Debito.setEditable(false);
				}
			}
		});
		tFApellido_Debito.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				tFNombre_Debito.setEditable(true);
				tFDNI_Debito.setEditable(true);
				tFMonto_Debito.setEditable(true);
				if (k.getKeyCode() == 8 || k.getKeyCode() == 127 || k.getKeyCode() == 20  || k.getKeyCode() >= 65 && k.getKeyCode() <= 90) {
					tFApellido_Debito.setEditable(true);
				} else {
					tFApellido_Debito.setEditable(false);
				}
			}
		});
		tFDNI_Debito.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				tFNombre_Debito.setEditable(true);
				tFApellido_Debito.setEditable(true);
				tFMonto_Debito.setEditable(true);
				if(k.getKeyCode() == 8 || k.getKeyCode() == 127  || k.getKeyChar() >= '0' && k.getKeyChar() <= '9')  {
					tFDNI_Debito.setEditable(true);
				} else {
					tFDNI_Debito.setEditable(false);
				}
			}
		});
		tFMonto_Debito.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				tFNombre_Debito.setEditable(true);
				tFApellido_Debito.setEditable(true);
				tFDNI_Debito.setEditable(true);
				if (k.getKeyCode() == 8 || k.getKeyCode() == 127  || k.getKeyChar() >= '0' && k.getKeyChar() <= '9') {
					tFMonto_Debito.setEditable(true);
				} else {
					tFMonto_Debito.setEditable(false);
				}
			}
		});
		tFNombre_Credito.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				tFApellido_Credito.setEditable(true);
				tFDNI_Credito.setEditable(true);
				tFMonto_Credito.setEditable(true);
				if (k.getKeyCode() == 8 || k.getKeyCode() == 127  || k.getKeyCode() == 20  || k.getKeyCode() >= 65 && k.getKeyCode() <= 90) {
					tFNombre_Credito.setEditable(true);
				} else {
					tFNombre_Credito.setEditable(false);
				}
			}
		});
		tFApellido_Credito.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				tFNombre_Credito.setEditable(true);
				tFDNI_Credito.setEditable(true);
				tFMonto_Credito.setEditable(true);
				if (k.getKeyCode() == 8 || k.getKeyCode() == 127  || k.getKeyCode() == 20  || k.getKeyCode() >= 65 && k.getKeyCode() <= 90) {
					tFApellido_Credito.setEditable(true);
				} else {
					tFApellido_Credito.setEditable(false);
				}
			}
		});
		tFDNI_Credito.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				tFNombre_Credito.setEditable(true);
				tFApellido_Credito.setEditable(true);
				tFMonto_Credito.setEditable(true);
				if (k.getKeyCode() == 8 || k.getKeyCode() == 127  || k.getKeyChar() >= '0' && k.getKeyChar() <= '9') {
					tFDNI_Credito.setEditable(true);
				} else {
					tFDNI_Credito.setEditable(false);
				}
			}
		});
		tFMonto_Credito.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				tFNombre_Credito.setEditable(true);
				tFApellido_Credito.setEditable(true);
				tFDNI_Credito.setEditable(true);
				if (k.getKeyCode() == 8 || k.getKeyCode() == 127  || k.getKeyChar() >= '0' && k.getKeyChar() <= '9') {
					tFMonto_Credito.setEditable(true);
				} else {
					tFMonto_Credito.setEditable(false);
				}
			}
		});
		//------------------------------------------------------------
		
	}
	
	//Esta parte se encarga de generara una listita imprimible de transacciones y colocarla en la tabla de transacciones
	public void imprimirTransacciones( PositionList<Transaccion> listaAImprimir ) {
		
		tTablaHistorial = new JTable(new String[][] {},new String[] {});
		
		
		String[][] valores = new String[listaAImprimir.size()][6];
		Iterator<Transaccion> it = listaAImprimir.iterator();
		int i = 0;
		Transaccion aux;
		while(it.hasNext()) {
			
			aux = it.next();
			
			valores[i][0] = Integer.toString(aux.getEmisor().getDNI());
			valores[i][1] = aux.getEmisor().getNombre();
			valores[i][2] = Integer.toString(aux.getReceptor().getDNI());
			valores[i][3] = Float.toString(aux.getMonto());
			valores[i][4] = aux.getTipo();
			valores[i][5] = aux.getFecha().getDate() + "/" + (aux.getFecha().getMonth() + 1) + "/" + (aux.getFecha().getYear() + 1900);
			
			i++;
		}
		tTablaHistorial = new JTable(valores,new String[] {"DNI Emisor", "Nombre Emisor", "DNI Beneficiario","Monto","Tipo","Fecha"});
		ContenerdorHistorial.setViewportView(tTablaHistorial);
	}
	
	public void imprimirTransacciones( Iterable<Entry<Float,Transaccion>> listaAImprimir ) {	
		
		int tamanio = 0;
		Iterator<Entry<Float,Transaccion>> cursorAux = listaAImprimir.iterator();
		
		while(cursorAux.hasNext()) {
			cursorAux.next();
			tamanio++;	
		}
		
		
		tTablaHistorial = new JTable(new String[][] {},new String[] {});
		
		String[][] valores = new String[tamanio][6];
		Iterator<Entry<Float,Transaccion>> it = listaAImprimir.iterator(); 
		Entry<Float,Transaccion> auxEntry;
		int i = 0;
		while(it.hasNext()) {
			auxEntry = it.next();
			valores[i][0] = Integer.toString(auxEntry.getValue().getEmisor().getDNI());
			valores[i][1] = auxEntry.getValue().getEmisor().getNombre();
			valores[i][2] = Integer.toString(auxEntry.getValue().getReceptor().getDNI());
			valores[i][3] = Float.toString(auxEntry.getValue().getMonto());
			valores[i][4] = auxEntry.getValue().getTipo();
			valores[i][5] = auxEntry.getValue().getFecha().getDate() + "/" + (auxEntry.getValue().getFecha().getMonth() + 1) + "/" + (auxEntry.getValue().getFecha().getYear() + 1900);
			i++;
		}
		tTablaHistorial = new JTable(valores,new String[] {"DNI Emisor", "Nombre Emisor", "DNI Beneficiario","Monto","Tipo","Fecha"});
		ContenerdorHistorial.setViewportView(tTablaHistorial);		
	}

	public void imprimirTransacciones( PriorityQueue<Float,Transaccion> colaAImprimir) {

		tTablaHistorial = new JTable(new String[][] {},new String[] {});
		
		String[][] valores = new String[colaAImprimir.size()][6];
		
		int tamanio = colaAImprimir.size();
		int i = 0;
		try {
			while(tamanio != 0) {
				valores[i][0] = Integer.toString(colaAImprimir.min().getValue().getEmisor().getDNI()); 
				valores[i][1] = colaAImprimir.min().getValue().getEmisor().getNombre();
				valores[i][2] = Integer.toString(colaAImprimir.min().getValue().getReceptor().getDNI());
				valores[i][3] = Float.toString(colaAImprimir.min().getValue().getMonto());
				valores[i][4] = colaAImprimir.min().getValue().getTipo();
				valores[i][5] = colaAImprimir.min().getValue().getFecha().getDate() + "/" + (colaAImprimir.min().getValue().getFecha().getMonth() + 1) + "/" + (colaAImprimir.min().getValue().getFecha().getYear() + 1900);
				colaAImprimir.removeMin();
				i++;
				tamanio--;
			}
		}catch(EmptyPriorityQueueException s) {
			s.getStackTrace();
		}
		
		tTablaHistorial = new JTable(valores,new String[] {"DNI Emisor", "Nombre Emisor", "DNI Beneficiario","Monto","Tipo","Fecha"});
		ContenerdorHistorial.setViewportView(tTablaHistorial);	
	}
	
	public void generadorCartel(float parametroSaldo, Date parametroFecha) {
		
		double MontoDoble = new Double(parametroSaldo).doubleValue();
		Locale Localidad = new Locale("en", "US");
		Currency Moneda = Currency.getInstance(Localidad);
		NumberFormat FormatoDeNumero = NumberFormat.getCurrencyInstance(Localidad);
		
		JFrame panelMuestraSaldo = new JFrame();
		panelMuestraSaldo.setBounds(100, 100, 258, 129);
		panelMuestraSaldo.getContentPane().setLayout(null);
		
		JLabel lbl1 = new JLabel("Su saldo el día:");
		lbl1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl1.setBounds(10, 21, 90, 14);
		panelMuestraSaldo.getContentPane().add(lbl1);
		
		JLabel lblEraDe = new JLabel("Era de : $");
		lblEraDe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEraDe.setBounds(10, 47, 68, 14);
		panelMuestraSaldo.getContentPane().add(lblEraDe);
		
		JLabel lblFecha = new JLabel( parametroFecha.getDate() +"/" + parametroFecha.getMonth() +"/" + (parametroFecha.getYear() + 1900) );
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFecha.setBounds(124, 21, 90, 14);
		panelMuestraSaldo.getContentPane().add(lblFecha);
		
		JLabel lblMonto = new JLabel(FormatoDeNumero.format(MontoDoble));
		lblMonto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMonto.setBounds(124, 47, 68, 14);
		panelMuestraSaldo.getContentPane().add(lblMonto);
		
		panelMuestraSaldo.setVisible(true);
	
	}
}
