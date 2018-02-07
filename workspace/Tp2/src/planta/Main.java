package planta;

import monitor.GestorMonitor;

public class Main {

	public static void main(String[] args) {

		GestorMonitor gm = new GestorMonitor();
		int[] entrada = {0};
		int[] secuenciaA1 = {1, 3};
		int[] secuenciaA2 = {5};
		int[] secuenciaA3 = {7};
		int[] salida = {9};
		int[] secuenciaAlt2 = {2, 4, 6, 8};
		int[] secuenciaB = {10, 11, 12, 13};
		int[] secuenciaC1 = {14, 15, 16, 17};
		int[] secuenciaC2 = {18, 19};
		
		Linea la1 = new Linea(gm,entrada);
		Linea la2 = new Linea(gm,secuenciaA1);
		Linea la3 = new Linea(gm,secuenciaA2);
		Linea la4 = new Linea(gm,secuenciaA3);
		Linea la5 = new Linea(gm,salida);
		Linea la6 = new Linea(gm,secuenciaAlt2);
		Linea lb = new Linea(gm,secuenciaB);
		Linea lc1 = new Linea(gm,secuenciaC1);
		Linea lc2 = new Linea(gm,secuenciaC2);
		
		Thread h1 = new Thread(la1);
		Thread h2 = new Thread(la2);
		Thread h3 = new Thread(la3);
		Thread h4 = new Thread(la4);
		Thread h5 = new Thread(la5);
		Thread h6 = new Thread(la6);
		Thread h7 = new Thread(lb);
		Thread h8 = new Thread(lc1);
		Thread h9 = new Thread(lc2);

		h1.start();
		h2.start();
		h3.start();
		h4.start();
		h5.start();
		h6.start();
		h7.start();
		h8.start();
		h9.start();
	}

}
