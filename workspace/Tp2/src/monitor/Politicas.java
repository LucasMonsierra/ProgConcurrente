package monitor;

import archivos.*;

public class Politicas {
	
	private static Lector lector = new Lector();
	private int a, b, c;
	
	public Politicas () {
		
		Matriz porcentajes = lector.leerPorcentajes();
		a = porcentajes.getValor(0, 0);
		b = porcentajes.getValor(0, 1);
		c = porcentajes.getValor(0, 2);
	}
	
	
	public int politica1(Matriz m) {
		int t = 0, may = 0;
		int[] politica1 = { 10,  3,  3,  3,  3,  3,  3,  3,  3,  1,  10,  3,  3,  1,  15,  13,  13, 13,  13,  5};
						//{t10,t11,t12,t13,t14,t15,t16,t17,t18,t19, t20,t21,t22,t23, t30, t31, t32,t33, t34,t35} 
		for (int i = 0; i < m.getColumnas() ; i++) {
			if (m.getValor(0, i) == 1 && politica1[i] > may ) { may = politica1[i]; t = i; }
		}
		return t;
	}
	
	public int cual(Matriz m) {
		
		double porcentajePiezasA = calcularPorcentaje(Escritor.getInstance().a, Escritor.getInstance().a + Escritor.getInstance().b + Escritor.getInstance().c);
		double porcentajePiezasB = calcularPorcentaje(Escritor.getInstance().b, Escritor.getInstance().a + Escritor.getInstance().b + Escritor.getInstance().c);
		double porcentajePiezasC = calcularPorcentaje(Escritor.getInstance().c, Escritor.getInstance().a + Escritor.getInstance().b + Escritor.getInstance().c);

		Matriz politica2 = new Matriz(1, m.getColumnas());
		
		if (porcentajePiezasA < a) {
			for ( int i = 0 ; i < 10 ; i++ ) {
				politica2.setValor(0, i, i + 15);
			}
		}
		
		if (porcentajePiezasB < b) {
			for ( int i = 10 ; i < 14 ; i++ ) {
				politica2.setValor(0, i, i);
			}
		}
		
		if (porcentajePiezasC < c) {
			for ( int i = 14 ; i < politica2.getColumnas() ; i++ ) {
				politica2.setValor(0, i, i);
			}
		}
		
		int t = 0, may = 0;
		for (int i = 0; i < m.getColumnas() ; i++) {
			if (m.getValor(0, i) == 1 && politica2.getValor(0, i) >= may ) { 
				may = politica2.getValor(0, i); 
				t = i; 
				}
		}
		return t;
	}

	public double calcularPorcentaje(double piezas, double total) {
		return (piezas/total)*100;
	}
}
