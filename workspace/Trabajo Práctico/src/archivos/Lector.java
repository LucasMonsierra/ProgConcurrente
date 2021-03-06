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

import jxl.Cell;
import jxl.CellType;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Lector {
	
	private String dirLog;
	private HashMap<String, Matriz> hash;
	private Document html;
	
	public Lector () { 
		dirLog = System.getProperty("user.home") + "//Log//";
		hash = new HashMap<>();
	}
	
	public File elegirHTML() {
		File file = null;
		
		JFileChooser fileChooser = new JFileChooser("C:\\Users\\");
		int select = fileChooser.showSaveDialog(fileChooser);
		
		if (select == JFileChooser.APPROVE_OPTION)
			file = fileChooser.getSelectedFile();

		return file;
	}
	
	public void parsearHTML (File file) {
		try { html = Jsoup.parse(file, "UTF-8"); }
		catch (IOException ex) { Logger.getLogger(Lector.class.getName()).log(Level.SEVERE, null, ex); }
	}
	
	public HashMap<String, Matriz> cargarDatos () {
		//File file = new File ("C:\\Users\\Lucas\\Documents\\GitHub\\ProgConcurrente\\TP CONCURRENTE\\Matrices\\Incidencia.html");
		System.out.print("Seleccionar el archivo HTML que contiene las matrices de Incidencia.\n");
		File file = elegirHTML();
		parsearHTML(file);
		Elements tableElements = html.select("table");
		Elements tableRowElements = tableElements.select(":not(thead) tr");
		
		int incidencia = 0, marcado = 0;
			
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
		obtenerMarcado (tableRowElements, marcado);
		leerInvariantes();
		obtenerTiemposDeTransiciones();
		leerPorcentajes();
		
		return hash;
	}
	
	public void leerInvariantes () {
		//File file = new File ("C:\\Users\\Lucas\\Documents\\GitHub\\ProgConcurrente\\TP CONCURRENTE\\Matrices\\Invariante.html");
		System.out.print("Seleccionar el archivo HTML que contiene los Invariantes.\n");
		File file = elegirHTML();
		parsearHTML(file);
		Elements tableElements = html.select("body");
		Elements tableRowElements = tableElements.select(":not(thead) tr, h3");

		int T = 0, P = 0, sumaP = 0;
		for ( int i = 0 ; i < tableRowElements.size() ; i++ ) {
			switch (tableRowElements.get(i).text()) {
				case "T-Invariants":
					T = i;
					break;
				case "P-Invariants":
					P = i;
					break;
				case "P-Invariant equations":
					sumaP = i;
					break;
			}
		}
		obtenerInvariantes (tableRowElements, T + 2, P, sumaP);
		obtenerEcuaciones (tableElements);
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
	
	private void obtenerMarcado (Elements tableRowElements, int marcado) {
		
		Element row = tableRowElements.get(marcado);
		String[] datos = row.text().split(" ");
		
		Matriz marc = new Matriz (1, datos.length - 1);
		for ( int i = 1 ; i < datos.length ; i++ )
			marc.setValor(0, i - 1, Integer.parseInt(datos[i]));
		
		hash.put("marcado", marc);
	}
	
	private void obtenerInvariantes (Elements tableRowElements, int in, int out, int fin) {
		
		int filas = out - in, columnas = tableRowElements.get(in).text().split(" ").length;
		Matriz matriz = new Matriz (filas, columnas);
		
		for ( int i = 0 ; i < filas ; i++ ) {
			Element row = tableRowElements.get(in);
			String[] datos = row.text().split(" ");
			
			for ( int j = 0 ; j < columnas ; j++ ) 
				matriz.setValor(i, j, Integer.parseInt(datos[j]));
			in++;
		}
		hash.put("tinvariantes", matriz);
		
		out = out + 2;
		filas = fin - out;
		columnas = tableRowElements.get(out).text().split(" ").length;
		matriz = new Matriz (filas, columnas);
		
		for ( int i = 0 ; i < filas ; i++ ) {
			Element row = tableRowElements.get(out);
			String[] datos = row.text().split(" ");
			
			for ( int j = 0 ; j < columnas ; j++ ) 
				matriz.setValor(i, j, Integer.parseInt(datos[j]));
			out++;
		}
		hash.put("pinvariantes", matriz);
	}
	
	private void obtenerEcuaciones (Elements tableElements) {
		
		String texto = tableElements.first().ownText();
		String[] datos = texto.split(" ");
		
		int r = 0;
		Matriz resultados = new Matriz(1,hash.get("pinvariantes").getFilas());
		for (int i = 0 ; i < datos.length ; i++) {
			if (datos[i].matches("\\d*")) {
				resultados.setValor(0, r, Integer.parseInt(datos[i]));
				r++;
			}
		}
		hash.put("pinvaresult", resultados);
	}
	
	private void obtenerTiemposDeTransiciones () {
		//File file = new File ("C:\\Users\\Lucas\\Documents\\GitHub\\ProgConcurrente\\TP CONCURRENTE\\Matrices\\TransConTiempo.xls");
		System.out.print("Seleccionar el archivo XLS que contiene los tiempos de las transiciones.\n");
		File file = elegirHTML();
		
		Workbook wbook = null;
		try { wbook = Workbook.getWorkbook(file); }
		catch (IOException ex) { System.out.print(ex); }
		catch (BiffException ex) { System.out.print(ex); }
		
		Sheet hoja = wbook.getSheet(0);
		int columnas = hoja.getColumns();
		int filas = hoja.getRows();
		
		Matriz alfa = new Matriz(1,filas);
		Matriz beta = new Matriz(1,filas);
				
		for (int i = 0 ; i < filas ; i++) {
			for (int j = 0 ; j < columnas ; j++) {
				Cell celda = hoja.getCell(j,i);
				if (celda.getType() == CellType.NUMBER) {
					NumberCell nc = (NumberCell) celda;
					if (j == 0) { alfa.setValor(0, i, (int) nc.getValue()); }
					else { beta.setValor(0, i, (int) nc.getValue()); }
				}
			}
		}
		hash.put("alfa", alfa);
		hash.put("beta", beta);
	}
	
	public Matriz leerLog (String archivoLog) throws Exception {
		
		Matriz matriz;
		
		File inFile = new File (dirLog + archivoLog);
		Scanner in = new Scanner (inFile);
		
		String[] length = in.nextLine().trim().split("\\s+");
		
		int columnas;
		for ( columnas = 0 ; columnas < length.length ; columnas++ );
		
		int filas;
		for ( filas = 1 ; in.hasNext() ; filas++) { in.nextLine(); }
		
		in.close();
		
		matriz = new Matriz(filas, columnas);
		in = new Scanner(inFile);
		
		for ( int j = 0 ; j < filas ; j++ ) {
		//while (in.hasNextLine()) {
			String[] currentLine = in.nextLine().trim().split("\\s+");
			for ( int i = 0 ; i < currentLine.length ; i++ ) {
				matriz.setValor(j, i, Integer.parseInt(currentLine[i]));
			}
		}
		in.close();
		return matriz;
	}
	
	public void leerPorcentajes () {
		
		Matriz matriz = new Matriz(1, 3);
		/*
		matriz.setValor(0, 0, 25);
		matriz.setValor(0, 1, 50);
		matriz.setValor(0, 2, 25);
		*/
		Scanner input = new Scanner(System.in);
		System.out.print("Ingrese el % de piezas A a fabricar: \n");
		matriz.setValor(0, 0, input.nextInt());
		System.out.print("Ingrese el % de piezas B a fabricar: \n");
		matriz.setValor(0, 1, input.nextInt());
		System.out.print("Ingrese el % de piezas C a fabricar: \n");
		matriz.setValor(0, 2, input.nextInt());
		input.close();
		
		hash.put("porcentajes", matriz);
	}
}
