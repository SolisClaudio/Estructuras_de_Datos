package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import Programa.CuentaBancaria;

import javax.swing.JPanel;
import javax.swing.UIManager;

import Exceptions.EmptyListException;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.FlowLayout;
import javax.swing.JTextPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;

public class Menu {

	private CuentaBancaria cuenta;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
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
	public Menu() {
		cuenta = null;
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void iniciar() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.getContentPane().setBackground(new Color(255, 228, 225));
		frame.getContentPane().setForeground(Color.BLACK);
		frame.setBounds(100, 100, 650, 620);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre: "+cuenta.getNombre());
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel.setBounds(6, 6, 210, 25);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Apellido: "+cuenta.getApellido());
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(6, 32, 210, 25);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("DNI: "+cuenta.getDni());
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(6, 57, 210, 25);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Saldo: "+cuenta.getSaldo());
		lblNewLabel_3.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_3.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_3.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(444, 105, 180, 33);
		frame.getContentPane().add(lblNewLabel_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(6, 271, 618, 131);
		frame.getContentPane().add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(Color.WHITE);
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(null);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(6, 140, 618, 120);
		frame.getContentPane().add(scrollPane_1);
		
		JTextArea panelHistorial = new JTextArea();
		panelHistorial.setEditable(false);
		scrollPane_1.setViewportView(panelHistorial);
		
		JButton botonTdebito = new JButton("Transferencia debito");
		botonTdebito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dni = JOptionPane.showInputDialog(botonTdebito, "Ingrese el DNI del beneficiario: \n(Solo numeros)", "Transferencia debito", JOptionPane.QUESTION_MESSAGE);
				String nombre = JOptionPane.showInputDialog(botonTdebito, "Ingrese el nombre del beneficiario: \n(Solo caracteres)", "Transferencia debito", JOptionPane.QUESTION_MESSAGE);
				String apellido = JOptionPane.showInputDialog(botonTdebito, "Ingrese el apellido del beneficiario: \n(Solo caracteres)", "Transferencia debito", JOptionPane.QUESTION_MESSAGE);
				String monto = JOptionPane.showInputDialog(botonTdebito, "Ingrese el monto a transferir: \n(Solo numeros)", "Transferencia debito", JOptionPane.QUESTION_MESSAGE);
				
				if(dni != null && nombre != null && apellido != null && monto != null && dni != "" && nombre != "" && apellido != "" && monto != "") {
					float montoF = Float.parseFloat(monto);
					cuenta.transferenciaDebito(montoF, nombre, apellido, dni);
					try {
						panelHistorial.append(cuenta.getHistorial().first().element().toString()+"\n");
						lblNewLabel_3.setText(""+cuenta.getSaldo());
					} catch (EmptyListException e1) {
						e1.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(botonTdebito, "Algo salio mal! Intentelo de nuevo.");
				}
			}
		});
		botonTdebito.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		botonTdebito.setBounds(6, 411, 320, 33);
		frame.getContentPane().add(botonTdebito);
		
		JButton botonUltimasNT = new JButton("UltimasNTransacciones");
		botonUltimasNT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cant = JOptionPane.showInputDialog(botonUltimasNT, "Ingrese la cantidad de transacciones a mostrar: \n(Solo numeros)", "Ultimas transacciones", JOptionPane.QUESTION_MESSAGE);
				if(cant != null && cant != "") {
					int cantI = Integer.parseInt(cant);
					textArea.setText(cuenta.ultimasNTransacciones(cantI));
				}
				else {
					JOptionPane.showMessageDialog(botonUltimasNT, "Algo salio mal! Intentelo de nuevo.");
				}
			}
		});
		botonUltimasNT.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		botonUltimasNT.setBounds(6, 456, 320, 33);
		frame.getContentPane().add(botonUltimasNT);
		
		JButton botonTcredito = new JButton("Transferencia credito");
		botonTcredito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dni = JOptionPane.showInputDialog(botonTcredito, "Ingrese el DNI del depositante: \n(Solo numeros)", "Transferencia credito", JOptionPane.QUESTION_MESSAGE);
				String nombre = JOptionPane.showInputDialog(botonTcredito, "Ingrese el nombre del depositante: \n(Solo caracteres)", "Transferencia credito", JOptionPane.QUESTION_MESSAGE);
				String apellido = JOptionPane.showInputDialog(botonTcredito, "Ingrese el apellido del depositante: \n(Solo caracteres)", "Transferencia credito", JOptionPane.QUESTION_MESSAGE);
				String monto = JOptionPane.showInputDialog(botonTcredito, "Ingrese el monto a depositar: \n(Solo numeros)", "Transferencia credito", JOptionPane.QUESTION_MESSAGE);

				if(dni != null && nombre != null && apellido != null && monto != null && dni != "" && nombre != "" && apellido != "" && monto != "") {
					float montoF = Float.parseFloat(monto);
					cuenta.transferenciaCredito(montoF, nombre, apellido, dni);
					try {
						panelHistorial.append(cuenta.getHistorial().first().element().toString()+"\n");
						lblNewLabel_3.setText(""+cuenta.getSaldo());
					} catch (EmptyListException e1) {
						e1.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(botonTcredito, "Algo salio mal! Intentelo de nuevo.");
				}
			}
		});
		
		botonTcredito.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		botonTcredito.setBounds(324, 411, 320, 33);
		frame.getContentPane().add(botonTcredito);
		
		JButton botonNMayoresT = new JButton("NMayoresTransacciones");
		botonNMayoresT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cant = JOptionPane.showInputDialog(botonNMayoresT, "Ingrese la cantidad de transacciones a mostrar: \n(Solo numeros)", "Mayores transacciones", JOptionPane.QUESTION_MESSAGE);
				if(cant != null && cant != "") {
					int cantI = Integer.parseInt(cant);
					textArea.setText(cuenta.NtransaccionesMayorValor(cantI));
				}
				else {
					JOptionPane.showMessageDialog(botonNMayoresT, "Algo salio mal! Intentelo de nuevo.");
				}
			}
		});
		botonNMayoresT.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		botonNMayoresT.setBounds(324, 456, 320, 33);
		frame.getContentPane().add(botonNMayoresT);
		
		JButton botonTMismoValor = new JButton("TransaccionesMismoValor");
		botonTMismoValor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String valor = JOptionPane.showInputDialog(botonTMismoValor, "Ingrese el valor a filtrar: \n(Solo numeros)", "Transacciones de un mismo valor", JOptionPane.QUESTION_MESSAGE);
				if(valor != null && valor != "") {
					float valorF = Float.parseFloat(valor);
					textArea.setText(cuenta.filtrarMismoValor(valorF));
				}
				else {
					JOptionPane.showMessageDialog(botonTMismoValor, "Algo salio mal! Intentelo de nuevo.");
				}
			}
		});
		botonTMismoValor.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		botonTMismoValor.setBounds(6, 501, 320, 33);
		frame.getContentPane().add(botonTMismoValor);
		
		JButton botonTMayores = new JButton("TransaccionesMayores");
		botonTMayores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String valor = JOptionPane.showInputDialog(botonTMayores, "Ingrese el valor a filtrar: \n(Solo numeros)", "Transacciones mayores a un valor", JOptionPane.QUESTION_MESSAGE);
				if(valor != null && valor != "") {
					float valorF = Float.parseFloat(valor);
					String[] opciones = {"Debito", "Credito", "Ambas"};
					int respuesta = JOptionPane.showOptionDialog(null, 
							"Seleccione el tipo de transferencia a filtrar:", 
							"Transacciones mayores", 
							JOptionPane.DEFAULT_OPTION, 
							JOptionPane.QUESTION_MESSAGE, null, 
							opciones, opciones[2]);
					if(respuesta == 0)
						textArea.setText(cuenta.filtrarMayores(valorF, "debito"));
					if(respuesta == 1)
						textArea.setText(cuenta.filtrarMayores(valorF, "credito"));
					if(respuesta == 2)
						textArea.setText(cuenta.filtrarMayores(valorF, "ambas"));
				}
				
			}
		});
		botonTMayores.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		botonTMayores.setBounds(324, 501, 320, 33);
		frame.getContentPane().add(botonTMayores);
		
		JButton botonTEnUnaFecha = new JButton("TransaccionesEnUnaFecha");
		botonTEnUnaFecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dia = JOptionPane.showInputDialog(botonTEnUnaFecha, "Ingrese el dia: \n(Solo numeros 1 - 31)", "Transacciones en una fecha determinada", JOptionPane.QUESTION_MESSAGE);
				String mes = JOptionPane.showInputDialog(botonTEnUnaFecha, "Ingrese el mes: \n(Solo numeros 1 - 12)", "Transacciones en una fecha determinada", JOptionPane.QUESTION_MESSAGE);
				String anio = JOptionPane.showInputDialog(botonTEnUnaFecha, "Ingrese el a�o: \n(Solo numeros)", "Transacciones en una fecha determinada", JOptionPane.QUESTION_MESSAGE);
				if(dia != null && dia != "" && mes != null && mes != "" && anio != null && anio != "") {
					int diaI = Integer.parseInt(dia);
					int mesI = Integer.parseInt(mes);
					int anioI = Integer.parseInt(anio);
					textArea.setText(cuenta.transaccionesEnUnaFecha(new Date(anioI-1900, mesI-1, diaI)));
				}
				else {
					JOptionPane.showMessageDialog(botonTEnUnaFecha, "Algo salio mal! Intentelo de nuevo.");
				}
			}
		});
		botonTEnUnaFecha.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		botonTEnUnaFecha.setBounds(6, 546, 320, 33);
		frame.getContentPane().add(botonTEnUnaFecha);
		
		JButton botonSaldoFecha = new JButton("SaldoEnUnaFecha");
		botonSaldoFecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dia = JOptionPane.showInputDialog(botonTEnUnaFecha, "Ingrese el dia: \n(Solo numeros 1 - 31)", "Saldo en una fecha determinada", JOptionPane.QUESTION_MESSAGE);
				String mes = JOptionPane.showInputDialog(botonTEnUnaFecha, "Ingrese el mes: \n(Solo numeros 1 - 12)", "Saldo en una fecha determinada", JOptionPane.QUESTION_MESSAGE);
				String anio = JOptionPane.showInputDialog(botonTEnUnaFecha, "Ingrese el a�o: \n(Solo numeros)", "Saldo en una fecha determinada", JOptionPane.QUESTION_MESSAGE);
				if(dia != null && dia != "" && mes != null && mes != "" && anio != null && anio != "") {
					int diaI = Integer.parseInt(dia);
					int mesI = Integer.parseInt(mes);
					int anioI = Integer.parseInt(anio);
					textArea.setText(cuenta.saldoCuentaDetDia(new Date(anioI-1900, mesI-1, diaI)));
				}
				else {
					JOptionPane.showMessageDialog(botonSaldoFecha, "Algo salio mal! Intentelo de nuevo.");
				}
			}
		});
		botonSaldoFecha.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		botonSaldoFecha.setBounds(324, 546, 320, 33);
		frame.getContentPane().add(botonSaldoFecha);
		
		JLabel lblNewLabel_2_1 = new JLabel("Historial de transacciones:");
		lblNewLabel_2_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel_2_1.setBounds(6, 105, 270, 25);
		frame.getContentPane().add(lblNewLabel_2_1);
	}
	
	public void setCuentaBancaria(CuentaBancaria cb) {
		cuenta = cb;
	}
}