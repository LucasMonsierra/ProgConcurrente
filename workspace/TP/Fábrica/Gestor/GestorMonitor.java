package Gestor;

import java.io.IOException;
//import java.lang.management.ManagementFactory;

public class GestorMonitor {
	private Mutex mutex;
	public RdP rdp;
	private Colas colas;
	private Politica politicas;
	private boolean k; //flag;
	//private long id;
	//private static Thread.State STATE;
	int[] m;
	int i;
	
	public GestorMonitor () throws NumberFormatException, IOException {
		rdp = new RdP();
		mutex = new Mutex();
		colas = new Colas();
		politicas = new Politica();
		m = new int[RdP.TRANS];
		//STATE.RUNNABLE;
		//flag = false;
		i = 0;
	}

	public void dispararTransicion (int t)  {

		mutex.adquirirMutex();
		k = true;
		
		//esperoElMutex();
		
		hilo: {
			while ( k == true ) {
				
				//k = politicas.puedo(t) && rdp.estaSencib(t);
				k = rdp.disparar(t);
				
				if ( k == true ) {

					System.out.print(i++ + "\n");
					//rdp.disparar(t);
					//politicas.disparoP(t);
					//System.out.print("Disparo T: " + t + ". Hilo: " + Thread.currentThread().getId() + "\n");
					int[] vs = rdp.getSencibilizadas();
					int[] vc = colas.quienesEstan();
						
					int c = 0;
					for (int i = 0 ; i < m.length ; i++) {
						m[i] = vs[i]*vc[i];
						if (m[i] == 1)
							c++;
					}
					/*System.out.print("\n            0 1 2 3 4 5 6 7 8 9 10      14        19 \n");
					System.out.print("Vector col: ");
					for (int i = 0 ; i < m.length ; i++)
						System.out.print(vc[i] + " ");
					System.out.print("\n");
					System.out.print("Vector sen: ");
					for (int i = 0 ; i < m.length ; i++)
						System.out.print(vs[i] + " ");
					System.out.print("\n");
					System.out.print("Vector mmm: ");
					for (int i = 0 ; i < m.length ; i++)
						System.out.print(m[i] + " ");
					System.out.print("\n\n");*/

					
					if (c > 0) {
						colas.liberarHilo(politicas.cual(m));
						//System.out.print("Desperte un hilo." + Thread.currentThread().getId() + "\n\n");
						break hilo;
					}
					else {
						//System.out.print("No hay hilo para despertar." + Thread.currentThread().getId() + "\n\n");
						k = false;
					}
				}
				else {
					//flag = true;
					//id = Thread.currentThread().getId();
					//System.out.print("La trans " + t + " no está sencib. Hilo : " + Thread.currentThread().getId() + "\n\n");
					mutex.liberarMutex();
					//System.out.print("Encolo: " + t + "\n");
					colas.adquirirCola(t);
				}
				/*if (!k)
					System.out.print("Me voy del monitor." + Thread.currentThread().getId() + "\n\n");
				else
					System.out.print("Vuelvo al WHILE." + Thread.currentThread().getId() + "\n\n");*/
			}
			mutex.liberarMutex();
		}
	}
	
	/*public void esperoElMutex() {

		if (flag) {
			while ( ManagementFactory.getThreadMXBean().getThreadInfo(id).getThreadState() == Thread.State.RUNNABLE ) {}
			flag = false;
		}
	}*/
}
