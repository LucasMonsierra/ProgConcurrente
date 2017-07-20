package monitor;

public class Main {

	public static void main(String[] args) {

		GestorMonitor gm = new GestorMonitor();
		int[] secuenciaA1 = {0, 1, 3, 5, 7, 9};
		int[] secuenciaA2 = {0, 2, 4, 6, 8, 9};
		int[] secuenciaB = {10, 11, 12, 13};
		int[] secuenciaC = {14, 15, 16, 17, 18, 19};
		
		Linea la1 = new Linea(gm,secuenciaA1);
		Linea la2 = new Linea(gm,secuenciaA1);
		Linea la3 = new Linea(gm,secuenciaA2);
		Linea lb = new Linea(gm,secuenciaB);
		Linea lc = new Linea(gm,secuenciaC);
		
		Thread h1 = new Thread(la1);
		Thread h2 = new Thread(la2);
		Thread h3 = new Thread(la3);
		Thread h4 = new Thread(lb);
		Thread h5 = new Thread(lc);
		
		h1.start();
		h2.start();
		h3.start();
		h4.start();
		h5.start();
	}

}
