package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

import Programa.*;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

public class Ingreso {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ingreso window = new Ingreso();
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
	public Ingreso() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 228, 225));
		frame.getContentPane().setForeground(new Color(0, 0, 0));
		frame.setBounds(100, 100, 460, 560);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(127, 115, 156, 40);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(127, 185, 156, 40);
		textField_1.setColumns(10);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(127, 253, 156, 40);
		textField_2.setColumns(10);
		frame.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setBounds(127, 322, 156, 40);
		textField_3.setColumns(10);
		frame.getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setBounds(127, 402, 156, 40);
		textField_4.setColumns(10);
		frame.getContentPane().add(textField_4);
		
		JLabel lblNewLabel = new JLabel("Inicio de sesion");
		lblNewLabel.setFont(new Font("Lucida Fax", Font.BOLD, 20));
		lblNewLabel.setBounds(6, 6, 207, 29);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre*");
		lblNewLabel_1.setFont(new Font("Lucida Fax", Font.BOLD, 18));
		lblNewLabel_1.setBounds(127, 86, 117, 28);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Apellido*");
		lblNewLabel_1_1.setFont(new Font("Lucida Fax", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(127, 156, 156, 29);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("DNI*");
		lblNewLabel_1_2.setFont(new Font("Lucida Fax", Font.BOLD, 18));
		lblNewLabel_1_2.setBounds(127, 236, 61, 16);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Clave*");
		lblNewLabel_1_3.setFont(new Font("Lucida Fax", Font.BOLD, 18));
		lblNewLabel_1_3.setBounds(127, 304, 61, 16);
		frame.getContentPane().add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Monto inicial");
		lblNewLabel_1_4.setFont(new Font("Lucida Fax", Font.BOLD, 18));
		lblNewLabel_1_4.setBounds(127, 373, 156, 29);
		frame.getContentPane().add(lblNewLabel_1_4);
		
		JButton btnIngresar = new JButton("INGRESAR");
		btnIngresar.setBounds(149, 461, 117, 29);
		frame.getContentPane().add(btnIngresar);
		
		JLabel lblNewLabel_2 = new JLabel("Cuenta Bancaria");
		lblNewLabel_2.setFont(new Font("Lucida Fax", Font.BOLD, 20));
		lblNewLabel_2.setBounds(279, 7, 175, 26);
		frame.getContentPane().add(lblNewLabel_2);
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = textField.getText();
				String apellido = textField_1.getText();
				String dni = textField_2.getText();
				String clave = textField_3.getText();
				float monto = 0;
				CuentaBancaria cuenta;
				if(textField_4.getText().length() != 0){
					monto = Float.parseFloat(textField_4.getText());
				}
				if(monto != 0) {
					 cuenta = new CuentaBancaria(dni, nombre, apellido, monto);
				}
				else {
					 cuenta = new CuentaBancaria(dni, nombre, apellido);
				}
				
				boolean valido = cuenta.ValidarIngreso(clave);
				System.out.println(valido);
				System.out.println(monto);
				if(valido) {
					frame.setVisible(false);
					Menu menu = new Menu();
					menu.setCuentaBancaria(cuenta);
					menu.iniciar();
				}
			}
		});
	}
}