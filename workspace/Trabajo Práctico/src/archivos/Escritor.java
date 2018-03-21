package archivos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;

import com.google.common.base.Objects;

public class Escritor {
	
	private String sUbicacion = System.getProperty("user.home") + "\\Log\\";
	private String log = "";
	private boolean flag = true;
	
	public Escritor() {
		File file = new File(sUbicacion);
		try { FileUtils.cleanDirectory(file); }
		catch (IOException e) { e.printStackTrace(); }
		
		try { Log logger = new Log(); }
		catch (SecurityException | IOException e) {
			e.printStackTrace();
            throw new RuntimeException("Problems with creating the log files");
		}
	}
	
	
	// GUARDA EN UN STRING EL LOS DISTINTOS LOG DE EJECUCIÓN DEL TEST DE INVARIANTE DE PLAZAS
	public void guardar(String texto) {
		if (!Objects.equal(null, texto)) { log = log.concat(texto + "\r\n"); }
	}
	
	public void generarLogs(int a, int b, int c) {
		if (flag) {
			imprimir(getPiezasHechas(a, b, c), "Piezas");
			imprimir(log, "TestLog");
			log = ""; flag = false;
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
	public String getlog () {
		return log;
	}
	
	public String getPiezasHechas (int a, int b, int c) {
		return "Piezas A: " + a + " Piezas B: " + b + " Piezas C: " + c;
	}
}
