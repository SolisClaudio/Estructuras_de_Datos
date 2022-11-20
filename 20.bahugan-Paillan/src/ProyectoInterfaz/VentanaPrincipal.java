package ProyectoInterfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import TDADiccionario.Entry;
import TDALista.Position;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.border.BevelBorder;

public class VentanaPrincipal {

	private JFrame frame;
	private static CuentaBancaria cuenta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal(cuenta);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaPrincipal(CuentaBancaria c) {
		cuenta = c;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 830, 495);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextPane txtpnNombre = new JTextPane();
		txtpnNombre.setBackground(SystemColor.control);
		txtpnNombre.setEditable(false);
		txtpnNombre.setText("Nombre");
		txtpnNombre.setBounds(0, 0, 54, 20);
		frame.getContentPane().add(txtpnNombre);
		
		JTextPane txtpnNombreUsuario = new JTextPane();
		txtpnNombreUsuario.setBackground(SystemColor.control);
		txtpnNombreUsuario.setEditable(false);
		txtpnNombreUsuario.setText(cuenta.nombreCompleto());
		txtpnNombreUsuario.setBounds(54, 0, 151, 20);
		frame.getContentPane().add(txtpnNombreUsuario);
		
		JTextPane textPaneMonto = new JTextPane();
		textPaneMonto.setBackground(SystemColor.control);
		textPaneMonto.setText("Dinero Actual:");
		textPaneMonto.setEditable(false);
		textPaneMonto.setBounds(225, 0, 89, 20);
		frame.getContentPane().add(textPaneMonto);
		
		JTextPane textPaneMontoDisplay = new JTextPane();
		textPaneMontoDisplay.setBackground(SystemColor.control);
		textPaneMontoDisplay.setBounds(324, 0, 100, 20);
		frame.getContentPane().add(textPaneMontoDisplay);
		int dinero = cuenta.dinero();
		textPaneMontoDisplay.setText(Integer.toString(dinero));
		
		JButton btnNewButton = new JButton("Nueva Transferencia");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String numero;
				String dni;
				numero = JOptionPane.showInputDialog(btnNewButton, "Ingrese dinero a transferir", "dinero", JOptionPane.WARNING_MESSAGE);
				dni = JOptionPane.showInputDialog(btnNewButton, "Ingrese dni del destinatario", "dni", JOptionPane.WARNING_MESSAGE);
				if (numero != null && dni != null) {
					cuenta.retirarDinero(Integer.parseInt(numero), dni);
					textPaneMontoDisplay.setText(Integer.toString(cuenta.dinero()));
				}
			}
		});
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setBounds(10, 45, 195, 23);
		frame.getContentPane().add(btnNewButton);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 11));
		textArea.setBounds(217, 44, 587, 401);
		frame.getContentPane().add(textArea);
		
		JButton btnDeposito = new JButton("Nuevo Deposito");
		btnDeposito.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnDeposito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String numero;
				String nombre;
				numero = JOptionPane.showInputDialog(btnNewButton, "Ingrese dinero a depositar", "dinero", JOptionPane.WARNING_MESSAGE);
				nombre = JOptionPane.showInputDialog(btnNewButton, "Ingrese nombre del remitente", "nombre", JOptionPane.WARNING_MESSAGE);
				if (numero != null && nombre != null) {
					cuenta.ingresarDinero(Integer.parseInt(numero), nombre);
					textPaneMontoDisplay.setText(Integer.toString(cuenta.dinero()));
				}
			}
		});
		btnDeposito.setHorizontalAlignment(SwingConstants.LEFT);
		btnDeposito.setBounds(10, 84, 195, 23);
		frame.getContentPane().add(btnDeposito);
		
		JButton btnTransferencias = new JButton("Ultimas Transferencias");
		btnTransferencias.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnTransferencias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String n = JOptionPane.showInputDialog(btnNewButton, "¿Cuantas Transferencias quiere mostrar?", "Transacciones", JOptionPane.WARNING_MESSAGE);
				if (n != null) {
					textArea.setText("");
						for(Position<Transaccion> p : cuenta.Registro(Integer.parseInt(n))) {
							textArea.append(p.element().toString()+"\n");
						}
					}
				}
			})
		;
		btnTransferencias.setHorizontalAlignment(SwingConstants.LEFT);
		btnTransferencias.setBounds(10, 118, 195, 23);
		frame.getContentPane().add(btnTransferencias);
		
		JButton btnTransaccionesMayorValor = new JButton("Transacciones con mayor valor");
		btnTransaccionesMayorValor.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnTransaccionesMayorValor.setHorizontalAlignment(SwingConstants.LEFT);
		btnTransaccionesMayorValor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String N = JOptionPane.showInputDialog(btnNewButton, "Indique el maximo de transacciones que quiere ver ", "Transacciones", JOptionPane.WARNING_MESSAGE);
				if (N != null) {
					textArea.setText("");
					for(Transaccion p : cuenta.MayoresTransacciones(Integer.parseInt(N))) {
						textArea.append(p.toString()+"\n");
					}
				}
			}
		});
		btnTransaccionesMayorValor.setBounds(10, 152, 195, 23);
		frame.getContentPane().add(btnTransaccionesMayorValor);
		
		JButton btnTransaccionesValorN = new JButton("Transacciones con valor N");
		btnTransaccionesValorN.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnTransaccionesValorN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String N = JOptionPane.showInputDialog(btnNewButton, "Indique valores de transaccion", "Transacciones", JOptionPane.WARNING_MESSAGE);
				if (N != null) {
					textArea.setText("");
					for(Entry<Integer, Transaccion> p : cuenta.TransaccionesMontoN(Integer.parseInt(N)).entries()) {
						textArea.append(p.getValue().toString()+"\n");
					}
				}
			}
		});
		btnTransaccionesValorN.setHorizontalAlignment(SwingConstants.LEFT);
		btnTransaccionesValorN.setBounds(10, 186, 195, 23);
		frame.getContentPane().add(btnTransaccionesValorN);
		
		JButton btnTransaccionesFecha = new JButton("Transacciones en Fecha");
		btnTransaccionesFecha.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnTransaccionesFecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String anio = JOptionPane.showInputDialog(btnNewButton, "Indique año de la transaccion", "Transaccion", JOptionPane.WARNING_MESSAGE);
				String mes = JOptionPane.showInputDialog(btnNewButton, "Indique mes de la transaccion", "Transaccion", JOptionPane.WARNING_MESSAGE);
				String dia = JOptionPane.showInputDialog(btnNewButton, "Indique dia de la transaccion", "Transaccion", JOptionPane.WARNING_MESSAGE);
				String fecha = anio+"/"+mes+"/"+dia;
				if (anio != null && mes != null && dia != null) {
					DateFormat df = new SimpleDateFormat("yyyy/MM/dd");		
					Calendar cal  = Calendar.getInstance();
					try {
						cal.setTime(df.parse(fecha));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					textArea.setText("");
					for(Position<Transaccion> p : cuenta.TransaccionesEnFecha(fecha)) {
						textArea.append(p.element().toString()+"\n");
					}
				}
			}
		});
		btnTransaccionesFecha.setHorizontalAlignment(SwingConstants.LEFT);
		btnTransaccionesFecha.setBounds(10, 220, 195, 23);
		frame.getContentPane().add(btnTransaccionesFecha);
		
		JButton btnMontoEn = new JButton("Monto en Fecha");
		btnMontoEn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnMontoEn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String anio = JOptionPane.showInputDialog(btnNewButton, "Indique año de la transaccion", "Transaccion", JOptionPane.WARNING_MESSAGE);
					String mes = JOptionPane.showInputDialog(btnNewButton, "Indique mes de la transaccion", "Transaccion", JOptionPane.WARNING_MESSAGE);
					String dia = JOptionPane.showInputDialog(btnNewButton, "Indique dia de la transaccion", "Transaccion", JOptionPane.WARNING_MESSAGE);
					String fecha = anio+"/"+mes+"/"+dia;
					if (anio != null && mes != null && dia != null) {
						DateFormat df = new SimpleDateFormat("yyyy/MM/dd");		
						Calendar cal  = Calendar.getInstance();
						cal.setTime(df.parse(fecha));
						textArea.setText("");
						textArea.append("el saldo en el dia "+fecha+ " fue de: "+cuenta.CalcularMontoFecha(fecha)+"\n");
					}
				}
					catch (ParseException pe) {
						pe.printStackTrace();
					}
			}
		});
		btnMontoEn.setHorizontalAlignment(SwingConstants.LEFT);
		btnMontoEn.setBounds(10, 288, 195, 23);
		frame.getContentPane().add(btnMontoEn);
		
		JButton btnNewButton_1 = new JButton("Mostrar N mayores");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] buttons = { "Credito", "Debito", "Ambas"}; 
				int respuesta = JOptionPane.showOptionDialog(btnNewButton_1, "Seleccione", null,JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[0]);
				String aux = JOptionPane.showInputDialog(btnNewButton_1, "Ingrese valor de las tranferencias a mostrar", null, JOptionPane.QUESTION_MESSAGE);
				if (aux != null) {
					int n = Integer.parseInt(aux);
					textArea.setText("");
					if(respuesta == 0) {
						for(Position<Transaccion> p : cuenta.CreditosMayores(n)) {
							textArea.append(p.element().toString()+"\n");
						}
					}
					if(respuesta == 1) {
						for(Position<Transaccion> p : cuenta.DebitosMayores(n)) {
							textArea.append(p.element().toString()+"\n");
						}
					}
					if(respuesta == 2) {
						for(Position<Transaccion> p : cuenta.TodosMayores(n)) {
							textArea.append(p.element().toString()+"\n");
						} 
					}
				}
			}
		});
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_1.setBounds(10, 254, 195, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		
		
	}
}
