package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.JDesktopPane;
import javax.swing.JSplitPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import java.awt.Button;
import java.awt.Choice;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Exceptions.InvalidKeyException;
import Programa.CuentaBancaria;
import Programa.Logica;
import Programa.Transaccion;
import TDALista.PositionList;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class InterfazFrame {

	private Logica logica;
	private CuentaBancaria sesion;
	
	private JFrame frmBancoEdd;
	private JTextField txtNombreApellido;
	private JTextField textFieldSaldo;
	private JTextField textFieldSymbol;
	private JTextField textFieldDia;
	private JTextField textFieldMes;
	private JTextField textFieldAño;
	private JTable tableTransacciones;
	private JTextField textFieldDia2;
	private JTextField textFieldMes2;
	private JTextField textFieldAño2;
	private JCheckBox chckbxDebito;
	private JCheckBox chckbxCredito;
	private JTextField textFieldMonto;
	private DefaultTableModel model;
	private JComboBox comboBoxMostrar;
	/**
	 * Create the application.
	 */
	public InterfazFrame(Logica l) {
		logica = l;
		sesion = logica.getSesionActual();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrmBancoEdd(new JFrame());
		getFrmBancoEdd().setTitle("Banco EDD");
		getFrmBancoEdd().setBounds(100, 100, 500, 534);
		getFrmBancoEdd().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrmBancoEdd().getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(130, 130, 130)));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 484, 40);
		getFrmBancoEdd().getContentPane().add(panel);
		panel.setLayout(null);
		
		txtNombreApellido = new JTextField();
		txtNombreApellido.setEditable(false);
		txtNombreApellido.setText(sesion.getNombre() + " " + sesion.getApellido());
		txtNombreApellido.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 15));
		txtNombreApellido.setBounds(10, 11, 210, 20);
		txtNombreApellido.setBorder(null);
		txtNombreApellido.setBackground(new Color(255,255,255));
		panel.add(txtNombreApellido);
		txtNombreApellido.setColumns(10);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int salir = JOptionPane.showConfirmDialog(btnSalir, "¿Desea cerrar sesión?", "Cerrar sesión", 2);
				if (salir == 0) {
					//cierro sesion
					sesion = null;
					//inicio ventana de logIn
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								LogInFrame window = new LogInFrame(logica);
								window.getFrmBancoeddInicio().setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				//Cierro ventana de interfaz
					frmBancoEdd.dispose();
				
				}
			}
		});
		btnSalir.setToolTipText("Cerrar sesión");
		btnSalir.setFont(new Font("Montserrat", Font.PLAIN, 11));
		btnSalir.setBounds(406, 10, 68, 23);
		panel.add(btnSalir);
		
		JPanel ButtonArea = new JPanel();
		ButtonArea.setToolTipText("");
		ButtonArea.setBackground(new Color(245, 245, 245));
		ButtonArea.setBorder(new LineBorder(new Color(192, 192, 192)));
		ButtonArea.setBounds(10, 51, 280, 43);
		getFrmBancoEdd().getContentPane().add(ButtonArea);
		ButtonArea.setLayout(null);
		
		JButton btnDebito = new JButton("Realizar débito");
		btnDebito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//lanzo ventana de debito
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							DebitoFrame window = new DebitoFrame(logica);
							window.getFrmBancoEdd().setVisible(true);
							window.getFrmBancoEdd().addWindowListener(new java.awt.event.WindowAdapter() {
						        @Override
						        public void windowClosed(WindowEvent windowEvent) {
						        	textFieldSaldo.setText(String.valueOf(sesion.getSaldo()));
						        	limpiarTabla();
						        	mostrarTabla(sesion.getHistorial());
						        	comboBoxMostrar.setSelectedIndex(0);
						        }
						    });
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}
				});
				
			}
			
			
		});
		btnDebito.setFont(new Font("Montserrat Medium", Font.PLAIN, 11));
		btnDebito.setToolTipText("Realizar débito");
		btnDebito.setBounds(10, 11, 125, 23);
		ButtonArea.add(btnDebito);
		
		
		
		JButton btnCredito = new JButton("Realizar crédito");
		btnCredito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//lanzo ventana credito
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								CreditoFrame window = new CreditoFrame(logica);
								window.getFrmBancoEdd().setVisible(true);
								window.getFrmBancoEdd().addWindowListener(new java.awt.event.WindowAdapter() {
							        @Override
							        public void windowClosed(WindowEvent windowEvent) {
							        	textFieldSaldo.setText(String.valueOf(sesion.getSaldo()));
							        	limpiarTabla();
							        	mostrarTabla(sesion.getHistorial());
							        	comboBoxMostrar.setSelectedIndex(0);
							        }
							    });
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				
			}
		});
		btnCredito.setFont(new Font("Montserrat Medium", Font.PLAIN, 11));
		btnCredito.setBounds(145, 11, 125, 23);
		ButtonArea.add(btnCredito);
		
		JPanel SaldoArea = new JPanel();
		SaldoArea.setLayout(null);
		SaldoArea.setToolTipText("");
		SaldoArea.setBorder(new LineBorder(new Color(192, 192, 192)));
		SaldoArea.setBackground(new Color(245, 245, 245));
		SaldoArea.setBounds(300, 51, 174, 43);
		getFrmBancoEdd().getContentPane().add(SaldoArea);
		
		textFieldSaldo = new JTextField();
		textFieldSaldo.setBackground(new Color(245, 245, 245));
		textFieldSaldo.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 18));
		textFieldSaldo.setHorizontalAlignment(SwingConstants.TRAILING);
		textFieldSaldo.setText(String.valueOf(sesion.getSaldo()));
		textFieldSaldo.setEditable(false);
		textFieldSaldo.setBounds(44, 0, 130, 43);
		SaldoArea.add(textFieldSaldo);
		textFieldSaldo.setColumns(10);
		
		textFieldSymbol = new JTextField();
		textFieldSymbol.setBackground(new Color(245, 245, 245));
		textFieldSymbol.setFont(new Font("Montserrat Black", Font.PLAIN, 28));
		textFieldSymbol.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldSymbol.setEditable(false);
		textFieldSymbol.setText("$");
		textFieldSymbol.setBounds(6, 1, 35, 41);
		textFieldSymbol.setBorder(null);
		SaldoArea.add(textFieldSymbol);
		textFieldSymbol.setColumns(10);
		
		JPanel HistoryArea = new JPanel();
		HistoryArea.setBackground(new Color(245, 245, 245));
		HistoryArea.setBounds(10, 105, 464, 345);
		HistoryArea.setBorder(new LineBorder(new Color(192, 192, 192)));
		getFrmBancoEdd().getContentPane().add(HistoryArea);
		HistoryArea.setLayout(null);
		
		chckbxDebito = new JCheckBox("Debito");
		chckbxDebito.setFont(new Font("Montserrat Light", Font.PLAIN, 9));
		chckbxDebito.setBackground(new Color(245, 245, 245));
		chckbxDebito.setBounds(211, 11, 57, 23);
		HistoryArea.add(chckbxDebito);
		
		chckbxCredito = new JCheckBox("Credito");
		chckbxCredito.setFont(new Font("Montserrat Light", Font.PLAIN, 9));
		chckbxCredito.setBackground(new Color(245, 245, 245));
		chckbxCredito.setBounds(270, 11, 61, 23);
		HistoryArea.add(chckbxCredito);
		
		
		textFieldDia = new JTextField();
		textFieldDia.setFont(new Font("Montserrat Medium", Font.PLAIN, 11));
		textFieldDia.setToolTipText("Día");
		textFieldDia.setBounds(185, 12, 20, 20);
		HistoryArea.add(textFieldDia);
		textFieldDia.setColumns(10);
		
		textFieldMes = new JTextField();
		textFieldMes.setFont(new Font("Montserrat Medium", Font.PLAIN, 11));
		textFieldMes.setToolTipText("Mes");
		textFieldMes.setColumns(10);
		textFieldMes.setBounds(210, 12, 20, 20);
		HistoryArea.add(textFieldMes);
		
		textFieldAño = new JTextField();
		textFieldAño.setFont(new Font("Montserrat Medium", Font.PLAIN, 11));
		textFieldAño.setToolTipText("Año");
		textFieldAño.setColumns(10);
		textFieldAño.setBounds(235, 12, 35, 20);
		HistoryArea.add(textFieldAño);
		
		textFieldMonto = new JTextField();
		textFieldMonto.setToolTipText("Monto");
		textFieldMonto.setFont(new Font("Montserrat Medium", Font.PLAIN, 11));
		textFieldMonto.setColumns(10);
		textFieldMonto.setBounds(185, 12, 85, 20);
		HistoryArea.add(textFieldMonto);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 444, 289);
		HistoryArea.add(scrollPane);
		
		tableTransacciones = new JTable();
		
		model=new DefaultTableModel();
		tableTransacciones.setModel(model);
		model.addColumn("Monto");
		model.addColumn("Tipo");
		model.addColumn("Emisor");
		model.addColumn("Receptor");
		model.addColumn("Fecha");
		model.addColumn("Hora");
		
		
		
		scrollPane.setViewportView(tableTransacciones);
		
		comboBoxMostrar = new JComboBox();
		comboBoxMostrar.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	if (comboBoxMostrar.getSelectedIndex() == 0) {
		    		textFieldDia.setVisible(false);
		    		textFieldMes.setVisible(false);
		    		textFieldAño.setVisible(false);
		    		textFieldMonto.setVisible(false);
		    		chckbxDebito.setVisible(false);
		    		chckbxCredito.setVisible(false);
		    	}
		    	else if (comboBoxMostrar.getSelectedIndex() == 1 || comboBoxMostrar.getSelectedIndex() == 2)  {
		    		textFieldDia.setVisible(true);
		    		textFieldDia.setToolTipText("N");
		    		textFieldMes.setVisible(false);
		    		textFieldAño.setVisible(false);
		    		textFieldMonto.setVisible(false);
		    		chckbxDebito.setVisible(false);
		    		chckbxCredito.setVisible(false);
		    	}
		    	else if (comboBoxMostrar.getSelectedIndex() == 3) {
		    		textFieldDia.setVisible(true);
		    		textFieldDia.setToolTipText("Día");
		    		textFieldMes.setVisible(true);
		    		textFieldAño.setVisible(true);
		    		textFieldMonto.setVisible(false);
		    		chckbxDebito.setVisible(false);
		    		chckbxCredito.setVisible(false);
		    	}
		    	else if (comboBoxMostrar.getSelectedIndex() == 4) {
		    		textFieldDia.setVisible(true);
		    		textFieldDia.setToolTipText("Monto");
		    		textFieldMes.setVisible(false);
		    		textFieldAño.setVisible(false);
		    		textFieldMonto.setVisible(false);
		    		chckbxDebito.setVisible(true);
		    		chckbxCredito.setVisible(true);
		    	}
		    	else if (comboBoxMostrar.getSelectedIndex() == 5) {
		    		textFieldDia.setVisible(false);
		    		textFieldMes.setVisible(false);
		    		textFieldAño.setVisible(false);
		    		textFieldMonto.setVisible(true);
		    		chckbxDebito.setVisible(false);
		    		chckbxCredito.setVisible(false);
		    	}
		    }
		});
		comboBoxMostrar.setFont(new Font("Montserrat Medium", Font.PLAIN, 11));
		comboBoxMostrar.setModel(new DefaultComboBoxModel(new String[] {"Mostrar todas", "Mostrar últimas N", "Mostrar N mayor Valor", "Mostrar fecha específica", "Mostrar valor superior a N", "Mostrar con valor igual a N"}));
		comboBoxMostrar.setSelectedIndex(0);
		comboBoxMostrar.setBounds(10, 11, 165, 22);
		HistoryArea.add(comboBoxMostrar);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxMostrar.getSelectedIndex() == 0) {
					//Mostrar todas
					Iterable<Transaccion> transacciones = sesion.getHistorial();
					limpiarTabla();
					mostrarTabla(transacciones);
				}
				else if(comboBoxMostrar.getSelectedIndex() == 1) {
					//Mostrar ultimas N
					try {
						Iterable<Transaccion> transacciones = sesion.ultimasN(Integer.parseInt(textFieldDia.getText()));
						limpiarTabla();
						mostrarTabla(transacciones);
					}catch(NumberFormatException e1){
						JOptionPane.showMessageDialog(
								null, "Ingrese una fecha Valida", "Fecha Invalida", 0);
					}
				}
				else if(comboBoxMostrar.getSelectedIndex() == 2) {
					//Mostrar N mayor valor
					try {
						limpiarTabla();
						mostrarTabla(logica.nMayores(Float.parseFloat( textFieldDia.getText())));
					}catch(NumberFormatException e1){
						JOptionPane.showMessageDialog(
								null, "Ingrese una fecha Valida", "Fecha Invalida", 0);
					}
				}

				else if(comboBoxMostrar.getSelectedIndex() == 3) {
					try{
						String fecha = (textFieldDia.getText()+"/"+textFieldMes.getText()+"/"+textFieldAño.getText());
						Iterable<Transaccion> transacciones = sesion.historialDia(fecha);
						limpiarTabla();
						mostrarTabla(transacciones);
					}catch(NumberFormatException e1){
						JOptionPane.showMessageDialog(
								null, "Ingrese una fecha Valida", "Fecha Invalida", 0);
					}
					
				}
				else if(comboBoxMostrar.getSelectedIndex() == 4) {
					//Mostrar Valor Superior a N
					try {
						float monto = Float.parseFloat( textFieldDia.getText());
						Iterable<Transaccion> transacciones = sesion.transaccionesEncimaDe(monto, chckbxDebito.isSelected(), chckbxCredito.isSelected());
						limpiarTabla();
						mostrarTabla(transacciones);
					}catch(NumberFormatException e1){
						JOptionPane.showMessageDialog(
								null, "Ingrese una fecha Valida", "Fecha Invalida", 0);
					}
				}
				
				else if(comboBoxMostrar.getSelectedIndex() == 5) {
					//Mostrar Valor Superior a N
					try {
						float monto = Float.parseFloat( textFieldMonto.getText());
						Iterable<Transaccion> transacciones;
						transacciones = logica.transaccionesValorK(monto);
						limpiarTabla();
						mostrarTabla(transacciones);
					} catch (InvalidKeyException | NumberFormatException e1){
						JOptionPane.showMessageDialog(
								null, "Ingrese un k valido.", "Valor invalido", 0);
					}
					}
			}
		});
		btnConfirmar.setFont(new Font("Montserrat Medium", Font.PLAIN, 11));
		btnConfirmar.setBounds(334, 11, 120, 23);
		HistoryArea.add(btnConfirmar);
		
		
		JButton btnConfirmar_1 = new JButton("Calcular saldo");
		btnConfirmar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				int dia = Integer.parseInt( textFieldDia2.getText());
				int mes = Integer.parseInt( textFieldMes2.getText());
				int año = Integer.parseInt( textFieldAño2.getText());
				
				JOptionPane.showMessageDialog(
						btnConfirmar_1,
						"El saldo el " + dia + "/" + mes + "/" + año + " era de: $" +  sesion.saldoEnFechaEspecifica(dia, mes, año) ,
						"Saldo en día Especifico", 1);
				
				}catch(NumberFormatException e1){
					JOptionPane.showMessageDialog(
							null, "Ingrese una fecha Valida", "Fecha Invalida", 0);
				}
			}
		});
		btnConfirmar_1.setFont(new Font("Montserrat Medium", Font.PLAIN, 11));
		btnConfirmar_1.setBounds(10, 461, 120, 23);
		getFrmBancoEdd().getContentPane().add(btnConfirmar_1);
		
		textFieldDia2 = new JTextField();
		textFieldDia2.setFont(new Font("Montserrat Medium", Font.PLAIN, 11));
		textFieldDia2.setToolTipText("Día");
		textFieldDia2.setColumns(10);
		textFieldDia2.setBounds(140, 462, 20, 20);
		getFrmBancoEdd().getContentPane().add(textFieldDia2);
		
		textFieldMes2 = new JTextField();
		textFieldMes2.setFont(new Font("Montserrat Medium", Font.PLAIN, 11));
		textFieldMes2.setToolTipText("Mes");
		textFieldMes2.setColumns(10);
		textFieldMes2.setBounds(165, 462, 20, 20);
		getFrmBancoEdd().getContentPane().add(textFieldMes2);
		
		textFieldAño2 = new JTextField();
		textFieldAño2.setFont(new Font("Montserrat Medium", Font.PLAIN, 11));
		textFieldAño2.setToolTipText("Año");
		textFieldAño2.setColumns(10);
		textFieldAño2.setBounds(190, 462, 35, 20);
		getFrmBancoEdd().getContentPane().add(textFieldAño2);
		
		mostrarTabla(sesion.getHistorial());
	}
	
	

	public JFrame getFrmBancoEdd() {
		return frmBancoEdd;
	}

	public void setFrmBancoEdd(JFrame frmBancoEdd) {
		this.frmBancoEdd = frmBancoEdd;
	}
	
	private void limpiarTabla() {
		
		while (model.getRowCount() > 0 ) 
			model.removeRow(0);
		}
	
	private void mostrarTabla(Iterable<Transaccion> lista) {
		for(Transaccion t : lista) {
			Object[] fila = new Object[6];
			fila[0] = t.getMonto();
			
			if ( t.getTipo() == 'd')
				fila[1] = "Débito";
			else if (t.getTipo() == 'c')
				fila[1] = "Crédito";
			
			fila[2] = t.getEmisor().getDNI();
			fila[3] = t.getReceptor().getDNI();
			fila[4] = t.getFecha();
			fila[5] = t.getHora();
			
			model.addRow(fila);
		}
	}
	
}
