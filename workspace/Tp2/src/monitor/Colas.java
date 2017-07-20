package monitor;

import java.util.concurrent.Semaphore;

public class Colas {
	
	private int TRANS;
	private Semaphore[] colas;
	
	public Colas() {
		TRANS = RdP.TRANS;
		colas = new Semaphore[TRANS];
		for (int i = 0 ; i < TRANS ; i++) {
			colas[i] = new Semaphore(0,true);
		}
	}
	
	public void adquirirCola(int t) {
		try {
			colas[t].acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void liberarHilo(int t) {
		colas[t].release();
	}
	
	public int[] quienesEstan() {
		int[] enCola = new int[TRANS];
		
		for (int i = 0 ; i < TRANS ; i++) {
			if (colas[i].hasQueuedThreads()) { enCola[i] = 1; }
			else { enCola[i] = 0; }
		}
		return enCola;
	}

}
