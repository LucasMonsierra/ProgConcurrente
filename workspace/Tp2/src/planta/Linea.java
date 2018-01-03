package planta;

import monitor.GestorMonitor;

public class Linea implements Runnable{
	
	private GestorMonitor gm;
	private int[] secuencia;

	
	public Linea(GestorMonitor gm, int[] s) {
		this.gm = gm;
		this.secuencia = s;
	}
	
	@Override
	public void run() {
		for(;;){//int j=0;j<500;j++) {
			int i = 0;
			while (i < secuencia.length) {
				try {
					gm.dispararTransicion(secuencia[i]);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				i++;
			}
		}
	}

}
