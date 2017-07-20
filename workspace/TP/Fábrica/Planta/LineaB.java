package Planta;

import Gestor.GestorMonitor;

public class LineaB implements Runnable {
	/*private Maquina2 m2;
	private Robot2 r2;*/
	private GestorMonitor gm;
	private int[] secB = {10, 11, 12, 13};
	
	public LineaB(Maquina2 m2, Robot2 r2, GestorMonitor gm) {
		/*this.m2 = m2;
		this.r2 = r2;*/
		this.gm = gm;
		
	}
	
	@Override
	public void run() {
		for (;;) {
			int i = 0;
			while (i < secB.length) {
				gm.dispararTransicion(secB[i]);
				i++;
			}
		}
	}
}
