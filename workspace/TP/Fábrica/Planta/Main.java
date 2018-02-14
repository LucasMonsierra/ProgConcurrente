package Planta;

import java.io.IOException;

import Gestor.GestorMonitor;
import recursos.Maquina2;
import recursos.Maquina3;
import recursos.Maquina4;
import recursos.Robot1;
import recursos.Robot2;
import recursos.Robot3;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		Maquina1 m1 = new Maquina1();
		Maquina2 m2 = new Maquina2();
		Maquina3 m3 = new Maquina3();
		Maquina4 m4 = new Maquina4();
		
		Robot1 r1 = new Robot1();
		Robot2 r2 = new Robot2();
		Robot3 r3 = new Robot3();

		GestorMonitor gm = new GestorMonitor();	
		
		LineaA lineaA1 = new LineaA(m1, m2, m3, m4, r1, r2, r3, gm);
		LineaA lineaA2 = new LineaA(m1, m2, m3, m4, r1, r2, r3, gm);
		LineaA lineaA3 = new LineaA(m1, m2, m3, m4, r1, r2, r3, gm);
		LineaB lineaB = new LineaB(m2, r2, gm);
		LineaC lineaC = new LineaC(m3, m4, r1, r2, r3, gm);
				
		Thread hilo1 = new Thread(lineaA1);
		Thread hilo2 = new Thread(lineaA2);
		Thread hilo3 = new Thread(lineaA3);
		Thread hilo4 = new Thread(lineaB);
		Thread hilo5 = new Thread(lineaC);
		
		hilo1.start();
		hilo2.start();
		hilo3.start();
		hilo4.start();
		hilo5.start();
		
	}
}
