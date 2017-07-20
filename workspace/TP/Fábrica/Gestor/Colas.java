package Gestor;

import java.util.concurrent.Semaphore;

public class Colas {
	private int trans = RdP.TRANS;	
	private Semaphore[] cola = new Semaphore[trans];
	
	public Colas () {
		for (int i = 0 ; i < trans ; i++ )
			cola[i] = new Semaphore(0,true);
	}
	
	public void adquirirCola (int t) {
		try {
			cola[t].acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void liberarHilo (int t) {
		cola[t].release();
	}
	
	public int[] quienesEstan () {
		int[] enCola = new int[trans];
		
		for (int i = 0 ; i < trans ; i++ ) {
			if (cola[i].hasQueuedThreads()) { enCola[i] = 1; }
			else { enCola[i] = 0; }
		}
					
		return enCola;
	}
}
