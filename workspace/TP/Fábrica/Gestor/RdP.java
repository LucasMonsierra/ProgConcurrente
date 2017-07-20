package Gestor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RdP {
	public static int PLAZAS;
	public static int TRANS;
	
	private int[] Marcado;
	private int[][] Ins;
	
	public RdP () throws NumberFormatException, IOException {
		PLAZAS = 29;
		TRANS = 20;
		
		Marcado  = new int[PLAZAS];
		Ins  = new int[PLAZAS][TRANS];
		
		levantarMatriz();
		levantarMarcado();
	}
	
	public boolean disparar (int t) {
		if (estaSencib(t)) {
			nuevoEstado(t);
			return true;
		}
		else 
			return false;
	}
	
	public void nuevoEstado (int t) {
		for (int i = 0 ; i < PLAZAS ; i++)
			Marcado[i] = Marcado[i] + Ins[i][t];
	}
	
	public boolean estaSencib (int t) {
		int[] v = getSencibilizadas();
		
		if (v[t] == 1)
			return true;
		else
			return false;
	}
	
	public int[] getSencibilizadas () {
		int[] sencib = new int[TRANS];
		int f;
		
		for (int i = 0; i < TRANS ; i++) {
			f = 0;
			for (int j = 0 ; j < PLAZAS ; j++) {
				if(Marcado[j] + Ins[j][i] < 0)
					f++;
				else if (Marcado[j] + Ins[j][i] > 0)
					sencib[i] = 1;
			}
			if (f > 0)
				sencib[i] = 0;
		}
		return sencib;
	}
	
	public void levantarMatriz () throws NumberFormatException, IOException {
		int i = 0, j = 0;
		String cadena;
		
        FileReader f = new FileReader("/Users/lucas/Desktop/Matriz.txt");
        
        BufferedReader b = new BufferedReader(f);
        
        while((cadena = b.readLine()) != null) {
        	for (String valor: cadena.split(" ")) {
        		Ins[i][j] = Integer.parseInt(valor);
        		j++;
        	}
        	i++;
        	j = 0; 
        }
        b.close();
	}
	
	public void levantarMarcado () throws NumberFormatException, IOException {
		int i = 0;
		String cadena;
		
        FileReader f = new FileReader("/Users/lucas/Desktop/Marcado.txt");
        
        BufferedReader b = new BufferedReader(f);
        
        while((cadena = b.readLine()) != null) {
        	for (String valor: cadena.split(" ")) {
        		Marcado[i] = Integer.parseInt(valor);
        		i++;
        	}
        }
        b.close();
	}
}
