package Gestor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PoliticaRdP {
	public static int PLAZAS;
	public static int TRANS;
	private int transRDP = RdP.TRANS;
	
	private int[] MarcadoPolitica;
	private int[][] InsPolitica;
	private int politica;
	
	public PoliticaRdP () throws NumberFormatException, IOException {
		PLAZAS = 9;
		TRANS = 7;
		
		MarcadoPolitica  = new int[PLAZAS];
		InsPolitica  = new int[PLAZAS][TRANS];
		
		politica = 1;
		
		levantarMatriz(politica);
		levantarMarcado(politica);
	}
	
	public boolean disparar(int t) {
		if (!estaSencib(t))
			return false;
		
		else {
			nuevoEstado(t);
			if (estaSencib(0)){
				nuevoEstado(0);
			}
			return true;
		}
	}
	
	private void nuevoEstado(int t) {
		for (int i = 0 ; i < PLAZAS ; i++)
			MarcadoPolitica[i] = MarcadoPolitica[i] + InsPolitica[i][t];
	}
	
	boolean estaSencib(int t) {
		int[] v = getSencibilizadas();
		
		if (v[t] == 1)
			return true;
		else
			return false;
	}

	private int[] getSencibilizadas() {
		int[] sencib = new int[TRANS];
		int f;
		
		for (int i = 0; i < TRANS ; i++) {
			f = 0;
			for (int j = 0 ; j < PLAZAS ; j++) {
				if(MarcadoPolitica[j] + InsPolitica[j][i] < 0)
					f++;
				else if (MarcadoPolitica[j] + InsPolitica[j][i] > 0)
					sencib[i] = 1;
			}
			if (f > 0)
				sencib[i] = 0;
		}
		return sencib;
	}
	
	public int[] getPoliticaRdP() {
		int[] p = new int[transRDP];
		
		for (int i = 0 ; i < p.length ; i++) {
			if (chequeoPolRdp(i))
				p[i] = 1;
			else
				p[i] = 0;
		}
		
		return p;
	}
	
	public boolean chequeoPolRdp(int i) {
		switch (i) {
			case 0: 
				if (!estaSencib(1))
					return false;
				break;
			case 9:
				if (!estaSencib(6))
					return false;
				break;
			case 10:
				if (!estaSencib(2))
					return false;
				break; 
			case 13:
				if (!estaSencib(5))
					return false;
				break;
			case 14:
				if (!estaSencib(3))
					return false;
				break;
			case 19:
				if (!estaSencib(4))
					return false;
				break; 
		}
		return true;
	}
	
	public void disparoPolRdp(int i) {
		switch (i) {
			case 0: 
				disparar(1);
				break;
			case 9:
				disparar(6);
				break;
			case 10:
				disparar(2);
				break; 
			case 13:
				disparar(5);
				break;
			case 14:
				disparar(3);
				break;
			case 19:
				disparar(4);
				break; 
		}
	}

	public void levantarMatriz (int p) throws NumberFormatException, IOException {
		int i = 0, j = 0;
		String cadena;
		
        FileReader f = new FileReader("/Users/lucas/Desktop/Politica" + p + ".txt");
        
        BufferedReader b = new BufferedReader(f);
        
        while((cadena = b.readLine()) != null) {
        	for (String valor: cadena.split(" ")) {
        		InsPolitica[i][j] = Integer.parseInt(valor);
        		j++;
        	}
        	i++;
        	j = 0; 
        }
        b.close();
	}
	
	public void levantarMarcado (int p) throws NumberFormatException, IOException {
		int i = 0;
		String cadena;
		
        FileReader f = new FileReader("/Users/lucas/Desktop/MarcadoP" + p + ".txt");
        
        BufferedReader b = new BufferedReader(f);
        
        while((cadena = b.readLine()) != null) {
        	for (String valor: cadena.split(" ")) {
        		MarcadoPolitica[i] = Integer.parseInt(valor);
        		i++;
        	}
        }
        b.close();
	}
}
