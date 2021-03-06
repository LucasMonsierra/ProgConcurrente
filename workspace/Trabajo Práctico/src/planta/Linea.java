package planta;

import monitor.GestorMonitor;
import recursos.*;

public class Linea implements Runnable{
	
	private GestorMonitor gm;
	private int[] secuencia;
	private final static Robot1 r1 = new Robot1();
	private final static Robot2 r2 = new Robot2();
	private final static Robot3 r3 = new Robot3();
	private final static Maquina1 m1 = new Maquina1();
	private final static Maquina2 m2 = new Maquina2();
	private final static Maquina3 m3 = new Maquina3();
	private final static Maquina4 m4 = new Maquina4();
	
	public Linea(GestorMonitor gm, int[] s) {
		this.gm = gm;
		this.secuencia = s;
	}
	
	@Override
	public void run() {
		for(;;){
			int i = 0;
			while (i < secuencia.length) {
				gm.dispararTransicion(secuencia[i]);
				ejecutarAccion(secuencia[i]);
				i++;
			}
		}
	}
	
	public void ejecutarAccion(int t) {
		switch (t) {
			case 0:
				r1.tomarPiezaA();break;
			case 1:
				m1.fabricarPiezaA();break;
			case 2:
				m3.fabricarPiezaA();break;
			case 3:
				r2.trasladarPiezaAalt1();break;
			case 4:
				r2.trasladarPiezaAalt2();break;
			case 5:
				m2.fabricarPiezaA();break;
			case 6:
				m4.fabricarPiezaA();break;
			case 7:
				r3.sacarPiezaAalt1();break;
			case 8:
				r3.sacarPiezaAalt2();break;
			case 10:
				r2.tomarPiezaB();break;
			case 11:
				m2.fabricarPiezaB();break;
			case 12:
				r2.sacarPiezaB();break;
			case 14:
				r3.tomarPiezaC();break;
			case 15:
				m4.fabricarPiezaC();break;
			case 16:
				r2.trasladarPiezaC();break;
			case 17:
				m3.fabricarPiezaC();break;
			case 18:
				r1.sacarPiezaC();break;
			default:
				break;
		}
	}

}
