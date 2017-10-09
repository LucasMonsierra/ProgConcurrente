package archivos;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Lector {
	
	private String dirLog;
	private HashMap<String, Matriz> hash;
	private Document html;
	
	public Lector () { 
		dirLog = System.getProperty("user.home") + "//Log//";
		hash = new HashMap<>();
	}
	
	public HashMap<String, Matriz> leerHTML () {
		
		File file = null;
		/*JFileChooser fileChooser = new JFileChooser();
		int select = fileChooser.showSaveDialog(fileChooser);
		
		if (select == JFileChooser.APPROVE_OPTION)
			file = fileChooser.getSelectedFile();*/
		
		//CAMBIAR POR EL FILECHOOSER CUANDO EST� LISTO
		file = new File ("C:\\Users\\Lucas\\Documents\\GitHub\\ProgConcurrente\\TP CONCURRENTE\\Matrices\\Incidencia.html");
		
		try {
			int incidencia = 0, marcado;
			html = Jsoup.parse(file, "UTF-8");
			Elements tableElements = html.select("table");
			Elements tableRowElements = tableElements.select(":not(thead) tr");
			
			for ( int i = 0 ; i < tableRowElements.size() ; i++ ) {
				
				Element row = tableRowElements.get(i);
				Elements rowItems = row.select("td");
				
				for ( int j = 0 ; j < rowItems.size() ; j++ ) {
					switch (rowItems.get(j).text()) {
					case "Combined incidence matrix I":
						incidencia = i;
						break;
					case "Initial":
						marcado = i;
						break;
					}
				}
			}
			
			obtenerMatrizIncidencia (tableRowElements, incidencia);
		} catch (IOException ex) {
			Logger.getLogger(Lector.class.getName()).log(Level.SEVERE, null, ex);
		}
		return hash;
	}
	
	private void obtenerMatrizIncidencia (Elements tableRowElements, int incidencia) {
		
		Element row = tableRowElements.get(incidencia + 1);
		String[] datos = row.text().split(" ");
		
		int columnas = 0, filas = 0;
		for ( int i = 0 ; i < datos.length ; i++ ) {
			if ( datos[i].contains("T"))
				columnas++;
			else if (datos[i].contains("P") || datos[i].contains("m") || datos[i].contains("x") || datos[i].contains("r"))
				filas++;
		}
		
		int f = -1;
		Matriz matriz = new Matriz (filas, columnas);
		for ( int i = 0 ; i < datos.length ; i++ ) {
			if (datos[i].contains("P") || datos[i].contains("m") || datos[i].contains("x") || datos[i].contains("r")) {
				f++;
				for ( int j = 0 ; j < columnas ; j++ ) {
					matriz.setValor(f, j, Integer.parseInt(datos[i + 1 + j]));
				}
			}
		}
		hash.put("incidencia", matriz);
	}
	
	public int[][] leerLog(String archivoLog) throws Exception {
		
		int[][] matriz;
		
		File inFile = new File (dirLog + archivoLog);
		Scanner in = new Scanner (inFile);
		
		String[] length = in.nextLine().trim().split("\\s+");
		
		int columnas;
		for ( columnas = 0 ; columnas < length.length ; columnas++ );
		
		int filas;
		for ( filas = 1 ; in.hasNext() ; filas++) { in.nextLine(); }
		
		in.close();
		
		matriz = new int[filas][columnas];
		in = new Scanner(inFile);
		
		for ( int j = 0 ; j < filas ; j++ ) {
		//while (in.hasNextLine()) {
			String[] currentLine = in.nextLine().trim().split("\\s+");
			for ( int i = 0 ; i < currentLine.length ; i ++ ) {
				matriz[j][i] = Integer.parseInt(currentLine[i]);
			}
		}
		in.close();
		return matriz;
	}
}
