package Planta;

import Gestor.GestorMonitor;
import recursos.Maquina2;
import recursos.Maquina3;
import recursos.Maquina4;
import recursos.Robot1;
import recursos.Robot2;
import recursos.Robot3;

public class LineaA implements Runnable {
	/*private Maquina1 m1;
	private Maquina2 m2;
	private Maquina3 m3;
	private Maquina4 m4;
	private Robot1 r1;
	private Robot2 r2;
	private Robot3 r3;*/
	private GestorMonitor gm;
	private int[] secA1 = {0, 1, 3, 5, 7, 9};
	
	
	public LineaA(Maquina1 m1, Maquina2 m2, Maquina3 m3, Maquina4 m4, Robot1 r1, Robot2 r2, Robot3 r3, GestorMonitor gm) {
		/*this.m1 = m1;
		this.m2 = m2;
		this.m3 = m3;
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
			while (i < secA1.length) {
				gm.dispararTransicion(secA1[i]);
				i++;
			}
		}
	}
}
