package monitor;

public class Linea implements Runnable{
	
	private GestorMonitor gm;
	private int[] secuencia;

	
	public Linea(GestorMonitor gm, int[] s) {
		this.gm = gm;
		this.secuencia = s;
	}
	
	@Override
	public void run() {
		for(;;) {
			int i = 0;
			while (i < secuencia.length) {
				gm.dispararTransicion(secuencia[i]);
				i++;
			}
		}
	}

}