package Gestor;

import java.util.concurrent.Semaphore;

public class Mutex {
	public Semaphore mutex;
	
	public Mutex () {
		mutex = new Semaphore(1,true);
	}
	
	public void adquirirMutex () {
		try {
			mutex.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void liberarMutex () {
		mutex.release();
	}
}
