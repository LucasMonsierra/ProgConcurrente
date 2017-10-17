package monitor;

import archivos.Matriz;

public class GestorMonitor {
	
	private Mutex mutex;
	private RdP rdp;
	private Colas colas;
	private Politicas politicas;
	private Matriz m;
	private int disp;
	
	public GestorMonitor() {
		mutex = new Mutex();
		rdp = new RdP();
		colas = new Colas(rdp.getTrans());
		politicas = new Politicas();
		m = new Matriz(1, rdp.getTrans());
		disp = 0;
	}
	
	public void dispararTransicion(int t) {
		
		hilo: {
			mutex.adquirirMutex();
			while (!rdp.shoot(t)) {				
				mutex.liberarMutex();
				colas.adquirirCola(t);
			}
				
			System.out.print(disp++ + ". T: " + t +"\n");
			Matriz vs = rdp.getSencib();
			Matriz vc = colas.quienesEstan();
			
			m = vs.and(vc);
						
			if (!m.esCero()) {
				colas.liberarHilo(politicas.cual(m));
				break hilo;
			}
			mutex.liberarMutex();
		}
	
	/*
	 mutex.adquirirMutex();
		k = true;
		
		hilo: {
			while (k == true) {
				k = rdp.shoot(t);
				
				if (k == true) {
					System.out.print(disp++ + ". T: " + t +"\n");
					Matriz vs = rdp.getSencib();
					Matriz vc = colas.quienesEstan();
					
					m = vs.and(vc);
					
					if (!m.esCero()) {
						colas.liberarHilo(politicas.cual(m));
						break hilo; 
					}
					else { k = false; }	
				}
				else {
					System.out.print("NoSenc: " + t + "\n");
					mutex.liberarMutex();
					System.out.print("Encolo: " + t + "\n");
					colas.adquirirCola(t);
				}
			}
			mutex.liberarMutex();
		}
	}*/
	}
}
