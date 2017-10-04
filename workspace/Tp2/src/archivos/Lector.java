package archivos;

import java.io.File;
import java.util.Scanner;

public class Lector {
	
	private String dirLog = System.getProperty("user.home") + "//Log//";

	
	
	
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
