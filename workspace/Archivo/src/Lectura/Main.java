package Lectura;
import java.io.BufferedReader;
import java.util.Arrays;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	
	public static final int f = 30;
	public static final int c = 20;

	public static void main(String[] args) throws FileNotFoundException, IOException {
	
		int[] M = new int[] {1, 2, 3, 4};
		
		System.out.print(Arrays.toString(M));
		
		
		
		
		/*String sUbicacion = System.getProperty("user.home") + "\\log\\";
		
		System.out.print(sUbicacion);*/
	}
		/*
		
		int[][] I = new int[f][c];
		int[] M = new int[f];
		int i = 0, j = 0;
		String cadena;
		
        FileReader f = new FileReader("/Users/lucas/Desktop/Matriz.txt");
        
        BufferedReader b = new BufferedReader(f);
        
        while((cadena = b.readLine())!=null) {
        	for (String valor: cadena.split(" ")) {
        		I[i][j] = Integer.parseInt(valor);
        		j++;
        	}
        	i++;
        	j = 0; 
        }
        b.close();
        for (int n = 0 ; n < I.length ; n++) {
        	for (int m = 0 ; m < I[0].length ; m++) {
        		System.out.print(I[n][m] + " ");
        	}
        	System.out.print("\n");        	
        }
        i = 0;
        FileReader f1 = new FileReader("/Users/lucas/Desktop/Marcado.txt");
        BufferedReader b1 = new BufferedReader(f1);
        
        while((cadena = b1.readLine()) != null) {
        	for(String valor: cadena.split(" ")) {
        		M[i] = Integer.parseInt(valor);
        		i++;
        	}
        }
        b1.close();
        for (int m = 0 ; m < M.length ; m++) {
    		System.out.print(M[m] + " ");
    	}
        int[] a = new int[10];
        for (int x = 0 ; x < 10 ; x++)
        	System.out.print(a[x]);
	}*/
}