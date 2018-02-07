package monitor;

import archivos.*;

public class Politicas {
	
	private static Lector lector = new Lector();
	
	public int cual(Matriz m) {
		
		Matriz porcentajes = lector.leerPorcentajes();
		Matriz politica = new Matriz(1, m.getColumnas());
		Matriz piezas = piezasHechas();
		double[] porcentajesHechos = new double[porcentajes.getColumnas()];
		
		for ( int i = 0 ; i < porcentajes.getColumnas() ; i++ ) {
			porcentajesHechos[i] = calcularPorcentaje(piezas.getValor(0, i), piezas.getValor(0, 3));
		}

		
		if (porcentajesHechos[0] < porcentajes.getValor(0, 0)) {
			for ( int i = 0 ; i < 10 ; i++ ) {
				politica.setValor(0, i, i + 15);
			}
		}
		
		if (porcentajesHechos[1] < porcentajes.getValor(0, 1)) {
			for ( int i = 10 ; i < 14 ; i++ ) {
				politica.setValor(0, i, i);
			}
		}
		
		if (porcentajesHechos[2] < porcentajes.getValor(0, 2)) {
			for ( int i = 14 ; i < politica.getColumnas() ; i++ ) {
				politica.setValor(0, i, i);
			}
		}
		
		int t = 0, may = 0;
		for (int i = 0; i < m.getColumnas() ; i++) {
			if (m.getValor(0, i) == 1 && politica.getValor(0, i) >= may ) { 
				may = politica.getValor(0, i); 
				t = i; 
			}
		}
		return t;
	}

	public double calcularPorcentaje(double piezas, double total) {
		return (piezas/total)*100;
	}
	
	public Matriz piezasHechas () {
		Matriz piezas = new Matriz(1, 4);
		piezas.setValor(0, 0, ((System.getProperty("a") != null) ? Integer.parseInt(System.getProperty("a")) : 0 ));
		piezas.setValor(0, 1, ((System.getProperty("b") != null) ? Integer.parseInt(System.getProperty("b")) : 0 ));
		piezas.setValor(0, 2, ((System.getProperty("c") != null) ? Integer.parseInt(System.getProperty("c")) : 0 ));
		for ( int i = 0 ; i < piezas.getColumnas() - 1 ; i++ ) {
			piezas.setValor(0, 3, piezas.getValor(0, 3) + piezas.getValor(0, i));
		}
		return piezas;
	}
}
