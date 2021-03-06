package monitor;

import archivos.Escritor;
import archivos.Lector;
import archivos.Matriz;

import test.JunitCore;

import java.util.HashMap;

public class RdP {
	
	private int TRANS, PLAZAS, a, b, c;
	private long ventana;
	private long[] timestamp;
	private Matriz Marcado, MatrizI, alfa, beta;
	private HashMap<String, Matriz> hash;
	private JunitCore jUnitCore;
	private Escritor escritor;
	
	public RdP() {
		
		Lector lector = new Lector();
		hash = lector.cargarDatos();
		
		Marcado = hash.get("marcado");
		MatrizI = hash.get("incidencia");
		
		alfa = hash.get("alfa");
		beta = hash.get("beta");
				
		PLAZAS = (MatrizI.getFilas());
		TRANS = (MatrizI.getColumnas());
		
		timestamp = new long[TRANS];
		calculoTimeStamp();	
		
		jUnitCore = new JunitCore();
		escritor = new Escritor();
	}
	
	public Matriz getVectorSencibilizadas() {
		int f;
		Matriz sencib = new Matriz(1, TRANS);
		
		for (int i = 0 ; i < TRANS ; i++) {
			f = 0;
			for (int j = 0 ; j < PLAZAS ; j++) {
				if (Marcado.getValor(0, j) + MatrizI.getValor(j, i) > 0) { sencib.setValor(0, i, 1); }
				else if (Marcado.getValor(0, j) + MatrizI.getValor(j, i) < 0) { f++; }
			}
			if (f > 0) { sencib.setValor(0, i, 0); }
		}
		return sencib;
	}
	
	public boolean estaSencibilizada(int t) {
		Matriz v = getVectorSencibilizadas();
		
		if (v.getValor(0, t) == 1) { return true; }
		else { return false; }
	}
	
	public void nuevoEstado(int t) {
		for (int i = 0 ; i < PLAZAS ; i++) {
			Marcado.setValor(0, i, Marcado.getValor(0, i) + MatrizI.getValor(i, t));
		}
	}
	
	public boolean disparar(int t) {
		
		if (estaSencibilizada(t)) {
			
			ventana = getVentanaTiempo(t);
			chequearFlag();
			
			if (ventana == 0) { 
				nuevoEstado(t);
				calculoTimeStamp();
				testearMarcado();
				//guardarMarcado(); 
				contarPiezas(t); 
				return true;
			}
			else { return false; }
		}
		else { return false; }
	}
	
	public void chequearFlag () {
		if (Thread.currentThread().getPriority() == Thread.MAX_PRIORITY) {
			ventana = 0; 
			Thread.currentThread().setPriority(Thread.NORM_PRIORITY);
		}
	}
	
	public void calculoTimeStamp() {
		long tiempo = System.currentTimeMillis();
		
		for (int i = 0 ; i < timestamp.length ; i++) {
			if (estaSencibilizada (i)) { timestamp[i] = tiempo; }
			else { timestamp[i] = 0; }
		}	
	}
	
	public long getVentanaTiempo(int t) {
		
		long tiempo = System.currentTimeMillis();
		
		//Es una transici�n sin tiempo
		if (!esTransConTiempo(t)) { return 0; }
		//Es una transici�n con tiempo y est� dentro de la ventana
		else if (onTime(t,tiempo) == 1) { return 0; }
		//Es una transici�n con tiempo y est� antes de la ventana
		else if (onTime(t,tiempo) == 0) { return aDormir(t,tiempo); }
		//Es una transici�n sin tiempo o con tiempo despu�s de la ventana
		return -1;
	}
	
	public int onTime(int trans, long tiempo) {
		int a = alfa.getValor(0,trans);
		int b = beta.getValor(0,trans);
		long t = tiempo - timestamp[trans];
				
		if (beta.esCero()) {
			if (a > t) return 0;
			else return 1;
		}
		else {
			if (a > t) return 0;
			else if (a < t && b > t) return 1;
			else return 2;
		}
	}
	
	public long aDormir(int trans, long tiempo) {
		int a = alfa.getValor(0,trans);
		return (a - (tiempo - timestamp[trans]));
	}
	
	public boolean esTransConTiempo(int t) {
		if (alfa.getValor(0, t) != 0 || beta.getValor(0, t) != 0) { return true; }
		else { return false; }
	}
	
	public void testearMarcado () {
		escritor.guardar(jUnitCore.testearMarcado(hash, Marcado));
	}
	
	public void contarPiezas (int t) {
		switch (t) {
			case 9:
				a++; System.setProperty("a", "" + a); break;
			case 13:
				b++; System.setProperty("b", "" + b); break;
			case 19:
				c++; System.setProperty("c", "" + c); break;
		}
		if ( a + b + c == 100) {
			escritor.generarLogs(a, b ,c);
		}
	}
	
	public int getTrans() {
		return MatrizI.getColumnas();
	}

	public long getVentana() {
		return ventana;
	}
	
	public Matriz getMarcado() {
		return Marcado;
	}
	
	public void setMarcado(Matriz nuevoMarcado) {
		Marcado = nuevoMarcado;
	}
	
	public HashMap<String, Matriz> getHash() {
		return hash;
	}
}
