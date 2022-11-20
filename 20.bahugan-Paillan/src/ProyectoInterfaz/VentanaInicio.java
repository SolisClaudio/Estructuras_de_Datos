package ProyectoInterfaz;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JPasswordField;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JDesktopPane;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.SystemColor;

public class VentanaInicio {

	private JFrame frame;
	private JPasswordField Contra;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInicio window = new VentanaInicio();
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
	public VentanaInicio() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JButton btnNewButton = new JButton("Ingresar");
		
		btnNewButton.setBounds(163, 194, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JTextPane txtpnNombreBanco = new JTextPane();
		txtpnNombreBanco.setBackground(SystemColor.control);
		txtpnNombreBanco.setEditable(false);
		txtpnNombreBanco.setText("BankJuanChad");
		txtpnNombreBanco.setBounds(148, 22, 96, 20);
		frame.getContentPane().add(txtpnNombreBanco);
		
		Contra = new JPasswordField();
		Contra.setBounds(96, 123, 127, 20);
		frame.getContentPane().add(Contra);
		
		JTextPane txtpnNombre = new JTextPane();
		txtpnNombre.setBackground(SystemColor.control);
		txtpnNombre.setText("Nombre ");
		txtpnNombre.setEditable(false);
		txtpnNombre.setBounds(10, 56, 57, 20);
		frame.getContentPane().add(txtpnNombre);
		
		JFormattedTextField formattedTextFieldNombre = new JFormattedTextField();
		formattedTextFieldNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		formattedTextFieldNombre.setBounds(96, 57, 127, 20);
		frame.getContentPane().add(formattedTextFieldNombre);
		
		JTextPane txtpnContrasea = new JTextPane();
		txtpnContrasea.setBackground(SystemColor.control);
		txtpnContrasea.setText("Contraseña");
		txtpnContrasea.setEditable(false);
		txtpnContrasea.setBounds(10, 122, 76, 20);
		frame.getContentPane().add(txtpnContrasea);
		
		JTextPane txtpnApellido = new JTextPane();
		txtpnApellido.setBackground(SystemColor.control);
		txtpnApellido.setText("Apellido");
		txtpnApellido.setEditable(false);
		txtpnApellido.setBounds(10, 78, 57, 20);
		frame.getContentPane().add(txtpnApellido);
		
		JTextPane txtpnDNI = new JTextPane();
		txtpnDNI.setBackground(SystemColor.control);
		txtpnDNI.setText("DNI");
		txtpnDNI.setEditable(false);
		txtpnDNI.setBounds(10, 100, 57, 20);
		frame.getContentPane().add(txtpnDNI);
		
		JFormattedTextField formattedTextFielApellido = new JFormattedTextField();
		formattedTextFielApellido.setBounds(96, 79, 127, 20);
		frame.getContentPane().add(formattedTextFielApellido);
		
		JFormattedTextField formattedTextFieldDNI = new JFormattedTextField();
		formattedTextFieldDNI.setBounds(96, 101, 127, 20);
		frame.getContentPane().add(formattedTextFieldDNI);
		
		JTextPane txtpnMontoInicial = new JTextPane();
		txtpnMontoInicial.setBackground(SystemColor.control);
		txtpnMontoInicial.setText("Monto Inicial");
		txtpnMontoInicial.setEditable(false);
		txtpnMontoInicial.setBounds(10, 142, 76, 23);
		frame.getContentPane().add(txtpnMontoInicial);
		JFormattedTextField formattedTextFieldMonto = new JFormattedTextField();
		formattedTextFieldMonto.setBounds(96, 145, 127, 20);
		frame.getContentPane().add(formattedTextFieldMonto);
		//-----------------------------IMAGEN
		JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBounds(255, 19, 170, 180);
        frame.getContentPane().add(lblNewLabel);
		
        ImageIcon icon = new ImageIcon("ImagenProyecto.png");
        Image image = icon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(170, 180,  java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        lblNewLabel.setIcon(icon);
        //-------------------------
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
				CuentaBancaria cuenta;
				if (formattedTextFieldMonto.getText().isEmpty()) {
					cuenta = new CuentaBancaria(formattedTextFieldNombre.getText(),formattedTextFielApellido.getText(),formattedTextFieldDNI.getText());
				}
				else {
					cuenta = new CuentaBancaria(formattedTextFieldNombre.getText(),formattedTextFielApellido.getText(),formattedTextFieldDNI.getText(),Integer.parseInt(formattedTextFieldMonto.getText()));
				}
				if(!cuenta.verificarContra(Contra.getText())) {
					JOptionPane.showMessageDialog(null, "la contraseña es incorrecta", "error", JOptionPane.WARNING_MESSAGE);
					}
				
				else {
					frame.setVisible(false);
					VentanaPrincipal menu = new VentanaPrincipal(cuenta);
					menu.main(null);
					
				}
				
				

				
			}
		});
	}
}
