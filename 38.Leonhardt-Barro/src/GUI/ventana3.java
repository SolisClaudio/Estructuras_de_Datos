package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Exceptions.InvalidContraseniaException;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class ventana3 extends JFrame {

	private JPanel contentPane;
	private JTextField nombre;
	private JTextField apellido;
	private JTextField dni;
	private JTextField contraseña;
	private JTextField Deposito;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventana3 frame = new ventana3();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ventana3() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 866, 468);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		nombre = new JTextField();
		nombre.setBounds(100, 86, 96, 19);
		contentPane.add(nombre);
		nombre.setColumns(10);
		
		apellido = new JTextField();
		apellido.setBounds(339, 86, 96, 19);
		contentPane.add(apellido);
		apellido.setColumns(10);
		
		dni = new JTextField();
		dni.setBounds(100, 164, 96, 19);
		contentPane.add(dni);
		dni.setColumns(10);
		
		contraseña = new JTextField();
		contraseña.setBounds(339, 164, 96, 19);
		contentPane.add(contraseña);
		contraseña.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(468, 220, 343, 189);
		contentPane.add(textArea);
		
		JButton InciarSesion = new JButton("Iniciar sesion");
		InciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    ventana1 v1;
				try {
					v1 = new ventana1(nombre.getText(),apellido.getText(),contraseña.getText(),Integer.parseInt(dni.getText()),Integer.parseInt(Deposito.getText()));
					v1.setVisible(true);
					setVisible(false);
				} catch (NumberFormatException | InvalidContraseniaException e1) {
					e1.printStackTrace();
					textArea.append("Contraseña incorrecta ");
				} 			
			}
		});
		InciarSesion.setBounds(176, 222, 112, 21);
		contentPane.add(InciarSesion);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(28, 89, 62, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Apellido:");
		lblNewLabel_1.setBounds(249, 89, 84, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("DNI:");
		lblNewLabel_2.setBounds(28, 167, 62, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Contrasenia:");
		lblNewLabel_3.setBounds(249, 167, 84, 16);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Monto a depositar:");
		lblNewLabel_4.setBounds(468, 131, 112, 19);
		contentPane.add(lblNewLabel_4);
		
		Deposito = new JTextField();
		Deposito.setBounds(575, 131, 96, 19);
		contentPane.add(Deposito);
		Deposito.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Si no desea ingresar nada,Ingrese 0");
		lblNewLabel_5.setBounds(468, 108, 203, 13);
		contentPane.add(lblNewLabel_5);
		
	}
}
