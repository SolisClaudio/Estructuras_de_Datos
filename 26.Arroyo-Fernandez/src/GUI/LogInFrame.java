package GUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import Exceptions.LogueoInvalidoException;
import Exceptions.RegistroInvalidoException;
import Programa.Logica;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LogInFrame {
	
	private Logica logica;
	
	private boolean login;
	
	
	private JFrame frmBancoeddInicio;
	private JTextField txtNombre;
	private JTextField textFieldNombre;
	private JTextField txtApellido;
	private JTextField textFieldApellido;
	private JTextField txtDni;
	private JTextField textFieldDNI;
	private JTextField txtClave;
	private final JButton btnIniciarSesion = new JButton("Iniciar Sesión");
	private final JButton btnRegistrar = new JButton("Crear cuenta nueva");
	private JTextField textFieldClave;

	

	/**
	 * Create the application.
	 */
	public LogInFrame(Logica l) {
		initialize();
		logica = l;
		login = true;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrmBancoeddInicio(new JFrame());
		getFrmBancoeddInicio().setTitle("BancoEDD - Inicio de sesión");
		getFrmBancoeddInicio().setBounds(100, 100, 300, 450);
		getFrmBancoeddInicio().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrmBancoeddInicio().getContentPane().setLayout(null);
		
		JTextPane txtpnBancoEdd = new JTextPane();
		txtpnBancoEdd.setEditable(false);
		txtpnBancoEdd.setBackground(new Color(240, 240, 240));
		txtpnBancoEdd.setFont(new Font("Montserrat Black", Font.PLAIN, 30));
		txtpnBancoEdd.setText("Banco EDD");
		txtpnBancoEdd.setBounds(48, 11, 187, 43);
		getFrmBancoeddInicio().getContentPane().add(txtpnBancoEdd);
		
		JTextArea txtBienvenido = new JTextArea();
		txtBienvenido.setEditable(false);
		txtBienvenido.setBackground(new Color(240, 240, 240));
		txtBienvenido.setFont(new Font("Montserrat", Font.PLAIN, 10));
		txtBienvenido.setLineWrap(true);
		txtBienvenido.setText("Bienvenido, ingrese sus datos para iniciar sesión.");
		txtBienvenido.setBounds(15, 54, 259, 20);
		getFrmBancoeddInicio().getContentPane().add(txtBienvenido);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Montserrat", Font.PLAIN, 11));
		txtNombre.setEditable(false);
		txtNombre.setText("Nombre");
		txtNombre.setBounds(28, 92, 86, 20);
		getFrmBancoeddInicio().getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		txtNombre.setBorder(null);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setToolTipText("Ingrese su nombre");
		textFieldNombre.setFont(new Font("Montserrat", Font.PLAIN, 11));
		textFieldNombre.setBounds(42, 114, 200, 21);
		getFrmBancoeddInicio().getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setFont(new Font("Montserrat", Font.PLAIN, 11));
		txtApellido.setText("Apellido");
		txtApellido.setEditable(false);
		txtApellido.setColumns(10);
		txtApellido.setBorder(null);
		txtApellido.setBounds(28, 146, 86, 20);
		getFrmBancoeddInicio().getContentPane().add(txtApellido);
		
		textFieldApellido = new JTextField();
		textFieldApellido.setToolTipText("Ingrese su apellido");
		textFieldApellido.setFont(new Font("Montserrat", Font.PLAIN, 11));
		textFieldApellido.setColumns(10);
		textFieldApellido.setBounds(42, 168, 200, 21);
		getFrmBancoeddInicio().getContentPane().add(textFieldApellido);
		
		txtDni = new JTextField();
		txtDni.setFont(new Font("Montserrat", Font.PLAIN, 11));
		txtDni.setText("DNI");
		txtDni.setEditable(false);
		txtDni.setColumns(10);
		txtDni.setBorder(null);
		txtDni.setBounds(28, 200, 86, 20);
		getFrmBancoeddInicio().getContentPane().add(txtDni);
		
		textFieldDNI = new JTextField();
		textFieldDNI.setToolTipText("Ingrese su DNI");
		textFieldDNI.setFont(new Font("Montserrat", Font.PLAIN, 11));
		textFieldDNI.setColumns(10);
		textFieldDNI.setBounds(42, 222, 200, 21);
		getFrmBancoeddInicio().getContentPane().add(textFieldDNI);
		
		txtClave = new JTextField();
		txtClave.setFont(new Font("Montserrat", Font.PLAIN, 11));
		txtClave.setText("Clave");
		txtClave.setEditable(false);
		txtClave.setColumns(10);
		txtClave.setBorder(null);
		txtClave.setBounds(28, 254, 86, 20);
		getFrmBancoeddInicio().getContentPane().add(txtClave);
		btnIniciarSesion.setToolTipText("Iniciar Sesión");
		
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(login) {
					try {
						String nombre = textFieldNombre.getText();
						String apellido = textFieldApellido.getText();
						int dni = Integer.parseInt(textFieldDNI.getText());
						String clave = textFieldClave.getText();
						logica.logIn(clave, nombre, apellido, dni);
						//Lanzo ventana de interfaz
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									InterfazFrame window = new InterfazFrame(logica);
									window.getFrmBancoEdd().setVisible(true);
									
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
						//Cierro ventana de LogIn
						frmBancoeddInicio.dispose();
					} catch (LogueoInvalidoException | NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "Datos ingresados incorrectos", "Logueo Invalido", 0);
					}
				}
				else {
					try {
							String nombre = textFieldNombre.getText();
							String apellido = textFieldApellido.getText();
							int dni = Integer.parseInt(textFieldDNI.getText());
							int monto;
							if (!textFieldDNI.getText().equals(""))
								monto = Integer.parseInt(textFieldClave.getText());
							else {
								monto = 0;
							}
							logica.signIn(nombre, apellido, dni, monto);
							JOptionPane.showMessageDialog(null, "Cuenta creada exitosamente", "Registro valido", 1);
						}
						catch(RegistroInvalidoException | NumberFormatException e1) {
							JOptionPane.showMessageDialog(null, "Datos ingresados incorrectos", "Registro Invalido", 0);
						}
				}
				
			}
		});
		
		
		btnIniciarSesion.setFont(new Font("Montserrat", Font.PLAIN, 11));
		btnIniciarSesion.setBounds(86, 308, 111, 20);
		getFrmBancoeddInicio().getContentPane().add(btnIniciarSesion);
		
		
		btnRegistrar.setFont(new Font("Montserrat", Font.PLAIN, 11));
		btnRegistrar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(login) {
					txtClave.setText("Monto inicial");
					btnIniciarSesion.setText("Registrar");
					btnRegistrar.setText("Iniciar Sesión");
					txtBienvenido.setText("Ingrese los datos para crear una nueva cuenta.");
					txtBienvenido.setBounds(20, 54, 259, 20);
					textFieldClave.setText("");
					textFieldClave.setToolTipText("Ingrese un monto inicial.");
					login = false;
				}
				else {
					txtClave.setText("Clave");
					btnIniciarSesion.setText("Iniciar Sesión");
					btnRegistrar.setText("Crear cuenta nueva");
					txtBienvenido.setText("Bienvenido, ingrese sus datos para iniciar sesión.");
					txtBienvenido.setBounds(15, 54, 259, 20);
					textFieldClave.setText("");
					textFieldClave.setToolTipText("Ingrese su clave (AxA'A')");
					login = true;
				}
			}
		});
		btnRegistrar.setBounds(118, 369, 151, 31);
		getFrmBancoeddInicio().getContentPane().add(btnRegistrar);
		
		textFieldClave = new JTextField();
		textFieldClave.setToolTipText("Ingrese su clave (AxA'A')");
		textFieldClave.setFont(new Font("Montserrat", Font.PLAIN, 11));
		textFieldClave.setColumns(10);
		textFieldClave.setBounds(42, 276, 200, 21);
		frmBancoeddInicio.getContentPane().add(textFieldClave);
		
		
	}

	public JFrame getFrmBancoeddInicio() {
		return frmBancoeddInicio;
	}

	public void setFrmBancoeddInicio(JFrame frmBancoeddInicio) {
		this.frmBancoeddInicio = frmBancoeddInicio;
		frmBancoeddInicio.setResizable(false);
	}
}
