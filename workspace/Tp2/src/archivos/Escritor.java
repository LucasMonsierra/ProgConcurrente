package archivos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;

public class Escritor {
	
	private static Escritor esc = null;
	//private String sArchivo = "System.txt";
	private String sUbicacion = System.getProperty("user.home") + "\\Log\\";
	
	private Escritor() {
		File file = new File(sUbicacion);
		try {
			FileUtils.cleanDirectory(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static synchronized Escritor getInstance() {
		if (esc == null) {
			esc = new Escritor();
		}
		return esc;
	}
	
	// GUARDA EN UN ARCHIVO CON EL NOMBRE QUE SE LE PASA EL TEXTO QUE SE DESEE
	public void imprimir(String sArchivo, String sTexto) {
		File fichero = new File(sUbicacion + sArchivo + ".txt");
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(fichero, true));
			bw.write(sTexto);
			bw.newLine();
			bw.close();
		} catch (IOException ex) {
			System.out.println(ex);
			JOptionPane.showMessageDialog(null, ex, "Log: ", JOptionPane.ERROR_MESSAGE);
		}
	}
}