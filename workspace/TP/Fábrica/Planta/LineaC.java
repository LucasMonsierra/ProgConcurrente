package Planta;

import Gestor.GestorMonitor;

public class LineaC implements Runnable {
	/*private Maquina3 m3;
	private Maquina4 m4;
	private Robot1 r1;
	private Robot2 r2;
	private Robot3 r3;*/
	private GestorMonitor gm;
	private int[] secC = {14, 15, 16, 17, 18, 19};
	
	public LineaC(Maquina3 m3, Maquina4 m4, Robot1 r1, Robot2 r2, Robot3 r3, GestorMonitor gm) {
		/*this.m3 = m3;
		this.m4 = m4;
		this.r1 = r1;
		this.r2 = r2;
		this.r3 = r3;*/
		this.gm = gm;
	}
	
	@Override
	public void run() {
		for (;;) {
			int i = 0;
			while (i < secC.length) {
				gm.dispararTransicion(secC[i]);
				i++;
			}
		}
	}
}
