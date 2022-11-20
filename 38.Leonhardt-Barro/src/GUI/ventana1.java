package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Exceptions.InvalidContraseniaException;
import Exceptions.InvalidMontoException;
import Exceptions.InvalidNException;
import Programa.CuentaBancaria;
import Programa.Transaccion;
import TDALista.PositionList;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class ventana1 extends JFrame {

	private JPanel contentPane;
    private CuentaBancaria usuario;
    private JTextField NMismoValor;
    private JTextField MontoTransferir;
    private JTextField DNITransferir;
    private JTextField MesTransferir;
    private JTextField DiaTransferir;
    private JTextField HoraTransferir;
    private JTextField UltimasNTran;
    private JTextField NTransaccionesMasCaras;
    private JTextField NombreCredito;
    private JTextField ApellidoCredito;
    private JTextField MontoCredito;
    private JTextField DiaCredito;
    private JTextField MesCredito;
    private JTextField HoraCredito;
    private JTextField TransaccionesFechaDIA;
    private JTextField TransaccionesFechaMes;
    private JTextField SuperenAn;
    private JTextField SaldoEnFechaDia;
    private JTextField SaldoEnFechaMes;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventana1 frame = new ventana1(null, null, null, 0, 0);
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
	public ventana1(String nombre,String apellido,String contraseña,int dni,int deposito) throws InvalidContraseniaException  {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1064, 478);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
			if(deposito<=0) {
			usuario = new CuentaBancaria(nombre,apellido,contraseña,dni);
			}else usuario = new CuentaBancaria(nombre,apellido,contraseña,dni,deposito);
		
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(538, 175, 477, 235);
		contentPane.add(textArea);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre: "+usuario.getNombre());
		lblNewLabel_1.setBounds(10, 25, 131, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Apellido "+usuario.getApellido());
		lblNewLabel_2.setBounds(10, 51, 142, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Saldo: "+usuario.getMonto());
		lblNewLabel_3.setBounds(171, 25, 71, 13);
		contentPane.add(lblNewLabel_3);
		
		JButton MismoValor = new JButton("Mismo Valor que:");
		MismoValor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			Iterator<Transaccion> it = usuario.MismoValor(Integer.parseInt(NMismoValor.getText())).iterator();
			String cad ="Transaccion con valor = "+Integer.parseInt(NMismoValor.getText())+" :\n";
			Transaccion aux;
			while(it.hasNext()) {
		    	aux=it.next(); 
				cad += aux.toString() + ":\n";
		     }
			textArea.setText(cad);
		  }
		});
		MismoValor.setBounds(10, 410, 194, 21);
		contentPane.add(MismoValor);
		
		NMismoValor = new JTextField();
		NMismoValor.setBounds(230, 411, 96, 19);
		contentPane.add(NMismoValor);
		NMismoValor.setColumns(10);
		
		JButton btnNewButton = new JButton("Transferir Dinero");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					usuario.transferencias(Integer.parseInt(MontoTransferir.getText()),Integer.parseInt(DNITransferir.getText()),Integer.parseInt(MesTransferir.getText()),Integer.parseInt(DiaTransferir.getText()),Integer.parseInt(HoraTransferir.getText()));
					lblNewLabel_3.setText("Saldo: "+usuario.getMonto());			
				} catch (InvalidMontoException e1) {
					e1.printStackTrace();
					textArea.setText("Saldo insuficiente");
				}
			}
		});
		btnNewButton.setBounds(10, 91, 161, 21);
		contentPane.add(btnNewButton);
		
		MontoTransferir = new JTextField();
		MontoTransferir.setBounds(230, 122, 96, 19);
		contentPane.add(MontoTransferir);
		MontoTransferir.setColumns(10);
		
		DNITransferir = new JTextField();
		DNITransferir.setText("");
		DNITransferir.setBounds(75, 122, 96, 19);
		contentPane.add(DNITransferir);
		DNITransferir.setColumns(10);
		
		MesTransferir = new JTextField();
		MesTransferir.setBounds(75, 165, 96, 19);
		contentPane.add(MesTransferir);
		MesTransferir.setColumns(10);
		
		DiaTransferir = new JTextField();
		DiaTransferir.setBounds(75, 145, 96, 19);
		contentPane.add(DiaTransferir);
		DiaTransferir.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Monto:");
		lblNewLabel.setBounds(181, 125, 45, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_4 = new JLabel("Destinatario:");
		lblNewLabel_4.setBounds(10, 122, 71, 13);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Dia:");
		lblNewLabel_5.setBounds(36, 151, 45, 13);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Mes :");
		lblNewLabel_6.setBounds(36, 168, 45, 13);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Hora:");
		lblNewLabel_7.setBounds(181, 148, 45, 13);
		contentPane.add(lblNewLabel_7);
		
		HoraTransferir = new JTextField();
		HoraTransferir.setBounds(230, 145, 96, 19);
		contentPane.add(HoraTransferir);
		HoraTransferir.setColumns(10);
		
		UltimasNTran = new JTextField();
		UltimasNTran.setBounds(230, 381, 96, 19);
		contentPane.add(UltimasNTran);
		UltimasNTran.setColumns(10);
		
		JButton UltimosNTransacciones = new JButton("Ultimas N Transacciones :");
		UltimosNTransacciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Iterator<Transaccion> it;
				if(Integer.parseInt(UltimasNTran.getText())<=usuario.getTransacciones().size()) {
				try {
					it = usuario.UltimosN(Integer.parseInt(UltimasNTran.getText())).iterator();
					String cad ="Las "+ Integer.parseInt(UltimasNTran.getText())+" ultimas Transacciones  :\n";
					Transaccion aux;
					while(it.hasNext()) {
				    	aux=it.next(); 
						cad += aux.toString() + ":\n";
				     }
					textArea.setText(cad);
				} catch (NumberFormatException | InvalidNException e1) {
					e1.printStackTrace();
				}
			  }else {textArea.setText("No existen "+Integer.parseInt(UltimasNTran.getText())+" Transacciones");
			       }
				}
		});
		UltimosNTransacciones.setBounds(10, 380, 194, 21);
		contentPane.add(UltimosNTransacciones);
		
		NTransaccionesMasCaras = new JTextField();
		NTransaccionesMasCaras.setBounds(230, 352, 96, 19);
		contentPane.add(NTransaccionesMasCaras);
		NTransaccionesMasCaras.setColumns(10);
		
		JButton NMasCaras = new JButton("N Trasacciones mas caras :");
		NMasCaras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Iterator<Transaccion> it;
				if(Integer.parseInt(NTransaccionesMasCaras.getText())<=usuario.getTransacciones().size()) {
				it = usuario.nMasValor(Integer.parseInt(NTransaccionesMasCaras.getText())).iterator();
					String cad ="Las "+ Integer.parseInt(NTransaccionesMasCaras.getText())+" trasacciones mas caras  :\n";
					Transaccion aux;
					while(it.hasNext()) {
				    	aux=it.next(); 
						cad += aux.toString() + ":\n";
				     }
					textArea.setText(cad);
				 }else {textArea.setText("No existen "+Integer.parseInt(NTransaccionesMasCaras.getText())+" Transacciones");
			       }
			  }
		});
		NMasCaras.setBounds(10, 351, 194, 21);
		contentPane.add(NMasCaras);
		
		NombreCredito = new JTextField();
		NombreCredito.setBounds(550, 22, 96, 19);
		contentPane.add(NombreCredito);
		NombreCredito.setColumns(10);
		
		ApellidoCredito = new JTextField();
		ApellidoCredito.setBounds(550, 48, 96, 19);
		contentPane.add(ApellidoCredito);
		ApellidoCredito.setColumns(10);
		
		MontoCredito = new JTextField();
		MontoCredito.setBounds(550, 72, 96, 19);
		contentPane.add(MontoCredito);
		MontoCredito.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Nombre depositante :");
		lblNewLabel_8.setBounds(429, 25, 123, 13);
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Apellido depositante:");
		lblNewLabel_9.setBounds(429, 51, 123, 13);
		contentPane.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Monto depositante :");
		lblNewLabel_10.setBounds(429, 75, 123, 13);
		contentPane.add(lblNewLabel_10);
		
		DiaCredito = new JTextField();
		DiaCredito.setBounds(698, 22, 96, 19);
		contentPane.add(DiaCredito);
		DiaCredito.setColumns(10);
		
		MesCredito = new JTextField();
		MesCredito.setBounds(698, 48, 96, 19);
		contentPane.add(MesCredito);
		MesCredito.setColumns(10);
		
		HoraCredito = new JTextField();
		HoraCredito.setBounds(698, 77, 96, 19);
		contentPane.add(HoraCredito);
		HoraCredito.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("Dia :");
		lblNewLabel_11.setBounds(656, 25, 45, 13);
		contentPane.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("Mes :");
		lblNewLabel_12.setBounds(656, 51, 45, 13);
		contentPane.add(lblNewLabel_12);
		
		JLabel lblNewLabel_13 = new JLabel("Hora:");
		lblNewLabel_13.setBounds(656, 80, 45, 13);
		contentPane.add(lblNewLabel_13);
		
		JButton btnNewButton_1 = new JButton("Acreditar dinero");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usuario.depositos(NombreCredito.getText(), ApellidoCredito.getText(), Integer.parseInt(MontoCredito.getText()),Integer.parseInt(MesCredito.getText()),Integer.parseInt(DiaCredito.getText()),Integer.parseInt(HoraCredito.getText()));
				lblNewLabel_3.setText("Saldo: "+usuario.getMonto());	
			}
		});
		btnNewButton_1.setBounds(294, 47, 131, 21);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_14 = new JLabel("Dia :");
		lblNewLabel_14.setBounds(235, 209, 45, 13);
		contentPane.add(lblNewLabel_14);
		
		JLabel lblNewLabel_15 = new JLabel("Mes :");
		lblNewLabel_15.setBounds(372, 209, 45, 13);
		contentPane.add(lblNewLabel_15);
		
		TransaccionesFechaDIA = new JTextField();
		TransaccionesFechaDIA.setBounds(266, 206, 96, 19);
		contentPane.add(TransaccionesFechaDIA);
		TransaccionesFechaDIA.setColumns(10);
		
		TransaccionesFechaMes = new JTextField();
		TransaccionesFechaMes.setBounds(404, 206, 96, 19);
		contentPane.add(TransaccionesFechaMes);
		TransaccionesFechaMes.setColumns(10);
		
		JButton TransaccionesEnFecha = new JButton("Transacciones en fecha :");
		TransaccionesEnFecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Iterator<Transaccion> it;
				it = usuario.TransaccionesFecha(Integer.parseInt(TransaccionesFechaDIA.getText()), Integer.parseInt(TransaccionesFechaMes.getText())).iterator();
					String cad ="Las trasacciones realizadas el dia :"+Integer.parseInt(TransaccionesFechaDIA.getText())+"/"+Integer.parseInt(TransaccionesFechaMes.getText())+ "son :\n";
					Transaccion aux;
					while(it.hasNext()) {
				    	aux=it.next(); 
						cad += aux.toString() + ":\n";
				     }
					textArea.setText(cad);
			}
		});
		TransaccionesEnFecha.setBounds(10, 205, 215, 21);
		contentPane.add(TransaccionesEnFecha);
		
		JLabel lblNewLabel_16 = new JLabel("Transacciones que superen a :");
		lblNewLabel_16.setBounds(10, 255, 161, 13);
		contentPane.add(lblNewLabel_16);
		
		SuperenAn = new JTextField();
		SuperenAn.setBounds(157, 296, 96, 19);
		contentPane.add(SuperenAn);
		SuperenAn.setColumns(10);
		
		JButton SuperenAnDebito = new JButton("Debito");
		SuperenAnDebito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Iterator<Transaccion> it;
				it = usuario.TransaccionesSuperenN('D', Integer.parseInt(SuperenAn.getText())).iterator();
					String cad ="Las trasacciones de debito que superan  :"+ Integer.parseInt(SuperenAn.getText())+ "son :\n";
					Transaccion aux;
					while(it.hasNext()) {
				    	aux=it.next(); 
						cad += aux.toString() + ":\n";
				     }
					textArea.setText(cad);
			}
		});
		SuperenAnDebito.setBounds(20, 271, 85, 21);
		contentPane.add(SuperenAnDebito);
		
		JButton SuperenAnCredito = new JButton("Credito");
		SuperenAnCredito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Iterator<Transaccion> it;
				it = usuario.TransaccionesSuperenN('C', Integer.parseInt(SuperenAn.getText())).iterator();
					String cad ="Las trasacciones de credito que superan  :"+ Integer.parseInt(SuperenAn.getText())+ "son :\n";
					Transaccion aux;
					while(it.hasNext()) {
				    	aux=it.next(); 
						cad += aux.toString() + ":\n";
				     }
					textArea.setText(cad);
			}
			
		});
		SuperenAnCredito.setBounds(20, 320, 85, 21);
		contentPane.add(SuperenAnCredito);
		
		JButton SuperanAnAmbas = new JButton("Ambas");
		SuperanAnAmbas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Iterator<Transaccion> it;
				it = usuario.TransaccionesSuperenN('a', Integer.parseInt(SuperenAn.getText())).iterator();
					String cad ="Las trasacciones que superan  :"+ Integer.parseInt(SuperenAn.getText())+ "son :\n";
					Transaccion aux;
					while(it.hasNext()) {
				    	aux=it.next(); 
						cad += aux.toString() + ":\n";
				     }
					textArea.setText(cad);
			}	
	
		});
		SuperanAnAmbas.setBounds(20, 295, 85, 21);
		contentPane.add(SuperanAnAmbas);
		
		JLabel lblNewLabel_17 = new JLabel("Valor :");
		lblNewLabel_17.setBounds(126, 299, 45, 13);
		contentPane.add(lblNewLabel_17);
		
		JLabel lblNewLabel_18 = new JLabel("Dia:");
		lblNewLabel_18.setBounds(429, 355, 45, 13);
		contentPane.add(lblNewLabel_18);
		
		JLabel lblNewLabel_19 = new JLabel("Mes:");
		lblNewLabel_19.setBounds(429, 384, 45, 13);
		contentPane.add(lblNewLabel_19);
		
		JButton SaldoEnFecha = new JButton("Saldo en el dia y mes :");
		SaldoEnFecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int saldo =usuario.SaldoEnFecha(Integer.parseInt(SaldoEnFechaMes.getText()),Integer.parseInt(SaldoEnFechaDia.getText()));
				textArea.setText("El saldo que tenias en la fecha :"+Integer.parseInt(SaldoEnFechaMes.getText())+"/"+Integer.parseInt(SaldoEnFechaDia.getText())+"es igual a = "+saldo);
			}
		});
		SaldoEnFecha.setBounds(360, 320, 174, 21);
		contentPane.add(SaldoEnFecha);
		
		SaldoEnFechaDia = new JTextField();
		SaldoEnFechaDia.setBounds(457, 352, 71, 19);
		contentPane.add(SaldoEnFechaDia);
		SaldoEnFechaDia.setColumns(10);
		
		SaldoEnFechaMes = new JTextField();
		SaldoEnFechaMes.setBounds(457, 381, 71, 19);
		contentPane.add(SaldoEnFechaMes);
		SaldoEnFechaMes.setColumns(10);
		
	}
}

