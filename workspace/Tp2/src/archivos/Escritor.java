package archivos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;

public class Escritor {
	
	private static Escritor esc = null;
	private String sUbicacion = System.getProperty("user.home") + "\\Log\\";
	public String marcados = "";
	public int a = 0;
	public int b = 0;
	public int c = 0;
	
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
	
	// GUARDA EN UN STRING EL LOS DISTINTOS MARCADOS LIMPIOS DE CARACTERES NO DESEADOS
	public void guardar(String texto) {
		marcados = marcados.concat(texto.trim() + "\r\n");
	}
	
	// GUARDA EN CADA ENTERO LA CANTIDAD DE PIEZAS QUE SE HIZO DE CADA UNA
	public void guardarPiezasHechas(int t) {
		switch (t) {
		case 9:
			a++; break;
		case 13:
			b++; break;
		case 19:
			c++; break;
		}
	}
	
	// GUARDA EN UN ARCHIVO EL STRING QUE TIENE TODOS LOS MARCADOS SIN CARACTERES ESPECIALES
	public void imprimir(String texto, String nombre) {
		File fichero = new File(sUbicacion + nombre);
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(fichero, true));
			bw.write(texto);
			bw.newLine();
			bw.close();
		} catch (IOException ex) {
			System.out.println(ex);
			JOptionPane.showMessageDialog(null, ex, "Log: ", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	//RETORNA EL STRING QUE CONTIENE TODOS LOS MARCADOS
	public String getMarcados () {
		return marcados;
	}
	
	public String getPiezasHechas () {
		return "Piezas A: " + a + " Piezas B: " + b + " Piezas C: " + c;
	}
}
