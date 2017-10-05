package monitor;

import archivos.Escritor;
import java.util.Arrays;

public class RdP {
	
	static int TRANS;
	private int PLAZAS;
	private int[] Marcado;
	private int[][] MatrizI;
	
	public RdP() {
		
		Marcado = new int[] {1, 1, 1, 1, 6, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 2, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1};
		MatrizI = new int[][] { {0, -1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, -1, 0, 1, 0, 0, 0, -1, 1, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, -1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 1, 0},
								{0, 0, 0, 0, 0, 0, -1, 0, 1, 0, 0, 0, 0, 0, 0, -1, 1, 0, 0, 0},
								{-1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{1, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 1, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 1, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 1, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 1, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 1, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 1, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 1, 1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, -1, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, -1, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, -1, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 1},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, -1, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, -1, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, -1, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, -1, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, -1},
								{-1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 1},
								{0, 0, 0, -1, -1, 1, 1, 0, 0, 0, -1, 1, -1, 1, 0, 0, -1, 1, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, -1, -1, 1, 0, 0, 0, 0, -1, 1, 0, 0, 0, 0},
								{0, 0, 0, -1, 0, 1, 0, 0, 0, 0, 0, -1, 1, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, -1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, -1, 0, 0, 0, 1, 0} };
								
		PLAZAS = (Marcado.length);
		TRANS = (MatrizI[0].length);
	}
	
	public int[] getSencib() {
		int f;
		int[] sencib = new int[TRANS];
		
		for (int i = 0 ; i < TRANS ; i++) {
			f = 0;
			for (int j = 0 ; j < PLAZAS ; j++) {
				if (Marcado[j] + MatrizI[j][i] > 0) { sencib[i] = 1; }
				else if (Marcado[j] + MatrizI[j][i] < 0) { f++; }
			}
			if (f > 0) { sencib[i] = 0; }
		}
		return sencib;		
	}
	
	public boolean isSencib(int t) {
		int[] v = getSencib();
		
		if (v[t] == 1) { return true; }
		else { return false; }
	}
	
	public void newState(int t) {
		for (int i = 0 ; i < PLAZAS ; i++) {
			Marcado[i] = Marcado[i] + MatrizI[i][t];
		}
	}
	
	public boolean shoot(int t) {
		if (isSencib(t)) { newState(t); imprimirMarcado(); return true; }
		else { imprimirMarcado(); return false; }
	}
	
	public void imprimirMarcado () {
		Escritor.getInstance().imprimir("Marcado", Arrays.toString(Marcado));
	}
}
