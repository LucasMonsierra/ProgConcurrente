package monitor;

import java.util.concurrent.Semaphore;

public class GestorMonitor {
	
	private Semaphore mutex;
	private RdP rdp;
	private Colas colas;
	private Politicas politicas;
	private boolean k;
	private int[] m;
	private int disp;
	
	public GestorMonitor() {
		mutex = new Semaphore(1,true);
		rdp = new RdP();
		colas = new Colas();
		politicas = new Politicas();
		m = new int[RdP.TRANS];
		disp = 0;
	}
	
	public void dispararTransicion(int t) {
		try {
			mutex.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		k = true;
		
		hilo: {
			while (k == true) {
				k = rdp.shoot(t);
				
				if (k == true) {
					System.out.print(disp++ + ". T: " + t +"\n");
					int[] vs = rdp.getSencib();
					int[] vc = colas.quienesEstan();
					
					int c = 0;
					for (int i = 0 ; i < m.length ; i++) {
						m[i] = vs[i]*vc[i];
						if (m[i] == 1) { c++; }
					}
/*############################################################*/
/*##############    IMPRESIONES POR PANTALLA	##############*/
/*############################################################*/
/*					System.out.print("SENCIB:");
					for (int i = 0 ; i < m.length ; i++) {System.out.print(vs[i]+ " ");}
					System.out.print("\n");
					System.out.print("ENCOLA:");
					for (int i = 0 ; i < m.length ; i++) {System.out.print(vc[i]+ " ");}
					System.out.print("\n");
					System.out.print("VECTOM:");
					for (int i = 0 ; i < m.length ; i++) {System.out.print(m[i]+ " ");}
					System.out.print("\n\n");
/*############################################################*/
/*############################################################*/
/*############################################################*/
					
					if (c > 0) {
						colas.liberarHilo(politicas.cual(m));
						break hilo; 
					}
					else { k = false; }
				}
				else {
					System.out.print("NoSenc: " + t + "\n");
					mutex.release();
					System.out.print("Encolo: " + t + "\n");
					colas.adquirirCola(t);
				}
			}
			mutex.release();
		}
	}
}
