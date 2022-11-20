package Programa;

import java.awt.EventQueue;


import GUI.LogInFrame;
import TDALista.ListaDoblementeEnlazada;

/**
 * Clase Main
 * @author Arroyo Tomas (126078) - Fernandez Maximo (131672)
 */

public class Main {

	/**
	 * Lanza la aplicacion
	 * @param args lanza la aplicacion
	 */
	public static void main(String[] args) {
		Logica logica = new Logica();
		
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
		
		
		
	}

}
